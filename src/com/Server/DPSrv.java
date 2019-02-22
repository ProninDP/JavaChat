package com.Server;
import com.Util.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
/**
 * Класс приема UDP данных о клиент-серверах из сети,
 * @autor Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class DPSrv{
  private static int srvPort = 9997;
  private static DatagramSocket ds;
  private static int buffer_size = 1024;
  private byte[] buffer = new byte[buffer_size];


  private ArrayList<String> readFromByteArray(byte[] bytes) throws Exception { //преобразуем байт массив в массив спсисков
    ArrayList<String> list = new ArrayList<>();
    try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais)) {
        list = (ArrayList) ois.readObject();
    }catch (IOException e){
      e.printStackTrace();
    }
    return list;
  }

  public void DPServer(ReceivedNL receivedNL) throws Exception { //принимаем из бродкаст сети клиентов
    ds = new DatagramSocket(srvPort);

    try {
      while (true) {
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        ds.receive(p);
        InetAddress adr = p.getAddress();
        ArrayList<String> received = readFromByteArray(p.getData());
        receivedNL.entrySet(adr, received); //создать список подключений
        receivedNL.print();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      ds.close();
    }
  }
}
