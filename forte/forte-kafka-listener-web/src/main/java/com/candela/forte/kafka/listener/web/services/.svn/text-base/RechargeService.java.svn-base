package com.candela.forte.kafka.listener.web.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.entity.CustomerDetail;
import com.candela.forte.integration.db.entity.Insurecovermaster;
import com.candela.forte.integration.db.entity.Mobilemaster;
import com.candela.forte.integration.db.entity.Packagemaster;
import com.candela.forte.integration.db.entity.Rechargedetail;
import com.candela.forte.integration.db.model.RechargeInfo;
import com.candela.forte.integration.db.model.RestResponse;
import com.candela.forte.integration.db.service.ForteDBService;
import com.candela.forte.integration.db.util.ForteUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RechargeService {

	private Logger	log	=	LoggerFactory.getLogger(RechargeService.class);
	
	@Autowired
	private ForteDBService forteDBService;
	
	@Autowired
	private ObjectMapper	mapper;
	
	@Autowired
	private SMSService	smsService;
	
	@Autowired
	private ForteUtility	forteUtility;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSS"); 
	
	
	
	public RestResponse processRechargeRequest(String rechargeMessage)throws Exception,Error{
		RestResponse	restResponse	=	new RestResponse();
		try {
				if(log.isDebugEnabled())
					log.debug("started executing processRechargeRequest for the rechargemessage ::"+rechargeMessage);
				
				RechargeInfo	rechargeInfo	=	mapper.readValue(rechargeMessage, RechargeInfo.class);
				
				log.info("rechargeInfo::" +rechargeInfo);
				
				Mobilemaster	mobilemaster 	=	prepareMobileMaster(rechargeInfo);
				
				log.info("mobilemaster" +mobilemaster.getMobileno());
				
				Mobilemaster	resultMobilemaster	=	forteDBService.onlySaveMobileInfo(mobilemaster);
				if(resultMobilemaster==null && mobilemaster.getRechargedetails()!=null && mobilemaster.getRechargedetails().iterator().hasNext()) {
					Rechargedetail	rechargedetail	=	forteDBService.onlySaveRechargeDetails( mobilemaster.getRechargedetails().iterator().next());
					if(rechargedetail!=null) {
						log.info("recharge details are saved sucessfully"+rechargedetail.getId());
						
						CustomerDetail	customerDetail	=	forteDBService.getCustomerDetail(rechargedetail.getMobilemaster().getMobileno());
						if(customerDetail!=null) {
							
							String message 	=	forteDBService.getPropertyValue(ForteConstants.RECHARGE_SMS_CUSTOMER_DETAILS_EN.getValue());
							
							if(message==null || message.isEmpty())
								log.error("No message was configured for the property ::"+ ForteConstants.RECHARGE_SMS_CUSTOMER_DETAILS_EN.getValue()+" in system property table ");
							else
								sendRechargeSMS(message, rechargeInfo,1);
							
						 message 	=	forteDBService.getPropertyValue(ForteConstants.RECHARGE_SMS_CUSTOMER_DETAILS_LAO.getValue());
							if(message==null || message.isEmpty())
								log.error("No message was configured for the property ::"+ ForteConstants.RECHARGE_SMS_CUSTOMER_DETAILS_LAO.getValue()+" in system property table ");
							else
								sendRechargeSMS(message, rechargeInfo,1);
							
							
						}else {
							String message 	=	forteDBService.getPropertyValue(ForteConstants.RECHARGE_SMS_NO_CUSTOMER_DETAILS_EN.getValue());
							
							if(message==null || message.isEmpty())
								log.error("No message was configured for the property ::"+ ForteConstants.RECHARGE_SMS_NO_CUSTOMER_DETAILS_EN.getValue()+" in system property table ");
							else
								sendRechargeSMS(message, rechargeInfo,1);
							
						 message 	=	forteDBService.getPropertyValue(ForteConstants.RECHARGE_SMS_NO_CUSTOMER_DETAILS_LAO.getValue());
							if(message==null || message.isEmpty())
								log.error("No message was configured for the property ::"+ ForteConstants.RECHARGE_SMS_NO_CUSTOMER_DETAILS_LAO.getValue()+" in system property table ");
							else
								sendRechargeSMS(message, rechargeInfo,1);
						}
						
					}else {
						log.error("Same recharge details are present with us"+rechargeInfo);
					}
					
					
				}else {
					//TODO first time rechare happed .. so need to send sms
					String message 	=	forteDBService.getPropertyValue(ForteConstants.FIRST_RECHARGE_SMS_EN.getValue());
						if(message==null || message.isEmpty())
							log.error("No message was configured for the property ::"+ ForteConstants.FIRST_RECHARGE_SMS_EN.getValue()+" in system property table ");
						else
							sendRechargeSMS(message, rechargeInfo,0);
						
					 message 	=	forteDBService.getPropertyValue(ForteConstants.FIRST_RECHARGE_SMS_LAO.getValue());
						if(message==null || message.isEmpty())
							log.error("No message was configured for the property ::"+ ForteConstants.FIRST_RECHARGE_SMS_LAO.getValue()+" in system property table ");
						else
							sendRechargeSMS(message, rechargeInfo,0);
				
				}
			
		}catch(Exception xe) {
			log.error("Exception while executing processRechargeRequest",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing processRechargeRequest",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}
	
	
	
	private void sendRechargeSMS(String message, RechargeInfo rechargeInfo,int smsType)throws Exception,Error{
		try {
				if(message!=null && !message.isEmpty()) {
					if(rechargeInfo!=null && rechargeInfo.getPackageCode()!=null) {
						Insurecovermaster	insurecovermaster	=	null;
						Packagemaster	packagemaster	=	forteDBService.getPackageDetails(rechargeInfo.getPackageCode());
						if(packagemaster!=null && packagemaster.getPremium()!=null) {
							if(smsType==0)
								insurecovermaster	=	forteDBService.getInsurenceDetailsByPremium(packagemaster.getPremium());
							else {
								Integer	totalPremiun	=	forteDBService.getMonthTotalPremiun(forteUtility.genarateMonthlyReferenceNumber(), rechargeInfo.getMobile());
								
								if(totalPremiun!=null)
									insurecovermaster	=	forteDBService.getInsurenceDetailsByPremium(totalPremiun);
								else
									insurecovermaster	=	forteDBService.getInsurenceDetailsByPremium(packagemaster.getPremium());
							}
								
							if(insurecovermaster!=null && insurecovermaster.getAccidentcover()!=null) {
								message	=	message.replace("{PA cover}", ""+insurecovermaster.getAccidentcover());
								message	=	message.replace("{MONYY}", forteUtility.getNextMonthDateFormate());
								message	=	message.replace("{Hospitalisation cover}", ""+insurecovermaster.getHospitalcover());
								 
							}else {
								log.error("There is no insurence details are configured in insurencemaster table for the premiun "+packagemaster.getPremium());
							}
						}else {
							log.error("There is no package details are configured in package master table for the package id "+rechargeInfo.getPackageCode());
						}
					}else {
						log.error("package code is not present in rechage info message",rechargeInfo);
					}
						
					smsService.sendSMS(message, rechargeInfo.getMobile());
				}else {
					log.error("No message was configured for the property ::"+ ForteConstants.FIRST_RECHARGE_SMS_EN.getValue()+" in system property table ");
				}
			
		}catch(Exception xe) {
			log.error("Exception while executing sendRechargeSMS",xe);
			//throw xe;
		}catch(Error error) {
			log.error("Error while executing sendRechargeSMS",error);
			//throw error;
		}
	}
	
	
	
	private Mobilemaster prepareMobileMaster(RechargeInfo rechargeInfo)throws Exception,Error{
		try {
				if(log.isDebugEnabled())
					log.debug("started prepareMobileMaster for the message "+rechargeInfo);
				Mobilemaster	mobilemaster	=	 new Mobilemaster();
				mobilemaster.setMobileno(rechargeInfo.getMobile());
				mobilemaster.setCasecreated("N");
				mobilemaster.setInfopresent("N");
				
				Date d		=	 dateFormat.parse(rechargeInfo.getRechargeTimeStamp());
				Timestamp	ts	=	new Timestamp(d.getTime());
				
				mobilemaster.setRegisterDate(ts);
				
				Rechargedetail	rechargedetails	=	new Rechargedetail();
				rechargedetails.setMobilemaster(mobilemaster);
				rechargedetails.setMonthlyReferenceNumber(forteUtility.genarateMonthlyReferenceNumber());
				rechargedetails.setPackageid(rechargeInfo.getPackageCode());
				Map<String,Packagemaster>	packagemasterMap	=	forteDBService.getAllPackageDetails();
				
				if(packagemasterMap!=null && packagemasterMap.get(rechargeInfo.getPackageCode())!=null) {
					rechargedetails.setPremium(packagemasterMap.get(rechargeInfo.getPackageCode()).getPremium());
					rechargedetails.setRechargeAmount(packagemasterMap.get(rechargeInfo.getPackageCode()).getAmount());
				}else{
					rechargedetails.setPremium(0);
					rechargedetails.setRechargeAmount(0);
				}
				
				
				
				rechargedetails.setRechargedate(ts);
				
				Set<Rechargedetail>	rechargedetailSet	=	 new HashSet<Rechargedetail>();
				rechargedetailSet.add(rechargedetails);
				
				mobilemaster.setRechargedetails(rechargedetailSet);
				
				return mobilemaster;
		}catch(Exception xe) {
			log.error("Exception while executing prepareMobileMaster",xe);
			throw xe;
		}catch(Error xe) {
			log.error("error while executing prepareMobileMaster",xe);
			throw xe;
		}
	}
	
	
	
}
