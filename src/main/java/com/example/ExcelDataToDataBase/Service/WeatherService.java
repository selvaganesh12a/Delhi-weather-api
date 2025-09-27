package com.example.ExcelDataToDataBase.Service;

import com.example.ExcelDataToDataBase.Entity.MonthlyTemperatureSummary;
import com.example.ExcelDataToDataBase.Entity.Weather;
import com.example.ExcelDataToDataBase.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<Weather> getWeatherDetailsByDate(LocalDateTime date) {
        return weatherRepository.findByDatetimeUtc(date);
    }

    public List<Weather> getWeatherDetailsByMonth(int month) {
        return weatherRepository.findByMonth(month);
    }

    public List<MonthlyTemperatureSummary> getMonthlyTemperatureSummary(int year) {
        return weatherRepository.findMonthlyTemperatureByYear(year);
    }
}
