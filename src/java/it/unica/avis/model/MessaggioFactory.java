package it.unica.avis.model;

import it.unica.avis.database.DatabaseManager;
import it.unica.avis.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessaggioFactory {
    
    private static MessaggioFactory instance;
    private MessaggioFactory() {}
    public static MessaggioFactory getInstance() {
        if (instance == null)
          instance = new MessaggioFactory();
        return instance;
    }
    
    public List<MessaggioAmministratore> getMessaggiAmministratore(){
        List<MessaggioAmministratore> lista = new ArrayList<>();
        MessaggioAmministratore messaggio;
          
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM messaggio_amministratore";
        stmt = conn.prepareStatement(query);
        set = stmt.executeQuery();
        
        while (set.next()){
            messaggio = new MessaggioAmministratore();

            messaggio.setId(set.getInt("id"));
            messaggio.setData(set.getString("data"));
            messaggio.setTesto(set.getString("testo"));
            messaggio.setAmministratore(AmministratoreFactory.getInstance().getAmministratore());
            messaggio.setDestinatario(set.getString("destinatario"));

            lista.add(messaggio);
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
    
    public List<MessaggioUtente> getMessaggiUtenti(String data){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM messaggio_utente JOIN utente" + 
                                 "ON utente.codicefiscale = messaggio_utente.utente_cf" + 
                                 "WHERE messaggio_utente.data =  ?";
        
        stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        set = stmt.executeQuery();
        
        
        if (set.next()) {
            
            List<MessaggioUtente> lista = new ArrayList<>();
            
            MessaggioUtente messaggio = new MessaggioUtente();
          
            messaggio.setId(set.getInt("id"));
            messaggio.setData(set.getString("data"));
            messaggio.setTesto(set.getString("testo"));
            messaggio.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("codicefiscale")));

            lista.add(messaggio);
            
            while (set.next()){
                messaggio = new MessaggioUtente();
          
                messaggio.setId(set.getInt("id"));
                messaggio.setData(set.getString("data"));
                messaggio.setTesto(set.getString("testo"));
                messaggio.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("codicefiscale")));

                lista.add(messaggio);
            }
            
            return lista;
            
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
    public List<MessaggioUtente> getMessaggiUtenti(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        List<MessaggioUtente> lista = new ArrayList<>();
        MessaggioUtente messaggio = new MessaggioUtente();
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * FROM messaggio_utente";
        
        stmt = conn.prepareStatement(query);
        set = stmt.executeQuery();
        while (set.next()) {
            messaggio = new MessaggioUtente();
          
            messaggio.setId(set.getInt("id"));
            messaggio.setData(set.getString("data"));
            messaggio.setTesto(set.getString("testo"));
            messaggio.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));

            lista.add(messaggio);
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
    
    public void setMessaggioAmministratore(String data, String testo, String destinatario, Amministratore amministratore){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO messaggio_amministratore VALUES (default, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        stmt.setString(2, testo);
        stmt.setInt(3, amministratore.getId());
        stmt.setString(4, destinatario);
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
    
    public void setMessaggioUtente(String data, String testo, Utente utente){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "INSERT INTO messaggio_utente VALUES(default, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, data);
            stmt.setString(2, testo);
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
}
  