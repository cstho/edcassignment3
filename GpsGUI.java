import nz.sodium.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Vector;

import swidgets.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GpsGUI {

    /**
     * Derived from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
    double lon2, double el1, double el2) {

    final int R = 6371; // Radius of the earth

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    double height = el1 - el2;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
    }

    public static class RolledWindowStamp {
        public long timeAdded;
        public double distanceTravelled;

        public RolledWindowStamp(long timeAdded, double distance){
            this.timeAdded= timeAdded;
            this.distanceTravelled = distance;
        }
    }



    public static void main(String[] args){



        Double[] latitudes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] longitudes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] altitudes = {-1385.0,-1385.0,-1385.0,-1385.0,-1385.0,-1385.0,-1385.0,-1385.0,-1385.0,-1385.0};
        Double[] distTravelled = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Boolean[] fitsCriteria = {true, true, true, true, true, true, true, true, true, true};

       
        Vector<RolledWindowStamp> rollingWindow0 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow1 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow2 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow3 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow4 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow5 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow6 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow7 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow8 = new Vector<RolledWindowStamp>();
        Vector<RolledWindowStamp> rollingWindow9 = new Vector<RolledWindowStamp>();
        


        /*JFrame tweaking */

        JFrame frame = new JFrame("GPS GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder( new TitledBorder("GPS Management") );

        //JToolBar tb = new JToolBar();
        final JPanel panel = new JPanel(new GridLayout(10,5,3,3));
        panel.setBorder(
            new TitledBorder("Filtered List") );
        gui.add(panel, BorderLayout.NORTH);

        final JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setBorder(
            new TitledBorder("Last Tracker Updated") );

        final JPanel filtration = new JPanel(new GridLayout(10,5,5,5));
        filtration.setBorder(
            new TitledBorder("All Trackers") );


        final JPanel panel3 = new JPanel(new GridLayout(5,5,5,5));
        panel3.setBorder(
            new TitledBorder("Longitude/Latitude Control Panel") );

        final JPanel center = new JPanel(new GridLayout(5,5,5,5));
        center.setBorder(
            new TitledBorder("Distances") );

        gui.add(panel2, BorderLayout.NORTH);
        gui.add(panel, BorderLayout.EAST);
        gui.add(filtration, BorderLayout.WEST);
        gui.add(panel3, BorderLayout.SOUTH);
        gui.add(center, BorderLayout.CENTER);

        

        
        frame.setContentPane(gui);

        frame.pack();

        frame.setSize(1200, 720); 
        frame.setVisible(true);



        /*--------------------------------- */
        /* Trackers (part 1) */
        STextField input0 = new STextField("");
        STextField buffer0 = new STextField("");
        Cell<String> text0 = input0.sUserChanges.hold(buffer0.getText()); //Hold buffer value so value doesnt disappear
        SLabel label0 = new SLabel(text0);
        panel.add(label0);
        
        STextField input1 = new STextField("");
        STextField buffer1 = new STextField("");
        Cell<String> text1 = input1.sUserChanges.hold(buffer1.getText()); //Hold buffer value so value doesnt disappear
        SLabel label1 = new SLabel(text1);
        panel.add(label1);
        

        STextField input2 = new STextField("");
        STextField buffer2 = new STextField("");
        Cell<String> text2 = input2.sUserChanges.hold(buffer2.getText()); //Hold buffer value so value doesnt disappear
        SLabel label2 = new SLabel(text2);
        panel.add(label2);

        STextField input3 = new STextField("");
        STextField buffer3 = new STextField("");
        Cell<String> text3 = input3.sUserChanges.hold(buffer3 .getText()); //Hold buffer value so value doesnt disappear
        SLabel label3 = new SLabel(text3);
        panel.add(label3);

        STextField input4 = new STextField("");
        STextField buffer4 = new STextField("");
        Cell<String> text4 = input4.sUserChanges.hold(buffer4.getText()); //Hold buffer value so value doesnt disappear
        SLabel label4 = new SLabel(text4);
        panel.add(label4);

        STextField input5 = new STextField("");
        STextField buffer5 = new STextField("");
        Cell<String> text5 = input5.sUserChanges.hold(buffer5.getText()); //Hold buffer value so value doesnt disappear
        SLabel label5 = new SLabel(text5);
        panel.add(label5);

        STextField input6 = new STextField("");
        STextField buffer6 = new STextField("");
        Cell<String> text6 = input6.sUserChanges.hold(buffer6.getText()); //Hold buffer value so value doesnt disappear
        SLabel label6 = new SLabel(text6);
        panel.add(label6);

        STextField input7 = new STextField("");
        STextField buffer7 = new STextField("");
        Cell<String> text7 = input7.sUserChanges.hold(buffer7.getText()); //Hold buffer value so value doesnt disappear
        SLabel label7 = new SLabel(text7);
        panel.add(label7);

        STextField input8 = new STextField("");
        STextField buffer8 = new STextField("");
        Cell<String> text8 = input8.sUserChanges.hold(buffer8.getText()); //Hold buffer value so value doesnt disappear
        SLabel label8 = new SLabel(text8);
        panel.add(label8);

        STextField input9 = new STextField("");
        STextField buffer9 = new STextField("");
        Cell<String> text9 = input9.sUserChanges.hold(buffer9.getText()); //Hold buffer value so value doesnt disappear
        SLabel label9 = new SLabel(text9);
        panel.add(label9);


        /* All Trackers Displayed */

        STextField finput0 = new STextField("");
        STextField fbuffer0 = new STextField("");
        Cell<String> ftext0 = finput0.sUserChanges.hold(fbuffer0.getText());

        STextField finput1 = new STextField("");
        STextField fbuffer1 = new STextField("");
        Cell<String> ftext1 = finput1.sUserChanges.hold(fbuffer1.getText());

        STextField finput2 = new STextField("");
        STextField fbuffer2 = new STextField("");
        Cell<String> ftext2 = finput2.sUserChanges.hold(fbuffer2.getText());

        STextField finput3 = new STextField("");
        STextField fbuffer3 = new STextField("");
        Cell<String> ftext3 = finput3.sUserChanges.hold(fbuffer3.getText());

        STextField finput4 = new STextField("");
        STextField fbuffer4 = new STextField("");
        Cell<String> ftext4 = finput4.sUserChanges.hold(fbuffer4.getText());

        STextField finput5 = new STextField("");
        STextField fbuffer5 = new STextField("");
        Cell<String> ftext5 = finput5.sUserChanges.hold(fbuffer5.getText());

        STextField finput6 = new STextField("");
        STextField fbuffer6 = new STextField("");
        Cell<String> ftext6 = finput6.sUserChanges.hold(fbuffer6.getText());

        STextField finput7 = new STextField("");
        STextField fbuffer7 = new STextField("");
        Cell<String> ftext7 = finput7.sUserChanges.hold(fbuffer7.getText());

        STextField finput8 = new STextField("");
        STextField fbuffer8 = new STextField("");
        Cell<String> ftext8 = finput8.sUserChanges.hold(fbuffer8.getText());

        STextField finput9 = new STextField("");
        STextField fbuffer9 = new STextField("");
        Cell<String> ftext9 = finput9.sUserChanges.hold(fbuffer9.getText());

        


        SLabel label0filt = new SLabel(ftext0);
        SLabel label1filt = new SLabel(ftext1);
        SLabel label2filt = new SLabel(ftext2);
        SLabel label3filt = new SLabel(ftext3);
        SLabel label4filt = new SLabel(ftext4);
        SLabel label5filt = new SLabel(ftext5);
        SLabel label6filt = new SLabel(ftext6);
        SLabel label7filt = new SLabel(ftext7);
        SLabel label8filt = new SLabel(ftext8);
        SLabel label9filt = new SLabel(ftext9);
        filtration.add(label0filt);
        filtration.add(label1filt);
        filtration.add(label2filt);
        filtration.add(label3filt);
        filtration.add(label4filt);
        filtration.add(label5filt);
        filtration.add(label6filt);
        filtration.add(label7filt);
        filtration.add(label8filt);
        filtration.add(label9filt);


        /*--------------------------------- */
        /*Part 4 */
        STextField inputDistance0 = new STextField("");
        STextField bufferDistance0 = new STextField("");
        Cell<String> textDistance0 = inputDistance0.sUserChanges.hold(bufferDistance0.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance0 = new SLabel(textDistance0);
        center.add(labelDistance0);

        STextField inputDistance1 = new STextField("");
        STextField bufferDistance1 = new STextField("");
        Cell<String> textDistance1 = inputDistance1.sUserChanges.hold(bufferDistance1.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance1 = new SLabel(textDistance1);
        center.add(labelDistance1);

        STextField inputDistance2 = new STextField("");
        STextField bufferDistance2 = new STextField("");
        Cell<String> textDistance2 = inputDistance2.sUserChanges.hold(bufferDistance2.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance2 = new SLabel(textDistance2);
        center.add(labelDistance2);
        
        STextField inputDistance3 = new STextField("");
        STextField bufferDistance3 = new STextField("");
        Cell<String> textDistance3 = inputDistance3.sUserChanges.hold(bufferDistance3.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance3 = new SLabel(textDistance3);
        center.add(labelDistance3);

        STextField inputDistance4 = new STextField("");
        STextField bufferDistance4 = new STextField("");
        Cell<String> textDistance4 = inputDistance4.sUserChanges.hold(bufferDistance4.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance4 = new SLabel(textDistance4);
        center.add(labelDistance4);

        STextField inputDistance5 = new STextField("");
        STextField bufferDistance5 = new STextField("");
        Cell<String> textDistance5 = inputDistance5.sUserChanges.hold(bufferDistance5.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance5 = new SLabel(textDistance5);
        center.add(labelDistance5);

        STextField inputDistance6 = new STextField("");
        STextField bufferDistance6 = new STextField("");
        Cell<String> textDistance6 = inputDistance6.sUserChanges.hold(bufferDistance6.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance6 = new SLabel(textDistance6);
        center.add(labelDistance6);

        STextField inputDistance7 = new STextField("");
        STextField bufferDistance7 = new STextField("");
        Cell<String> textDistance7 = inputDistance7.sUserChanges.hold(bufferDistance7.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance7 = new SLabel(textDistance7);
        center.add(labelDistance7);

        STextField inputDistance8 = new STextField("");
        STextField bufferDistance8 = new STextField("");
        Cell<String> textDistance8 = inputDistance8.sUserChanges.hold(bufferDistance8.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance8 = new SLabel(textDistance8);
        center.add(labelDistance8);

        STextField inputDistance9 = new STextField("");
        STextField bufferDistance9 = new STextField("");
        Cell<String> textDistance9 = inputDistance9.sUserChanges.hold(bufferDistance9.getText()); //Hold buffer value so value doesnt disappear
        SLabel labelDistance9 = new SLabel(textDistance9);
        center.add(labelDistance9);
        




        /*--------------------------------- */

        /* 4 Comma delimited string (Part 2)*/
        STextField inputTimestamp = new STextField("");
        STextField bufferTimestamp = new STextField("");
        Cell<String> textTimestamp = inputTimestamp.sUserChanges.hold(bufferTimestamp.getText());
        SLabel labelTimestamp = new SLabel(textTimestamp);
        labelTimestamp.setForeground(Color.RED);
        panel2.add(labelTimestamp);

        /*--------------------------------- */
        /* Timer handling - clear after 3 seconds of nothing */
        int delay = 3000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                bufferTimestamp.setText(inputTimestamp.getText()); //set buffer to normal timestamp
                inputTimestamp.selectAll();
                inputTimestamp.replaceSelection(""); //auto clear after 3 seconds 
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);


        /*--------------------------------- */
        /*Latitude and Longitude Handling (Part 3) */

        
        STextField inputLat = new STextField("90", 5);
        STextField inputLong = new STextField("180", 5);

        STextField inputLatMin = new STextField("-90", 5);
        STextField inputLongMin = new STextField("-180", 5);


        SButton button = new SButton("Set Defined Range");
        Cell<String> textLat = button.sClicked.snapshot(inputLat.text, (t1, t2) -> {
            if(inputLat.getText().isEmpty()){
                return "90";
            }
            if(Integer.parseInt(inputLat.getText()) < -90) {
                return "-90";
            }
            if(Integer.parseInt(inputLat.getText()) > 90) {
                return "90";
            }

            return t2;
        }).hold("90");
        Cell<String> textLong = button.sClicked.snapshot(inputLong.text, (t1, t2) -> {
            if(inputLong.getText().isEmpty()){
                return "180";
            }
            if(Integer.parseInt(inputLong.getText()) < -180) {
                return "-180";
            }
            if(Integer.parseInt(inputLong.getText()) > 180) {
                return "180";
            }

            return t2;
        }).hold("180");

        Cell<String> textLatMin = button.sClicked.snapshot(inputLatMin.text, (t1, t2) -> {
            if(inputLatMin.getText().isEmpty()){
                return "-90";
            }
            if(Integer.parseInt(inputLatMin.getText()) > Integer.parseInt(inputLat.getText())) {
                
                return inputLat.getText();
            }
            if(Integer.parseInt(inputLatMin.getText()) < -90) {
                return "-90";
            }
            if(Integer.parseInt(inputLatMin.getText()) > 90) {
                return "90";
            }

            return t2;
        }).hold("-90");
        Cell<String> textLongMin = button.sClicked.snapshot(inputLongMin.text, (t1, t2) -> {
            if(inputLongMin.getText().isEmpty()){
                return "-180";
            }
            if(Integer.parseInt(inputLongMin.getText()) > Integer.parseInt(inputLong.getText())) {
                return inputLong.getText();
            }
            if(Integer.parseInt(inputLongMin.getText()) < -180) {
                return "-180";
            }
            if(Integer.parseInt(inputLongMin.getText()) > 180) {
                return "180";
            }

            return t2;
        }).hold("-180");


        SLabel outputLat = new SLabel(textLat);
        SLabel outputLong = new SLabel(textLong);

        SLabel outputLatMin = new SLabel(textLatMin);
        SLabel outputLongMin = new SLabel(textLongMin);


        


        ActionListener buttonPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                double minLat;
                double minLong;
                double latitude;
                double longitude;
                boolean valid = false;

                if (inputLatMin.getText().isEmpty()){
                    minLat = -90;
                } else {
                    minLat = Double.parseDouble(inputLatMin.getText());
                }

                if (inputLongMin.getText().isEmpty()){
                    minLong = -180;
                } else {
                    minLong = Double.parseDouble(inputLongMin.getText());
                }


                if (inputLat.getText().isEmpty()){
                    latitude = 90;
                } else {
                    latitude = Double.parseDouble(inputLat.getText());
                }

                if (inputLong.getText().isEmpty()){
                    longitude = 180;
                } else {
                    longitude = Double.parseDouble(inputLong.getText());
                }



                if((latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180)) {
                    valid = true;
                } 

                if (valid) {
                    if (latitudes[0] > latitude || longitudes[0] > longitude || latitudes[0] < minLat || longitudes[0] < minLong) {
                        label0.setVisible(false);
                        fitsCriteria[0] = false;
                    } else {
                        label0.setVisible(true);
                        fitsCriteria[0] = true;
                    }
                    if (latitudes[1] > latitude || longitudes[1] > longitude || latitudes[1] < minLat || longitudes[1] < minLong) {
                        label1.setVisible(false);
                        fitsCriteria[1] = false;
                    } else {
                        label1.setVisible(true);
                        fitsCriteria[1] = true;
                    }
                    if (latitudes[2] > latitude || longitudes[2] > longitude || latitudes[2] < minLat || longitudes[2] < minLong) {
                        label2.setVisible(false);
                        fitsCriteria[2] = false;
                    } else {
                        label2.setVisible(true);
                        fitsCriteria[2] = true;
                    }
                    if (latitudes[3] > latitude || longitudes[3] > longitude || latitudes[3] < minLat || longitudes[3] < minLong) {
                        label3.setVisible(false);
                        fitsCriteria[3] = false;
                    } else {
                        label3.setVisible(true);
                        fitsCriteria[3] = true;
                    }
                    if (latitudes[4] > latitude || longitudes[4] > longitude || latitudes[4] < minLat || longitudes[4] < minLong) {
                        label4.setVisible(false);
                        fitsCriteria[4] = false;
                    } else {
                        label4.setVisible(true);
                        fitsCriteria[4] = true;
                    }
                    if (latitudes[5] > latitude || longitudes[5] > longitude || latitudes[5] < minLat || longitudes[5] < minLong) {
                        label5.setVisible(false);
                        fitsCriteria[5] = false;
                    } else {
                        label5.setVisible(true);
                        fitsCriteria[5] = true;
                    }
                    if (latitudes[6] > latitude || longitudes[6] > longitude || latitudes[6] < minLat || longitudes[6] < minLong) {
                        label6.setVisible(false);
                        fitsCriteria[6] = false;
                    } else {
                        label6.setVisible(true);
                        fitsCriteria[6] = true;
                    }
                    if (latitudes[7] > latitude || longitudes[7] > longitude || latitudes[7] < minLat || longitudes[7] < minLong) {
                        label7.setVisible(false);
                        fitsCriteria[7] = false;
                    } else {
                        label7.setVisible(true);
                        fitsCriteria[7] = true;
                    }
                    if (latitudes[8] > latitude || longitudes[8] > longitude || latitudes[8] < minLat || longitudes[8] < minLong) {
                        label8.setVisible(false);
                        fitsCriteria[8] = false;
                    } else {
                        label8.setVisible(true);
                        fitsCriteria[8] = true;
                    }
                    if (latitudes[9] > latitude || longitudes[9] > longitude || latitudes[9] < minLat || longitudes[9] < minLong) {
                        label9.setVisible(false);
                        fitsCriteria[9] = false;
                    } else {
                        label9.setVisible(true);
                        fitsCriteria[9] = true;
                    }
                   
                }
    

            }
        };

        button.addActionListener(buttonPerformer);
        Cell<String> fieldName1Min = new Cell<String>("Minimum Latitude");
        Cell<String> fieldName1 = new Cell<String>("Maximum Latitude");
        Cell<String> fieldName2Min = new Cell<String>("Minimum Longitude");
        Cell<String> fieldName2 = new Cell<String>("Maximum Longitude");
        SLabel maxLat = new SLabel(fieldName1);
        SLabel maxLong = new SLabel(fieldName2);
        SLabel minLat = new SLabel(fieldName1Min);
        SLabel minLong = new SLabel(fieldName2Min);
        outputLat.setForeground(Color.BLUE);
        outputLong.setForeground(Color.BLUE);
        outputLatMin.setForeground(Color.BLUE);
        outputLongMin.setForeground(Color.BLUE);

        

        panel3.add(minLat);
        panel3.add(inputLatMin);
        panel3.add(outputLatMin);
        panel3.add(maxLat);
        panel3.add(inputLat);
        
        panel3.add(outputLat);

        panel3.add(minLong);
        panel3.add(inputLongMin);
        panel3.add(outputLongMin);
        panel3.add(maxLong);
        panel3.add(inputLong);
        
        panel3.add(outputLong);
        panel3.add(button);

        /*--------------------------------- */



        // Initialise the GPS Service
        GpsService serv = new GpsService();
        // Retrieve Event Streams
        Stream<GpsEvent>[] streams = serv.getEventStreams();        

        // Attach a handler method to each stream
        for(Stream<GpsEvent> s : streams){
            
            s.listen((GpsEvent ev) -> {
                System.out.println(ev);
                if (ev.name.equals("Tracker0")) {
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    //Replace all tracker component
                    fbuffer0.setText(finput0.getText());
                    finput0.selectAll();
                    finput0.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if (fitsCriteria[0] == true) {
                        
                        if(altitudes[0] != -1385.0) {
                            double updatedDistance = distance(latitudes[0], ev.latitude, longitudes[0], ev.longitude, altitudes[0] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[0] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow0.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow0.size() ; i++) {
                            if (rollingWindow0.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow0.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[0] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance0.setText(inputDistance0.getText());
                        inputDistance0.selectAll();
                        inputDistance0.replaceSelection("Distance travelled by Tracker0: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[0] = ev.latitude;
                        longitudes[0] = ev.longitude;
                        altitudes[0] = ev.altitude;

                        //Replace tracker component
                        buffer0.setText(input0.getText());
                        input0.selectAll();
                        input0.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);


                    }

                }
                if (ev.name.equals("Tracker1" )) {
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                    //Replace tracker component
                    fbuffer1.setText(finput1.getText());
                    finput1.selectAll();
                    finput1.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    if (fitsCriteria[1] == true) {
                        if(altitudes[1] != -1385.0) {
                            double updatedDistance = distance(latitudes[1], ev.latitude, longitudes[1], ev.longitude, altitudes[1] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[1] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow1.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow1.size() ; i++) {
                            if (rollingWindow1.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow1.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[1] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance1.setText(inputDistance1.getText());
                        inputDistance1.selectAll();
                        inputDistance1.replaceSelection("Distance travelled by Tracker1: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[1] = ev.latitude;
                        longitudes[1] = ev.longitude;
                        altitudes[1] = ev.altitude;

                        //Replace tracker component
                        buffer1.setText(input1.getText());
                        input1.selectAll();
                        input1.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                }

                
                }

                if (ev.name.equals("Tracker2") ) {
                    //Replace tracker component
                    fbuffer2.setText(finput2.getText());
                    finput2.selectAll();
                    finput2.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if(fitsCriteria[2] == true){
                        if(altitudes[2] != -1385.0) {
                            double updatedDistance = distance(latitudes[2], ev.latitude, longitudes[2], ev.longitude, altitudes[2] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[2] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow2.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow2.size() ; i++) {
                            if (rollingWindow2.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow2.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[2] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance2.setText(inputDistance2.getText());
                        inputDistance2.selectAll();
                        inputDistance2.replaceSelection("Distance travelled by Tracker2: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[2] = ev.latitude;
                        longitudes[2] = ev.longitude;
                        altitudes[2] = ev.altitude;

                        //Replace tracker component
                        buffer2.setText(input2.getText());
                        input2.selectAll();
                        input2.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                }

                }

                if (ev.name.equals("Tracker3")) {
                    //Replace tracker component
                    fbuffer3.setText(finput3.getText());
                    finput3.selectAll();
                    finput3.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if(fitsCriteria[3] == true){
                        if(altitudes[3] != -1385.0) {
                            double updatedDistance = distance(latitudes[3], ev.latitude, longitudes[3], ev.longitude, altitudes[3] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[3] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow3.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow3.size() ; i++) {
                            if (rollingWindow3.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow3.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[3] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance3.setText(inputDistance3.getText());
                        inputDistance3.selectAll();
                        inputDistance3.replaceSelection("Distance travelled by Tracker3: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[3] = ev.latitude;
                        longitudes[3] = ev.longitude;
                        altitudes[3] = ev.altitude;

                        //Replace tracker component
                        buffer3.setText(input3.getText());
                        input3.selectAll();
                        input3.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    }

                
                }

                if (ev.name.equals("Tracker4")) {
                    //Replace tracker component
                    fbuffer4.setText(finput4.getText());
                    finput4.selectAll();
                    finput4.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if (fitsCriteria[4] == true){
                        if(altitudes[4] != -1385.0) {
                            double updatedDistance = distance(latitudes[4], ev.latitude, longitudes[4], ev.longitude, altitudes[4] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[4] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow4.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow4.size() ; i++) {
                            if (rollingWindow4.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow4.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[4] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance4.setText(inputDistance4.getText());
                        inputDistance4.selectAll();
                        inputDistance4.replaceSelection("Distance travelled by Tracker4: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[4] = ev.latitude;
                        longitudes[4] = ev.longitude;
                        altitudes[4] = ev.altitude;

                        //Replace tracker component
                        buffer4.setText(input4.getText());
                        input4.selectAll();
                        input4.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    }

                
                }

                if (ev.name.equals("Tracker5")) {
                    //Replace tracker component
                    fbuffer5.setText(finput5.getText());
                    finput5.selectAll();
                    finput5.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if(fitsCriteria[5] == true) {
                        if(altitudes[5] != -1385.0) {
                            double updatedDistance = distance(latitudes[5], ev.latitude, longitudes[5], ev.longitude, altitudes[5] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[5] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow5.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow5.size() ; i++) {
                            if (rollingWindow5.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow5.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[5] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance5.setText(inputDistance5.getText());
                        inputDistance5.selectAll();
                        inputDistance5.replaceSelection("Distance travelled by Tracker5: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[5] = ev.latitude;
                        longitudes[5] = ev.longitude;
                        altitudes[5] = ev.altitude;

                        //Replace tracker component
                        buffer5.setText(input5.getText());
                        input5.selectAll();
                        input5.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    }

                
                }

                if (ev.name.equals("Tracker6") ) {
                    //Replace tracker component
                    fbuffer6.setText(finput6.getText());
                    finput6.selectAll();
                    finput6.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if(fitsCriteria[6] == true)
                    {
                        if(altitudes[6] != -1385.0) {
                            double updatedDistance = distance(latitudes[6], ev.latitude, longitudes[6], ev.longitude, altitudes[6] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[6] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow6.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow6.size() ; i++) {
                            if (rollingWindow6.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow6.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[6] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance6.setText(inputDistance6.getText());
                        inputDistance6.selectAll();
                        inputDistance6.replaceSelection("Distance travelled by Tracker6: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[6] = ev.latitude;
                        longitudes[6] = ev.longitude;
                        altitudes[6] = ev.altitude;

                        //Replace tracker component
                        buffer6.setText(input6.getText());
                        input6.selectAll();
                        input6.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    }
                
                }

                if (ev.name.equals("Tracker7") ) {
                    //Replace tracker component
                    fbuffer7.setText(finput7.getText());
                    finput7.selectAll();
                    finput7.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if(fitsCriteria[7] == true) {

                    
                        if(altitudes[7] != -1385.0) {
                            double updatedDistance = distance(latitudes[7], ev.latitude, longitudes[7], ev.longitude, altitudes[7] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[7] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow7.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow7.size() ; i++) {
                            if (rollingWindow7.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow7.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[7] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance7.setText(inputDistance7.getText());
                        inputDistance7.selectAll();
                        inputDistance7.replaceSelection("Distance travelled by Tracker7: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[7] = ev.latitude;
                        longitudes[7] = ev.longitude;
                        altitudes[7] = ev.altitude;

                        //Replace tracker component
                        buffer7.setText(input7.getText());
                        input7.selectAll();
                        input7.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    }
                }

                if (ev.name.equals("Tracker8")) {
                    //Replace tracker component
                    fbuffer8.setText(finput8.getText());
                    finput8.selectAll();
                    finput8.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                    if (fitsCriteria[8] == true) {

                    
                    if(altitudes[8] != -1385.0) {
                        double updatedDistance = distance(latitudes[8], ev.latitude, longitudes[8], ev.longitude, altitudes[8] * 0.3048, ev.altitude * 0.3048);
                        distTravelled[8] += updatedDistance;

                        RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                        
                        rollingWindow8.add(rolled);
                    }

                    double tempDistTravelled = 0.0;
                    for (int i = 0; i < rollingWindow8.size() ; i++) {
                        if (rollingWindow8.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                        {
                            tempDistTravelled += rollingWindow8.get(i).distanceTravelled;
                        }
                    }
                    distTravelled[8] = tempDistTravelled;
                    int intDistance = (int)tempDistTravelled;

  
                    bufferDistance8.setText(inputDistance8.getText());
                    inputDistance8.selectAll();
                    inputDistance8.replaceSelection("Distance travelled by Tracker8: " + String.valueOf(intDistance) + "m");
                    

                    
                    //Add data to a map for processing
                    latitudes[8] = ev.latitude;
                    longitudes[8] = ev.longitude;
                    altitudes[8] = ev.altitude;

                    //Replace tracker component
                    buffer8.setText(input8.getText());
                    input8.selectAll();
                    input8.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                }
                }

                if (ev.name.equals("Tracker9")) {

                    //Replace tracker component
                    fbuffer9.setText(finput9.getText());
                    finput9.selectAll();
                    finput9.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                    if(fitsCriteria[9] == true) {

                        
                        if(altitudes[9] != -1385.0) {
                            double updatedDistance = distance(latitudes[9], ev.latitude, longitudes[9], ev.longitude, altitudes[9] * 0.3048, ev.altitude * 0.3048);
                            distTravelled[9] += updatedDistance;

                            RolledWindowStamp rolled = new RolledWindowStamp(ZonedDateTime.now().toInstant().toEpochMilli(), updatedDistance);
                            
                            rollingWindow9.add(rolled);
                        }

                        double tempDistTravelled = 0.0;
                        for (int i = 0; i < rollingWindow9.size() ; i++) {
                            if (rollingWindow9.get(i).timeAdded > (ZonedDateTime.now().toInstant().toEpochMilli() - 300000) )
                            {
                                tempDistTravelled += rollingWindow9.get(i).distanceTravelled;
                            }
                        }
                        distTravelled[9] = tempDistTravelled;
                        int intDistance = (int)tempDistTravelled;

    
                        bufferDistance9.setText(inputDistance9.getText());
                        inputDistance9.selectAll();
                        inputDistance9.replaceSelection("Distance travelled by Tracker9: " + String.valueOf(intDistance) + "m");
                        

                        
                        //Add data to a map for processing
                        latitudes[9] = ev.latitude;
                        longitudes[9] = ev.longitude;
                        altitudes[9] = ev.altitude;

                        //Replace tracker component
                        buffer9.setText(input9.getText());
                        input9.selectAll();
                        input9.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
  
                    }
                }
            });



        } 


    }

} 
