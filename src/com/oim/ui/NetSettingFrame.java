/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui;

import com.oim.ui.swing.ColorFrame;
import java.net.URL;
import javafx.animation.Animation;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Only
 */
public class NetSettingFrame extends BaseFrame {
    
    ColorFrame colorFrame = new ColorFrame();
    
    public NetSettingFrame() {
        init();
    }
    
    private void init() {

        //AnchorPane rootPane = new AnchorPane();
        //rootPane.setId("bg");
        StackPane userPane1 = new StackPane();
        userPane1.setStyle("-fx-background-color:rgba(255, 255, 255, 0.2)");
        Node icoNode = createTile();
        
        userPane1.getChildren().add(icoNode);
        // rootPane.setPrefWidth(320);
        // rootPane.setPrefHeight(220);
        this.setBackground("Resources\\Images\\Wallpaper\\1.jpg");
        this.setCenter(userPane1);
        this.setTitle("登录");
        
        this.setWidth(740);
        this.setHeight(260);
        icoNode.setOnMouseClicked((MouseEvent me) -> {
            if (me.getClickCount() == 2) {
                colorFrame.setVisible(true);
            }
        });
    }
    
    
    public Node createTile() {
        URL url = NetSettingFrame.class.getResource("/resources/login/185_100.gif");
        
        ImageView imageView = new ImageView(new Image(url.toString()));
        Button tile = new Button("", imageView);
        //tile.setMinSize(140, 145);
        //tile.setPrefSize(140, 145);
        //tile.setMaxSize(140, 145);
        tile.setContentDisplay(ContentDisplay.TOP);
        tile.getStyleClass().clear();
        tile.getStyleClass().add("head-image-show");
        tile.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println(".handle()");
            }
        });
        return tile;
    }
    
    private Node getIcon() {
        URL url = NetSettingFrame.class.getResource("/resources/images/AlphaMediaPlayer.png");
        if (url != null) {
            ImageView imageView = new ImageView(new Image(url.toString()));
            return imageView;
        } else {
            ImageView imageView = new ImageView(new Image(NetSettingFrame.class.getResource("/resources/images/icon-overlay.png").toString()));
            imageView.setMouseTransparent(true);
            Rectangle overlayHighlight = new Rectangle(-8, -8, 130, 130);
            overlayHighlight.setFill(new LinearGradient(0, 0.5, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.web("#444444"))}));
            overlayHighlight.setOpacity(0.8);
            overlayHighlight.setMouseTransparent(true);
            overlayHighlight.setBlendMode(BlendMode.ADD);
            Rectangle background = new Rectangle(-8, -8, 130, 130);
            background.setFill(Color.web("#b9c0c5"));
            Group group = new Group(background);
            Rectangle clipRect = new Rectangle(114, 114);
            clipRect.setArcWidth(38);
            clipRect.setArcHeight(38);
            group.setClip(clipRect);
            Node content = createIconContent();
            if (content != null) {
                content.setTranslateX((int) ((114 - content.getBoundsInParent().getWidth()) / 2) - (int) content.getBoundsInParent().getMinX());
                content.setTranslateY((int) ((114 - content.getBoundsInParent().getHeight()) / 2) - (int) content.getBoundsInParent().getMinY());
                group.getChildren().add(content);
            }
            group.getChildren().addAll(overlayHighlight, imageView);
            // Wrap in extra group as clip dosn't effect layout without it
            return new Group(group);
        }
    }
    // REMOVE ME

    public static Node createIconContent() {
        Circle circle = new Circle(57, 57, 40);
        circle.setStroke(Color.web("#b9c0c5"));
        circle.setStrokeWidth(5);
        circle.getStrokeDashArray().addAll(15d, 15d);
        circle.setFill(null);
        javafx.scene.effect.InnerShadow effect = new javafx.scene.effect.InnerShadow();
        effect.setOffsetX(1);
        effect.setOffsetY(1);
        effect.setRadius(3);
        effect.setColor(Color.rgb(0, 0, 0, 0.6));
        circle.setEffect(effect);
        return circle;
    }
}
