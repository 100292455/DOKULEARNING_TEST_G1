package es.uc3m.tiw.web.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import es.uc3m.tiw.model.Mensaje;

public class EscribirEnQueue {
	
	// Usando CDI no es necesario inyectar el ConnectionFactory
	 @Resource(name = "jms_tiwConnectionFactory")	
	 private  ConnectionFactory connectionFactory;
	
	  @Resource(lookup = "jms_mensajes")
	  private  Queue queue;
	 // @Inject
	 // private JMSContext jmsContext;

	  public void enviar(Mensaje mensaje)
	  {

		  try {
			MessageProducer messageProducer;
			  ObjectMessage message;
			  Connection connection = connectionFactory.createConnection();
			  
			  Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			  
			  

			  messageProducer = session.createProducer(queue);
			  message = session.createObjectMessage();
			  message.setObject(mensaje);


			  messageProducer.send(message);
			  messageProducer.close();
			  session.close();
			  connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
}
