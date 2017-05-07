package com.naren.servlet;

import java.io.IOException;
import javax.jms.TextMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JMSServlet() {
		super();
	}
	@Override
	public void init(ServletConfig config){
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * JMS Producer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// sending a message
			template.convertAndSend(destination, request.getParameter("message"));

		} catch (Exception e) {
			response.getWriter().append("Served at: ").append("Error While sending message"+e);
			LOGGER.error("Error While sending message ",e);
			throw new ServletException("Error While sending message ",e);
		}
		response.getWriter().append("Message: "+request.getParameter("message")+" Sent successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * JMS Consumer
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			while(true){
				// receiving a message				TextMessage msg = (TextMessage)template.receive(destination);
				if (msg != null) {					response.getWriter().append("Message"+msg.getText() +" Received Successfully");;
				}
				LOGGER.debug("Message"+msg.getText() +" Received Successfully");
			}
		} catch(Exception e) {			response.getWriter().append("Error While receiving message");
			LOGGER.error("Error While receiving message ",e);
			throw new ServletException("Error While receiving message",e);
		}
	}
}
