package it.unica.avis.model;

import it.unica.avis.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtenteFactory {
  private static UtenteFactory instance;
  private UtenteFactory() {}
  public static UtenteFactory getInstance() {
    if (instance == null)
      instance = new UtenteFactory();
    return instance;
  }
  /* Viene restituito l'utente che ha effettuato il login */
  public Utente getUtenteByUsernamePassword(String username, String password) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente WHERE username = ? AND password = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        set = stmt.executeQuery();
        if (set.next()) {
          Utente utente = new Utente();
          utente.setUsername(set.getString("username"));
          utente.setPassword(set.getString("password"));
          utente.setNome(set.getString("nome"));
          utente.setCognome(set.getString("cognome"));
          utente.setEmail(set.getString("email"));
          utente.setFoto(set.getString("fotografia"));
          utente.setSesso(set.getString("sesso"));
          utente.setCodiceFiscale(set.getString("codicefiscale"));
          utente.setFoto(set.getString("fotografia"));
          utente.setCellulare(set.getString("cellulare"));
          utente.setGruppoSanguigno(set.getString("grupposanguigno"));
          utente.setPatologie(set.getString("patologie"));
          utente.setDataDiNascita(set.getString("nascita"));
          
          return utente;
        } else {
          return null;
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return null;
    }
  
  public Utente getUtenteByUsername(String username) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente WHERE username = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        set = stmt.executeQuery();
        if (set.next()) {
          Utente utente = new Utente();
          utente.setUsername(set.getString("username"));
          utente.setPassword(set.getString("password"));
          utente.setNome(set.getString("nome"));
          utente.setCognome(set.getString("cognome"));
          utente.setEmail(set.getString("email"));
          utente.setFoto(set.getString("fotografia"));
          utente.setCodiceFiscale(set.getString("codicefiscale"));
          utente.setSesso(set.getString("sesso"));
          utente.setCellulare(set.getString("cellulare"));
          utente.setGruppoSanguigno(set.getString("grupposanguigno"));
          utente.setPatologie(set.getString("patologie"));
          utente.setDataDiNascita(set.getString("nascita"));
          
          return utente;
        } else {
          return null;
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return null;
    }
  
  public List<Utente> getUtenti() {
      List<Utente> lista = new ArrayList<>();
      Utente utente;
      
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente";
        stmt = conn.prepareStatement(query);
        set = stmt.executeQuery();
        while (set.next()) {
          utente = new Utente();
          utente.setUsername(set.getString("username"));
          utente.setPassword(set.getString("password"));
          utente.setNome(set.getString("nome"));
          utente.setCognome(set.getString("cognome"));
          utente.setEmail(set.getString("email"));
          utente.setFoto(set.getString("fotografia"));
          utente.setCodiceFiscale(set.getString("codicefiscale"));
          utente.setSesso(set.getString("sesso"));
          utente.setCellulare(set.getString("cellulare"));
          utente.setGruppoSanguigno(set.getString("grupposanguigno"));
          utente.setPatologie(set.getString("patologie"));
          utente.setDataDiNascita(set.getString("nascita"));
          
          lista.add(utente);
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return lista;
    }
  
  public Utente getUtenteByCodiceFiscale(String codice) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente WHERE codicefiscale = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, codice);
        set = stmt.executeQuery();
        if (set.next()) {
          Utente utente = new Utente();
          utente.setUsername(set.getString("username"));
          utente.setPassword(set.getString("password"));
          utente.setNome(set.getString("nome"));
          utente.setCognome(set.getString("cognome"));
          utente.setEmail(set.getString("email"));
          utente.setFoto(set.getString("fotografia"));
          utente.setCodiceFiscale(set.getString("codicefiscale"));
          utente.setSesso(set.getString("sesso"));
          utente.setCellulare(set.getString("cellulare"));
          utente.setGruppoSanguigno(set.getString("grupposanguigno"));
          utente.setPatologie(set.getString("patologie"));
          utente.setDataDiNascita(set.getString("nascita"));
          
          return utente;
        } else {
          return null;
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return null;
    }
  
  public void registraUtente(
          String nome, String cognome, String email, 
          String username, String password, String sesso, String gruppoSanguigno,
          String patologie, String dataDiNascita, String codiceFiscale, String cellulare,
          String foto
  ){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, codiceFiscale);
        stmt.setString(2, username);
        stmt.setString(3, password);
        stmt.setString(4, nome);
        stmt.setString(5, cognome);
        stmt.setString(6, sesso);
        stmt.setString(7, email);
        stmt.setString(8, gruppoSanguigno);
        stmt.setString(9, patologie);
        stmt.setString(10, foto);
        stmt.setString(11, dataDiNascita);
        stmt.setString(12, cellulare);
        set = stmt.executeQuery();
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
  }
  
  public void modificaUtente(Utente utente){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "UPDATE utente "
                + "SET nome=?, cognome=?, password=?, sesso=?, email=?, grupposanguigno=?, "
                + "cellulare=?, nascita=?, patologie=?, fotografia=? "
                + "WHERE codicefiscale=? AND username=?";
        
        stmt = conn.prepareStatement(query);
        
        
        
        stmt.setString(1, utente.getNome());
        stmt.setString(2, utente.getCognome());
        stmt.setString(3, utente.getPassword());
        stmt.setString(4, utente.getSesso());
        stmt.setString(5, utente.getEmail());
        stmt.setString(6, utente.getGruppoSanguigno());
        stmt.setString(7, utente.getCellulare());
        stmt.setString(8, utente.getDataDiNascita());
        stmt.setString(9, utente.getPatologie());
        stmt.setString(10, utente.getFoto());
        
        stmt.setString(11, utente.getCodiceFiscale());
        stmt.setString(12, utente.getUsername());
        
        set = stmt.executeQuery();
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
  }
  
  public String checkUsername(String user){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente WHERE username = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, user);
        set = stmt.executeQuery();
        if (set.next()) {
          return "invalido";
        } else {
          return "valido";
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return null;
}
  
  public String checkCodiceFiscale(String codice){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM utente WHERE codicefiscale = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, codice);
        set = stmt.executeQuery();
        if (set.next()) {
          return "invalido";
        } else {
          return "valido";
        }
      } catch (SQLException e) {
        Logger.getLogger(UtenteFactory.class.getName()).
        log(Level.SEVERE, null, e);
      } finally {
        try {
          set.close();
        } catch (Exception e) {}
        try {
          stmt.close();
        } catch (Exception e) {}
        try {
          conn.close();
        } catch (Exception e) {}
      }
      return null;
}
}

