/**
 * 
 */
package com.naren.listner;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;

/**
 * @author ntanwa
 *
 */
public class ActiveAMQListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(ActiveAMQListener.class);

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof ActiveMQTextMessage) {
			try {
				LOG.debug("Message Received :::: "+((TextMessage) message).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
