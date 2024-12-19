package com.github.syndexmx.socksbase.services;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.impl.CorrectionSocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.syndexmx.socksbase.model.TestSocks.testSocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CorrectionSocksServiceImplTest {

    @Mock
    private SocksRepositoryService socksRepositoryService;

    @InjectMocks
    CorrectionSocksServiceImpl serviceUnderTest;

    @Test
    void testThatSocksAreCorrected() {
        Socks socks = testSocks;
        when(socksRepositoryService.save(eq(socks))).thenReturn(socks);
        final Socks result = serviceUnderTest.correctSocks(socks);
        assertEquals(socks, result);
    }


}
