package it.unica.avis.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Utente {
  private String username;
  private String password;
  private String nome;
  private String cognome;
  private String email;
  private String foto;
  private String codiceFiscale;
  private String sesso;
  private String gruppoSanguigno;
  private String patologie;
  private String cellulare;
  private String dataDiNascita;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        String[] x = foto.split("/");
        return x[x.length-2] + "/" + x[x.length-1];
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    public String getPatologie() {
        return patologie;
    }

    public void setPatologie(String patologie) {
        this.patologie = patologie;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    
    @Override
    public String toString(){
        return username + " " + codiceFiscale;
    }
}