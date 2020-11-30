package jar;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Logger {

	public static void main(String[] args) {
		
		try {
			Subscriber subscriber = new Subscriber();
			subscriber.subscribeToMessages("topic");
			log("hello");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void log(String data){

		//First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", data);
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");
         
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put("employee", employeeDetails);
         
        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");
         
        JSONObject employeeObject2 = new JSONObject(); 
        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);
         
        //Write JSON file
        try (FileWriter file = new FileWriter("logger/src/main/java/jar/Log.json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
