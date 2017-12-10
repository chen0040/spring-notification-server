# spring-notification-server

Demo of connecting java client to spring web application via websocket

# Usage

### Spring Notificatin Server

git clone this project, run the "./make.ps1" powershell script in the project root directory to build spring-notification-server.jar
into the "bin" folder.

Run the following command to start the spring-notification-server at http://localhost:8080

```bash
java -jar bin/spring-notification-server.jar
```

The spring-notification-server defines an end point at http://localhost:8080/my-ws and sends a ping message to any connected client that subscribe to its topic "/topics/event" every 10 seconds. the angularjs demo can be viewed by navigating to http://localhost:8080 on your web browser.

### Java WebSocket Client

The spring-notification-java-client project contains the implementation of the java websocket client. To subscribe to the "/topics/event" at http://localhost:8080/my-ws, run the following java codes:

```java

SpringBootWebSocketClient client = new SpringBootWebSocketClient();
client.setId("sub-001");
TopicHandler handler = client.subscribe("/topics/event");
handler.addListener(new StompMessageListener() {
 @Override
 public void onMessage(StompMessage message) {
    System.out.println(message.getHeader("destination") + ": " + message.getContent());
 }
});
client.connect("ws://localhost:8080/my-ws/websocket");
Thread.sleep(60000L);
client.disconnect();
```

### Android WebSocket Client

The spring-notification-android-client project contains the implementation of the android websocket client. To subscribe to the "/topics/event" at http://localhost:8080/my-ws, run the following android codes:

```java

SpringBootWebSocketClient client = new SpringBootWebSocketClient();
client.setId("sub-001");
TopicHandler handler = client.subscribe("/topics/event");
handler.addListener(new StompMessageListener() {
 @Override
 public void onMessage(StompMessage message) {
    System.out.println(message.getHeader("destination") + ": " + message.getContent());
 }
});
client.connect("ws://localhost:8080/my-ws/websocket");
Thread.sleep(60000L);
client.disconnect();
```

### Angular 4 Application

To run the angular application that communicate with the spring-notification-server:


```bash 
cd ng4-application
npm install
ng serve
```

This will start the angular application at http://localhost:4200 

The websocket implementation that subscribe to "/topics/event" websocket topic of the spring-notification-server can be found in the app.service.ts and app.component.ts



