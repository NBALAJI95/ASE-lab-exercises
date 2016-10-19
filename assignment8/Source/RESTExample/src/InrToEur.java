/**
 * @author ry6d3
 *
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONException;
import org.json.JSONObject;
 
@Path("/INRtoEURservice")
public class InrToEur {
 	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public double convertInrtoEurfromInput(@PathParam("f") double rupee) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		double euro;
		euro = 0.0136760 * rupee; 
		jsonObject.put("INR Value", rupee); 
		jsonObject.put("EUR Value", euro);
  
		return euro;
	  }
}