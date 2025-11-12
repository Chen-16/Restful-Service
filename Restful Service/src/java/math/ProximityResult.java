/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package math;

/**
 *
 * @author barne
 */

//this class will be the resultting info that will be returned to the client in JSON format
//how close together places are 
public class ProximityResult {
    private double distanceInMeters;
    private double durationInSeconds;
    private double distanceInKilometers;
    private double durationInMinutes;
    private String status;
    private String message;
    public ProximityResult(){
        
    }
    //error response stuff
    public ProximityResult(double DistanceInMeters, double durationInSeconds){
        this.distanceInMeters = distanceInMeters;
        this.durationInSeconds = durationInSeconds;
        this.durationInMinutes = durationInSeconds / 60.0;
        this.distanceInKilometers = distanceInMeters / 1000.0;
        this.status = "suc cessful";
        this.message = "";
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    
    public double getDurationInSeconds(){
        return durationInSeconds;
    }
    public void setDurationInSeconds(double durationInSeconds){
        this.durationInSeconds = durationInSeconds;
    }
    public double getDistanceInMeters(){
        return distanceInMeters;
    }
    public void setDistanceInMeters(double distanceInMeters){
        this.distanceInMeters = distanceInMeters;
    }
    public double getDistanceInKilometers(){
        return distanceInKilometers;
    }
    public void setDistanceInKilometers(double distanceInKilometers){
        this.distanceInKilometers = distanceInKilometers;
    }
    public double getDurationInMinutes(){
        return durationInMinutes;
    }
    public void setDurationInMinutes(double durationInMinutes){
        this.durationInMinutes = durationInMinutes;
    }
}
    
