package com.oim.ui;

import com.oim.common.util.Config;
import com.oim.common.util.MessageConfig;
import com.oim.common.util.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerTest {
    private ServerSocket server;

    private Map<String, ObjectOutputStream> allClients = new HashMap<>();

    public ServerTest(){
        try{
            server = new ServerSocket(Config.port);
            System.out.println("服务器创建成功!");

            while (true){
                Socket client =server.accept();
                System.out.println(client.getInetAddress().getHostAddress()+" Connecting !");
                ClientThread  ct=new ClientThread(client);
                ct.start();

            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    class ClientThread extends Thread{
        private Socket client;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClientThread(Socket client){
            super();
            this.client = client;
            try {
                in = new ObjectInputStream(client.getInputStream());
                out = new ObjectOutputStream(client.getOutputStream());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        @Override
        public void run(){
            while(true){
                try {
                    MessageConfig m=(MessageConfig)in.readObject();//这里是读取这个通道（socket）发送过来的消息
                    if(m.getType().equals("register")) {
                        //这是如果接收到的消息类型是注册消息，应该执行这里注册的代码

                        //‘所谓的存储到数据库，本次项目是采用将注册的用户对象通过序列化的方式存储到磁盘上’
                        ObjectOutputStream  xuliehua=new ObjectOutputStream(new FileOutputStream("E:\\idea workspace\\qq\\qq\\Resources\\"+m.getFrom().getUsername()+".data"));
                        xuliehua.writeObject(m.getFrom());
                        xuliehua.close();
                        System.out.println("存储成功");

                        //当服务端将注册的信息存储成功后，应该给该客户端发送一条成功的消息
                        MessageConfig reigsterResult=new MessageConfig();
                        reigsterResult.setContent("success");

                        out.writeObject(reigsterResult);

                        out.flush();
                    }else if(m.getType().equals("login")) {
                        System.out.println("接收到的登录信息如下："+m);

                        //服务端接收到消息之后，查询数据库判断该用户是否存在

                        File  f=new File("E:\\idea workspace\\qq\\qq\\Resources\\"+m.getFrom().getUsername()+".data");
                        MessageConfig result=new MessageConfig();
                        if(f.exists()) {
                            ObjectInputStream  in=new ObjectInputStream(new FileInputStream(f));
                            User user=(User)in.readObject();
                            if(m.getFrom().getUsername().equals(user.getUsername())&&m.getFrom().getPassword().equals(user.getPassword())) {
                                result.setContent("success");

                                //如果这个socket连接进来的用户登录的账户密码成功，则应该讲这个账号和它使用的底层输出流存入到集合中，方便后期能找到对应账号的流从而给该用户输出数据
                                allClients.put(m.getFrom().getUsername(), out);
                            }else {
                                result.setContent("fail");
                            }
                        }else {
                            result.setContent("fail");
                        }
                        out.writeObject(result);
                        out.flush();

                    }else if(m.getType().equals("chat")) {
                        //服务器接收到某一个用户的聊天消息后应该做的事情
                        System.out.println(m);
                        //遍历之前存储所有用户的集合，找到接收人的账号对应的输出流，将数据使用对应的输出流写给接收人

                        for(String username:allClients.keySet()) {
                            if(username.equals(m.getTo().getUsername())) {
                                allClients.get(username).writeObject(m);
                                allClients.get(username).flush();
                                break;
                            }
                        }

                    }else if(m.getType().equals("groupchat")) {
                        //群聊就是所有人都推送消息，那么就需要遍历服务器里存储的所有登录用户的集合，把消息发给所有登录的用户
                        for(String username:allClients.keySet()) {
                            allClients.get(username).writeObject(m);
                            allClients.get(username).flush();
                        }
                    }else if(m.getType().equals("loadData")) {
                        //有一个客户端登陆成功了，加载了主界面，而主界面发送一个请求过来要求获取用户列表
                        StringBuffer  allUsers=new StringBuffer();
                        File  dataDir=new File("files");
                        String[] childs=dataDir.list();
                        for(String username:childs) {
                            allUsers.append(username.replace(".data", "")+",");
                        }
                        System.out.println(allUsers.toString());

                        MessageConfig allUsersMessage=new MessageConfig();
                        allUsersMessage.setContent(allUsers.toString());
                        out.writeObject(allUsersMessage);
                        out.flush();
                    }else {
                        ServerTest.this.wait(5000);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] arg){
        ServerTest s = new ServerTest();
    }
}
