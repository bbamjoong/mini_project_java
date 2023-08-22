package com.example.WebServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VerySimpleWebServer {
    public static void main(String[] args) throws Exception{
        //3000 port로 대기한다.
        ServerSocket serverSocket = new ServerSocket(3000);

        //클라이언트를 대기한다.
        //클라이언트가 접속하는 순간, 클라이언트와 통신할 수 있는 socket을 리턴
        System.out.println("클라이언트 접속을 기다립니다.");
//        //Socket은 브라우저(client)와 통신할 수 있는 객체
        Socket clientSocket = serverSocket.accept();
//
        // Client와 읽고 쓸 수 있는 InputStream, OutputStream 리턴
        OutputStream out = clientSocket.getOutputStream();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
        InputStream in = clientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String firstLine = br.readLine();
        List<String> headers = new ArrayList<>();
        String line;

        //빈줄을 만나면 while문을 끝낸다.
        while(!(line = br.readLine()).isEmpty()){
            headers.add(line);
        } // -> 요청정보 읽기 끝.

        System.out.println(firstLine);
        for(int i = 0; i < headers.size();i++){
            System.out.println(headers.get(i));
        }

        //클라이언트 에게 응답메시지 보내기
        //HTTP/1.1 200 OK  <-- 상태메시지 : 요청이 성공적이라는 뜻
        //헤더 1
        //헤더 2
        //빈줄
        //내용

        pw.println("HTTP/1.1 200 OK");
        pw.println("name : kim");
        pw.println("email: rlaqjawnd625@naver.com");
        pw.println("");
        pw.println("<html>");
        pw.println("<h1>Hello!!!</h1>");
        pw.println("</html>");
        pw.close();

        clientSocket.close();
        serverSocket.close();
        System.out.println("서버가 종료됩니다.");
    }
}
