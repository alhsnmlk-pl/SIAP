/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Al
 */
public class RecentComponent extends javax.swing.JPanel {

    // status yang bisa dipilih untuk badge
    public enum Status {
        HADIR("Hadir", "#EEF7F1", "#A8D5B9", "#1B5E3D"),
        TERLAMBAT("Terlambat", "#FFFBEB", "#FFD230", "#BB4D00"),
        PULANG("Pulang", "#F0F9FF", "#74D4FF", "#0069A8"),
        PULANG_AWAL("Pulang Awal", "#FFFBEB", "#FFD230", "#BB4D00"),
        MENUNGGU_VERIFIKASI("Menunggu", "#FFFBEB", "#FFD230", "#BB4D00"),
        DITERIMA("Diterima", "#EEF7F1", "#A8D5B5", "#1B5E3D"),
        DITOLAK("Ditolak", "#FEF2F2", "#FECACA", "#B91C1C");

        private final String label;
        private final Color backgroundColor;
        private final Color borderColor;
        private final Color textColor;

        Status(String label, String backgroundHex, String borderHex, String textHex) {
            this.label = label;
            this.backgroundColor = Color.decode(backgroundHex);
            this.borderColor = Color.decode(borderHex);
            this.textColor = Color.decode(textHex);
        }

        // ubah status dari database menjadi pilihan status
        public static Status fromDatabaseValue(String databaseValue) {
            try {
                if (databaseValue == null || databaseValue.trim().isEmpty()) {
                    System.err.println("RecentComponent: status dari database kosong.");
                    return null;
                }

                // ubah teks menjadi huruf besar agar mudah dicocokkan
                String value = databaseValue.trim().toUpperCase(Locale.ROOT);
                value = value.replace(" ", "_");
                value = value.replace("-", "_");

                switch (value) {
                    case "HADIR":
                        return HADIR;
                    case "TERLAMBAT":
                        return TERLAMBAT;
                    case "PULANG":
                        return PULANG;
                    case "PULANG_AWAL":
                        return PULANG_AWAL;
                    case "MENUNGGU":
                    case "MENUNGGU_VERIFIKASI":
                        return MENUNGGU_VERIFIKASI;
                    case "DITERIMA":
                        return DITERIMA;
                    case "DITOLAK":
                        return DITOLAK;
                    default:
                        System.err.println("RecentComponent: status tidak dikenal: " + databaseValue);
                        return null;
                }
            } catch (Exception error) {
                System.err.println("RecentComponent: gagal membaca status. " + error.getMessage());
                return null;
            }
        }
    }

    // data yang ditampilkan pada satu baris komponen
    public static final class Item {
        private final String initials;
        private final String name;
        private final String description;
        private final Status status;

        public Item(String initials, String name, String description, Status status) {
            this.initials = initials;
            this.name = name;
            this.description = description;
            this.status = status;
        }

        // constructor ini menerima status dari database sebagai teks
        public Item(String initials, String name, String description, String databaseStatus) {
            this(initials, name, description, Status.fromDatabaseValue(databaseStatus));
        }
    }

    // buat tampilan satu baris dengan data bawaan editor formulir
    public RecentComponent() {
        initComponents();
        UiStyles.seRoundPanel(jPanel60, new Insets(0, 0, 0, 0), 999, Color.decode("#ECEDEB"), 0f); 
        jPanel58.setBorder(BorderFactory.createEmptyBorder());
        jPanel58.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 7));
        setStatus(Status.DITERIMA);

        // batasi tinggi agar baris tidak meregang ke bawah
        Dimension preferredSize = getPreferredSize();
        setMaximumSize(new Dimension(Integer.MAX_VALUE, preferredSize.height));
    }

    // buat satu baris sekaligus mengisi datanya
    public RecentComponent(Item item) {
        this();
        setItem(item);
    }

    // ubah tulisan dan warna badge sesuai status
    public void setStatus(Status status) {
        if (status == null) {
            System.err.println("RecentComponent: status kosong, badge tidak diubah.");
            return;
        }

        // tulisan di dalam badge ditampilkan oleh label yang sudah disiapkan
        jLabel65.setText(status.label);
        jLabel65.setForeground(status.textColor);
        lblBadge.setBackground(status.backgroundColor);
        lblBadge.setOpaque(true);
        UiStyles.seRoundPanel(lblBadge, new Insets(0, 8, 0, 8), 8, status.borderColor, 1f);

        // lebar badge menyesuaikan panjang tulisannya
        FontMetrics metrics = jLabel65.getFontMetrics(jLabel65.getFont());
        int badgeWidth = metrics.stringWidth(status.label) + 18;
        lblBadge.setPreferredSize(new Dimension(badgeWidth, 26));
        jPanel58.setPreferredSize(new Dimension(badgeWidth, 41));
        jPanel58.setMinimumSize(new Dimension(badgeWidth, 41));
        revalidate();
        repaint();
    }

    // pilih status dari teks database
    public void setStatus(String databaseStatus) {
        Status status = Status.fromDatabaseValue(databaseStatus);
        if (status != null) {
            setStatus(status);
        }
    }

    // isi inisial, nama, keterangan, dan status untuk satu baris
    public void setItem(Item item) {
        if (item == null || item.status == null) {
            System.err.println("RecentComponent: item kosong atau statusnya tidak dikenal.");
            setVisible(false);
            return;
        }
        setVisible(true);
        jLabel62.setText(textOrEmpty(item.initials));
        lblName.setText(textOrEmpty(item.name));
        lblDesc.setText(textOrEmpty(item.description));
        setStatus(item.status);
    }

    // hapus isi lama lalu tambahkan satu baris untuk setiap item
    // gunakan container di dalam scrollpane jika daftar panjang
    public static void populate(JPanel container, List<Item> items) {
        if (container == null || items == null) {
            System.err.println("RecentComponent: container atau daftar item kosong.");
            return;
        }

        container.removeAll();
        // boxlayout menyusun baris dari atas ke bawah
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        for (Item item : items) {
            if (item == null || item.status == null) {
                System.err.println("RecentComponent: satu item dilewati karena datanya tidak lengkap.");
                continue;
            }
            RecentComponent row = new RecentComponent(item);
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            container.add(row);
        }

        // minta swing menghitung ulang ukuran lalu menggambar ulang container
        container.revalidate();
        container.repaint();
    }

    private static String textOrEmpty(String value) {
        // ubah nilai null menjadi teks kosong
        return value == null ? "" : value;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel57 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        lblBadge = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout(8, 8));

        jPanel57.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        jPanel57.setOpaque(false);
        jPanel57.setPreferredSize(new java.awt.Dimension(34, 41));
        jPanel57.setLayout(new java.awt.CardLayout());

        jPanel60.setBackground(new java.awt.Color(245, 201, 168));
        jPanel60.setPreferredSize(new java.awt.Dimension(24, 32));
        jPanel60.setLayout(new java.awt.CardLayout());

        jLabel62.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 12)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("AH");
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel62.setIconTextGap(0);
        jPanel60.add(jLabel62, "card2");

        jPanel57.add(jPanel60, "card2");

        add(jPanel57, java.awt.BorderLayout.LINE_START);

        jPanel59.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 2, 0));
        jPanel59.setOpaque(false);
        jPanel59.setLayout(new java.awt.GridLayout(2, 1));

        lblName.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        lblName.setText("Athoil Hakim");
        jPanel59.add(lblName);

        lblDesc.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        lblDesc.setText("Kelas 8B · scan 13:06 WIB");
        jPanel59.add(lblDesc);

        add(jPanel59, java.awt.BorderLayout.CENTER);

        jPanel58.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0));
        jPanel58.setOpaque(false);
        jPanel58.setPreferredSize(new java.awt.Dimension(100, 41));

        lblBadge.setBackground(new java.awt.Color(240, 249, 255));
        lblBadge.setLayout(new java.awt.CardLayout());

        jLabel65.setBackground(new java.awt.Color(240, 249, 255));
        jLabel65.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 105, 168));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Diterima");
        lblBadge.add(jLabel65, "card2");

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(lblBadge, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(lblBadge, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        add(jPanel58, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel lblBadge;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
