import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Component
public class receiveQueueExample {

    static String connectionString="Endpoint=sb://hs-cc-poc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=tGj4bD3KtsUjkhaJOqdvspnUa+2rnxUkM+ASbBJa/qg=";
    static String topicname="cctopic";
    static String topicsubscription="cctopic_subscription";
    public static void main (String[] args) throws InterruptedException {
        receivemessages();
    }

    public static void receivemessages() throws InterruptedException{

        ServiceBusProcessorClient receive  = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .topicName(topicname)
                .subscriptionName(topicsubscription)
                .processMessage(receiveQueueExample :: processMessage)
                .processError(context -> processError(context))
                .buildProcessorClient();
        receive.start();

    }

    private static void processMessage(ServiceBusReceivedMessageContext context){

        ServiceBusReceivedMessage message = context.getMessage();
        System.out.println("Processing message :"+message.getBody());

    }
    private static void processError(ServiceBusErrorContext context){

    }

}
