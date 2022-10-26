import nz.sodium.*;
import javax.swing.*;
import java.awt.FlowLayout;
import swidgets.*;

/** 
 * An example of how to use the GpsService class
 */
public class GpsGUI {

    public static void main(String[] args){

        JFrame frame = new JFrame("GPS GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Initialise the GPS Service
        GpsService serv = new GpsService();
        // Retrieve Event Streams
        Stream<GpsEvent>[] streams = serv.getEventStreams();    

        // Attach a handler method to each stream
        for(Stream<GpsEvent> s : streams){
            s.listen((GpsEvent ev) -> {
                System.out.println(ev);
            });
            Cell<String> trackerNumber = s.map((GpsEvent ev) -> {
                String []splits = ev.toString().split(" | ");
                String name = (splits[0]);
                return name;
                
            }).hold("");

            Cell<String> latitude = s.map((GpsEvent ev) -> {
                String []splits = ev.toString().split(":");
                String lat = (splits[1].replace("lon","").trim());
                return lat;
                
            }).hold("");

            Cell<String> longitude = s.map((GpsEvent ev) -> {
                String []splits = ev.toString().split(":");
                String lon = (splits[2].replace("alt","").trim());
                return lon;
                
            }).hold("");

            Cell<String> tracker0 = s.map((GpsEvent ev) -> {
                String output = ev.name + " has latitude " + ev.latitude + " and longitude " + ev.longitude;
                return output;
                
            }).hold("");
            //SLabel label0 = new SLabel(tracker0);
            //frame.add(label0);

            SLabel label1 = new SLabel(trackerNumber);
            frame.add(label1);

            SLabel label2 = new SLabel(latitude);
            frame.add(label2);

            SLabel label3 = new SLabel(longitude);
            frame.add(label3);
        } 



        frame.setSize(400, 560);
        frame.setVisible(true);
    }

} 
