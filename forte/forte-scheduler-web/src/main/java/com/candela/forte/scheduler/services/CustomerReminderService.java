package com.candela.forte.scheduler.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.entity.Mobilemaster;
import com.candela.forte.integration.db.entity.SmsFollowupDetail;
import com.candela.forte.integration.db.model.CallCenterProcessInfo;
import com.candela.forte.integration.db.model.ProcessData;
import com.candela.forte.integration.db.model.ProcessVariables;
import com.candela.forte.integration.db.service.ForteDBService;
import com.candela.forte.integration.db.util.ForteUtility;
import com.laotel.ltcservice.SendSMSResult;

@Service
public class CustomerReminderService {

	private Logger	log	=	LoggerFactory.getLogger(CustomerReminderService.class);
	
	@Autowired
	private ForteDBService	forteDBService;
	
	@Autowired
	private ForteUtility	forteUtility;
	
	@Autowired
	private SMSService	smsService;
	
	@Autowired
	private RestTemplate	restTemplate;
	
	
	public void sendReminderSMSToCustomer()throws Exception,Error{
		try {
				int maxOcc	=	0;
				
				while(maxOcc<100) {
					
					List<Mobilemaster> mobileMasterList	=	forteDBService.getPendingCustomerDetails(maxOcc,new Integer(forteDBService.getPropertyValue(ForteConstants.SMS_MAX_RECORDS.getValue())));
					
					if(mobileMasterList==null || mobileMasterList.size()==0)
						break;
					
					Date currentDate	=	new Date();
					for (Mobilemaster mobilemaster : mobileMasterList) {
						try {
							Date regDate	=	mobilemaster.getRegisterDate();
							if(regDate!=null) {
								
								long noOfDays	=	forteUtility.getDaysBetweenDates(regDate, currentDate);
								long firstReminder	=	new Long(forteDBService.getPropertyValue(ForteConstants.FIRST_SMS_REMINDER_DAYS.getValue()));
								long secondReminder	=	new Long(forteDBService.getPropertyValue(ForteConstants.SECOND_SMS_REMINDER_DAYS.getValue()));
								long thirdReminder	=	new Long(forteDBService.getPropertyValue(ForteConstants.THIRD_REMINDER_DAYS.getValue()));
								
								if(noOfDays==firstReminder) {
									//TODO send first reminder
									String messageLao	=	forteDBService.getPropertyValue(ForteConstants.FIRST_REMINDER_SMS_LAO.getValue());
									String messageEn	=	forteDBService.getPropertyValue(ForteConstants.FIRST_REMINDER_SMS_EN.getValue());
									
									SmsFollowupDetail	smsFollowupDetail	=	forteDBService.getSmsFollowupDetail( mobilemaster.getMobileno());
									if(smsFollowupDetail==null || (smsFollowupDetail!=null && smsFollowupDetail.getFirstRemDate()==null)) {
										SendSMSResult srl	=	smsService.sendSMS(messageLao, mobilemaster.getMobileno());
										SendSMSResult sr	=	smsService.sendSMS(messageEn, mobilemaster.getMobileno());
										
										if((srl!=null && srl.getResultCode()!=null && srl.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue())) ||
												(sr!=null && sr.getResultCode()!=null && sr.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue()))){
											
											Mobilemaster	mobileMaster	=	new Mobilemaster();
											mobileMaster.setMobileno(mobilemaster.getMobileno());

											smsFollowupDetail	=	 new SmsFollowupDetail();
											smsFollowupDetail.setFirstRemDate(new Date());
											smsFollowupDetail.setMobilemaster(mobileMaster);
											
											forteDBService.saveSMSFollowupMessage(smsFollowupDetail);
											
										}
									}
									
								}else if(noOfDays==secondReminder) {
									//TODO send 2nd reminder
									SmsFollowupDetail	smsFollowupDetail	=	forteDBService.getSmsFollowupDetail( mobilemaster.getMobileno());
									if(smsFollowupDetail ==null || (smsFollowupDetail!=null && smsFollowupDetail.getSecondRemDate()==null)) {
										String messageLao	= forteDBService.getPropertyValue(ForteConstants.SECOND_REMINDER_SMS_LAO.getValue());
										String messageEn	= forteDBService.getPropertyValue(ForteConstants.SECOND_REMINDER_SMS_EN.getValue());
										SendSMSResult srl	=	smsService.sendSMS(messageLao, mobilemaster.getMobileno());
										SendSMSResult sr	=	 smsService.sendSMS(messageEn, mobilemaster.getMobileno());
										if((srl!=null && srl.getResultCode()!=null && srl.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue())) ||
												(sr!=null && sr.getResultCode()!=null && sr.getResultCode().equalsIgnoreCase(ForteConstants.SMS_SUCCESS_CODE.getValue()))){
											forteDBService.updatesecondRemDate(mobilemaster.getMobileno(), new Date());
										}
									}
									
									
								}else if(noOfDays==thirdReminder){
									//TODO to trigger case for customer service
									triggerCallCenterProcess(mobilemaster);
								}
								
							}
						}catch(Exception xee) {
							log.error("Exception while sending sms",xee);
						}catch(Error errorr) {
							log.error("Error while sending sms",errorr);
						}
						
					}
					
					if(maxOcc==100)
						break;
					
					maxOcc=maxOcc+1;
					
				}
				
		}catch(Exception xe) {
			log.error("Exception while executing sendReminderSMSToCustomer",xe);
		}catch(Error error) {
			log.error("Error while executing sendReminderSMSToCustomer",error);
		}
	}
	
	
	private Object triggerCallCenterProcess(Mobilemaster mobilemaster)throws Exception,Error{
		try {
				boolean flag	=	false;
				CallCenterProcessInfo	callCenterProcessInfo	=	 new CallCenterProcessInfo();
				ProcessVariables	processVariables	=	new ProcessVariables();
				ProcessData		processData	=	 new ProcessData();
				processData.setType("String");
				processData.setValue(mobilemaster.getMobileno());
				
				processVariables.setMobileNo(processData);
				
				callCenterProcessInfo.setVariables(processVariables);
				callCenterProcessInfo.setBusinessKey("mobileNo");
				
				ResponseEntity<String> response	=	restTemplate.postForEntity(forteDBService.getPropertyValue(ForteConstants.CUSTOMER_CARE_PROCESS_REST_URL.getValue()), callCenterProcessInfo,String.class);
				if(response!=null && response.getBody()!=null && response.getBody().contains("id"))
					flag	=	 true;
				
				if(flag) {
					forteDBService.updateCaseCreateFlag(mobilemaster.getMobileno());
				}
				return null;
		}catch(Exception xe) {
			log.error("exception while executing triggerCallCenterProcess",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing triggerCallCenterProcess",error);
			throw error;
		}
	}
}
