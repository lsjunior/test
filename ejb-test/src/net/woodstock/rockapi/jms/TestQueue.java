package net.woodstock.rockapi.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import net.woodstock.rockframework.domain.ejb.jms.TextMessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TestQueue extends TextMessageListener {

	@Override
	protected void onLocalMessage(TextMessage message) throws JMSException {
		System.out.println("Context: " + this.getContext());
		System.out.println(message.getText());
	}

}
