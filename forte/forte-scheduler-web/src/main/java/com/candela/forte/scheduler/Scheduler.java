package com.candela.forte.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.candela.forte.scheduler.services.CustomerReminderService;
import com.candela.forte.scheduler.services.RechargeAndSMSService;

@Component
public class Scheduler {
	
	private Logger	log	=	LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	private	RechargeAndSMSService	rechargeAndSMSService;
	
	@Autowired
	private CustomerReminderService	 customerReminderService;
	
   
  // @Scheduled(cron = "0 0 0 1 * ?")
	 //@Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "${update.monthly.recharge.cron}")
   public void updateRechargeDetails()throws Exception,Error {
	   try {
		   		log.info("started executing updateRechargeDetails");
		   			rechargeAndSMSService.updateMontlyRechargeAmount();
		      log.info("completed executing updateRechargeDetails");
	   }catch(Exception xe) {
		   log.error("Exception while executing updateRechargeDetails job ",xe);
		   throw xe;
	   }catch(Error error) {
		   log.error("Error while executing updateRechargeDetails job ",error);
		   throw error;
	   }
   }
   
   //@Scheduled(cron = "0 0/5 * * * ?")
	@Scheduled(cron = "${insurence.monthly.sms.cron}")
   public void sendInsurenceSMS()throws Exception,Error {
	   try {
		   	  log.info("started executing sendInsurenceSMS");
		   		rechargeAndSMSService.sendMonthlyPremiumSMS();
		   	  log.info("started executing sendInsurenceSMS");
	   }catch(Exception xe) {
		   log.error("Exception while executing sendInsurenceSMS job ",xe);
		   throw xe;
	   }catch(Error error) {
		   log.error("Error while executing sendInsurenceSMS job ",error);
		   throw error;
	   }
   }
   
	@Scheduled(cron = "${update.customer.reminder.cron}")
	   public void customerDetailReminderSMS()throws Exception,Error {
		   try {
			   	  log.info("started executing customerDetailReminderSMS");
			   	customerReminderService.sendReminderSMSToCustomer();
			   	  log.info("started executing customerDetailReminderSMS");
		   }catch(Exception xe) {
			   log.error("Exception while executing customerDetailReminderSMS job ",xe);
			   throw xe;
		   }catch(Error error) {
			   log.error("Error while executing customerDetailReminderSMS job ",error);
			   throw error;
		   }
	   }
   
}