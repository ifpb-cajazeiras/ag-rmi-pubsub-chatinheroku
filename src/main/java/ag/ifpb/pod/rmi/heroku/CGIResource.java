package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class CGIResource extends ServerResource {

  @Post
  public Representation forward(Representation rep) throws IOException{
    //
    String rmiPort = getQueryValue("forward");
    //
    int bsize = (int) rep.getSize();
    byte[] b = new byte[bsize];
    rep.getStream().read(b);
    //
    ByteArrayRepresentation bar = new ByteArrayRepresentation(b);
    bar.setSize(bsize);
    bar.setMediaType(MediaType.APPLICATION_OCTET_STREAM);
    //
    String url = "http://localhost:" + rmiPort + "";
    ClientResource resource = new ClientResource(url);
    resource.setProtocol(new Protocol("http", "HTTP", "", 1099, "1.0"));
    Representation resp = resource.post(bar);
    //
    int rsize = (int) resp.getSize();
    byte[] r = new byte[rsize];
    resp.getStream().read(r);
    //
    resource.release();
    //
    ByteArrayRepresentation barr = new ByteArrayRepresentation(r);
    barr.setSize(rsize);
    barr.setMediaType(MediaType.APPLICATION_OCTET_STREAM);
    return barr;
  }
  
}
