package com.candela.epos.solutions.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.candela.epos.solutions.db.repository.UserRepository;

@Configuration
public class EposUtil {

	private Logger log = LoggerFactory.getLogger(EposUtil.class);

	@Autowired
	private UserRepository userRepository;

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}

	public static boolean isNullOrEmpty(List<?> element) {
		return element == null || element.isEmpty();
	}

	public boolean existsByEmail(String email) {
		if (email == null) {
			return true;
		}
		Long existsByEmail = userRepository.countByEmail(email);
		log.info("Printing existing email id count: " + existsByEmail);
		if (existsByEmail >= 1) {
			return false;
		}
		return true;
	}

	public boolean existsByagentId(String agentId) {
		if (agentId == null) {
			return true;
		}
		Long existsByAgentId = userRepository.countByAgentId(agentId);
		log.info("Printing existing agentId count: " + existsByAgentId);
		if (existsByAgentId >= 1) {
			return false;
		}
		return true;
	}

}
