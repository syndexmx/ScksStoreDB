package com.github.syndexmx.socksbase.controllers.csvtools;

import com.github.syndexmx.socksbase.model.Socks;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.syndexmx.socksbase.controllers.csvtools.csvParser.getAllSocksFromBatch;
import static com.github.syndexmx.socksbase.model.TestSocks.testOtherSocks;
import static com.github.syndexmx.socksbase.model.TestSocks.testSocks;
import static org.junit.jupiter.api.Assertions.*;

class csvParserTest {

    @Test
    void testGetAllSocksFromBatch() {
        Socks socks = testSocks;
        socks.setTypeId(0L);
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(socks.getColour());
        csvBuilder.append(",");
        csvBuilder.append(socks.getCotton());
        csvBuilder.append(",");
        csvBuilder.append(socks.getAmount());
        csvBuilder.append("\n");

        Socks otherSocks = testOtherSocks;
        otherSocks.setTypeId(0L);
        csvBuilder.append(otherSocks.getColour());
        csvBuilder.append(",");
        csvBuilder.append(otherSocks.getCotton());
        csvBuilder.append(",");
        csvBuilder.append(otherSocks.getAmount());
        csvBuilder.append("\n");

        List<Socks> expectedList = List.of(socks, otherSocks);

        List<Socks> actualResult = getAllSocksFromBatch(csvBuilder.toString());

        assertEquals(expectedList.toString(), actualResult.toString());
    }

}