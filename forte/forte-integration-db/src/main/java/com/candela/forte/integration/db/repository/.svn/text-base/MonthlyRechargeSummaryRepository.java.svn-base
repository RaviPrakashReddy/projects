package com.candela.forte.integration.db.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.candela.forte.integration.db.entity.MonthlyRechargeSummary;

@Component
@Repository
public interface MonthlyRechargeSummaryRepository extends JpaRepository<MonthlyRechargeSummary, Long>{

	@Modifying
	@Query(value=" INSERT INTO {h-schema}monthly_recharge_summary(mobileno,month_reference_number,month_total_recharge,carry_forward_amount,sent_sms,last_modified_date,"
			+ "monthly_premium_amount,monthly_recharge_amount,previous_month_carryforward_amount,date_formate) " + 
		
			"select totalrecords.mobileno,totalrecords.monthly_reference_number,CASE WHEN  COALESCE(totalrecords.totalmonthlyRecharge,0)+COALESCE(totalrecords.prevoiusmonthCarryamount,0)<=(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) THEN COALESCE(totalrecords.totalmonthlyRecharge,0)+COALESCE(totalrecords.prevoiusmonthCarryamount,0)  "
			+ " ELSE (select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) end "
			+ " , CASE WHEN COALESCE(totalrecords.totalmonthlyRecharge,0)+COALESCE(totalrecords.prevoiusmonthCarryamount,0)<=(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) THEN 0 " + 
			"			                       ELSE COALESCE(totalrecords.totalmonthlyRecharge,0)+COALESCE(totalrecords.prevoiusmonthCarryamount,0)-(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) " + 
			"			      end ,'N',CURRENT_DATE,COALESCE(totalrecords.totalmonthlyRecharge,0) as totalPremiumAmount,monthlyRechargeAmount,COALESCE(totalrecords.prevoiusmonthCarryamount,0) as previousmonthcarryforwardsamount,to_char(CURRENT_DATE - interval '1 month', 'Mon-YYYY') from ("+
			
			
			"select mobileno,monthly_reference_number,COALESCE(sum(premium),0) as totalmonthlyRecharge, COALESCE(sum(recharge_amount),0) as monthlyRechargeAmount,"
			+ " (select COALESCE(carry_forward_amount,0) from {h-schema}monthly_recharge_summary as mmm where mmm.mobileno=rechargede.mobileno and mmm.month_reference_number=:prevoiusMonthRefe)  as prevoiusmonthCarryamount" + 
			"	from {h-schema}rechargedetails as rechargede  where monthly_reference_number=:monthly_reference_number and mobileno not in(select distinct mobileno from {h-schema}monthly_recharge_summary where month_reference_number=:monthly_reference_number) group by mobileno ,monthly_reference_number"
			+ ") as totalrecords"
			
			,nativeQuery=true)
	@Transactional
	public void insertMonthlySummary(@Param("monthly_reference_number") String monthlyReferenceNumber ,@Param("property_name") String propertyName, @Param("prevoiusMonthRefe") String prevoiusMonthRefe)throws Exception,Error;
	
	
	
	@Modifying
	@Query(value=" UPDATE {h-schema}monthly_recharge_summary  " + 
			"SET month_reference_number = recharge_details.monthly_reference_number, " + 
			"    month_total_recharge = recharge_details.totalpremium+carry_forward_amount , " + 
			"    carry_forward_amount = " + 
			"    	CASE WHEN recharge_details.totalpremium+carry_forward_amount<=12 THEN 0 " + 
			"                        ELSE recharge_details.totalpremium+carry_forward_amount-12 " + 
			"       end, " + 
			"    sent_sms='N', " + 
			"    last_modified_date=recharge_details.currentdate " + 
			"FROM ( " + 
			"   select mobileno,monthly_reference_number,sum(premium) as totalpremium, CASE WHEN sum(premium)<=15000 THEN 0 " + 
			"                        ELSE sum(premium)-15000 " + 
			"       end,'N', CURRENT_DATE as currentdate  from  {h-schema}rechargedetails  where monthly_reference_number=:monthly_reference_number and mobileno in(select distinct mobileno from {h-schema}monthly_recharge_summary where month_reference_number!=:monthly_reference_number) " + 
			"    group by mobileno ,monthly_reference_number " + 
			"    ) AS recharge_details  " + 
			"WHERE  " + 
			"    recharge_details.mobileno = monthly_recharge_summary.mobileno",nativeQuery=true)
	@Transactional
	public void updateMonthlySummary(@Param("monthly_reference_number") String monthlyReferenceNumber)throws Exception,Error;
	
	

	@Query(value=" from MonthlyRechargeSummary as mrs where mrs.monthReferenceNumber=:monthReferenceNumber and mrs.sentSms='N' order by mrs.mobileno desc",nativeQuery=false)
	public List<MonthlyRechargeSummary> findMonthlyReferenceNumber(@Param("monthReferenceNumber") String monthReferenceNumber, Pageable pageable);
	
//	@Modifying
//	@Query(value=" UPDATE {h-schema}monthly_recharge_summary  " + 
//			"SET month_reference_number =:monthly_reference_number , " + 
//			"    month_total_recharge = carry_forward_amount , " + 
//			"    carry_forward_amount = " + 
//			"    	CASE WHEN carry_forward_amount<=(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) THEN 0 " + 
//			"                        ELSE carry_forward_amount-(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name)" + 
//			"       end, " + 
//			"    sent_sms='N', " + 
//			"    last_modified_date=CURRENT_DATE where  month_reference_number=:last_monthly_reference_number and carry_forward_amount>0 ",nativeQuery=true)
//	
//	 @Transactional
//	public void updateMonthlyNotRechargedSummary(@Param("monthly_reference_number") String monthlyReferenceNumber,@Param("property_name") String propertyName,@Param("last_monthly_reference_number") String lastmonthlyReferenceNumber)throws Exception,Error;
//	
	
	
	@Modifying
	@Query(value=" INSERT INTO {h-schema}monthly_recharge_summary(mobileno,month_reference_number,month_total_recharge,carry_forward_amount,sent_sms,last_modified_date,"
			+ "monthly_premium_amount,monthly_recharge_amount,previous_month_carryforward_amount,date_formate) " + 
			
			"  select "
			
			+ " finalrecords.mobileno,:monthly_reference_number,CASE WHEN COALESCE(finalrecords.carry_forward_amount,0)<=(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) "
			+ " THEN  COALESCE(finalrecords.carry_forward_amount,0) " + 
			"	   ELSE (select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) end ," + 
			"  CASE WHEN COALESCE(finalrecords.carry_forward_amount,0)<=(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) THEN 0 " + 
			"	   ELSE COALESCE(finalrecords.carry_forward_amount,0)-(select CAST ( property_value AS integer ) as maxpremiunValue from {h-schema}system_properties where  property_name=:property_name) " + 
			"  end ,'N',CURRENT_DATE,0,0,COALESCE(finalrecords.carry_forward_amount,0),to_char(CURRENT_DATE - interval '1 month', 'Mon-YYYY')"

			+ ""
			+ " from {h-schema}monthly_recharge_summary finalrecords where finalrecords.mobileno in"
			+ "((select prev.mobileno from {h-schema}monthly_recharge_summary prev where prev.month_reference_number=:last_monthly_reference_number and prev.carry_forward_amount>0 "
			+ " EXCEPT "
			+ "select curr.mobileno from {h-schema}monthly_recharge_summary curr where curr.month_reference_number=:monthly_reference_number ) "
			+ " EXCEPT "
			+ " select rd.mobileno from {h-schema}rechargedetails rd where rd.monthly_reference_number=:monthly_reference_number) "
			+ "and finalrecords.month_reference_number=:last_monthly_reference_number and finalrecords.carry_forward_amount>0 "
			
		
			
			,nativeQuery=true)
	 @Transactional
	public void updateMonthlyNotRechargedSummary(@Param("monthly_reference_number") String monthlyReferenceNumber,@Param("property_name") String propertyName,@Param("last_monthly_reference_number") String lastmonthlyReferenceNumber)throws Exception,Error;
	
	
	@Query(value=" from MonthlyRechargeSummary as mrs where mrs.mobileno=:mobileno and mrs.lastModifiedDate>=:lastModifiedDate",nativeQuery=false)
	public List<MonthlyRechargeSummary> getMonthlySummaryDetails(@Param("lastModifiedDate") Date lastModifiedDate, @Param("mobileno") String mobileno)throws Exception,Error;
	
	
 }
