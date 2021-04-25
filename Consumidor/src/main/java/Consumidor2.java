import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumidor2 {

    public static void main(String[] args) throws JMSException, NamingException {

        String clienteID = "consumidor2";
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.setClientID(clienteID);
        connection.start();
        System.out.println("Conexão iniciada...");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topico = (Topic)context.lookup("numbers");
        MessageConsumer consumer = session.createDurableSubscriber(topico, clienteID + "-signature");
        //consumer.setMessageListener( new ConsumidorHandler());

        do{
            System.out.println("Aguardando mensagem...");
            Message message = consumer.receive();
            String msgBody = ((TextMessage) message).getText();
            System.out.println(clienteID +": Mensagem recebida: [" + msgBody + "]");
            message.acknowledge();
        } while(true);
    }
}
