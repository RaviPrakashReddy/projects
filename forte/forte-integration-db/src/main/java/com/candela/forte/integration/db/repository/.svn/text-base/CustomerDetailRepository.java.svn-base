package com.candela.forte.integration.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candela.forte.integration.db.entity.CustomerDetail;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {

	@Query(value=" FROM CustomerDetail customerDetail WHERE customerDetail.mobilemaster.mobileno=:mobileno",nativeQuery=false)
	public CustomerDetail getCustomerDetails(@Param("mobileno") String mobileno) throws Exception,Error;
	
}
