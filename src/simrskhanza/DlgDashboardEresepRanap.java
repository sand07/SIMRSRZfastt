package simrskhanza;

import fungsi.BackgroundMusic;
import fungsi.WarnaTable;
import fungsi.WarnaTableDasbordApotekRanap;
import fungsi.WarnaTableResepRanap1;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgPemberianObat;
import inventory.DlgResepObat;
import inventory.DlgReturJual;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.codehaus.groovy.syntax.Numbers;
import setting.DlgSetAplikasi;

public class DlgDashboardEresepRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    public DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, cito = 0, iniResep = 0;
    private String norawat = "", norm = "", idObat = "", jenisResep = "", resepPulang = "";
    public Timer tEresep;
    private BackgroundMusic music;

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgDashboardEresepRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "No. Telp/HP.", "Ruang Rawat",
            "Cara Bayar", "Jlh. Item Obat", "Status", "cekResepCito", "Resep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(98);
            }
        }
//        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());
        tbPasien.setDefaultRenderer(Object.class, new WarnaTableDasbordApotekRanap());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Cek", "no_id", "no_rawat", "No.", "Tgl. Resep", "Jam", "Deskripsi Obat/Alkes",
            "Status", "Dokter Yang Meresepkan", "Jns. Resep", "Resep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbdaftarResep.setModel(tabMode1);
        tbdaftarResep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbdaftarResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbdaftarResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(30);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(98);
            }
        }
        tbdaftarResep.setDefaultRenderer(Object.class, new WarnaTableResepRanap1());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDashboardEresepRanap")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                        btnPenjab.requestFocus();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgDashboardEresepRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        OtomatisRefresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnDataPemberianObat = new javax.swing.JMenuItem();
        MnInputPemberianObat = new javax.swing.JMenuItem();
        MnCekNotif = new javax.swing.JMenuItem();
        MnCekNotifCito = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        MnContengBelum = new javax.swing.JMenuItem();
        MnCetakResepThermal = new javax.swing.JMenuItem();
        MnCetakResepBil = new javax.swing.JMenuItem();
        MnCetakResepA5 = new javax.swing.JMenuItem();
        TdataQRresep = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel23 = new widget.Label();
        tglCari1 = new widget.Tanggal();
        jLabel24 = new widget.Label();
        tglCari2 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        cmbRuang = new widget.ComboBox();
        jLabel22 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        BtnQRcode = new widget.Button();
        panelGlass8 = new widget.panelisi();
        ChkAutoRefres = new widget.CekBox();
        jLabel13 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAllCari = new widget.Button();
        BtnKeluar = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
        jPanel5 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbdaftarResep = new widget.Table();
        jPanel6 = new javax.swing.JPanel();
        panelGlass11 = new widget.panelisi();
        scrollPane3 = new widget.ScrollPane();
        gambarQR = new Painter();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnDataPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianObat.setText("Data Pemberian Obat");
        MnDataPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianObat.setIconTextGap(5);
        MnDataPemberianObat.setName("MnDataPemberianObat"); // NOI18N
        MnDataPemberianObat.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDataPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnDataPemberianObat);

        MnInputPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputPemberianObat.setText("Input Pemberian Obat");
        MnInputPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputPemberianObat.setIconTextGap(5);
        MnInputPemberianObat.setName("MnInputPemberianObat"); // NOI18N
        MnInputPemberianObat.setPreferredSize(new java.awt.Dimension(190, 26));
        MnInputPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnInputPemberianObat);

        MnCekNotif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNotif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/icons8-audio-24.png"))); // NOI18N
        MnCekNotif.setText("Cek Notif Resep Ranap");
        MnCekNotif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekNotif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekNotif.setIconTextGap(5);
        MnCekNotif.setName("MnCekNotif"); // NOI18N
        MnCekNotif.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCekNotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNotifActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCekNotif);

        MnCekNotifCito.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNotifCito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/icons8-audio-24.png"))); // NOI18N
        MnCekNotifCito.setText("Cek Notif Resep CITO");
        MnCekNotifCito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekNotifCito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekNotifCito.setIconTextGap(5);
        MnCekNotifCito.setName("MnCekNotifCito"); // NOI18N
        MnCekNotifCito.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCekNotifCito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNotifCitoActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCekNotifCito);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemua.setIconTextGap(5);
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(180, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hilangkan Conteng Semua");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(180, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusConteng);

        MnContengBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengBelum.setText("Conteng Semua Yang Belum");
        MnContengBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengBelum.setIconTextGap(5);
        MnContengBelum.setName("MnContengBelum"); // NOI18N
        MnContengBelum.setPreferredSize(new java.awt.Dimension(180, 26));
        MnContengBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengBelumActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnContengBelum);

        MnCetakResepThermal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepThermal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakResepThermal.setText("Resep Kertas Thermal");
        MnCetakResepThermal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepThermal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepThermal.setIconTextGap(5);
        MnCetakResepThermal.setName("MnCetakResepThermal"); // NOI18N
        MnCetakResepThermal.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakResepThermal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepThermalActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakResepThermal);

        MnCetakResepBil.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepBil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakResepBil.setText("Resep Kertas Bill");
        MnCetakResepBil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepBil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepBil.setIconTextGap(5);
        MnCetakResepBil.setName("MnCetakResepBil"); // NOI18N
        MnCetakResepBil.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakResepBil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepBilActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakResepBil);

        MnCetakResepA5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakResepA5.setText("Resep Kertas HVS/A5");
        MnCetakResepA5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepA5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepA5.setIconTextGap(5);
        MnCetakResepA5.setName("MnCetakResepA5"); // NOI18N
        MnCetakResepA5.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakResepA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepA5ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakResepA5);

        TdataQRresep.setColumns(20);
        TdataQRresep.setRows(5);
        TdataQRresep.setName("TdataQRresep"); // NOI18N
        TdataQRresep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataQRresepKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dashboard e-Resep Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 105));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 16));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tanggal : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel23);

        tglCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        tglCari1.setDisplayFormat("dd-MM-yyyy");
        tglCari1.setName("tglCari1"); // NOI18N
        tglCari1.setOpaque(false);
        tglCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(tglCari1);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("s.d.");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel24);

        tglCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-05-2024" }));
        tglCari2.setDisplayFormat("dd-MM-yyyy");
        tglCari2.setName("tglCari2"); // NOI18N
        tglCari2.setOpaque(false);
        tglCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(tglCari2);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Ruang Perawatan :");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass9.add(jLabel21);

        cmbRuang.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRuang.setName("cmbRuang"); // NOI18N
        cmbRuang.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass9.add(cmbRuang);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Cara Bayar : ");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel22);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(kdpnj);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        nmpnj.setPreferredSize(new java.awt.Dimension(210, 23));
        panelGlass9.add(nmpnj);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelGlass9.add(btnPenjab);

        BtnQRcode.setForeground(new java.awt.Color(0, 0, 0));
        BtnQRcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnQRcode.setMnemonic('D');
        BtnQRcode.setText("QR Code Resep");
        BtnQRcode.setToolTipText("Alt+D");
        BtnQRcode.setName("BtnQRcode"); // NOI18N
        BtnQRcode.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnQRcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQRcodeActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnQRcode);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 16));

        ChkAutoRefres.setBackground(new java.awt.Color(255, 255, 250));
        ChkAutoRefres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAutoRefres.setForeground(new java.awt.Color(0, 0, 0));
        ChkAutoRefres.setText("Aktifkan Auto Refresh Data");
        ChkAutoRefres.setBorderPainted(true);
        ChkAutoRefres.setBorderPaintedFlat(true);
        ChkAutoRefres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkAutoRefres.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setName("ChkAutoRefres"); // NOI18N
        ChkAutoRefres.setPreferredSize(new java.awt.Dimension(190, 23));
        ChkAutoRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAutoRefresActionPerformed(evt);
            }
        });
        panelGlass8.add(ChkAutoRefres);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Key Word : ");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass8.add(jLabel13);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setHighlighter(null);
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCari);

        BtnAllCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllCari.setMnemonic('D');
        BtnAllCari.setText("Semua Data");
        BtnAllCari.setToolTipText("Alt+D");
        BtnAllCari.setName("BtnAllCari"); // NOI18N
        BtnAllCari.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAllCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllCariActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnAllCari);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Daftar Pasien Yang Telah Diresepkan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("");
        tbPasien.setComponentPopupMenu(jPopupMenu);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        jPanel4.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Resep Obat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbdaftarResep.setToolTipText("");
        tbdaftarResep.setComponentPopupMenu(jPopupMenu1);
        tbdaftarResep.setName("tbdaftarResep"); // NOI18N
        Scroll1.setViewportView(tbdaftarResep);

        jPanel5.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(44, 270));
        jPanel6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Untuk Membantu Proses Pencarian Ke Rak Obat Scan QR Code Ini ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setPreferredSize(new java.awt.Dimension(230, 230));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        gambarQR.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane3.setViewportView(gambarQR);

        panelGlass11.add(scrollPane3);

        jPanel6.add(panelGlass11, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel5);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPasienMouseClicked

    private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPasienKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllCariActionPerformed
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnAllCariActionPerformed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgDashboardEresepRanap");
        penjab.onCari();
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void MnCetakResepThermalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepThermalActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode.getRowCount() != 0 && tabMode1.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //cek contengnya
            x = 0;
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }
            
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item obat yg. dipilih...!!");
                tampilResep();
                tbdaftarResep.requestFocus();
            } else if (x > 1) {
                idObat = "";
                jenisResep = "";
                cito = 0;
                iniResep = 0;
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        }
                    }
                }

                //cek resep cito
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 9).toString().equals("CITO")) {
                        cito++;
                    }
                }

                if (cito == 0) {
                    jenisResep = "BIASA";
                } else {
                    jenisResep = "CITO";
                }

                //cek resep pulang
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 10).toString().equals("Pulang")) {
                        iniResep++;
                    }
                }
                
                if (iniResep == 0) {
                    resepPulang = "Dalam Perawatan";
                } else {
                    resepPulang = "Pulang";
                }

                Map<String, Object> param = new HashMap<>();
                param.put("norawat", norawat);
                param.put("resep", jenisResep + " (" + resepPulang + ")");
                param.put("pasien", Sequel.cariIsi("select concat(p.no_rkm_medis,' - ',p.nm_pasien) from reg_periksa rp "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + norawat + "'"));
                param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + norawat + "'"));
                param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                param.put("tglcetak", Sequel.cariIsi("select concat(date_format(now(),'%d-%m-%Y'),', Jam : ',time_format(now(),'%H:%i'))"));
                param.put("dokterPeresep", Sequel.cariIsi("SELECT d.nm_dokter from catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                        + "where noId in (" + idObat + ") order by noId desc limit 1"));

                Valid.MyReport("rptStrukResepRanap.jasper", "report", "::[ Struk Resep Dokter Rawat Inap Kertas Thermal ]::",
                        " SELECT *, concat(DATE_FORMAT(tgl_perawatan,'%d-%m-%Y'),' / ',TIME_FORMAT(jam_perawatan,'%H:%i')) tgl "
                        + "from catatan_resep_ranap where noId in (" + idObat + ") order by status, noId desc", param);
                MnHapusContengActionPerformed(null);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnCetakResepThermalActionPerformed

    private void MnDataPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianObatActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            tbPasien.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", norawat) > 0) {
                DlgPemberianObat formobat = new DlgPemberianObat(null, false);
                formobat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                formobat.setLocationRelativeTo(internalFrame1);
                formobat.isCek();
                formobat.setNoRm(norawat, tglCari1.getDate(), tglCari2.getDate(), "ranap");
                formobat.tampilPO();
                formobat.setVisible(true);
            } else {
                DlgPemberianObat formobat = new DlgPemberianObat(null, false);
                formobat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                formobat.setLocationRelativeTo(internalFrame1);
                formobat.isCek();
                formobat.setNoRm(norawat, tglCari1.getDate(), tglCari2.getDate(), "ralan");
                formobat.tampilPO();
                formobat.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnDataPemberianObatActionPerformed

    private void MnInputPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputPemberianObatActionPerformed
        if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel..!!!");
        } else {
            akses.setform("DlgDashboardEresepRanap");
            dlgobt.setNoRm(norawat, tglCari2.getDate(), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H')"), 
                    Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%i')"), Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%s')"), false);
            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgobt.isCek();
            dlgobt.tampil();
            dlgobt.setLocationRelativeTo(internalFrame1);
            dlgobt.setVisible(true);
        }
    }//GEN-LAST:event_MnInputPemberianObatActionPerformed

    private void ChkAutoRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAutoRefresActionPerformed
        if (ChkAutoRefres.isSelected() == true) {            
            tEresep.start();
            JOptionPane.showMessageDialog(rootPane, "Data pasien akan di refresh otomatis per 5 menit sekali,..!!");
            tampil();
        } else {
            tEresep.stop();
            tampil();
        }
    }//GEN-LAST:event_ChkAutoRefresActionPerformed

    private void TdataQRresepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataQRresepKeyPressed

    }//GEN-LAST:event_TdataQRresepKeyPressed

    private void MnCetakResepA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepA5ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode.getRowCount() != 0 && tabMode1.getRowCount() != 0) {
            x = 0;
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Utk. mencetak resep obat silahkan conteng item yg. dipilih...!!!!");
                tbdaftarResep.requestFocus();
                tampilResep();
            } else if (x > 0) {
                idObat = "";
                jenisResep = "";
                cito = 0;
                iniResep = 0;
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        }                        
                    }
                }
                
                //cek resep cito
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 9).toString().equals("CITO")) {
                        cito++;
                    }
                }

                if (cito == 0) {
                    jenisResep = "BIASA";
                } else {
                    jenisResep = "CITO";
                }

                //cek resep pulang
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 10).toString().equals("Pulang")) {
                        iniResep++;
                    }
                }
                
                if (iniResep == 0) {
                    resepPulang = "Dalam Perawatan";
                } else {
                    resepPulang = "Pulang";
                }

                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));

                if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + norawat + "' and jnspelayanan='1'") == 0) {
                    param.put("nosep", "-");
                } else {
                    param.put("nosep", Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + norawat + "' and jnspelayanan='1' order by tglsep desc limit 1"));
                }

                param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1") + " (Resep : " + jenisResep + " - " + resepPulang + ")");
                
                Valid.MyReport("rptResepRanap.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                        "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                        + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                        + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                        + "FROM catatan_resep_ranap c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                        + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                        + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                        + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                        + "c.no_rawat = '" + norawat + "' AND c.noId IN (" + idObat + ") ORDER BY c.noId", param);
                MnHapusContengActionPerformed(null);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnCetakResepA5ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuang);
        tampilAwal();
    }//GEN-LAST:event_formWindowOpened

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
        } else {
            TdataQRresep.setText("");
            ((Painter) gambarQR).setImage("");
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 2).equals(norawat)) {
                    tbdaftarResep.setValueAt(Boolean.TRUE, i, 0);
                } 
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnContengBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengBelumActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
        } else {
            TdataQRresep.setText("");
            ((Painter) gambarQR).setImage("");
            //cek status belum dulu
            x = 0;
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 7).toString().equals("BELUM")) {
                    x++;
                }
            }
            
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Item resep obat dg. status BELUM tidak ditemukan...!!!!");
            } else if (x > 0) {
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 7).toString().equals("BELUM")) {
                        tbdaftarResep.setValueAt(Boolean.TRUE, i, 0);
                    }
                }
            }
        }
    }//GEN-LAST:event_MnContengBelumActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
        } else {
            TdataQRresep.setText("");
            ((Painter) gambarQR).setImage("");
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 2).equals(norawat)) {
                    tbdaftarResep.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void MnCetakResepBilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepBilActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbPasien.requestFocus();
        } else if (tabMode.getRowCount() != 0 && tabMode1.getRowCount() != 0) {
            x = 0;
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }
           
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Utk. mencetak resep obat silahkan conteng item yg. dipilih...!!!!");
                tbdaftarResep.requestFocus();
                tampilResep();
            } else if (x > 0) {
                idObat = "";
                jenisResep = "";
                cito = 0;
                iniResep = 0;
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                        if (idObat.equals("")) {
                            idObat = "'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        } else {
                            idObat = idObat + ",'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                        }
                    }
                }
                
                //cek resep cito
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 9).toString().equals("CITO")) {
                        cito++;
                    }
                }

                if (cito == 0) {
                    jenisResep = "BIASA";
                } else {
                    jenisResep = "CITO";
                }

                //cek resep pulang
                for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                    if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")
                            && tbdaftarResep.getValueAt(i, 10).toString().equals("Pulang")) {
                        iniResep++;
                    }
                }
                
                if (iniResep == 0) {
                    resepPulang = "Dalam Perawatan";
                } else {
                    resepPulang = "Pulang";
                }

                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("norawat", norawat);
                param.put("resep", jenisResep + " (" + resepPulang + ")");
                param.put("pasien", Sequel.cariIsi("select concat(p.no_rkm_medis,' - ',p.nm_pasien) from reg_periksa rp "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + norawat + "'"));
                param.put("carabyr", Sequel.cariIsi("select pj.png_jawab from reg_periksa rp "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + norawat + "'"));
                param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                param.put("dokterPeresep", Sequel.cariIsi("SELECT d.nm_dokter from catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter "
                        + "where noId in (" + idObat + ") order by noId desc limit 1"));

                Valid.MyReport("rptCatatanResep.jasper", "report", "::[ Cetak e-Resep ]::",
                        "SELECT *, concat(DATE_FORMAT(tgl_perawatan,'%d-%m-%Y'),' / ',TIME_FORMAT(jam_perawatan,'%H:%i')) tgl "
                        + "from catatan_resep_ranap where noId in (" + idObat + ") order by status, noId desc", param);
                MnHapusContengActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnCetakResepBilActionPerformed

    private void BtnQRcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQRcodeActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data masih kosong. Tidak ada data yang bisa diconteng...!!!!");
        } else {
            //cek contengnya
            x = 0;
            for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
                if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item obat yg. dipilih utk. QR code nya...!!");
                tampilResep();
                tbdaftarResep.requestFocus();
            } else if (x > 1) {
                tampilQR();
            }
        }
    }//GEN-LAST:event_BtnQRcodeActionPerformed

    private void MnCekNotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNotifActionPerformed
        notifAlarmResepRanap();
    }//GEN-LAST:event_MnCekNotifActionPerformed

    private void MnCekNotifCitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNotifCitoActionPerformed
        notifAlarmResepRanapCito();
    }//GEN-LAST:event_MnCekNotifCitoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDashboardEresepRanap dialog = new DlgDashboardEresepRanap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAllCari;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnQRcode;
    public widget.CekBox ChkAutoRefres;
    private javax.swing.JMenuItem MnCekNotif;
    private javax.swing.JMenuItem MnCekNotifCito;
    private javax.swing.JMenuItem MnCetakResepA5;
    private javax.swing.JMenuItem MnCetakResepBil;
    private javax.swing.JMenuItem MnCetakResepThermal;
    private javax.swing.JMenuItem MnContengBelum;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnDataPemberianObat;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnInputPemberianObat;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextArea TdataQRresep;
    private widget.Button btnPenjab;
    private widget.ComboBox cmbRuang;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel13;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdpnj;
    private widget.TextBox nmpnj;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbPasien;
    private widget.Table tbdaftarResep;
    private widget.Tanggal tglCari1;
    private widget.Tanggal tglCari2;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
        ((Painter) gambarQR).setImage("");
        try {
            if (cmbRuang.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT cr.no_rawat, p.no_rkm_medis, concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') pasienya, p.no_tlp, b.nm_gedung, "
                        + "pj.png_jawab, COUNT(cr.no_rawat) jlh_item_obat, cr.status FROM catatan_resep_ranap cr "
                        + "inner join reg_periksa rp on rp.no_rawat=cr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kamar_inap ki on ki.no_rawat=cr.no_rawat "
                        + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and cr.no_rawat like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and p.no_rkm_medis like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and p.no_tlp like ? "
                        + "GROUP BY cr.no_rawat ORDER BY cr.tgl_perawatan, cr.jam_perawatan");
            } else {
                ps = koneksi.prepareStatement("SELECT cr.no_rawat, p.no_rkm_medis, concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') pasienya, p.no_tlp, b.nm_gedung, "
                        + "pj.png_jawab, COUNT(cr.no_rawat) jlh_item_obat, cr.status FROM catatan_resep_ranap cr "
                        + "inner join reg_periksa rp on rp.no_rawat=cr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kamar_inap ki on ki.no_rawat=cr.no_rawat "
                        + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and cr.no_rawat like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and p.no_rkm_medis like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') like ? or "
                        + "cr.tgl_perawatan between ? and ? and cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and p.no_tlp like ? "
                        + "GROUP BY cr.no_rawat ORDER BY cr.tgl_perawatan, cr.jam_perawatan");
            }

            try {
                if (cmbRuang.getSelectedIndex() == 0) {
                    ps.setString(1, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(6, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(7, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(10, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(11, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(15, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(16, "%" + TCari.getText().trim() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(4, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(7, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(8, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(9, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(12, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(13, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(14, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                    ps.setString(18, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(19, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(20, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("pasienya"),
                        rs.getString("no_tlp"),
                        rs.getString("nm_gedung"),
                        rs.getString("png_jawab"),
                        rs.getString("jlh_item_obat"),
                        rs.getString("status"),
                        Sequel.cariIsi("SELECT count(-1) from catatan_resep_ranap where no_rawat='" + rs.getString("no_rawat") + "' and status='belum' and jenis_resep='cito'")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDashboardEresepRanap.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilAwal() {
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
        ((Painter) gambarQR).setImage("");
        try {
            if (cmbRuang.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT cr.no_rawat, p.no_rkm_medis, concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') pasienya, p.no_tlp, b.nm_gedung, "
                        + "pj.png_jawab, COUNT(cr.no_rawat) jlh_item_obat, cr.status FROM catatan_resep_ranap cr "
                        + "inner join reg_periksa rp on rp.no_rawat=cr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kamar_inap ki on ki.no_rawat=cr.no_rawat "
                        + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and cr.no_rawat like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and p.no_rkm_medis like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and rp.kd_pj like ? and p.no_tlp like ? "
                        + "GROUP BY cr.no_rawat ORDER BY cr.tgl_perawatan, cr.jam_perawatan");
            } else {
                ps = koneksi.prepareStatement("SELECT cr.no_rawat, p.no_rkm_medis, concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') pasienya, p.no_tlp, b.nm_gedung, "
                        + "pj.png_jawab, COUNT(cr.no_rawat) jlh_item_obat, cr.status FROM catatan_resep_ranap cr "
                        + "inner join reg_periksa rp on rp.no_rawat=cr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kamar_inap ki on ki.no_rawat=cr.no_rawat "
                        + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and cr.no_rawat like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and p.no_rkm_medis like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and concat(p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,')') like ? or "
                        + "cr.status='belum' and ki.stts_pulang<>'Pindah Kamar' and b.nm_gedung like ? and rp.kd_pj like ? and p.no_tlp like ? "
                        + "GROUP BY cr.no_rawat ORDER BY cr.tgl_perawatan, cr.jam_perawatan");
            }

            try {
                if (cmbRuang.getSelectedIndex() == 0) {
                    ps.setString(1, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                } else {
                    ps.setString(1, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(2, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(5, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(8, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + cmbRuang.getSelectedItem().toString() + "%");
                    ps.setString(11, "%" + kdpnj.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("pasienya"),
                        rs.getString("no_tlp"),
                        rs.getString("nm_gedung"),
                        rs.getString("png_jawab"),
                        rs.getString("jlh_item_obat"),
                        rs.getString("status"),
                        Sequel.cariIsi("SELECT count(-1) from catatan_resep_ranap where no_rawat='" + rs.getString("no_rawat") + "' and status='belum' and jenis_resep='cito'")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDashboardEresepRanap.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        tglCari1.setDate(new Date());
        tglCari2.setDate(new Date());
        cmbRuang.setSelectedIndex(0);
        kdpnj.setText("");
        nmpnj.setText("");
        TCari.setText("");
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
    }

    private void getData() {
        norawat = "";
        norm = "";
        TdataQRresep.setText("");
        ((Painter) gambarQR).setImage("");

        if (tbPasien.getSelectedRow() != -1) {
            norawat = tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString();
            norm = tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString();
            tampilResep();
        }
    }

    public JTable getTable() {
        return tbPasien;
    }

    private void tampilResep() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT cr.noId, cr.no_rawat, DATE_FORMAT(cr.tgl_perawatan,'%d-%m-%Y') tgl, "
                    + "cr.jam_perawatan, cr.nama_obat, cr.status, d.nm_dokter, cr.jenis_resep, cr.resep_untuk "
                    + "FROM catatan_resep_ranap cr INNER JOIN reg_periksa rp on rp.no_rawat=cr.no_rawat "
                    + "inner join dokter d on d.kd_dokter=cr.kd_dokter WHERE cr.no_rawat='" + norawat + "' "
                    + "ORDER BY cr.status, cr.noId desc, cr.tgl_perawatan DESC, cr.jam_perawatan DESC");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("noId"),
                        rs1.getString("no_rawat"),
                        x + ".",
                        rs1.getString("tgl"),
                        rs1.getString("jam_perawatan"),
                        rs1.getString("nama_obat"),
                        rs1.getString("status"),
                        rs1.getString("nm_dokter"),
                        rs1.getString("jenis_resep"),
                        rs1.getString("resep_untuk")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgDashboardEresepRanap.tampilResep() : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isCek() {
        MnDataPemberianObat.setEnabled(akses.getberi_obat());
        MnInputPemberianObat.setEnabled(akses.getberi_obat());
        MnCekNotif.setEnabled(akses.getadmin());
        MnCekNotifCito.setEnabled(akses.getadmin());
        TCari.requestFocus();
    }
    
    private void OtomatisRefresh() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChkAutoRefres.isSelected() == true) {
                    tampil();                    
                }
            }
        };
        
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        //interval 300000 ms = 5 menit
        tEresep = new Timer(300000, taskPerformer);
    }
    
//    private void OtomatisRefresStop() {
//        ActionListener taskPerformer = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (ChkAutoRefres.isSelected() == true) {
//                    tampil();
//                }
//            }
//        };
//        
//        // Timer
//        //interval 1000 ms = 1 detik
//        //interval 30000 ms = 30 detik atau setngah menit
//        //interval 300000 ms = 5 menit
//        new Timer(300000, taskPerformer).stop();
//    }
    
    private void tampilQR() {
        idObat = "";
        jenisResep = "";
        for (i = 0; i < tbdaftarResep.getRowCount(); i++) {
            if (tbdaftarResep.getValueAt(i, 0).toString().equals("true")) {
                if (idObat.equals("")) {
                    idObat = "'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                } else {
                    idObat = idObat + ",'" + tbdaftarResep.getValueAt(i, 1).toString() + "'";
                }
                jenisResep = "(RESEP " + tbdaftarResep.getValueAt(i, 9).toString() + ")";
            }
        }

        TdataQRresep.setText("");
        ((Painter) gambarQR).setImage("");
        try {
            ps2 = koneksi.prepareStatement(
                    " (select concat('Rg. Rawat : ',b.nm_bangsal) datanya from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norawat + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1) "
                    + "UNION ALL "
                    + "SELECT CONCAT('No. RM : ',rp.no_rkm_medis) FROM catatan_resep_ranap cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "SELECT CONCAT('Pasien : ',p.nm_pasien,' (Umur : ',rp.umurdaftar,' ',rp.sttsumur,'.)') FROM catatan_resep_ranap cr "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "WHERE cr.no_rawat='" + norawat + "' GROUP BY cr.no_rawat "
                    + "UNION ALL "
                    + "select concat('Cara Bayar : ',pj.png_jawab) from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "where rp.no_rawat='" + norawat + "' "
                    + "UNION ALL "
                    + "select concat('Scan QR Tgl. : ',date_format(now(),'%d-%m-%Y'),', Jam : ',time_format(now(),'%H:%i')) "
                    + "UNION ALL "
                    + "SELECT '______________________________' "
                    + "UNION ALL "
                    + "SELECT 'Item Obat/Alkes : " + jenisResep + "' "
                    + "UNION ALL "
                    + "SELECT CONCAT('~ ',nama_obat) FROM catatan_resep_ranap where noId in (" + idObat + ") "
                    + "UNION ALL "
                    + "SELECT '______________________________' "
                    + "UNION ALL "
                    + "SELECT concat('Totalnya Ada : ',count(noId),' Item Resep.') FROM catatan_resep_ranap WHERE noId in (" + idObat + ")");

            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    TdataQRresep.append(rs2.getString("datanya"));
                    TdataQRresep.append("\n");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }        
       
        Valid.cetakQr(TdataQRresep.getText(), Sequel.FolderQRresepRanap(), "QRresepRanap.jpg");
        Sequel.queryu("delete from setting_qr where judul = 'QRresepRanap'");
        Sequel.menyimpanQr("setting_qr", "'QRresepRanap'", "file QRCode Resep Ranap", Sequel.cariFolderPrintResepRanap());

        try {            
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select gambar from setting_qr where judul = 'QRresepRanap'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((DlgDashboardEresepRanap.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }
        } catch (Exception ex) {
            cetak(ex.toString());
        }        
    }
    
    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    private String folder;

    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }
        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            try {
                double d = image.getHeight(this) / this.getHeight();
                double w = image.getWidth(this) / d;
                double x = this.getWidth() / 2 - w / 2;
                g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
            } catch (Exception e) {
            }            
        }
    }
    
    private void cetak(String str) {
        System.out.println(str);
    }
    
    private void notifAlarmResepRanap() {
        try {
            music = new BackgroundMusic("./suara/resep_rawat_inap.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void notifAlarmResepRanapCito() {
        try {
            music = new BackgroundMusic("./suara/resep_cito_rawat_inap.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
