package it.unica.avis.servlet;

import it.unica.avis.model.Medico;
import it.unica.avis.model.MedicoFactory;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.PrenotazioneFactory;
import it.unica.avis.model.Sessione;
import it.unica.avis.model.SessioneFactory;
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

@WebServlet(urlPatterns = {"/SessioniAmministratoreServlet"})
public class SessioniAmministratoreServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("user_amm") != null){
            List<Sessione> sessioni = SessioneFactory.getInstance().getSessioni();
            String[] data = Utils.dataCorrente().split("/");
            String[] a;
            List<Sessione> s = new ArrayList<>();
            List<Double> dob = new ArrayList<>();
            
            try{
                for(int i = 0;  i < sessioni.size(); i++){
                a = sessioni.get(i).getData().split("-");
                dob = PrenotazioneFactory.getInstance().getOrePrenotate(sessioni.get(i).getId());
                        
                if(Utils.oreDellaSessione(sessioni.get(i).getInizio(), sessioni.get(i).getFine(), dob).length > 0)
                    if(Integer.parseInt(a[0]) >= Integer.parseInt(data[0]))
                        s.add(sessioni.get(i));
                    else if(Integer.parseInt(a[1]) >= Integer.parseInt(data[1]))
                        s.add(sessioni.get(i));
                    else if(Integer.parseInt(a[2]) >= Integer.parseInt(data[2]))
                        s.add(sessioni.get(i));
                }
                request.setAttribute("sessione", s.toArray(new Sessione[0]));
                
                // Preleva tutti i medici dal database e li passa dalla jsp
                List<Medico> medici = MedicoFactory.getInstance().getMedici();
                request.setAttribute("medici", medici.toArray(new Medico[0]));
                
                
                request.getRequestDispatcher("Amministratore/sessioni.jsp").forward(request, response);
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
