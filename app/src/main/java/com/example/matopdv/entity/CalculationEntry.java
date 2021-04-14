package com.example.matopdv.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalculationEntry implements Serializable {

    private double baseNumber;
    private double vatAmount;
    private String dateCreated;

    public CalculationEntry(double baseNumber, double vatAmount, String dateCreated) {
        this.baseNumber = baseNumber;
        this.vatAmount = vatAmount;
        this.dateCreated = dateCreated;
    }

    public double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(double baseNumber) {
        this.baseNumber = baseNumber;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFormattedBaseNumber() {
        return String.format(Locale.getDefault(), "%.2f", baseNumber);
    }


    public String getTotalAmount(int numberOfDecimalPlaces) {
        return String.format(Locale.getDefault(), "%." + numberOfDecimalPlaces + "f", (baseNumber + (baseNumber * (vatAmount / 100))));
    }


    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy.", Locale.getDefault());
        return sdf.format(new Date());
    }


}
