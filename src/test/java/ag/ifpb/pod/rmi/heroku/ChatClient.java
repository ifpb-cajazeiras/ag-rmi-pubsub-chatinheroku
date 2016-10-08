package ag.ifpb.pod.rmi.heroku;

import java.rmi.RemoteException;

import ag.ifpb.pod.rmi.heroku.share.Message;

public interface ChatClient {
  void sendMessage(Message message) throws RemoteException ;
}
