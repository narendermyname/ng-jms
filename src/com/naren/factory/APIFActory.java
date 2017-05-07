/**
 * 
 */
package com.naren.factory;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author ntanwa
 *
 */
public final class APIFActory implements ApplicationContextAware,Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 33303637177860761L;
	
	private static ApplicationContext context;
	
	/**
	 * 
	 */
	private APIFActory() {
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		context = appContext;
	}
	/**
	 * Factory method
	 * @return
	 */
	public static APIFActory getInstance(){
		return FactoryHelper.INSTANCE;
	}
	/**
	 * Factory Helper class
	 * @author ntanwa
	 *
	 */
	private static class FactoryHelper{
		private static final APIFActory INSTANCE = new APIFActory();
	}
	/**
	 * ReadResolve method use to prevent creating of new object on deserilization
	 * @return
	 * @throws ObjectStreamException
	 */
	protected APIFActory readResolve() throws ObjectStreamException{
		return getInstance();
	}
	/**
	 * Get Active AMQ Destination
	 * @return
	 */
	public ActiveMQDestination getActiveMQDestination(){
		return (ActiveMQDestination) context.getBean("destination");
	}
	/**
	 * Get JMS Template
	 * @return
	 */
	public JmsTemplate getJMSTemplate(){
		return (JmsTemplate) context.getBean("jmsTemplate");
	}
}
