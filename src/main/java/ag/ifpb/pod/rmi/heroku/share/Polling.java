package ag.ifpb.pod.rmi.heroku.share;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Polling extends Remote{
  List<Message> poll(String uuid) throws RemoteException;
}
