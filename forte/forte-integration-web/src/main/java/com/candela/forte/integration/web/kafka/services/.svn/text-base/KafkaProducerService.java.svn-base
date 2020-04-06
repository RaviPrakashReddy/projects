package com.candela.forte.integration.web.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.candela.forte.integration.db.model.RestResponse;

@Service
public class KafkaProducerService {

	private Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public RestResponse sendMessageToKafka(String topic, String message) throws Exception, Error {
		try {
			RestResponse restResponse = new RestResponse();
			if (log.isDebugEnabled())
				log.debug(
						"started executing sendMessageToKafka based on topic :: " + topic + ":: message ::" + message);

			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
			log.info("response from kafka ::" + future);
			if (future != null) {
				log.info(future.get().toString());

				future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
					String errorMsg = null;

					@Override
					public void onSuccess(SendResult<String, String> result) {
						errorMsg = "Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset()
								+ "]";
						log.info(errorMsg);
						log.info("Successfully sent message to kafka topic : " + topic);
					}

					@Override
					public void onFailure(Throwable ex) {
						errorMsg = "Unable to send message=[" + message + "] due to : " + ex.getMessage();
						log.info(errorMsg);
						log.info("sending message to kafka topic is faild : " + topic + "with exception"
								+ ex.getMessage());
					}
				});

				if (future.get().getRecordMetadata().serializedValueSize() >= 0) {
					restResponse.setResponse(
							" Message Successfully sent to topic : " + future.get().getProducerRecord().topic() + " : "
									+ future.get().getRecordMetadata().serializedValueSize());
				} else {
					restResponse.setStatus(1);
					restResponse.setErrorMessage("No response from kafka");
				}

			} else {
				restResponse.setStatus(1);
				restResponse.setErrorMessage("No response from kafka");
			}
			return restResponse;
		} catch (Exception xe) {
			log.error("Exception while executing sendMessageToKafka", xe);
			throw xe;
		} catch (Error error) {
			log.error("Error while executing sendMessageToKafka", error);
			throw error;
		}

	}
}
