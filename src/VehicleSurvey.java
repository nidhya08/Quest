import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;


/**
 * Created by Nidhya on 08/08/2015.
 */
public class VehicleSurvey {
    public static int quaterTimeInterval = 15;
    boolean isSameVehicle = false;
    LinkedHashMap<String, String> northVehicleTimeMap = new LinkedHashMap<>();
    LinkedHashMap<String, String> southVehicleTimeMap = new LinkedHashMap<String,String>();
    LinkedHashMap<String, Integer> northVehicleCountMap = new LinkedHashMap<String,Integer>();
    LinkedHashMap<String, Integer> southVehicleCountMap = new LinkedHashMap<String,Integer>();

    public String calculateTime(String inputTime) {
        String timeStr = inputTime.substring(1);
        String formattedHour="";
        String formattedMin="";
        String formattedSec="";
        String formattedMilliSec="";

        long millisec = Long.parseLong(timeStr);
        long seconds = millisec / 1000;
        millisec = millisec%1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hour = minutes / 60;
        minutes = minutes % 60;

        formattedHour = String.format("%02d",hour);
        formattedMin = String.format("%02d",minutes);
        formattedSec = String.format("%02d",seconds);
        formattedMilliSec = String.format("%03d",millisec);

        return formattedHour+":"+formattedMin+":"+formattedSec+":"+formattedMilliSec;
    }

    public void readFileInput(String path) {
        File inputFile = new File(path);
        LineIterator lineIterator;
        String calculatedTime="";
        boolean flag = true;
        try {
            lineIterator = FileUtils.lineIterator(inputFile, "UTF-8");
            while (lineIterator.hasNext()) {
                String timeLine = lineIterator.nextLine();

                if(flag==true) {
                    calculatedTime = calculateTime(timeLine);
                    flag=!flag;
                    continue;
                } else {
                   setDirectionMap(timeLine,calculatedTime);
                    flag=!flag;
                }

            }

            LineIterator.closeQuietly(lineIterator);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputFile != null) {
                inputFile.exists();
            }
        }
    }
    public LinkedHashMap<String,Integer> countVehicle(int timeInterval,LinkedHashMap<String,String> timeMap){

        String startingHour = "";
        int vehicleCount =0;
        boolean noteStartingTime=true;
        boolean isNextTimeSlot;
        LinkedHashMap<String, Integer> vehicleCountMap = new LinkedHashMap<>();

        for (Map.Entry<String,String> entry : timeMap.entrySet()){
            if(noteStartingTime) {
                startingHour = entry.getKey();
                noteStartingTime=false;
            }
            String timeGap = "";
            try {
                timeGap = CustomizedTime.addTime(startingHour,timeInterval);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(CustomizedTime.compareTime(entry.getKey(),timeGap)){
                isNextTimeSlot = true;
                startingHour=entry.getKey();
            } else {
                isNextTimeSlot = false;
            }
            if(!isNextTimeSlot){
                vehicleCount++;
            } else{
                vehicleCountMap.put(CustomizedTime.subtractTime(startingHour, timeInterval), vehicleCount);
                vehicleCount =1;
            }

        }
        vehicleCountMap.put(startingHour,vehicleCount);
        System.out.println("final map: "+vehicleCountMap.toString());
        return vehicleCountMap;
    }
    public void setDirectionMap(String vehicleDirection,String time){
        if (vehicleDirection.charAt(0) == 'A')
            northVehicleTimeMap.put(time, "N");
        else if (vehicleDirection.charAt(0) == 'B')
            southVehicleTimeMap.put(time, "S");
    }
    public void vehicleCountInBothDir(){
        northVehicleCountMap = countVehicle(15,northVehicleTimeMap);
        southVehicleCountMap = countVehicle(15,southVehicleTimeMap);

        System.out.println("Vehicles count in north direction per 15 minutes: : "+northVehicleCountMap.toString());
        vehicleCountAtDayAndNight(northVehicleCountMap);
        System.out.println("Vehicles count in south direction per 15 minutes: : " + southVehicleCountMap.toString());
        vehicleCountAtDayAndNight(southVehicleCountMap);
    }
    public void vehicleCountAtDayAndNight(LinkedHashMap<String,Integer> vehicleCountPerDir){
        int vehicleCountInMorn = 0;
        int vehicleCountInEvng = 0;
        for (Map.Entry<String,Integer> entry : vehicleCountPerDir.entrySet()){
            String time[] = entry.getKey().split(":");
            if(Integer.parseInt(time[0]) >=0 && Integer.parseInt(time[0]) <12){
                vehicleCountInMorn += entry.getValue();
            } else
                vehicleCountInEvng+= entry.getValue();
        }
        System.out.println("mrng vs evng " + vehicleCountInMorn + " : " + vehicleCountInEvng);
    }
    public void vehicleCountPerDay(LinkedHashMap<String,Integer> vehicleCountPerDir){
        boolean isNewDay = true;
        for(Map.Entry<String,Integer> entry : vehicleCountPerDir.entrySet()){
            String time[] = entry.getKey().split(":");

        }
    }
}
