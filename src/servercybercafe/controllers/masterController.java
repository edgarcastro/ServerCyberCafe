/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.controllers;

import java.util.List;
import servercybercafe.model.Server;
import servercybercafe.views.MainView;

/**
 *
 * @author edgar
 */
public class masterController {
    
    private static MainView mv;
    private static Server sv;
    
    public static void init(){
        mv = new MainView();
        mv.setLocationRelativeTo(null);
        mv.setVisible(true);
    }
    
    public static void initServer(int port){
        sv = new Server(port);
        sv.start();
    }
    
    public static void updateClients(List clients){
        mv.updateNumberClients(clients.size());
        mv.updateClients(clients);
    }
    
    public static void blockClient(int idPC){
        sv.blockOneClient(idPC);
    }
    
    public static void unBlockClient(int idPC){
        sv.unBlockOneClient(idPC);
    }
    
    public static void apagarCliente(int idPC){
        sv.apagarOneCliente(idPC);
    }
    
    public static void reiniciarCliente(int idPC){
        sv.reiniciarOneCliente(idPC);
    }
    
    public static void cancelarOperacion(int idPC){
        sv.cancelarOperacion(idPC);
    }
}
