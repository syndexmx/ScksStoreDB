package com.github.syndexmx.socksbase.services;

import com.github.syndexmx.socksbase.model.Socks;
import org.springframework.stereotype.Service;

@Service
public interface CorrectionSocksService {

    Socks correctSocks(Socks socksToFix);
}
