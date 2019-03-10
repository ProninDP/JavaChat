package com.Server;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.Util.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Класс прием-отправки сообщений,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */


public class SocketSrv {
  private static BufferedReader in; // поток чтения из сокета
  private static BufferedWriter out; // поток записи в сокет
  private static final int PORT = 9996;


  public void SocketServer() throws IOException, InterruptedException { //принимаем соединения
    String msg;
    ServerSocket server = new ServerSocket(PORT);
    ExecutorService executorService = Executors.newFixedThreadPool(1000);
    Semaphore semaphore = new Semaphore(1000);
    while (true) {
      semaphore.acquire();
      Socket socket = server.accept();
      executorService.execute(() -> {
        try (Socket accept = socket) {
          server(accept);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          semaphore.release();
        }
      });
    }
  }
  public static void server(final Socket accept) throws IOException{
    InputStream is = accept.getInputStream();
    OutputStream os = accept.getOutputStream();
    ObjectInputStream ois = new ObjectInputStream(is);
    ObjectOutputStream oos = new ObjectOutputStream(os);
  }


  public void SocketClient(InetAddress ipaddr, int port) {
    try (Socket socket = new Socket(ipaddr, port)){
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      in.close();
      out.close();
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  public void SocketPing(ReceivedNL receivedNL) { //"пингуем" юзеров по списку
    try {
      Enumeration en = receivedNL.getHmap().keys();
      while (en.hasMoreElements()) {
        InetAddress addr = (InetAddress) en.nextElement();
        //получаем значение по ключу, и рассматриваем его как объект типа InetAddress
        try (Socket sping = new Socket(addr, PORT);){
          out = new BufferedWriter(new OutputStreamWriter(sping.getOutputStream()));
          out.write("ping" + "\n");
          out.flush();
          out.close();
        } catch (SocketException e){
          System.out.println("Сокет клиента не доступен, удаляем из списка!");
          receivedNL.delSet(addr); //получатель не доступен, удаляем из списка
        }
      }
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}

