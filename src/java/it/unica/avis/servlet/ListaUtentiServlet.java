package it.unica.avis.servlet;

import it.unica.avis.model.Donazione;
import it.unica.avis.model.DonazioneFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
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

@WebServlet(urlPatterns = {"/ListaUtentiServlet"})
public class ListaUtentiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("user_amm") != null){
            List<Integer> donazioniEffettuate = new ArrayList<>();
            Integer d = 0;
            List<Utente> listaUtenti = UtenteFactory.getInstance().getUtenti();
            List<Donazione> prenotazione = new ArrayList<>();
            
            try{
                for(int i = 0; i < listaUtenti.size(); i++){
                    prenotazione = DonazioneFactory.getInstance().getDonazioniEffettuatePerUtente(listaUtenti.get(i).getUsername());
                    if(prenotazione != null){
                        d = prenotazione.size();
                        donazioniEffettuate.add(d);
                    }
                    else {
                        donazioniEffettuate.add(0);
                    }
                }
                
                request.setAttribute("utenti", listaUtenti.toArray(new Utente[0]));
                request.setAttribute("donazione", donazioniEffettuate.toArray(new Integer[0]));
                
                request.getRequestDispatcher("Amministratore/listaUtenti.jsp").forward(request, response);
            }
            catch(Exception e){
                request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
                request.setAttribute("link", "index.jsp");
                request.getRequestDispatcher("error.jsp").forward(request, response); // Inoltra alla pagina di errore
            }
        }
        else{
            response.sendRedirect("./index");
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
