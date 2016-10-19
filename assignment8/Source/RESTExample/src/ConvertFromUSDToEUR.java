import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/convertUSDToEUR")
public class ConvertFromUSDToEUR {
	public ConvertFromUSDToEUR(){	
	}
	
	public static void main(String[] args){
	}

public double inr;
	@GET
	@Path("/usdtoinrservice1")		
	@Produces("application/xml")
	public double convertUSDToINR(double usd) {
		UsdToInr n=new UsdToInr();
		inr = n.convertUSDToINRfromInput(usd);
		return inr;
	}

	@GET
	@Path("/INRtoEURservice1")		
	@Produces("application/xml")
	public double convertINRToEUR(double inr) {
		InrToEur n=new InrToEur();
		double eur = n.convertInrtoEurfromInput(inr);
		return eur;
	}
	
	@Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertUSDtoEURfromInput(@PathParam("f") double usd) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("USD", usd);
		jsonObject.put("INR", convertUSDToINR(usd));
		jsonObject.put("EURO", convertINRToEUR(convertUSDToINR(usd)));
		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	
}
