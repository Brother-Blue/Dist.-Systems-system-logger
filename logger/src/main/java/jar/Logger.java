package jar;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
public class Logger {

	private final static String DEVICEROOT = "dentistimo/";

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
				System.out.println("Please choose an approperiate topic.");
		}

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

	/* 
	The base functionality for the FileWriter.
	Kept in case it is needed in the future.

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
	*/
}
