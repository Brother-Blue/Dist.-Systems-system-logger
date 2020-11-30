package jar;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
public class Logger {

	public static void main(String[] args) {
		
		try {
			Subscriber subscriber = new Subscriber();
			subscriber.subscribeToMessages("test");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	protected void log(String data){
         
        //Write JSON file
        try (FileWriter file = new FileWriter("../src/main/java/jar/Log.txt", true)) {
 
            file.write(data + "\n");
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
