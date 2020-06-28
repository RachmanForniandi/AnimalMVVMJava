package com.example.animalmvvmjavaapp.model;

public class Animal {

    public String name;
    public Taxonomy taxonomy;
    public String location;
    public Speed speed;
    public String diet;
    public String lifeSpan;
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
