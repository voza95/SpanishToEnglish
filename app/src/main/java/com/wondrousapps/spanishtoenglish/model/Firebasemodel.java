package com.wondrousapps.spanishtoenglish.model;

public class Firebasemodel {
String engphrases,spaphrases,title;

    public Firebasemodel(String engphrases, String spaphrases, String title) {
        this.engphrases = engphrases;
        this.spaphrases = spaphrases;
        this.title = title;
    }

    public Firebasemodel(String engphrases, String spaphrases) {
        this.engphrases = engphrases;
        this.spaphrases = spaphrases;
    }


    public String getEngphrases() {
        return engphrases;
    }

    public void setEngphrases(String engphrases) {
        this.engphrases = engphrases;
    }

    public String getSpaphrases() {
        return spaphrases;
    }

    public void setSpaphrases(String spaphrases) {
        this.spaphrases = spaphrases;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
