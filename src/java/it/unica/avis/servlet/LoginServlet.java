package it.unica.avis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unica.avis.model.*;
import it.unica.avis.utils.Utils;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(); // Crea una nuova sessione o recupera quella esistente
        String user = request.getParameter("user"); // Recupera i parametri passati dal client (login.jsp)
        String pass = request.getParameter("pass");
        
        try {
            Utils.checkString(user, 5, 20); // Valida parametri ricevuti
            Utils.checkString(pass, 5, 20); // Valida parametri ricevuti
            Utente utente = UtenteFactory.getInstance().getUtenteByUsernamePassword(user, pass);
            if (utente != null) { // Verifica se le credenziali sono corrette
                
                /* Imposta gli attributi per la sessione corrente */
              session.setAttribute("user", utente.getUsername());
              session.setAttribute("pass", utente.getPassword());
              session.setAttribute("codiceFiscale", utente.getCodiceFiscale());
              session.setAttribute("nome", utente.getNome());
              session.setAttribute("cognome", utente.getCognome());
              session.setAttribute("email", utente.getEmail());
              
              String a = utente.getFoto();
              String[] x = a.split("/");
              a = x[x.length-2] + "/" + x[x.length-1];
              
              session.setAttribute("foto", a);
              session.setAttribute("sesso", utente.getSesso());
              session.setAttribute("gruppoSanguigno", utente.getGruppoSanguigno());
              session.setAttribute("patologie", utente.getPatologie());
              session.setAttribute("cellulare", utente.getCellulare());
              session.setAttribute("dataDiNascita", utente.getDataDiNascita());
              session.setAttribute("lastLogin", Utils.convertTime(session.getLastAccessedTime())); // Imposta last login
                
                response.sendRedirect("areapersonale"); // Redirect alla servlet user
              }
              else {
                throw new Exception("User o pass non validi!");
              }
            } catch (Exception e) {
              session.invalidate(); // Invalida sessione
              request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
              request.setAttribute("link", "login.jsp");
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
