/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.component;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Al
 */
public class StyleTabel extends JPanel {

    // tingkat lengkung sudut panel
    private static final int RADIUS = 16;

    // pemakaian: StyleTabel.pasang(panelTabel, tblGuru, 170, 250, 70, 200, 110, 160)
    public static void pasang(JPanel container, JTable table, int... lebar) {
        lebar(table, lebar);
        container.removeAll();
        container.setLayout(new BorderLayout());
        container.setOpaque(false);
        container.add(new StyleTabel(table), BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    public StyleTabel(JTable table) {
        super(new BorderLayout());
        setOpaque(false);

        // tampilan isi tabel
        table.putClientProperty("FlatLaf.style",
                "showHorizontalLines: true;"
                + "showVerticalLines: false;"
                + "intercellSpacing: 0,1;"
                + "cellMargins: 0,16,0,16;"
                + "showCellFocusIndicator: false;"
                + "selectionBackground: #D9EEE1;"
                + "selectionInactiveBackground: #D9EEE1;"
                + "selectionForeground: #66706B;"
                + "selectionInactiveForeground: #66706B");

        table.setGridColor(Color.decode("#ECEDEB"));
        table.setRowHeight(41);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.decode("#66706B"));
        table.setFillsViewportHeight(true);

        // hanya satu baris yang bisa dipilih
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        // semua isi sel rata kiri termasuk angka
        DefaultTableCellRenderer kiri = new DefaultTableCellRenderer();
        kiri.setHorizontalAlignment(SwingConstants.LEFT);
        table.setDefaultRenderer(Object.class, kiri);
        table.setDefaultRenderer(Number.class, kiri);

        // tampilan header tanpa efek hover dan klik
        JTableHeader header = table.getTableHeader();
        header.putClientProperty("FlatLaf.style",
                "height: 32;"
                + "background: #F6F7F5;"
                + "foreground: #66706B;"
                + "hoverBackground: #F6F7F5;"
                + "hoverForeground: #66706B;"
                + "pressedBackground: #F6F7F5;"
                + "pressedForeground: #66706B;"
                + "separatorColor: #F6F7F5;"
                + "bottomSeparatorColor: #ECEDEB;"
                + "cellMargins: 0,16,0,16");
        header.setFont(fontHeader(header.getFont()));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        // header rata kiri dengan tetap memakai renderer bawaan
        TableCellRenderer base = header.getDefaultRenderer();
        header.setDefaultRenderer((t, v, sel, foc, r, c) -> {
            Component comp = base.getTableCellRendererComponent(t, v, false, false, r, c);
            if (comp instanceof JLabel) {
                ((JLabel) comp).setHorizontalAlignment(SwingConstants.LEFT);
            }
            return comp;
        });

        // scroll pane hanya vertikal
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.getViewport().setBackground(Color.WHITE);
        sp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        sp.setColumnHeaderView(header);
        sp.getColumnHeader().setOpaque(true);
        sp.getColumnHeader().setBackground(Color.decode("#F6F7F5"));

        // sudut kanan atas di atas scrollbar ikut warna header
        JPanel pojok = new JPanel();
        pojok.setBackground(Color.decode("#F6F7F5"));
        sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pojok);

        add(sp, BorderLayout.CENTER);
    }

    // pakai plus jakarta sans semibold kalau terpasang, kalau tidak pakai font bawaan
    private static Font fontHeader(Font dasar) {
        String nama = "Plus Jakarta Sans SemiBold";
        boolean ada = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames()).contains(nama);
        return ada ? new Font(nama, Font.PLAIN, 12) : dasar.deriveFont(Font.BOLD, 12f);
    }

    // warna latar induk terdekat yang opaque untuk menutup sudut luar
    private Color latarInduk() {
        Container p = getParent();
        while (p != null && !p.isOpaque()) {
            p = p.getParent();
        }
        return p == null ? Color.WHITE : p.getBackground();
    }

    // semua cat ulang anak harus lewat panel ini agar sudut tidak tertimpa
    @Override
    protected boolean isPaintingOrigin() {
        return true;
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        float d = RADIUS * 2f;

        // gambar isi seperti biasa tanpa clip
        super.paintChildren(g2);

        // tutup empat sudut luar dengan warna latar induk
        Area sudut = new Area(new Rectangle2D.Float(0, 0, w, h));
        sudut.subtract(new Area(new RoundRectangle2D.Float(0, 0, w, h, d, d)));
        g2.setColor(latarInduk());
        g2.fill(sudut);

        // garis tepi digambar paling akhir
        g2.setColor(Color.decode("#ECEDEB"));
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, w - 1, h - 1, d, d));
        g2.dispose();
    }

    // angka adalah proporsi antar kolom, tabel pas selebar panel dan kolom tidak bisa ditarik
    public static void lebar(JTable table, int... lebar) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel cm = table.getColumnModel();
        int n = Math.min(lebar.length, cm.getColumnCount());
        for (int i = 0; i < cm.getColumnCount(); i++) {
            cm.getColumn(i).setResizable(false);
        }
        for (int i = 0; i < n; i++) {
            cm.getColumn(i).setPreferredWidth(lebar[i]);
        }
    }
}