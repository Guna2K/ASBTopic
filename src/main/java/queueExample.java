import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class queueExample {
    static String connectionString="Endpoint=sb://hs-cc-poc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=tGj4bD3KtsUjkhaJOqdvspnUa+2rnxUkM+ASbBJa/qg=";
    static String topicname="cctopic";
    static String topicsubscription="cctopic_subscription";
    public static void main(String[] args) {
            sendMessage();
    }
    static void sendMessage(){
        ServiceBusSenderClient serviceBusSenderClient= new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .topicName(topicname)
                .buildClient();
        serviceBusSenderClient.sendMessage(new ServiceBusMessage("hello ASB try 2"));
        System.out.println("message has been sent");

    }
}
