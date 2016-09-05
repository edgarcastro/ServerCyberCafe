/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import servercybercafe.controllers.masterController;

/**
 *
 * @author edgar
 */
public class Server extends Thread{
    private static List<ThreadForClient> clients;
    private int port;
    private Boolean state;
    private ServerSocket conexionServidor;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }
    
    public void runServer(){
        try {
             // creamos una instancia de ServerSocket indicando el puerto a utilizar
            // debemos capturar la excepcion que se puede lanzar en caso de que no se
            // pueda abrir el puerto de red
            conexionServidor = new ServerSocket(port);
            state = true;
            // MOSTRAMOS UN MESAJE DE OK
            JOptionPane.showMessageDialog(null, "Servidor Conectado en el puerto :"+port);
            System.out.println("Servidor Conectado en el puerto: "+port);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo iniciar el servidor");
            System.out.println("ERROR: No se puedo iniciar el servidor\nMensaje Original: "+error.getMessage());
        }
    }
    
    public void listenClients(){
        while(state == true){
            try{
                Socket newClient = conexionServidor.accept();
                ThreadForClient tfc = new ThreadForClient(newClient);
                clients.add(tfc);
                tfc.setNameClient("PC 0"+clients.indexOf(tfc));
                tfc.setIdClient(clients.indexOf(tfc));
                JOptionPane.showMessageDialog(null, "Hay un nuevo Cliente llamado: "+tfc.getNameClient());
                System.out.println("Hay un nuevo Cliente llamado: "+tfc.getNameClient());
                masterController.updateClients(clients);
                tfc.start();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Error de entrada Input o Salida Output");
                System.out.println("\"Error de entrada Input o Salida Output \"");
            }
        }
    }
    
     @Override
    public void run() {
        super.run();
        runServer();
        listenClients();
    }
    
    public void blockOneClient(int idPc){
        clients.get(idPc).sendActions("BLOCK");
    }
    
    public void unBlockOneClient(int idPc){
        clients.get(idPc).sendActions("UNBLOCK");
    }
    
    public void apagarOneCliente(int idPc){
        clients.get(idPc).sendActions("APAGAR");
    }
    
    public void reiniciarOneCliente(int idPc){
        clients.get(idPc).sendActions("REINICIAR");
    }
    
    public void cancelarOperacion(int idPc){
        clients.get(idPc).sendActions("CANCELAR");
    }
}
