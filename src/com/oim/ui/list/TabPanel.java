/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui.list;

import com.fxexperience.javafx.animation.FadeInLeftTransition;
import com.fxexperience.javafx.animation.FadeInRightTransition;
import com.oim.common.box.ImageBox;
import com.oim.ui.main.Tab;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author XiaHui
 */
public class TabPanel extends VBox {

    private final HBox topBox = new HBox();
    private final VBox topRootBox = new VBox();
    private final VBox box = new VBox();
    private final Map<Tab, Node> map = new HashMap<Tab, Node>();
    private Tab tab;
    Timeline timeline = new Timeline();

    public TabPanel() {
        initComponent();
        iniEvent();
    }

    private void initComponent() {
        this.getChildren().add(topRootBox);
        this.getChildren().add(box);

        topBox.setPrefHeight(35);
        topBox.setMinHeight(35);

        Separator separator = new Separator();
        // separator.setOrientation(Orientation.HORIZONTAL);
        separator.setPrefHeight(1);
        separator.setMaxHeight(1);
        separator.setBorder(Border.EMPTY);
        //separator.setAlignment(Pos.CENTER_LEFT);
        // separator.getStyleClass().remove("line");
        //separator.r
        separator.setStyle("-fx-background-color:rgba(0, 0, 0, 0.3);");
        // separator.setBackground(Background.EMPTY);
        HBox line = new HBox();
        line.setMinHeight(1);
        line.setStyle("-fx-background-color:rgba(0, 0, 0, 0.1);");
        topRootBox.getChildren().add(topBox);
        topRootBox.getChildren().add(line);

    }

    private void iniEvent() {
    }

    public void add(Image normalImage, Image hoverImage, Image selectedImage, Node node) {
        Tab tabTemp = new Tab(normalImage, hoverImage, selectedImage);
        topBox.getChildren().add(tabTemp);
        HBox.setHgrow(tabTemp, Priority.ALWAYS);
        map.put(tabTemp, node);

        if (null == tab) {
            tabTemp.setSelected(true);
            tab = tabTemp;
            box.getChildren().add(node);
        }
//        tabTemp.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//               // System.out.println(".handle()");
//            }
//        });
        tabTemp.setOnMouseClicked((Event event) -> {
            tabOnMouseClicked(event);
        });

    }

    private void tabOnMouseClicked(Event event) {
        Object o = event.getSource();
        if (o instanceof Tab) {
            Tab tabTemp = (Tab) o;
            if (!tabTemp.equals(tab)) {
                int oldIndex = 0;
                int newIndex = topBox.getChildren().indexOf(o);

                if (null != tab) {
                    tab.setSelected(false);
                    oldIndex = topBox.getChildren().indexOf(tab);
                }
                Node node = map.get(tabTemp);
                if (!tabTemp.isSelected()) {
                    tabTemp.setSelected(true);
                    node.setVisible(false);
                    setSelectedNode(node);
//                    if (oldIndex < newIndex) {
//                        new FadeInRightBigTransition(node).play();
//                    } else {
//                        new FadeInLeftBigTransition(node).play();
//                    }

                    if (oldIndex < newIndex) {
                        new FadeInRightTransition(node).play();
                    } else {
                        new FadeInLeftTransition(node).play();
                    }

                    timeline.setCycleCount(1);
                    //timeline.setDelay(Duration.ONE);
                    KeyValue ky = new KeyValue(node.visibleProperty(), true);
                    KeyFrame kf = new KeyFrame(new Duration(230), ky);
                    timeline.getKeyFrames().add(kf);
                    timeline.play();
                    //node.setVisible(true);
                }
                tab = tabTemp;
            }
        }
    }

    private void setSelectedNode(Node node) {
        box.getChildren().clear();
        box.getChildren().add(node);
    }

    private void iniTest() {

        Image normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_normal.png");
        Image hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_hover.png");
        Image selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_contacts_selected.png");

        VBox rootVBox = new VBox();
        this.add(normalImage, hoverImage, selectedImage, rootVBox);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_group_selected.png");

        VBox box2 = new VBox();
        this.add(normalImage, hoverImage, selectedImage, box2);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/icon_last_selected.png");

        VBox box3 = new VBox();
        this.add(normalImage, hoverImage, selectedImage, box3);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/qzone_selected.png");

        VBox boxq = new VBox();

        this.add(normalImage, hoverImage, selectedImage, boxq);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_tab_inco_selected.png");

        VBox box4 = new VBox();

        this.add(normalImage, hoverImage, selectedImage, box4);

        normalImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_normal.png");
        hoverImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_hover.png");
        selectedImage = ImageBox.getImageClassPath("/resources/main/images/panel/main_panel_phone_inco_selected.png");

        VBox box5 = new VBox();

        this.add(normalImage, hoverImage, selectedImage, box5);
    }
}
