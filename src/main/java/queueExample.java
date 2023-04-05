import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class TopicSendExample {
    static String connectionString=<ConnectionString>;
    static String topicname=<topic name>;
    public static void main(String[] args) {
            sendMessage();
    }
    static void sendMessage(){
        ServiceBusSenderClient serviceBusSenderClient= new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .topicName(topicname)
                .buildClient();
        serviceBusSenderClient.sendMessage(new ServiceBusMessage("hello ASB try 1"));
        System.out.println("message has been sent");

    }
}
