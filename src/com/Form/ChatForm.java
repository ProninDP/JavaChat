package com.Form;

import com.Util.Smile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
/**
 * Класс формы оправки-чтения сообщений,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class ChatForm extends JFrame{
  private static final String ICON_DIR = "/com/res/smiledir/";
  private JPanel rootPanel;
  private JTabbedPane tabbedPane1;
  private JPanel tabP1;
  private JPanel tabP2;
  private JPanel tabP3;
  private JLabel nameL;
  private JTextField nameField;
  private JLabel nameAD;
  private JTextField nameADField;
  private JLabel emailL;
  private JTextField mailField;
  private JLabel stL;
  private JTextField statField;
  private JButton buttInfo;
  private JButton buttSmile;
  private JButton buttFont;
  private JButton buttBG;
  private JButton buttFile;
  private JButton buttHist;
  private JButton buttClear;
  private JButton buttCloseTab;
  private JButton buttCit;
  private JCheckBox checkAnswer;
  private JButton buttSM;
  private JScrollPane ScrollP1;
  private JScrollPane ScrollP2;
  private JPanel jpanel;
  private JTextPane textPaneIn;
  private JTextPane textPaneOut;
  private Smile smile;

  public ChatForm(Smile smile){
    this.smile = smile;
    this.getContentPane().add(rootPanel);
    //tabbedPane1.addTab(name, new TabbPane());
  }
  public void addtab(ArrayList<String> name){
    tabbedPane1.addTab(name.get(0), jpanel);
    nameField.setText(name.get(1));
    nameADField.setText(name.get(0));
    statField.setText(name.get(2));
    buttCloseTab.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int select = tabbedPane1.getSelectedIndex();
        if (select>=1){
          tabbedPane1.remove(select);
        } if (select==0){
          setVisible(false);
        }
      }
    });

    buttSmile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        /*JFrame smilefram = new JFrame();
        smilefram.setUndecorated(true);
        smilefram.setVisible(true);
        smilefram.setSize(250, 300);
        JPanel contents = new JPanel();
        //contents.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton exit = new JButton("Exit");
        //JList<String> jList = new JList<>(smile.getSmileName());
        exit.setSize(200, 200);
        JScrollPane jScrollPane = new JScrollPane();
        for(String s : smile.getSmileName())
          jScrollPane.add(new JButton(s));
        contents.add(jScrollPane);
        contents.add(exit);
        smilefram.setContentPane(contents);
        exit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            smilefram.dispose();
          }
        });*/

        JLayeredPane lp = getLayeredPane();
        JComponent source = (JComponent) e.getSource();
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setSize(200, 200);
        jPanel.setVisible(true);
        jPanel.setBorder(new LineBorder(Color.gray));
        jPanel.setLocation(new Point(source.getX() + source.getWidth() / 2,
                source.getY() + source.getHeight() / 2));

        for(String s : smile.getSmileName()) {
          jPanel.add(creatLabel(s, source), JLayeredPane.POPUP_LAYER);
        }
        jPanel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            jPanel.setVisible(false);
          }
        });
        //lp.add(jPanel, JLayeredPane.POPUP_LAYER);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //jScrollPane.setBounds(jPanel.getX(), jPanel.getY(), jPanel.getWidth(), jPanel.getHeight());
        jScrollPane.setPreferredSize(jPanel.getPreferredSize());
        //jScrollPane.add(jPanel);
        lp.add(jPanel, JLayeredPane.POPUP_LAYER);
        //jScrollPane.getViewport().setPreferredSize(new Dimension(250, 250));
      }
      private JLabel creatLabel(String s, JComponent source) {
        JLabel jLabel = new JLabel();
        jLabel.setOpaque(false);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setBackground(new Color(50, 210, 250, 200));
        jLabel.setSize(24, 24);
        jLabel.setBorder(new LineBorder(Color.gray));
        jLabel.setVisible(true);
        URL imageURL = ChatForm.class.getResource(ICON_DIR + s);
        ImageIcon image = new ImageIcon(imageURL);
        jLabel.setIcon(image);
        jLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            jLabel.setVisible(false);
          }
        });
        return jLabel;
      }
    });
  }
  public void restoreWindow() {
    setVisible(true);
    setExtendedState(getExtendedState() & (JFrame.ICONIFIED ^ 0xFFFF));
    requestFocus();
  }
  private void hideWindow() {
    setVisible(false);
  }
  class WindowCloser extends WindowAdapter {
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
