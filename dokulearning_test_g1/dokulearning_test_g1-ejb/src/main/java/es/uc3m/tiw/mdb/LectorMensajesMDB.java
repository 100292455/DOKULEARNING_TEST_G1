package es.uc3m.tiw.mdb;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MensajeDAO;
import es.uc3m.tiw.model.dao.MensajeDAOImpl;

/**
 * Message-Driven Bean implementation class for: LectorMensajesMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms_tiwConnectionFactory"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms_mensajes")
@TransactionManagement(TransactionManagementType.BEAN)
public class LectorMensajesMDB implements MessageListener {
	private MensajeDAO msgDao;
	private CursoDAO curDao;
	@PersistenceContext(unitName="demoTIW")
	EntityManager em;
	@Resource
	UserTransaction ut;
	
	@PostConstruct
	private void configurador(){
		System.out.println("****POSTCONSTRUCT LectorMnsajesMDB");
		msgDao = new MensajeDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em,ut);
	}

    /**
     * Default constructor. 
     */
    public LectorMensajesMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
       System.out.println("******Ha llegado un mensaje");
        
        ObjectMessage om =  (ObjectMessage) message;
        Mensaje mensaje = null;
        try {
        		mensaje = (Mensaje) om.getObject();
        		msgDao.guardarMensaje(mensaje);
        		
		} catch (JMSException | SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}    
        Curso c = mensaje.getCurso();
        Collection<Mensaje> listaMensajesCursoActual = msgDao.recuperarMensajePorCurso(mensaje.getID_curso());
        c.setMensaje(listaMensajesCursoActual);
        try {
			curDao.modificarCurso(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
        
    }
        
    }
