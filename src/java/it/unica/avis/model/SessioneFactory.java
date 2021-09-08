package it.unica.avis.model;

import it.unica.avis.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessioneFactory {
  private static SessioneFactory instance;
  private SessioneFactory() {}
  public static SessioneFactory getInstance() {
    if (instance == null)
      instance = new SessioneFactory();
    return instance;
  }
    
    public Sessione getSessione(String data){
        Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "SELECT * " +
                                    "FROM sessione " +
                                    "WHERE sessione.data = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        set = stmt.executeQuery();
        if (set.next()) {
          Sessione sessione = new Sessione();
          
          sessione.setData(set.getString("data"));
          sessione.setLuogo(set.getString("luogo"));
          sessione.setLuogo(set.getString("inizio"));
          sessione.setLuogo(set.getString("fine"));
          
          return sessione;
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

    public void setSessione(String data, String luogo, String inizio, String fine){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO sessione VALUES (default, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        stmt.setString(2, luogo);
        stmt.setString(3, inizio);
        stmt.setString(4, fine);
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
    
     public List<Sessione> getSessioni(){
        List<Sessione> sessioni = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM sessione";
          stmt = conn.prepareStatement(query);
          set = stmt.executeQuery();
          while(set.next()) {
            Sessione sessione = new Sessione();

            sessione.setId(set.getInt("id"));
            sessione.setData(set.getString("data"));
            sessione.setLuogo(set.getString("luogo"));
            sessione.setInizio(set.getString("inizio"));
            sessione.setFine(set.getString("fine"));

            sessioni.add(sessione);
          }
        } catch (SQLException e) {
          Logger.getLogger(UtenteFactory.class.getName()).
          log(Level.SEVERE, null, e);
        } 
        finally {
          try {set.close();} catch (Exception e) {}
          try {stmt.close();} catch (Exception e) {}
          try {conn.close();} catch (Exception e) {}
        }
        return sessioni;
      }
     
     public Sessione getSessioneById(String id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM sessione WHERE id=?";
          stmt = conn.prepareStatement(query);
          stmt.setInt(1, Integer.parseInt(id));
          set = stmt.executeQuery();
          if(set.next()) {
            Sessione sessione = new Sessione();

            sessione.setId(set.getInt("id"));
            sessione.setData(set.getString("data"));
            sessione.setLuogo(set.getString("luogo"));
            sessione.setInizio(set.getString("inizio"));
            sessione.setFine(set.getString("fine"));

            return sessione;
          }
        } catch (SQLException e) {
          Logger.getLogger(UtenteFactory.class.getName()).
          log(Level.SEVERE, null, e);
        } 
        finally {
          try {set.close();} catch (Exception e) {}
          try {stmt.close();} catch (Exception e) {}
          try {conn.close();} catch (Exception e) {}
        }
        return null;
      }
}
