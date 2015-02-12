package org.me.gmtimageapp;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * @author jsimmonds 2015
 * v0.0.6
 */
public class GMTtime extends javax.swing.JFrame {
    
    private static String gmtTime;
    private static String clockTime;
    private static long millis;    
    private static boolean runClock = true;
    private static String[] timeZone = { "GMT", "EST", "CET", "AET", "JST" }; // note that the acronym used for Sydney is AET, but Java displays EST, with the correct Sydney offset   
    private static int x; // the index of the timeZone array element ( the array values set the time offset from GMT )
    private static SimpleDateFormat sdf = new SimpleDateFormat( "H:mm:ss z" ); // just use the time, no date       
        
    /**
     * Creates new form GMTtime
     */
    public GMTtime() {
        
        initComponents(); // calls the GUI palette generated code method
        setIcon(); // sets the icon on the main window (frame1)       
        sdf.setTimeZone( TimeZone.getTimeZone( timeZone[ 0 ] ) ); // initialize first timezone displayed
        
        // set the GUI to appear in the middle of the screen on launch
        this.setLocationRelativeTo( null );                 
    } // <editor fold> end constructor GMTtime

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String args[]) {         
        
        // set the background color for the panel
        UIManager UI = new UIManager();         
        UIManager.getLookAndFeelDefaults().put(
            "nimbusBase",new ColorUIResource(222,230,252)); 
        UIManager.getLookAndFeelDefaults().put(
            "Panel.background",new ColorUIResource(0,0,0));
        UIManager.getLookAndFeelDefaults().put(
            "ComboBox.listRenderer.background",new ColorUIResource(222,230,252)); 
        UIManager.getLookAndFeelDefaults().put( 
            "ComoboBox.padding", "insets(2,8,2,2)" );
        UIManager.getLookAndFeelDefaults().put(
            "JTextfield.background", new ColorUIResource(0,0,0));
        
                /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : 
                javax.swing.UIManager.getInstalledLookAndFeels() ) { 
                
                if ("Nimbus".equals(info.getName() ) ) {                    
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;                  
                }                
            }
        } catch ( ClassNotFoundException | InstantiationException | 
                IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex ) {
            JOptionPane.showMessageDialog( null, ex.toString(), 
                "Error setting Look and Feel", JOptionPane.ERROR_MESSAGE );
        }
        //Add whatever other settings you want to the method
        //</editor-fold>       
        UIManager.getLookAndFeelDefaults().put(
            "Panel.background",new Color(0,0,0));
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GMTtime().setVisible(true); // calls the class constructor which calls the GUI method                
        }); // end EventQueue lambda call to class constructor              
        
        while ( runClock == true ) { // GMT London, EST New York, AET Sydney, CET Paris, JST Tokyo             
            
            millis = System.currentTimeMillis();            
            
            // this was dropping seconds until I divided the millis by 10. 
            if( ( ( millis % 1000 )  / 10 ) == 0 ) {  
                
                Date currentTime = new Date(); // moving this inside the if block cut ~400+ MB running memory                             
                
                // uses the formatter (sdf) to select/format the values wanted from Date
                gmtTime = String.format("%s%n", sdf.format( currentTime ) );                
                clockTime = gmtTime; // added this to get around a null pointer exception                
                timeText.setText( clockTime ); // update the time displayed in the textfield                
                
                gmtTime = ""; // trying to find the source of the last 80-100MB of runtime memory
                clockTime = null;                
                currentTime = null;
                millis = 0; // this cut running memory by ~300+ MB
            } // end if              
        } // end while  
    } // </editor fold> end main

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bigBen = new javax.swing.JLabel();
        timeText = new javax.swing.JTextField();
        pickZone = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trade Session Time");
        setAlwaysOnTop(true);
        setBackground(new ColorUIResource(222,230,252));
        setMinimumSize(new java.awt.Dimension(250, 170));
        setPreferredSize(new java.awt.Dimension(250, 170));

        bigBen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/showgmt/images/bigbenicon.jpg"))); // NOI18N
        bigBen.setToolTipText("Click to close clock");
        bigBen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bigBen.setIconTextGap(0);
        bigBen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bigBenMouseClicked(evt);
            }
        });

        timeText.setEditable(false);
        timeText.setBackground(new java.awt.Color(0, 0, 0));
        timeText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        timeText.setForeground(new java.awt.Color(240, 240, 240));
        timeText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeText.setText(gmtTime);
        timeText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        timeText.setMargin(new java.awt.Insets(0, 0, 0, 0));
        timeText.setMinimumSize(new java.awt.Dimension(120, 32));
        timeText.setPreferredSize(new java.awt.Dimension(120, 32));

        pickZone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pickZone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "London", "New York", "Paris", "Sydney", "Tokyo" }));
        pickZone.setMinimumSize(new java.awt.Dimension(102, 26));
        pickZone.setName("Pick a Zone"); // NOI18N
        pickZone.setOpaque(false);
        pickZone.setPreferredSize(new java.awt.Dimension(102, 26));
        pickZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickZoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(bigBen)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pickZone, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bigBen))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pickZone, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        bigBen.getAccessibleContext().setAccessibleName("Big Ben");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bigBenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bigBenMouseClicked
        
        runClock = false; // stop the clock loop
        System.exit( 0 ); // close the app
    }//GEN-LAST:event_bigBenMouseClicked

    private void pickZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickZoneActionPerformed
        
        pickZone.addItemListener((ItemEvent event) -> {
            // the lambda replaces the anonymous inner class and itemStateChanged method call
            if( event.getStateChange() == ItemEvent.SELECTED )
                x = pickZone.getSelectedIndex();
        }); // end itemListener lambda method   
        
        sdf.setTimeZone( TimeZone.getTimeZone( timeZone[ x ] ) ); // offset system time using selected combobox index
        String imageStr; // uses the index of the selected combobox element to select an image
        switch ( x ) {   // the same index is used to select the timezone offset that goes with the image & city
            
            case 0:
                imageStr = "/showgmt/images/bigbenicon.jpg";
                break;
            case 1:
                imageStr = "/showgmt/images/ny_clock_150_90_121.jpg";
                break;
            case 2:
                imageStr = "/showgmt/images/smartParis150_90_123.jpg";
                break;
            case 3:
                imageStr = "/showgmt/images/sydneyCrop150_90_122.jpg";
                break;
            case 4:
                imageStr = "/showgmt/images/smartGinza150_90_120.jpg";
                break;
            default:
                imageStr = "No image for that, pls try again.";
                break;            
        } // end switch for imageStr   
        
        // sets the image to match the timezone
        bigBen.setIcon( new javax.swing.ImageIcon( 
            getClass().getResource( imageStr ) ) );
        // take out the garbage
        Runtime takeThis = Runtime.getRuntime();
        takeThis.gc();
    }//GEN-LAST:event_pickZoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bigBen;
    private javax.swing.JComboBox pickZone;
    private static javax.swing.JTextField timeText;
    // End of variables declaration//GEN-END:variables
    
    private void setIcon() {
        
        // this bit of wizardry brought to you by anhpnh2801 on YouTube: https://www.youtube.com/watch?v=40ikcEonWng
        setIconImage( Toolkit.getDefaultToolkit().getImage( 
            getClass().getResource( "ringsAvatar32x32trans.png" ) ) );
    } // end method setIcon
} // end class GMTtime

/**************************************** spare code collection **********************************************/       
    // this format works when you're using a LaF (like Nimbus) and want to change how something looks    
    // UIManager.getLookAndFeelDefaults().put("OptionPane.background",new ColorUIResource(222, 230, 252) );
        
    /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override                                                               ← this was replaced by the lamda above
            public void run() {
                new GMTtime().setVisible(true);
            }
        });     
       
// set the padding of the text displayed in the combobox ← done from GUI properties now
// ( BasicComboBoxRenderer )pickZone.getRenderer()).setBorder( new EmptyBorder( 0, 4, 0, 2 ) );
//Insets insets = new Insets(2,6,2,4);

// running app from a web page  // doesn't work
 <applet code=GMTtime.class 
        archive="relative/path/GMTtime.jar"
        width="250" height="170">
</applet>

// may need this
<jar href="DynamicTreeDemo.jar"
            main="true" />

 */
