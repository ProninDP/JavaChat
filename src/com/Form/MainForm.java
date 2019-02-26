package com.Form;

import com.Util.*;

import javax.lang.model.util.Elements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Класс главной формы чата,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */

public class MainForm extends JFrame {
  private static final String APPLICATION_NAME = "JavaChat";
  private static final String ICON_STR = "/com/res/mainform/ic_question_answer_black_18dp.png";
  private JPanel rootPanel;
  private JPanel panel1;
  private JPanel panel2;
  private JList list1;
  private JComboBox<String> statBox;
  private JToolBar toolB;
  private JButton buttRef;
  private JButton buttInf;
  private JButton buttTul;
  private JButton buttSend;
  private JScrollPane jscp;

  private void restoreWindow() {
    setVisible(true);
    setExtendedState(getExtendedState() & (JFrame.ICONIFIED ^ 0xFFFF));
    requestFocus();
  }
  private void hideWindow() {
    setVisible(false);
  }

  public MainForm(ReceivedNL receivedNL, Status status) {
    this.getContentPane().add(rootPanel);
    //Отобразить список
    list1.setListData(receivedNL.hmap.values().toArray());
    //Окно чата
    ChatForm cf = new ChatForm();
    cf.setDefaultCloseOperation(ChatForm.HIDE_ON_CLOSE);
    cf.pack();
    cf.setTitle("Chat");
    cf.setVisible(false);
    list1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          cf.restoreWindow();
          int idx = list1.getSelectedIndex();
          if (idx != -1){
            cf.addtab((ArrayList<String>) list1.getSelectedValue());
            cf.setTitle("Chat with " + list1.getSelectedValue());
          }
        }
      }
    });
    //Сварачивание в трей
    setTrayIcon();
    //Отправить всем
    buttSend.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
    //обновить список
    synchronized (receivedNL) {
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          list1.setListData(receivedNL.hmap.values().toArray());
        }
      },0, 1000);
      buttRef.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          list1.setListData(receivedNL.hmap.values().toArray());
        }
      });
    }
    //Статус в комбобокс
    statBox.addItem("Доступен");
    statBox.addItem("Занят");
    statBox.addItem("Нет на месте");
    statBox.setSelectedIndex(0);
    statBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        status.setStatus((String)statBox.getSelectedItem());
      }
    });
  }

  //Сварачивание в трей
  private void setTrayIcon() {
    if (!SystemTray.isSupported()) {
      return;
    }
    //image
    URL imageURL = MainForm.class.getResource(ICON_STR);
    Image image = Toolkit.getDefaultToolkit().getImage(imageURL);

    //menu
    PopupMenu trayPopupMenu = new PopupMenu();

    //1t menuitem for popupmenu
    MenuItem action = new MenuItem("Action");
    action.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Action Clicked");
      }
    });
    trayPopupMenu.add(action);

    //2nd menuitem of popupmenu
    MenuItem miRestore = new MenuItem("Restore");
    miRestore.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        restoreWindow();
      }
    });
    trayPopupMenu.add(miRestore);


    //3th menuitem of popupmenu
    MenuItem close = new MenuItem("Close");
    close.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    trayPopupMenu.add(close);

    //setting tray icon
    TrayIcon trayIcon = new TrayIcon(image, APPLICATION_NAME, trayPopupMenu);
    //adjust to default size as per system recommendation
    trayIcon.setImageAutoSize(true);
    trayIcon.addMouseListener(new TrayMouseListener());

    SystemTray systemTray = SystemTray.getSystemTray();
    try{
      systemTray.add(trayIcon);
      addWindowListener(new WindowMinimizeListener());
    }catch(AWTException awtException){
      awtException.printStackTrace();
    }
    trayIcon.displayMessage(APPLICATION_NAME, "Application started!",
            TrayIcon.MessageType.INFO);
  }
  class TrayMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        restoreWindow();
      }
    }
  }
  class WindowMinimizeListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      hideWindow();
    }
    @Override
    public void windowIconified(WindowEvent e) {
      hideWindow();
    }
  }
}
