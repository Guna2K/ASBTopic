import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
@Component
public class receiveQueueExample {

    static String connectionString="Endpoint=sb://hs-cc-poc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=tGj4bD3KtsUjkhaJOqdvspnUa+2rnxUkM+ASbBJa/qg=";
    static String topicname="cctopic";
    static String topicsubscription="cctopic_subscription";

    static Integer maxMessages=1;

        ServiceBusReceiverClient receive= new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .receiver()
                .topicName(topicname)
                .subscriptionName(topicsubscription)
                .buildClient();

        @Scheduled(fixedDelay = 3000)
        public void topicConsumer(){
            try{
                IterableStream<ServiceBusReceivedMessage> messages=receive.receiveMessages(maxMessages, Duration.ofMillis(1500));
                processMessages(messages);
            } catch (Exception e){
                System.out.println("Exception occured in first");
            }
        }
        private void processMessages(IterableStream<ServiceBusReceivedMessage> messages){
            messages.stream().forEach(message ->{
                try{
                    System.out.println(message.getCorrelationId());
                    System.out.println("Done receiving");
                } catch (Exception e){
                    System.out.println("Error occured");
                }
                finally {
                    System.out.println("Final");
                }
            });
        }

}
