package com.candela.forte.kafka.listener.web.services;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.service.ForteDBService;
import com.laotel.ltcservice.SendSMSResult;
import com.laotel.ltcservice.Services;
import com.laotel.ltcservice.ServicesSoap;
import com.laotel.ltcservice.ServicesSoapImpl;
import javax.xml.namespace.QName;

@Service
public class SMSService {

	private Logger	log	=	 LoggerFactory.getLogger(SMSService.class);

	private ServicesSoapImpl	servicesSoapImpl	=	 new ServicesSoapImpl();

	@Autowired
	private ForteDBService	forteDBService;
	
	
	URL wsdlURL = Services.WSDL_LOCATION;
	Services services = new Services(wsdlURL,SERVICE_NAME);
	ServicesSoap port = services.getServicesSoap();
	
	
	private static final QName SERVICE_NAME = new QName("http://ltcservice.laotel.com", "Services");
	
	public Object sendSMS(String message,String mobileNumber)throws Exception,Error{
		try {
			log.info("started sending sms for the mobile number :"+mobileNumber+" , message : "+message);
			
				SendSMSResult	sendSMSResult	= port.sendSMSViaKey(mobileNumber, message, forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_USER_ID.getValue()),
						forteDBService.getPropertyValue(ForteConstants.SMS_HEADER.getValue()), forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_KEY.getValue())); 	
						
						
						//servicesSoapImpl.sendSMSViaKey(mobileNumber, message, forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_USER_ID.getValue()),
						//forteDBService.getPropertyValue(ForteConstants.SMS_HEADER.getValue()), forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_KEY.getValue()));
				
			log.info("completed sending sms,  result code ::"+sendSMSResult.getResultCode()+" ResultDesc :: "+sendSMSResult.getResultDesc()+" Msisdn ::"+sendSMSResult.getMsisdn()+" Receipt :: "+sendSMSResult.getReceipt()+" UserId:: "+sendSMSResult.getUserId());
			return sendSMSResult;
		}catch(Exception xe) {
			log.error("Exception while executing sendsms",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing sendsms",error);
			throw error;
		}
	}
}
