/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oim.ui;

import com.only.fx.OnlyFrame;

/**
 *
 * @author Only
 */
public class BaseFrame extends OnlyFrame {

    public BaseFrame() {
        init();
    }

    private void init() {
        getRootPane().getStylesheets().add(this.getClass().getResource("/resources/common/css/base.css").toString());
    }
}
