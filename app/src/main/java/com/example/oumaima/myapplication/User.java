package com.example.oumaima.myapplication;

public class User {

    private String email;
    private String phone;
    private String nom;
    private String prenom;



    public User(){

    }

    public User(String email, String phone, String nom, String prenom) {

        this.email = email;
        this.phone = phone;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
