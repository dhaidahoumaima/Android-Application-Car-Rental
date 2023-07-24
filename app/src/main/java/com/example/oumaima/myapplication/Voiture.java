package com.example.oumaima.myapplication;

public class Voiture {
    private String name;
    private String phone;
    private String price;
    private String image;
    private String marque;
    private String model;
    private String description;
    private String ville;
    private String boite_vitesse;




    public Voiture(){

    }

    public Voiture(String name, String phone, String price, String image, String marque, String model, String description, String ville, String boite_vitesse) {
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.image = image;
        this.marque = marque;
        this.model = model;
        this.description = description;
        this.ville = ville;
        this.boite_vitesse = boite_vitesse;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public String getVille() {
        return ville;
    }

    public String getBoite_vitesse() {
        return boite_vitesse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setBoite_vitesse(String boite_vitesse) {
        this.boite_vitesse = boite_vitesse;
    }
}
