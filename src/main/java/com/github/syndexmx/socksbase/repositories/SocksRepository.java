package com.github.syndexmx.socksbase.repositories;

import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {
}
