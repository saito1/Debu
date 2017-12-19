package com.edu.rafaelsaito.debu.Modelo;

import java.io.Serializable;

public class ContatoEntity implements Serializable{
    private long id;
    private String name;
    private String address;
    private String telephone;
    private String image;

    public long getId() {
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
}
