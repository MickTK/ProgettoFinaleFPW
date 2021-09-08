package it.unica.avis.servlet;

import it.unica.avis.model.Donazione;
import it.unica.avis.model.DonazioneFactory;
import it.unica.avis.model.MessaggioAmministratore;
import it.unica.avis.model.MessaggioFactory;
import it.unica.avis.model.MessaggioUtente;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
import it.unica.avis.model.Sessione;
import it.unica.avis.model.SessioneFactory;
import it.unica.avis.model.Utente;
import it.unica.avis.model.UtenteFactory;
import it.unica.avis.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ContattiServlet", urlPatterns = {"/contatti"})
public class ContattiServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("user") != null){
            try{
                String user = (String) session.getAttribute("user");
                Utente utente = UtenteFactory.getInstance().getUtenteByUsername(user);
                // Tutti i messaggi dell'amministratore presenti nel db
                List<MessaggioAmministratore> messaggi = MessaggioFactory.getInstance().getMessaggiAmministratore();
                // Messaggi che l'utente visualizzerà
                List<MessaggioAmministratore> m = new ArrayList<>();
                // Donazioni efffettuate dall'utente
                List<Donazione> donazioniEffettuate = DonazioneFactory.getInstance().getDonazioniEffettuatePerUtente(user);
                List<Prenotazione> prenotazioni = PrenotazioneFactory.getInstance().getPrenotazionePerUtente(user);
                
                for(int i = 0; i < messaggi.size(); i++){
                    // Per utenti che possono donare
                    if(messaggi.get(i).getDestinatario().equals("disp")){
                        if(donazioniEffettuate == null || donazioniEffettuate.size() <= 0)
                            m.add(messaggi.get(i));
                        else if(Utils.disponibilitaUtente(donazioniEffettuate, utente)){
                            m.add(messaggi.get(i));
                        }
                    }
                    // Per utente destinatario del messaggio
                    else if(messaggi.get(i).getDestinatario().equals(user)){
                        m.add(messaggi.get(i));
                    }
                    // Per utenti che sono prenotati per una determinata sessione
                    else{
                        for(int j = 0; j < prenotazioni.size(); j++){
                            if(prenotazioni.get(j).getSessione().getData().equals(messaggi.get(i).getDestinatario())){
                                m.add(messaggi.get(i));
                            }
                        }
                    }
                }
                
                List<MessaggioAmministratore> n = new ArrayList<>();
                
                // Riordina i messaggi
                for(int i = m.size()-1; i >= 0; i--)
                    n.add(m.get(i));
                
                request.setAttribute("ricevuti", n);
                request.getRequestDispatcher("messaggiUtenti.jsp").forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
                request.setAttribute("link", "areaPersonale.jsp");
                request.getRequestDispatcher("error.jsp").forward(request, response); // Inoltra alla pagina di errore
            }
        } else{
            response.sendRedirect("login.jsp");
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
