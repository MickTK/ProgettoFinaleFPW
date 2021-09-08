package it.unica.avis.model;

public class Sessione {
    private int id;
    private String data;
    private String inizio;
    private String fine;
    private String luogo;
   
    public int getId(){
        return id;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getInizio() {
        return inizio;
    }

    public void setInizio(String inizio) {
        this.inizio = inizio;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public void setId(int id) {
        this.id = id;
    }
}
