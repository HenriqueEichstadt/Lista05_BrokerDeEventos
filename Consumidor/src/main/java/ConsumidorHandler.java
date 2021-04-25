import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumidorHandler implements MessageListener {

    private StringBuilder taEventosRecebidos = new StringBuilder();

    @Override
    public void onMessage(Message message) {
        String msgBody = null;
        try{
            msgBody = ((TextMessage)message).getText();
            taEventosRecebidos.append(msgBody);
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
