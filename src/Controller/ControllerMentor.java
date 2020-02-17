/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Mentor.MenuMentor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sayid
 */
public class ControllerMentor implements ActionListener{
    private MenuMentor view;
    private Database db;
    private String emailLogin;
    
    public ControllerMentor(String s) throws SQLException {
        view = new MenuMentor();
        view.setVisible(true);
        view.addActionListener(this);
        db = new Database();
        this.emailLogin = s;
        setNama();
    }
    
    public void setNama() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM MENTOR WHERE EMAIL = '" + emailLogin + "';");
        if (rs.next()) {
            view.setJLabel2(rs.getString("nama"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnLogout())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMentor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnInfo())) {
            try {
                new ControllerInfoMurid(emailLogin);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMentor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnElearning())) {
            try {
                new ControllerElearningMentor();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMentor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
