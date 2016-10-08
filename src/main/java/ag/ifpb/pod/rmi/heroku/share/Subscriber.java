package ag.ifpb.pod.rmi.heroku.share;

import java.rmi.RemoteException;

public interface Subscriber {
  void update() throws RemoteException;
}
