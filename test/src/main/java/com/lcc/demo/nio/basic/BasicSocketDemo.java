package com.lcc.demo.nio.basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lcc
 * @version 2020/3/21
 */
public class BasicSocketDemo {

  private static final int LISTEN_PORT = 10000;

  public static void main(String[] args) {
    Server server = new Server();
    server.start();
  }

  public static final class Server extends Thread {

    @Override
    public void run() {
      try {
        ServerSocket socket = new ServerSocket(LISTEN_PORT);
        while (!Thread.interrupted()) {
          new Thread(new Handler(socket.accept())).start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public static final class Handler implements Runnable {

      private final Socket socket;

      public Handler(Socket socket) {
        this.socket = socket;
      }

      @Override
      public void run() {
        byte[] input = new byte[1024];
        try {
          socket.getInputStream().read(input);
          System.out.println("接收输入字符：" + input.toString());
          socket.getOutputStream().write("成功！".getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      private void process(byte[] bytes) {
        System.out.println("处理输入字符：" + bytes.toString());
      }

      public Socket getSocket() {
        return socket;
      }
    }
  }
}
