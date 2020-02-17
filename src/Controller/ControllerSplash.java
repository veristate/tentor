/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.SplashScreen;

/**
 *
 * @author Sayid
 */
public class ControllerSplash {
        private SplashScreen view;
        
        public ControllerSplash() throws InterruptedException {
            view = new SplashScreen();
            view.setVisible(true);
            Thread.sleep(2000);
            delay();
        }
        
        public void delay() {
            view.setVisible(false);
            new ControllerMenuAwal();
        }
}
