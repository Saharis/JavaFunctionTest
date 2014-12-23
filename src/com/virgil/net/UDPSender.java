package com.virgil.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender {
    public static void main(String[] args) {
        try {
            //定义要发送的字符串并转为byte数组
            byte[] buffer = "Hello".getBytes();
            int messageCount=0;
            //目标ip地址
            InetAddress wiFiDestIP = InetAddress.getByName("192.168.2.27");
            //定义UDP数据包，需要指定目标地址及端口号
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length,wiFiDestIP,8001);
            //发送数据
            DatagramSocket sendSocket = new DatagramSocket();
            StringBuffer dataString =new StringBuffer();
            while(true){
                sendSocket.send(packet);
                System.out.println("Send Data:" +  new String(packet.getData()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                messageCount++;
                buffer = ("Hello" + messageCount).getBytes();
                packet.setData(buffer,0,buffer.length);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}