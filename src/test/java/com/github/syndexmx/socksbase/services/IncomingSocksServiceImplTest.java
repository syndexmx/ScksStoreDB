package com.github.syndexmx.socksbase.services;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.impl.CorrectionSocksServiceImpl;
import com.github.syndexmx.socksbase.services.impl.IncomingSocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.syndexmx.socksbase.model.TestSocks.testSocks;
import static com.github.syndexmx.socksbase.model.TestSocks.testTripleSocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IncomingSocksServiceImplTest {

    @Mock
    private SocksRepositoryService socksRepositoryService;

    @InjectMocks
    IncomingSocksServiceImpl serviceUnderTest;

    @Test
    void testThatSocksAreAdded() {
        Socks socks = testSocks;
        Socks tripleSocks = testTripleSocks;
        when(socksRepositoryService.save(eq(socks))).thenReturn(tripleSocks);
        final Socks result = serviceUnderTest.addSocks(socks);
        assertEquals(tripleSocks, result);
    }


}
