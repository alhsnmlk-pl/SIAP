package views.component;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class RoundedTablePanel extends JPanel {

    public static void pasang(JPanel container, JTable table) {

        container.removeAll();
        container.setLayout(new BorderLayout());
        container.setOpaque(false);
        container.add(new RoundedTablePanel(table), BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    public RoundedTablePanel(JTable table) {
        super(new BorderLayout());
        setOpaque(false);

        table.putClientProperty("FlatLaf.style",
                "showHorizontalLines: true;"
              + "showVerticalLines: false;"
              + "intercellSpacing: 0,1;"
              + "cellMargins: 0,16,0,16;"
              + "showCellFocusIndicator: false;"
              + "selectionBackground: #E2EEE5;"
              + "selectionInactiveBackground: #E2EEE5;"
              + "selectionForeground: #1E1E1E");

        table.setGridColor(new Color(232, 232, 230));
        table.setRowHeight(40);
        table.setBackground(Color.WHITE);

        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.putClientProperty("FlatLaf.style",
                "background: #F6F6F4;"
              + "foreground: #6E736E;"
              + "hoverBackground: #F6F6F4;"
              + "pressedBackground: #F6F6F4;"
              + "separatorColor: #F6F6F4;"
              + "bottomSeparatorColor: #E8E8E6;"
              + "cellMargins: 0,16,0,16");
        header.setFont(new Font("Plus Jakarta Sans SemiBold", Font.PLAIN, 12));
        header.setPreferredSize(new Dimension(0, 32));

        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        
        // mengambil renderer bawaan dari header tabel
        TableCellRenderer base = header.getDefaultRenderer();
        // memasang renderer pembungkus yang tetap memakai renderer bawaan
        header.setDefaultRenderer((t, v, sel, foc, r, c) -> {
            // menyiapkan komponen sel dengan renderer bawaan
            Component comp = base.getTableCellRendererComponent(t, v, sel, foc, r, c);
            // memastikan komponen hasil berupa label
            if (comp instanceof JLabel) {
                // meratakan teks label ke sisi kiri
                ((JLabel) comp).setHorizontalAlignment(SwingConstants.LEFT);
            }
            // mengembalikan komponen yang sudah disiapkan
            return comp;
        });

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(null);
        sp.getViewport().setBackground(Color.WHITE);
        sp.setColumnHeaderView(header);
        sp.getColumnHeader().setBackground(new Color(246, 246, 244));
        add(sp, BorderLayout.CENTER);
    }

    // menggambar komponen turunan di dalam panel dengan bingkai bersudut membulat
    @Override
    protected void paintChildren(Graphics g) {
        // menyalin konteks gambar agar pengaturan tidak mengubah konteks asal
        Graphics2D g2 = (Graphics2D) g.create();
        // menghaluskan garis lengkung dan garis diagonal
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // membatasi area gambar ke bentuk persegi panjang bersudut membulat
        g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 24, 24));
        // menggambar seluruh komponen turunan di dalam batas tersebut
        super.paintChildren(g2);

        // menghapus batas klip sementara sebelum menggambar bingkai
        g2.setClip(null);
        // menentukan warna garis bingkai
        g2.setColor(new Color(236, 237, 235));
        // mengatur ketebalan garis bingkai menjadi satu piksel
        g2.setStroke(new BasicStroke(1f));
        // menggambar bingkai panel dengan sudut membulat
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);
        // melepas salinan konteks gambar setelah selesai digunakan
        g2.dispose();
    }

    // mengatur lebar pilihan untuk setiap kolom tabel
    public static void lebar(JTable table, int... lebar) {
        // menyesuaikan lebar kolom berikutnya ketika ukuran tabel berubah
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        // mengambil model kolom dari tabel
        TableColumnModel cm = table.getColumnModel();
        // membatasi jumlah kolom ke jumlah nilai lebar yang tersedia
        int n = Math.min(lebar.length, cm.getColumnCount());
        // menerapkan lebar pilihan pada setiap kolom
        for (int i = 0; i < n; i++) {
            // mengatur lebar pilihan untuk kolom pada indeks saat ini
            cm.getColumn(i).setPreferredWidth(lebar[i]);
        }
    }
}
