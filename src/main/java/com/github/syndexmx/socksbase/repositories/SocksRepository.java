package com.github.syndexmx.socksbase.repositories;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {

    @Query(value = "SELECT SUM(amount) FROM socks WHERE colour = ?1 AND (cotton BETWEEN ?2 AND ?3)",
            nativeQuery = true)
    Long getMatchingAmount(String colour, int moreThan, int lessThan);

    @Query(value = "SELECT SUM(amount) FROM socks WHERE colour = ?1 AND cotton = ?2",
            nativeQuery = true)
    Long getExactlyMatchingAmount(String colour, int equal);

    @Query(value = "SELECT * FROM socks WHERE cotton BETWEEN ?1 AND ?2",
            nativeQuery = true)
    List<SocksEntity> getMatchingList(int moreThan, int lessThan);

    @Query(value = "SELECT * FROM socks WHERE cotton = ?1",
            nativeQuery = true)
    List<SocksEntity> getExactlyMatchingList(Integer equal);
}
