package com.example.matopdv;

public class ListItem {
    private String broj2;
    private String plus2;
    private String pdv2;
    private String posto2;
    private String jednako2;
    private String ukupno1;
    private String zagrada1;
    private String ukupno2;
    private String zagrada2;
    private String  tvDatum;

    public ListItem(String broj2, String plus2, String pdv2, String posto2, String jednako2, String ukupno1, String zagrada1, String ukupno2, String zagrada2, String tvDatum) {
        this.broj2 = broj2;
        this.plus2 = plus2;
        this.pdv2 = pdv2;
        this.posto2 = posto2;
        this.jednako2 = jednako2;
        this.ukupno1 = ukupno1;
        this.zagrada1 = zagrada1;
        this.ukupno2 = ukupno2;
        this.zagrada2 = zagrada2;
        this.tvDatum = tvDatum;
    }

    public String getBroj2() {
        return broj2;
    }

    public void setBroj2(String broj2) {
        this.broj2 = broj2;
    }

    public String getPlus2() {
        return plus2;
    }

    public void setPlus2(String plus2) {
        this.plus2 = plus2;
    }

    public String getPdv2() {
        return pdv2;
    }

    public void setPdv2(String pdv2) {
        this.pdv2 = pdv2;
    }

    public String getPosto2() {
        return posto2;
    }

    public void setPosto2(String posto2) {
        this.posto2 = posto2;
    }

    public String getJednako2() {
        return jednako2;
    }

    public void setJednako2(String jednako2) {
        this.jednako2 = jednako2;
    }

    public String getUkupno1() {
        return ukupno1;
    }

    public void setUkupno1(String ukupno1) {
        this.ukupno1 = ukupno1;
    }

    public String getZagrada1() {
        return zagrada1;
    }

    public void setZagrada1(String zagrada1) {
        this.zagrada1 = zagrada1;
    }

    public String getUkupno2() {
        return ukupno2;
    }

    public void setUkupno2(String ukupno2) {
        this.ukupno2 = ukupno2;
    }

    public String getZagrada2() {
        return zagrada2;
    }

    public void setZagrada2(String zagrada2) {
        this.zagrada2 = zagrada2;
    }

    public String getTvDatum() {
        return tvDatum;
    }

    public void setTvDatum(String tvDatum) {
        this.tvDatum = tvDatum;
    }
}
