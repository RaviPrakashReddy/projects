package com.candela.epos.solutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.candela.epos.solutions.db.repository.UserRepository;
import com.candela.epos.solutions.emailservice.EposEmailSender;
import com.candela.epos.solutions.model.User;
import com.candela.epos.solutions.service.UserService;
import com.candela.epos.solutions.util.EposUtils;
import com.candela.epos.solutions.util.GenerateRandomPasswordForUser;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EposUtils eposUtil;

	@Autowired
	GenerateRandomPasswordForUser generateRandomPasswordForUser;

	@Autowired
	private EposEmailSender eposEmailSender;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User create(User user) throws Exception {
		log.info("Started executing saving userdetils to Bucket:: ");
		User saveUser = new User();
		try {
			saveUser = this.validateAgentIdAndEmailAndSave(user);

			if (saveUser != null) {
				log.info("Started sending email to Agent from E-POS Solution application::");
				eposEmailSender.sendMail(saveUser.getEmail());
			} else {
				log.info("ePOS-Solution sending emailed failed::");
			}

		} catch (Exception xe) {
			log.error("Exception while saving Userdetails:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while saving Userdetails:: ", error);
			throw error;
		}
		return saveUser;
	}

	public User validateAgentIdAndEmailAndSave(User user) throws Exception {
		log.info("Started Validating agentId and Email for user:: ");
		User validateUserAndSave = new User();
		try {
			if (user.getAgentId() != null) {
				log.info("Checking agentId does existed or not:: ");
				boolean isValidAgentId = eposUtil.existsByagentId(user.getAgentId());
				log.info("Printing isValidAgentId: " + isValidAgentId);
				if (isValidAgentId) {
					if (user.getEmail() != null) {
						log.info("Checking email id does existed or not:: ");
						boolean isValidEmail = eposUtil.existsByEmail(user.getEmail());
						if (isValidEmail) {
							if (user.getPassword() != null)
								user.setPassword(generateRandomPasswordForUser.generateRandomPassword());
							validateUserAndSave = userRepository.save(user);
						} else {
							log.info("Such Email id already existed:: ");
						}
					} else {
						log.info("Can't able to process due to request email id is empty or null:: ");
					}
				} else {
					log.info("Such AagentId already existed:: ");
				}
			} else {
				log.info("Can't able to process due to request agentId is empty or null:: ");
			}
		} catch (Exception xe) {
			log.error("Exception while saving Userdetails:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while saving Userdetails:: ", error);
			throw error;
		}
		return validateUserAndSave;
	}

	@Override
	public List<User> createListUsers(@Valid List<User> user) {
		log.info("Started executing saving userdetils to couchdb:: " + user);

		// User savetodb = null;
		// String s1 = null;
		User exits = new User();
		List<User> userlist = new ArrayList<User>();
		for (User userKey : user) {
			String key = userKey.getKey();
			log.info("Printing User Key:: " + key);

			if (key != null) {
				exits = userRepository.findByKey(key);
				log.info("User Key if existed:: " + exits);
			}

			try {
				if (exits == null) {
					User savetodb = (User) userRepository.save(userKey);
					userlist.add(savetodb);
				} else {
					log.info("User already existed");
				}
			} catch (Exception xe) {
				log.error("Exception while saving Userdetails:: ", xe);
				throw xe;
			} catch (Error error) {
				log.error("Error while saving Userdetails:: ", error);
				throw error;
			}
		}

		return userlist;
	}

	@Override
	public User findByagentId(String agentId) throws Exception {

		try {
			User userAid = userRepository.findByagentId(agentId);
			log.info("Printing return AgentId from Bucket:: " + userAid);
			return userAid;
		} catch (Exception xe) {
			throw xe;
		} catch (Error error) {
			throw error;
		}
	}

	@Override
	public User findByUsername(String username) throws Exception {

		try {
			User uName = userRepository.findByUsername(username);
			log.info("Printing return Username from Bucket:: " + uName);
			return uName;
		} catch (Exception xe) {
			throw xe;
		} catch (Error error) {
			throw error;
		}
	}

	@Override
	public User findByKey(String key) {

		try {
			return userRepository.findByKey(User.getKeyFor(key));
		} catch (Exception xe) {
			log.error("Exception while findKey Userdetails:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while findKey Userdetails:: ", error);
			throw error;
		}
	}

	@Override
	public User deleteByagentId(String agentId) throws Exception {
		try {
			User deleteAgentId = userRepository.deleteByagentId(agentId);
			log.info("Printing deleted AgentId from Bucket:: " + deleteAgentId);
			if (deleteAgentId != null) {
				log.info("Deleted sucessful given agentId:: ");
			} else {
				log.info("Can't able to delete agentId due to request agentId is empty or null:: ");
			}
			return deleteAgentId;
		} catch (Exception xe) {
			log.error("Exception while deleting user:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while deleting user:: ", error);
			throw error;
		}
	}

	@Override
	public User update(String agentId, User user) throws Exception {
		try {
			User saveUserUpdate = new User();
			User findUserAgentId = this.findByagentId(agentId);
			log.info("Printing existed AgentId from Bucket:: " + findUserAgentId);
			if (findUserAgentId != null) {
				log.info("Started updating user details:: ");
				User userUpdate = userRepository.save(user);
			} else {
				log.info("Can't able to update agentId due to request agentId is empty or null:: ");
			}
			return saveUserUpdate;
		} catch (Exception xe) {
			log.error("Exception while updating Userdetails:: ", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while updating Userdetails:: ", error);
			throw error;
		}
	}

}
