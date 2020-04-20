package com.company;

public class Trader {

    public String getName() {
        return name;
    }

    private final String name;

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
