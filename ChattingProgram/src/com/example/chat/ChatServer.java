package com.example.chat2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8000);
        List<ChatThread> list = Collections.synchronizedList(new ArrayList<>());

        while(true){
            Socket socket = serverSocket.accept(); // 클라이언트가 접속
            ChatThread chatThread = new ChatThread(socket, list);
            System.out.println(socket.getInetAddress() + " " + chatThread.getName() + " 유저가 접속");
            chatThread.start();
        }
    }
}

class ChatThread extends Thread{
    private String name = "\"닉네임을 정하지 않은 유저\"";
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    private List<ChatThread> list;

    public ChatThread(Socket socket, List<ChatThread> list) throws Exception{
        this.socket = socket;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        this.br = br;
        this.pw = pw;
        this.list = list;
        this.list.add(this);
    }

    private void sendMessage(String msg){
        pw.println(msg);
        pw.flush();
    }

    @Override
    public void run() {
        try {
            pw.println("닉네임을 입력해주세요.");
            pw.flush();

            String line;
            //아직 닉네임을 생성하지 않았다는 의미의 boolean형 변수
            boolean identity = false;

            while ((line = br.readLine())!=null){
                if (!identity){
                    identity = true;
                    if(line.equals("/quit")){
                        quitChat(name);
                        break;
                    }
                    //닉네임을 입력하는 경우
                    name = line;
                    //서버에 닉네임 생성 정보 전송
                    System.out.println(socket.getInetAddress() + " " +
                            list.get(list.indexOf(this)).getName() + " 유저가 " + name + " 닉네임을 \"생성\"");
                    //닉네임을 생성한 유저 클라이언트에 환영 문구 전송
                    this.pw.println(name + "님 채팅에 오신 것을 환영합니다!");
                    pw.flush();
                    //다른 유저들에게 접속문구 전송
                    broadcast(name + "님이 접속하였습니다.", false);
                }
                //닉네임 생성 이후의 채팅
                else {
                    //"/quit"메시지 입력 시 while문 종료 -> finally 구문으로 이동
                    if ("/quit".equals(line)) {
                        quitChat(name);
                        break;
                    }
                    // "/online"메시지 입력 시
                    //접속중인 유저 리스트를 보여주고 채팅 진행
                    if ("/online".equals(line)) {
                        online(list);
                        continue;
                    }
                    broadcast(name + " : " + line, true);
                }
            }
        }catch (Exception ex) { //ChatThread가 연결이 끊어졌다.
            System.out.println(name + "(이)가 \"강제종료\"");
            threadInfo(list);
        }finally{
            //닉네임 생성 단계에 "/quit" 입력 시 닉네임을 전송할 필요가 없다.
            if(!name.equals("\"닉네임을 정하지 않은 유저\"")){
                broadcast(name + "님이 접속을 종료하였습니다.", false);
            }

            //list에서 현재 Thread를 삭제한다.
            list.remove(this);
            System.out.println(name + "(이)가 종료하면서 남은 사용자 Thread : " + list);
            System.out.println();

            // BufferedReader, PrintWriter, Socket을 종료한다.
            try{
                br.close();
            }catch(Exception ex){}

            try{
                pw.close();
            }catch(Exception ex){}

            try{
                socket.close();
            }catch(Exception ex){}
        }
    }

    private void broadcast(String msg, boolean includeMe){
        try{
            for(ChatThread ct : list){
                if (!includeMe) { // 나를 포함하지마세요.
                    if(ct == this){
                        continue;
                    }
                }
                if(!ct.name.equals("\"닉네임을 정하지 않은 유저\"")) {
                    ct.sendMessage(msg);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void online(List<ChatThread> list){
        int cnt = 0;
        for(ChatThread ct : list){
            if (!ct.name.equals("\"닉네임을 정하지 않은 유저\"")) {
                this.pw.print(ct.name + " ");
                cnt += 1;
            }
        }
        this.pw.println();
        this.pw.println("총 " + cnt + "명 접속중입니다. ^^");
        this.pw.println();
        pw.flush();
    }

    private void threadInfo(List<ChatThread> list){
        System.out.println("접속 정보 : " + socket.getInetAddress() + " " +
                list.get(list.indexOf(this)).getName());
    }

    private void quitChat(String name){
        System.out.println(name + "(이)가 \"/quit\"으로 종료");
        threadInfo(list);
    }
}