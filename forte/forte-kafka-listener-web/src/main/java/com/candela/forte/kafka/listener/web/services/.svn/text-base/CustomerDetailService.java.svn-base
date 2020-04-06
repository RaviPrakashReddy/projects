package com.candela.forte.kafka.listener.web.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candela.forte.integration.db.config.ForteConstants;
import com.candela.forte.integration.db.entity.CustomerDetail;
import com.candela.forte.integration.db.entity.Mobilemaster;
import com.candela.forte.integration.db.model.CustomerUpdateinfo;
import com.candela.forte.integration.db.model.RestResponse;
import com.candela.forte.integration.db.service.ForteDBService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerDetailService {

	private Logger log = LoggerFactory.getLogger(CustomerDetailService.class);
	@Autowired
	private ForteDBService forteDBService;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private SMSService	smservice;
	
	private   SimpleDateFormat simpleDateFormat	=	 new SimpleDateFormat("yyyy-MM-dd");

	public RestResponse processCustomerDetailRequest(String updateDetailMessage) throws Exception, Error {
		RestResponse restResponse = new RestResponse();
		try {
			if (log.isDebugEnabled())
				log.debug("started executing processCustomerDetailRequest for the updateDetailMessage ::"
						+ updateDetailMessage);

			CustomerUpdateinfo customerUpdateinfo = mapper.readValue(updateDetailMessage, CustomerUpdateinfo.class);
			log.info("customerUpdateinfo::" + customerUpdateinfo);

			
			CustomerDetail customerDetail =	forteDBService.getCustomerDetail(customerUpdateinfo.getMobile());
					if(customerDetail==null)
						customerDetail	=	 new CustomerDetail();
					customerDetail	=	prepareCustomerDetail(customerUpdateinfo,customerDetail);

			log.info("customerDetail" + customerDetail.getMobilemaster());

			customerDetail = forteDBService.saveOrUpdateCustomerDetails(customerDetail);
			
			//TODO update mobile master 
			
			if(customerDetail!=null) {
				forteDBService.updateInfoPresentFlag(customerUpdateinfo.getMobile());
			}
			
			//String message	=	forteDBService.getPropertyValue(ForteConstants.CUSTOMER_DETAILS_UPDATED_SMS.getValue());
			
			//smservice.sendSMS(message, customerDetail.getMobilemaster().getMobileno());

		} catch (Exception xe) {
			log.error("Exception while executing processRechargeRequest", xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		} catch (Error error) {
			log.error("Error while executing processRechargeRequest", error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return restResponse;
	}

	private CustomerDetail prepareCustomerDetail(CustomerUpdateinfo customerUpdateinfo,CustomerDetail customerDetail) throws Exception, Error {
		try {
			if (log.isDebugEnabled())
				log.debug("started prepareCustomerDetail for the message " + customerUpdateinfo);

			Mobilemaster mobilemaster = new Mobilemaster();
			if(customerUpdateinfo.getMobile()!=null)
				mobilemaster.setMobileno(customerUpdateinfo.getMobile());
			
			if(customerUpdateinfo.getDob()!=null) {
				
				customerDetail.setDob(new Date(simpleDateFormat.parse(customerUpdateinfo.getDob()).getTime()));
			}
			
			if(customerUpdateinfo.getFirstName()!=null)
				customerDetail.setFirstName(customerUpdateinfo.getFirstName());
			
			if(customerUpdateinfo.getGender()!=null)
				customerDetail.setGender(customerUpdateinfo.getGender());
			
			if(customerUpdateinfo.getIdNumber()!=null)
				customerDetail.setIdNumber(customerUpdateinfo.getIdNumber());
			if(customerUpdateinfo.getIdType()!=null)
				customerDetail.setIdType(customerUpdateinfo.getIdType());
			
			if(customerUpdateinfo.getLastName()!=null)
				customerDetail.setLastName(customerUpdateinfo.getLastName());

				customerDetail.setLastUpdatedDate(new Timestamp(new Date().getTime()));
			
			if(customerUpdateinfo.getNomineeContactNum()!=null)
				customerDetail.setNomineeContactNo(customerUpdateinfo.getNomineeContactNum());
			
			if(customerUpdateinfo.getNomineeName()!=null)
				customerDetail.setNomineeName(customerUpdateinfo.getNomineeName());
			
			if(customerUpdateinfo.getPreferredLanguage()!=null)
				customerDetail.setPrefLang(customerUpdateinfo.getPreferredLanguage());
			
			if(customerUpdateinfo.getNomineeRelation()!=null)
				customerDetail.setRelationNominee(customerUpdateinfo.getNomineeRelation());
			
			if(customerUpdateinfo.getUpdatedBy()!=null)
				customerDetail.setUpdatedBy(customerUpdateinfo.getUpdatedBy());
			
			/*
			 * Set<CustomerDetail> customerDetailSet = new HashSet<CustomerDetail>();
			 * customerDetailSet.add(customerDetail);
			 * mobilemaster.setCustomerDetails(customerDetailSet);
			 */
			
			customerDetail.setMobilemaster(mobilemaster);

			return customerDetail;
		} catch (Exception xe) {
			log.error("Exception while executing prepareCustomerDetail", xe);
			throw xe;
		} catch (Error xe) {
			log.error("error while executing prepareCustomerDetail", xe);
			throw xe;
		}
	}
}
