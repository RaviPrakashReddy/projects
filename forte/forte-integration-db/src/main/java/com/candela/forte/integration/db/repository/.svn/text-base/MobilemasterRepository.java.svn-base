package com.candela.forte.integration.db.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.candela.forte.integration.db.entity.Mobilemaster;

@Component
@Repository
public interface MobilemasterRepository extends JpaRepository<Mobilemaster, String>{
	//and mobilemaster.registerDate >= (CURRENT_DATE - interval '7 day')
	@Query(value=" FROM Mobilemaster mobilemaster WHERE mobilemaster.casecreated='N' and mobilemaster.infopresent='N' ",nativeQuery=false)
	public List<Mobilemaster> getPendingCustomerDetails(Pageable pageable) throws Exception,Error;
	
	@Modifying
	@Transactional
	@Query(value="update {h-schema}Mobilemaster set casecreated='Y' where mobileno=:mobileno",nativeQuery=true)
	public void updateCaseCreateFlag(@Param("mobileno") String mobileno)throws Exception,Error;
	
	

	@Modifying
	@Transactional
	@Query(value="update {h-schema}Mobilemaster set infopresent='Y' where mobileno=:mobileno",nativeQuery=true)
	public void updateInfoPresentFlag(@Param("mobileno") String mobileno)throws Exception,Error;
}
