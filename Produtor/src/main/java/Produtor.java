import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Produtor {
    private static int numeroMensagens = 1000;

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("Conexão iniciada...");

        Topic topico = (Topic) context.lookup("numbers");

        MessageProducer producer = session.createProducer(topico);

        for(int numero = 0; numero < numeroMensagens; numero++){
            String text = String.valueOf(numero+1);
            Message message = session.createTextMessage(text);
            System.out.println("Transmitindo mensagem: " + text);
            producer.send(message);
        }

        connection.close();
        context.close();
        System.out.println("Conexão finalizada...");
    }
}
