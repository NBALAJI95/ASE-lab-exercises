/**
 * @author ry6d3
 *
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/usdtoinrservice")
public class UsdToInr {
	 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public Double convertUSDToINRfromInput(@PathParam("c") Double c) {
		Double rupee;
		Double dollar = c;
		rupee = 66.64*dollar;
 
		return rupee;
	}
}