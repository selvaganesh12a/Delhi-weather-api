package com.example.ExcelDataToDataBase.Repository;

import com.example.ExcelDataToDataBase.Entity.MonthlyTemperatureSummary;
import com.example.ExcelDataToDataBase.Entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather,String> {

    List<Weather> findByDatetimeUtc(LocalDateTime date);

    @Query("SELECT w FROM Weather w WHERE FUNCTION('MONTH', w.datetimeUtc) = :month")
    List<Weather> findByMonth(@Param("month") int month);

    @Query(value = "SELECT new com.example.ExcelDataToDataBase.Entity.MonthlyTemperatureSummary(" +
            "FUNCTION('MONTH', w.datetimeUtc) AS month, " +
            "MAX(w.tempm), " +
            "MIN(w.tempm), " +
            "(SELECT sub.tempm FROM Weather sub WHERE FUNCTION('MONTH', sub.datetimeUtc) = FUNCTION('MONTH', w.datetimeUtc) AND FUNCTION('YEAR', sub.datetimeUtc) = FUNCTION('YEAR', w.datetimeUtc) ORDER BY sub.tempm LIMIT 1 OFFSET " +
            "(SELECT COUNT(*) / 2 FROM Weather sub_count WHERE FUNCTION('MONTH', sub_count.datetimeUtc) = FUNCTION('MONTH', w.datetimeUtc) AND FUNCTION('YEAR', sub_count.datetimeUtc) = FUNCTION('YEAR', w.datetimeUtc))" +
            ") " +
            ") " +
            "FROM Weather w " +
            "WHERE FUNCTION('YEAR', w.datetimeUtc) = :year " +
            "GROUP BY FUNCTION('MONTH', w.datetimeUtc)")
    List<MonthlyTemperatureSummary> findMonthlyTemperatureByYear(@Param("year") int year);
}
