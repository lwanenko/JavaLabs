package lab4;

import java.util.Random;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class TopicProducer implements Runnable, MessageListener{
    static boolean done_f = false;
    static boolean done_g = false;
    static boolean result_f = false;
    static boolean result_g = false;
    public static boolean global_done = false;
    public static boolean global_result = false;
    private final String brokerUrl;
    public final String topicName;
    String x;
   
 
    public TopicProducer(String brokerUrl, String topicName , Integer _x)
    {
        this.brokerUrl = brokerUrl;
        this.topicName = topicName;
        x = _x.toString();
    }
   
    public void run()
    {
        try
        {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//Session.AUTO_ACKNOWLEDGE
            Destination destination = session.createTopic(topicName);
            Destination temp_dest = session.createTemporaryTopic();
            MessageConsumer responseConsumer = session.createConsumer(temp_dest);
            responseConsumer.setMessageListener(this);
            MessageProducer producer = session.createProducer(destination);    
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);    
            String text = x;
            TextMessage message = session.createTextMessage(text);
            message.setJMSReplyTo(temp_dest);
            String correlationId = this.createRandomString();
            message.setJMSCorrelationID(correlationId);
            producer.send(message);
            for(int i = 0 ; i < 1 ; i %= 1){
                Thread.sleep(500);
                Boolean res = result_f && result_g;
                if(done_f && done_g){
                    global_done = true;
                    global_result = res;
                    break;
                }
                if(done_f && !result_f){
                    global_done = true;
                    global_result = false;
                    break;
                }
                if(done_g && !result_g){
                    global_done = true;
                    global_result = false;
                    break;
                }
            }
            session.close();
            connection.close();
        }
        catch (Exception e)
        {
        }
    }
   
    public void onMessage(Message message) {
        try{
            if (message == null)
                return;
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;            
                String text = textMessage.getText();
                if(message.getJMSCorrelationID().equals("F")){
                    done_f = true;
                    result_f = Boolean.parseBoolean(text);
                //  System.out.println("f(" + x + ") = " + text);
                }else{
                    done_g = true;
                    result_g = Boolean.parseBoolean(text);
                //  System.out.println("g(" + x + ") = " + text);
                }
            }
        }catch (Exception e)
        {
        }
    }
   
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
}