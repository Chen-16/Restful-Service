/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package math;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author barne
 */

//this is the complete OSRM API response and will follow the structure from OSRM route API v1
public class OSRMResponse {
    private String code; //"ok" if its successful
    private List <Route> routes; //array of the route objects gained gained from the API
    
    public OSRMResponse(){
        //empty constructor
    }
    public OSRMResponse(String code, List<Route> routes){
        this.code = code;
        this.routes = routes;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public List<Route> getRoutes(){
        return routes;
    }
    public void setRoutes(List<Route> routes){
        this.routes = routes;
    }
    @Override
    public String toString(){
        return "OSRMResponse{" + "Code= '" + code + '\'' + ", routes= " + routes + '}';
    }
    
}
