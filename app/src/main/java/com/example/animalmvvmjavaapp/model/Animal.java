package com.example.animalmvvmjavaapp.model;

import com.google.gson.annotations.SerializedName;

public class Animal {

    public String name;
    public Taxonomy taxonomy;
    public String location;
    public Speed speed;
    public String diet;

    @SerializedName("lifespan")
    public String lifeSpan;

    @SerializedName("image")
    public String imgUrl;

    public Animal(String name){
        this.name = name;
    }
}


class Taxonomy{
    String kingdom;
    String order;
    String family;
}

class Speed{
    String metric;
    String imperial;
}
