package jar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

// This class is the implementation of the subscriber.
public class Subscriber implements MqttCallback{

	Logger logger = new Logger();
    private final static ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();
	private final static String BROKER = "tcp://localhost:1883";
	private final static String USER_ID = "test-subscriber";
	private final IMqttClient middleware;

	// Here we connect the subscriber to the middleware.
    public Subscriber() throws MqttException {
        middleware = new MqttClient(BROKER, USER_ID);
		middleware.connect();
		middleware.setCallback(this);
	}
	
	// Here we set the subscriber topic.
    void subscribeToMessages(String topic) {
		THREAD_POOL.submit(() -> {
			try {
				middleware.subscribe(topic);
				System.out.println("subscribed to topic; "+ topic);
			} catch (MqttSecurityException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
			}
		});
	}

    // Here we check for errors with the mqtt collection.
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost!");
		try {
			middleware.disconnect();
			middleware.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}

	// Here we write the message to the correct file depending on the topic, i.e. error or appointment.
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("topic '" + topic + "': " + message);

		String data = message.toString();
		String type = topic.toString();

		logger.writeFile(data, type);
	}
}
