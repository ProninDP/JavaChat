package com.Thread;

import com.Server.DPClient;
import com.Util.NameList;
import com.Util.Status;
/**
 * Класс потока отправки данных пользователя по UDP,
 * @autor Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class DPClientThread implements Runnable{
  private DPClient dpClient = new DPClient();
  private Thread t;
  private NameList nameList;
  private Status status;


  public DPClientThread(String threadname, NameList nameList, Status status){
    this.status = status;
    this.nameList = nameList;
    t = new Thread(this, threadname);
    System.out.println("Новый поток: " + t);
    t.start();
    System.out.println("ЗапущенDPCLIENT: " + t.isAlive());
  }

  @Override
  public void run() {
    try {
      while (true){
        dpClient.dpClient(nameList, status.getStatus());
        Thread.sleep(10000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
