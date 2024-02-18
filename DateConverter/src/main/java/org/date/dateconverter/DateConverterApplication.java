package org.date.dateconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController

public class DateConverterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DateConverterApplication.class, args);
    }

    @GetMapping("/convert")
    public Map<String, String> convertTime(@RequestParam long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map<String, String> result = new HashMap<>();
        result.put("local_time", localDateTime.format(formatter));
        result.put("gmt_time", gmtDateTime.format(formatter));
        return result;
    }
}

