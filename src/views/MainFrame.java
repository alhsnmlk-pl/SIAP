/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import models.modelUser;
import views.component.UiStyles;

/**
 *
 * @author Al
 */
public class MainFrame extends javax.swing.JFrame {

    private modelUser currentUser = new modelUser();
    private CardLayout cardLayout; //menyipan card layout dari panelContent

    /**
     * Creates new form FrameMain
     */
    public MainFrame() {
        initComponents();
        setSidebarSvgIcons();
        updateSearchBorder();
        startClock();
        styleSidebar();
        updateRoleLabel();
        styleBtnSidebar();
        styleScrollBar();
        styleMainHeader();
        inisiasiPanel();
    }

    public void setUser(modelUser currentUser) {
        this.currentUser = currentUser;
    }

    // mnggganti icon png ke svg agar hd ketika zoom
    private void setSidebarSvgIcons() {

        lblNamaSekolah.setIcon(new FlatSVGIcon("iconsvg/siapV2Sidebar.svg"));
        btnLogout.setIcon(new FlatSVGIcon("iconsvg/logout.svg"));
        lblSearch.setIcon(new FlatSVGIcon("iconsvg/search.svg"));

        setSvgMenuIcon(btndashAbs, "dashboardNonaktif.svg", "dashboardAktif.svg");
        setSvgMenuIcon(btnDataMaster, "dataMasterNonaktif.svg", "dataMasterAktif.svg");
        setSvgMenuIcon(btnSiswa, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnKelas, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnGuru, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnQRSiswa, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnAbsQR, "absensiQrNonaktif.svg", "absensiQrAktif.svg");
        setSvgMenuIcon(btnScanQR, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnHasilScan, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnRekapAbsensi, "rekapLaporanNonaktif.svg", "rekapLaporanAktif.svg");
        setSvgMenuIcon(btnRekapAbsensiItem, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnLaporanAbsensi, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnDashboardPPDB, "dashboardNonaktif.svg", "dashboardAktif.svg");
        setSvgMenuIcon(btnDataPendaftar, "dataPendaftarNonaktif.svg", "dataPendaftarAktif.svg");
        setSvgMenuIcon(btnPenempatanKelas7, "penempatanKelasNonaktif.svg", "penempatanKelasAktif.svg");
        setSvgMenuIcon(btnRekapPPDB, "rekapLaporanNonaktif.svg", "rekapLaporanAktif.svg");
        setSvgMenuIcon(btnRekapPPDBItem, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnLaporanPPDB, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnPengaturan, "pengaturanNonaktif.svg", "pengaturanAktif.svg");
        setSvgMenuIcon(btnJamAbsensi, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnPPDB, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnPengguna, "dotsNonaktif.svg", "dotsAktif.svg");
        setSvgMenuIcon(btnProfil, "profilNonaktif.svg", "profilAktif.svg");

    }

    // atur icon normal dan aktif utk semua button menu
    private void setSvgMenuIcon(JToggleButton button, String iconNonaktif, String iconAktif) {

        // siapin icon normal dan icon waktu menu dipilih
        FlatSVGIcon normal = new FlatSVGIcon("iconsvg/" + iconNonaktif);
        FlatSVGIcon aktif = new FlatSVGIcon("iconsvg/" + iconAktif);

        button.setIcon(normal);
        button.setDisabledIcon(normal);
        button.setSelectedIcon(aktif);
        button.setDisabledSelectedIcon(aktif);

    }

    //tampilan sidebar
    private void styleSidebar() {
        UiStyles.seRoundPanel(panelSidebar, 0, 0, 0, 0, 48, "#ECEDEB", 0f);
        UiStyles.seRoundPanel(panelIsisfooter, 16, 16, 16, 16, 24, "#ECEDEB", 0f);
        UiStyles.setButton(btnLogout, "#FFFFFF", "#0D3B26", "#E5E5E5", "#CCCCCC", null, 0);
    }

    //role sesuai login
    private void updateRoleLabel() {
        String userRole = currentUser.getRole();
        // sesuaikan tulisan role dengan user yang diterima dari login
        if (userRole != null && "Kepala Madrasah".equalsIgnoreCase(userRole.trim())) {
            // nama role dibuat dua baris agar muat di footer
            lblRole.setText("<html>Kepala<br>Madrasah</html>");
            panelFooterSidebar.setPreferredSize(new java.awt.Dimension(240, 114));
            panelIsisfooter.setPreferredSize(new java.awt.Dimension(208, 88));
        } else {
            lblRole.setText("Operator");
        }
    }
    
    //method style berparamter agar bisa di gunakan berbeda versi
    private void setSidebarStyle(
            JToggleButton[] buttons,
            String hover,
            String pressed,
            String selected,
            String margin) {

        for (JToggleButton b : buttons) {
            b.putClientProperty("FlatLaf.style",
                    "arc: 0;"
                    + "borderWidth: 0;"
                    + "focusWidth: 0;"
                    + "innerFocusWidth: 0;"
                    + "background: #FFFFFF00;"
                    + "hoverBackground: " + hover + ";"
                    + "pressedBackground: " + pressed + ";"
                    + "foreground: #FFFFFFBF;"
                    + "selectedBackground: " + selected + ";"
                    + "selectedForeground: #FFFFFF;"
                    + "margin: " + margin + ";"
            );
        }
    }

    private void styleBtnSidebar() {

        // tombol utama yang langsung buka halaman
        setSidebarStyle(
                new JToggleButton[]{
                    btndashAbs,
                    btnDashboardPPDB,
                    btnDataPendaftar,
                    btnPenempatanKelas7,
                    btnProfil
                },
                "#FFFFFF15",
                "#FFFFFF25",
                "#FFFFFF20",
                "0, 24, 0, 24"
        );

        // tombol utama yang punya submenu
        setSidebarStyle(
                new JToggleButton[]{
                    btnDataMaster,
                    btnAbsQR,
                    btnRekapAbsensi,
                    btnRekapPPDB,
                    btnPengaturan
                },
                "#FFFFFF00",
                "#FFFFFF00",
                "#FFFFFF00",
                "0, 0, 0, 0"
        );

        // tombol yang ada di dalam submenu
        setSidebarStyle(
                new JToggleButton[]{
                    btnSiswa,
                    btnKelas,
                    btnGuru,
                    btnQRSiswa,
                    btnScanQR,
                    btnHasilScan,
                    btnRekapAbsensiItem,
                    btnLaporanAbsensi,
                    btnRekapPPDBItem,
                    btnLaporanPPDB,
                    btnJamAbsensi,
                    btnPPDB,
                    btnPengguna
                },
                "#FFFFFF00",
                "#FFFFFF00",
                "#FFFFFF26",
                "0, 24, 0, 24"
        );
    }

    private void styleScrollBar() { // atur tampilan scroll bar
        JScrollBar scrollBar = scrollBarr.getVerticalScrollBar(); // ambil scroll bar vertikal
        scrollBar.setBackground(new Color(20, 77, 50)); // atur warna latar scroll bar
        scrollBar.putClientProperty("FlatLaf.style", // atur style pakai flatlaf
                "width: 8; showButtons: false;"
                + "track: #144D32; hoverTrackColor: #144D32;"
                + "thumb: #7A9D89; hoverThumbColor: #A8C0B2; pressedThumbColor: #C9D8D0;"
                + "trackInsets: 0,2,0,2; thumbInsets: 1,2,1,2;"
                + "trackArc: 999; thumbArc: 999");
    }

    // atur bagian header halaman utama
    private void styleMainHeader() {
        UiStyles.seRoundPanel(panelMainHeader, 8, 16, 8, 16, 48, "#ECEDEB", 1f);
        UiStyles.seRoundPanel(panelContent, 16, 16, 16, 16, 48, "#ECEDEB", 1f);
        tField.putClientProperty("JTextField.placeholderText", "Cari menu…");
        tField.putClientProperty("FlatLaf.style", "background:#FFFFFF00; placeholderForeground:#66706B");
        UiStyles.seRoundPanel(pnlAvatar, 0, 0, 0, 0, 999, "#404040", 0f);
    }

    private void startClock() {
        // perbarui tanggal dan jam di header tiap detik
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, d MMM yyyy · HH.mm 'WIB'", new Locale("id", "ID"));
        Timer t = new Timer(1000, e -> lblDateTime.setText(fmt.format(new Date())));
        t.setInitialDelay(0);
        t.start();
    }

    private void inisiasiPanel() {
        // ambil card layout dari panelContent
        cardLayout = (CardLayout) panelContent.getLayout();
        // kosongin panel bawaan nwteans agar dashboard tdk dobel
        panelContent.removeAll();

        // grup tombol menu agar yang kepilih satu
        ButtonGroup menuPages = new ButtonGroup();

        addMenuPage(menuPages, btndashAbs, "dashboard-absensi", new views.absensi.DashboarAbsensiPanel());
        addMenuPage(menuPages, btnSiswa, "siswa", new views.master.SiswaPanel());
        addMenuPage(menuPages, btnKelas, "kelas", new views.master.KelasPanel());
        addMenuPage(menuPages, btnGuru, "guru", new views.master.GuruPanel());
        addMenuPage(menuPages, btnQRSiswa, "qr-siswa", new views.absensi.QRSiswaPanel());
        addMenuPage(menuPages, btnScanQR, "scan-qr", new views.absensi.ScanQRPanel());
        addMenuPage(menuPages, btnHasilScan, "hasil-scan", new views.absensi.HasilScanPanel());
        addMenuPage(menuPages, btnRekapAbsensiItem, "rekap-absensi", new views.rekaplaporan.RekapAbsensiPanel());
        addMenuPage(menuPages, btnLaporanAbsensi, "laporan-absensi", new views.rekaplaporan.LaporanAbsensiPanel());
        addMenuPage(menuPages, btnDashboardPPDB, "dashboard-ppdb", new views.ppdb.DashboardPPDBPanel());
        addMenuPage(menuPages, btnDataPendaftar, "data-pendaftar", new views.ppdb.DataPendaftarPanel());
        addMenuPage(menuPages, btnPenempatanKelas7, "penempatan-kelas", new views.ppdb.PenempatanKelaspanel());
        addMenuPage(menuPages, btnRekapPPDBItem, "rekap-ppdb", new views.rekaplaporan.RekapPPDBPanel());
        addMenuPage(menuPages, btnLaporanPPDB, "laporan-ppdb", new views.rekaplaporan.LaporanPPDBPanel());
        addMenuPage(menuPages, btnJamAbsensi, "pengaturan-absensi", new views.pengaturan.PengaturanAbsensiPanel());
        addMenuPage(menuPages, btnPPDB, "pengaturan-ppdb", new views.pengaturan.PengaturanPPDBPanel());
        addMenuPage(menuPages, btnPengguna, "pengguna", new views.pengaturan.PenggunaPanel());
        addMenuPage(menuPages, btnProfil, "profil", new views.pengaturan.ProfilPanel());

        // buka dashboard absensi pas aplikasi pertama kali jalan
        btndashAbs.setSelected(true);
        cardLayout.show(panelContent, "dashboard-absensi");

        // tutup semua submenu pas kondisi awal
        updateSubMenuState(btnDataMaster, lblChevronDataMaster, pnlIsiDataMaster,
                          new JToggleButton[]{btnSiswa, btnKelas, btnGuru, btnQRSiswa});
        
        updateSubMenuState(btnAbsQR, lblChevronAbsensiQR, pnlIsiAbsensiQR,
                          new JToggleButton[]{btnScanQR, btnHasilScan});
        
        updateSubMenuState(btnRekapAbsensi, lblChevronRekapAbsensi, pnlIsiRekapAbsensi,
                          new JToggleButton[]{btnRekapAbsensiItem, btnLaporanAbsensi});
        
        updateSubMenuState(btnRekapPPDB, lblChevronRekapPPDB, pnlIsiRekapPPDB,
                          new JToggleButton[]{btnRekapPPDBItem, btnLaporanPPDB});
        
        updateSubMenuState(btnPengaturan, lblChevronPengaturan, pnlIsiPengaturan,
                          new JToggleButton[]{btnJamAbsensi, btnPPDB, btnPengguna});
    }

    private void addMenuPage(ButtonGroup menuPages, JToggleButton button, String cardName, javax.swing.JPanel page) {
        // masukin tombol ke grup menu
        menuPages.add(button);
        // lepas panel dari parent sebelumnya kalau ada
        if (page.getParent() != null) {
            page.getParent().remove(page);
        }
        // tambahin panel ke card layout
        panelContent.add(page, cardName);
        // tampilkan card yang sesuai pas tombol diklik
        button.addActionListener(e -> activateMenuPage(button, cardName));
    }

    private void activateMenuPage(JToggleButton button, String cardName) {
        // cek parent kalau tombol yang diklik ada di submenu
        JToggleButton parent = getSubMenuParent(button);
        if (parent == null) {
            // tutup submenu kalau yang diklik tombol utama
            closeAllSubMenus();
        } else if (!parent.isSelected()) {
            // buka parent submenu yang isinya tombol tadi
            parent.setSelected(true);
        }

        // tampilkan halaman sesuai nama card
        cardLayout.show(panelContent, cardName);
    }

    private JToggleButton getSubMenuParent(JToggleButton button) {
        // cari tombol parent dari submenu yang diklik
        if (button == btnSiswa || button == btnKelas || button == btnGuru || button == btnQRSiswa) {
            return btnDataMaster;
        }
        if (button == btnScanQR || button == btnHasilScan) {
            return btnAbsQR;
        }
        if (button == btnRekapAbsensiItem || button == btnLaporanAbsensi) {
            return btnRekapAbsensi;
        }
        if (button == btnRekapPPDBItem || button == btnLaporanPPDB) {
            return btnRekapPPDB;
        }
        if (button == btnJamAbsensi || button == btnPPDB || button == btnPengguna) {
            return btnPengaturan;
        }
        return null;
    }

    private void closeAllSubMenus() {
        // matiin semua tombol parent biar semua isi submenu ikut tertutup
        btnDataMaster.setSelected(false);
        btnAbsQR.setSelected(false);
        btnRekapAbsensi.setSelected(false);
        btnRekapPPDB.setSelected(false);
        btnPengaturan.setSelected(false);
    }

    private void updateSubMenuState(JToggleButton button, javax.swing.JLabel chevron, javax.swing.JPanel content,
                                    JToggleButton[] children) {
        // status tombol parent jadi penentu submenu lagi buka atau tutup
        boolean buka = button.isSelected();
        if (buka) {
            // kalau submenu ini dibuka, tutup submenu lain biar satu aja yang tampil
            if (button != btnDataMaster) {
                btnDataMaster.setSelected(false);
            }
            if (button != btnAbsQR) {
                btnAbsQR.setSelected(false);
            }
            if (button != btnRekapAbsensi) {
                btnRekapAbsensi.setSelected(false);
            }
            if (button != btnRekapPPDB) {
                btnRekapPPDB.setSelected(false);
            }
            if (button != btnPengaturan) {
                btnPengaturan.setSelected(false);
            }
        }

        // panah ke bawah kalau buka, panah ke kanan kalau tutup
        chevron.setIcon(new FlatSVGIcon(buka
                ? "iconsvg/IconChevronDown.svg"
                : "iconsvg/IconChevronRight.svg"));
        // tampilkan isi submenu saat buka, sembunyikan saat tutup
        content.setVisible(buka);

        if (buka) {
            // cek apakah ada halaman submenu yang sudah dipilih
            boolean childSelected = false;
            for (JToggleButton child : children) {
                childSelected |= child.isSelected();
            }
            if (!childSelected && children.length > 0) {
                // kalau belum ada, pilih halaman pertama biar langsung tampil
                children[0].doClick();
            }
        }

        // rapihin ulang tampilan menu setelah submenu berubah
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelSidebar = new javax.swing.JPanel();
        panelHeaderSidebar = new javax.swing.JPanel();
        lblNamaSekolah = new javax.swing.JLabel();
        panelFooterSidebar = new javax.swing.JPanel();
        panelIsisfooter = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        panelCenterSidebar = new javax.swing.JPanel();
        scrollBarr = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        pnllblAbsensi = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlBtndashAbs = new javax.swing.JPanel();
        btndashAbs = new javax.swing.JToggleButton();
        pnlSubMenuMaster = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblChevronDataMaster = new javax.swing.JLabel();
        btnDataMaster = new javax.swing.JToggleButton();
        pnlIsiDataMaster = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        btnSiswa = new javax.swing.JToggleButton();
        jPanel17 = new javax.swing.JPanel();
        btnKelas = new javax.swing.JToggleButton();
        jPanel18 = new javax.swing.JPanel();
        btnGuru = new javax.swing.JToggleButton();
        jPanel19 = new javax.swing.JPanel();
        btnQRSiswa = new javax.swing.JToggleButton();
        pnlSubMenuAbsensiQR = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lblChevronAbsensiQR = new javax.swing.JLabel();
        btnAbsQR = new javax.swing.JToggleButton();
        pnlIsiAbsensiQR = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        btnScanQR = new javax.swing.JToggleButton();
        jPanel23 = new javax.swing.JPanel();
        btnHasilScan = new javax.swing.JToggleButton();
        pnlSubMenuRekapAbsensi = new javax.swing.JPanel();
        pnlHeaderRekapAbsensi = new javax.swing.JPanel();
        lblChevronRekapAbsensi = new javax.swing.JLabel();
        btnRekapAbsensi = new javax.swing.JToggleButton();
        pnlIsiRekapAbsensi = new javax.swing.JPanel();
        pnlItemRekapAbsensi = new javax.swing.JPanel();
        btnRekapAbsensiItem = new javax.swing.JToggleButton();
        pnlItemLaporanAbsensi = new javax.swing.JPanel();
        btnLaporanAbsensi = new javax.swing.JToggleButton();
        pnlSectionPPDB = new javax.swing.JPanel();
        lblSectionPPDB = new javax.swing.JLabel();
        pnlBtnDashboardPPDB = new javax.swing.JPanel();
        btnDashboardPPDB = new javax.swing.JToggleButton();
        pnlBtnDataPendaftar = new javax.swing.JPanel();
        btnDataPendaftar = new javax.swing.JToggleButton();
        pnlBtnPenempatanKelas7 = new javax.swing.JPanel();
        btnPenempatanKelas7 = new javax.swing.JToggleButton();
        pnlSubMenuRekapPPDB = new javax.swing.JPanel();
        pnlHeaderRekapPPDB = new javax.swing.JPanel();
        lblChevronRekapPPDB = new javax.swing.JLabel();
        btnRekapPPDB = new javax.swing.JToggleButton();
        pnlIsiRekapPPDB = new javax.swing.JPanel();
        pnlItemRekapPPDB = new javax.swing.JPanel();
        btnRekapPPDBItem = new javax.swing.JToggleButton();
        pnlItemLaporanPPDB = new javax.swing.JPanel();
        btnLaporanPPDB = new javax.swing.JToggleButton();
        pnlSectionSistem = new javax.swing.JPanel();
        lblSectionSistem = new javax.swing.JLabel();
        pnlSubMenuPengaturan = new javax.swing.JPanel();
        pnlHeaderPengaturan = new javax.swing.JPanel();
        lblChevronPengaturan = new javax.swing.JLabel();
        btnPengaturan = new javax.swing.JToggleButton();
        pnlIsiPengaturan = new javax.swing.JPanel();
        pnlItemJamAbsensi = new javax.swing.JPanel();
        btnJamAbsensi = new javax.swing.JToggleButton();
        pnlItemPPDB = new javax.swing.JPanel();
        btnPPDB = new javax.swing.JToggleButton();
        pnlItemPengguna = new javax.swing.JPanel();
        btnPengguna = new javax.swing.JToggleButton();
        pnlBtnProfil = new javax.swing.JPanel();
        btnProfil = new javax.swing.JToggleButton();
        panelMain = new javax.swing.JPanel();
        panelMainHeader = new javax.swing.JPanel();
        pnlSearchBar = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        tField = new javax.swing.JTextField();
        pnlHeaderRight = new javax.swing.JPanel();
        lblDateTime = new javax.swing.JLabel();
        pnlProfile = new javax.swing.JPanel();
        pnlAvatar = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        pnlProfileText = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblUserEmail = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        dashboarAbsensiPanel1 = new views.absensi.DashboarAbsensiPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        mainPanel.setLayout(new java.awt.BorderLayout(16, 0));

        panelSidebar.setBackground(new java.awt.Color(20, 77, 50));
        panelSidebar.setMinimumSize(new java.awt.Dimension(240, 668));
        panelSidebar.setPreferredSize(new java.awt.Dimension(240, 688));
        panelSidebar.setLayout(new java.awt.BorderLayout());

        panelHeaderSidebar.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 24, 8, 24));
        panelHeaderSidebar.setMinimumSize(new java.awt.Dimension(240, 64));
        panelHeaderSidebar.setOpaque(false);
        panelHeaderSidebar.setPreferredSize(new java.awt.Dimension(240, 64));
        panelHeaderSidebar.setLayout(new java.awt.CardLayout());

        lblNamaSekolah.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 13)); // NOI18N
        lblNamaSekolah.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaSekolah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/siapV2Sidebar.png"))); // NOI18N
        lblNamaSekolah.setText("MTs Darul Ulum Kepel");
        lblNamaSekolah.setIconTextGap(8);
        panelHeaderSidebar.add(lblNamaSekolah, "card2");

        panelSidebar.add(panelHeaderSidebar, java.awt.BorderLayout.PAGE_START);

        panelFooterSidebar.setBackground(new java.awt.Color(20, 77, 50));
        panelFooterSidebar.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 16, 16));
        panelFooterSidebar.setMinimumSize(new java.awt.Dimension(240, 96));
        panelFooterSidebar.setOpaque(false);
        panelFooterSidebar.setPreferredSize(new java.awt.Dimension(240, 96));
        panelFooterSidebar.setLayout(new java.awt.CardLayout());

        panelIsisfooter.setBackground(new java.awt.Color(42, 90, 67));
        panelIsisfooter.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        panelIsisfooter.setOpaque(false);
        panelIsisfooter.setLayout(new java.awt.BorderLayout(8, 8));

        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(82, 40));

        btnLogout.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        btnLogout.setText("Keluar");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setMargin(new java.awt.Insets(0, 8, 0, 8));
        btnLogout.setPreferredSize(new java.awt.Dimension(82, 40));
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelIsisfooter.add(jPanel7, java.awt.BorderLayout.LINE_END);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Masuk Sebagai");
        jPanel8.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        lblRole.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        lblRole.setForeground(new java.awt.Color(255, 255, 255));
        lblRole.setText("Operator");
        jPanel8.add(lblRole, java.awt.BorderLayout.CENTER);

        panelIsisfooter.add(jPanel8, java.awt.BorderLayout.CENTER);

        panelFooterSidebar.add(panelIsisfooter, "card2");

        panelSidebar.add(panelFooterSidebar, java.awt.BorderLayout.PAGE_END);

        panelCenterSidebar.setMinimumSize(new java.awt.Dimension(240, 528));
        panelCenterSidebar.setOpaque(false);
        panelCenterSidebar.setLayout(new java.awt.BorderLayout());

        scrollBarr.setBackground(new java.awt.Color(20, 77, 50));
        scrollBarr.setBorder(null);
        scrollBarr.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBarr.setMinimumSize(new java.awt.Dimension(240, 528));
        scrollBarr.setOpaque(false);

        pnlMenu.setBackground(new java.awt.Color(20, 77, 50));
        pnlMenu.setMinimumSize(new java.awt.Dimension(240, 528));
        pnlMenu.setName(""); // NOI18N
        pnlMenu.setLayout(new javax.swing.BoxLayout(pnlMenu, javax.swing.BoxLayout.Y_AXIS));

        pnllblAbsensi.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        pnllblAbsensi.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnllblAbsensi.setOpaque(false);
        pnllblAbsensi.setPreferredSize(new java.awt.Dimension(240, 32));
        pnllblAbsensi.setLayout(new java.awt.CardLayout());

        jLabel5.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(137, 167, 153));
        jLabel5.setText("Absensi");
        pnllblAbsensi.add(jLabel5, "card2");

        pnlMenu.add(pnllblAbsensi);

        pnlBtndashAbs.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlBtndashAbs.setOpaque(false);
        pnlBtndashAbs.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlBtndashAbs.setLayout(new java.awt.CardLayout());

        btndashAbs.setBackground(new java.awt.Color(20, 77, 50));
        btndashAbs.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btndashAbs.setForeground(new java.awt.Color(137, 167, 153));
        btndashAbs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardNonaktif.png"))); // NOI18N
        btndashAbs.setText("Dashboard Absensi");
        btndashAbs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndashAbs.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardNonaktif.png"))); // NOI18N
        btndashAbs.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardAktif.png"))); // NOI18N
        btndashAbs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btndashAbs.setIconTextGap(8);
        btndashAbs.setMargin(new java.awt.Insets(0, 24, 0, 24));
        btndashAbs.setRolloverEnabled(false);
        btndashAbs.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardAktif.png"))); // NOI18N
        pnlBtndashAbs.add(btndashAbs, "card2");

        pnlMenu.add(pnlBtndashAbs);

        pnlSubMenuMaster.setOpaque(false);
        pnlSubMenuMaster.setLayout(new javax.swing.BoxLayout(pnlSubMenuMaster, javax.swing.BoxLayout.Y_AXIS));

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 24));
        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel14.setOpaque(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel14.setLayout(new java.awt.BorderLayout(8, 0));

        lblChevronDataMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IconChevronRight.png"))); // NOI18N
        lblChevronDataMaster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChevronDataMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chevronMouseClicked(evt);
            }
        });
        jPanel14.add(lblChevronDataMaster, java.awt.BorderLayout.LINE_END);

        btnDataMaster.setBackground(new java.awt.Color(20, 77, 50));
        btnDataMaster.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnDataMaster.setForeground(new java.awt.Color(137, 167, 153));
        btnDataMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataMasterNonaktif.png"))); // NOI18N
        btnDataMaster.setText("Data Master");
        btnDataMaster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataMaster.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataMasterNonaktif.png"))); // NOI18N
        btnDataMaster.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataMasterAktif.png"))); // NOI18N
        btnDataMaster.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataMaster.setIconTextGap(8);
        btnDataMaster.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDataMaster.setRolloverEnabled(false);
        btnDataMaster.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataMasterAktif.png"))); // NOI18N
        btnDataMaster.addChangeListener(this::btnDataMasterStateChanged);
        jPanel14.add(btnDataMaster, java.awt.BorderLayout.CENTER);

        pnlSubMenuMaster.add(jPanel14);

        pnlIsiDataMaster.setOpaque(false);
        pnlIsiDataMaster.setLayout(new javax.swing.BoxLayout(pnlIsiDataMaster, javax.swing.BoxLayout.Y_AXIS));

        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel15.setLayout(new java.awt.CardLayout());

        btnSiswa.setBackground(new java.awt.Color(20, 77, 50));
        btnSiswa.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnSiswa.setForeground(new java.awt.Color(137, 167, 153));
        btnSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnSiswa.setText("Siswa");
        btnSiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiswa.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnSiswa.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnSiswa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSiswa.setIconTextGap(8);
        btnSiswa.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnSiswa.setRolloverEnabled(false);
        btnSiswa.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel15.add(btnSiswa, "card3");

        pnlIsiDataMaster.add(jPanel15);

        jPanel17.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel17.setOpaque(false);
        jPanel17.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel17.setLayout(new java.awt.CardLayout());

        btnKelas.setBackground(new java.awt.Color(20, 77, 50));
        btnKelas.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnKelas.setForeground(new java.awt.Color(137, 167, 153));
        btnKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnKelas.setText("Kelas");
        btnKelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKelas.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnKelas.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnKelas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKelas.setIconTextGap(8);
        btnKelas.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnKelas.setRolloverEnabled(false);
        btnKelas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel17.add(btnKelas, "card3");

        pnlIsiDataMaster.add(jPanel17);

        jPanel18.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel18.setOpaque(false);
        jPanel18.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel18.setLayout(new java.awt.CardLayout());

        btnGuru.setBackground(new java.awt.Color(20, 77, 50));
        btnGuru.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnGuru.setForeground(new java.awt.Color(137, 167, 153));
        btnGuru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnGuru.setText("Guru / Wali");
        btnGuru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuru.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnGuru.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnGuru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuru.setIconTextGap(8);
        btnGuru.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnGuru.setRolloverEnabled(false);
        btnGuru.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel18.add(btnGuru, "card3");

        pnlIsiDataMaster.add(jPanel18);

        jPanel19.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel19.setOpaque(false);
        jPanel19.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel19.setLayout(new java.awt.CardLayout());

        btnQRSiswa.setBackground(new java.awt.Color(20, 77, 50));
        btnQRSiswa.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnQRSiswa.setForeground(new java.awt.Color(137, 167, 153));
        btnQRSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnQRSiswa.setText("QR Siswa");
        btnQRSiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQRSiswa.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnQRSiswa.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnQRSiswa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQRSiswa.setIconTextGap(8);
        btnQRSiswa.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnQRSiswa.setRolloverEnabled(false);
        btnQRSiswa.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel19.add(btnQRSiswa, "card3");

        pnlIsiDataMaster.add(jPanel19);

        pnlSubMenuMaster.add(pnlIsiDataMaster);

        pnlMenu.add(pnlSubMenuMaster);

        pnlSubMenuAbsensiQR.setOpaque(false);
        pnlSubMenuAbsensiQR.setLayout(new javax.swing.BoxLayout(pnlSubMenuAbsensiQR, javax.swing.BoxLayout.Y_AXIS));

        jPanel20.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 24));
        jPanel20.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel20.setOpaque(false);
        jPanel20.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel20.setLayout(new java.awt.BorderLayout(8, 0));

        lblChevronAbsensiQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IconChevronRight.png"))); // NOI18N
        lblChevronAbsensiQR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChevronAbsensiQR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chevronMouseClicked(evt);
            }
        });
        jPanel20.add(lblChevronAbsensiQR, java.awt.BorderLayout.LINE_END);

        btnAbsQR.setBackground(new java.awt.Color(20, 77, 50));
        btnAbsQR.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnAbsQR.setForeground(new java.awt.Color(137, 167, 153));
        btnAbsQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/absensiQrNonaktif.png"))); // NOI18N
        btnAbsQR.setText("Absensi QR");
        btnAbsQR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAbsQR.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/absensiQrNonaktif.png"))); // NOI18N
        btnAbsQR.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/absensiQrAktif.png"))); // NOI18N
        btnAbsQR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAbsQR.setIconTextGap(8);
        btnAbsQR.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAbsQR.setRolloverEnabled(false);
        btnAbsQR.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/absensiQrAktif.png"))); // NOI18N
        btnAbsQR.addChangeListener(this::btnAbsQRStateChanged);
        jPanel20.add(btnAbsQR, java.awt.BorderLayout.CENTER);

        pnlSubMenuAbsensiQR.add(jPanel20);

        pnlIsiAbsensiQR.setOpaque(false);
        pnlIsiAbsensiQR.setLayout(new javax.swing.BoxLayout(pnlIsiAbsensiQR, javax.swing.BoxLayout.Y_AXIS));

        jPanel22.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel22.setOpaque(false);
        jPanel22.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel22.setLayout(new java.awt.CardLayout());

        btnScanQR.setBackground(new java.awt.Color(20, 77, 50));
        btnScanQR.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnScanQR.setForeground(new java.awt.Color(137, 167, 153));
        btnScanQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnScanQR.setText("Scan QR");
        btnScanQR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnScanQR.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnScanQR.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnScanQR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnScanQR.setIconTextGap(8);
        btnScanQR.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnScanQR.setRolloverEnabled(false);
        btnScanQR.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel22.add(btnScanQR, "card3");

        pnlIsiAbsensiQR.add(jPanel22);

        jPanel23.setMaximumSize(new java.awt.Dimension(32767, 32));
        jPanel23.setOpaque(false);
        jPanel23.setPreferredSize(new java.awt.Dimension(240, 32));
        jPanel23.setLayout(new java.awt.CardLayout());

        btnHasilScan.setBackground(new java.awt.Color(20, 77, 50));
        btnHasilScan.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnHasilScan.setForeground(new java.awt.Color(137, 167, 153));
        btnHasilScan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnHasilScan.setText("Hasil Scan");
        btnHasilScan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHasilScan.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnHasilScan.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnHasilScan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHasilScan.setIconTextGap(8);
        btnHasilScan.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnHasilScan.setRolloverEnabled(false);
        btnHasilScan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        jPanel23.add(btnHasilScan, "card3");

        pnlIsiAbsensiQR.add(jPanel23);

        pnlSubMenuAbsensiQR.add(pnlIsiAbsensiQR);

        pnlMenu.add(pnlSubMenuAbsensiQR);

        pnlSubMenuRekapAbsensi.setOpaque(false);
        pnlSubMenuRekapAbsensi.setLayout(new javax.swing.BoxLayout(pnlSubMenuRekapAbsensi, javax.swing.BoxLayout.Y_AXIS));

        pnlHeaderRekapAbsensi.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 24));
        pnlHeaderRekapAbsensi.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlHeaderRekapAbsensi.setOpaque(false);
        pnlHeaderRekapAbsensi.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlHeaderRekapAbsensi.setLayout(new java.awt.BorderLayout(8, 0));

        lblChevronRekapAbsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IconChevronRight.png"))); // NOI18N
        lblChevronRekapAbsensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChevronRekapAbsensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chevronMouseClicked(evt);
            }
        });
        pnlHeaderRekapAbsensi.add(lblChevronRekapAbsensi, java.awt.BorderLayout.LINE_END);

        btnRekapAbsensi.setBackground(new java.awt.Color(20, 77, 50));
        btnRekapAbsensi.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnRekapAbsensi.setForeground(new java.awt.Color(137, 167, 153));
        btnRekapAbsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanNonaktif.png"))); // NOI18N
        btnRekapAbsensi.setText("Rekap & Laporan");
        btnRekapAbsensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRekapAbsensi.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanNonaktif.png"))); // NOI18N
        btnRekapAbsensi.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanAktif.png"))); // NOI18N
        btnRekapAbsensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekapAbsensi.setIconTextGap(8);
        btnRekapAbsensi.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnRekapAbsensi.setRolloverEnabled(false);
        btnRekapAbsensi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanAktif.png"))); // NOI18N
        btnRekapAbsensi.addChangeListener(this::btnRekapAbsensiStateChanged);
        pnlHeaderRekapAbsensi.add(btnRekapAbsensi, java.awt.BorderLayout.CENTER);

        pnlSubMenuRekapAbsensi.add(pnlHeaderRekapAbsensi);

        pnlIsiRekapAbsensi.setOpaque(false);
        pnlIsiRekapAbsensi.setLayout(new javax.swing.BoxLayout(pnlIsiRekapAbsensi, javax.swing.BoxLayout.Y_AXIS));

        pnlItemRekapAbsensi.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemRekapAbsensi.setOpaque(false);
        pnlItemRekapAbsensi.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemRekapAbsensi.setLayout(new java.awt.CardLayout());

        btnRekapAbsensiItem.setBackground(new java.awt.Color(20, 77, 50));
        btnRekapAbsensiItem.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnRekapAbsensiItem.setForeground(new java.awt.Color(137, 167, 153));
        btnRekapAbsensiItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnRekapAbsensiItem.setText("Rekap");
        btnRekapAbsensiItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRekapAbsensiItem.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnRekapAbsensiItem.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnRekapAbsensiItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekapAbsensiItem.setIconTextGap(8);
        btnRekapAbsensiItem.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnRekapAbsensiItem.setRolloverEnabled(false);
        btnRekapAbsensiItem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemRekapAbsensi.add(btnRekapAbsensiItem, "card3");

        pnlIsiRekapAbsensi.add(pnlItemRekapAbsensi);

        pnlItemLaporanAbsensi.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemLaporanAbsensi.setOpaque(false);
        pnlItemLaporanAbsensi.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemLaporanAbsensi.setLayout(new java.awt.CardLayout());

        btnLaporanAbsensi.setBackground(new java.awt.Color(20, 77, 50));
        btnLaporanAbsensi.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnLaporanAbsensi.setForeground(new java.awt.Color(137, 167, 153));
        btnLaporanAbsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnLaporanAbsensi.setText("Laporan");
        btnLaporanAbsensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporanAbsensi.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnLaporanAbsensi.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnLaporanAbsensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLaporanAbsensi.setIconTextGap(8);
        btnLaporanAbsensi.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnLaporanAbsensi.setRolloverEnabled(false);
        btnLaporanAbsensi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemLaporanAbsensi.add(btnLaporanAbsensi, "card3");

        pnlIsiRekapAbsensi.add(pnlItemLaporanAbsensi);

        pnlSubMenuRekapAbsensi.add(pnlIsiRekapAbsensi);

        pnlMenu.add(pnlSubMenuRekapAbsensi);

        pnlSectionPPDB.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        pnlSectionPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlSectionPPDB.setOpaque(false);
        pnlSectionPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlSectionPPDB.setLayout(new java.awt.CardLayout());

        lblSectionPPDB.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblSectionPPDB.setForeground(new java.awt.Color(137, 167, 153));
        lblSectionPPDB.setText("PPDB");
        pnlSectionPPDB.add(lblSectionPPDB, "card2");

        pnlMenu.add(pnlSectionPPDB);

        pnlBtnDashboardPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlBtnDashboardPPDB.setOpaque(false);
        pnlBtnDashboardPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlBtnDashboardPPDB.setLayout(new java.awt.CardLayout());

        btnDashboardPPDB.setBackground(new java.awt.Color(20, 77, 50));
        btnDashboardPPDB.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnDashboardPPDB.setForeground(new java.awt.Color(137, 167, 153));
        btnDashboardPPDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardNonaktif.png"))); // NOI18N
        btnDashboardPPDB.setText("Dashboard PPDB");
        btnDashboardPPDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboardPPDB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardNonaktif.png"))); // NOI18N
        btnDashboardPPDB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardAktif.png"))); // NOI18N
        btnDashboardPPDB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboardPPDB.setIconTextGap(8);
        btnDashboardPPDB.setMargin(new java.awt.Insets(0, 24, 0, 24));
        btnDashboardPPDB.setRolloverEnabled(false);
        btnDashboardPPDB.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboardAktif.png"))); // NOI18N
        pnlBtnDashboardPPDB.add(btnDashboardPPDB, "card2");

        pnlMenu.add(pnlBtnDashboardPPDB);

        pnlBtnDataPendaftar.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlBtnDataPendaftar.setOpaque(false);
        pnlBtnDataPendaftar.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlBtnDataPendaftar.setLayout(new java.awt.CardLayout());

        btnDataPendaftar.setBackground(new java.awt.Color(20, 77, 50));
        btnDataPendaftar.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnDataPendaftar.setForeground(new java.awt.Color(137, 167, 153));
        btnDataPendaftar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataPendaftarNonaktif.png"))); // NOI18N
        btnDataPendaftar.setText("Data Pendaftar");
        btnDataPendaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDataPendaftar.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataPendaftarNonaktif.png"))); // NOI18N
        btnDataPendaftar.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataPendaftarAktif.png"))); // NOI18N
        btnDataPendaftar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDataPendaftar.setIconTextGap(8);
        btnDataPendaftar.setMargin(new java.awt.Insets(0, 24, 0, 24));
        btnDataPendaftar.setRolloverEnabled(false);
        btnDataPendaftar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dataPendaftarAktif.png"))); // NOI18N
        pnlBtnDataPendaftar.add(btnDataPendaftar, "card2");

        pnlMenu.add(pnlBtnDataPendaftar);

        pnlBtnPenempatanKelas7.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlBtnPenempatanKelas7.setOpaque(false);
        pnlBtnPenempatanKelas7.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlBtnPenempatanKelas7.setLayout(new java.awt.CardLayout());

        btnPenempatanKelas7.setBackground(new java.awt.Color(20, 77, 50));
        btnPenempatanKelas7.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnPenempatanKelas7.setForeground(new java.awt.Color(137, 167, 153));
        btnPenempatanKelas7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penempatanKelasNonaktif.png"))); // NOI18N
        btnPenempatanKelas7.setText("Penempatan Kelas 7");
        btnPenempatanKelas7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPenempatanKelas7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penempatanKelasNonaktif.png"))); // NOI18N
        btnPenempatanKelas7.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penempatanKelasAktif.png"))); // NOI18N
        btnPenempatanKelas7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPenempatanKelas7.setIconTextGap(8);
        btnPenempatanKelas7.setMargin(new java.awt.Insets(0, 24, 0, 24));
        btnPenempatanKelas7.setRolloverEnabled(false);
        btnPenempatanKelas7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/penempatanKelasAktif.png"))); // NOI18N
        pnlBtnPenempatanKelas7.add(btnPenempatanKelas7, "card2");

        pnlMenu.add(pnlBtnPenempatanKelas7);

        pnlSubMenuRekapPPDB.setOpaque(false);
        pnlSubMenuRekapPPDB.setLayout(new javax.swing.BoxLayout(pnlSubMenuRekapPPDB, javax.swing.BoxLayout.Y_AXIS));

        pnlHeaderRekapPPDB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 24));
        pnlHeaderRekapPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlHeaderRekapPPDB.setOpaque(false);
        pnlHeaderRekapPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlHeaderRekapPPDB.setLayout(new java.awt.BorderLayout(8, 0));

        lblChevronRekapPPDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IconChevronRight.png"))); // NOI18N
        lblChevronRekapPPDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChevronRekapPPDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chevronMouseClicked(evt);
            }
        });
        pnlHeaderRekapPPDB.add(lblChevronRekapPPDB, java.awt.BorderLayout.LINE_END);

        btnRekapPPDB.setBackground(new java.awt.Color(20, 77, 50));
        btnRekapPPDB.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnRekapPPDB.setForeground(new java.awt.Color(137, 167, 153));
        btnRekapPPDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanNonaktif.png"))); // NOI18N
        btnRekapPPDB.setText("Rekap & Laporan");
        btnRekapPPDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRekapPPDB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanNonaktif.png"))); // NOI18N
        btnRekapPPDB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanAktif.png"))); // NOI18N
        btnRekapPPDB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekapPPDB.setIconTextGap(8);
        btnRekapPPDB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnRekapPPDB.setRolloverEnabled(false);
        btnRekapPPDB.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rekapLaporanAktif.png"))); // NOI18N
        btnRekapPPDB.addChangeListener(this::btnRekapPPDBStateChanged);
        pnlHeaderRekapPPDB.add(btnRekapPPDB, java.awt.BorderLayout.CENTER);

        pnlSubMenuRekapPPDB.add(pnlHeaderRekapPPDB);

        pnlIsiRekapPPDB.setOpaque(false);
        pnlIsiRekapPPDB.setLayout(new javax.swing.BoxLayout(pnlIsiRekapPPDB, javax.swing.BoxLayout.Y_AXIS));

        pnlItemRekapPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemRekapPPDB.setOpaque(false);
        pnlItemRekapPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemRekapPPDB.setLayout(new java.awt.CardLayout());

        btnRekapPPDBItem.setBackground(new java.awt.Color(20, 77, 50));
        btnRekapPPDBItem.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnRekapPPDBItem.setForeground(new java.awt.Color(137, 167, 153));
        btnRekapPPDBItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnRekapPPDBItem.setText("Rekap");
        btnRekapPPDBItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRekapPPDBItem.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnRekapPPDBItem.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnRekapPPDBItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRekapPPDBItem.setIconTextGap(8);
        btnRekapPPDBItem.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnRekapPPDBItem.setRolloverEnabled(false);
        btnRekapPPDBItem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemRekapPPDB.add(btnRekapPPDBItem, "card3");

        pnlIsiRekapPPDB.add(pnlItemRekapPPDB);

        pnlItemLaporanPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemLaporanPPDB.setOpaque(false);
        pnlItemLaporanPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemLaporanPPDB.setLayout(new java.awt.CardLayout());

        btnLaporanPPDB.setBackground(new java.awt.Color(20, 77, 50));
        btnLaporanPPDB.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnLaporanPPDB.setForeground(new java.awt.Color(137, 167, 153));
        btnLaporanPPDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnLaporanPPDB.setText("Laporan");
        btnLaporanPPDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporanPPDB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnLaporanPPDB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnLaporanPPDB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLaporanPPDB.setIconTextGap(8);
        btnLaporanPPDB.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnLaporanPPDB.setRolloverEnabled(false);
        btnLaporanPPDB.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemLaporanPPDB.add(btnLaporanPPDB, "card3");

        pnlIsiRekapPPDB.add(pnlItemLaporanPPDB);

        pnlSubMenuRekapPPDB.add(pnlIsiRekapPPDB);

        pnlMenu.add(pnlSubMenuRekapPPDB);

        pnlSectionSistem.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 24, 8, 24));
        pnlSectionSistem.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlSectionSistem.setOpaque(false);
        pnlSectionSistem.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlSectionSistem.setLayout(new java.awt.CardLayout());

        lblSectionSistem.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        lblSectionSistem.setForeground(new java.awt.Color(137, 167, 153));
        lblSectionSistem.setText("Sistem");
        pnlSectionSistem.add(lblSectionSistem, "card2");

        pnlMenu.add(pnlSectionSistem);

        pnlSubMenuPengaturan.setOpaque(false);
        pnlSubMenuPengaturan.setLayout(new javax.swing.BoxLayout(pnlSubMenuPengaturan, javax.swing.BoxLayout.Y_AXIS));

        pnlHeaderPengaturan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 24));
        pnlHeaderPengaturan.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlHeaderPengaturan.setOpaque(false);
        pnlHeaderPengaturan.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlHeaderPengaturan.setLayout(new java.awt.BorderLayout(8, 0));

        lblChevronPengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/IconChevronRight.png"))); // NOI18N
        lblChevronPengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChevronPengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chevronMouseClicked(evt);
            }
        });
        pnlHeaderPengaturan.add(lblChevronPengaturan, java.awt.BorderLayout.LINE_END);

        btnPengaturan.setBackground(new java.awt.Color(20, 77, 50));
        btnPengaturan.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnPengaturan.setForeground(new java.awt.Color(137, 167, 153));
        btnPengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pengaturanNonaktif.png"))); // NOI18N
        btnPengaturan.setText("Pengaturan");
        btnPengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPengaturan.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pengaturanNonaktif.png"))); // NOI18N
        btnPengaturan.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pengaturanAktif.png"))); // NOI18N
        btnPengaturan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPengaturan.setIconTextGap(8);
        btnPengaturan.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPengaturan.setRolloverEnabled(false);
        btnPengaturan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pengaturanAktif.png"))); // NOI18N
        btnPengaturan.addChangeListener(this::btnPengaturanStateChanged);
        pnlHeaderPengaturan.add(btnPengaturan, java.awt.BorderLayout.CENTER);

        pnlSubMenuPengaturan.add(pnlHeaderPengaturan);

        pnlIsiPengaturan.setOpaque(false);
        pnlIsiPengaturan.setLayout(new javax.swing.BoxLayout(pnlIsiPengaturan, javax.swing.BoxLayout.Y_AXIS));

        pnlItemJamAbsensi.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemJamAbsensi.setOpaque(false);
        pnlItemJamAbsensi.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemJamAbsensi.setLayout(new java.awt.CardLayout());

        btnJamAbsensi.setBackground(new java.awt.Color(20, 77, 50));
        btnJamAbsensi.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnJamAbsensi.setForeground(new java.awt.Color(137, 167, 153));
        btnJamAbsensi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnJamAbsensi.setText("Jam Absensi");
        btnJamAbsensi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJamAbsensi.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnJamAbsensi.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnJamAbsensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnJamAbsensi.setIconTextGap(8);
        btnJamAbsensi.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnJamAbsensi.setRolloverEnabled(false);
        btnJamAbsensi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemJamAbsensi.add(btnJamAbsensi, "card3");

        pnlIsiPengaturan.add(pnlItemJamAbsensi);

        pnlItemPPDB.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemPPDB.setOpaque(false);
        pnlItemPPDB.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemPPDB.setLayout(new java.awt.CardLayout());

        btnPPDB.setBackground(new java.awt.Color(20, 77, 50));
        btnPPDB.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnPPDB.setForeground(new java.awt.Color(137, 167, 153));
        btnPPDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnPPDB.setText("PPDB");
        btnPPDB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPPDB.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnPPDB.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnPPDB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPPDB.setIconTextGap(8);
        btnPPDB.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnPPDB.setRolloverEnabled(false);
        btnPPDB.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemPPDB.add(btnPPDB, "card3");

        pnlIsiPengaturan.add(pnlItemPPDB);

        pnlItemPengguna.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlItemPengguna.setOpaque(false);
        pnlItemPengguna.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlItemPengguna.setLayout(new java.awt.CardLayout());

        btnPengguna.setBackground(new java.awt.Color(20, 77, 50));
        btnPengguna.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnPengguna.setForeground(new java.awt.Color(137, 167, 153));
        btnPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnPengguna.setText("Pengguna");
        btnPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPengguna.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsNonaktif.png"))); // NOI18N
        btnPengguna.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        btnPengguna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPengguna.setIconTextGap(8);
        btnPengguna.setMargin(new java.awt.Insets(0, 24, 0, 34));
        btnPengguna.setRolloverEnabled(false);
        btnPengguna.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dotsAktif.png"))); // NOI18N
        pnlItemPengguna.add(btnPengguna, "card3");

        pnlIsiPengaturan.add(pnlItemPengguna);

        pnlSubMenuPengaturan.add(pnlIsiPengaturan);

        pnlMenu.add(pnlSubMenuPengaturan);

        pnlBtnProfil.setMaximumSize(new java.awt.Dimension(32767, 32));
        pnlBtnProfil.setOpaque(false);
        pnlBtnProfil.setPreferredSize(new java.awt.Dimension(240, 32));
        pnlBtnProfil.setLayout(new java.awt.CardLayout());

        btnProfil.setBackground(new java.awt.Color(20, 77, 50));
        btnProfil.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        btnProfil.setForeground(new java.awt.Color(137, 167, 153));
        btnProfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profilNonaktif.png"))); // NOI18N
        btnProfil.setText("Profil");
        btnProfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfil.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profilNonaktif.png"))); // NOI18N
        btnProfil.setDisabledSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profilAktif.png"))); // NOI18N
        btnProfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProfil.setIconTextGap(8);
        btnProfil.setMargin(new java.awt.Insets(0, 24, 0, 24));
        btnProfil.setRolloverEnabled(false);
        btnProfil.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profilAktif.png"))); // NOI18N
        pnlBtnProfil.add(btnProfil, "card2");

        pnlMenu.add(pnlBtnProfil);

        scrollBarr.setViewportView(pnlMenu);

        panelCenterSidebar.add(scrollBarr, java.awt.BorderLayout.CENTER);

        panelSidebar.add(panelCenterSidebar, java.awt.BorderLayout.CENTER);

        mainPanel.add(panelSidebar, java.awt.BorderLayout.LINE_START);

        panelMain.setOpaque(false);
        panelMain.setLayout(new java.awt.BorderLayout(16, 16));

        panelMainHeader.setBackground(new java.awt.Color(246, 247, 245));
        panelMainHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16));
        panelMainHeader.setPreferredSize(new java.awt.Dimension(992, 56));
        panelMainHeader.setLayout(new java.awt.BorderLayout());

        pnlSearchBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        pnlSearchBar.setPreferredSize(new java.awt.Dimension(320, 40));
        pnlSearchBar.putClientProperty("FlatLaf.style", "arc:999");
        pnlSearchBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchMousePressed(evt);
            }
        });
        pnlSearchBar.setLayout(new java.awt.BorderLayout());

        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchMousePressed(evt);
            }
        });
        pnlSearchBar.add(lblSearch, java.awt.BorderLayout.LINE_START);

        tField.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tField.setForeground(new java.awt.Color(22, 32, 27));
        tField.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8));
        tField.setCaretColor(new java.awt.Color(22, 32, 27));
        tField.setMargin(new java.awt.Insets(0, 8, 0, 0));
        tField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusChanged(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusChanged(evt);
            }
        });
        pnlSearchBar.add(tField, java.awt.BorderLayout.CENTER);

        panelMainHeader.add(pnlSearchBar, java.awt.BorderLayout.LINE_START);

        pnlHeaderRight.setOpaque(false);
        pnlHeaderRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 16, 0));

        lblDateTime.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        lblDateTime.setForeground(new java.awt.Color(102, 112, 107));
        lblDateTime.setText("Minggu, 18 Okt 2026 · 16.58 WIB");
        lblDateTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblDateTime.setPreferredSize(new java.awt.Dimension(212, 24));
        pnlHeaderRight.add(lblDateTime);

        pnlProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));
        pnlProfile.setOpaque(false);
        pnlProfile.setPreferredSize(new java.awt.Dimension(218, 40));
        pnlProfile.setLayout(new java.awt.BorderLayout(8, 0));

        pnlAvatar.setBackground(new java.awt.Color(245, 201, 168));
        pnlAvatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlAvatar.setMaximumSize(new java.awt.Dimension(40, 40));
        pnlAvatar.setMinimumSize(new java.awt.Dimension(40, 40));
        pnlAvatar.setPreferredSize(new java.awt.Dimension(40, 40));
        pnlAvatar.setLayout(new java.awt.BorderLayout());

        lblAvatar.setFont(new java.awt.Font("Plus Jakarta Sans", 1, 14)); // NOI18N
        lblAvatar.setForeground(new java.awt.Color(13, 59, 38));
        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvatar.setText("FA");
        lblAvatar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlAvatar.add(lblAvatar, java.awt.BorderLayout.CENTER);

        pnlProfile.add(pnlAvatar, java.awt.BorderLayout.LINE_START);

        pnlProfileText.setOpaque(false);
        pnlProfileText.setPreferredSize(new java.awt.Dimension(158, 40));
        pnlProfileText.setLayout(new java.awt.GridLayout(2, 1));

        lblUserName.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(22, 32, 27));
        lblUserName.setText("Fajar Anhari");
        lblUserName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblUserName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlProfileText.add(lblUserName);

        lblUserEmail.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 12)); // NOI18N
        lblUserEmail.setForeground(new java.awt.Color(102, 112, 107));
        lblUserEmail.setText("operator@darululum.sch.id");
        lblUserEmail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblUserEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlProfileText.add(lblUserEmail);

        pnlProfile.add(pnlProfileText, java.awt.BorderLayout.CENTER);

        pnlHeaderRight.add(pnlProfile);

        panelMainHeader.add(pnlHeaderRight, java.awt.BorderLayout.LINE_END);

        panelMain.add(panelMainHeader, java.awt.BorderLayout.PAGE_START);

        panelContent.setBackground(new java.awt.Color(246, 247, 245));
        panelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        panelContent.setLayout(new java.awt.CardLayout());
        panelContent.add(dashboarAbsensiPanel1, "card2");

        panelMain.add(panelContent, java.awt.BorderLayout.CENTER);

        mainPanel.add(panelMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchMousePressed(java.awt.event.MouseEvent evt) {
        // arahkan kursor ke kolom pencarian
        tField.requestFocusInWindow();
    }

    private void searchFocusChanged(java.awt.event.FocusEvent evt) {
        // ubah border kolom pencarian pas fokusnya berubah
        updateSearchBorder();
    }

    private void updateSearchBorder() {
        
        boolean fokus = tField.hasFocus();
        java.awt.Color c;
        if (fokus) {
            c = java.awt.Color.decode("#337253");
        } else {
            c = new java.awt.Color(51, 114, 83, 0);
        }

        pnlSearchBar.setBorder(new com.formdev.flatlaf.ui.FlatLineBorder(
                new java.awt.Insets(0, 12, 0, 12), c, 1.5f, 999));
    }

    // klik chevron utk buka menu parent yg sesuai
    private void chevronMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chevronMouseClicked
        // ambil chevron yang diklik
        Object sumber = evt.getSource();

        // ubah status parent; event state changed nanti buka atau nutup isinya
        if (sumber == lblChevronDataMaster) {
            // kalau kebuka, tutup; kalau tertutup, buka Data Master
            btnDataMaster.setSelected(!btnDataMaster.isSelected());
        } else if (sumber == lblChevronAbsensiQR) {
            // kalau kebuka, tutup; kalau tertutup, buka Absensi QR
            btnAbsQR.setSelected(!btnAbsQR.isSelected());
        } else if (sumber == lblChevronRekapAbsensi) {
            // kalau kebuka, tutup; kalau tertutup, buka Rekap Absensi
            btnRekapAbsensi.setSelected(!btnRekapAbsensi.isSelected());
        } else if (sumber == lblChevronRekapPPDB) {
            // kalau kebuka, tutup; kalau tertutup, buka Rekap PPDB
            btnRekapPPDB.setSelected(!btnRekapPPDB.isSelected());
        } else if (sumber == lblChevronPengaturan) {
            // kalau kebuka, tutup; kalau tertutup, buka Pengaturan
            btnPengaturan.setSelected(!btnPengaturan.isSelected());
        }
    }//GEN-LAST:event_chevronMouseClicked

    // semua tombol state changed pakai logika yg sama
    private void btnDataMasterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnDataMasterStateChanged
        // perbarui isi submenu data master
        updateSubMenuState(btnDataMaster, lblChevronDataMaster, pnlIsiDataMaster,
                new JToggleButton[]{btnSiswa, btnKelas, btnGuru, btnQRSiswa});
    }//GEN-LAST:event_btnDataMasterStateChanged

    private void btnAbsQRStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnAbsQRStateChanged
        // perbarui isi submenu absensi QR
        updateSubMenuState(btnAbsQR, lblChevronAbsensiQR, pnlIsiAbsensiQR,
                new JToggleButton[]{btnScanQR, btnHasilScan});
    }//GEN-LAST:event_btnAbsQRStateChanged

    private void btnRekapAbsensiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnRekapAbsensiStateChanged
        // perbarui isi submenu rekap absensi
        updateSubMenuState(btnRekapAbsensi, lblChevronRekapAbsensi, pnlIsiRekapAbsensi,
                new JToggleButton[]{btnRekapAbsensiItem, btnLaporanAbsensi});
    }//GEN-LAST:event_btnRekapAbsensiStateChanged

    private void btnRekapPPDBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnRekapPPDBStateChanged
        // perbarui isi submenu rekap PPDB
        updateSubMenuState(btnRekapPPDB, lblChevronRekapPPDB, pnlIsiRekapPPDB,
                new JToggleButton[]{btnRekapPPDBItem, btnLaporanPPDB});
    }//GEN-LAST:event_btnRekapPPDBStateChanged

    private void btnPengaturanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnPengaturanStateChanged
        // perbarui isi submenu pengaturan
        updateSubMenuState(btnPengaturan, lblChevronPengaturan, pnlIsiPengaturan,
                new JToggleButton[]{btnJamAbsensi, btnPPDB, btnPengguna});
    }//GEN-LAST:event_btnPengaturanStateChanged

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // tutup halaman utama lalu balik ke halaman login
        dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // pakai tampilan FlatLaf lalu buka halaman utama
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        System.setProperty("sun.java2d.uiScale", "1.25");
        try {
            UIManager.put("Component.focusColor", Color.decode("#337253"));
            UIManager.put("Component.focusedBorderColor", Color.decode("#337253"));
            UIManager.put("Component.accentColor", Color.decode("#337253"));
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAbsQR;
    private javax.swing.JToggleButton btnDashboardPPDB;
    private javax.swing.JToggleButton btnDataMaster;
    private javax.swing.JToggleButton btnDataPendaftar;
    private javax.swing.JToggleButton btnGuru;
    private javax.swing.JToggleButton btnHasilScan;
    private javax.swing.JToggleButton btnJamAbsensi;
    private javax.swing.JToggleButton btnKelas;
    private javax.swing.JToggleButton btnLaporanAbsensi;
    private javax.swing.JToggleButton btnLaporanPPDB;
    private javax.swing.JButton btnLogout;
    private javax.swing.JToggleButton btnPPDB;
    private javax.swing.JToggleButton btnPenempatanKelas7;
    private javax.swing.JToggleButton btnPengaturan;
    private javax.swing.JToggleButton btnPengguna;
    private javax.swing.JToggleButton btnProfil;
    private javax.swing.JToggleButton btnQRSiswa;
    private javax.swing.JToggleButton btnRekapAbsensi;
    private javax.swing.JToggleButton btnRekapAbsensiItem;
    private javax.swing.JToggleButton btnRekapPPDB;
    private javax.swing.JToggleButton btnRekapPPDBItem;
    private javax.swing.JToggleButton btnScanQR;
    private javax.swing.JToggleButton btnSiswa;
    private javax.swing.JToggleButton btndashAbs;
    private views.absensi.DashboarAbsensiPanel dashboarAbsensiPanel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblChevronAbsensiQR;
    private javax.swing.JLabel lblChevronDataMaster;
    private javax.swing.JLabel lblChevronPengaturan;
    private javax.swing.JLabel lblChevronRekapAbsensi;
    private javax.swing.JLabel lblChevronRekapPPDB;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblNamaSekolah;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSectionPPDB;
    private javax.swing.JLabel lblSectionSistem;
    private javax.swing.JLabel lblUserEmail;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelCenterSidebar;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelFooterSidebar;
    private javax.swing.JPanel panelHeaderSidebar;
    private javax.swing.JPanel panelIsisfooter;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelMainHeader;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel pnlAvatar;
    private javax.swing.JPanel pnlBtnDashboardPPDB;
    private javax.swing.JPanel pnlBtnDataPendaftar;
    private javax.swing.JPanel pnlBtnPenempatanKelas7;
    private javax.swing.JPanel pnlBtnProfil;
    private javax.swing.JPanel pnlBtndashAbs;
    private javax.swing.JPanel pnlHeaderPengaturan;
    private javax.swing.JPanel pnlHeaderRekapAbsensi;
    private javax.swing.JPanel pnlHeaderRekapPPDB;
    private javax.swing.JPanel pnlHeaderRight;
    private javax.swing.JPanel pnlIsiAbsensiQR;
    private javax.swing.JPanel pnlIsiDataMaster;
    private javax.swing.JPanel pnlIsiPengaturan;
    private javax.swing.JPanel pnlIsiRekapAbsensi;
    private javax.swing.JPanel pnlIsiRekapPPDB;
    private javax.swing.JPanel pnlItemJamAbsensi;
    private javax.swing.JPanel pnlItemLaporanAbsensi;
    private javax.swing.JPanel pnlItemLaporanPPDB;
    private javax.swing.JPanel pnlItemPPDB;
    private javax.swing.JPanel pnlItemPengguna;
    private javax.swing.JPanel pnlItemRekapAbsensi;
    private javax.swing.JPanel pnlItemRekapPPDB;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlProfileText;
    private javax.swing.JPanel pnlSearchBar;
    private javax.swing.JPanel pnlSectionPPDB;
    private javax.swing.JPanel pnlSectionSistem;
    private javax.swing.JPanel pnlSubMenuAbsensiQR;
    private javax.swing.JPanel pnlSubMenuMaster;
    private javax.swing.JPanel pnlSubMenuPengaturan;
    private javax.swing.JPanel pnlSubMenuRekapAbsensi;
    private javax.swing.JPanel pnlSubMenuRekapPPDB;
    private javax.swing.JPanel pnllblAbsensi;
    private javax.swing.JScrollPane scrollBarr;
    private javax.swing.JTextField tField;
    // End of variables declaration//GEN-END:variables
}
