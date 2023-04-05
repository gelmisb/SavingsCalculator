package com.example.savingscalculator.calculatesavings;

import android.app.Application;

public class Income extends Application {

    private String wages;
    private String pWages;
    private String cbp;
    private String maintenance;
    private String otherIncome;
    private float totalIncome;


    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getpWages() {
        return pWages;
    }

    public void setpWages(String pWages) {
        this.pWages = pWages;
    }

    public String getCbp() {
        return cbp;
    }

    public void setCbp(String cbp) {
        this.cbp = cbp;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIcome) {
        this.otherIncome = otherIcome;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome() {
        this.totalIncome = Float.parseFloat(wages) + Float.parseFloat(pWages) + Float.parseFloat(cbp) + Float.parseFloat(maintenance) + Float.parseFloat(otherIncome);
    }
}