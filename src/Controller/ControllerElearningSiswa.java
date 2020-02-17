/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Siswa.ElearningSiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sayid
 */
public class ControllerElearningSiswa implements ActionListener{
    private ElearningSiswa view;
    private Database db;
    
    public ControllerElearningSiswa() throws SQLException {
        view = new ElearningSiswa();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
        setTabelMateri();
    }
    
    public void setTabelMateri() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM ELEARNING");
        DefaultTableModel model = (DefaultTableModel) view.getTabelMateri().getModel();
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("materi"), rs.getString("tugas")});
        } view.getTabelMateri().setModel(model);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnKembali())) {
            view.setVisible(false);
        }
    }
}
