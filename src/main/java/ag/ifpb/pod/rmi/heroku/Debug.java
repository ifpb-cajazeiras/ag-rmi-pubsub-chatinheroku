package ag.ifpb.pod.rmi.heroku;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Debug {
  private static Logger logger = Logger.getGlobal();
  
  public static void info(String msg){
    logger.log(Level.INFO, msg);
  }
  
}
