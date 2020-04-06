package com.candela.forte.integration.db.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.entity.ClaimsDetail;
import com.candela.forte.integration.db.entity.CustomerDetail;
import com.candela.forte.integration.db.entity.Insurecovermaster;
import com.candela.forte.integration.db.entity.Mobilemaster;
import com.candela.forte.integration.db.entity.MonthlyRechargeSummary;
import com.candela.forte.integration.db.entity.Packagemaster;
import com.candela.forte.integration.db.entity.Rechargedetail;
import com.candela.forte.integration.db.entity.SmsFollowupDetail;
import com.candela.forte.integration.db.entity.SystemProperty;
import com.candela.forte.integration.db.repository.ClaimsDetailRepository;
import com.candela.forte.integration.db.repository.CustomerDetailRepository;
import com.candela.forte.integration.db.repository.InsurecovermasterRepository;
import com.candela.forte.integration.db.repository.MobilemasterRepository;
import com.candela.forte.integration.db.repository.MonthlyRechargeSummaryRepository;
import com.candela.forte.integration.db.repository.PackagemasterRepository;
import com.candela.forte.integration.db.repository.RechargedetailRepository;
import com.candela.forte.integration.db.repository.SmsFollowupDetailRepository;
import com.candela.forte.integration.db.repository.SystemPropertyRepository;

@Service
public class ForteDBService {

	private Logger log = LoggerFactory.getLogger(ForteDBService.class);

	@Autowired
	private MobilemasterRepository mobilemasterRepository;

	@Autowired
	private RechargedetailRepository rechargedetailRepository;

	

	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	@Autowired
	private SystemPropertyRepository	systemPropertyRepository;
	
	@Autowired
	private MonthlyRechargeSummaryRepository	monthlyRechargeSummaryRepository;
	
	@Autowired
	private InsurecovermasterRepository	insurecovermasterRepository;
	
	@Autowired
	private SmsFollowupDetailRepository	smsFollowupDetailRepository;
	
	@Autowired
	private PackagemasterRepository	packagemasterRepository;
	
	@Autowired
	private ClaimsDetailRepository	claimsDetailRepository;
	
	
	private Map<String,String> propertyMap	=	null;
	
	private Map<Integer,Insurecovermaster> insurecovermasterMap	=	null;
	
	private Map<String,Packagemaster> packagemasterMap	=	null;
	
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Mobilemaster onlySaveMobileInfo(Mobilemaster mobilemaster) throws Exception, Error {
		try {
			log.info("started executing onlySaveMobileInfo", mobilemaster.getMobileno());

			boolean exist = mobilemasterRepository.existsById(mobilemaster.getMobileno());
			if (!exist) {
				mobilemaster = mobilemasterRepository.save(mobilemaster);
				return mobilemaster;
			} else {
				return null;
			}

		} catch (Exception xe) {
			log.error("Exception while executing onlySaveMobileInfo", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing onlySaveMobileInfo", error);
			throw error;
		}
	}

	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Rechargedetail onlySaveRechargeDetails(Rechargedetail rechargedetail) throws Exception, Error {
		try {
			if (log.isDebugEnabled())
				log.debug("started executing onlySaveRechargeDetails", rechargedetail.getMobilemaster().getMobileno());

			List<Long> dublicateRechargesId = rechargedetailRepository.chectDuplicateRecharges(
					rechargedetail.getRechargedate(), rechargedetail.getPackageid(),
					rechargedetail.getMobilemaster().getMobileno());

			if (dublicateRechargesId != null && dublicateRechargesId.size() > 0) {
				// TODO .. This recharge details are already exist
				return null;
			} else {
				Mobilemaster mobilemaster = new Mobilemaster();
				mobilemaster.setMobileno(rechargedetail.getMobilemaster().getMobileno());
				rechargedetail.setMobilemaster(mobilemaster);
				rechargedetail = rechargedetailRepository.save(rechargedetail);
			}
			return rechargedetail;
		} catch (Exception xe) {
			log.error("Exception while executing onlySaveRechargeDetails", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing onlySaveRechargeDetails", error);
			throw error;
		}
	}

	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public CustomerDetail saveOrUpdateCustomerDetails(CustomerDetail customerDetail)throws Exception, Error {
		try {
			return customerDetailRepository.save(customerDetail);
		} catch (Exception xe) {
			log.error("Exception while executing saveUpdateDetails", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing saveUpdateDetails", error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public CustomerDetail getCustomerDetail(String mobileNo)throws Exception,Error{
		try {
			return customerDetailRepository.getCustomerDetails(mobileNo);
		}catch(Exception xe) {
			throw xe;
		}catch(Error error) {
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public String getPropertyValue(String propertyName)throws Exception,Error{
		try {
				if(propertyMap==null)
					propertyMap	=	getAllProperty();
				return propertyMap.get(propertyName);
		}catch(Exception xe) {
			log.error("Exception while executing getPropertyValue",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getPropertyValue",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Map<String,String> getAllProperty()throws Exception,Error{
		try {
				log.info("started fetching system properties ");
				 List<SystemProperty>	systemPropertyList	=	 systemPropertyRepository.findAll();
				log.info("completed fetching system properties "+systemPropertyList);
				
				if(propertyMap==null)
					propertyMap	=	new HashMap<String, String>();
				
				for (SystemProperty systemProperty : systemPropertyList) {
					propertyMap.put(systemProperty.getPropertyName(), systemProperty.getPropertyValue());
				}
				log.info("system properties map "+propertyMap);
				return propertyMap;
		}catch(Exception xe) {
			log.error("Exception while executing getAllProperty",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getAllProperty",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void insertMonthlySummary(String monthlyReferenceNumber,String lastMonthlyReferenceNumber)throws Exception,Error{
		try {
			  log.info("started insertMonthlySummary based on  monthlyReferenceNumber::"+monthlyReferenceNumber);
				 monthlyRechargeSummaryRepository.insertMonthlySummary(monthlyReferenceNumber,ForteConstants.MAX_PREMIUN_AMOUNT.getValue(),lastMonthlyReferenceNumber);
		}catch(Exception xe) {
			log.error("Exception while executing insertMonthlySummary",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing insertMonthlySummary",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateMonthlySummary( String monthlyReferenceNumber)throws Exception,Error{
		try {
			  log.info("started updateMonthlySummary based on  monthlyReferenceNumber::"+monthlyReferenceNumber);
			  	monthlyRechargeSummaryRepository.updateMonthlySummary(monthlyReferenceNumber);
		}catch(Exception xe) {
			log.error("Exception while executing updateMonthlySummary",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing updateMonthlySummary",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateMonthlyNotRechargedSummary( String monthlyReferenceNumber,String lastMonthlyReferenceNumber)throws Exception,Error{
		try {
			  log.info("started updateMonthlyNotRechargedSummary based on  monthlyReferenceNumber::"+monthlyReferenceNumber);
			  	monthlyRechargeSummaryRepository.updateMonthlyNotRechargedSummary(monthlyReferenceNumber, ForteConstants.MAX_PREMIUN_AMOUNT.getValue(),lastMonthlyReferenceNumber);
		}catch(Exception xe) {
			log.error("Exception while executing updateMonthlyNotRechargedSummary",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing updateMonthlyNotRechargedSummary",error);
			throw error;
		}
	}
	
	
	
//	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
//	public List<MonthlyRechargeSummary> getMonthlyRechargeSummaryDetails(String monthlyReferenceNumber,int maxRecords)throws Exception,Error{
//		try {
//			return monthlyRechargeSummaryRepository.getMonthlyRechargeSummaryDetails(monthlyReferenceNumber, maxRecords);
//		}catch(Exception xe) {
//			log.error("Exception while executing getMonthlyRechargeSummaryDetails",xe);
//			throw xe;
//		}catch(Error error) {
//			log.error("Error while executing getMonthlyRechargeSummaryDetails",error);
//			throw error;
//		}
//	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MonthlyRechargeSummary> getMonthlyRechargeSummaryDetailsPagenation(String monthlyReferenceNumber,int maxRecords)throws Exception,Error{
		try {
			 log.info("started executing getMonthlyRechargeSummaryDetailsPagenation, based monthlyReferenceNumber ::"+monthlyReferenceNumber+" ,maxRecords ::"+maxRecords);
				Pageable pageable = PageRequest.of(0, maxRecords);
				return monthlyRechargeSummaryRepository.findMonthlyReferenceNumber(monthlyReferenceNumber, pageable);
		}catch(Exception xe) {
			log.error("Exception while executing getMonthlyRechargeSummaryDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getMonthlyRechargeSummaryDetails",error);
			throw error;
		}
	}
	
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public MonthlyRechargeSummary saveMonthlyRechargeSummaryDetails(MonthlyRechargeSummary monthlyRechargeSummary)throws Exception,Error{
		try {
			 log.info("started executing saveMonthlyRechargeSummaryDetails, based mobile number ::"+monthlyRechargeSummary.getMobileno());
				return monthlyRechargeSummaryRepository.save(monthlyRechargeSummary);
		}catch(Exception xe) {
			log.error("Exception while executing getMonthlyRechargeSummaryDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getMonthlyRechargeSummaryDetails",error);
			throw error;
		}
	}
	
	
	
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Insurecovermaster getInsurenceDetailsByPremium(int premium)throws Exception,Error{
		try {
				if(insurecovermasterMap==null)
					insurecovermasterMap	=	getAllInsurenceDetails();
					int maxPremiunValue	=	new Integer(getPropertyValue(ForteConstants.MAX_PREMIUN_AMOUNT.getValue()));
				if(premium<=maxPremiunValue)
					return insurecovermasterMap.get(premium);
				else
					return insurecovermasterMap.get(maxPremiunValue);
		}catch(Exception xe) {
			log.error("Exception while executing getPropertyValue",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getPropertyValue",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Map<Integer,Insurecovermaster> getAllInsurenceDetails()throws Exception,Error{
		try {
				log.info("started fetching getAllInsurenceDetails");
				 List<Insurecovermaster>	insurenceList	=	insurecovermasterRepository.findAll();
				log.info("completed fetching getAllInsurenceDetails "+insurenceList);
				
				if(insurecovermasterMap==null)
						insurecovermasterMap	=	new HashMap<Integer, Insurecovermaster>();
				
				for (Insurecovermaster insurecovermaster : insurenceList) {
					insurecovermasterMap.put(insurecovermaster.getPremium(), insurecovermaster);
				}
				
				log.info("insurecovermasterMap "+insurecovermasterMap);
				
				return insurecovermasterMap;
				
		}catch(Exception xe) {
			log.error("Exception while executing getAllInsurenceDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getAllInsurenceDetails",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public List<Mobilemaster> getPendingCustomerDetails(int pageNo,int maxRecord) throws Exception,Error{
		try {
				Pageable pageable = PageRequest.of(pageNo, maxRecord);
				return mobilemasterRepository.getPendingCustomerDetails(pageable);
		}catch(Exception xe) {
			log.error("Exception while executing getPendingCustomerDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getPendingCustomerDetails",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void updatesecondRemDate(String mobileno,Date secondSMSDate) throws Exception,Error{
		try {
			
			SmsFollowupDetail	smsFollowupDetail	=	smsFollowupDetailRepository.findByMobileno(mobileno);
			
			if(smsFollowupDetail!=null) {
				smsFollowupDetail.setSecondRemDate(secondSMSDate);
				saveSMSFollowupMessage(smsFollowupDetail);
			}else {
					smsFollowupDetail	=	 new SmsFollowupDetail();
					 Mobilemaster	mobilemaster	=	 new Mobilemaster();
					 mobilemaster.setMobileno(mobileno);
					 smsFollowupDetail.setSecondRemDate(secondSMSDate);
					 smsFollowupDetail.setMobilemaster(mobilemaster);
					 saveSMSFollowupMessage(smsFollowupDetail);
			}
			
		}catch(Exception xe) {
			log.error("Exception while executing updatesecondRemDate",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing updatesecondRemDate",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public SmsFollowupDetail saveSMSFollowupMessage(SmsFollowupDetail smsFollowupDetail) throws Exception,Error{
		try {
				return smsFollowupDetailRepository.save(smsFollowupDetail);
		}catch(Exception xe) {
			log.error("Exception while executing saveSMSFollowupMessage",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing saveSMSFollowupMessage",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public SmsFollowupDetail getSmsFollowupDetail(String mobileno) throws Exception,Error{
		try {
				if(log.isDebugEnabled())
					log.debug("started getting SmsFollowupDetail based on  mobileno"+mobileno);
				
				return	smsFollowupDetailRepository.findByMobileno(mobileno);

		}catch(Exception xe) {
			log.error("Exception while executing getSmsFollowupDetail",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getSmsFollowupDetail",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateCaseCreateFlag(String mobileno)throws Exception,Error{
		try {
				 mobilemasterRepository.updateCaseCreateFlag(mobileno);
		}catch(Exception xe) {
			log.error("Exception while executing updateCaseCreateFlag",xe);
			throw xe;
		}catch(Error error){
			log.error("Error while executing updateCaseCreateFlag",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateInfoPresentFlag(String mobileno)throws Exception,Error{
		try {
				 mobilemasterRepository.updateInfoPresentFlag(mobileno);
		}catch(Exception xe) {
			log.error("Exception while executing updateInfoPresentFlag",xe);
			throw xe;
		}catch(Error error){
			log.error("Error while executing updateInfoPresentFlag",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Packagemaster getPackageDetails(String packageCode)throws Exception,Error{
		try {
				if(packagemasterMap==null)
					packagemasterMap	=	getAllPackageDetails();
				return packagemasterMap.get(packageCode);
		}catch(Exception xe) {
			log.error("Exception while executing getPackageDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getPackageDetails",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Map<String, Packagemaster> getAllPackageDetails()throws Exception,Error{
		try {
				if(packagemasterMap==null)
					packagemasterMap	=	null;
				packagemasterMap	=	new HashMap<String, Packagemaster>();
				List<Packagemaster>	packageMasterLis	=	packagemasterRepository.findAll();
				for (Packagemaster packagemaster : packageMasterLis) {
					packagemasterMap.put(packagemaster.getPackageid(), packagemaster);
				}
				return packagemasterMap;
		}catch(Exception xe) {
			log.error("Exception while executing getAllPackageDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getAllPackageDetails",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public Integer getMonthTotalPremiun(String monthlyReferenceNumber,String mobileno)throws Exception,Error{
		try {
				return rechargedetailRepository.getMonthTotalPremiun(monthlyReferenceNumber, mobileno);
				
		}catch(Exception xe) {
			log.error("Exception while executing getMonthTotalPremiun",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getMonthTotalPremiun",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public List<MonthlyRechargeSummary> getMonthlySummaryDetails(String mobileno)throws Exception,Error{
		try {
				Date today = new Date(); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.MONTH, -6);
				
				Date	lastModifiedDate	=	cal.getTime();
				return monthlyRechargeSummaryRepository.getMonthlySummaryDetails(lastModifiedDate, mobileno);
				
		}catch(Exception xe) {
			log.error("Exception while executing getMonthlySummaryDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getMonthlySummaryDetails",error);
			throw error;
		}
	}
	
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public List<Rechargedetail> getRechargeHistory(String mobileno)throws Exception,Error{
		try {
				Date today = new Date(); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.MONTH, -6);
				cal.set(Calendar.DATE, 01);
				Timestamp	lastModifiedDate =	new Timestamp(cal.getTime().getTime());
				return rechargedetailRepository.getRechargeHistory(lastModifiedDate, mobileno);
				
		}catch(Exception xe) {
			log.error("Exception while executing getRechargeHistory",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getRechargeHistory",error);
			throw error;
		}
	}
	
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public List<ClaimsDetail> getClaimsDetails(String mobileno)throws Exception,Error{
		try {
				return claimsDetailRepository.findByMobileno(mobileno);
		}catch(Exception xe) {
			log.error("Exception while executing getClaimsDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getClaimsDetails",error);
			throw error;
		}
	}
	
	@Transactional(value = "forteTransactionManager", propagation = Propagation.SUPPORTS, readOnly = false)
	public ClaimsDetail saveClaimsDetails(ClaimsDetail claimsDetail)throws Exception,Error{
		try {
				return claimsDetailRepository.save(claimsDetail);
				
		}catch(Exception xe) {
			log.error("Exception while executing getClaimsDetails",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getClaimsDetails",error);
			throw error;
		}
	}
	
	

}
