package com.example.ExcelDataToDataBase.Controller;

import com.example.ExcelDataToDataBase.Entity.MonthlyTemperatureSummary;
import com.example.ExcelDataToDataBase.Entity.Weather;
import com.example.ExcelDataToDataBase.Service.ExcelService;
import com.example.ExcelDataToDataBase.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) return ResponseEntity.badRequest().body("Please upload a valid Excel File");

        System.out.println("Received file: " + file.getOriginalFilename());

        try {
            excelService.importExcelData(file.getInputStream());
            return ResponseEntity.ok("Weather Data imported successfully!");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error while parsing the file: " + e.getMessage());
        }
    }

    @GetMapping("/date/{date}")
    public List<Weather> getWeatherByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime datetime){
        return weatherService.getWeatherDetailsByDate(datetime);
    }

    @GetMapping("/month/{month}")
    public List<Weather> getWeatherByMonth(@PathVariable int month){
        return weatherService.getWeatherDetailsByMonth(month);
    }

    @GetMapping("/summary/{year}")
    public List<MonthlyTemperatureSummary> getSummaryByYear(@PathVariable int year) {
        return weatherService.getMonthlyTemperatureSummary(year);
    }
}
