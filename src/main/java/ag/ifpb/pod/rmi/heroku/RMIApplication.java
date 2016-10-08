package ag.ifpb.pod.rmi.heroku;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RMIApplication extends Application{

  @Override
  public Restlet createInboundRoot() {
    Router router = new Router();
    router.attach("/hello", HelloResource.class);
    router.attach("/cgi-bin/java-rmi.cgi", CGIResource.class);
    return router;
  }
  
}
