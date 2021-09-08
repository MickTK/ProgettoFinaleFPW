package it.unica.avis.model;

import it.unica.avis.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrenotazioneFactory {
    
    private static PrenotazioneFactory instance;
  private PrenotazioneFactory() {}
  public static PrenotazioneFactory getInstance() {
    if (instance == null)
      instance = new PrenotazioneFactory();
    return instance;
  }
    
    public Prenotazione getPrenotazione(String data, String orario,
            String nomeMedico, String cognomeMedico, String username
    ){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * " +
                                    "FROM prenotazione JOIN sessione " +
                                    "ON sessione.id = prenotazione.sessione_id AND sessione.data = ? " +
                                    "AND prenotazione.orario = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        stmt.setString(2, orario);
        set = stmt.executeQuery();
        if (set.next()) {
          Prenotazione prenotazione = new Prenotazione();
          
          prenotazione.setId(set.getInt("id"));
          prenotazione.setOrario(set.getString("orario"));
          prenotazione.setSessione(SessioneFactory.getInstance().getSessione(data));
          prenotazione.setUtente(UtenteFactory.getInstance().getUtenteByUsername(username));
          
          return prenotazione;
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
    
    public Prenotazione getPrenotazionePerId(int id){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM prenotazione WHERE id=?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        set = stmt.executeQuery();
        if (set.next()) {
          Prenotazione prenotazione = new Prenotazione();
          
          prenotazione.setId(set.getInt("id"));
          prenotazione.setOrario(set.getString("orario"));
          prenotazione.setSessione(SessioneFactory.getInstance().getSessioneById(set.getString("sessione_id")));
          prenotazione.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));
          
          return prenotazione;
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
    
    // Elimina la prenotazione dal db
    public void eliminaPrenotazione(int id){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "DELETE FROM prenotazione WHERE id=?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
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
    
    // Recupera tutte le prenotazioni presenti nel db
    public List<Prenotazione> getPrenotazioni(){
        List<Prenotazione> lista = new ArrayList<>();
        Prenotazione prenotazione = new Prenotazione();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM prenotazione";
          stmt = conn.prepareStatement(query);
          set = stmt.executeQuery();
          while (set.next()) {
            prenotazione = new Prenotazione();
              
            prenotazione.setId(set.getInt("id"));
            prenotazione.setOrario(set.getString("orario"));
            prenotazione.setSessione(SessioneFactory.getInstance().getSessioneById(set.getString("sessione_id")));
            prenotazione.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));
            
            lista.add(prenotazione);
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
    
    // Visualizzate nella cronologia dell'utente
    public List<Prenotazione> getPrenotazionePerUtente(String user){
        List<Prenotazione> lista = new ArrayList<>();
        Prenotazione prenotazione = new Prenotazione();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * " +
                                      "FROM prenotazione JOIN utente " +
                                      "ON prenotazione.utente_cf=utente.codicefiscale AND utente.username=?";
          stmt = conn.prepareStatement(query);
          stmt.setString(1, user);
          set = stmt.executeQuery();
          while (set.next()) {
            prenotazione = new Prenotazione();
              
            prenotazione.setId(set.getInt("id"));
            prenotazione.setOrario(set.getString("orario"));
            prenotazione.setSessione(SessioneFactory.getInstance().getSessioneById(set.getString("sessione_id")));
            prenotazione.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));
            
            lista.add(prenotazione);
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
    
    public void setPrenotazione(String orario, Sessione sessione, Utente utente){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO prenotazione VALUES (default, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, orario);
        stmt.setInt(2, sessione.getId());
        stmt.setString(3, utente.getCodiceFiscale());
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
    
    public List<Double> getOrePrenotate(int id){
        List<Double> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * " +
                                      "FROM prenotazione JOIN sessione " +
                                      "ON sessione.id = prenotazione.sessione_id AND sessione.id=?";
          stmt = conn.prepareStatement(query);
          stmt.setInt(1, id);
          set = stmt.executeQuery();
          while (set.next()) {
            Prenotazione prenotazione = new Prenotazione();
            lista.add(Double.parseDouble(set.getString("orario")));
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
}
