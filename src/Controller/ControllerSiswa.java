/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Siswa.MenuSiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sayid
 */
public class ControllerSiswa implements ActionListener{
    private MenuSiswa view;
    private Database db;
    private String emailLogin;

    public ControllerSiswa(String emailLogin) throws SQLException {
        view = new MenuSiswa();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
        this.emailLogin = emailLogin;
        setNama();
        setTabelMentor();
    }
    
    public void setNama() throws SQLException {
        ResultSet rs = db.query("SELECT NAMA FROM SISWA WHERE EMAIL = '" + emailLogin + "';");
        if (rs.next()) {
            view.setJLabel2(rs.getString("nama"));
        }
    }
            
    public void setTabelMentor() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM MENTOR WHERE JADWAL1 = 'kosong';");
        DefaultTableModel model = (DefaultTableModel) view.getTabelMentor().getModel();
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("nama"), rs.getString("jadwal1"), rs.getString("email")});
        } view.getTabelMentor().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnLogout())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerSiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnInfo())) {
            try {
                new ControllerInfoMentor(emailLogin);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerSiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnPilih())) {
            int i = view.getTabelMentor().getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) view.getTabelMentor().getModel();
            String selected = model.getValueAt(i, 2).toString();
            try {
                db.update("UPDATE SISWA SET EMAILMENTOR = '" + selected + "' WHERE EMAIL = '" + emailLogin + "';");
                db.update("UPDATE MENTOR SET JADWAL1 = 'penuh' WHERE EMAIL = '" + selected + "';");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                setTabelMentor();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerSiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnElearning())) {
            try {
                new ControllerElearningSiswa();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerSiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
