package com.candela.forte.integration.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.candela.forte.integration.db.entity.ClaimsDetail;

@Component
@Repository
public interface ClaimsDetailRepository extends JpaRepository<ClaimsDetail, Long> {
	@Query(value=" from ClaimsDetail cd where cd.mobilemaster.mobileno=:mobileno")
	public List<ClaimsDetail> findByMobileno(@Param("mobileno") String mobileno)throws Exception,Error;

}
