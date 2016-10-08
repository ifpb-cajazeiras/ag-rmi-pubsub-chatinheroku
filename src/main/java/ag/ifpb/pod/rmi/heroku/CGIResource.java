package ag.ifpb.pod.rmi.heroku;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class CGIResource extends ServerResource {

  @Post
  public Representation forward(Representation rep){
    //
    String rmiPort = getQueryValue("forward");
    //
    String url = "http://localhost:" + rmiPort + "/";
    ClientResource resource = new ClientResource(url);
    return resource.post(rep);
  }
  
}
