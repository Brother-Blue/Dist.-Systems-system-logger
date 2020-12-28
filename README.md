# System Logger

## System Logger High-level description 

The System Logger contains a subscriber, a Logger class and three files for logging responses from the Appointment Handler. The subscriber subscribes to the topics it is supposed to log, and the logger class redirects the response to the correct file.

### Communication
All communication between the System Logger and the rest of the system is done via a broker, using the MQTT-protocol applying a Publish/Subscribe architectural style. 

### How to run

1. Install MQTT-broker
    1. Download the Mosquitto MQTT-broker from [here](https://mosquitto.org/)
    1. Locate and open the Mosquitto configuration file (mosquitto.conf)
    1. Add "#Websockets
            listener 9001
            protocol websockets"
    1. Restart the broker
    
1. Move to the first logger folder, where you will see a Pom file.

1. Here, you run: mvn clean install

1. Then, you should move to the target folder, where you run: java -jar logger.jar