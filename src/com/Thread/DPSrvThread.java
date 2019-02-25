package com.Thread;

import com.Server.DPSrv;
import com.Util.ReceivedNL;
/**
 * Класс получения данных пользователей по UDP,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class DPSrvThread implements Runnable{
  private DPSrv dpSrv = new DPSrv();
  private Thread t;
  private ReceivedNL receivedNL;
  public DPSrvThread(String threadname, ReceivedNL receivedNL){
    this.receivedNL = receivedNL;
    t = new Thread(this, threadname);
    System.out.println("Новый поток: " + t);
    t.start();
    System.out.println("ЗапущенDPSRV: " + t.isAlive());
  }
  @Override
  public void run() {
    try {
      dpSrv.DPServer(receivedNL);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
