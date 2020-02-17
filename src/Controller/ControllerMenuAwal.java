/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MenuAwal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sayid
 */
public class ControllerMenuAwal implements ActionListener{
    private MenuAwal view;
    
    public ControllerMenuAwal() {
        view = new MenuAwal();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnLogin())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenuAwal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnFAQ())) {
            new ControllerFAQ();
        }
    }
}
