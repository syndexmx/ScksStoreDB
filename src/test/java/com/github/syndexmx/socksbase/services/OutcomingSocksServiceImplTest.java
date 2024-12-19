package com.github.syndexmx.socksbase.services;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.impl.OutcomingSocksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.syndexmx.socksbase.model.TestSocks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OutcomingSocksServiceImplTest {

    @Mock
    private SocksRepositoryService socksRepositoryService;

    @InjectMocks
    OutcomingSocksServiceImpl serviceUnderTest;

    @Test
    void testThatSocksAreSubtracted() {
        Socks socks = testSocks;
        Socks doubleSocks = testDoubleSocks;
        doReturn(Optional.of(socks))
                .when(socksRepositoryService
                .findTypeByColourAndCotton(eq(socks.getColour()), eq(socks.getCotton())));
        doReturn(doubleSocks)
                .when(socksRepositoryService.save(eq(socks)));
        Socks result = serviceUnderTest.removeSocks(socks);
        assertEquals(doubleSocks, result);
        // TO DO : make it work
    }

    @Test
    void testThatExceptionIsThrownWhenNoType() {
        Socks socks = testSocks;
        Socks doubleSocks = testDoubleSocks;
        doReturn(Optional.empty())
                .when(socksRepositoryService
                        .findTypeByColourAndCotton(eq(socks.getColour()), eq(socks.getCotton())));
        doReturn(doubleSocks)
                .when(socksRepositoryService.save(eq(socks)));
        try {
            Socks result = serviceUnderTest.removeSocks(socks);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("No such socks"));
        }
        // TO DO : make it work
    }

}
