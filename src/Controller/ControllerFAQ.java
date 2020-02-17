/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.FAQ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sayid
 */
public class ControllerFAQ implements ActionListener{
    private FAQ view;
    
    public ControllerFAQ() {
        view = new FAQ();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnKembali())) {
            view.setVisible(false);
        }
    }
}
