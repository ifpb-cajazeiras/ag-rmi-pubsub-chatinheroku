package ag.ifpb.pod.rmi.heroku.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscriber extends Remote {
  void update(Message message) throws RemoteException;
}
