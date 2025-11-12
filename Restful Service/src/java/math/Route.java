/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package math;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *
 * @author barne
 */
public class Route {
    private double distance;
        private String geometry;
    private double duration;
    
    
    //an object taken from the API will have a duration, distance and geometry info
    //so these are the getters and setters for such info
    public Route(){
    }
    public Route(double distance, double duration, String geometry){
        this.distance = distance;
        this.duration = duration;
        this.geometry = geometry; //going to use polyline (as shown on the OSRM website)
    }
    public double getDistance(){
        return distance;
    }
    public void setDistance (double distance){
        this.distance = distance;
    }
    public String getGeometry(){
        return geometry;
    }
    public void setGeometry(String geometry){
        this.geometry = geometry;
    }
    public double getDuration(){
        return duration;
    }
    public void setDuration(double duration){
        this.duration = duration;
    }
    @Override
    public String toString(){
        return "Route{" + "distance= " + distance + ", duration=" + duration + ", geometry =" + geometry + '\'' + '}';
    }
}
