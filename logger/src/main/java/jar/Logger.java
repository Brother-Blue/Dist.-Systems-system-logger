package jar;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Logger {

    // Felix had Thread_pool in his example code, but it functions without

    private final IMqttClient middleware;

    public Logger(String broker, String userId) throws MqttException {
        middleware = new MqttClient(broker, userId);
        middleware.connect();
    }

    // Hmmmmmmm what are these ? We will see in his lecture on tuesday :)
    public static void main(String[] args) throws MqttException, InterruptedExceptions {
        Logger logger = new Logger("test-logger", "tcp://localhost:1883");
        logger.subscribeToMessages("frosk");
    }

    private void subscribeToMessages(String sourceTopic) {
		try {
			middleware.subscribe(sourceTopic);
		} catch (MqttSecurityException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

    // Seems important
    @Override
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

    // This will stay for now
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}
}
