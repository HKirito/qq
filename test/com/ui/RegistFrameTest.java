package com.ui;
import com.oim.common.util.Config;
import com.oim.common.util.MessageConfig;
import com.oim.common.util.User;
import com.oim.ui.LoginFrame;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistFrameTest extends JFrame {
    private Socket client;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    public static void main(String[] arg){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Ready to create !");
                    RegistFrameTest frameTest = new RegistFrameTest();
                    System.out.println("Create succeed !");
                    frameTest.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public RegistFrameTest(){
        try {
            client = new Socket(Config.serverIP,Config.port);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

        setResizable(false);
        setTitle("\u804A\u5929\u8F6F\u4EF6-\u6CE8\u518C\u7A97\u53E3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u8D26\u53F7");
        label.setBounds(83, 42, 37, 15);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(133, 39, 197, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("\u5BC6\u7801");
        label_1.setBounds(83, 83, 37, 15);
        contentPane.add(label_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(133, 80, 197, 21);
        contentPane.add(passwordField);

        JLabel label_2 = new JLabel("\u5E74\u9F84");
        label_2.setBounds(83, 129, 37, 15);
        contentPane.add(label_2);

        JComboBox comboBox = new JComboBox();
        /**
         * 用循环快速初始化下来列表的值
         */
        for(int n=1;n<=100;n++) {
            comboBox.addItem(n+"岁");
        }
        comboBox.setBounds(133, 126, 197, 21);
        contentPane.add(comboBox);

        JLabel label_3 = new JLabel("\u6027\u522B");
        label_3.setBounds(83, 179, 37, 15);
        contentPane.add(label_3);

        JRadioButton radioButton = new JRadioButton("\u7537");
        radioButton.setBounds(154, 175, 62, 23);
        contentPane.add(radioButton);

        JRadioButton radioButton_1 = new JRadioButton("\u5973");
        radioButton_1.setBounds(268, 175, 62, 23);
        contentPane.add(radioButton_1);

        ButtonGroup  sex=new ButtonGroup();
        sex.add(radioButton);
        sex.add(radioButton_1);

        JButton button = new JButton("\u6CE8\u518C");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //1.先取出用户在注册界面上填写的内容
                String account=textField.getText();
                String password=passwordField.getText();
                String age=comboBox.getSelectedItem().toString();
                String  sex=radioButton.isSelected()?"男":"女";

                //2.封装一个消息对象，然后使用socket的输出流将注册的消息对象发送给服务器

                MessageConfig m=new MessageConfig();

                User user=new User(account,password,sex,age);//将要注册四个属性封装成一个独立的user对象

                m.setFrom(user);
                m.setType("register");;

                //3.将封装的消息使用socket中的输出流输出到服务器
                try {

                    out.writeObject(m);
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //4.消息发送出去之后这里就可以接受服务器给我们回过来的注册结果的消息
                try {
                    MessageConfig result=(MessageConfig)in.readObject();
                    if(result.getContent().equals("success")) {
                        JOptionPane.showMessageDialog(null, "注册成功!", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
                        //当注册成功之后除了弹框提示成功之外，可以主动打开登录界面
//                        LoginFrameTest loginTest = new LoginFrameTest();
//                        loginTest.start(new Stage());

                        RegistFrameTest.this.dispose();//当登录界面打开后，让注册界面隐藏

                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }catch (ClassNotFoundException e1){
                    e1.printStackTrace();
                }
            }
        });
        button.setBounds(95, 222, 93, 23);
        contentPane.add(button);

        JButton button_1 = new JButton("\u767B\u5F55");
        button_1.setBounds(237, 222, 93, 23);
        contentPane.add(button_1);
    }
}
