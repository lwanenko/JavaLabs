package lab4;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
 
public class CalcF {
    private static final String BROKER_URL = "tcp://localhost:61616?jms.prefetchPolicy.all=1000";
   
    public static void main(String[] args) {
/*     
        String text = "Hello world";
        BufferedWriter output = null;
        try {
            File file = new File("C:\\example.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            try {
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          }
        }*/
       
       
        F Fconsumer = new F(BROKER_URL);
        G Gconsumer = new G(BROKER_URL);
        thread(Fconsumer, false);
        thread(Gconsumer, false);
       // System.out.print("dfsdf");
    }
   
    public static void thread(Runnable runnable, boolean daemon)
    {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
 
}
