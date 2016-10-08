package ag.ifpb.pod.rmi.heroku.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ag.ifpb.pod.rmi.heroku.share.Message;
import ag.ifpb.pod.rmi.heroku.share.Subscriber;

@SuppressWarnings("serial")
public class TopicManagerImpl extends UnicastRemoteObject implements TopicManager {
  private Map<String, Subscriber> subscribers = new HashMap<String, Subscriber>();
  private Map<String, List<Message>> messages = new HashMap<String, List<Message>>();
  
  public TopicManagerImpl() throws RemoteException{}

  //@Override
  public void register(String uuid, Subscriber subscriber) throws RemoteException {
    System.out.println("registrando um subscriber: " + uuid);
    subscribers.put(uuid, subscriber);
    messages.put(uuid, new ArrayList<Message>());
  }

  //@Override
  public void publish(Message message) throws RemoteException {
    System.out.println("publicando uma mensagem de: " + message.from());
    Set<String> uuids = subscribers.keySet();
    for (String uuid : uuids) {
      if (!uuid.equals(message.from())){
        List<Message> list = messages.get(uuid);
        list.add(message);
      }
    }
  }
  
  //@Override
  public void notifySubscribers() throws RemoteException {
    Set<String> uuids = subscribers.keySet();
    for (String uuid : uuids) {
      //
      Subscriber subscriber = subscribers.get(uuid);
      //
      List<Message> notifications = messages.get(uuid);
      if (notifications.isEmpty()) continue;
      //
      for (Message message : notifications) {
        System.out.println("notificando " + uuid + " com mensagem de " + message.from());
        subscriber.update(message);
      }
      //
      notifications.clear();
    }
  }

}
