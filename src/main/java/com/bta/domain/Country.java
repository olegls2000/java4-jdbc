package com.bta.domain;

public class Country {
    private long id;
    private String name;
    private long population;
    private City capital;

    public Country(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public Country(long id, String name, long population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", capital=" + capital +
                '}';
    }
}
