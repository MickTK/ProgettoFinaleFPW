package it.unica.avis.servlet;

import it.unica.avis.model.Donazione;
import it.unica.avis.model.DonazioneFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
import it.unica.avis.model.Sessione;
import it.unica.avis.model.SessioneFactory;
import it.unica.avis.model.Utente;
import it.unica.avis.model.UtenteFactory;
import it.unica.avis.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PrenotazioneServlet", urlPatterns = {"/prenotazione"})
public class PrenotazioneServlet extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            
            try{
                String pk = request.getParameter("pk_sessione");
                String ora = request.getParameter("orarioPrenotazione");
                String cf = (String) session.getAttribute("codiceFiscale");
                
                Sessione s = SessioneFactory.getInstance().getSessioneById(pk);
                Utente u = UtenteFactory.getInstance().getUtenteByCodiceFiscale(cf);
                List<Donazione> donazioniEffettuate = DonazioneFactory.getInstance().getDonazioniEffettuatePerUtente(u.getUsername());
                List<Prenotazione> prenotazioni = PrenotazioneFactory.getInstance().getPrenotazionePerUtente(u.getUsername());
                
                // Impedisce di registrarsi più di una volta alla stessa sessione
                for(int i = 0; i < prenotazioni.size(); i++){
                    if(prenotazioni.get(i).getSessione().getId() == s.getId())
                        throw new Exception("Prenotazione già effettuata per la sessione corrente.");
                }
                
                /* 
                    Confronta la data della sessione con le date delle donazioni effettuate dall'utente
                    per accertarsi che dall'ultima donazione siano passati almeno 90 giorni e che abbia donato meno di 4/2 volte
                */
                if(!Utils.requisitiPrenotazione(donazioniEffettuate, s, u))
                    throw new Exception("Hai superato il numero di donazioni per questo periodo!" + 
                            "\nControlla nel tuo registro delle prenotazioni i requisiti per poterti prenotare.");
                
                PrenotazioneFactory.getInstance().setPrenotazione(ora, s, u);
                request.getRequestDispatcher("cronologia").forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
                request.setAttribute("link", "cronologia");
                request.getRequestDispatcher("error.jsp").forward(request, response); // Inoltra alla pagina di errore
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
