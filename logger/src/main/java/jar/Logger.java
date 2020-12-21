package jar;

import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Logger {

	private final static String DEVICEROOT = "dentistimo/";

	// Here we subscribe to the topics we write to the files.
	public static void main(String[] args) {
		
		try {
			Subscriber subscriber = new Subscriber();
			subscriber.subscribeToMessages(DEVICEROOT + "log/general");
			subscriber.subscribeToMessages(DEVICEROOT + "log/error");
			subscriber.subscribeToMessages(DEVICEROOT + "log/confirmation");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	// This method writes the message to a specific file depending on the topic.
	protected void writeFile(String data, String type) {
		
		String file = "";

		switch(type) {

			case "dentistimo/log/general":
				file = "../src/main/java/jar/Log.txt";
				break;
			
			case "dentistimo/log/error":
				file = "../src/main/java/jar/Error.txt";
				break;
			
			case "dentistimo/log/confirmation":
				file = "../src/main/java/jar/Confirmation.txt";
				break;
			
			default:
				System.out.println(type + " <- is not a recognised topic" + "Please choose an approperiate topic.");
		}

		// This writes the message to a file as long as there is a specified file to write to.
		if(file != null){
			try (FileWriter FileWriter = new FileWriter(file, true)) {
 
				FileWriter.write(data + "\n");
				FileWriter.flush();
				FileWriter.close();
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
