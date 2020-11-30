package jar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
// import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.MqttMessage;
// import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Logger {

    private final static ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();
    private final IMqttClient middleware;

    public Logger(String userId, String broker) throws MqttException {
        middleware = new MqttClient(broker, userId);
        middleware.connect();
    }

    // Hmmmmmmm what are these ? We will see in his lecture on tuesday :)
    public static void main(String[] args) throws MqttException, InterruptedException {
        Logger logger = new Logger("test-logger", "tcp://localhost:1883");
        logger.subscribeToMessages("frosk");
    }

    private void subscribeToMessages(String sourceTopic) {
		THREAD_POOL.submit(() -> {
			try {
				middleware.subscribe(sourceTopic);
			} catch (MqttSecurityException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
			}
		});
	}

    // Seems important
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost!");
		try {
			middleware.disconnect();
			middleware.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		// Try to reestablish? Plan B?
	}

}
