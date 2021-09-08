package it.unica.avis.utils;

import it.unica.avis.model.Donazione;
import it.unica.avis.model.Sessione;
import it.unica.avis.model.Prenotazione;
import it.unica.avis.model.Utente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Utils {
    
    public static void checkString(String param, int min, int max) throws Exception{
        
        if(param == null)
            throw new Exception("Parametro nullo");
        
        if(param.length() < min || param.length() > max)
            throw new Exception("Stringa" + " (" + param + ") " + "non valida: "
                    + "deve avere una dimensione compresa tra "
                    + min + " e " + max + " caratteri.");
    }
    
    public static void checkInteger(String param, int min, int max) throws Exception{
    
        try{
            int valore = Integer.valueOf(param);
            if(valore<min || valore>max)
                throw new Exception("Numero non valido: "
                        + "deve essere compreso tra " + min + " e " + max);
        } catch(NumberFormatException e){
            throw new Exception("La stringa non rappresenta un numero intero");
        }
    }

    public static String convertTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + "_" + (cal.get(Calendar.MONTH) + 1) + "_" 
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
    }
    
    public static void checkEmail(String email) throws Exception {
        // Controlla la presenza dei carratteri speciali
        if(!email.contains("@") || !email.contains("."))
            throw new Exception("L'e-mail inserita non è valida");
    }
    
    public static Double[] oreDellaSessione(String inizio, String fine, List<Double> orePrenotate) throws Exception {
        List<Double> lista = new ArrayList<>();
        Double ini = Double.parseDouble(inizio);
        Double fin = Double.parseDouble(fine);
        
        if(ini >= fin) throw new Exception("Ora di inizio >= ora di fine");
        
        while(ini < fin){
            if(!orePrenotate.contains(ini))
                lista.add(ini);
             ini += 0.30;
             if(((ini - ini.intValue())*100) >= 59 ) 
                 ini = ini.intValue() + 1.0;
        }
        
        return lista.toArray(new Double[0]);
    }
    
    // Data
    public static String dataCorrente(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        return dtf.format(LocalDateTime.now()).toString();
    }
    
    // Data e ora
    public static String dataCorrenteCompleta(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        return dtf.format(LocalDateTime.now()).toString();
    }
    
    public static void debug(String x){
        System.out.println("*** Debug check for \"" + x + "\" ***");
    }
    
    // Controlla il numero di donazioni effettuate nell'anno corrente
    public static boolean disponibilitaUtente(List<Donazione> donazioniEffettuate, Utente utente){
        String corrente = Utils.dataCorrente().split("/")[0];
        String donazione;
        int offsetMesi, meseMax = 0, count = 0, donUltimoMese = 0;
        
        // Uomo = 4 donazione all'anno, Donna = 2 donazioni all'anno
        if(utente.getSesso().toUpperCase().equals("M"))
            offsetMesi = 4;
        else 
            offsetMesi = 2;
        
        for(int i= 0; i < donazioniEffettuate.size(); i++){
            
            // Controllo limite di donazioni in un anno
            donazione = donazioniEffettuate.get(i).getSessione().getData().split("-")[0];
            if(Integer.parseInt(donazione) > meseMax){
                meseMax = Integer.parseInt(donazione);
                count = 1;
                
                if(Integer.parseInt(Utils.dataCorrente().split("/")[1]) > donUltimoMese)
                    donUltimoMese = Integer.parseInt(Utils.dataCorrente().split("/")[1]);
            }
            else if(Integer.parseInt(donazione) == meseMax){
                count++;
            }

            // Donazioni anno corrente
            if(count >= offsetMesi){
                return false;
            }
        }
        
            // Controllo 90 giorni dall'ultima donazione effettuata
            corrente = Utils.dataCorrente().split("/")[1];
            if(Integer.parseInt(corrente) >= donUltimoMese+3){
                return false;
            }
            
        return true;
    }
    
// Determina se l'utente può o non può prenotarsi per la sessione
public static boolean requisitiPrenotazione(List<Donazione> donazioniEffettuate, Sessione sessione, Utente utente){
    String s = sessione.getData().split("-")[0];
    int limite = 0, count = 0;
    int d1, d2 = Integer.parseInt(sessione.getData().split("-")[2]);
    int m1, m2 = Integer.parseInt(sessione.getData().split("-")[1]);
    int y1, y2 = Integer.parseInt(sessione.getData().split("-")[0]);
    
    if(utente.getSesso().toUpperCase().equals("M"))
        limite = 4;
    else
        limite = 2;
    
    for(int i = 0; i < donazioniEffettuate.size(); i++){
        if(s.equals(donazioniEffettuate.get(i).getSessione().getData().split("-")[0]))
            count++;
        
        // 90 giorni
        y1 = Integer.parseInt(donazioniEffettuate.get(i).getSessione().getData().split("-")[0]);
        m1 = Integer.parseInt(donazioniEffettuate.get(i).getSessione().getData().split("-")[1]);
        d1 = Integer.parseInt(donazioniEffettuate.get(i).getSessione().getData().split("-")[2]);
        if((y2 - y1) * 12 + (m2 - m1) + 1 < 3)
            return false;
        else if((y2 - y1) * 12 + (m2 - m1) + 1 > 3 && d2 < d1)
            return false;
    }
    
    
    if(count >= limite)
        return false;
    
    return true;
}
}
