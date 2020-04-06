package com.candela.forte.scheduler.services;

import java.net.URL;

import javax.xml.namespace.QName;

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

@Service
public class SMSService {

	private Logger	log	=	 LoggerFactory.getLogger(SMSService.class);


	@Autowired
	private ForteDBService	forteDBService;
	
	
	private URL wsdlURL 		=	null;//Services.WSDL_LOCATION;
	private Services services 	=	null;//new Services(wsdlURL,SERVICE_NAME);
	private ServicesSoap port 	=	null;//services.getServicesSoap();

	private QName SERVICE_NAME 	=	null;//new QName("http://ltcservice.laotel.com", "Services");
	
	public SendSMSResult sendSMS(String message,String mobileNumber)throws Exception,Error{
		try {
			log.info("started sending sms for the mobile number :"+mobileNumber+" , message : "+message);
				if(wsdlURL==null) {
					wsdlURL			=	new URL(forteDBService.getPropertyValue(ForteConstants.SMS_WSDL_URL.getValue())); // URL("http://ltcservice.laotel.com:5678/Services.asmx?WSDL");
					SERVICE_NAME	=	new QName(forteDBService.getPropertyValue(ForteConstants.SMS_WSDL_QNAME.getValue()), "Services"); 			 // QName("http://ltcservice.laotel.com", "Services");
					services 		=	new Services(wsdlURL,SERVICE_NAME);
					port 			=	services.getServicesSoap();
				}
			
				SendSMSResult	sendSMSResult	= port.sendSMSViaKey(mobileNumber, message, forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_USER_ID.getValue()),
						forteDBService.getPropertyValue(ForteConstants.SMS_HEADER.getValue()), forteDBService.getPropertyValue(ForteConstants.SMS_SERVER_KEY.getValue())); 	
				
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
