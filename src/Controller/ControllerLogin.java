/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sayid
 */
public class ControllerLogin implements ActionListener{
    private Login view;
    private Database db;

    public ControllerLogin() throws SQLException {
        view = new Login();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnLogin())) {
            if (view.getEmail().equals("admin") && (view.getPassword().equals("admin"))) {
                view.setVisible(false);
                try {
                    new ControllerAdmin();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (view.getRbMentor()) {
                try {
                    ResultSet rs = db.query("SELECT * FROM MENTOR WHERE EMAIL ='" + view.getEmail() + "' AND PASSWORD ='" + view.getPassword() + "';");
                    if (view.getEmail().equals("") || view.getPassword().equals("")) {
                        view.setJLabel5Text("Text field kosong");
                    } else if (rs.next()) {
                        view.setVisible(false);
                        new ControllerMentor(view.getEmail());
                    } else {
                        view.setJLabel5Text("Mentor tidak ada");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (view.getRbSiswa()) {
                try {
                    ResultSet rs = db.query("SELECT * FROM SISWA WHERE EMAIL ='" + view.getEmail() + "' AND PASSWORD ='" + view.getPassword() + "';");
                    if (view.getEmail().equals("") || view.getPassword().equals("")) {
                        view.setJLabel5Text("Text field kosong");
                    } else if (rs.next()) {
                        view.setVisible(false);
                        new ControllerSiswa(view.getEmail());
                    } else {
                        view.setJLabel5Text("Siswa tidak ada");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                view.setJLabel5Text("Pilih mentor atau siswa");
            }
        } else if (s.equals(view.getBtnLupa())) {
            view.setVisible(false);
            try {
                new ControllerLupa();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnRMentor())) {
            view.setVisible(false);
            try {
                new ControllerRMentor();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnRSiswa())) {
            view.setVisible(false);
            try {
                new ControllerRSiswa();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
