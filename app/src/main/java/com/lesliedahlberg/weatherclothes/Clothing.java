package com.lesliedahlberg.weatherclothes;

/**
 * Created by lesliedahlberg on 2016-10-10.
 * This class contains information about one item of clothing
 * It should be used in conjunction with a database as a class to feed an adapter
 */
public class Clothing {
    public int identifier;
    public String title;
    public String description;
    public String illustration;

    public Clothing(int identifier, String title, String description, String illustration){
        this.identifier = identifier;
        this.title = title;
        this.description = description;
        this.illustration = illustration;
    }
}
