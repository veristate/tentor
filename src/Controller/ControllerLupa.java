/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.LupaPass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sayid
 */
public class ControllerLupa implements ActionListener{
    private LupaPass view;
    private Database db;
    
    public ControllerLupa() throws SQLException {
        view = new LupaPass();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnCari())) {
            if (view.getRbSiswa()) {
                if (view.getEmailLupa().equals("")) {
                    view.setJLabel4("Email kosong");
                } else {
                    try {
                        ResultSet rs = db.query("SELECT PASSWORD FROM SISWA WHERE EMAIL = '" + view.getEmailLupa() + "';");
                        if (rs.next()) {
                            view.setJLabel3(rs.getString("password"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLupa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (view.getRbMentor()) {
                if (view.getEmailLupa().equals("")) {
                    view.setJLabel4("Email kosong");
                } else {
                    try {
                        ResultSet rs = db.query("SELECT PASSWORD FROM MENTOR WHERE EMAIL = '" + view.getEmailLupa() + "';");
                        if (rs.next()) {
                            view.setJLabel3(rs.getString("password"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLupa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                view.setJLabel4("Pilih siswa atau mentor");
            }
        } else if (s.equals(view.getBtnBatal())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerLupa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
