/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import servercybercafe.controllers.masterController;


/**
 *
 * @author edgar
 */
public class Servercybercafe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
  
  JFrame.setDefaultLookAndFeelDecorated(true);
  JDialog.setDefaultLookAndFeelDecorated(true);
  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
  //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
}
catch (Exception e)
 {
  e.printStackTrace();
 }
        masterController.init();
    }
    
}
