package ag.ifpb.pod.rmi.heroku;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ag.ifpb.pod.rmi.heroku.share.Message;
import ag.ifpb.pod.rmi.heroku.share.Polling;
import ag.ifpb.pod.rmi.heroku.share.Publisher;
import ag.ifpb.pod.rmi.heroku.share.Subscriber;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Subscriber{
  private final String subscriberUUID;
  private final Publisher publisherStub;
  private final Polling pollingStub;
  
  public ChatClientImpl(String uuid, Publisher publisher, Polling polling) throws RemoteException{
    subscriberUUID = uuid;
    publisherStub = publisher;
    pollingStub = polling;
  }
  
  //@Override
  public void sendMessage(Message message) throws RemoteException {
    publisherStub.publish(message);
  }
  
  public void update() throws RemoteException{
    List<Message> message = pollingStub.poll(subscriberUUID);
    for (Message msg : message) {
      System.out.println(msg.from() + ": " + msg.getText());
    }
  }
  
}
