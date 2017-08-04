package j.socket.udpsever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Created by EdisonCat on 17-7-18.
 */
public class UDPThread implements Runnable {

    DatagramSocket datagramSocket = null;
    DatagramPacket datagramPacket = null;
    String info=null;
    InetAddress inetAddress=null;

    /**
     * Constructs a udp thread for processing data received
     * and updating database, etc.
     * @param datagramSocket a datagram socket that received a datagram packet.
     * @param datagramPacket a datagram packet that contains data sent by a client.
     */
    public UDPThread(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {

        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;

    }

    @Override
    public void run() {

        getInfo();
        modifyData(this.info);
    }

    /**
     * Gets information, including address, data, port number, from
     * the datagram packet received and replies to the client.
     */
    public void getInfo(){

        int port=9898;
        byte  [] data2=null;
        DatagramPacket datagramPacketSent = null;

        this.info=new String(this.datagramPacket.getData(),0,this.datagramPacket.getLength());
        this.inetAddress=this.datagramPacket.getAddress();
        port=this.datagramPacket.getPort();
        data2="server received".getBytes();
        datagramPacketSent=new DatagramPacket(data2,data2.length,this.inetAddress,port);
        try {
            this.datagramSocket.send(datagramPacketSent);
        } catch (IOException e) {
            System.err.println("Error:IOException.");
            e.printStackTrace();
        }
    }

    /**
     * Creates an object of ModifyData
     * and pass a parameter of String type.
     * @param info the string got from the datagram packet sent by a client.
     */
    public void modifyData(String info){

        DataModifier dataModifier=new DataModifier(info);
    }
}