/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Mentor.InfoMurid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Sayid
 */
public class ControllerInfoMurid implements ActionListener{
    private InfoMurid view;
    private Database db;
    private String emailLogin;
    
    public ControllerInfoMurid(String s) throws SQLException {
        view = new InfoMurid();
        view.setVisible(true);
        view.addActionListener(this);
        db = new Database();
        this.emailLogin = s;
        setNama();
    }
    
    public void setNama() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM SISWA WHERE EMAILMENTOR = '" + emailLogin + "';");
        if (rs.next()) {
            view.setJLabel5(rs.getString("nama"));
            view.setJLabel6(rs.getString("email"));
            view.setJLabel7(rs.getString("nohp"));
            view.setJLabel8(rs.getString("kelas"));
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
