/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;


import com.oim.ui.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author kirito
 */
public class MianFrameTest extends Application {

    @Override
    public void start(Stage primaryStage) {
       new MainFrame().show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
