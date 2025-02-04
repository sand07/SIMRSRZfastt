/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package simrskhanza;
import bridging.BPJSCekReferensiDokterDPJP;
import bridging.SatuSehatCekNIK;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
import restore.DlgRestoreDokter;


/**
 *
 * @author dosen
 */
public class DlgDokter extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
    private DlgCariSpesialis spesial = new DlgCariSpesialis(null, false);
    private BPJSCekReferensiDokterDPJP dpjp = new BPJSCekReferensiDokterDPJP(null, false);
    private SatuSehatCekNIK cekViaSatuSehat = new SatuSehatCekNIK();
    private PreparedStatement stat, stat1;
    private ResultSet rs, rs1;
    private String kerja1 = "", kerja2 = "", urlfoto = "", kddokterBPJS = "", kddokterSatuSehat = "";

    /** Creates new form DlgDokter
     * @param parent
     * @param modal */
    public DlgDokter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        Object[] row={
            "Kode Dokter", "Nama Dokter", "J.K.", "Tmp.Lahir", "Tgl.Lahir", "G.D.", "Agama",
            "Alamat Tinggal", "No.HP/Telp", "Stts.Nikah", "Spesialis", "Alumni", "No.Ijin Praktek",
            "NIK/No. KTP", "Status Kerja", "URL Foto", "Kode Dokter BPJS", "Kode Dokter Satu Sehat"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbDokter.setModel(tabMode);
        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 18; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(145);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(300);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
            "Kode Dokter", "Nama Dokter", "J.K.", "Tmp.Lahir", "Tgl.Lahir", "G.D.", "Agama",
            "Alamat Tinggal", "No.HP/Telp", "Stts.Nikah", "Spesialis", "Alumni", "No.Ijin Praktek", 
            "NIK/No. KTP", "Status Kerja", "URL Foto"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDokter1.setModel(tabMode1);
        tbDokter1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 16; i++) {
            TableColumn column = tbDokter1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(145);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(300);
            }
        }
        tbDokter1.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        KdSps.setDocument(new batasInput((byte)5).getKata(KdSps));
        TNm.setDocument(new batasInput((byte)50).getKata(TNm));
        TTmp.setDocument(new batasInput((byte)20).getKata(TTmp));
        TNoi.setDocument(new batasInput((byte)40).getKata(TNoi));
        TAlmt.setDocument(new batasInput((byte)60).getKata(TAlmt));
        TAlumni.setDocument(new batasInput((byte)60).getKata(TAlumni));
        TTlp.setDocument(new batasInput((byte)13).getOnlyAngka(TTlp));
        TnoKTP.setDocument(new batasInput((byte)20).getOnlyAngka(TnoKTP));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
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
        
        ChkInput.setSelected(false);
        isForm();
        
        dpjp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (dpjp.getTable().getSelectedRow() != -1) {
                    kddokterBPJS = dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString();
                    if (Sequel.cariInteger("select count(-1) from mapping_dokter where kd_dokter_rs='" + TKd.getText() + "'") == 0) {
                        Sequel.menyimpan("mapping_dokter", "?,?,?", "Kode Dokter", 3, new String[]{kddokterBPJS, TKd.getText(), ""});
                    } else {
                        Sequel.mengedit("mapping_dokter", "kd_dokter_rs='" + TKd.getText() + "'", "kd_dokter_bpjs='" + kddokterBPJS + "'");
                    }
                    tampil();
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

        dpjp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dpjp.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
              
        spesial.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(spesial.getTable().getSelectedRow()!= -1){                   
                    KdSps.setText(spesial.getTable().getValueAt(spesial.getTable().getSelectedRow(),0).toString());
                    TSpesialis.setText(spesial.getTable().getValueAt(spesial.getTable().getSelectedRow(),1).toString());
                }   
                KdSps.requestFocus();
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
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai.getTable().getSelectedRow()!= -1){                   
                    TKd.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),0).toString());
                    TNm.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),1).toString());
                    CmbJk.setSelectedItem(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),2).toString().replaceAll("Wanita","PEREMPUAN").replaceAll("Pria","LAKI-LAKI"));
                    TTmp.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),11).toString());
                    TAlmt.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),13).toString());
                    Valid.SetTgl(DTPLahir,pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(),12).toString());
                }   
                TKd.requestFocus();
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

        Popup = new javax.swing.JPopupMenu();
        MnRestore = new javax.swing.JMenuItem();
        MnDokterBPJS = new javax.swing.JMenuItem();
        MnDokterSatuSehat = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass8 = new widget.panelisi();
        jLabel14 = new widget.Label();
        cmbCrJk = new widget.ComboBox();
        jLabel11 = new widget.Label();
        CmbCrGd = new widget.ComboBox();
        jLabel16 = new widget.Label();
        CmbCrStts = new widget.ComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TTmp = new widget.TextBox();
        CmbJk = new widget.ComboBox();
        TNm = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        CMbGd = new widget.ComboBox();
        jLabel13 = new widget.Label();
        DTPLahir = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbAgama = new widget.ComboBox();
        jLabel19 = new widget.Label();
        CmbStts = new widget.ComboBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel12 = new widget.Label();
        TSpesialis = new widget.TextBox();
        TAlmt = new widget.TextBox();
        TTlp = new widget.TextBox();
        TKd = new widget.TextBox();
        jLabel15 = new widget.Label();
        TNoi = new widget.TextBox();
        jLabel22 = new widget.Label();
        TAlumni = new widget.TextBox();
        KdSps = new widget.TextBox();
        btnSpesial = new widget.Button();
        BtnCariPegawai = new widget.Button();
        jLabel23 = new widget.Label();
        TnoKTP = new widget.TextBox();
        jLabel24 = new widget.Label();
        CmbSttsKerja = new widget.ComboBox();
        jLabel17 = new widget.Label();
        Turl = new widget.TextBox();
        ChkInput = new widget.CekBox();
        TabDokter = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbDokter1 = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        MnRestore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRestore.setText("Data Sampah");
        MnRestore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRestore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRestore.setIconTextGap(5);
        MnRestore.setName("MnRestore"); // NOI18N
        MnRestore.setPreferredSize(new java.awt.Dimension(200, 28));
        MnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRestoreActionPerformed(evt);
            }
        });
        Popup.add(MnRestore);

        MnDokterBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterBPJS.setText("Update Kode Dokter BPJS");
        MnDokterBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterBPJS.setIconTextGap(5);
        MnDokterBPJS.setName("MnDokterBPJS"); // NOI18N
        MnDokterBPJS.setPreferredSize(new java.awt.Dimension(200, 28));
        MnDokterBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterBPJSActionPerformed(evt);
            }
        });
        Popup.add(MnDokterBPJS);

        MnDokterSatuSehat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterSatuSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterSatuSehat.setText("Update Kode Dokter SatuSehat");
        MnDokterSatuSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterSatuSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterSatuSehat.setIconTextGap(5);
        MnDokterSatuSehat.setName("MnDokterSatuSehat"); // NOI18N
        MnDokterSatuSehat.setPreferredSize(new java.awt.Dimension(200, 28));
        MnDokterSatuSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterSatuSehatActionPerformed(evt);
            }
        });
        Popup.add(MnDokterSatuSehat);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Dokter ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass6.setLayout(null);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        panelGlass6.add(BtnSimpan);
        BtnSimpan.setBounds(6, 10, 100, 30);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
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
        panelGlass6.add(BtnBatal);
        BtnBatal.setBounds(108, 10, 100, 30);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setEnabled(false);
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnHapus);
        BtnHapus.setBounds(210, 10, 100, 30);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnEdit);
        BtnEdit.setBounds(312, 10, 100, 30);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setEnabled(false);
        BtnPrint.setName("BtnPrint"); // NOI18N
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
        panelGlass6.add(BtnPrint);
        BtnPrint.setBounds(414, 10, 100, 30);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(130, 26));
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
        panelGlass6.add(BtnAll);
        BtnAll.setBounds(516, 10, 110, 30);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass6.add(jLabel10);
        jLabel10.setBounds(647, 14, 50, 23);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        panelGlass6.add(LCount);
        LCount.setBounds(700, 14, 60, 23);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
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
        panelGlass6.add(BtnKeluar);
        BtnKeluar.setBounds(774, 10, 100, 30);

        jPanel2.add(panelGlass6, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("J.K. :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(40, 23));
        panelGlass8.add(jLabel14);

        cmbCrJk.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "LAKI-LAKI", "PEREMPUAN" }));
        cmbCrJk.setLightWeightPopupEnabled(false);
        cmbCrJk.setName("cmbCrJk"); // NOI18N
        cmbCrJk.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbCrJk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCrJkItemStateChanged(evt);
            }
        });
        cmbCrJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCrJkKeyPressed(evt);
            }
        });
        panelGlass8.add(cmbCrJk);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("G.D. :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(40, 23));
        panelGlass8.add(jLabel11);

        CmbCrGd.setForeground(new java.awt.Color(0, 0, 0));
        CmbCrGd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "A", "B", "AB", "O", "-" }));
        CmbCrGd.setLightWeightPopupEnabled(false);
        CmbCrGd.setName("CmbCrGd"); // NOI18N
        CmbCrGd.setPreferredSize(new java.awt.Dimension(100, 23));
        CmbCrGd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCrJkItemStateChanged(evt);
            }
        });
        CmbCrGd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbCrGdKeyPressed(evt);
            }
        });
        panelGlass8.add(CmbCrGd);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Stts.Nikah :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel16);

        CmbCrStts.setForeground(new java.awt.Color(0, 0, 0));
        CmbCrStts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "BELUM MENIKAH", "MENIKAH", "JANDA", "DUDA" }));
        CmbCrStts.setLightWeightPopupEnabled(false);
        CmbCrStts.setName("CmbCrStts"); // NOI18N
        CmbCrStts.setPreferredSize(new java.awt.Dimension(100, 23));
        CmbCrStts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCrJkItemStateChanged(evt);
            }
        });
        CmbCrStts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbCrSttsKeyPressed(evt);
            }
        });
        panelGlass8.add(CmbCrStts);

        jSeparator4.setBackground(new java.awt.Color(195, 215, 195));
        jSeparator4.setForeground(new java.awt.Color(195, 215, 195));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setName("jSeparator4"); // NOI18N
        jSeparator4.setOpaque(true);
        jSeparator4.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass8.add(jSeparator4);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(230, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TCariKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass8.add(BtnCari);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(215, 215));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(1331, 168));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Kode Dokter :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(2, 12, 153, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Dokter :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(2, 42, 153, 23);

        TTmp.setForeground(new java.awt.Color(0, 0, 0));
        TTmp.setName("TTmp"); // NOI18N
        TTmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTmpKeyPressed(evt);
            }
        });
        FormInput.add(TTmp);
        TTmp.setBounds(160, 102, 148, 23);

        CmbJk.setForeground(new java.awt.Color(0, 0, 0));
        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        CmbJk.setName("CmbJk"); // NOI18N
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput.add(CmbJk);
        CmbJk.setBounds(160, 72, 100, 23);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setName("TNm"); // NOI18N
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        FormInput.add(TNm);
        TNm.setBounds(160, 42, 254, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Kelamin :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(2, 72, 153, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Gol. Darah :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(265, 72, 65, 23);

        CMbGd.setForeground(new java.awt.Color(0, 0, 0));
        CMbGd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "O", "-" }));
        CMbGd.setName("CMbGd"); // NOI18N
        CMbGd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CMbGdKeyPressed(evt);
            }
        });
        FormInput.add(CMbGd);
        CMbGd.setBounds(335, 72, 50, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tmp/Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(2, 102, 153, 23);

        DTPLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-01-2024" }));
        DTPLahir.setDisplayFormat("dd-MM-yyyy");
        DTPLahir.setName("DTPLahir"); // NOI18N
        DTPLahir.setOpaque(false);
        DTPLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPLahirKeyPressed(evt);
            }
        });
        FormInput.add(DTPLahir);
        DTPLahir.setBounds(315, 102, 92, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Agama :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(422, 12, 60, 23);

        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU", "-" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        cmbAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAgamaKeyPressed(evt);
            }
        });
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(486, 12, 130, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Stts. Nikah :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(639, 12, 90, 23);

        CmbStts.setForeground(new java.awt.Color(0, 0, 0));
        CmbStts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM MENIKAH", "MENIKAH", "JANDA", "DUDA" }));
        CmbStts.setLightWeightPopupEnabled(false);
        CmbStts.setName("CmbStts"); // NOI18N
        CmbStts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbSttsKeyPressed(evt);
            }
        });
        FormInput.add(CmbStts);
        CmbStts.setBounds(733, 12, 130, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Alamat :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(422, 42, 60, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No.Telp :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(422, 72, 60, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Spesialis :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(422, 102, 60, 23);

        TSpesialis.setEditable(false);
        TSpesialis.setForeground(new java.awt.Color(0, 0, 0));
        TSpesialis.setName("TSpesialis"); // NOI18N
        FormInput.add(TSpesialis);
        TSpesialis.setBounds(568, 102, 266, 23);

        TAlmt.setForeground(new java.awt.Color(0, 0, 0));
        TAlmt.setHighlighter(null);
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(486, 42, 378, 23);

        TTlp.setForeground(new java.awt.Color(0, 0, 0));
        TTlp.setName("TTlp"); // NOI18N
        TTlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTlpKeyPressed(evt);
            }
        });
        FormInput.add(TTlp);
        TTlp.setBounds(486, 72, 130, 23);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N
        TKd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdKeyPressed(evt);
            }
        });
        FormInput.add(TKd);
        TKd.setBounds(160, 12, 140, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("No. Ijin Praktek (SIP) :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(2, 132, 153, 23);

        TNoi.setForeground(new java.awt.Color(0, 0, 0));
        TNoi.setName("TNoi"); // NOI18N
        TNoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoiKeyPressed(evt);
            }
        });
        FormInput.add(TNoi);
        TNoi.setBounds(160, 132, 254, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Alumni :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(422, 132, 60, 23);

        TAlumni.setForeground(new java.awt.Color(0, 0, 0));
        TAlumni.setHighlighter(null);
        TAlumni.setName("TAlumni"); // NOI18N
        TAlumni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlumniKeyPressed(evt);
            }
        });
        FormInput.add(TAlumni);
        TAlumni.setBounds(486, 132, 378, 23);

        KdSps.setEditable(false);
        KdSps.setForeground(new java.awt.Color(0, 0, 0));
        KdSps.setHighlighter(null);
        KdSps.setName("KdSps"); // NOI18N
        KdSps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdSpsKeyPressed(evt);
            }
        });
        FormInput.add(KdSps);
        KdSps.setBounds(486, 102, 80, 23);

        btnSpesial.setForeground(new java.awt.Color(0, 0, 0));
        btnSpesial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSpesial.setMnemonic('1');
        btnSpesial.setToolTipText("ALt+1");
        btnSpesial.setName("btnSpesial"); // NOI18N
        btnSpesial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpesialActionPerformed(evt);
            }
        });
        FormInput.add(btnSpesial);
        btnSpesial.setBounds(836, 102, 28, 23);

        BtnCariPegawai.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariPegawai.setMnemonic('1');
        BtnCariPegawai.setToolTipText("ALt+1");
        BtnCariPegawai.setName("BtnCariPegawai"); // NOI18N
        BtnCariPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPegawaiActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPegawai);
        BtnCariPegawai.setBounds(305, 12, 28, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("NIK KTP : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(620, 72, 60, 23);

        TnoKTP.setForeground(new java.awt.Color(0, 0, 0));
        TnoKTP.setName("TnoKTP"); // NOI18N
        TnoKTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoKTPKeyPressed(evt);
            }
        });
        FormInput.add(TnoKTP);
        TnoKTP.setBounds(680, 72, 150, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Status Kerja :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(879, 12, 80, 23);

        CmbSttsKerja.setForeground(new java.awt.Color(0, 0, 0));
        CmbSttsKerja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "NON AKTIF" }));
        CmbSttsKerja.setName("CmbSttsKerja"); // NOI18N
        CmbSttsKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbSttsKerjaActionPerformed(evt);
            }
        });
        CmbSttsKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbSttsKerjaKeyPressed(evt);
            }
        });
        FormInput.add(CmbSttsKerja);
        CmbSttsKerja.setBounds(965, 12, 100, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("URL Foto :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(2, 162, 153, 23);

        Turl.setForeground(new java.awt.Color(0, 0, 0));
        Turl.setName("Turl"); // NOI18N
        Turl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurlKeyPressed(evt);
            }
        });
        FormInput.add(Turl);
        Turl.setBounds(160, 162, 705, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabDokter.setBackground(new java.awt.Color(250, 255, 245));
        TabDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabDokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabDokter.setName("TabDokter"); // NOI18N
        TabDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDokterMouseClicked(evt);
            }
        });

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setComponentPopupMenu(Popup);
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        tbDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDokterKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbDokter);

        TabDokter.addTab("Status Kerja AKTIF", scrollPane1);

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbDokter1.setAutoCreateRowSorter(true);
        tbDokter1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter1.setName("tbDokter1"); // NOI18N
        tbDokter1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokter1MouseClicked(evt);
            }
        });
        tbDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDokter1KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbDokter1);

        TabDokter.addTab("Status Kerja NON AKTIF", scrollPane2);

        internalFrame1.add(TabDokter, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TTmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTmpKeyPressed
        Valid.pindah(evt,CMbGd,DTPLahir);
}//GEN-LAST:event_TTmpKeyPressed

    private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
        Valid.pindah(evt,TNm,CMbGd);
}//GEN-LAST:event_CmbJkKeyPressed

    private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        Valid.pindah(evt,TKd,CmbJk);
}//GEN-LAST:event_TNmKeyPressed

    private void CMbGdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CMbGdKeyPressed
        Valid.pindah(evt,CmbJk,TTmp);
}//GEN-LAST:event_CMbGdKeyPressed

    private void DTPLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPLahirKeyPressed
        Valid.pindah(evt,TTmp,TNoi);
}//GEN-LAST:event_DTPLahirKeyPressed

    private void cmbAgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgamaKeyPressed
        Valid.pindah(evt,DTPLahir,CmbStts);
}//GEN-LAST:event_cmbAgamaKeyPressed

    private void CmbSttsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbSttsKeyPressed
        Valid.pindah(evt,cmbAgama,TAlmt);
}//GEN-LAST:event_CmbSttsKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        Valid.pindah(evt,CmbStts,TTlp);
}//GEN-LAST:event_TAlmtKeyPressed

    private void TTlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTlpKeyPressed
        Valid.pindah(evt,TAlmt,TnoKTP);
}//GEN-LAST:event_TTlpKeyPressed

    private void TKdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){  
            BtnCariPegawaiActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,TNm);
        }
}//GEN-LAST:event_TKdKeyPressed

    private void TNoiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoiKeyPressed
        Valid.pindah(evt,DTPLahir,cmbAgama);
}//GEN-LAST:event_TNoiKeyPressed

    private void TAlumniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlumniKeyPressed
        Valid.pindah(evt,KdSps,BtnSimpan);
}//GEN-LAST:event_TAlumniKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode dokter");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama dokter");
        } else if (TSpesialis.getText().trim().equals("") || KdSps.getText().trim().equals("")) {
            Valid.textKosong(KdSps, "spesialis");
        } else {
            if (Turl.getText().trim().equals("") || Turl.getText().trim().equals(" ")) {
                urlfoto = "-";
            } else {
                urlfoto = Turl.getText();
            }
            
            try {
                koneksi.setAutoCommit(false);
                Sequel.menyimpan("jnj_jabatan", "'-','-','0'");
                Sequel.menyimpan("departemen", "'-','-','1'");
                Sequel.menyimpan("bidang", "'-'");
                Sequel.menyimpan("bank", "'T'");
                Sequel.menyimpan("stts_wp", "'-','-'");
                Sequel.menyimpan("stts_kerja", "'-','-','0'");
                Sequel.menyimpan("pendidikan", "'-','0','0','0','0'");
                Sequel.menyimpan("pegawai", "'0','" + TKd.getText() + "','" + TNm.getText() + "','" + CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria") + "',"
                        + "'-','-','-','-','-','-','-','-','0','" + TTmp.getText() + "','" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "','" + TAlmt.getText() + "','-','0000-00-00','<1','-','T','-',"
                        + "'AKTIF','0','0','0','0000-00-00','0','0','" + TnoKTP.getText() + "'");

                if (Sequel.menyimpantf("dokter", "'" + TKd.getText() + "','" + TNm.getText() + "',"
                        + "'" + CmbJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "',"
                        + "'" + TTmp.getText() + "','" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "','" + CMbGd.getSelectedItem() + "',"
                        + "'" + cmbAgama.getSelectedItem() + "','" + TAlmt.getText() + "','" + TTlp.getText() + "','" + CmbStts.getSelectedItem() + "',"
                        + "'" + KdSps.getText() + "','" + TAlumni.getText() + "','" + TNoi.getText() + "','" + kerja1 + "','" + urlfoto + "'", "Dokter") == true) {

                    if (Sequel.cariInteger("select count(-1) from mapping_dokter where kd_dokter_rs='" + TKd.getText() + "'") == 0) {
                        Sequel.menyimpan("mapping_dokter", "?,?,?", "Kode Dokter", 3, new String[]{"", TKd.getText(), ""});
                    }
                    
                    if (TabDokter.getSelectedIndex() == 0) {
                        tampil();
                        emptTeks();
                    } else if (TabDokter.getSelectedIndex() == 1) {
                        tampil1();
                        emptTeks();
                    }
                }

                koneksi.setAutoCommit(true);
            } catch (Exception ex) {
                return;
            }            
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TAlumni,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        try {
//            Sequel.mengedit("dokter", "kd_dokter='" + TKd.getText() + "'", "status='0'");
            Sequel.mengedit("pegawai", "nik='" + TKd.getText() + "'", "stts_aktif='KELUAR'");
            if (TabDokter.getSelectedIndex() == 0) {
                tampil();
            } else if (TabDokter.getSelectedIndex() == 1) {
                tampil1();
            }
            emptTeks();
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();   
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDokter.jasper","report","::[ Data Dokter ]::",
                    "select dokter.kd_dokter,dokter.nm_dokter,dokter.jk,dokter.tmp_lahir, "
                    + "dokter.tgl_lahir,dokter.gol_drh,dokter.agama,dokter.almt_tgl,dokter.no_telp, "
                    + "dokter.stts_nikah,spesialis.nm_sps,dokter.alumni,dokter.no_ijn_praktek "
                    + "from dokter inner join spesialis on dokter.kd_sps=spesialis.kd_sps "
                    + "where dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and  dokter.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.tmp_lahir like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.tgl_lahir like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.agama like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.almt_tgl like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.no_telp like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and spesialis.nm_sps like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.alumni like '%" + TCari.getText().trim() + "%' or "
                    + "dokter.status='1' and dokter.jk like '%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%' and dokter.gol_drh like '%" + CmbCrGd.getSelectedItem().toString().trim() + "%' and dokter.stts_nikah like '%" + CmbCrStts.getSelectedItem().toString().trim() + "%' and dokter.no_ijn_praktek like '%" + TCari.getText().trim() + "%' "
                    + "order by dokter.kd_dokter", param);       
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        CmbCrStts.setSelectedIndex(0);
        cmbCrJk.setSelectedIndex(0);
        CmbCrGd.setSelectedIndex(0);
        CmbSttsKerja.setSelectedIndex(0);
        TCari.setText("");

        if (TabDokter.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDokter.getSelectedIndex() == 1) {
            tampil1();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode dokter");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama dokter");
        } else if (TSpesialis.getText().trim().equals("") || KdSps.getText().trim().equals("")) {
            Valid.textKosong(KdSps, "spesialis");
        } else {
            if (Turl.getText().trim().equals("") || Turl.getText().trim().equals(" ")) {
                urlfoto = "-";
            } else {
                urlfoto = Turl.getText();
            }
            
            try {
                koneksi.setAutoCommit(false);
                if (TabDokter.getSelectedIndex() == 0) {
                    Sequel.mengedit("pegawai", "nik='" + tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString() + "'",
                            "nik='" + TKd.getText() + "',nama='" + TNm.getText() + "',jk='" + CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria") + "',"
                            + "tmp_lahir='" + TTmp.getText() + "',tgl_lahir='" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "',alamat='" + TAlmt.getText() + "',no_ktp='" + TnoKTP.getText() + "'");

                    Sequel.mengedit("dokter", "kd_dokter='" + tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString() + "'", "kd_dokter='" + TKd.getText() + "',nm_dokter='" + TNm.getText()
                            + "',jk='" + CmbJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim()
                            + "',tmp_lahir='" + TTmp.getText()
                            + "',tgl_lahir='" + Valid.SetTgl(DTPLahir.getSelectedItem() + "")
                            + "',gol_drh='" + CMbGd.getSelectedItem()
                            + "',agama='" + cmbAgama.getSelectedItem()
                            + "',almt_tgl='" + TAlmt.getText()
                            + "',no_telp='" + TTlp.getText()
                            + "',stts_nikah='" + CmbStts.getSelectedItem()
                            + "',kd_sps='" + KdSps.getText()
                            + "',alumni='" + TAlumni.getText()
                            + "',no_ijn_praktek='" + TNoi.getText()
                            + "',status='" + kerja1
                            + "',url_photo='" + urlfoto + "'");

                } else if (TabDokter.getSelectedIndex() == 1) {
                    Sequel.mengedit("pegawai", "nik='" + tbDokter1.getValueAt(tbDokter1.getSelectedRow(), 0).toString() + "'",
                            "nik='" + TKd.getText() + "',nama='" + TNm.getText() + "',jk='" + CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria") + "',"
                            + "tmp_lahir='" + TTmp.getText() + "',tgl_lahir='" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "',alamat='" + TAlmt.getText() + "',no_ktp='" + TnoKTP.getText() + "'");

                    Sequel.mengedit("dokter", "kd_dokter='" + tbDokter1.getValueAt(tbDokter1.getSelectedRow(), 0).toString() + "'", "kd_dokter='" + TKd.getText() + "',nm_dokter='" + TNm.getText()
                            + "',jk='" + CmbJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim()
                            + "',tmp_lahir='" + TTmp.getText()
                            + "',tgl_lahir='" + Valid.SetTgl(DTPLahir.getSelectedItem() + "")
                            + "',gol_drh='" + CMbGd.getSelectedItem()
                            + "',agama='" + cmbAgama.getSelectedItem()
                            + "',almt_tgl='" + TAlmt.getText()
                            + "',no_telp='" + TTlp.getText()
                            + "',stts_nikah='" + CmbStts.getSelectedItem()
                            + "',kd_sps='" + KdSps.getText()
                            + "',alumni='" + TAlumni.getText()
                            + "',no_ijn_praktek='" + TNoi.getText()
                            + "',status='" + kerja1
                            + "',url_photo='" + urlfoto + "'");
                }
                
                if (TabDokter.getSelectedIndex() == 0) {
                    tampil();
                    emptTeks();
                } else if (TabDokter.getSelectedIndex() == 1) {
                    tampil1();
                    emptTeks();
                }
                
                koneksi.setAutoCommit(true);                     
            } catch (SQLException ex) {
                return;
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabDokter.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDokter.getSelectedIndex() == 1) {
            tampil1();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void cmbCrJkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCrJkItemStateChanged
        tampil();
}//GEN-LAST:event_cmbCrJkItemStateChanged

    private void cmbCrJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCrJkKeyPressed
        Valid.pindah(evt,BtnAll, CmbCrGd);
}//GEN-LAST:event_cmbCrJkKeyPressed

    private void CmbCrGdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbCrGdKeyPressed
        Valid.pindah(evt,cmbCrJk, CmbCrStts);
}//GEN-LAST:event_CmbCrGdKeyPressed

    private void CmbCrSttsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbCrSttsKeyPressed
        Valid.pindah(evt,CmbCrGd,TCari);
}//GEN-LAST:event_CmbCrSttsKeyPressed

    private void TCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyTyped
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyTyped

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbDokterMouseClicked

    private void tbDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDokterKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDokterKeyPressed

private void KdSpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdSpsKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){            
                Sequel.cariIsi("select nm_sps from spesialis where kd_sps=?",TSpesialis,KdSps.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){  
            btnSpesialActionPerformed(null);
        }else{            
            Valid.pindah(evt,TTlp,TAlumni);
        }
}//GEN-LAST:event_KdSpsKeyPressed

private void btnSpesialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpesialActionPerformed
        spesial.emptTeks();
        spesial.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        spesial.setLocationRelativeTo(internalFrame1);
        spesial.setAlwaysOnTop(false);
        spesial.setVisible(true);
}//GEN-LAST:event_btnSpesialActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void BtnCariPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPegawaiActionPerformed
        pegawai.isCek();        
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
        pegawai.TCari.requestFocus();
    }//GEN-LAST:event_BtnCariPegawaiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (TabDokter.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDokter.getSelectedIndex() == 1) {
            tampil1();
        }
    }//GEN-LAST:event_formWindowOpened

    private void MnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRestoreActionPerformed
        DlgRestoreDokter restore = new DlgRestoreDokter(null, true);
        restore.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        restore.setLocationRelativeTo(internalFrame1);
        restore.setVisible(true);
        restore.TCari.requestFocus();
    }//GEN-LAST:event_MnRestoreActionPerformed

    private void TnoKTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoKTPKeyPressed
        Valid.pindah(evt,TnoKTP,btnSpesial);
    }//GEN-LAST:event_TnoKTPKeyPressed

    private void CmbSttsKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbSttsKerjaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbSttsKerjaKeyPressed

    private void CmbSttsKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbSttsKerjaActionPerformed
        kerja1 = "";

        if (CmbSttsKerja.getSelectedItem().toString().equals("AKTIF")) {
            kerja1 = "1";
        } else {
            kerja1 = "0";
        }
    }//GEN-LAST:event_CmbSttsKerjaActionPerformed

    private void TabDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDokterMouseClicked
        if (TabDokter.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDokter.getSelectedIndex() == 1) {
            tampil1();
        }
    }//GEN-LAST:event_TabDokterMouseClicked

    private void tbDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDokter1KeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDokter1KeyPressed

    private void tbDokter1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokter1MouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getData1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDokter1MouseClicked

    private void TurlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TurlKeyPressed

    private void MnDokterBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterBPJSActionPerformed
        dpjp.setSize(872, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.Dokter.requestFocus();
        dpjp.poliklinik("", "");
    }//GEN-LAST:event_MnDokterBPJSActionPerformed

    private void MnDokterSatuSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterSatuSehatActionPerformed
//        if (tbDokter.getSelectedRow() == 0) {
//            JOptionPane.showMessageDialog(rootPane, "Silahkan salah klik satu nama dokternya terlebih dahulu..!!");
//        } else {
//            kddokterSatuSehat = "";
//            try {
                kddokterSatuSehat = cekViaSatuSehat.tampilIDParktisi(TnoKTP.getText());
                if (!kddokterSatuSehat.equals("")) {
                    if (Sequel.cariInteger("select count(-1) from mapping_dokter where kd_dokter_rs='" + TKd.getText() + "'") == 0) {
                        Sequel.menyimpan("mapping_dokter", "?,?,?", "Kode Dokter", 3, new String[]{"", TKd.getText(), kddokterSatuSehat});
                    } else {
                        Sequel.mengedit("mapping_dokter", "kd_dokter_rs='" + TKd.getText() + "'", "kd_dokter_satu_sehat='" + kddokterSatuSehat + "'");
                    }
                }
//            } catch (Exception e) {
//                System.out.println("Notifikasi : " + e);
//            }
            tampil();
//        }
    }//GEN-LAST:event_MnDokterSatuSehatActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDokter dialog = new DlgDokter(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCariPegawai;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CMbGd;
    private widget.CekBox ChkInput;
    private widget.ComboBox CmbCrGd;
    private widget.ComboBox CmbCrStts;
    private widget.ComboBox CmbJk;
    private widget.ComboBox CmbStts;
    private widget.ComboBox CmbSttsKerja;
    private widget.Tanggal DTPLahir;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdSps;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokterBPJS;
    private javax.swing.JMenuItem MnDokterSatuSehat;
    private javax.swing.JMenuItem MnRestore;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox TAlmt;
    private widget.TextBox TAlumni;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.TextBox TNm;
    private widget.TextBox TNoi;
    private widget.TextBox TSpesialis;
    private widget.TextBox TTlp;
    private widget.TextBox TTmp;
    private javax.swing.JTabbedPane TabDokter;
    private widget.TextBox TnoKTP;
    private widget.TextBox Turl;
    private widget.Button btnSpesial;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbCrJk;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator4;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbDokter;
    private widget.Table tbDokter1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            stat = koneksi.prepareStatement(
                    "SELECT dokter.kd_dokter, dokter.nm_dokter, dokter.jk, dokter.tmp_lahir, dokter.tgl_lahir, dokter.gol_drh, dokter.agama, dokter.almt_tgl, "
                    + "dokter.no_telp, dokter.stts_nikah, spesialis.nm_sps, dokter.alumni, dokter.no_ijn_praktek, IF(pegawai.no_ktp='','-',pegawai.no_ktp) no_ktp, "
                    + "if(dokter.status='1','AKTIF','NON AKTIF') status, ifnull(dokter.url_photo,'-') url_photo "
                    + "FROM dokter INNER JOIN spesialis ON dokter.kd_sps = spesialis.kd_sps INNER JOIN pegawai ON pegawai.nik=dokter.kd_dokter "
                    + "where dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.kd_dokter like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.nm_dokter like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.tmp_lahir like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.tgl_lahir like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.agama like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.almt_tgl like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.no_telp like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.stts_nikah like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.url_photo like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and spesialis.nm_sps like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.alumni like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and pegawai.no_ktp like ? or "
                    + "dokter.status='1' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.no_ijn_praktek like ? "
                    + "order by dokter.kd_dokter desc");
            try {
                stat.setString(1, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(2, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(3, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(4, "%" + TCari.getText().trim() + "%");
                stat.setString(5, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(6, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(7, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(8, "%" + TCari.getText().trim() + "%");
                stat.setString(9, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(10, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(11, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(12, "%" + TCari.getText().trim() + "%");
                stat.setString(13, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(14, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(15, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(16, "%" + TCari.getText().trim() + "%");
                stat.setString(17, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(18, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(19, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(20, "%" + TCari.getText().trim() + "%");
                stat.setString(21, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(22, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(23, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(24, "%" + TCari.getText().trim() + "%");
                stat.setString(25, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(26, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(27, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(28, "%" + TCari.getText().trim() + "%");
                stat.setString(29, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(30, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(31, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(32, "%" + TCari.getText().trim() + "%");
                stat.setString(33, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(34, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(35, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(36, "%" + TCari.getText().trim() + "%");
                stat.setString(37, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(38, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(39, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(40, "%" + TCari.getText().trim() + "%");
                stat.setString(41, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(42, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(43, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(44, "%" + TCari.getText().trim() + "%");
                stat.setString(45, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(46, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(47, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(48, "%" + TCari.getText().trim() + "%");
                stat.setString(49, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat.setString(50, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat.setString(51, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat.setString(52, "%" + TCari.getText().trim() + "%");
                rs = stat.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        Sequel.cariIsi("select ifnull(kd_dokter_bpjs,'') from mapping_dokter where kd_dokter_rs='" + rs.getString(1) + "'"),
                        Sequel.cariIsi("select ifnull(kd_dokter_satu_sehat,'') from mapping_dokter where kd_dokter_rs='" + rs.getString(1) + "'")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (stat != null) {
                    stat.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    private void tampil1() {
        Valid.tabelKosong(tabMode1);
        try {
            stat1 = koneksi.prepareStatement(
                    "SELECT dokter.kd_dokter, dokter.nm_dokter, dokter.jk, dokter.tmp_lahir, dokter.tgl_lahir, dokter.gol_drh, dokter.agama, dokter.almt_tgl, "
                    + "dokter.no_telp, dokter.stts_nikah, spesialis.nm_sps, dokter.alumni, dokter.no_ijn_praktek, IF(pegawai.no_ktp='','-',pegawai.no_ktp) no_ktp, "
                    + "if(dokter.status='1','AKTIF','NON AKTIF') status, ifnull(dokter.url_photo,'-') url_photo "
                    + "FROM dokter INNER JOIN spesialis ON dokter.kd_sps = spesialis.kd_sps INNER JOIN pegawai ON pegawai.nik=dokter.kd_dokter "
                    + "where dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.kd_dokter like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.nm_dokter like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.tmp_lahir like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.tgl_lahir like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.agama like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.almt_tgl like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.no_telp like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.stts_nikah like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.url_photo like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and spesialis.nm_sps like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.alumni like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and pegawai.no_ktp like ? or "
                    + "dokter.status='0' and dokter.jk like ? and dokter.gol_drh like ? and dokter.stts_nikah like ? and dokter.no_ijn_praktek like ? "
                    + "order by dokter.kd_dokter desc");
            try {
                stat1.setString(1, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(2, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(3, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(4, "%" + TCari.getText().trim() + "%");
                stat1.setString(5, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(6, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(7, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(8, "%" + TCari.getText().trim() + "%");
                stat1.setString(9, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(10, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(11, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(12, "%" + TCari.getText().trim() + "%");
                stat1.setString(13, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(14, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(15, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(16, "%" + TCari.getText().trim() + "%");
                stat1.setString(17, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(18, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(19, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(20, "%" + TCari.getText().trim() + "%");
                stat1.setString(21, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(22, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(23, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(24, "%" + TCari.getText().trim() + "%");
                stat1.setString(25, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(26, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(27, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(28, "%" + TCari.getText().trim() + "%");
                stat1.setString(29, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(30, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(31, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(32, "%" + TCari.getText().trim() + "%");
                stat1.setString(33, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(34, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(35, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(36, "%" + TCari.getText().trim() + "%");
                stat1.setString(37, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(38, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(39, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(40, "%" + TCari.getText().trim() + "%");
                stat1.setString(41, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(42, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(43, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(44, "%" + TCari.getText().trim() + "%");
                stat1.setString(45, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(46, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(47, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(48, "%" + TCari.getText().trim() + "%");
                stat1.setString(49, "%" + cmbCrJk.getSelectedItem().toString().replaceAll("LAKI-LAKI", "L").replaceAll("PEREMPUAN", "P").trim() + "%");
                stat1.setString(50, "%" + CmbCrGd.getSelectedItem().toString().trim() + "%");
                stat1.setString(51, "%" + CmbCrStts.getSelectedItem().toString().trim() + "%");
                stat1.setString(52, "%" + TCari.getText().trim() + "%");
                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9),
                        rs1.getString(10),
                        rs1.getString(11),
                        rs1.getString(12),
                        rs1.getString(13),
                        rs1.getString(14),
                        rs1.getString(15),
                        rs1.getString(16)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }

                if (stat1 != null) {
                    stat1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode1.getRowCount());
    }

    public void emptTeks() {
        TKd.setText("");
        TNm.setText("");
        CmbJk.setSelectedIndex(0);
        CMbGd.setSelectedIndex(0);
        TTmp.setText("");
        cmbAgama.setSelectedIndex(0);
        CmbStts.setSelectedIndex(0);
        TAlmt.setText("");
        TAlumni.setText("");
        TNoi.setText("");
        TTlp.setText("");
        KdSps.setText("");
        TSpesialis.setText("");
        TnoKTP.setText("");
        DTPLahir.setDate(new Date());
        TKd.requestFocus();
        Valid.autoNomerDokter(" dokter ","D",7,TKd);
        CmbSttsKerja.setSelectedIndex(0);
        Turl.setText("-");
    }

    private void getData() {
        kerja2 = "";
        
        int row=tbDokter.getSelectedRow();
        if(row!= -1){
            TKd.setText(tbDokter.getValueAt(row,0).toString());
            TNm.setText(tbDokter.getValueAt(row,1).toString());
            TTmp.setText(tbDokter.getValueAt(row,3).toString());
            CMbGd.setSelectedItem(tbDokter.getValueAt(row,5).toString());
            cmbAgama.setSelectedItem(tbDokter.getValueAt(row,6).toString());
            TAlmt.setText(tbDokter.getValueAt(row,7).toString());
            TTlp.setText(tbDokter.getValueAt(row,8).toString());
            CmbStts.setSelectedItem(tbDokter.getValueAt(row,9).toString());
            TSpesialis.setText(tbDokter.getValueAt(row,10).toString());
            Sequel.cariIsi("select kd_sps from spesialis where nm_sps='"+tbDokter.getValueAt(row,10).toString()+"'", KdSps);
            TAlumni.setText(tbDokter.getValueAt(row,11).toString());
            TNoi.setText(tbDokter.getValueAt(row,12).toString());
            TnoKTP.setText(tbDokter.getValueAt(row,13).toString());
            kerja2 = tbDokter.getValueAt(row,14).toString();
            Turl.setText(tbDokter.getValueAt(row,15).toString());
            
            switch (tbDokter.getValueAt(row,2).toString()) {
                case "L":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "P":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }
            
            Valid.SetTgl(DTPLahir,tbDokter.getValueAt(row,4).toString());
            
            if (kerja2.equals("AKTIF")) {
                CmbSttsKerja.setSelectedIndex(0);
            } else {
                CmbSttsKerja.setSelectedIndex(1);
            }       
        }
    }
    
    private void getData1() {
        kerja2 = "";
        
        int row=tbDokter1.getSelectedRow();
        if(row!= -1){
            TKd.setText(tbDokter1.getValueAt(row,0).toString());
            TNm.setText(tbDokter1.getValueAt(row,1).toString());
            TTmp.setText(tbDokter1.getValueAt(row,3).toString());
            CMbGd.setSelectedItem(tbDokter1.getValueAt(row,5).toString());
            cmbAgama.setSelectedItem(tbDokter1.getValueAt(row,6).toString());
            TAlmt.setText(tbDokter1.getValueAt(row,7).toString());
            TTlp.setText(tbDokter1.getValueAt(row,8).toString());
            CmbStts.setSelectedItem(tbDokter1.getValueAt(row,9).toString());
            TSpesialis.setText(tbDokter1.getValueAt(row,10).toString());
            Sequel.cariIsi("select kd_sps from spesialis where nm_sps='"+tbDokter1.getValueAt(row,10).toString()+"'", KdSps);
            TAlumni.setText(tbDokter1.getValueAt(row,11).toString());
            TNoi.setText(tbDokter1.getValueAt(row,12).toString());
            TnoKTP.setText(tbDokter1.getValueAt(row,13).toString());
            kerja2 = tbDokter1.getValueAt(row,14).toString();
            Turl.setText(tbDokter1.getValueAt(row,15).toString());
            
            switch (tbDokter1.getValueAt(row,2).toString()) {
                case "L":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "P":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }
            
            Valid.SetTgl(DTPLahir,tbDokter1.getValueAt(row,4).toString());
            
            if (kerja2.equals("AKTIF")) {
                CmbSttsKerja.setSelectedIndex(0);
            } else {
                CmbSttsKerja.setSelectedIndex(1);
            }      
        }
    }
    
    public JTextField getTextField(){
        return TKd;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    private void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 215));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdokter());
        BtnEdit.setEnabled(akses.getdokter());
        TabDokter.setEnabledAt(1, akses.getstatus_kerja_dokter());
        CmbSttsKerja.setEnabled(akses.getstatus_kerja_dokter());        
//        BtnPrint.setEnabled(var.getdokter());
//        BtnHapus.setEnabled(var.getdokter());

        if (akses.getkode().equals("Admin Utama")) {
            MnRestore.setEnabled(true);
        } else {
            MnRestore.setEnabled(false);
        }
    }
}
