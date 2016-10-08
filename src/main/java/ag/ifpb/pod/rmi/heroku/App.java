package ag.ifpb.pod.rmi.heroku;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.restlet.Component;
import org.restlet.data.Protocol;

import ag.ifpb.pod.rmi.heroku.service.TopicManager;
import ag.ifpb.pod.rmi.heroku.service.TopicManagerImpl;

public class App {
  
  private static void startServer() throws Exception{
    String _port = System.getenv("PORT") != null ? 
        System.getenv("PORT") : "8080";
    Integer port = Integer.parseInt(_port);
    //
    Component component = new Component();
    component.getServers().add(Protocol.HTTP, port);
    component.getClients().add(new Protocol("http", "HTTP", "", 1099, "1.0"));
    //
    RMIApplication application = new RMIApplication();
    component.getDefaultHost().attach(application);
    //
    component.start();
  }
  
  private static void startRMIServer() throws AccessException, RemoteException, AlreadyBoundException{
    //
    final TopicManager manager = new TopicManagerImpl();
    //
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("__ChatServer__", manager);
    //
//    Timer timer = new Timer();
//    timer.schedule(new TimerTask() {
//      @Override
//      public void run() {
//        try {
//          manager.notifySubscribers();
//        } catch (RemoteException e) {
//          e.printStackTrace();
//        }
//      }
//    }, 1000, 10000);//1s, 10s
    //
  }
  
  public static void main(String[] args) throws Exception {
    startRMIServer();
    startServer();
  }
  
}
