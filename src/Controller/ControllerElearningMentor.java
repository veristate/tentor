/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Mentor.ElearningMentor;
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
public class ControllerElearningMentor implements ActionListener{
    private ElearningMentor view;
    private Database db;
    
    public ControllerElearningMentor() throws SQLException {
        view = new ElearningMentor();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
        setTabel();
    }
    
    public void setTabel() throws SQLException {
        ResultSet rs = db.query("SELECT * FROM ELEARNING");
        DefaultTableModel model1 = (DefaultTableModel) view.getTabelMateri().getModel();
        while (rs.next()) {
            model1.addRow(new Object[]{rs.getString("materi"), rs.getString("tugas")});
        } view.getTabelMateri().setModel(model1);
    }

    @Override 
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnKembali())) {
            view.setVisible(false);
        } else if (s.equals(view.getBtnTambah())) {
            try {
                db.update("INSERT INTO ELEARNING(materi,tugas) VALUES('" + view.getTfMateri() + "','" + view.getTfTugas() + "');");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerElearningMentor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
