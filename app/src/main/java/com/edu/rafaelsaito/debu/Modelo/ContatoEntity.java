package com.edu.rafaelsaito.debu.Modelo;

import java.io.Serializable;

public class ContatoEntity implements Serializable{

    private int id;
    private String name;
    private String address;
    private String telephone;
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
