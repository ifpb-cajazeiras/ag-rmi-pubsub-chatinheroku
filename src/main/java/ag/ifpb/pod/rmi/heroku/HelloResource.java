package ag.ifpb.pod.rmi.heroku;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HelloResource extends ServerResource {

  @Get
  public Representation hello(){
    return new StringRepresentation("HELLO");
  }
  
}
