package j.socket.udpsever;

import java.io.IOException;
import java.net.*;

/**
 * Created by EdisonCat on 17-7-14.
 */
public class UDPServer implements ConstantValues{

    DatagramSocket datagramSocket=null;
    DatagramPacket datagramPacketReceived=null;
    byte [] dataReceived=null;

    public static void main(String [] args){

        UDPServer udpServer=new UDPServer();
        udpServer.start();

    }


    /**
     * Constructs a udp server and set up port number.
     * Prints starting status.
     */
    public UDPServer(){
        try {
            datagramSocket=new DatagramSocket(PORT);
            System.out.println("\nserver started");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    /**
     * Calls methods through a while loop.
     */
    public void start(){

        while (true){

            receiveDate();
            createThread();
        }
    }


    /**
     * Tries to receive the datagram packet sent by a client.
     * Waits until data is received.
     * Calls method createThread() if data is received.
     */
    public void receiveDate(){

        dataReceived=new byte[1024];
        datagramPacketReceived=new DatagramPacket(dataReceived,dataReceived.length);

        try {

            datagramSocket.receive(datagramPacketReceived);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    /**
     * Creates a thread after data was received.
     */
    public void createThread(){

        Thread thread=new Thread(new UDPThread(datagramSocket,datagramPacketReceived));
        thread.start();
    }

}