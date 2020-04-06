package com.candela.epos.solutions.service;

import java.util.List;

import com.candela.epos.solutions.model.User;

public interface UserService {

	// Custom methods

	User create(User user) throws Exception;

	List<User> createListUsers(List<User> user);

	User findByagentId(String agentId) throws Exception;

	User findByKey(String key);

	User findByUsername(String username) throws Exception;

	User deleteByagentId(String agentId) throws Exception;

	//User updateByagentId(String agentId, User user) throws Exception;

	User update(String agentId, User user) throws Exception;
}
