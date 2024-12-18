package com.github.syndexmx.socksbase.services;

import org.springframework.stereotype.Service;

@Service
public interface SocksFilteredCollectionService {

    Long getFilteredAmount(String colour, int moreThan, int lessThan);

    Long getFilteredAmount(String colour, int equal);
}
