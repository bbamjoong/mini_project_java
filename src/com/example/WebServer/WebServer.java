package com.example.WebServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebServer {
    public static void main(String[] args) throws Exception{
        //클라이언트가 접속할 때까지 대기할 떄 필요한 객체가 ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트가 접속하면 클라이언트와 통신하는 socket 리턴

                OutputStream out = clientSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
                InputStream in = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String firstLine = br.readLine();
                String msg = "";
                if (firstLine.contains("/hello")) {
                    msg = "hello";
                } else if (firstLine.contains("/hi")) {
                    msg = "hi";
                }

                List<String> headers = new ArrayList<>();
                String line;

                //빈줄을 만나면 while문을 끝낸다.
                while (!(line = br.readLine()).isEmpty()) {
                    headers.add(line);
                } // -> 요청정보 읽기 끝.

                System.out.println(firstLine);
                for (String header : headers) {
                    System.out.println(header);
                }

                pw.println("HTTP/1.1 200 OK");
                pw.println("name : kim");
                pw.println("email: rlaqjawnd625@naver.com");
                pw.println("");
                pw.println("<html>");
                pw.println("<h1>" + msg + "!!!</h1>");
                pw.println("</html>");

                pw.flush();
                br.close();
                pw.close();
                clientSocket.close();
            }
        } finally {
            System.out.println("서버가 종료됩니다.");
        }

    }
}
