/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.component;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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

    public static void setTable(JTable table, JScrollPane scrollPane) {
        // ====== HEADER STYLE ======
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
        header.setOpaque(true);
        header.setReorderingAllowed(false);
        // Custom renderer: warna tetap, tidak ada efek hover/press
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable tbl,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        tbl, value, false, false, row, column);
                lbl.setBackground(Color.decode("#F6F7F5"));
                lbl.setForeground(Color.decode("#66706B"));
                lbl.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 13));
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                lbl.setOpaque(true);
                return lbl;
            }
        });

        // ====== ROW STYLE ======
        table.setRowHeight(42);
        table.setFont(new java.awt.Font("Plus Jakarta Sans", java.awt.Font.PLAIN, 14));
        table.setBackground(Color.decode("#FFFFFF"));
        table.setForeground(Color.decode("#16201B"));
        table.setSelectionBackground(Color.decode("#D9EEE1"));
        table.setSelectionForeground(Color.decode("#16201B"));
        table.setGridColor(Color.decode("#ECEDEB"));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFocusable(false);
        table.setFillsViewportHeight(true);

        // Set non-editable
        table.setDefaultEditor(Object.class, null);

        // Custom cell renderer: warna select + padding kiri-kanan
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable tbl,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        tbl, value, isSelected, false, row, column);
                lbl.setOpaque(true);
                if (isSelected) {
                    lbl.setBackground(Color.decode("#D9EEE1"));
                    lbl.setForeground(Color.decode("#16201B"));
                } else {
                    lbl.setBackground(Color.decode("#FFFFFF"));
                    lbl.setForeground(Color.decode("#16201B"));
                }
                return lbl;
            }
        });

        // ====== SCROLLPANE ROUNDED (arc 32) ======
        scrollPane.putClientProperty("FlatLaf.style",
                "arc:32;"
                + "borderColor:#ECEDEB;"
                + "background:#FFFFFF;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;");
        scrollPane.getViewport().setBackground(Color.decode("#FFFFFF"));
        scrollPane.setBackground(Color.decode("#FFFFFF"));
    }
}
