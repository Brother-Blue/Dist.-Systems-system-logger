package jar;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
public class Logger {

	public static void main(String[] args) {
		
		try {
			Subscriber subscriber = new Subscriber();
			subscriber.subscribeToMessages("frosk");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	protected void log(String data){
         
        //Write JSON file
        try (FileWriter file = new FileWriter("logger/src/main/java/jar/Log.json")) {
 
            file.write(data);
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
