/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui.list;

import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 *
 * @author XiaHui
 */
public class ListRootPanel extends VBox {

    VBox box = new VBox();
    ScrollPane scrollPane = new ScrollPane();

    public ListRootPanel() {
        initComponent();
        iniEvent();
    }

    private void initComponent() {
        this.getChildren().add(scrollPane);

        scrollPane.setBackground(Background.EMPTY);
        //scrollPane.setVvalue(20);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(box);
        scrollPane.widthProperty().addListener((Observable observable) -> {
            box.setPrefWidth(scrollPane.getWidth());
        });
    }

    private void iniEvent() {
    }

    public void addNode(Node node) {
        box.getChildren().add(node);
    }

    public void removeNode(Node node) {
        box.getChildren().remove(node);
    }
}
