package it.unica.avis.model;

import it.unica.avis.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AmministratoreFactory {
    
    private static AmministratoreFactory instance;
    private AmministratoreFactory() {}
    public static AmministratoreFactory getInstance() {
        if (instance == null)
          instance = new AmministratoreFactory();
        return instance;
    }
    
    public Amministratore getAmministratore(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * FROM amministratore";
          stmt = conn.prepareStatement(query);
          set = stmt.executeQuery();
          if (set.next()) {
              Amministratore admin = new Amministratore();
              
              admin.setId(set.getInt("id"));
              admin.setCognome(set.getString("cognome"));
              admin.setNome(set.getString("nome"));
              admin.setEmail(set.getString("email"));
              admin.setPassword(set.getString("password"));
              admin.setUsername(set.getString("username"));
              
              return admin;
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
}
