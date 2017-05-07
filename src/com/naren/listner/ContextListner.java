package com.naren.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class ContextListner
 *
 */
@WebListener
public class ContextListner implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListner.class);
	
	//private BrokerService broker;
    /**
     * Default constructor. 
     */
    public ContextListner() {
    	//broker = new BrokerService();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	LOG.debug("ContextListner.contextDestroyed()");
    	 
    	try {
			//broker.stop();
			LOG.debug("JMS broker spoped.");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	LOG.debug("ContextListner.contextInitialized()");
    	
    	// configure the broker  
    	try {
			//broker.addConnector("tcp://localhost:61616");
			//broker.start();  
			LOG.debug("JMS broker started.");
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
    }
	
}
