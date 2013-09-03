package net.woodstock.rockapi.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import net.woodstock.rockframework.domain.ejb.jms.TextMessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/testTopic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TestTopic extends TextMessageListener {

	@Override
	protected void onLocalMessage(TextMessage message) throws JMSException {
		System.out.println("Context: " + this.getContext());
		System.out.println(message.getText());
	}

}
