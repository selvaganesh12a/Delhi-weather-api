package com.example.ExcelDataToDataBase.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Weather {
    @Id
    private LocalDateTime datetime_utc;
    private String conds;
    private int dewptm;
    private int fog;
    private int hail;
    private float heatindexm;
    private int hum;
    private int pressurem;
    private int rain;
    private int snow;
    private int tempm;
    private int thunder;
    private int tornado;
    private int vism;
    private int wdird;
    private String wdire;
    private float wgustm;
    private float windchillm;
    private float wspdm;
}
