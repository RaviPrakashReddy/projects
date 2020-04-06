package com.candela.forte.integration.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.candela.forte.integration.db.entity.Insurecovermaster;

@Component
@Repository
public interface InsurecovermasterRepository extends JpaRepository<Insurecovermaster, Integer> {

}
