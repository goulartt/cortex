package br.com.cortex.currency.config;

import java.io.Serializable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

import org.springframework.jms.core.JmsTemplate;

public class RcsJmsTemplate extends JmsTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1858434206464856305L;

	public RcsJmsTemplate() {
	}

	public RcsJmsTemplate(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	/**
	 * Actually send the given JMS message.
	 *
	 * AF: EXTENDED TO COPY THE PRIORITY FROM THE MESSAGE TO THE PRODUCER
	 *
	 * @param producer the JMS MessageProducer to send with
	 * @param message  the JMS Message to send
	 * @throws JMSException if thrown by JMS API methods
	 */
	@Override
	protected void doSend(MessageProducer producer, Message message) throws JMSException {
		if (getDeliveryDelay() >= 0) {
			producer.setDeliveryDelay(getDeliveryDelay());
		}

		producer.send(message, getDeliveryMode(), message.getJMSPriority(), getTimeToLive());

	}
}