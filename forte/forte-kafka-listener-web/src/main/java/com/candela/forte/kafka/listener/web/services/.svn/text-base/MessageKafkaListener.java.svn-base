package com.candela.forte.kafka.listener.web.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MessageKafkaListener {

	private Logger log = LoggerFactory.getLogger(MessageKafkaListener.class);

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper mapper; 
	
	@Autowired
	private RechargeService rechargeService;
	
	@Autowired
	private CustomerDetailService customerDetailService;

	@KafkaListener(topics = "#{'${Recharge_Info_Q}'}", groupId = "#{'${Group_Id}'}")
	public void processRechargeInfo(String message) throws JsonParseException, JsonMappingException, IOException {
		try {
					log.info("cosumed message from topic "+message);
					rechargeService.processRechargeRequest(message);
		}catch(Exception xe) {
			log.error("Exception while executing processRechargeInfo",xe);
			throw new IOException(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing processRechargeInfo",error);
			throw new IOException(error.getMessage());
		}
		
	}

	@KafkaListener(topics = "#{'${Update_Info_Q}'}", groupId = "#{'${Group_Id}'}")
	public void updateCustomerInfo(String customerDetails) throws JsonParseException, JsonMappingException, IOException,Exception {
		try {
				log.info("cosumed message from topic "+customerDetails);
			
				customerDetailService.processCustomerDetailRequest(customerDetails);
				
		}catch(Exception xe) {
			log.error("Exception while executing updateCustomerInfo",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing updateCustomerInfo",error);
			throw new Exception(error.fillInStackTrace());
		}
	}
}
