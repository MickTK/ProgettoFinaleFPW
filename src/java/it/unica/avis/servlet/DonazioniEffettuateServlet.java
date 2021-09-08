package it.unica.avis.servlet;

import it.unica.avis.model.Medico;
import it.unica.avis.model.MedicoFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
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

@WebServlet(urlPatterns = {"/DonazioniEffettuateServlet"})
public class DonazioniEffettuateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("user_amm") != null){
            List<Prenotazione> prenotazioni = PrenotazioneFactory.getInstance().getPrenotazioni();
            List<Prenotazione> p = new ArrayList<>();
            List<Medico> medici = MedicoFactory.getInstance().getMedici();
            
            try{
                
                String[] data = Utils.dataCorrente().split("/");
                String[] comp;
                
                for(int i = 0; i < prenotazioni.size(); i++){
                    comp = prenotazioni.get(i).getSessione().getData().split("-");
                    
                    if(Integer.parseInt(comp[0]) < Integer.parseInt(data[0]))
                        p.add(prenotazioni.get(i));
                    else if(Integer.parseInt(comp[0]) == Integer.parseInt(data[0]) &&
                                Integer.parseInt(comp[1]) < Integer.parseInt(data[1]))
                        p.add(prenotazioni.get(i));
                    else if(Integer.parseInt(comp[0]) == Integer.parseInt(data[0]) &&
                                Integer.parseInt(comp[1]) == Integer.parseInt(data[1]) &&
                                Integer.parseInt(comp[2]) <= Integer.parseInt(data[2]))
                        p.add(prenotazioni.get(i));
                }
                
                request.setAttribute("medici", medici.toArray(new Medico[0]));
                request.setAttribute("prenotazioni", p.toArray(new Prenotazione[0]));
                request.getRequestDispatcher("Amministratore/donazioniEffettuate.jsp").forward(request, response);
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
