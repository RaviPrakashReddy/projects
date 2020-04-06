package com.candela.forte.integration.web.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.candela.forte.integration.db.entity.ClaimsDetail;
import com.candela.forte.integration.db.entity.CustomerDetail;
import com.candela.forte.integration.db.entity.Mobilemaster;
import com.candela.forte.integration.db.entity.MonthlyRechargeSummary;
import com.candela.forte.integration.db.entity.Rechargedetail;
import com.candela.forte.integration.db.model.CallCenterProcessInfo;
import com.candela.forte.integration.db.model.CustomerInfo;
import com.candela.forte.integration.db.model.CustomerUpdateinfo;
import com.candela.forte.integration.db.model.ProcessData;
import com.candela.forte.integration.db.model.ProcessVariables;
import com.candela.forte.integration.db.model.RestResponse;
import com.candela.forte.integration.db.service.ForteDBService;
import com.candela.forte.integration.web.kafka.services.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerInfoRestService {

	private Logger log = LoggerFactory.getLogger(CustomerInfoRestService.class);

	@Value("${Recharge_Info_Q}")
	private String RECHARGEINFO_TOPIC;

	@Value("${Update_Info_Q}")
	private String UPDATEINFO_TOPIC;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Autowired
	private ForteDBService	forteDBService;

	public RestResponse processRechargeInformation(CustomerInfo customerInfo) throws Exception, Error {
		try {
			if (customerInfo != null) {
				return kafkaProducerService.sendMessageToKafka(RECHARGEINFO_TOPIC,
						mapper.writeValueAsString(customerInfo));
			} else {
				throw new Exception("Can't able to process due to request object is empty or null");
			}

		} catch (Exception xe) {
			log.error("Exception while executing processRechargeInformation", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing processRechargeInformation", error);
			throw error;
		}

	}

	public RestResponse updateCustomerInformation(CustomerUpdateinfo customerUpdateinfo) throws Exception, Error {
		try {
			if (customerUpdateinfo != null) {
				return kafkaProducerService.sendMessageToKafka(UPDATEINFO_TOPIC,
						mapper.writeValueAsString(customerUpdateinfo));
			} else {
				throw new Exception("Can't able to process due to request object is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing updateCustomerInformation", xe);
			throw xe;
		} catch (Error error) {
			log.error("Exception while executing updateCustomerInformation", error);
			throw error;
		}

	}
	
	public RestResponse getCustomerDetaildByMobileNo(String mobileNo)throws Exception,Error{
		RestResponse restResponse	=	 new RestResponse();
		try {
			if(log.isDebugEnabled())
				log.debug("started getting customer details based on mobileNo"+mobileNo );
				CustomerDetail	customerDetail	=	forteDBService.getCustomerDetail(mobileNo);
				if(customerDetail!=null) {
					restResponse.setResponse(customerDetail);
				}else {
					restResponse.setStatus(1);
					restResponse.setResponse("No record found based on mobile number :"+mobileNo);
				}
		}catch(Exception xe) {
			log.error("Exception while executing getCustomerDetaildByMobileNo",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getCustomerDetaildByMobileNo",error);
			throw error;
		}
		return restResponse;
	}
	
	public RestResponse  getCallCenterProcessInfo(String mobileNo)throws Exception,Error{
		RestResponse	restResponse	= new RestResponse();
		try {
				CallCenterProcessInfo	callCenterProcessInfo	=	 new CallCenterProcessInfo();
				callCenterProcessInfo.setBusinessKey("mobileNo");
				
				ProcessVariables	processVariables	=	 new	ProcessVariables();
				
				ProcessData	processData	=	new ProcessData();
				processData.setType("String");
				processData.setValue(mobileNo);
				
				processVariables.setMobileNo(processData);
				
				callCenterProcessInfo.setVariables(processVariables);
				
				restResponse.setResponse(callCenterProcessInfo);;
			
		}catch(Exception xe) {
			log.error("Exception while executing getCustomerDetaildByMobileNo",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getCustomerDetaildByMobileNo",error);
			throw error;
		}
		return restResponse;
	}
	
	
	public RestResponse	getRechargeSummaryDetails(String mobileno)throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
				List<MonthlyRechargeSummary>	monthlyRechargeSummaries	=	forteDBService.getMonthlySummaryDetails(mobileno);
				if(monthlyRechargeSummaries!=null && monthlyRechargeSummaries.size()>0)
					restResponse.setResponse(monthlyRechargeSummaries);
				else {
					restResponse.setStatus(1);
					restResponse.setResponse("No record found for the mobile number ::"+mobileno);
				}
		}catch(Exception xe) {
			log.error("Exception while executing  getRechargeSummaryDetails",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing getRechargeSummaryDetails",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}

	
	public RestResponse	getRechargeHistory(String mobileno)throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
				List<Rechargedetail>	rechargedetails	=	forteDBService.getRechargeHistory(mobileno);
				if(rechargedetails!=null && rechargedetails.size()>0)
					restResponse.setResponse(rechargedetails);
				else {
					restResponse.setStatus(1);
					restResponse.setResponse("No record found for the mobile number ::"+mobileno);
				}
		}catch(Exception xe) {
			log.error("Exception while executing getRechargeHistory ",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing getRechargeHistory ",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}
	
	public  RestResponse getClaimsDetails(String mobileno)throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
			List<ClaimsDetail>	claimsDetails	=	forteDBService.getClaimsDetails(mobileno);
				if(claimsDetails!=null && claimsDetails.size()>0)
					restResponse.setResponse(claimsDetails);
				else {
					restResponse.setStatus(1);
					restResponse.setResponse("No record found for the mobile number ::"+mobileno);
				}
		}catch(Exception xe) {
			log.error("Exception while executing getClaimsDetails ",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing getClaimsDetails ",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}
	
	public  RestResponse saveClaimsDetails(ClaimsDetail claimsDetail)throws Exception,Error{
		RestResponse	restResponse	=	 new RestResponse();
		try {
				if(claimsDetail!=null && claimsDetail.getMobileNumber()!=null) {
					Mobilemaster	mobilemaster	=	new Mobilemaster();
					mobilemaster.setMobileno(claimsDetail.getMobileNumber());
					claimsDetail.setMobilemaster(mobilemaster);
					claimsDetail	=	forteDBService.saveClaimsDetails(claimsDetail);
					if(claimsDetail!=null)
						restResponse.setResponse(claimsDetail);
					else {
						restResponse.setStatus(1);
						restResponse.setResponse("Can't able to save claimsDetail "+claimsDetail);
					}
				}else {
					restResponse.setStatus(1);
					restResponse.setErrorMessage("Can't process thi request because mobile number is not present in request object");
					restResponse.setResponse("Can't process thi request because mobile number is not present in request object");
				}
				
		}catch(Exception xe) {
			log.error("Exception while executing saveClaimsDetails ",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while executing saveClaimsDetails ",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}
}
