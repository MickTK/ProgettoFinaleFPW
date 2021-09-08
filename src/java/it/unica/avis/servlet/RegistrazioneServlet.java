package it.unica.avis.servlet;

import it.unica.avis.model.UtenteFactory;
import it.unica.avis.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "RegistrazioneServlet", urlPatterns = {"/registrazione"})
@MultipartConfig
public class RegistrazioneServlet extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String sesso = request.getParameter("sesso");
        String gruppoSanguigno = request.getParameter("gruppoSanguigno");
        String patologie = request.getParameter("patologie");
        String codiceFiscale = request.getParameter("codiceFiscale");
        String cellulare = request.getParameter("cellulare");
        String dataDiNascita = request.getParameter("dataDiNascita");
        
        try {
            /* Valida parametri ricevuti */
            Utils.checkString(user, 5, 20);
            Utils.checkString(pass, 5, 20);
            Utils.checkString(nome, 3, 50);
            Utils.checkString(cognome, 3, 50);
            Utils.checkString(codiceFiscale, 12, 16);
            Utils.checkString(cellulare, 8, 20);
            Utils.checkEmail(email);
            /* Salvataggio path immagine */
            Part file = request.getPart("file");
            File daSalvare = new File("/home/fpw/Desktop/FPW/ProgettoFinale/web/uploads/" + request.getPart("file").getSubmittedFileName());
            try(InputStream contenutoFile = file.getInputStream()){
                Files.copy(contenutoFile, daSalvare.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            String path = "/home/fpw/Desktop/FPW/ProgettoFinale/web/uploads/" + request.getPart("file").getSubmittedFileName();
            UtenteFactory.getInstance().registraUtente(
                    nome, cognome, email, user, pass, sesso,
                    gruppoSanguigno, patologie, dataDiNascita, codiceFiscale,
                    cellulare, path
            );
            
            
            response.sendRedirect("login.jsp");
            } catch (Exception e) {
                if(!e.getMessage().equals("/home/fpw/Desktop/FPW/ProgettoFinale/web/uploads"))
                    request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
              
                else
                    request.setAttribute("errorMessage", "Fotografia non inserita");
                request.setAttribute("link", "registrazione.jsp");
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
