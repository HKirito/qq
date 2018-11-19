/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui;

import com.oim.common.box.ImageBox;
import com.only.fx.TitlePane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

/**
 * 登录窗口
 *
 * @author XiaHui
 */
public class LoginFrame extends BaseFrame {

    private DropShadow dropShadow = new DropShadow();

    private Button loginButton = new Button();//登录按钮
    private Button settingButton = new Button();//顶部设置按钮
    private TextField accountField = new TextField();//账号输入框
    private PasswordField passwordField = new PasswordField();

    private Button headButton = new Button();

    private CheckBox rememberCheckBox = new CheckBox();
    private CheckBox autoCheckBox = new CheckBox();

    private Label registerLabel = new Label();
    private Label forgetLabel = new Label();

    private Button addAccountButton = new Button();
    private Button towCodeButton = new Button();

    public LoginFrame() {
        initComponent();
        iniEvent();
    }

    private void initComponent() {
        this.setTitle("登录");
        this.setResizable(false);
        this.setWidth(445);
        this.setHeight(345);
        this.setTitlePaneStyle(2);
        this.setRadius(5);
        VBox rootBox = new VBox();
        this.setCenter(rootBox);
        this.getScene().getStylesheets().add(this.getClass().getResource("/resources/login/css/login.css").toString());
        this.getIcons().clear();
        
        rootBox.setBackground(Background.EMPTY);

        settingButton.setId("setting-button");
        settingButton.setPrefWidth(30);
        settingButton.setPrefHeight(27);

        TitlePane titlePane = getTitlePane();
        titlePane.getChildren().add(0, settingButton);

        AnchorPane backgroundRootPane = new AnchorPane();
        backgroundRootPane.setBackground(Background.EMPTY);
        backgroundRootPane.setPrefWidth(430);
        backgroundRootPane.setPrefHeight(180);

        AnchorPane backgroundPane = new AnchorPane();
        backgroundPane.setBackground(Background.EMPTY);
        backgroundPane.setPrefWidth(430);
        backgroundPane.setPrefHeight(180);

        Image logoIamge = new Image(this.getClass().getResource("/resources/login/logo.png").toExternalForm(), true);
        ImageView logoImageView = new ImageView();

        logoImageView.setImage(logoIamge);
        logoImageView.setLayoutX(125);
        logoImageView.setLayoutY(50);
        logoImageView.setEffect(dropShadow);

        backgroundPane.getChildren().add(logoImageView);

        WebView webView = new WebView();
        webView.setPrefSize(430, 180);

        WebEngine webEngine = webView.getEngine();
        webEngine.load(this.getClass().getResource("/resources/login/html/index.html").toString());

        backgroundRootPane.getChildren().add(webView);
        backgroundRootPane.getChildren().add(backgroundPane);

        AnchorPane userPane = new AnchorPane();
        userPane.setPrefWidth(428);
        userPane.setPrefHeight(150);
        userPane.setStyle("-fx-background-color:#ebf2f9;");

        accountField.getStyleClass().remove("text-field");
        passwordField.getStyleClass().remove("text-field");

        accountField.setBackground(Background.EMPTY);
        passwordField.setBackground(Background.EMPTY);

        accountField.setBorder(Border.EMPTY);
        passwordField.setBorder(Border.EMPTY);

        accountField.setPromptText("账号");
        passwordField.setPromptText("密码");

        accountField.setPrefWidth(170);
        passwordField.setPrefWidth(170);

        accountField.setPrefHeight(30);
        passwordField.setPrefHeight(29);

        loginButton.setText("登  录");
        loginButton.setId("login-button");
        loginButton.setLayoutX(135);
        loginButton.setLayoutY(107);
        loginButton.setPrefHeight(30);
        loginButton.setPrefWidth(194);
        //TODO

        rememberCheckBox.setText("记住密码");
        autoCheckBox.setText("自动登录");

        autoCheckBox.setPrefHeight(15);

        rememberCheckBox.setLayoutX(136);
        rememberCheckBox.setLayoutY(80);

        autoCheckBox.setLayoutX(260);
        autoCheckBox.setLayoutY(80);

        registerLabel.setText("注册账号");
        forgetLabel.setText("忘记密码");

        registerLabel.setLayoutX(340);
        registerLabel.setLayoutY(20);
        forgetLabel.setLayoutX(340);
        forgetLabel.setLayoutY(50);

        registerLabel.setCursor(Cursor.HAND);
        forgetLabel.setCursor(Cursor.HAND);

        registerLabel.setStyle("-fx-text-fill: #55A8E7;");
        forgetLabel.setStyle("-fx-text-fill: #55A8E7;");

        addAccountButton.setId("add-account-button");
        towCodeButton.setId("tow-code-button");

        addAccountButton.setLayoutX(10);
        addAccountButton.setLayoutY(120);
        addAccountButton.setPrefHeight(24);
        addAccountButton.setPrefWidth(24);

        towCodeButton.setLayoutX(398);
        towCodeButton.setLayoutY(120);
        towCodeButton.setPrefHeight(24);
        towCodeButton.setPrefWidth(24);

        Button accountButton = new Button();
        accountButton.setId("combo-box-button");
        accountButton.setPrefHeight(20);
        accountButton.setPrefWidth(20);
        accountButton.setLayoutX(170);
        accountButton.setLayoutY(4);

        AnchorPane accountPane = new AnchorPane();
        accountPane.setId("account-input");
        accountPane.setLayoutX(135);
        accountPane.setLayoutY(15);
        accountPane.setPrefWidth(194);
        accountPane.getChildren().add(accountField);
        accountPane.getChildren().add(accountButton);

        AnchorPane passwordButton = new AnchorPane();
        passwordButton.setId("password-button");
        passwordButton.setPrefHeight(16);
        passwordButton.setPrefWidth(15);
        passwordButton.setPrefSize(15, 15);
        passwordButton.setLayoutX(173);
        passwordButton.setLayoutY(6);

        AnchorPane passwordPane = new AnchorPane();
        passwordPane.setId("password-input");
        passwordPane.setLayoutX(135);
        passwordPane.setLayoutY(44);
        passwordPane.setPrefWidth(194);
        passwordPane.setPrefHeight(30);
        passwordPane.getChildren().add(passwordField);
        passwordPane.getChildren().add(passwordButton);

        accountPane.setOnMouseEntered((Event event) -> {
            accountPane.toFront();
        });

        passwordPane.setOnMouseEntered((Event event) -> {
            passwordPane.toFront();
        });

        userPane.getChildren().addAll(accountPane, passwordPane);
        userPane.getChildren().addAll(loginButton);

        userPane.getChildren().addAll(rememberCheckBox);
        userPane.getChildren().addAll(autoCheckBox);

        userPane.getChildren().addAll(addAccountButton);
        userPane.getChildren().addAll(towCodeButton);

        userPane.getChildren().addAll(registerLabel);
        userPane.getChildren().addAll(forgetLabel);

        Image statusImage = new Image(this.getClass().getResource("/resources/common/images/status/imonline.png").toExternalForm(), true);
        ImageView statusImageView = new ImageView();
        statusImageView.setImage(statusImage);
        statusImageView.setLayoutY(3);
        statusImageView.setLayoutX(3);

        AnchorPane statusButton = new AnchorPane();
        statusButton.setId("status-button");
        statusButton.setPrefHeight(13);
        statusButton.setPrefWidth(13);
        statusButton.setLayoutX(62);
        statusButton.setLayoutY(62);

        statusButton.getChildren().add(statusImageView);

        Image image = ImageBox.getImagePath("Resources/Images/Head/User/1_100.gif", 80, 80);

        Rectangle clip = new Rectangle();
        clip.setArcHeight(8);
        clip.setArcWidth(8);

        clip.setWidth(80);
        clip.setHeight(80);

        ImageView imageHeadView = new ImageView();
//        imageHeadView.setLayoutY(15);
//        imageHeadView.setLayoutX(40);
        imageHeadView.getStyleClass().add("image-head-view");
        imageHeadView.setClip(clip);

        imageHeadView.setImage(image);

        AnchorPane headPane = new AnchorPane();

        // headPane.getStyleClass().add("head-image-show");
        headPane.setLayoutY(15);
        headPane.setLayoutX(40);

        headPane.getChildren().add(imageHeadView);
        headPane.getChildren().add(statusButton);

        userPane.getChildren().add(headPane);

        headButton.setGraphic(imageHeadView);
        headButton.setPrefWidth(80);
        headButton.setPrefHeight(80);

        rootBox.getChildren().add(backgroundRootPane);
        rootBox.getChildren().add(userPane);

        ContextMenu menu = new ContextMenu();
        Image i = new Image(this.getClass().getResource("/resources/common/images/status/imonline.png").toExternalForm(), true);
        ImageView iv = new ImageView();
        iv.setImage(i);

        MenuItem menuItem = new MenuItem("我在线上", iv);
        menu.getItems().add(menuItem);

        i = new Image(this.getClass().getResource("/resources/common/images/status/Qme.png").toExternalForm(), true);
        iv = new ImageView();
        iv.setImage(i);
        menuItem = new MenuItem("Q我吧", iv);
        menu.getItems().add(menuItem);

        i = new Image(this.getClass().getResource("/resources/common/images/status/away.png").toExternalForm(), true);
        iv = new ImageView();
        iv.setImage(i);
        menuItem = new MenuItem("离开", iv);
        menu.getItems().add(menuItem);

        i = new Image(this.getClass().getResource("/resources/common/images/status/busy.png").toExternalForm(), true);
        iv = new ImageView();
        iv.setImage(i);
        menuItem = new MenuItem("忙碌", iv);
        menu.getItems().add(menuItem);

        i = new Image(this.getClass().getResource("/resources/common/images/status/mute.png").toExternalForm(), true);
        iv = new ImageView();
        iv.setImage(i);
        menuItem = new MenuItem("请勿打扰", iv);
        menu.getItems().add(menuItem);

        i = new Image(this.getClass().getResource("/resources/common/images/status/invisible.png").toExternalForm(), true);
        iv = new ImageView();
        iv.setImage(i);
        menuItem = new MenuItem("隐身", iv);
        menu.getItems().add(menuItem);

        statusButton.setOnMouseClicked((Event event) -> {
            menu.show(LoginFrame.this,LoginFrame.this.getX() + 110, LoginFrame.this.getY() + 280);
            menu.show(statusButton, LoginFrame.this.getX() + 110, LoginFrame.this.getY() + 280);
        });
    }

    private void iniEvent() {

    }
}
