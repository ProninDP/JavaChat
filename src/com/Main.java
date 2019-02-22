package com;

import com.Form.MainForm;
import com.Thread.DPClientThread;
import com.Thread.DPSrvThread;
import com.Util.NameList;
import com.Util.ReceivedNL;
import com.Util.Status;

import javax.swing.*;
/**
 * Класс создания обьектов(Список пользователей, Локальные данные, Главная Форма чата),
 * запуска главных потоков(UDP Прием сообщений, UDP Отправка сообщений, TCP Сервер).
 * @autor Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class Main {

  public static void main(String[] args) {
    ReceivedNL receivedNL = new ReceivedNL(); //Список клиент-серверов
    NameList nameList = new NameList(); //Данные локального пользователя
    Status status = new Status("Доступен"); //Статус клиента
    DPSrvThread dpSrvThread = new DPSrvThread("DPSRV", receivedNL);
    DPClientThread dpClientThread = new DPClientThread("DPCLIENT", nameList, status);
    SwingUtilities.invokeLater(() -> {
      MainForm mf = new MainForm(receivedNL, status);
      mf.setDefaultCloseOperation(MainForm.EXIT_ON_CLOSE);
      mf.pack();
      mf.setTitle("JavaChat");
      mf.setVisible(true);
    });
    //Проверка запуска потоков
    System.out.println("ЗапущенDPSRV: " + dpSrvThread.t.isAlive());
    System.out.println("ЗапущенDPCLIENT: " + dpClientThread.t.isAlive());
  }
}
