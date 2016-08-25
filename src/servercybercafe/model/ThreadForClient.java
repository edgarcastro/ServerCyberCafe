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

    public ThreadForClient(Socket sk) {
        this.sk = sk;
    }
    
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
    
    
    public void capturaNombre() {
        DataInputStream flujoEntrada = null;
        try {
            // OBTENEMOS EL FLUJO DE ENTRADA DEL CLIENTE Y CREAMOS CON ESTE UN FLUJO DE ENTRADA DE DATOS
            flujoEntrada = new DataInputStream(sk.getInputStream());
            // leemos mediante un while hasta que llegue la cedula del cliente
            while (nameClient == null) {
                // CAPTURAMOS LA CEDULA
                nameClient = flujoEntrada.readUTF();
                // cuando esta llegue, le enviamos al cliente su numero aleatorio    
                //notificarNumeroDeLoteria(boleto);
            }
        } catch (IOException ex) {
            // AQUI COLOCAMOS UN MENSAJE DE ERROR EN LA VENTANA.
            //ventana.mostrarMensajes("ERROR: Problemas de Entrada y Salida en el cliente IP: " + conexionCliente.getInetAddress().getHostAddress());
        } 
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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

}
