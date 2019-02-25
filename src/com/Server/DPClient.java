package com.Server;

import com.Util.NameList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
/**
 * Класс оправки UDP данных о локальном клиент-сервере в сеть,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class DPClient {

  private byte[] writeToByteArray(ArrayList<String> list) { //преобразуем массив спсисков в байт массив
    byte[] data = new byte[list.size()];
    try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos)) {
      out.writeObject(list);
      data = baos.toByteArray();
    }catch (IOException e){
      e.printStackTrace();
    }
    return data;
  }

  public void dpClient(NameList nameList, String status) { //отправляем имя клиента в бродкаст сеть
    try (DatagramSocket ds = new DatagramSocket(9998)) {
      byte[] data = writeToByteArray(nameList.nl(status));
      ds.send(new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 9997));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
