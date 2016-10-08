package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.Scanner;
import java.util.UUID;

import ag.ifpb.pod.rmi.heroku.share.Message;
import ag.ifpb.pod.rmi.heroku.share.Publisher;
import ag.ifpb.pod.rmi.heroku.share.Topic;

public class AppClient {

  @SuppressWarnings("restriction")
  private static Registry getRegistry() throws IOException{
    String url = "ag-rmi-pubsub-chatinheroku.herokuapp.com";
    //String url = "localhost";
    RMISocketFactory.setSocketFactory(new sun.rmi.transport.proxy.RMIHttpToCGISocketFactory());
    return LocateRegistry.getRegistry(
        url, 1099, RMISocketFactory.getSocketFactory());
  }
  
  public static void main(String[] args) throws NotBoundException, IOException {
    String uuid = UUID.randomUUID().toString();
    //
    Registry registry = getRegistry();
    Topic topic = (Topic) registry.lookup("__ChatServer__");
    Publisher publisher = (Publisher) registry.lookup("__ChatServer__");
    //
    ChatClientImpl client = new ChatClientImpl(publisher);
    topic.register(uuid, client);
    //
    Scanner scanner = new Scanner(System.in);
    while(true){
      //
      String text = scanner.nextLine();
      //
      Message message = new Message();
      message.setFrom(uuid);
      message.setText(text);
      //
      client.sendMessage(message);
    }
    //
  }
}
