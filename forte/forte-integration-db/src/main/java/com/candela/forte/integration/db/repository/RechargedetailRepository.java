package com.candela.forte.integration.db.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.candela.forte.integration.db.entity.Rechargedetail;

@Component
@Repository
public interface RechargedetailRepository extends JpaRepository<Rechargedetail, String>{
	
	
	@Query(value="SELECT id FROM Rechargedetail rechargedetail WHERE rechargedetail.rechargedate=:rechargedate and rechargedetail.packageid=:packageid and "
			+ " rechargedetail.mobilemaster.mobileno=:mobileno",nativeQuery=false)
	public List<Long> chectDuplicateRecharges(@Param("rechargedate") Timestamp rechargedate, @Param("packageid") String packageid,@Param("mobileno") String mobileno) throws Exception,Error;
	
	
	@Query(value="SELECT sum(premium) FROM Rechargedetail rechargedetail WHERE rechargedetail.monthlyReferenceNumber=:monthlyReferenceNumber and rechargedetail.mobilemaster.mobileno=:mobileno group by monthlyReferenceNumber",nativeQuery=false)
	public Integer getMonthTotalPremiun(@Param("monthlyReferenceNumber") String monthlyReferenceNumber,@Param("mobileno") String mobileno) throws Exception,Error;
	
	
	@Query(value="FROM Rechargedetail rechargedetail WHERE rechargedetail.rechargedate>=:rechargedate and rechargedetail.mobilemaster.mobileno=:mobileno ",nativeQuery=false)
	public List<Rechargedetail>  getRechargeHistory(@Param("rechargedate") Timestamp rechargedate,@Param("mobileno") String mobileno) throws Exception,Error;
	
	
	

}
