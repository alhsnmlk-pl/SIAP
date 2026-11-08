/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.UUID;

/**
 *
 * @author Al
 */
public class modelUser {
    private String idPengguna, nama, username, password, role, email, no_telp, status, terakhir_masuk, dibuat ;

    public modelUser() {
    }
    
    

    public modelUser(String idPengguna, String nama, String username, String password, String role, String email, String no_telp, String status, String terakhir_masuk, String dibuat) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.no_telp = no_telp;
        this.status = status;
        this.terakhir_masuk = terakhir_masuk;
        this.dibuat = dibuat;
    }
    
    

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna() {
        this.idPengguna =  UUID.randomUUID().toString();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerakhir_masuk() {
        return terakhir_masuk;
    }

    public void setTerakhir_masuk(String terakhir_masuk) {
        this.terakhir_masuk = terakhir_masuk;
    }

    public String getDibuat() {
        return dibuat;
    }

    public void setDibuat(String dibuat) {
        this.dibuat = dibuat;
    }

    
    
    
}
