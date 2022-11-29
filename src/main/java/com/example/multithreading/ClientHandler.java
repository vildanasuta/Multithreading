package com.example.multithreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    public ClientHandler(Socket client) {
        this.clientSocket=client;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        PrintWriter out=null;
        BufferedReader in=null;
        try{
            out=new PrintWriter(clientSocket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while((line=in.readLine())!=null){
                System.out.printf("Sent from client: "+line+System.lineSeparator());
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(out!=null)
                    out.close();
                if(in!=null)
                    in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
