package lab4;

import java.util.Random;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class F implements Runnable, ExceptionListener{
    private final String brokerUrl;
    private final String topicName = "F";
 
    public F(String brokerUrl){
        this.brokerUrl = brokerUrl;
    }
   
    public void run(){
        try
        {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topicName);
            MessageConsumer consumer = session.createConsumer(destination);
            MessageProducer replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            Message message = consumer.receive();
            if(message == null)
                return;
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;            
                String text = textMessage.getText();  
                TextMessage response = session.createTextMessage();  
         //       Thread.sleep(66);
         //       Random random = new Random();
                String cur = message.getJMSDestination().toString();
                cur = cur.substring(cur.length() - 1);
                int wait = 0;//Math.abs((random.nextInt() % 10000)) + 1000;
                wait = 3500;
         //       Thread.sleep(66);
                Thread.sleep(wait);
                Boolean ans = Integer.parseInt(text) >= 0;
                response.setText(ans.toString());
                response.setJMSCorrelationID(cur);
                replyProducer.send(message.getJMSReplyTo() , response);
            }
            consumer.close();
            session.close();
            connection.close();
            return;
        }
        catch (Exception e)
        {
        }
    }
   
    public synchronized void onException(JMSException ex)
    {
        ex.printStackTrace();
    }
 
}