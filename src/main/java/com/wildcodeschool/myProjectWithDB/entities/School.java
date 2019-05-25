package com.wildcodeschool.myProjectWithDB.entities;

public class School {


    private int id;
    private String name;
    private int capacity;
    private String country;
    private String graduate;

    public School(int id, String name, int capacity, String country, String graduate) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.country = country;
        this.graduate = graduate;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
