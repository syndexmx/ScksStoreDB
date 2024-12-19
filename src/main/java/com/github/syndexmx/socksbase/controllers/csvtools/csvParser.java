package com.github.syndexmx.socksbase.controllers.csvtools;

import com.github.syndexmx.socksbase.model.Socks;
import lombok.extern.slf4j.Slf4j;

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
            throw new RuntimeException("Improper CSV line format.");
        }
        Long typeId = 0L;
        String colour = cells[0];
        Integer cotton = Integer.parseInt(cells[1]);
        Integer amount = Integer.parseInt(cells[2]);
        Socks socks = Socks.builder()
                .typeId(typeId)
                .colour(colour)
                .cotton(cotton)
                .amount(amount)
                .build();
        return socks;
    }

}
