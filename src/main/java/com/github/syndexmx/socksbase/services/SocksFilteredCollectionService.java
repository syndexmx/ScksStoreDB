package com.github.syndexmx.socksbase.services;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.model.Socks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocksFilteredCollectionService {

    Long getFilteredAmount(String colour, int moreThan, int lessThan);
    Long getFilteredAmount(String colour, int equal);

    List<Socks> getFilteredList(int moreThan, int lessThan);
    List<Socks> getFilteredList(Integer equal);
}
