package ag.ifpb.pod.rmi.heroku;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ag.ifpb.pod.rmi.heroku.share.Message;
import ag.ifpb.pod.rmi.heroku.share.Publisher;
import ag.ifpb.pod.rmi.heroku.share.Subscriber;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Subscriber{
  private final Publisher stub;
  
  public ChatClientImpl(Publisher publisher) throws RemoteException{
    stub = publisher;
  }

  
  //@Override
  public void sendMessage(Message message) throws RemoteException {
    stub.publish(message);
  }
  
  //@Override
  public void update(Message message) throws RemoteException {
    System.out.println(message.from() + ": " + message.getText());
  }
  
}
