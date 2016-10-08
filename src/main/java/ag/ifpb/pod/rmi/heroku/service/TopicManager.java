package ag.ifpb.pod.rmi.heroku.service;

import java.rmi.RemoteException;

import ag.ifpb.pod.rmi.heroku.share.Publisher;
import ag.ifpb.pod.rmi.heroku.share.Topic;

public interface TopicManager extends Publisher, Topic {
  void notifySubscribers() throws RemoteException;
}
