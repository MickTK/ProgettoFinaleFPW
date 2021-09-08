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

public class MedicoFactory {
    
    private static MedicoFactory instance;
    private MedicoFactory() {}
    public static MedicoFactory getInstance() {
        if (instance == null)
          instance = new MedicoFactory();
        return instance;
    }
    
    // Restituisce una lista con tutti i medici
    public List<Medico> getMedici(){
        List<Medico> medici = new ArrayList<>();
        Medico medico;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM medico";
          stmt = conn.prepareStatement(query);
          set = stmt.executeQuery();
          while (set.next()) {
            medico = new Medico();

            medico.setId(set.getInt("id"));
            medico.setNome(set.getString("nome"));
            medico.setCognome(set.getString("cognome"));
            medico.setCellulare(set.getString("cellulare"));
            medico.setEmail(set.getString("email"));
            medico.setAsl(set.getString("asl"));

            medici.add(medico);
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
        return medici;
    }
    
    public Medico getMedico(String nome, String cognome){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM medico WHERE nome = ? AND cognome = ?";
          stmt = conn.prepareStatement(query);
          stmt.setString(1, nome);
          stmt.setString(2, cognome);
          set = stmt.executeQuery();
          if (set.next()) {
            Medico medico = new Medico();

            medico.setId(set.getInt("id"));
            medico.setNome(set.getString("nome"));
            medico.setCognome(set.getString("cognome"));
            medico.setCellulare(set.getString("cellulare"));
            medico.setEmail(set.getString("email"));
            medico.setAsl(set.getString("asl"));

            return medico;
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
    
    public Medico getMedicoById(Integer id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM medico WHERE id = ?";
          stmt = conn.prepareStatement(query);
          stmt.setInt(1, id);
          set = stmt.executeQuery();
          if (set.next()) {
            Medico medico = new Medico();

            medico.setId(set.getInt("id"));
            medico.setNome(set.getString("nome"));
            medico.setCognome(set.getString("cognome"));
            medico.setCellulare(set.getString("cellulare"));
            medico.setEmail(set.getString("email"));
            medico.setAsl(set.getString("asl"));

            return medico;
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
    
    public void setMedico(String nome, String cognome, String cellulare, String email, String asl){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO medico VALUES (default, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, nome);
        stmt.setString(2, cognome);
        stmt.setString(3, cellulare);
        stmt.setString(4, email);
        stmt.setString(5, asl);
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
