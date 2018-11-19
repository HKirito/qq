/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui;

import com.oim.common.box.ImageBox;
import com.oim.ui.component.IconButton;
import com.oim.ui.list.HeadItem;
import com.oim.ui.list.ListNodePanel;
import com.oim.ui.list.ListRootPanel;
import com.oim.ui.list.TabPanel;
import com.oim.ui.main.UserDataPanel;
import java.util.Random;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Only
 */
public class MainFrame extends BaseFrame {

    BorderPane borderPane = new BorderPane();

    VBox baseBox = new VBox();
    UserDataPanel userDataPanel = new UserDataPanel();
    HBox titleBox = new HBox();

    VBox centerBox = new VBox();
    HBox searchBox = new HBox();
    TabPanel tabPanel = new TabPanel();

    HBox appBox = new HBox();
    HBox rightAppBox = new HBox();
    HBox appRootBox = new HBox();

    HBox rightFunctionBox = new HBox();
    HBox functionBox = new HBox();
    HBox functionRootBox = new HBox();

    VBox bottomBox = new VBox();

    public MainFrame() {
        initComponent();
        initTest();
        initUserList();
    }

    /**
     * 初始化布局排版和各个组件
     */
    private void initComponent() {
        this.setBackground("Resources/Images/Wallpaper/14.jpg");
        this.setTitle("Test");
        this.setTitlePaneStyle(2);
        this.setWidth(290);
        this.setHeight(630);
        this.setMinWidth(290);
        this.setMinHeight(530);
        this.setMaxWidth(610);
        this.setRadius(5);
        this.setCenter(borderPane);

        //Image logoIamge = new Image(this.getClass().getResource("/resources/main/images/logo.png").toExternalForm(), true);
        Image logoIamge = new Image(this.getClass().getResource("/resources/main/images/QQlogoCover.png").toExternalForm(), true);
        ImageView logoImageView = new ImageView();

        logoImageView.setImage(logoIamge);
        Label titleLabel = new Label("", logoImageView);
        titleLabel.setStyle("-fx-text-fill: white;");
        titleBox.setPrefHeight(30);

        titleBox.getChildren().add(this.getGapNode(10));
        titleBox.getChildren().add(titleLabel);

        baseBox.getChildren().add(this.getGapNode(5));
        baseBox.getChildren().add(titleBox);
        baseBox.getChildren().add(userDataPanel);

        borderPane.setTop(baseBox);
        borderPane.setCenter(centerBox);
        borderPane.setBottom(bottomBox);

        centerBox.getChildren().add(searchBox);
        centerBox.getChildren().add(tabPanel);
        VBox.setVgrow(tabPanel, Priority.ALWAYS);

        TextField searchField = new TextField();
        searchField.setPromptText("搜索：联系人、多人聊天、群、企业");
        searchField.getStyleClass().remove("text-field");
        searchField.setBackground(Background.EMPTY);
        searchField.setMinHeight(30);
        searchField.setStyle("-fx-prompt-text-fill:#000000;");

        Image image = ImageBox.getImageClassPath("/resources/main/images/panel/find_down.png");
        ImageView headImageView = new ImageView();
        headImageView.setImage(image);

        searchBox.getChildren().add(searchField);
        searchBox.getChildren().add(headImageView);
        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        searchBox.setStyle("-fx-background-color:rgba(0, 0, 0, 0.3)");

        tabPanel.setStyle("-fx-background-color:rgba(230, 230, 230, 0.6)");

        appBox.setSpacing(3);
        functionBox.setSpacing(3);

        appRootBox.setSpacing(3);
        functionBox.setSpacing(3);

        appRootBox.setAlignment(Pos.CENTER_RIGHT);
        functionRootBox.setAlignment(Pos.CENTER_RIGHT);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setPrefHeight(1);

        VBox appTempBox = new VBox();
        appTempBox.getChildren().add(appBox);

        appRootBox.getChildren().add(this.getGapNode(5));
        appRootBox.getChildren().add(appTempBox);
        appRootBox.getChildren().add(rightAppBox);
        appRootBox.getChildren().add(this.getGapNode(2));

        functionRootBox.getChildren().add(this.getGapNode(5));
        functionRootBox.getChildren().add(functionBox);
        functionRootBox.getChildren().add(rightFunctionBox);
        functionRootBox.getChildren().add(this.getGapNode(3));

        HBox.setHgrow(appTempBox, Priority.ALWAYS);
        HBox.setHgrow(functionBox, Priority.ALWAYS);

        AnchorPane gapPane = new AnchorPane();
        gapPane.setPrefHeight(5);

        bottomBox.getChildren().add(separator);
        bottomBox.getChildren().add(gapPane);
        bottomBox.getChildren().add(appRootBox);
        bottomBox.getChildren().add(functionRootBox);
        bottomBox.getChildren().add(this.getGapNode(1));

        bottomBox.setStyle("-fx-background-color:rgba(230, 230, 230, 0.7)");

    }

    public void setHeadImage(Image headImage) {
        userDataPanel.setHeadImage(headImage);
    }

    public void setStatusImage(Image statusImage) {
        userDataPanel.setStatusImage(statusImage);
    }

    public void setNickname(String nickname) {
        this.userDataPanel.setNickname(nickname);
    }

    public void setText(String text) {
        this.userDataPanel.setText(text);
    }

    public void addBusinessIcon(Node node) {
        userDataPanel.addBusinessIcon(node);
    }

    public void removeBusinessIcon(Node node) {
        userDataPanel.removeBusinessIcon(node);
    }

    public void addAppIcon(Node node) {
        appBox.getChildren().add(node);
    }

    public void addFunctionIcon(Node node) {
        functionBox.getChildren().add(node);
    }

    public void addRightAppIcon(Node node) {
        rightAppBox.getChildren().add(node);
    }

    public void addRightFunctionIcon(Node node) {
        rightFunctionBox.getChildren().add(node);
    }

    public void addTab(Image normalImage, Image hoverImage, Image selectedImage, Node node) {
        tabPanel.add(normalImage, hoverImage, selectedImage, node);
    }

    /**
     * 测试数据
     */
    private void initTest() {
        ///////////////////////////////////////用户信息
        Image statusImage = ImageBox.getImageClassPath("/resources/common/images/status/invisible.png");
        Image headImage = ImageBox.getImagePath("Resources/Images/Head/User/85_100.gif", 60, 60);
        this.setHeadImage(headImage);
        this.setStatusImage(statusImage);
        this.setNickname("华农兄弟");
        this.setText("我看你好像中暑了，不如。。。");

        Image businessImage = ImageBox.getImageClassPath("/resources/main/images/top/1.png");

        IconButton iconButton = new IconButton(businessImage);
        this.addBusinessIcon(iconButton);

        businessImage = ImageBox.getImageClassPath("/resources/main/images/top/2.png");

        iconButton = new IconButton(businessImage);
        this.addBusinessIcon(iconButton);

        businessImage = ImageBox.getImageClassPath("/resources/main/images/top/3.png");

        iconButton = new IconButton(businessImage);
        this.addBusinessIcon(iconButton);

        businessImage = ImageBox.getImageClassPath("/resources/main/images/top/4.png");

        iconButton = new IconButton(businessImage);
        this.addBusinessIcon(iconButton);

        businessImage = ImageBox.getImageClassPath("/resources/main/images/top/5.png");

        iconButton = new IconButton(businessImage);
        this.addBusinessIcon(iconButton);

        /////////////////////////////////////////////function
        Image normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/menu_btn_normal.png");
        Image hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/menu_btn2_down.png");
        Image pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/menu_btn_highlight.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/tools.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/tools_hover.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/tools_down.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/message.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/message_highlight.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/message_down.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/filemanager.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/filemanager_hover.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/filemanager_down.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/mycollection_mainpanel.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/myCollection_mainpanel_hover.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/myCollection_mainpanel_down.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/find.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/find_hover.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/find_down.png");

        iconButton = new IconButton("查找", normalImage, hoverImage, pressedImage);
        this.addFunctionIcon(iconButton);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/tools.png");

        iconButton = new IconButton("应用宝", normalImage);
        this.addRightFunctionIcon(iconButton);

        /////////////////////////////////////////////////////////////////////app
        normalImage = ImageBox.getImageClassPath("/resources/main/images/bottom/appbox_mgr_btn.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/bottom/appbox_mgr_btn_hover.png");
        pressedImage = ImageBox.getImageClassPath("/resources/main/images/bottom/appbox_mgr_btn_down.png");

        iconButton = new IconButton(normalImage, hoverImage, pressedImage);
        this.addRightAppIcon(iconButton);

        Image appImage = ImageBox.getImageClassPath("/resources/main/images/app/1.png");

        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/2.png");

        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/3.png");

        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/7.png");
        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/8.png");
        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/9.png");
        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/10.png");
        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

        appImage = ImageBox.getImageClassPath("/resources/main/images/app/11.png");
        iconButton = new IconButton(appImage);
        this.addAppIcon(iconButton);

    }

    private void initUserList() {
        Random random = new Random();

        Image normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_normal.png");
        Image hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_hover.png");
        Image selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_selected.png");
        ListRootPanel userList = new ListRootPanel();
        this.addTab(normalImage, hoverImage, selectedImage, userList);

        ListNodePanel[] teamNode = new ListNodePanel[5];
        for (int j = 0; j < 5; j++) {
            teamNode[j] = new ListNodePanel();
            teamNode[j].setText("Friend " + j);

            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(100) + 1;
                HeadItem head = new HeadItem();
                Image image = ImageBox.getImagePath("Resources/Images/Head/User/" + index + ".png", 40, 40);
                head.setHeadImage(image);
                head.setRemark("竹鼠 " + (j + 1));
                head.setNickname("(竹鼠)");
                head.setStatus("[4G]");
                head.setShowText("今天华农兄弟又抓走一个，下一个会不会是我啊。。。。");

                Image iconImage = ImageBox.getImagePath("Resources/Images/Default/Status/FLAG/Big/imonline.png");
                IconButton iconButton = new IconButton(iconImage);
                head.addBusinessIcon(iconButton);
                head.setOnMouseClicked((MouseEvent me) -> {
                    if (!head.isFocused()) {
                        head.requestFocus();
                    }
                    if (me.getClickCount() == 2) {
                        head.setPulse(!head.isPulse());
                    }
                });
                teamNode[j].addItem(head);
            }
            userList.addNode(teamNode[j]);
        }

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_selected.png");

        ListRootPanel groupRoot = new ListRootPanel();
        this.addTab(normalImage, hoverImage, selectedImage, groupRoot);

        ListNodePanel[] groupNode = new ListNodePanel[5];
        for (int j = 0; j < 5; j++) {
            groupNode[j] = new ListNodePanel();
            groupNode[j].setText("我的群" + j);
            groupNode[j].setNumberText("[5]");
            for (int i = 0; i < 5; i++) {

                int index = random.nextInt(100) + 1;
                HeadItem head = new HeadItem();
                Image image = ImageBox.getImagePath("Resources/Images/Head/User/" + index + ".png", 40, 40);
                head.setHeadImage(image);

                head.setRemark("女神经" + (j + 1));
                head.setNickname("(哈加额)");
                head.setStatus("[2G]");
                head.setShowText("哈哈哈，又有新闻了");
                Image iconImage = ImageBox.getImagePath("Resources/Images/Default/Status/FLAG/Big/imonline.png");
                IconButton iconButton = new IconButton(iconImage);
                head.addBusinessIcon(iconButton);

                groupNode[j].addItem(head);
            }
            groupRoot.addNode(groupNode[j]);
        }
//
        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_selected.png");

        ListRootPanel lastRoot = new ListRootPanel();
        this.addTab(normalImage, hoverImage, selectedImage, lastRoot);

        for (int j = 0; j < 15; j++) {

            HeadItem head = new HeadItem();
            Image image = ImageBox.getImagePath("Resources/Images/Head/User/" + (j + 1) + ".png", 40, 40);

            head.setHeadImage(image);

            head.setRemark("女神经" + (j + 1));
            head.setNickname("(哈加额)");
            head.setStatus("[2G]");
            head.setShowText("哈哈哈，又有新闻了");
            Image iconImage = ImageBox.getImagePath("Resources/Images/Default/Status/FLAG/Big/imonline.png");
            IconButton iconButton = new IconButton(iconImage);
            head.addBusinessIcon(iconButton);

            lastRoot.addNode(head);
        }
//
        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_selected.png");

        VBox boxq = new VBox();

//        webEngine.load("http://www.qq.com");
//        boxq.getChildren().add(webView);
        boxq.getChildren().add(new Button("我的空间"));
        boxq.setStyle("-fx-background-color:rgba(44, 123, 245, 1)");
        this.addTab(normalImage, hoverImage, selectedImage, boxq);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_selected.png");

        VBox box4 = new VBox();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.oschina.net/code/snippet_935786_52805");
        box4.getChildren().add(webView);
        box4.setStyle("-fx-background-color:rgba(215, 165, 230, 1)");
        this.addTab(normalImage, hoverImage, selectedImage, box4);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_selected.png");

        VBox box5 = new VBox();
//        webView = new WebView();
//        webEngine = webView.getEngine();
//        webEngine.load("http://download.csdn.net/detail/onlyxiahui/9434701");
        box5.getChildren().add(new Button("我的手机"));
        box5.setStyle("-fx-background-color:rgba(112, 245, 86, 1);");
        this.addTab(normalImage, hoverImage, selectedImage, box5);
    }
}
