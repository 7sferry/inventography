/************************
 * Made by [MR Ferry™]  *
 * on November 2023     *
 ************************/

package com.example.inventoryservice.controller.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

import static com.example.entities.constant.OrderConstants.ORDERING_ROLLBACK_TOPIC;

@RequiredArgsConstructor
@Slf4j
public class RollbackOrder implements KafkaListenerErrorHandler{
	private final KafkaOperations<String, Object> kafkaOperations;

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception){
		Object payload = message.getPayload();
		log.error("error guys", exception);
		kafkaOperations.send(ORDERING_ROLLBACK_TOPIC, payload);
		return null;
	}
}
