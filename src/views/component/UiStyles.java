/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.component;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Al
 */
public final class UiStyles {

    private UiStyles() {
    }

    public static void seRoundPanel(JPanel panel, Insets insets, int lengkung, Color warnaBorder, float ketebalanBorder) {
        // atur border panel biar sudutnya melengkung
        panel.setBorder(new FlatLineBorder(new Insets(
                insets.top, insets.left, insets.bottom, insets.right), warnaBorder, ketebalanBorder, lengkung));
    }

    public static void setTextField(JTextField tField) {
        // atur style kolom teks
        tField.putClientProperty("FlatLaf.style",
                "arc:999;"
                + "background:#F6F7F5; foreground:#16201B;"
                + "caretColor:#66706B;"
                + "borderWidth:1; borderColor:#ECEDEB;"
                + "focusedBorderColor:#337253; focusWidth:0; innerFocusWidth:1;"
                );
    }

    public static void setButton(JButton btn) {
        // atur style tombol utama
        btn.putClientProperty("FlatLaf.style",
                "arc:100;"
                + "borderWidth:0; focusWidth:0; innerFocusWidth:0;"
                + "background:#144D32; foreground:#FFFFFF;"
                + "hoverBackground:#22764B; pressedBackground:#0D3B26");
        btn.setFocusPainted(false);
    }

    public static void setButtonv2(JButton btnv2) {
        // atur style tombol kedua
        btnv2.putClientProperty("FlatLaf.style",
                "arc:100;"
                + "borderWidth:0; focusWidth:0; innerFocusWidth:0;"
                + "background:#FFFFFF; foreground:#0D3B26;"
                + "hoverBackground:#E5E5E5; pressedBackground:#CCCCCC");
        btnv2.setFocusPainted(false);
    }
    
    public static void setButtonv3(JButton btnv3) {
        // atur style tombol kedua
        btnv3.putClientProperty("FlatLaf.style",
                "arc:100;"
                + "borderWidth:1; borderColor:#144D32; hoverBorderColor:#144D32; pressedBorderColor:#144D32;"
                + "focusWidth:0; innerFocusWidth:0;"
                + "background:#FFFFFF; foreground:#0D3B26;"
                + "hoverBackground:#E5E5E5; pressedBackground:#CCCCCC");
        btnv3.setFocusPainted(false);
    }
}
