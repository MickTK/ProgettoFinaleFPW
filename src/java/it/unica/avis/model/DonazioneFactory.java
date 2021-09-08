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

public class DonazioneFactory {
    
    private static DonazioneFactory instance;
  private DonazioneFactory() {}
  public static DonazioneFactory getInstance() {
    if (instance == null)
      instance = new DonazioneFactory();
    return instance;
  }
    
    public List<Donazione> getDonazioniEffettuatePerUtenteConData(String nome, String cognome, String data){
        List<Donazione> lista = new ArrayList<>();
        Donazione donazione = new Donazione();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * " +
                                      "FROM donazione_effettuata JOIN utente " +
                                      "ON donazione_effettuata.utente_cf=utente.codicefiscale AND utente.nome=? " +
                                      "AND utente.cognome=? AND utente.nascita=?";
          stmt = conn.prepareStatement(query);
          stmt.setString(1, nome);
          stmt.setString(2, cognome);
          stmt.setString(3, data);
          set = stmt.executeQuery();
          while (set.next()) {
            donazione = new Donazione();
              
            donazione.setId(set.getInt("id"));
            donazione.setOrario(set.getString("orario"));
            donazione.setQuantita(set.getString("quantita"));
            donazione.setNote(set.getString("note"));
            donazione.setSessione(SessioneFactory.getInstance().getSessioneById(set.getString("sessione_id")));
            donazione.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));
            
            lista.add(donazione);
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
    
    public void donazioneEffettuata(Prenotazione prenotazione, String quantita, int medico){
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet set = null;
      try {
        conn = DatabaseManager.getInstance().getDbConnection();
        String query = "INSERT INTO donazione_effettuata VALUES (default, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, prenotazione.getOrario());
        stmt.setString(2, quantita);
        stmt.setInt(3, prenotazione.getSessione().getId());
        stmt.setString(4, prenotazione.getUtente().getCodiceFiscale());
        stmt.setString(5, prenotazione.getNote());
        stmt.setInt(6, medico);
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
    
    // Visualizzate nella cronologia dell'utente
    public List<Donazione> getDonazioniEffettuatePerUtente(String user){
        List<Donazione> lista = new ArrayList<>();
        Donazione donazione = new Donazione();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
          conn = DatabaseManager.getInstance().getDbConnection();
          String query = "SELECT * " +
                                      "FROM donazione_effettuata JOIN utente " +
                                      "ON donazione_effettuata.utente_cf=utente.codicefiscale and utente.username=? ";
          stmt = conn.prepareStatement(query);
          stmt.setString(1, user);
          set = stmt.executeQuery();
          while (set.next()) {
            donazione = new Donazione();
              
            donazione.setId(set.getInt("id"));
            donazione.setOrario(set.getString("orario"));
            donazione.setQuantita(set.getString("quantita"));
            donazione.setSessione(SessioneFactory.getInstance().getSessioneById(set.getString("sessione_id")));
            donazione.setUtente(UtenteFactory.getInstance().getUtenteByCodiceFiscale(set.getString("utente_cf")));
            donazione.setMedico(MedicoFactory.getInstance().getMedicoById(set.getInt("medico_id")));
            donazione.setNote(set.getString("note"));

            lista.add(donazione);
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
