package com.candela.forte.scheduler.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.entity.CustomerDetail;
import com.candela.forte.integration.db.entity.Insurecovermaster;
import com.candela.forte.integration.db.entity.MonthlyRechargeSummary;
import com.candela.forte.integration.db.model.RestResponse;
import com.candela.forte.integration.db.service.ForteDBService;
import com.candela.forte.integration.db.util.ForteUtility;
import com.laotel.ltcservice.SendSMSResult;

@Service
public class RechargeAndSMSService {

	private Logger	log	=	LoggerFactory.getLogger(RechargeAndSMSService.class);
	
	@Autowired
	private SMSService	smsService;
	
	@Autowired
	private ForteDBService	forteDBService;
	
	@Autowired
	private ForteUtility	forteUtility;
	
	public RestResponse	 updateMontlyRechargeAmount()throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
			log.info("started calculating and updating monthly recharge amount based on mobile numbers");
			
			String	monthlyReferenceNumber	=	 forteUtility.getPreviousMonthDateFormate(); 
			
			log.info("started inserting new mobile numbers /first time rechrge mobile  numbers data based on "+monthlyReferenceNumber);
				forteDBService.insertMonthlySummary(monthlyReferenceNumber,forteUtility.getLastTolastMonthDateFormate());
			log.info("completed inserting new mobile numbers /first time rechrge mobile  numbers data based on "+monthlyReferenceNumber);
			
//			log.info("started updating recharge data based on "+monthlyReferenceNumber);
//				forteDBService.updateMonthlySummary(monthlyReferenceNumber);
//			log.info("started updating recharge data based on "+monthlyReferenceNumber);
//			
			log.info("started updateMonthlyNotRechargedSummary data based on "+forteUtility.getLastTolastMonthDateFormate());
				forteDBService.updateMonthlyNotRechargedSummary(monthlyReferenceNumber,forteUtility.getLastTolastMonthDateFormate());
			log.info("started updateMonthlyNotRechargedSummary data based on "+forteUtility.getLastTolastMonthDateFormate());
			
		
			log.info("completed calculating and updating monthly recharge amount based on mobile numbers");
			
		}catch(Exception xe) {
			log.error("Exception while executing updateMontlyRechargeAmount",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error xe) {
			log.error("Error while executing updateMontlyRechargeAmount",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}
		return restResponse;
	}
	
	public RestResponse sendMonthlyPremiumSMS()throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
				String	monthlyReferenceNumber	=	 forteUtility.genarateMonthlyReferenceNumber(); 
				List<MonthlyRechargeSummary>	mobilenumberList	=	forteDBService.getMonthlyRechargeSummaryDetailsPagenation(monthlyReferenceNumber, new Integer(forteDBService.getPropertyValue(ForteConstants.SMS_MAX_RECORDS.getValue())));
				if(mobilenumberList!=null) {
					
					for (MonthlyRechargeSummary monthlyRechargeSummary : mobilenumberList) {
						try {
							log.info("started sending message"+monthlyRechargeSummary);
							if(monthlyRechargeSummary.getMobileno()!=null && !monthlyRechargeSummary.getMobileno().isEmpty()) {
								if(monthlyRechargeSummary.getMonthTotalRecharge()!=0 && monthlyRechargeSummary.getMonthTotalRecharge() !=null 
										&& forteDBService.getInsurenceDetailsByPremium(monthlyRechargeSummary.getMonthTotalRecharge())!=null) {
									CustomerDetail	customerDetail			=	 forteDBService.getCustomerDetail(monthlyRechargeSummary.getMobileno());
									Insurecovermaster	insurecovermaster	=	forteDBService.getInsurenceDetailsByPremium(monthlyRechargeSummary.getMonthTotalRecharge());
									if(customerDetail!=null) {
											// send LAO message
											String message	=	forteDBService.getPropertyValue(ForteConstants.FIRST_DAY_OF_EVERY_MONTH_LAO.getValue());
											if(message!=null) {
												message		=	message.replace("{MONYY}",forteUtility.getNextMonthDateFormate());
												if(insurecovermaster.getHospitalcover()!=null)
													message		=	message.replace("{Hospitalization cover}",insurecovermaster.getHospitalcover().toString());
												else 
													message		=	message.replace("{Hospitalization cover}","0");
												if(insurecovermaster.getAccidentcover()!=null)
													message		=	message.replace("{PA Cover}",insurecovermaster.getAccidentcover().toString());
												else
													message		=	message.replace("{PA Cover}","0");
												
												SendSMSResult sendSMSResult	=	smsService.sendSMS(message, monthlyRechargeSummary.getMobileno());
												if(sendSMSResult!=null && sendSMSResult.getResultCode()!=null && sendSMSResult.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue())) {
													monthlyRechargeSummary.setSentSms("Y");
													forteDBService.saveMonthlyRechargeSummaryDetails(monthlyRechargeSummary);
												}else {
													//No need to any thing.. have to retry after some time
												}
											}else {
												log.error("No sms message configured in system property table for the property "+ForteConstants.FIRST_DAY_OF_EVERY_MONTH_LAO.getValue());
											}
												
											//TODO send english message
											 message	=	forteDBService.getPropertyValue(ForteConstants.FIRST_DAY_OF_EVERY_MONTH_EN.getValue());
											 if(message!=null) {
												 message		=	message.replace("{MONYY}",forteUtility.getNextMonthDateFormate());
													if(insurecovermaster.getHospitalcover()!=null)
														message		=	message.replace("{Hospitalisation cover}",insurecovermaster.getHospitalcover().toString());
													else
														message		=	message.replace("{Hospitalisation cover}","0");
													if(insurecovermaster.getAccidentcover()!=null)
														message		=	message.replace("{PA cover}",insurecovermaster.getAccidentcover().toString());
													else
														message		=	message.replace("{PA cover}","0");
													
													SendSMSResult sendSMSResult	=	smsService.sendSMS(message, monthlyRechargeSummary.getMobileno());
													if(sendSMSResult!=null && sendSMSResult.getResultCode()!=null && sendSMSResult.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue())) {
														monthlyRechargeSummary.setSentSms("Y");
														forteDBService.saveMonthlyRechargeSummaryDetails(monthlyRechargeSummary);
													}else {
														//No need to any thing.. have to retry after some time
													}
											 }else {
												 log.error("No sms message configured in system property table for the property "+ForteConstants.FIRST_DAY_OF_EVERY_MONTH_EN.getValue());
											 }
										
									}else {
										log.error("Customer details are not exist for the mobile number :"+monthlyRechargeSummary.getMobileno());
									}
									
								}else {
									log.error("There is no insurence details based on premiun amount ::"+monthlyRechargeSummary.getMonthTotalRecharge());
								}
								
							}else {
								log.error("There is no mobile number in monthly recharge summary");
							}
						}catch(Exception xee) {
							log.error("Exception while send sms",xee);
						}catch(Error error) {
							log.error("Error while send sms",error);
						}
						
					}
				}

		}catch(Exception xe) {
			log.error("Exception while executing sendMonthlyPremiumSMS");
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error xe) {
			log.error("Error while executing sendMonthlyPremiumSMS");
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}
		return restResponse;
	}
	
}
