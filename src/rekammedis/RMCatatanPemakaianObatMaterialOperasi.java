package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMCatatanPemakaianObatMaterialOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String wktSimpan = "", dataDipilih = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCatatanPemakaianObatMaterialOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Catatan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCatatan.setModel(tabMode);
        tbCatatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbCatatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } 
        }
        tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbInfus.setModel(tabMode1);
        tbInfus.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbInfus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbInfus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbInfus.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbObat.setModel(tabMode2);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbPsiko.setModel(tabMode3);
        tbPsiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPsiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbPsiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbPsiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbAnti.setModel(tabMode4);
        tbAnti.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAnti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbAnti.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbAnti.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbBahan.setModel(tabMode5);
        tbBahan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbBahan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbBahan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbBenang.setModel(tabMode6);
        tbBenang.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBenang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbBenang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbBenang.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null,new Object[]{
                "kode", "Nama Barang", "Keterangan", "Jumlah", "Satuan"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbLain.setModel(tabMode7);
        tbLain.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbLain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbLain.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(380);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            }
        }
        tbLain.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMCatatanPemakaianObatMaterialOperasi")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas.requestFocus();
                    }
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnMasterMaterial = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        TtglCatatan = new widget.Tanggal();
        label20 = new widget.Label();
        TnipPetugas = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel122 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbInfus = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbObat = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbPsiko = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbAnti = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbBahan = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbBenang = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbLain = new widget.Table();
        BtnHapusInfus = new widget.Button();
        BtnHapusObat = new widget.Button();
        BtnHapusPsiko = new widget.Button();
        BtnHapusAnti = new widget.Button();
        BtnHapusBahan = new widget.Button();
        BtnHapusBenang = new widget.Button();
        BtnHapusLain = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCatatan = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnMasterMaterial.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMasterMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMasterMaterial.setText("Master Data Obat & Material");
        MnMasterMaterial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMasterMaterial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMasterMaterial.setIconTextGap(5);
        MnMasterMaterial.setName("MnMasterMaterial"); // NOI18N
        MnMasterMaterial.setPreferredSize(new java.awt.Dimension(210, 26));
        MnMasterMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMasterMaterialActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMasterMaterial);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Pemakaian Obat Dan Material Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1914));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(279, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(352, 10, 407, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 430, 23);

        TtglCatatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2024" }));
        TtglCatatan.setDisplayFormat("dd-MM-yyyy");
        TtglCatatan.setName("TtglCatatan"); // NOI18N
        TtglCatatan.setOpaque(false);
        TtglCatatan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglCatatan);
        TtglCatatan.setBounds(668, 38, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Nama Petugas :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 1883, 140, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        TnipPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPetugas);
        TnipPetugas.setBounds(145, 1883, 150, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(298, 1883, 360, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(660, 1883, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya);
        chkSaya.setBounds(695, 1883, 90, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Tgl. Catatan :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(576, 38, 85, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " I. Infus Dan Alat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(600, 402));

        tbInfus.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbInfus.setName("tbInfus"); // NOI18N
        Scroll2.setViewportView(tbInfus);

        FormInput.add(Scroll2);
        Scroll2.setBounds(145, 66, 614, 240);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " II. Obat Anestesi ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(600, 402));

        tbObat.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbObat.setName("tbObat"); // NOI18N
        Scroll3.setViewportView(tbObat);

        FormInput.add(Scroll3);
        Scroll3.setBounds(145, 310, 614, 490);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " III. Golongan Psikotropika & Narkotika ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(600, 402));

        tbPsiko.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbPsiko.setName("tbPsiko"); // NOI18N
        Scroll4.setViewportView(tbPsiko);

        FormInput.add(Scroll4);
        Scroll4.setBounds(145, 803, 614, 140);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " IV. Antibiotik ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(600, 402));

        tbAnti.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbAnti.setName("tbAnti"); // NOI18N
        Scroll5.setViewportView(tbAnti);

        FormInput.add(Scroll5);
        Scroll5.setBounds(145, 947, 614, 140);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " V. Bahan Habis Pakai ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);
        Scroll6.setPreferredSize(new java.awt.Dimension(600, 402));

        tbBahan.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbBahan.setName("tbBahan"); // NOI18N
        Scroll6.setViewportView(tbBahan);

        FormInput.add(Scroll6);
        Scroll6.setBounds(145, 1090, 614, 240);

        Scroll7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " VI. Benang ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.setPreferredSize(new java.awt.Dimension(600, 402));

        tbBenang.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbBenang.setName("tbBenang"); // NOI18N
        Scroll7.setViewportView(tbBenang);

        FormInput.add(Scroll7);
        Scroll7.setBounds(145, 1333, 614, 240);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " VI. Lain-lain ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);
        Scroll8.setPreferredSize(new java.awt.Dimension(600, 402));

        tbLain.setToolTipText("Silahkan dobel klik pada kolom keterangan atau jumlah untuk mengisi datanya");
        tbLain.setName("tbLain"); // NOI18N
        Scroll8.setViewportView(tbLain);

        FormInput.add(Scroll8);
        Scroll8.setBounds(145, 1576, 614, 300);

        BtnHapusInfus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusInfus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusInfus.setMnemonic('H');
        BtnHapusInfus.setText("Hapus Infus & Alat");
        BtnHapusInfus.setToolTipText("Alt+H");
        BtnHapusInfus.setName("BtnHapusInfus"); // NOI18N
        BtnHapusInfus.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusInfusActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusInfus);
        BtnHapusInfus.setBounds(765, 73, 150, 23);

        BtnHapusObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusObat.setMnemonic('H');
        BtnHapusObat.setText("Hapus Obat Anestesi");
        BtnHapusObat.setToolTipText("Alt+H");
        BtnHapusObat.setName("BtnHapusObat"); // NOI18N
        BtnHapusObat.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusObat);
        BtnHapusObat.setBounds(765, 317, 160, 23);

        BtnHapusPsiko.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPsiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusPsiko.setMnemonic('H');
        BtnHapusPsiko.setText("Hapus Gol. Psiko & Narkotika");
        BtnHapusPsiko.setToolTipText("Alt+H");
        BtnHapusPsiko.setName("BtnHapusPsiko"); // NOI18N
        BtnHapusPsiko.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusPsiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPsikoActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusPsiko);
        BtnHapusPsiko.setBounds(765, 810, 205, 23);

        BtnHapusAnti.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusAnti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusAnti.setMnemonic('H');
        BtnHapusAnti.setText("Hapus Antibiotik");
        BtnHapusAnti.setToolTipText("Alt+H");
        BtnHapusAnti.setName("BtnHapusAnti"); // NOI18N
        BtnHapusAnti.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusAnti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusAntiActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusAnti);
        BtnHapusAnti.setBounds(765, 954, 150, 23);

        BtnHapusBahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusBahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusBahan.setMnemonic('H');
        BtnHapusBahan.setText("Hapus Bahan Habis Pakai");
        BtnHapusBahan.setToolTipText("Alt+H");
        BtnHapusBahan.setName("BtnHapusBahan"); // NOI18N
        BtnHapusBahan.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusBahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusBahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusBahan);
        BtnHapusBahan.setBounds(765, 1097, 185, 23);

        BtnHapusBenang.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusBenang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusBenang.setMnemonic('H');
        BtnHapusBenang.setText("Hapus Benang");
        BtnHapusBenang.setToolTipText("Alt+H");
        BtnHapusBenang.setName("BtnHapusBenang"); // NOI18N
        BtnHapusBenang.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusBenang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusBenangActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusBenang);
        BtnHapusBenang.setBounds(765, 1340, 125, 23);

        BtnHapusLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusLain.setMnemonic('H');
        BtnHapusLain.setText("Hapus Lain-lain");
        BtnHapusLain.setToolTipText("Alt+H");
        BtnHapusLain.setName("BtnHapusLain"); // NOI18N
        BtnHapusLain.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapusLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusLainActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusLain);
        BtnHapusLain.setBounds(765, 1583, 130, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus Semua Pemakaian");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(185, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Catatan Pemakaian Obat Dan Material Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCatatan.setComponentPopupMenu(jPopupMenu1);
        tbCatatan.setName("tbCatatan"); // NOI18N
        tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCatatanMouseClicked(evt);
            }
        });
        tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCatatanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCatatan);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Catatan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-11-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari2);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount);

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 42));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnAll);

        panelGlass11.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            wktSimpan = "";
            try {
                wktSimpan = Sequel.cariIsi("select now()");
                for (i = 0; i < tbInfus.getRowCount(); i++) {
                    if (!tbInfus.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbInfus.getValueAt(i, 0).toString() + "',"
                                + "'" + tbInfus.getValueAt(i, 3).toString() + "',"
                                + "'" + tbInfus.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data infus dan alat");
                    }
                }

                for (i = 0; i < tbObat.getRowCount(); i++) {
                    if (!tbObat.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbObat.getValueAt(i, 0).toString() + "',"
                                + "'" + tbObat.getValueAt(i, 3).toString() + "',"
                                + "'" + tbObat.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data obat anestesi");
                    }
                }

                for (i= 0; i < tbPsiko.getRowCount(); i++) {
                    if (!tbPsiko.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbPsiko.getValueAt(i, 0).toString() + "',"
                                + "'" + tbPsiko.getValueAt(i, 3).toString() + "',"
                                + "'" + tbPsiko.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data golongan psikotropika & narkotika");
                    }
                }

                for (i = 0; i < tbAnti.getRowCount(); i++) {
                    if (!tbAnti.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbAnti.getValueAt(i, 0).toString() + "',"
                                + "'" + tbAnti.getValueAt(i, 3).toString() + "',"
                                + "'" + tbAnti.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data antibiotik");
                    }
                }

                for (i = 0; i < tbBahan.getRowCount(); i++) {
                    if (!tbBahan.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbBahan.getValueAt(i, 0).toString() + "',"
                                + "'" + tbBahan.getValueAt(i, 3).toString() + "',"
                                + "'" + tbBahan.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data bahan habis pakai");
                    }
                }

                for (i = 0; i < tbBenang.getRowCount(); i++) {
                    if (!tbBenang.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbBenang.getValueAt(i, 0).toString() + "',"
                                + "'" + tbBenang.getValueAt(i, 3).toString() + "',"
                                + "'" + tbBenang.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data benang");
                    }
                }

                for (i = 0; i < tbLain.getRowCount(); i++) {
                    if (!tbLain.getValueAt(i, 3).toString().trim().equals("")) {
                        Sequel.menyimpanIgnore("catatan_material_operasi",
                                "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                + "'" + tbLain.getValueAt(i, 0).toString() + "',"
                                + "'" + tbLain.getValueAt(i, 3).toString() + "',"
                                + "'" + tbLain.getValueAt(i, 2).toString() + "',"
                                + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data lain-lain");
                    }
                }

                TCari.setText(TNoRw.getText());
                tampil();
                if (Sequel.cariInteger("select count(-1) from catatan_material_operasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
                    emptTeks();
                    tampilInfus();
                    tampilObat();
                    tampilPsiko();
                    tampilAnti();
                    tampilBahan();
                    tampilBenang();
                    tampilLainlain();
                }
            } catch (Exception e) {
                System.out.println("Simpan Catatan Pemakaian Obat Dan Material Operasi : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
        tampilInfus();
        tampilObat();
        tampilPsiko();
        tampilAnti();
        tampilBahan();
        tampilBenang();
        tampilLainlain();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            wktSimpan = "";
            if (tbCatatan.getSelectedRow() > -1) {
                wktSimpan = Sequel.cariIsi("select now()");
                if (Sequel.queryu2tf("delete from catatan_material_operasi where no_rawat='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    for (i = 0; i < tbInfus.getRowCount(); i++) {
                        if (!tbInfus.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbInfus.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbInfus.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbInfus.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data infus dan alat");
                        }
                    }

                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        if (!tbObat.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbObat.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbObat.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data obat anestesi");
                        }
                    }

                    for (i = 0; i < tbPsiko.getRowCount(); i++) {
                        if (!tbPsiko.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbPsiko.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbPsiko.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbPsiko.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data golongan psikotropika & narkotika");
                        }
                    }

                    for (i = 0; i < tbAnti.getRowCount(); i++) {
                        if (!tbAnti.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbAnti.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbAnti.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbAnti.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data antibiotik");
                        }
                    }

                    for (i = 0; i < tbBahan.getRowCount(); i++) {
                        if (!tbBahan.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbBahan.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbBahan.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbBahan.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data bahan habis pakai");
                        }
                    }

                    for (i = 0; i < tbBenang.getRowCount(); i++) {
                        if (!tbBenang.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbBenang.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbBenang.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbBenang.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data benang");
                        }
                    }

                    for (i = 0; i < tbLain.getRowCount(); i++) {
                        if (!tbLain.getValueAt(i, 3).toString().trim().equals("")) {
                            Sequel.menyimpanIgnore("catatan_material_operasi",
                                    "'" + TNoRw.getText() + "','" + TrgRawat.getText() + "',"
                                    + "'" + Valid.SetTgl(TtglCatatan.getSelectedItem() + "") + "',"
                                    + "'" + tbLain.getValueAt(i, 0).toString() + "',"
                                    + "'" + tbLain.getValueAt(i, 3).toString() + "',"
                                    + "'" + tbLain.getValueAt(i, 2).toString() + "',"
                                    + "'" + TnipPetugas.getText() + "','" + wktSimpan + "'", "Data lain-lain");
                        }
                    }

                    TCari.setText(TNoRw.getText());
                    tampil();
                    if (Sequel.cariInteger("select count(-1) from catatan_material_operasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
                        emptTeks();
                        tampilInfus();
                        tampilObat();
                        tampilPsiko();
                        tampilAnti();
                        tampilBahan();
                        tampilBenang();
                        tampilLainlain();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCatatan.requestFocus();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCatatanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCatatanMouseClicked

    private void tbCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCatatanKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMCatatanPemakaianObatMaterialOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPetugas.setText("-");
                TnmPetugas.setText("-");
            } else {
                TnipPetugas.setText(akses.getkode());
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPetugas.getText() + "'"));
            }
        } else {
            TnipPetugas.setText("-");
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data catatan pemakaian obat & material mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from catatan_material_operasi where waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                    tampilInfus();
                    tampilObat();
                    tampilPsiko();
                    tampilAnti();
                    tampilBahan();
                    tampilBenang();
                    tampilLainlain();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
                tampilInfus();
                tampilObat();
                tampilPsiko();
                tampilAnti();
                tampilBahan();
                tampilBenang();
                tampilLainlain();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo_rs_warna_miring1 from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
//            param.put("theSignIn", cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + " Wita");
//            param.put("identifikasi", cmbIdentifikasi.getSelectedItem().toString());
//            param.put("lokasiOperasi1", cmbLokasi1.getSelectedItem().toString());
//            param.put("prosedur1", cmbProsedur1.getSelectedItem().toString());
//            param.put("surat", cmbSurat.getSelectedItem().toString());
//            param.put("lokasiOperasi2", cmbLokasi2.getSelectedItem().toString());
//            param.put("mesin", cmbMesin.getSelectedItem().toString());
//            param.put("pulse", cmbPulse.getSelectedItem().toString());
//            param.put("apakahPasien", cmbApakahPasien.getSelectedItem().toString());
//            param.put("kesulitan", cmbKesulitan.getSelectedItem().toString());
//            param.put("dan", cmbDan.getSelectedItem().toString());
//            param.put("resiko", cmbResiko.getSelectedItem().toString());
//            param.put("500", cmb500.getSelectedItem().toString());
//            param.put("dua", cmbDua.getSelectedItem().toString());
//            param.put("rencana", cmbRencana.getSelectedItem().toString());
//            param.put("perawatSirkuler", "(" + TnmPetugas.getText() + ")");
//            param.put("dokterAnestesi1", "(" + TnmDokterAnes1.getText() + ")");
//            
//            param.put("theTimeOut", cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + " Wita");
//            param.put("konfirmasi", cmbKonfirmasi.getSelectedItem().toString());
//            param.put("namaPasien", cmbNama.getSelectedItem().toString());
//            param.put("prosedur2", cmbProsedur2.getSelectedItem().toString());
//            param.put("lokasiDimana", cmbLokasiDimana.getSelectedItem().toString());
//            param.put("apakahAntibiotik", cmbApakahAntibiotik.getSelectedItem().toString());
//            param.put("cekNamaAntibiotik", cmbNamaAntibiotik.getSelectedItem().toString());
//            param.put("cekDosisAntibiotik", cmbDosisAntibiotik.getSelectedItem().toString());            
//            if (TnmAntibiotik.getText().equals("")) {
//                param.put("nmAntibiotik", "- Nama Antibiotik Yang Diberikan .........");
//            } else {
//                param.put("nmAntibiotik", "- Nama Antibiotik Yang Diberikan : " + TnmAntibiotik.getText());
//            }            
//            if (TdosisAntibiotik.getText().equals("")) {
//                param.put("dosisAntibiotik", "- Dosis Antibiotik Yang Diberikan .........");
//            } else {
//                param.put("dosisAntibiotik", "- Dosis Antibiotik Yang Diberikan : " + TdosisAntibiotik.getText());
//            }            
//            param.put("antisipasi", Tantisipasi.getText());
//            param.put("reviewDokter", TreviewA.getText());
//            param.put("reviewTimAnes", TreviewB.getText());
//            param.put("cvc", Tjika.getText());
//            param.put("reviewTimPerawat", TreviewC.getText());
//            param.put("apakahFoto", cmbApakahFoto.getSelectedItem().toString());
//            param.put("operator", "(" + TnmOperator.getText() + ")");
//            param.put("dokterAnestesi2", "(" + TnmDokterAnes2.getText() + ")");
//            param.put("perawatSirkuit1", "(" + TnmPerawatSirkuit1.getText() + ")");
//            
//            param.put("theSignOut", cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + " Wita");
//            param.put("namaProsedur", cmbNamaProsedur.getSelectedItem().toString());
//            param.put("instrumen", cmbInstrumen.getSelectedItem().toString());
//            param.put("spesimen", cmbSpesimen.getSelectedItem().toString());
//            param.put("adakah", cmbAdakah.getSelectedItem().toString());
//            param.put("operatorDokterBedah", cmb2Operator.getSelectedItem().toString());
//            param.put("halYang", ThalYang.getText());
//            param.put("tglTindakan", Valid.SetTglINDONESIA(Valid.SetTgl(TtglCatatan.getSelectedItem() + "")));
//            param.put("verifikasi", Tverifikasi.getText());
//            param.put("perawatSirkuit2", "(" + TnmPerawatSirkuit2.getText() + ")");
//            param.put("dokterAnestesi3", "(" + TnmDokterAnes3.getText() + ")");

            Valid.MyReport("rptCeklisKeselamatanOperasi.jasper", "report", "::[ Lembar Checklist Keselamatan Operasi ]::",
                "SELECT now() tanggal", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        tampilInfus();
        tampilObat();
        tampilPsiko();
        tampilAnti();
        tampilBahan();
        tampilBenang();
        tampilLainlain();
    }//GEN-LAST:event_formWindowOpened

    private void BtnHapusInfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusInfusActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data infus dan alat mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbInfus.getRowCount(); i++) {
                    if (!tbInfus.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbInfus.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbInfus.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilInfus();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusInfusActionPerformed

    private void BtnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusObatActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data obat anestesi mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbObat.getRowCount(); i++) {
                    if (!tbObat.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbObat.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbObat.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilObat();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusObatActionPerformed

    private void BtnHapusPsikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPsikoActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data golongan psikotropika & narkotika mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbPsiko.getRowCount(); i++) {
                    if (!tbPsiko.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbPsiko.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbPsiko.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilPsiko();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusPsikoActionPerformed

    private void BtnHapusAntiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusAntiActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data antibiotik mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbAnti.getRowCount(); i++) {
                    if (!tbAnti.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbAnti.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbAnti.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilAnti();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusAntiActionPerformed

    private void BtnHapusBahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusBahanActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data bahan habis pakai mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbBahan.getRowCount(); i++) {
                    if (!tbBahan.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbBahan.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbBahan.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilBahan();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusBahanActionPerformed

    private void BtnHapusBenangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusBenangActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data benang mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbBenang.getRowCount(); i++) {
                    if (!tbBenang.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbBenang.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbBenang.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilBenang();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusBenangActionPerformed

    private void BtnHapusLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusLainActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data lain-lain mau dihapus semua..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                dataDipilih = "";
                for (i = 0; i < tbLain.getRowCount(); i++) {
                    if (!tbLain.getValueAt(i, 3).toString().trim().equals("")) {
                        if (dataDipilih.equals("")) {
                            dataDipilih = "'" + tbLain.getValueAt(i, 0).toString() + "'";
                        } else {
                            dataDipilih = dataDipilih + ",'" + tbLain.getValueAt(i, 0).toString() + "'";
                        }
                    }
                }
                
                if (Sequel.queryu2tf("delete from catatan_material_operasi where kd_material in (" + dataDipilih + ") and "
                        + "no_rawat='" + TNoRw.getText() + "' and waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString()
                }) == true) {
                    tampil();
                    tampilLainlain();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusLainActionPerformed

    private void MnMasterMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMasterMaterialActionPerformed
        akses.setform("RMCatatanPemakaianObatMaterialOperasi");
        DlgMasterMaterialOperasi master = new DlgMasterMaterialOperasi(null, false);
        master.isCek();
        master.emptTeks();
        master.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        master.setLocationRelativeTo(internalFrame1);
        master.setVisible(true);
    }//GEN-LAST:event_MnMasterMaterialActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCatatanPemakaianObatMaterialOperasi dialog = new RMCatatanPemakaianObatMaterialOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusAnti;
    private widget.Button BtnHapusBahan;
    private widget.Button BtnHapusBenang;
    private widget.Button BtnHapusInfus;
    private widget.Button BtnHapusLain;
    private widget.Button BtnHapusObat;
    private widget.Button BtnHapusPsiko;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnMasterMaterial;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglCatatan;
    private widget.CekBox chkSaya;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel122;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label20;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.Table tbAnti;
    private widget.Table tbBahan;
    private widget.Table tbBenang;
    private widget.Table tbCatatan;
    private widget.Table tbInfus;
    private widget.Table tbLain;
    private widget.Table tbObat;
    private widget.Table tbPsiko;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT c.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, date_format(c.tgl_catatan,'%d-%m-%Y') tglCatatan FROM catatan_material_operasi c "
                    + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=c.nip_petugas WHERE "
                    + "c.tgl_catatan between ? and ? and c.no_rawat LIKE ? or "
                    + "c.tgl_catatan between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "c.tgl_catatan between ? and ? and p.nm_pasien LIKE ? or "
                    + "c.tgl_catatan between ? and ? and pg.nama LIKE ? group by c.no_rawat ORDER BY c.waktu_simpan desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");                
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");                
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),                        
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglCatatan")                        
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMCatatanPemakaianObatMaterialOperasi.tampil() : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {  
        TnipPetugas.setText("-");
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);        
        TtglCatatan.setDate(new Date());        
    }

    private void getData() {
        chkSaya.setSelected(false);
        
        if (tbCatatan.getSelectedRow() != -1) {
            TNoRw.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
            TPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 6).toString());        
            TnipPetugas.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 23).toString());
            TnmPetugas.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 57).toString());
            
            Valid.SetTgl(TtglCatatan, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 56).toString());            
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
        
        BtnHapusInfus.setEnabled(akses.getkegiatan_operasi());
        BtnHapusObat.setEnabled(akses.getkegiatan_operasi());
        BtnHapusPsiko.setEnabled(akses.getkegiatan_operasi());
        BtnHapusAnti.setEnabled(akses.getkegiatan_operasi());
        BtnHapusBahan.setEnabled(akses.getkegiatan_operasi());
        BtnHapusBenang.setEnabled(akses.getkegiatan_operasi());
        BtnHapusLain.setEnabled(akses.getkegiatan_operasi());
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        TCari.setText(norw);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
    }
    
    public void tampilInfus() {     
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT * FROM master_infus_operasi WHERE status='Aktif' ORDER BY kd_infus");
            try {
                rs1 = ps1.executeQuery();                
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{                        
                        rs1.getString("kd_infus"),
                        rs1.getString("nama_infus"),
                        "", "",
                        rs1.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilInfus() : " + e);
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
    
    public void tampilObat() {     
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT * FROM master_obat_operasi WHERE status='Aktif' ORDER BY kd_obat");
            try {
                rs2 = ps2.executeQuery();                
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{                        
                        rs2.getString("kd_obat"),
                        rs2.getString("nama_obat"),
                        "", "",
                        rs2.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilObat() : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilPsiko() {     
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT * FROM master_narkotika_operasi WHERE status='Aktif' ORDER BY kd_narkotika");
            try {
                rs3 = ps3.executeQuery();                
                while (rs3.next()) {
                    tabMode3.addRow(new Object[]{                        
                        rs3.getString("kd_narkotika"),
                        rs3.getString("nama_narkotika"),
                        "", "",
                        rs3.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilPsiko() : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilAnti() {     
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("SELECT * FROM master_antibiotik_operasi WHERE status='Aktif' ORDER BY kd_antibiotik");
            try {
                rs4 = ps4.executeQuery();                
                while (rs4.next()) {
                    tabMode4.addRow(new Object[]{                        
                        rs4.getString("kd_antibiotik"),
                        rs4.getString("nama_antibiotik"),
                        "", "",
                        rs4.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilAnti() : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilBahan() {     
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT * FROM master_bahan_operasi WHERE status='Aktif' ORDER BY kd_bahan");
            try {
                rs5 = ps5.executeQuery();                
                while (rs5.next()) {
                    tabMode5.addRow(new Object[]{                        
                        rs5.getString("kd_bahan"),
                        rs5.getString("nama_bahan"),
                        "", "",
                        rs5.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilBahan() : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilBenang() {     
        Valid.tabelKosong(tabMode6);
        try {
            ps6 = koneksi.prepareStatement("SELECT * FROM master_benang_operasi WHERE status='Aktif' ORDER BY kd_benang");
            try {
                rs6 = ps6.executeQuery();                
                while (rs6.next()) {
                    tabMode6.addRow(new Object[]{                        
                        rs6.getString("kd_benang"),
                        rs6.getString("nama_benang"),
                        "", "",
                        rs6.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilBenang() : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilLainlain() {     
        Valid.tabelKosong(tabMode7);
        try {
            ps7 = koneksi.prepareStatement("SELECT * FROM master_lainlain_operasi WHERE status='Aktif' ORDER BY kd_lainlain");
            try {
                rs7 = ps7.executeQuery();                
                while (rs7.next()) {
                    tabMode7.addRow(new Object[]{                        
                        rs7.getString("kd_lainlain"),
                        rs7.getString("nama_lainlain"),
                        "", "",
                        rs7.getString("satuan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilLainlain() : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
