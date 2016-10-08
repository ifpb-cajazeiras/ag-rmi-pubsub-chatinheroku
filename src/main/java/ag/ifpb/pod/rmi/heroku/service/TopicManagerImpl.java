package ag.ifpb.pod.rmi.heroku.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ag.ifpb.pod.rmi.heroku.Debug;
import ag.ifpb.pod.rmi.heroku.share.Message;

@SuppressWarnings("serial")
public class TopicManagerImpl extends UnicastRemoteObject implements TopicManager {
  private List<String> subscribers = new ArrayList<String>();
  private Map<String, List<Message>> messages = new HashMap<String, List<Message>>();
  
  public TopicManagerImpl() throws RemoteException{}

  //@Override
  public void register(String uuid) throws RemoteException {
    Debug.info("registrando um subscriber: " + uuid);
    subscribers.add(uuid);
    messages.put(uuid, new ArrayList<Message>());
  }

  //@Override
  public void publish(Message message) throws RemoteException {
    Debug.info("publicando uma mensagem de: " + message.from());
    List<String> uuids = subscribers;
    for (String uuid : uuids) {
      if (!uuid.equals(message.from())){
        List<Message> list = messages.get(uuid);
        list.add(message);
      }
    }
  }
  
  public List<Message> poll(String uuid) throws RemoteException {
    //
    Debug.info("verificando se há notificações para: " + uuid);
    //
    List<Message> result = new ArrayList<Message>();
    //
    List<String> uuids = subscribers;
    for (String suuid : uuids) {
      //
      List<Message> notifications = messages.get(suuid);
      if (notifications.isEmpty()) continue;
      //
      result.addAll(notifications);
      //
      notifications.clear();
    }
    //
    return result;
  }

}
