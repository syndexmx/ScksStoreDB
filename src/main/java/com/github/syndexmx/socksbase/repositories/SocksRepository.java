package com.github.syndexmx.socksbase.repositories;

import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {

    @Query(value = "SELECT SUM(amount) FROM socks WHERE colour = ?1 AND (cotton BETWEEN ?2 AND ?3)",
            nativeQuery = true)
    Long getMatchingAmount(String colour, int moreThan, int lessThan);

    @Query(value = "SELECT SUM(amount) FROM socks WHERE colour = ?1 AND cotton = ?2",
            nativeQuery = true)
    Long getExactlyMatchingAmount(String colour, int equal);
}
