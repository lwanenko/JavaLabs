package lab4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
public class Main {
    private static final String BROKER_URL = "tcp://localhost:61616?jms.prefetchPolicy.all=1000";
    private static final Set<String> TOPICS = new HashSet<>(Arrays.asList("F" , "G"));// , "test_topic2"
    static TopicProducer ck = null;
   
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print("Введiть х: ");
        Process proc = Runtime.getRuntime().exec("java -classpath E:\\Eclipse_jee\\workspace\\CalcF\\build\\classes;E:\\Eclipse_jee\\workspace\\CalcF\\javax.jms.jar;E:\\Eclipse_jee\\workspace\\CalcF\\activemq-all-5.14.3.jar CalcF");
//      ProcessBuilder proc = new ProcessBuilder("java.exe" , "-cp" , "javax.jms.jar,activemq-all-5.14.3.jar" , "CalcF");
//      Process pr = proc.start();
        Integer x = sc.nextInt();
        for (String topic : TOPICS)
        {
            TopicProducer producer = new TopicProducer(BROKER_URL, topic, x);
            if(ck == null)ck = producer;
            thread(producer, true);
        }
        Boolean interrupt = false;
        Boolean ask = true;
        for(int i = 0 ; !interrupt ; ){
            Thread.sleep(50);
            i += 50;
            if(ck.global_done){
                boolean rf = ck.result_f;
                boolean rg = ck.result_g;
                System.out.println("f(" + x + ") || g(" + x + ") = " + (rf || rg));
                //proc.destroy();
                return;
            }
            if(i >= 10000 && ask){
                String ans = "";
                System.out.printf("Бажаєте припинити обрахунки?\n\t Y1 - продовжити, \n\tY2 -продовжити та не питати більше, \n\t N -зупинити обрахунки\n");
                ans = sc.next();
                switch (ans) {
                    case "Y1":
                        break;
                    case "Y2":
                        ask = false;
                        break;
                    default:
                        interrupt = true;
                        break;
                }
                i = 0;
            }
        //  proc.destroy();
        }
    }
    public static void thread(Runnable runnable, boolean daemon)
    {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}