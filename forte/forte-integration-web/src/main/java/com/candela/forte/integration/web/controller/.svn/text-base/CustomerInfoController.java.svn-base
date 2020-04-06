package com.candela.forte.integration.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candela.forte.integration.db.entity.ClaimsDetail;
import com.candela.forte.integration.db.model.CustomerInfo;
import com.candela.forte.integration.db.model.CustomerUpdateinfo;
import com.candela.forte.integration.db.model.RestResponse;
import com.candela.forte.integration.web.services.CustomerInfoRestService;

@RestController
@RequestMapping("/customer")
public class CustomerInfoController {

	private Logger log = LoggerFactory.getLogger(CustomerInfoController.class);

	@Autowired
	private CustomerInfoRestService customerInfoRestService;

	@GetMapping(value = "/test")
	public String test() {
		return "Hello, Welcome To Forte Project...";
	}

	@PostMapping(value = "/rechargeinfo")
	public ResponseEntity<RestResponse> rechargeInformation(@Valid @RequestBody CustomerInfo customerInfo)
			throws Exception, Error {
		RestResponse restResponse = new RestResponse();
		try {
			if (log.isDebugEnabled())
				log.debug("started executing rechargeInformation for the CustomerInfo :: " + customerInfo);

			restResponse = customerInfoRestService.processRechargeInformation(customerInfo);

			if (restResponse != null && restResponse.getStatus() == 0) {
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception xe) {
			log.error("Exception while executing rechargeInformation", xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		} catch (Error error) {
			log.error("Error while executing rechargeInformation", error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/updateinfo")
	public ResponseEntity<RestResponse> updateCustomerInformation(
			@Valid @RequestBody CustomerUpdateinfo customerUpdateinfo) throws Exception, Error {
		RestResponse restResponse = new RestResponse();
		try {
			restResponse = customerInfoRestService.updateCustomerInformation(customerUpdateinfo);

			if (restResponse != null && restResponse.getStatus() == 0) {
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.CREATED);
			}
			return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);

		} catch (Exception xe) {
			log.error("Exception while executing updateCustomerInformation", xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		} catch (Error error) {
			log.error("Error while executing updateCustomerInformation", error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="customerdetails/{mobileno}")
	public ResponseEntity<RestResponse> getCustomerDetaildByMobileNo(@PathVariable("mobileno") String mobileNo)throws Exception,Error{
		RestResponse restResponse = new RestResponse();
		try {
				restResponse	=	customerInfoRestService.getCustomerDetaildByMobileNo(mobileNo);
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
		}catch(Exception xe) {
			log.error("Error while getting customer details",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while getting customer details",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value="callcenterprocessInfo/{mobileno}")
	public ResponseEntity<RestResponse> callcenterprocessInfo(@PathVariable("mobileno") String mobileNo)throws Exception,Error{
		RestResponse restResponse = new RestResponse();
		try {
				restResponse	=	customerInfoRestService.getCallCenterProcessInfo(mobileNo);
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
		}catch(Exception xe) {
			log.error("Error while  callcenterprocessInfo details",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while  callcenterprocessInfo details",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="rechargeSummaryDetails/{mobileno}")
	public ResponseEntity<RestResponse> getRechargeSummaryDetails(@PathVariable("mobileno") String mobileNo)throws Exception,Error{
		RestResponse restResponse = new RestResponse();
		try {
				restResponse	=	customerInfoRestService.getRechargeSummaryDetails(mobileNo);
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
		}catch(Exception xe) {
			log.error("Error while  getRechargeSummaryDetails details",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while  getRechargeSummaryDetails details",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="rechargeHistory/{mobileno}")
	public ResponseEntity<RestResponse> getRechargeHistory(@PathVariable("mobileno") String mobileNo)throws Exception,Error{
		RestResponse restResponse = new RestResponse();
		try {
				restResponse	=	customerInfoRestService.getRechargeHistory(mobileNo);
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
		}catch(Exception xe) {
			log.error("Error while  getRechargeHistory details",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while  getRechargeHistory details",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping(value="claimsDetails/{mobileno}")
	public ResponseEntity<RestResponse> getClaimsDetails(@PathVariable("mobileno") String mobileNo)throws Exception,Error{
		RestResponse restResponse = new RestResponse();
		try {
				restResponse	=	customerInfoRestService.getClaimsDetails(mobileNo);
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
		}catch(Exception xe) {
			log.error("Error while  getClaimsDetails ",xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		}catch(Error error) {
			log.error("Error while  getClaimsDetails ",error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(value = "/saveclaimsDetails")
	public ResponseEntity<RestResponse> saveClaimsDetails(@RequestBody ClaimsDetail claimsDetail) throws Exception, Error {
		RestResponse restResponse = new RestResponse();
		try {
			restResponse = customerInfoRestService.saveClaimsDetails(claimsDetail);

			if (restResponse != null && restResponse.getStatus() == 0) {
				return new ResponseEntity<RestResponse>(restResponse, HttpStatus.CREATED);
			}
			return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);

		} catch (Exception xe) {
			log.error("Exception while executing saveClaimsDetails", xe);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(xe.getMessage());
		} catch (Error error) {
			log.error("Error while executing saveClaimsDetails", error);
			restResponse.setStatus(1);
			restResponse.setErrorMessage(error.getMessage());
		}
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.NOT_FOUND);
	}
	
}
