/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Siswa.InfoMentor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Sayid
 */
public class ControllerInfoMentor implements ActionListener{
    private InfoMentor view;
    private Database db;
    private String emails;
    private String emailm;
    
    public ControllerInfoMentor(String emails) throws SQLException {
        view = new InfoMentor();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
        this.emails = emails;
        setKata();
        setKata2();
    }
    
    public void setKata() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM SISWA WHERE EMAIL = '" + emails + "'");
        if (rs.next()) {
            this.emailm = rs.getString("emailmentor");
        }
    }
    
    public void setKata2() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM MENTOR WHERE EMAIL = '" + emailm + "'");
        if (rs.next()) {
            view.setJLabel4(rs.getString("nama"));
            view.setJLabel5(rs.getString("email"));
            view.setJLabel6(rs.getString("nohp"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnKembali())) {
            view.setVisible(false);
        }
    }
}
