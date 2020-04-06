package com.candela.forte.integration.db.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.candela.forte.integration.db.entity.SmsFollowupDetail;

@Component
@Repository
public interface SmsFollowupDetailRepository extends JpaRepository<SmsFollowupDetail, Long>{

	@Modifying
	@Transactional
	@Query(value="update {h-schema}sms_followup_details set secondRemDate=:secondRemDate where mobileno=:mobileno",nativeQuery=true)
	public void updatesecondRemDate(@Param("secondRemDate") Date date,@Param("mobileno") String mobileno)throws Exception,Error;
	
	@Query(value=" FROM SmsFollowupDetail smsFollowupDetail WHERE smsFollowupDetail.mobilemaster.mobileno=:mobileno",nativeQuery=false)
	public SmsFollowupDetail findByMobileno(String mobileno)throws Exception,Error;
}
