/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.MenuAdmin;
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
public class ControllerAdmin implements ActionListener{//komposisi
    private MenuAdmin view; //enkapsulasi
    private Database db;
    
    public ControllerAdmin() throws SQLException {
        view = new MenuAdmin();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
        setTableMentor();
        setTableSiswa();
    }
    
    public void setTableMentor() throws SQLException {
        ResultSet rs1 = db.query("SELECT * FROM MENTOR");
        DefaultTableModel model1 = (DefaultTableModel) view.getTabelMentor().getModel();
        while (rs1.next()) {
            model1.addRow(new Object[]{rs1.getString("nama"), rs1.getString("email"), rs1.getString("nohp"),
                rs1.getString("alamat"), rs1.getString("lulusan"), rs1.getString("password"), rs1.getString("kelamin"), rs1.getString("jadwal1")});
        } view.getTabelMentor().setModel(model1);
    }
    
    public void setTableSiswa() throws SQLException {
        ResultSet rs2 = db.query("SELECT * FROM SISWA");
        DefaultTableModel model2 = (DefaultTableModel) view.getTabelSiswa().getModel();
        while (rs2.next()) {
            model2.addRow(new Object[]{rs2.getString("nama"), rs2.getString("email"), rs2.getString("nohp"),
                rs2.getString("kelas"), rs2.getString("password"), rs2.getString("alamat"),
                rs2.getString("kelamin"), rs2.getString("emailmentor"), });
        } view.getTabelSiswa().setModel(model2);
    }
    
    @Override //polimorfisme
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnLogout())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnDeleteMentor())) {
            int i = view.getTabelMentor().getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) view.getTabelMentor().getModel();
            String selected = model.getValueAt(i, 1).toString();
            try {
                db.update("DELETE FROM MENTOR WHERE EMAIL = '" + selected + "';");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(i);
        } else if (s.equals(view.getBtnDeleteSiswa())) {
            int j = view.getTabelSiswa().getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) view.getTabelSiswa().getModel();
            String selected = model.getValueAt(j, 1).toString();
            try {
                db.update("DELETE FROM SISWA WHERE EMAIL = '" + selected + "';");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(j);
        }
    }
    
}
