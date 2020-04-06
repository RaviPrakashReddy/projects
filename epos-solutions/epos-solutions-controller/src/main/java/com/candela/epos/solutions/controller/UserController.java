package com.candela.epos.solutions.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candela.epos.solutions.model.User;
import com.candela.epos.solutions.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = "/test")
	public String test() {
		return "Hello, Welcome To EPOS Solutions Project ...";
	}

	@PostMapping("/saveuserdetails")
	public User saveUserDetails(@Valid @RequestBody User user) throws Exception {
		log.info("Started executing saveuserdetails for the User:: " + user);
		try {
			if (user != null) {
				return userServiceImpl.create(user);
			} else {
				throw new Exception("Can't able to process due to request object is empty or null");
			}
		} catch (Exception ex) {
			log.error("Exception while executing saveUserDetails:: ", ex);
			throw ex;
		} catch (Error error) {
			log.error("Error while executing saveUserDetails:: ", error);
			throw error;
		}
	}

	@PostMapping("/savelistofusers")
	public List<User> saveListOfUsers(@Valid @RequestBody List<User> user) throws Exception {
		log.info("Started executing saveuserdetails for the User:: " + user);

		try {
			if (user != null) {
				return userServiceImpl.createListUsers(user);
			} else {
				throw new Exception("Can't able to process due to request object is empty or null");
			}
		} catch (Exception ex) {
			log.error("Exception while executing saveUserDetails:: ", ex);
			throw ex;
		} catch (Error error) {
			log.error("Error while executing saveUserDetails:: ", error);
			throw error;
		}
	}

	@GetMapping(value = "getbyagentid/{agentId}")
	public User findByAgentId(@PathVariable("agentId") String agentId) throws Exception {
		try {
			log.info("Printing provided AgentId:: " + agentId);
			if (agentId != null) {
				return userServiceImpl.findByagentId(agentId);
			} else {
				throw new Exception("Can't able to findByAgentId due to request agentId is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing findByAgentId:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing findByAgentId:: ", error);
			throw error;
		}
	}

	@GetMapping(value = "getbykey/{agentkey}")
	public User findByKey(@PathVariable("agentkey") String key) throws Exception {
		try {
			log.info("Printing provided KEY:: " + key);
			if (key != null) {

				return userServiceImpl.findByKey(key);
			} else {
				throw new Exception("Can't able to findByKey due to request Key is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing findByKey:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing findByKey:: ", error);
			throw error;
		}
	}

	@GetMapping(value = "getbyusername/{username}")
	public User findByUsername(@PathVariable("username") String username) throws Exception {
		try {
			log.info("Printing provided Username:: " + username);
			if (username != null) {
				return userServiceImpl.findByUsername(username);
			} else {
				throw new Exception("Can't able to findByUsername due to request Username is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing findByUsername:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing findByUsername:: ", error);
			throw error;
		}
	}

	@DeleteMapping(value = "deletebyagentid/{deleteagentId}")
	public User deleteByAgentId(@PathVariable("deleteagentId") String agentId) throws Exception {
		try {
			log.info("Deleting provided AgentId:: " + agentId);
			if (agentId != null) {
				return userServiceImpl.deleteByagentId(agentId);
			} else {
				throw new Exception("Can't able to process due to request agentId is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing deleteByAgentId:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing deleteByAgentId:: ", error);
			throw error;
		}
	}

	@PutMapping(value = "updatebyagentid/{agentId}")
	public User updateByagentId(@PathVariable("agentId") String agentId, @Valid @RequestBody User user)
			throws Exception {
		try {
			log.info("Updating user details which provided AgentId:: " + agentId);
			if (agentId != null && user != null) {
				return userServiceImpl.update(agentId, user);
			} else {
				throw new Exception("Can't able to updateByagentId due to request agentId or user is empty or null");
			}
		} catch (Exception xe) {
			log.error("Exception while executing updateByagentId:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing updateByagentId:: ", error);
			throw error;
		}
	}

	@PutMapping(value = "resetpassword/{resetPassword}")
	public User resetPassword(@PathVariable("resetPassword") String agentId, @Valid @RequestBody User user)
			throws Exception, Error {
		try {
			return user;
		} catch (Exception xe) {
			log.error("Exception while executing ResetPassword:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing ResetPassword:: ", error);
			throw error;
		}

	}

}
