package com.github.syndexmx.socksbase.controllers.csvtools;

import com.github.syndexmx.socksbase.aspects.exceptionhandling.CustomHttpResponseException;
import com.github.syndexmx.socksbase.model.Socks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class csvParser {

    public static List<Socks> getAllSocksFromBatch(String csvBody) {
        List<Socks> list = new ArrayList<>();
        String lines[] = csvBody.split("\\r?\\n");
        List<String> csvStrings = Arrays.asList(lines);
        log.info("CSV " + csvStrings.size() + " lines.");
        csvStrings.forEach(line -> {if (line.length() > 0) {
            list.add(csvLineToSocks(line));}});
        return list;
    }

    private static Socks csvLineToSocks(String csvLine) {
        String separator = ",";
        if (csvLine.contains(";")) {
            separator = ";";
        }
        String[] cells = csvLine.split(separator);
        if (cells.length != 3) {
            throw new CustomHttpResponseException(HttpStatus.NOT_ACCEPTABLE, "Некорректный формат CSV файла.");
        }
        Long typeId = 0L;
        String colour = cells[0];
        Integer cotton = 0;
        Integer amount = 0;
        try {
            cotton = Integer.parseInt(cells[1]);
            amount = Integer.parseInt(cells[2]);
        } catch (RuntimeException e){
            throw new CustomHttpResponseException(HttpStatus.NOT_ACCEPTABLE, "Некорректный формат численных данных в CSV файле.");
        }
        Socks socks = Socks.builder()
                .typeId(typeId)
                .colour(colour)
                .cotton(cotton)
                .amount(amount)
                .build();
        return socks;
    }

}
