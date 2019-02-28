package com.Thread;

import com.Server.SocketSrv;
import com.Util.ReceivedNL;
/**
 * Класс тест отправки сообщений ping,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */

public class SocketPingThread implements Runnable{
  private SocketSrv socketSrv = new SocketSrv();
  private ReceivedNL receivedNL;

  public SocketPingThread(String threadname, ReceivedNL receivedNL) {
    this.receivedNL = receivedNL;
    Thread t = new Thread(this, threadname);
    System.out.println("Новый поток: " + t);
    t.start();
    System.out.println("Запущен SocketSrv.SocketPing: " + t.isAlive());
  }

  @Override
  public void run() {
    try {
      while (true) {
        socketSrv.SocketPing(receivedNL);
        Thread.sleep(15000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
