package com.naren.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.naren.factory.APIFActory;

/**
 * Servlet implementation class JMSServlet
 */
@WebServlet("/JMSServlet")
@Component
public class JMSServlet extends HttpServlet {

	Logger LOGGER = Logger.getLogger(JMSServlet.class);
	private JmsTemplate template = APIFActory.getInstance().getJMSTemplate();

	private ActiveMQDestination destination = APIFActory.getInstance().getActiveMQDestination();
	/*protected Topic queue;  

	protected String queueName = "jms/topic/MyTopic";  

	protected String url = "tcp://localhost:61616";  

	protected int ackMode = Session.AUTO_ACKNOWLEDGE;  */

	private static final long serialVersionUID = 1L;

	//	@Resource(name = "foo")
	//	private Topic fooTopic;
	//
	//	@Resource(name = "bar")
	//	private Queue barQueue;

	//	@Resource
	//	private ConnectionFactory connectionFactory;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JMSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(ServletConfig config){
		//context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//template = (JmsTemplate) context.getBean("jmsTemplate");
		//destination = (ActiveMQDestination) context.getBean("destination");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {

			// sending a message
			template.convertAndSend(destination, request.getParameter("message"));

		} catch (Exception e) {
			response.getWriter().append("Served at: ").append("Error While sending message"+e.getMessage());
			LOGGER.error("Error While sending message ",e);
			throw new ServletException("Error While sending message ",e);
		}
		response.getWriter().append("Message: "+request.getParameter("message")+"Sent successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			while(true){
				// receiving a message				TextMessage msg = (TextMessage)template.receive(destination);
				if (msg instanceof TextMessage) {					response.getWriter().append("Message"+msg.getText() +" Received Successfully");;
				}
				LOGGER.debug("Message"+msg.getText() +" Received Successfully");
			}
		} catch(Exception e) {			response.getWriter().append("Error While receiving message");
			LOGGER.error("Error While receiving message ",e);
			throw new ServletException("Error While receiving message",e);
		}
	}

	/*public void processMessage(Message message) {  

		try {  

			TextMessage txtMsg = (TextMessage) message;  

			System.out.println("Received a message: " + txtMsg.getText());  

		} catch (Exception e) {  

			e.printStackTrace();  

		} 

	}

	@SuppressWarnings("unused")
	private void jmsservice(){
		//Sending message
		try {

			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
			javax.naming.Context initCtx = new InitialContext(props);

			//InitialContext initCtx = new InitialContext();  
			Context envContext = (Context) initCtx.lookup("java:comp/env");  
			ConnectionFactory connectionFactory = (ConnectionFactory) envContext.lookup("/jms/ConnectionFactory");  
			Connection connection = connectionFactory.createConnection();  
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
			Destination destination = session.createTopic("jms/topic/MyTopic");  
			MessageProducer producer = session.createProducer(destination);  
			TextMessage msg=session.createTextMessage();  
			msg.setText("Message sent");  
			producer.send(msg);
		} catch (NamingException e) {

			e.printStackTrace();
		} catch (JMSException e) {

			e.printStackTrace();
		}  


		//Receiving message
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);  
			TopicConnection connection = (TopicConnection)connectionFactory.createTopicConnection();  

			connection.start();  
			MessageConsumer consumer = null;  
			Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);  
			queue = session.createTopic(queueName);  
			consumer = session.createConsumer(queue);  

			System.out.println("Waiting for message (max 5) ");  

			for (int i = 0; i < 5; i++) {  
				Message message = consumer.receive();  
				processMessage(message);  

			}  

			System.out.println("Closing connection");  
			consumer.close();  
			session.close();  
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */

}
