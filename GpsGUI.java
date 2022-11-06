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
        /*JFrame tweaking */

        JFrame frame = new JFrame("GPS GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder( new TitledBorder("GPS Management") );

        //JToolBar tb = new JToolBar();
        final JPanel panel = new JPanel(new GridLayout(5,5,3,3));
        panel.setBorder(
            new TitledBorder("All Trackers") );
        gui.add(panel, BorderLayout.NORTH);

        final JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setBorder(
            new TitledBorder("Last Tracker Updated") );
        gui.add(panel, BorderLayout.NORTH);
        gui.add(panel2);

        
        frame.setContentPane(gui);

        frame.pack();

        frame.setSize(800, 300); 
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


        // Initialise the GPS Service
        GpsService serv = new GpsService();
        // Retrieve Event Streams
        Stream<GpsEvent>[] streams = serv.getEventStreams();        

        // Attach a handler method to each stream
        for(Stream<GpsEvent> s : streams){
            
            s.listen((GpsEvent ev) -> {
                System.out.println(ev);
                if (ev.name.equals("Tracker0")) {

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
