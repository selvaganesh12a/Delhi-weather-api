package com.example.ExcelDataToDataBase.Entity;

import lombok.Data;

@Data
public class MonthlyTemperatureSummary {
    private int month;
    private double maxTemp;
    private double minTemp;
    private double medianTemp;

    public MonthlyTemperatureSummary(int month, double maxTemp, double minTemp, double medianTemp){
        this.month = month;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.medianTemp = medianTemp;
    }
}
