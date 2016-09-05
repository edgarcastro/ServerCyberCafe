/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author edgar
 */
public class ThreadForClient extends Thread{
    // PROPIEDADES 
    // es el socket de conexion con el cliente
    private Socket sk;
    private String nameClient;
    private Integer idClient;
    private Boolean locked;

    public ThreadForClient(Socket sk) {
        this.sk = sk;
        locked = false;
    }
    /**
     * Asinga un un nombre al cliente y se lo envia
     */
    public void sendName() { 
        Thread tout = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    DataOutputStream flujoSalida = null;
                    // creamos un flujo de salida para enviar datos el cliente
                    flujoSalida = new DataOutputStream(sk.getOutputStream());
                    // enviamos un dato de tipo Chars, en este caso la orden de apagar
                    flujoSalida.writeUTF(nameClient);
                    // obligamos a que ese dato salga del cache de la tarjeta de red del servidor
                    flujoSalida.flush();
                } catch (IOException error) {
                    // colocamos un mensaje de error en caso de que ocurra
                    System.out.println(""+error);
                    //getVentana().mostrarMensajes("Error al Enviar datos al cliente\nMensaje Original: " + error.getMessage());
                    //Servidor.getListaClientesConectados().remove(HiloAtiendeCliente.this);
                    //ventana.actualizarClientesConectados();
                }
                
            }
            
        };
        tout.start();
    }
    
    /**
     * este metodo recibe Strig con la accion que sera enviada a un cliente 
     * para que este la realice
     * @param mensaje 
     */
    public void sendActions(String mensaje){
        Thread tout = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    DataOutputStream flujoSalida = null;
                    // creamos un flujo de salida para enviar datos el cliente
                    flujoSalida = new DataOutputStream(sk.getOutputStream());
                    // enviamos un dato de tipo Chars, en este caso la orden de apagar
                    flujoSalida.writeUTF(mensaje);
                    // obligamos a que ese dato salga del cache de la tarjeta de red del servidor
                    flujoSalida.flush();
                    setLocked(true);
                    System.out.println("Mande a bloquear a "+nameClient);
                } catch (IOException error) {
                    // colocamos un mensaje de error en caso de que ocurra
                    System.out.println(""+error);
                    //getVentana().mostrarMensajes("Error al Enviar datos al cliente\nMensaje Original: " + error.getMessage());
                    //Servidor.getListaClientesConectados().remove(HiloAtiendeCliente.this);
                    //ventana.actualizarClientesConectados();
                }
                
            }
            
        };
        tout.start();
        
    }
    
    public void blockClient(){
        String req = "BLOCK";
        Thread tout = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    DataOutputStream flujoSalida = null;
                    // creamos un flujo de salida para enviar datos el cliente
                    flujoSalida = new DataOutputStream(sk.getOutputStream());
                    // enviamos un dato de tipo Chars, en este caso la orden de apagar
                    flujoSalida.writeUTF(req);
                    // obligamos a que ese dato salga del cache de la tarjeta de red del servidor
                    flujoSalida.flush();
                    setLocked(true);
                    System.out.println("Mande a bloquear a "+nameClient);
                } catch (IOException error) {
                    // colocamos un mensaje de error en caso de que ocurra
                    System.out.println(""+error);
                    //getVentana().mostrarMensajes("Error al Enviar datos al cliente\nMensaje Original: " + error.getMessage());
                    //Servidor.getListaClientesConectados().remove(HiloAtiendeCliente.this);
                    //ventana.actualizarClientesConectados();
                }
                
            }
            
        };
        tout.start();
    }
    
    public void unBlockClient(){
        String req = "UNBLOCK";
        Thread tout = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    DataOutputStream flujoSalida = null;
                    // creamos un flujo de salida para enviar datos el cliente
                    flujoSalida = new DataOutputStream(sk.getOutputStream());
                    // enviamos un dato de tipo Chars, en este caso la orden de apagar
                    flujoSalida.writeUTF(req);
                    // obligamos a que ese dato salga del cache de la tarjeta de red del servidor
                    flujoSalida.flush();
                    setLocked(false);
                    System.out.println("Mande a desbloquear a "+nameClient);
                } catch (IOException error) {
                    // colocamos un mensaje de error en caso de que ocurra
                    System.out.println(""+error);
                    //getVentana().mostrarMensajes("Error al Enviar datos al cliente\nMensaje Original: " + error.getMessage());
                    //Servidor.getListaClientesConectados().remove(HiloAtiendeCliente.this);
                    //ventana.actualizarClientesConectados();
                }
                
            }
            
        };
        tout.start();
    }
    
     // SOBRE ESCRIBIR EL METODO RUN PARA EJECUTAR EL METODO capturarCedula como un subproceso
    @Override
    public void run() {
        super.run();
        this.sendName();
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

}
