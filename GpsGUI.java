import nz.sodium.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;  
import java.util.Date;

import swidgets.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GpsGUI {

    public static void main(String[] args){
        Double[] latitudes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0, 0.0};
        Double[] longitudes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0, 0.0};


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

        gui.add(panel2, BorderLayout.NORTH);
        gui.add(panel, BorderLayout.EAST);
        gui.add(filtration, BorderLayout.WEST);
        gui.add(panel3, BorderLayout.SOUTH);

        
        frame.setContentPane(gui);

        frame.pack();

        frame.setSize(800, 720); 
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


        SLabel label0filt = new SLabel(text0);
        SLabel label1filt = new SLabel(text1);
        SLabel label2filt = new SLabel(text2);
        SLabel label3filt = new SLabel(text3);
        SLabel label4filt = new SLabel(text4);
        SLabel label5filt = new SLabel(text5);
        SLabel label6filt = new SLabel(text6);
        SLabel label7filt = new SLabel(text7);
        SLabel label8filt = new SLabel(text8);
        SLabel label9filt = new SLabel(text9);


  
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
                    } else {
                        label0.setVisible(true);
                    }
                    if (latitudes[1] > latitude || longitudes[1] > longitude || latitudes[1] < minLat || longitudes[1] < minLong) {
                        label1.setVisible(false);
                    } else {
                        label1.setVisible(true);
                    }
                    if (latitudes[2] > latitude || longitudes[2] > longitude || latitudes[2] < minLat || longitudes[2] < minLong) {
                        label2.setVisible(false);
                    } else {
                        label2.setVisible(true);
                    }
                    if (latitudes[3] > latitude || longitudes[3] > longitude || latitudes[3] < minLat || longitudes[3] < minLong) {
                        label3.setVisible(false);
                    } else {
                        label3.setVisible(true);
                    }
                    if (latitudes[4] > latitude || longitudes[4] > longitude || latitudes[4] < minLat || longitudes[4] < minLong) {
                        label4.setVisible(false);
                    } else {
                        label4.setVisible(true);
                    }
                    if (latitudes[5] > latitude || longitudes[5] > longitude || latitudes[5] < minLat || longitudes[5] < minLong) {
                        label5.setVisible(false);
                    } else {
                        label5.setVisible(true);
                    }
                    if (latitudes[6] > latitude || longitudes[6] > longitude || latitudes[6] < minLat || longitudes[6] < minLong) {
                        label6.setVisible(false);
                    } else {
                        label6.setVisible(true);
                    }
                    if (latitudes[7] > latitude || longitudes[7] > longitude || latitudes[7] < minLat || longitudes[7] < minLong) {
                        label7.setVisible(false);
                    } else {
                        label7.setVisible(true);
                    }
                    if (latitudes[8] > latitude || longitudes[8] > longitude || latitudes[8] < minLat || longitudes[8] < minLong) {
                        label8.setVisible(false);
                    } else {
                        label8.setVisible(true);
                    }
                    if (latitudes[9] > latitude || longitudes[9] > longitude || latitudes[9] < minLat || longitudes[9] < minLong) {
                        label9.setVisible(false);
                    } else {
                        label9.setVisible(true);
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
                    //Add data to a map for processing
                    latitudes[0] = ev.latitude;
                    longitudes[0] = ev.longitude;

                    //Replace tracker component
                    buffer0.setText(input0.getText());
                    input0.selectAll();
                    input0.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);

                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                }

                if (ev.name.equals("Tracker1")) {
                    //Add data to a map for processing
                    latitudes[1] = ev.latitude;
                    longitudes[1] = ev.longitude;
                    buffer1.setText(input1.getText());
                    input1.selectAll();
                    input1.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);


                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                
                }

                if (ev.name.equals("Tracker2")) {
                    //Add data to a map for processing
                    latitudes[2] = ev.latitude;
                    longitudes[2] = ev.longitude;

                    buffer2.setText(input2.getText());
                    input2.selectAll();
                    input2.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                }

                if (ev.name.equals("Tracker3")) {
                    //Add data to a map for processing
                    latitudes[3] = ev.latitude;
                    longitudes[3] = ev.longitude;
                    buffer3.setText(input3.getText());
                    input3.selectAll();
                    input3.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                
                }

                if (ev.name.equals("Tracker4")) {
                    //Add data to a map for processing
                    latitudes[4] = ev.latitude;
                    longitudes[4] = ev.longitude;
                    buffer4.setText(input4.getText());
                    input4.selectAll();
                    input4.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                
                }

                if (ev.name.equals("Tracker5")) {
                    //Add data to a map for processing
                    latitudes[5] = ev.latitude;
                    longitudes[5] = ev.longitude;
                    buffer5.setText(input5.getText());
                    input5.selectAll();
                    input5.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();

                
                }

                if (ev.name.equals("Tracker6")) {
                    //Add data to a map for processing
                    latitudes[6] = ev.latitude;
                    longitudes[6] = ev.longitude;
                    buffer6.setText(input6.getText());
                    input6.selectAll();
                    input6.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                
                }

                if (ev.name.equals("Tracker7")) {
                    //Add data to a map for processing
                    latitudes[7] = ev.latitude;
                    longitudes[7] = ev.longitude;
                    buffer7.setText(input7.getText());
                    input7.selectAll();
                    input7.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                }

                if (ev.name.equals("Tracker8")) {
                    //Add data to a map for processing
                    latitudes[8] = ev.latitude;
                    longitudes[8] = ev.longitude;
                    buffer8.setText(input8.getText());
                    input8.selectAll();
                    input8.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                }

                if (ev.name.equals("Tracker9")) {
                    //Add data to a map for processing
                    latitudes[9] = ev.latitude;
                    longitudes[9] = ev.longitude;
                    buffer9.setText(input9.getText());
                    input9.selectAll();
                    input9.replaceSelection(ev.name + " Latitude: " + ev.latitude +  " Longitude: " + ev. longitude);
                    //Replace date component
                    bufferTimestamp.setText(inputTimestamp.getText());
                    inputTimestamp.selectAll();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Date date = new Date();

                    inputTimestamp.replaceSelection(ev.name + ", " + ev.latitude + ", " + ev.longitude + ", " + formatter.format(date).toString() );
                    timer.restart();
                }
            });



        } 


    }

} 
