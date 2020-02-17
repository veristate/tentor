/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.Siswa.RSiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Sayid
 */
public class ControllerRSiswa implements ActionListener{
    private RSiswa view;
    private Database db;

    public ControllerRSiswa() throws SQLException {
        view = new RSiswa();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(view.getBtnBatal())) {
            view.setVisible(false);
            try {
                new ControllerLogin();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ControllerRSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        } else if (s.equals(view.getBtnRegister())) {
            if (view.isNull()) {
                view.setJlabel11("Text field kosong");
            } else if (view.getPassword() == false) {
                view.setJlabel11("Password tidak sama");
            } else if ((view.getLaki() == false) && (view.getPerempuan() == false)) {
                view.setJlabel11("Pilih jenis kelamin");
            } else {
                String kelamin;
                if (view.getLaki()) {
                    kelamin = "Laki - laki";
                } else {
                    kelamin = "Perempuan";
                }
                String nama = view.getTfNama();
                String email = view.getTfEmail();
                String nohp = view.getTfNoHp();
                String password = view.getTfPassword();
                String tingkatan = view.getTingkatan();
                String alamat = view.getTfAlamat();
                try {
                    ResultSet rs = db.query("SELECT * FROM SISWA WHERE EMAIL ='" + email +"';");
                    if (rs.next()) {
                        view.setJlabel11("Email sudah tersedia");
                    } else {
                        db.update("INSERT INTO siswa(nama,email,nohp,kelas,password,alamat,kelamin,emailmentor) "
                                + "VALUES('" + nama + "','" + email + "','" + nohp + "','" + tingkatan + "','" + password + 
                                "','" + alamat + "','" + kelamin + "','');");
                        view.setJlabel11("Berhasil register");
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(ControllerRSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }
}
