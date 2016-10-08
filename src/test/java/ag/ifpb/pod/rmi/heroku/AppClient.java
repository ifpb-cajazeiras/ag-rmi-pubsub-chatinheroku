package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import ag.ifpb.pod.rmi.heroku.service.TopicManager;
import ag.ifpb.pod.rmi.heroku.share.Message;
import ag.ifpb.pod.rmi.heroku.share.Polling;
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
    final String uuid = UUID.randomUUID().toString();
    //
    Registry registry = getRegistry();
    TopicManager manager = (TopicManager) registry.lookup("__ChatServer__");
    Topic topic = (Topic) manager;
    Publisher publisher = (Publisher) manager;
    Polling polling = (Polling) manager;
    //
    final ChatClientImpl client = new ChatClientImpl(uuid, publisher, polling);
    topic.register(uuid);
    //
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          client.update();
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    }, 1000, 10000);//1s, 10s
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
