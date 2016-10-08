package ag.ifpb.pod.rmi.heroku;

import java.util.logging.Logger;

public class Debug {
  private static Logger logger = Logger.getLogger("AGChatPubSub");
  
  public static void info(String msg){
    logger.info(msg);
  }
  
}
