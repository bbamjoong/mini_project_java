package com.example.chat2;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8000);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        //백그라운드(Thread)로 서버가 보내준 메시지를 읽어들여서 화면에 출력
        InputThread inputThread = new InputThread(br);
        inputThread.start();

        //클라이언트는 읽어들인 메시지를 서버로 전달
        try{
            String line;
            while((line = keyboard.readLine()) != null) {
                // "/quit"메시지 입력 시 finally 구문으로 이동
                if("/quit".equals(line)){
                    pw.println("/quit");
                    pw.flush();
                    break;
                }
                // 메시지 입력시 화면에 출력
                pw.println(line);
                pw.flush();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        //BufferedReader, PrintWriter,
        //키보드로부터 입력받는 BufferedReader, socket을 종료
        }finally {
            try {
                br.close();
            } catch (Exception ex) {}
            try {
                pw.close();
            } catch (Exception ex) {}

            try {
                keyboard.close();
            } catch (Exception ex) {}

            try {
                //소켓 종료전에 안내메시지 출력
                System.out.println("접속을 종료합니다. 안녕히 가세요.");
                socket.close();
            } catch (Exception ex) {}
        }
    }
}

class InputThread extends Thread{
    BufferedReader br;
    public InputThread(BufferedReader br){
        this.br = br;
    }

    @Override
    public void run() {
        try{
            //읽어들인 메시지를 화면에 출력
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            //Thread 종료 시 메시지 출력
        }catch(Exception ex){
            System.out.println("Thread가 종료되었어요.");
        }
    }
}