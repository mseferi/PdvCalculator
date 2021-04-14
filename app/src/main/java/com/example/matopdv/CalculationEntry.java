package com.example.matopdv;

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

    Double getFormattedBaseNumber() {
        Double frmBaseNmb = baseNumber;
        String.format("%.2f", frmBaseNmb);

        return frmBaseNmb;
    }

    Double getTotalAmount() {
        double amount = baseNumber + (baseNumber * (vatAmount / 100));
        String.format("%.2f", amount);

        return amount;
    }

    String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy.", Locale.getDefault());
        String dateTime = sdf.format(new Date());

        return dateTime;
    }

    
}
