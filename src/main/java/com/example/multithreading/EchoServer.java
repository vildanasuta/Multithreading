package com.example.multithreading;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server=null;
        try {
            server=new ServerSocket(32000);
            server.setReuseAddress(true);
            while(true) {
                Socket client = server.accept();
                System.out.println("New client connected:  " + client.getInetAddress().getHostAddress());
                ClientHandler handler = new ClientHandler(client);
                new Thread(handler).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(server!=null){
                try{
                    server.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }
}