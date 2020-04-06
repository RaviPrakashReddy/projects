package com.candela.epos.solutions.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.candela.epos.solutions.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByagentId(String agentId);

	User findByKey(String key);

	User findByUsername(String username);

	Long countByEmail(String email);

	Long countByAgentId(String agentId);

	User deleteByagentId(String agentId);

	// User updateByagentId(User updateUser);

	User findByEmail(String email);

}
