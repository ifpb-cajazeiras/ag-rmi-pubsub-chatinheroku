package ag.ifpb.pod.rmi.heroku.service;

import ag.ifpb.pod.rmi.heroku.share.Polling;
import ag.ifpb.pod.rmi.heroku.share.Publisher;
import ag.ifpb.pod.rmi.heroku.share.Topic;

public interface TopicManager extends Publisher, Topic, Polling {
}
