package it.unica.avis.servlet;

import it.unica.avis.model.DonazioneFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
import it.unica.avis.utils.Utils;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SalvaPrenotazioneServlet", urlPatterns = {"/SalvaPrenotazioneServlet"})
public class SalvaPrenotazioneServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        try{
            if(session != null && session.getAttribute("user_amm") != null){
            String id = request.getParameter("id");
            String quantita = request.getParameter("quantita");
            String note = request.getParameter("note");
            String stato = request.getParameter("stato");
            int medico_id = Integer.parseInt(request.getParameter("medico_id"));
        
            try{
                Integer.parseInt(quantita);
            }
            catch(Exception e){
                throw new Exception(" quantita' non valida");
            }
            
            if(stato.equals("conferma")){
                try{
                    Prenotazione prenotazione = PrenotazioneFactory.getInstance().getPrenotazionePerId(Integer.parseInt(id));
                    prenotazione.setNote(note);
                    DonazioneFactory.getInstance().donazioneEffettuata(prenotazione, quantita, medico_id);
                    PrenotazioneFactory.getInstance().eliminaPrenotazione(prenotazione.getId());
                }
                catch(Exception e){
                    Utils.debug(e.getMessage());
                }
            }
            else if(stato.equals("elimina")){
                try{
                    PrenotazioneFactory.getInstance().eliminaPrenotazione(Integer.parseInt(id));
                }
                catch(Exception e){
                    Utils.debug(e.getMessage());
                }
            }
        }
        else{
            response.sendRedirect("./index");
        }
        }
        catch(Exception e){
            request.setAttribute("riscontro", e.getMessage());
            request.getRequestDispatcher("confermaJSON.jsp").forward(request, response);
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
