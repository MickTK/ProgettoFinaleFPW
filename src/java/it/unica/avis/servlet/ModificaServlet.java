package it.unica.avis.servlet;

import it.unica.avis.model.Utente;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "ModificaServlet", urlPatterns = {"/modifica"})
@MultipartConfig
public class ModificaServlet extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    
    try {
        Utente utente = new Utente();
        
        /* Nuovi attributi sessione */
        session.setAttribute("pass", request.getParameter("pass"));
        session.setAttribute("nome", request.getParameter("nome"));
        session.setAttribute("cognome", request.getParameter("cognome"));
        session.setAttribute("email", request.getParameter("email"));
        session.setAttribute("sesso", request.getParameter("sesso"));
        session.setAttribute("gruppoSanguigno", request.getParameter("gruppoSanguigno"));
        session.setAttribute("patologie", request.getParameter("patologie"));
        session.setAttribute("cellulare", request.getParameter("cellulare"));
        session.setAttribute("dataDiNascita", request.getParameter("dataDiNascita"));
        
        /* Salvataggio path immagine */
        String flag = (String)session.getAttribute("foto");
        try{
            Part file = request.getPart("file");
            session.setAttribute("foto", "uploads/" + file.getSubmittedFileName());

            File daSalvare = new File("/home/fpw/Desktop/FPW/ProgettoFinale/web/uploads/" + request.getPart("file").getSubmittedFileName());
            try(InputStream contenutoFile = file.getInputStream()){
                Files.copy(contenutoFile, daSalvare.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            String path = "/home/fpw/Desktop/FPW/ProgettoFinale/web/uploads/" + request.getPart("file").getSubmittedFileName();
            utente.setFoto(path);
        }
        catch(Exception e){
            utente.setFoto(flag);
            session.setAttribute("foto", flag);
        }
        
        /* Creazione nuovo oggetto utente per la sostituzione */
        utente.setCodiceFiscale((String) session.getAttribute("codiceFiscale"));
        utente.setUsername((String) session.getAttribute("user"));
        
        utente.setNome((String) session.getAttribute("nome"));
        utente.setCognome((String) session.getAttribute("cognome"));
        utente.setSesso((String) session.getAttribute("sesso"));
        utente.setGruppoSanguigno((String) session.getAttribute("gruppoSanguigno"));
        utente.setPatologie((String) session.getAttribute("patologie"));
        utente.setDataDiNascita((String) session.getAttribute("dataDiNascita"));
        utente.setEmail((String) session.getAttribute("email"));
        utente.setCellulare((String) session.getAttribute("cellulare"));
        utente.setPassword((String) session.getAttribute("pass"));
        
        
        
        /* Controlli */
        Utils.checkString(utente.getPassword(), 5, 50);
        Utils.checkString(utente.getNome(), 3, 50);
        Utils.checkString(utente.getCognome(), 3, 50);
        Utils.checkString(utente.getCellulare(), 8, 20);
        Utils.checkEmail(utente.getEmail());
        
        UtenteFactory.getInstance().modificaUtente(utente);
        
        response.sendRedirect("areapersonale");
        } catch (Exception e) {
          request.setAttribute("errorMessage", e.getMessage()); // Imposta parametri richiesta
          request.setAttribute("link", "areaPersonale.jsp");
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
