package com.Server;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import com.Util.*;
/**
 * Класс прием-отправки сообщений,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */


public class DSSrv {
  //private static Socket socket; //сокет для общения
  private static ServerSocket server; // серверсокет
  private static BufferedReader in; // поток чтения из сокета
  private static BufferedWriter out; // поток записи в сокет
  private ReceivedNL receivedNL;
  private static final int PORT = 9996;
  public static Story story; // история общей переписки
  private static InetAddress ipaddr;


  public void DSServer() throws IOException { //принимаем данные
    String ping;
    try (ServerSocket server = new ServerSocket(PORT)){
      while (true) {
        Socket socket = server.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //TODO: Добавить новое соединение в список сокетов

        ping = in.readLine();

        if (ping.equals("ping")) {
          System.out.println("Don't worry, this is ping");
        }
      }

    } catch (IOException e){
      e.printStackTrace();
    } finally {
      in.close();
      out.close();
    }
  }

  public void DSClient(InetAddress ipaddr, int port) throws IOException {
    try (Socket socket = new Socket(ipaddr, port)){
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }catch (IOException e){
      e.printStackTrace();
    }finally {
      in.close();
      out.close();
    }
  }

  public void DSCping() throws IOException { //"пингуем" юзеров по списку
    try {
      Enumeration en = receivedNL.hmap.keys();
      while (en.hasMoreElements()) {
        InetAddress addr = (InetAddress) en.nextElement();
        //получаем значение по ключу, и рассматриваем его как объект типа InetAddress
        try (Socket sping = new Socket(addr, PORT);){
          out = new BufferedWriter(new OutputStreamWriter(sping.getOutputStream()));
          out.write("ping" + "\n");
          out.flush();
        } catch (SocketException e){
          receivedNL.delSet(addr); //получатель не доступен, удаляем из списка
        } finally {
          out.close();
        }
      }
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}

