package com.example.ExcelDataToDataBase.Service;

import com.example.ExcelDataToDataBase.Entity.Weather;
import com.example.ExcelDataToDataBase.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private WeatherRepository weatherRepository;

    private static final int BATCH_SIZE = 500;

    @Transactional
    public void importExcelData(InputStream inputStream) throws Exception {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            List<Weather> weatherBatch = new ArrayList<>();
            int rowCount = 0;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header

                Weather weather = new Weather();
                weather.setDatetime_utc(formatter.formatCellValue(row.getCell(0)));
                weather.setConds(formatter.formatCellValue(row.getCell(1)));
                weather.setDewptm(getIntCell(row.getCell(2)));
                weather.setFog(getIntCell(row.getCell(3)));
                weather.setHail(getIntCell(row.getCell(4)));
                weather.setHeatindexm(getFloatCell(row.getCell(5)));
                weather.setHum(getIntCell(row.getCell(6)));
                weather.setPressurem(getIntCell(row.getCell(8)));
                weather.setRain(getIntCell(row.getCell(9)));
                weather.setSnow(getIntCell(row.getCell(10)));
                weather.setTempm(getIntCell(row.getCell(11)));
                weather.setThunder(getIntCell(row.getCell(12)));
                weather.setTornado(getIntCell(row.getCell(13)));
                weather.setVism(getIntCell(row.getCell(14)));
                weather.setWdird(getIntCell(row.getCell(15)));
                weather.setWdire(formatter.formatCellValue(row.getCell(16)));
                weather.setWgustm(getFloatCell(row.getCell(17)));
                weather.setWindchillm(getFloatCell(row.getCell(18)));
                weather.setWspdm(getFloatCell(row.getCell(19)));

                weatherBatch.add(weather);
                rowCount++;

                if (rowCount % BATCH_SIZE == 0) {
                    weatherRepository.saveAll(weatherBatch);
                    weatherRepository.flush();
                    weatherBatch.clear();
                }
            }

            if (!weatherBatch.isEmpty()) {
                weatherRepository.saveAll(weatherBatch);
                weatherRepository.flush();
            }

        } finally {
            if (workbook != null) workbook.close();
        }
    }

    private int getIntCell(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) return (int) cell.getNumericCellValue();
        try { return Integer.parseInt(cell.getStringCellValue()); } catch (Exception e) { return 0; }
    }

    private float getFloatCell(Cell cell) {
        if (cell == null) return 0f;
        if (cell.getCellType() == CellType.NUMERIC) return (float) cell.getNumericCellValue();
        try { return Float.parseFloat(cell.getStringCellValue()); } catch (Exception e) { return 0f; }
    }
}
