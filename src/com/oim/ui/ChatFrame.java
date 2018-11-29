package com.oim.ui;

import com.oim.common.util.MessageConfig;
import com.oim.common.util.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ChatFrame extends JFrame {
    private ObjectOutputStream out;
    private String user,my;
    private JTextArea textArea ;

    public JTextArea getTextArea() {
        return textArea;
    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatFrame frame = new ChatFrame("Test","Test2",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    /**
     * Create the frame.
     */
    public ChatFrame(String user,String my,ObjectOutputStream  out) {
        this.out=out;
        this.my=my;
        this.user=user;
        if(user.equals("群聊")) {
            setTitle("群聊");
        }else {
            setTitle("和"+user+"聊天中");
        }
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 528, 484);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 502, 244);
        contentPane.add(scrollPane);

        textArea= new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 279, 502, 114);
        contentPane.add(scrollPane_1);

        JTextArea textArea_1 = new JTextArea();
        scrollPane_1.setViewportView(textArea_1);

        JButton button = new JButton("\u53D1\u9001");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //1.先提取用户编辑好的消息，然后将消息显示在上面的界面上
                String m=textArea_1.getText();
                textArea_1.setText("");
                textArea.append("["+my+"]\t"+new Date().toLocaleString()+"\r\n   "+m+"\r\n\r\n");
                textArea.setCaretPosition(textArea.getText().length());
                //2.将消息发送到服务器端，让服务器转发给对应的用户
                MessageConfig  message=new MessageConfig();
                message.setContent(m);
                message.setFrom(new User(my));
                message.setTo(new User(user));
                if(user.equals("群聊")) {
                    message.setType("groupchat");
                }else {
                    message.setType("chat");
                }

                try {
                    out.writeObject(message);
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        button.setBounds(262, 408, 93, 23);
        contentPane.add(button);

        JButton button_1 = new JButton("\u5173\u95ED");
        button_1.setBounds(385, 408, 93, 23);
        contentPane.add(button_1);
    }
}

