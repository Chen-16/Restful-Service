/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package math;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * REST Web Service
 *
 * @author barne
 */
@Path("math")
public class MathsOperations {

    @Context
    private UriInfo context;
    private static final Logger logger = Logger.getLogger(MathsOperations.class.getName());
    private static final String OSRM_BASE_URL ="http//router.project-osrm.org/route/v1/driving/";

    /**
     * Creates a new instance of MathsOperations
     */
    public MathsOperations() {
    }

    /**
     * Retrieves representation of an instance of math.MathsOperations
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("x") int x,@QueryParam("y") int y) {
        //TODO return proper representation object
        String jsonString = "( \"x\": " + x + ", \"y\": " + y + ",\"result\": " + (x + y) + " )";
        return jsonString;
    }

    /**
     * PUT method for updating or creating an instance of MathsOperations
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    // the get methods below will be what checks the proximity between the two locations using the oSRM api
    @GET
    @Path("proximity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProximity(@QueryParam("userLon") String userLon,@QueryParam("userLat") String userLat,@QueryParam("itemLon") String itemLon,@QueryParam("itemLat") String itemLat){
        try{
            String coordinates = userLon + "," + userLat +  "," + "<--   -->" + itemLon + "," + itemLat;
            URI uri = new URIBuilder(OSRM_BASE_URL + coordinates).addParameter("overview", "false").build();
            logger.log (Level.INFO, "gonna call the OSRM api now : {0}", uri.toString());
            
            //create the HTTP client the same as in lab 2 and then send the request and get response
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            //now check if it was successful by getting the statuscode (if its 200 then its OK, but if not then something has gone wrong
            if (response.statusCode() != 200){
                logger.log(Level.SEVERE, "OSRM API returned the code: {0}", response.statusCode());
                ProximityResult errorResult = new ProximityResult("Error", "the api is unavailable");
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(errorResult).build();
            }
            
            logger.log(Level.INFO,"OSRM api response was received");
            //now its time to deserialise the JSON response using jackson from Lab 3
            ObjectMapper mapper = new ObjectMapper();
            OSRMResponse osrmResponse = mapper.readValue(response.body(),OSRMResponse.class);
            //gonna make sure that the route we were given by OSRM is valid 
            //im going to make sure that the code and routes given to me are valid by checking if the routes are empty and if the code is OK
            if (!"OK".equals(osrmResponse.getCode()) || osrmResponse.getRoutes() == null || osrmResponse.getRoutes().isEmpty()){
                logger.log(Level.SEVERE, "OSRM returned an invalid response");
                ProximityResult errorResult = new ProximityResult("error, no route was found between the two locations");
                return Response.status(Response.Status.NOT_FOUND).entity(errorResult).build();
            }
            
            //get the first/best route
            Route route = osrmResponse.getRoutes.get(0);
            //create a resulting object with thew extracted data
            ProximityResult result = new ProximityResult(route.getDistance(), route.getDuration());
            logger.log(Level.INFO, "Proximity calculated: {0}km, {1}min", new Object[]{result.getDistanceInKilometers(), result.getDurationInMinutes()});
            //return the json response
            return response.ok(result).build();
        }catch(Exception e){
            e.printStackTrace();
        }  
    }    
}
