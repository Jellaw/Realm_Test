package com.example.realmtest;

import io.realm.RealmObject;

public class Country extends RealmObject {
    private String name;
    private String population;
    private String id;

    public Country(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Country: "
                 + name + '\n' +
                "population=" + population + '\n' +
                "id=" + id;
    }
}
