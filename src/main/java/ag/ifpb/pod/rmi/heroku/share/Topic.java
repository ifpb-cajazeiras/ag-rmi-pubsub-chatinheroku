package ag.ifpb.pod.rmi.heroku.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Topic extends Remote{
  void register(String uuid) throws RemoteException;
}
