package com.example.varosok;

public class Varosok {
    private int id;
    private String  orszag;
    private String varos;
    private int  lakossag;

    public Varosok(int id, String orszag, String varos, int lakossag) {
        this.id = id;
        this.orszag = orszag;
        this.varos = varos;
        this.lakossag = lakossag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public int getLakossag() {
        return lakossag;
    }

    public void setLakossag(int lakossag) {
        this.lakossag = lakossag;
    }
}
