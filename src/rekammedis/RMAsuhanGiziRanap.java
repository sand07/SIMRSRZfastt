/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */

package rekammedis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsuhanGiziRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, 
            tabMode6, tabMode7, tabMode8, tabMode9, tabMode10, tabMode11, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15, ps16,
            ps17, ps18, ps19, ps20, pscppt, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15, rs16,
            rs17, rs18, rs19, rs20, rscppt, rsrestor;
    private int i = 0, x = 0, z = 0, jml = 0, cetakBBU = 0, cetakPBU = 0, cetakBBPB = 0, cekTampil = 0;
    private boolean[] pilih;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String user = "", dataKonfirmasi = "", mual = "", nyeri = "", diare = "", kesulitan = "", odema = "",
            konstipasi = "", anoreksia = "", gangguan = "", makanlebih = "", makankurang = "", asupanCukup = "",
            asupanMenurun = "", asupanRendah = "", asupanTidak = "", alergi = "", pantangan = "", tglHistori = "",
            cekumur = "", ceksttsumur = "", diagnosaPrin = "", dataKlinis1 = "", dataKlinis2 = "", klinis1 = "",
            klinis2 = "", klinis3 = "", klinis4 = "", klinis5 = "", klinis6 = "", klinis7 = "", klinis8 = "",
            dataRiwayat1 = "", dataRiwayat2 = "", riw1 = "", riw2 = "", riw3 = "", riw4 = "", riw5 = "", riw6 = "",
            riw7 = "", riw8 = "", riw9 = "", asupanmakan = "", antro = "", biokimia = "", klinis = "", lainlain = "",
            rencanamonev = "", ren1 = "", ren2 = "", ren3 = "", ren4 = "", ren5 = "", bbu = "", pbu = "", bbpb = "",
            gizianak1 = "", gizianak2 = "", gizianak3 = "", kodekamar = "", sttsgizi = "", nmgedung = "", sttsgizibbu = "",
            sttsgizipbu = "", sttsgizibbpb = "", sttsgiziprin = "", angkaBulan = "";
    private String[] kode, diagnosa;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsuhanGiziRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Umur/Jns. Asuhan", "Jns. Kelamin", "Tgl. Asuhan", "Ruang Perawatan", "Nama Ahli Gizi",
            "tgl_asuhan", "ruang_rawat", "bb", "tb", "imt", "lila", "tinggi_lutut", "ulna", "tb_est", "bb_koreksi",
            "bbi", "status_gizi", "biokimia", "mual_muntah", "nyeri_ulu_hati", "diare", "kesulitan_menelan", "oedema", "konstipasi",
            "anoreksia", "gangguan_gigi_geligi", "klinis_lainnya", "makan_lebih_3x", "makan_kurang_3x", "riwayat_gizi_lainnya",
            "alergi_makanan", "ket_alergi_makanan", "pantangan_makan", "ket_pantangan_makan", "asupan_cukup", "asupan_menurun",
            "asupan_rendah", "asupan_tidak_cukup", "hasil_recall", "riwayat_penyakit_personal", "berkaitan_dengan", "ditandai_dengan",
            "bentuk_makanan", "jenis_diet", "rute_makanan", "metode_hitungan", "kalori", "bmr", "faktor_aktivitas", "jns_faktor_aktivitas",
            "faktor_koreksi", "faktor_stres", "jns_faktor_stres", "protein", "protein_lain", "lemak", "lemak_lain", "karbohidrat",
            "karbohidrat_lain", "nip_petugas", "umurcek", "sttsumur", "klasifikasi_imt", "persentase_cdc", "monev_asupan_makan",
            "monev_antropometri", "monev_biokimia", "monev_klinis", "monev_lain", "jenis_asuhan", "indek_bbu", "indek_pbu", "indek_bbpb", 
            "ket_indek_bbu", "ket_indek_pbu", "ket_indek_bbpb", "stts_gizi_bbu", "stts_gizi_pbu", "stts_gizi_bbpb"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsuhan.setModel(tabMode);
        tbAsuhan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsuhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 82; i++) {
            TableColumn column = tbAsuhan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 50) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 51) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 53) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 57) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 58) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 59) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 60) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 61) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 62) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 63) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 64) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 65) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 66) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 67) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 68) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 69) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 70) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 71) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 72) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 73) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 74) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 75) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 76) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 77) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 78) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 79) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 80) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 81) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbAsuhan.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asuhan", 
            "Tgl. Eksekusi", "Status Data", "Ruang Rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null,new Object[]{
                "Cek", "Kode Diagnosa", "Deskripsi Diagnosa"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbDiagnosa.setModel(tabMode2);
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(480);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null,new Object[]{
            "No. Rawat", "Kode Diagnosa", "Deskripsi Diagnosa", "Status", "Tanggal"
            }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayatDiagnosa.setModel(tabMode3);
        tbRiwayatDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbRiwayatDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(450);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } 
        }
        tbRiwayatDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new String[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asuhan", "Status Gizi", "Ruang Rawat/Gedung", "kdkamar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbStatusGZ.setModel(tabMode4);
        tbStatusGZ.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbStatusGZ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbStatusGZ.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(160);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbStatusGZ.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new String[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asuhan", "Status Gizi Indeks (BB/U)", "Status Gizi Indeks (PB/U atau TB/U)", 
            "Status Gizi Indeks (BB/PB atau BB/TB)", "Ruang Rawat/Gedung"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbStatusGZ1.setModel(tabMode5);
        tbStatusGZ1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbStatusGZ1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbStatusGZ1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(230);
            } else if (i == 6) {
                column.setPreferredWidth(230);
            } else if (i == 7) {
                column.setPreferredWidth(230);
            } else if (i == 8) {
                column.setPreferredWidth(160);
            } 
        }
        tbStatusGZ1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new String[]{
            "No.", "Ruang Rawat/Gedung", "Jlh. Pasien Dirawat", "Jlh. Mendapat Asuhan", "Jlh. Belum Dapat Asuhan", 
            "Persentase Asuhan Gizi", "Persentase Belum Dapat Asuhan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPersentase.setModel(tabMode6);
        tbPersentase.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPersentase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPersentase.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } 
        }
        tbPersentase.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new String[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Alamat", "Umur", "Tgl. Input", "Diagnosa Awal", "BB-Awal", "BB-Akhir",
            "PB/TB", "BB/U", "BB/PB", "PB/U", "Perhitng. Keb. Zat Gizi", "Diagn. dr. Sp.GZ/Ahli GZ", "Pemberian Nutrisi", 
            "Faskes/Puskesmas Asal"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBuruk.setModel(tabMode7);
        tbBuruk.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBuruk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbBuruk.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(50);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(50);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(250);            
            } else if (i == 17) {
                column.setPreferredWidth(200);
            }
        }
        tbBuruk.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode8 = new DefaultTableModel(null, new String[]{
            "No.", "No. Rawat", "No. RM", "Nama Pasien", "Umur", "Tgl. Asuhan", "Klasifikasi IMT", "Status Gizi",
            "Status Gizi Indeks (BB/U)", "Status Gizi Indeks (PB/U atau TB/U)", "Status Gizi Indeks (BB/PB atau BB/TB)",
            "Ruang Rawat/Gedung"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBalita.setModel(tabMode8);
        tbBalita.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBalita.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbBalita.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(195);
            } else if (i == 10) {
                column.setPreferredWidth(205);
            } else if (i == 11) {
                column.setPreferredWidth(140);
            } 
        }
        tbBalita.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode9 = new DefaultTableModel(null, new String[]{
            "No.", "Status Gizi", "Jlh. Pasien", "Persentase"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekapBBU.setModel(tabMode9);
        tbRekapBBU.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekapBBU.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbRekapBBU.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } 
        }
        tbRekapBBU.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode10 = new DefaultTableModel(null, new String[]{
            "No.", "Status Gizi", "Jlh. Pasien", "Persentase"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekapPBU.setModel(tabMode10);
        tbRekapPBU.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekapPBU.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbRekapPBU.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } 
        }
        tbRekapPBU.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode11 = new DefaultTableModel(null, new String[]{
            "No.", "Status Gizi", "Jlh. Pasien", "Persentase"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekapBBPB.setModel(tabMode11);
        tbRekapBBPB.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekapBBPB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbRekapBBPB.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } 
        }
        tbRekapBBPB.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tbb.setDocument(new batasInput((int) 5).getKata(Tbb));
        Ttb.setDocument(new batasInput((int) 5).getKata(Ttb));
        Tlila.setDocument(new batasInput((int) 5).getKata(Tlila));
        Tbbi.setDocument(new batasInput((int) 5).getKata(Tbbi));
        Ttl.setDocument(new batasInput((int) 5).getKata(Ttl));
        Tulna.setDocument(new batasInput((int) 5).getKata(Tulna));
        TtbEst.setDocument(new batasInput((int) 20).getKata(TtbEst));
        TbbKoreksi.setDocument(new batasInput((int) 40).getKata(TbbKoreksi));
        Talergi.setDocument(new batasInput((int) 220).getKata(Talergi));
        Tpantangan.setDocument(new batasInput((int) 220).getKata(Tpantangan));
        TjnsDiet.setDocument(new batasInput((int) 220).getKata(TjnsDiet));
        Tkalori.setDocument(new batasInput((int) 40).getKata(Tkalori));
        Tprotein.setDocument(new batasInput((int) 20).getKata(Tprotein));
        Tlemak.setDocument(new batasInput((int) 20).getKata(Tlemak));
        Tkarbo.setDocument(new batasInput((int) 20).getKata(Tkarbo));
        Tfs.setDocument(new batasInput((int) 3).getKata(Tfs));
        Tfk.setDocument(new batasInput((int) 4).getKata(Tfk));
        TCariDiagnosa.setDocument(new batasInput((int) 100).getKata(TCariDiagnosa));
        TketBbu.setDocument(new batasInput((int) 20).getKata(TketBbu));
        TketPbu.setDocument(new batasInput((int) 20).getKata(TketPbu));
        TketBbpb.setDocument(new batasInput((int) 20).getKata(TketBbpb));
        Tpersentase.setDocument(new batasInput((int) 6).getKata(Tpersentase));
        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
        TbbAkhir.setDocument(new batasInput((int) 5).getKata(TbbAkhir));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    Tnip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        MnRiwayatData = new javax.swing.JMenuItem();
        MnMonev = new javax.swing.JMenuItem();
        MnAsesmenUlang = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusConteng = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnHapusRiwayat = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        MnCetakLapRekap = new javax.swing.JMenuItem();
        MnCetakLapStatusGZ = new javax.swing.JMenuItem();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        MnCetakLapRekap1 = new javax.swing.JMenuItem();
        MnCetakLapStatusGZ1 = new javax.swing.JMenuItem();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        MnIndividuPx = new javax.swing.JMenuItem();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel30 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel33 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        internalFrame20 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbRiwayatDiagnosa = new widget.Table();
        WindowLapStatusGZ = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnCetak = new widget.Button();
        jLabel17 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        jLabel18 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        tgl2 = new widget.Tanggal();
        WindowLapStatusGZanak = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnCetak1 = new widget.Button();
        jLabel26 = new widget.Label();
        cmbGedung1 = new widget.ComboBox();
        jLabel27 = new widget.Label();
        tgl3 = new widget.Tanggal();
        jLabel28 = new widget.Label();
        tgl4 = new widget.Tanggal();
        jLabel37 = new widget.Label();
        cmbPilihAsuhan = new widget.ComboBox();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tbb = new widget.TextBox();
        Tlila = new widget.TextBox();
        jLabel34 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel12 = new widget.Label();
        tglAsuhan = new widget.Tanggal();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        label105 = new widget.Label();
        Ttb = new widget.TextBox();
        Tjk = new widget.TextBox();
        jLabel66 = new widget.Label();
        label107 = new widget.Label();
        BtnNilaiIMT = new widget.Button();
        Timt = new widget.TextBox();
        label108 = new widget.Label();
        label109 = new widget.Label();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        Ttl = new widget.TextBox();
        label106 = new widget.Label();
        Tulna = new widget.TextBox();
        label110 = new widget.Label();
        BtnNilaiTB = new widget.Button();
        TtbEst = new widget.TextBox();
        label111 = new widget.Label();
        TbbKoreksi = new widget.TextBox();
        label112 = new widget.Label();
        cmbSttsGizi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tbiokimia = new widget.TextArea();
        jLabel71 = new widget.Label();
        ChkMual = new widget.CekBox();
        ChkNyeri = new widget.CekBox();
        ChkDiare = new widget.CekBox();
        ChkKesulitan = new widget.CekBox();
        ChkOedema = new widget.CekBox();
        ChkKonstipasi = new widget.CekBox();
        ChkAnorek = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        jLabel72 = new widget.Label();
        TklinisLain = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        ChkMakanLebih3 = new widget.CekBox();
        ChkMakanKurang3 = new widget.CekBox();
        TriwayatLain = new widget.TextBox();
        jLabel75 = new widget.Label();
        ChkAlergi = new widget.CekBox();
        Talergi = new widget.TextBox();
        ChkPantangan = new widget.CekBox();
        Tpantangan = new widget.TextBox();
        jLabel76 = new widget.Label();
        ChkAsupanCukup = new widget.CekBox();
        ChkAsupanMenurun = new widget.CekBox();
        jLabel77 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TriwPenyakit = new widget.TextArea();
        jLabel78 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        jLabel79 = new widget.Label();
        TCariDiagnosa = new widget.TextBox();
        BtnCariDiagnosa = new widget.Button();
        BtnAllDiagnosa = new widget.Button();
        jLabel13 = new widget.Label();
        Tumur = new widget.TextBox();
        Tsttsumur = new widget.Label();
        jLabel80 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Tberkaitan = new widget.TextArea();
        scrollPane16 = new widget.ScrollPane();
        Tditandai = new widget.TextArea();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbBentuk = new widget.ComboBox();
        jLabel84 = new widget.Label();
        TjnsDiet = new widget.TextBox();
        jLabel85 = new widget.Label();
        Tkalori = new widget.TextBox();
        jLabel86 = new widget.Label();
        Tprotein = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        Tlemak = new widget.TextBox();
        Tkarbo = new widget.TextBox();
        BtnPasteHasil = new widget.Button();
        Tbbi = new widget.TextBox();
        label113 = new widget.Label();
        jLabel89 = new widget.Label();
        cmbHasilRecal = new widget.ComboBox();
        cmbProtein = new widget.ComboBox();
        cmbLemak = new widget.ComboBox();
        cmbKarbo = new widget.ComboBox();
        BtnNilaiBB = new widget.Button();
        jLabel90 = new widget.Label();
        cmbRute = new widget.ComboBox();
        ChkAsupanRendah = new widget.CekBox();
        ChkAsupanTdkCukup = new widget.CekBox();
        jLabel91 = new widget.Label();
        cmbMetode = new widget.ComboBox();
        jLabel92 = new widget.Label();
        cmbFaktorA = new widget.ComboBox();
        Tfa = new widget.TextBox();
        jLabel93 = new widget.Label();
        cmbFaktorS = new widget.ComboBox();
        Tfs = new widget.TextBox();
        jLabel94 = new widget.Label();
        Tfk = new widget.TextBox();
        jLabel95 = new widget.Label();
        Tbmr = new widget.TextBox();
        BtnNilaiKalori = new widget.Button();
        jLabel96 = new widget.Label();
        ChkAsupanMakan = new widget.CekBox();
        ChkAntropometri = new widget.CekBox();
        ChkBiokimia = new widget.CekBox();
        ChkKlinis = new widget.CekBox();
        ChkLain_lain = new widget.CekBox();
        jLabel97 = new widget.Label();
        cmbKlasifikasiIMT = new widget.ComboBox();
        jLabel98 = new widget.Label();
        ChkBbu = new widget.CekBox();
        TketBbu = new widget.TextBox();
        jLabel99 = new widget.Label();
        cmbBbu = new widget.ComboBox();
        ChkPbu = new widget.CekBox();
        TketPbu = new widget.TextBox();
        jLabel100 = new widget.Label();
        cmbPbu = new widget.ComboBox();
        ChkBbpb = new widget.CekBox();
        TketBbpb = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbBbpb = new widget.ComboBox();
        jLabel14 = new widget.Label();
        cmbAsuhan = new widget.ComboBox();
        Tpersentase = new widget.TextBox();
        BtnCekSttsGizi = new widget.Button();
        label114 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        ThitungZat = new widget.TextBox();
        jLabel104 = new widget.Label();
        TdiagnosaDokter = new widget.TextBox();
        jLabel105 = new widget.Label();
        Tpemberian = new widget.TextBox();
        jLabel106 = new widget.Label();
        Tfaskes = new widget.TextBox();
        jLabel107 = new widget.Label();
        TbbAkhir = new widget.TextBox();
        jLabel108 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsuhan = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbStatusGZ = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari5 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari6 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount2 = new widget.Label();
        internalFrame6 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbStatusGZ1 = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel24 = new widget.Label();
        DTPCari7 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari8 = new widget.Tanggal();
        jLabel11 = new widget.Label();
        TCari3 = new widget.TextBox();
        BtnCari3 = new widget.Button();
        BtnAll3 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        jLabel15 = new widget.Label();
        LCount3 = new widget.Label();
        internalFrame8 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbPersentase = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbBulan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Ttahun = new widget.TextBox();
        BtnCari4 = new widget.Button();
        BtnCetakPersentase = new widget.Button();
        BtnKeluar4 = new widget.Button();
        internalFrame9 = new widget.InternalFrame();
        Scroll8 = new widget.ScrollPane();
        tbBuruk = new widget.Table();
        panelGlass13 = new widget.panelisi();
        jLabel109 = new widget.Label();
        DTPCari9 = new widget.Tanggal();
        jLabel110 = new widget.Label();
        DTPCari10 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCari4 = new widget.TextBox();
        BtnCari5 = new widget.Button();
        BtnAll4 = new widget.Button();
        BtnCetakPersentase1 = new widget.Button();
        BtnKeluar5 = new widget.Button();
        jLabel36 = new widget.Label();
        LCount4 = new widget.Label();
        internalFrame10 = new widget.InternalFrame();
        panelGlass16 = new widget.panelisi();
        Scroll9 = new widget.ScrollPane();
        tbBalita = new widget.Table();
        panelGlass17 = new widget.panelisi();
        Scroll10 = new widget.ScrollPane();
        tbRekapBBU = new widget.Table();
        Scroll11 = new widget.ScrollPane();
        tbRekapPBU = new widget.Table();
        Scroll12 = new widget.ScrollPane();
        tbRekapBBPB = new widget.Table();
        panelGlass15 = new widget.panelisi();
        jLabel111 = new widget.Label();
        DTPCari11 = new widget.Tanggal();
        jLabel112 = new widget.Label();
        DTPCari12 = new widget.Tanggal();
        jLabel38 = new widget.Label();
        TCari5 = new widget.TextBox();
        BtnCari6 = new widget.Button();
        BtnAll5 = new widget.Button();
        BtnCetakPersentase2 = new widget.Button();
        BtnKeluar6 = new widget.Button();
        jLabel39 = new widget.Label();
        LCount5 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus/Diganti");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        MnMonev.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonev.setText("Monitoring & Evaluasi Gizi");
        MnMonev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMonev.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMonev.setIconTextGap(5);
        MnMonev.setName("MnMonev"); // NOI18N
        MnMonev.setPreferredSize(new java.awt.Dimension(195, 26));
        MnMonev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonevActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMonev);

        MnAsesmenUlang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenUlang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenUlang.setText("Asesmen Ulang Gizi");
        MnAsesmenUlang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenUlang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenUlang.setIconTextGap(5);
        MnAsesmenUlang.setName("MnAsesmenUlang"); // NOI18N
        MnAsesmenUlang.setPreferredSize(new java.awt.Dimension(195, 26));
        MnAsesmenUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenUlangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnAsesmenUlang);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Ceklist");
        MnHapusConteng.setToolTipText("");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(130, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusConteng);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnHapusRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusRiwayat.setText("Hapus Riwayat Data");
        MnHapusRiwayat.setToolTipText("");
        MnHapusRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusRiwayat.setIconTextGap(5);
        MnHapusRiwayat.setName("MnHapusRiwayat"); // NOI18N
        MnHapusRiwayat.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusRiwayatActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnHapusRiwayat);

        jPopupMenu4.setName("jPopupMenu4"); // NOI18N

        MnCetakLapRekap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakLapRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakLapRekap.setText("Cetak Lap. Rekap");
        MnCetakLapRekap.setName("MnCetakLapRekap"); // NOI18N
        MnCetakLapRekap.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetakLapRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakLapRekapActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnCetakLapRekap);

        MnCetakLapStatusGZ.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakLapStatusGZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakLapStatusGZ.setText("Cetak Lap. Status Gizi");
        MnCetakLapStatusGZ.setName("MnCetakLapStatusGZ"); // NOI18N
        MnCetakLapStatusGZ.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetakLapStatusGZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakLapStatusGZActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnCetakLapStatusGZ);

        jPopupMenu5.setName("jPopupMenu5"); // NOI18N

        MnCetakLapRekap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakLapRekap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakLapRekap1.setText("Cetak Lap. Rekap");
        MnCetakLapRekap1.setName("MnCetakLapRekap1"); // NOI18N
        MnCetakLapRekap1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetakLapRekap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakLapRekap1ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnCetakLapRekap1);

        MnCetakLapStatusGZ1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakLapStatusGZ1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakLapStatusGZ1.setText("Cetak Lap. Status Gizi");
        MnCetakLapStatusGZ1.setName("MnCetakLapStatusGZ1"); // NOI18N
        MnCetakLapStatusGZ1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetakLapStatusGZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakLapStatusGZ1ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnCetakLapStatusGZ1);

        jPopupMenu6.setName("jPopupMenu6"); // NOI18N

        MnIndividuPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIndividuPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIndividuPx.setText("Cetak Data Individu Pasien");
        MnIndividuPx.setName("MnIndividuPx"); // NOI18N
        MnIndividuPx.setPreferredSize(new java.awt.Dimension(190, 26));
        MnIndividuPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIndividuPxActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnIndividuPx);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asuhan Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel30);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("s.d.");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel31);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel32);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(BtnCari2);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Record :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel33);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll1);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        internalFrame20.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame20.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setComponentPopupMenu(jPopupMenu3);
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbRiwayat);

        internalFrame20.add(Scroll6, java.awt.BorderLayout.CENTER);

        Scroll7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Riwayat Diganosa ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.setPreferredSize(new java.awt.Dimension(352, 402));

        tbRiwayatDiagnosa.setToolTipText("");
        tbRiwayatDiagnosa.setName("tbRiwayatDiagnosa"); // NOI18N
        Scroll7.setViewportView(tbRiwayatDiagnosa);

        internalFrame20.add(Scroll7, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame20, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowLapStatusGZ.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowLapStatusGZ.setName("WindowLapStatusGZ"); // NOI18N
        WindowLapStatusGZ.setUndecorated(true);
        WindowLapStatusGZ.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Lap. Status Gizi Pasien Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(230, 90, 90, 30);

        BtnCetak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetak.setMnemonic('C');
        BtnCetak.setText("Cetak");
        BtnCetak.setToolTipText("Alt+C");
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCetak);
        BtnCetak.setBounds(120, 90, 100, 30);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nama Gedung : ");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame5.add(jLabel17);
        jLabel17.setBounds(0, 28, 118, 23);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        internalFrame5.add(cmbGedung);
        cmbGedung.setBounds(120, 28, 205, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Periode Tgl. MRS : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 58, 118, 23);

        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame5.add(tgl1);
        tgl1.setBounds(120, 58, 85, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("s.d.");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame5.add(jLabel23);
        jLabel23.setBounds(207, 58, 30, 23);

        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame5.add(tgl2);
        tgl2.setBounds(240, 58, 85, 23);

        WindowLapStatusGZ.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowLapStatusGZanak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowLapStatusGZanak.setName("WindowLapStatusGZanak"); // NOI18N
        WindowLapStatusGZanak.setUndecorated(true);
        WindowLapStatusGZanak.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Lap. Status Gizi Pasien Anak ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(230, 118, 90, 30);

        BtnCetak1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetak1.setMnemonic('C');
        BtnCetak1.setText("Cetak");
        BtnCetak1.setToolTipText("Alt+C");
        BtnCetak1.setName("BtnCetak1"); // NOI18N
        BtnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetak1ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCetak1);
        BtnCetak1.setBounds(120, 118, 100, 30);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nama Gedung : ");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame7.add(jLabel26);
        jLabel26.setBounds(0, 28, 118, 23);

        cmbGedung1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbGedung1.setName("cmbGedung1"); // NOI18N
        internalFrame7.add(cmbGedung1);
        cmbGedung1.setBounds(120, 28, 205, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Periode Tgl. MRS : ");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame7.add(jLabel27);
        jLabel27.setBounds(0, 58, 118, 23);

        tgl3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        tgl3.setDisplayFormat("dd-MM-yyyy");
        tgl3.setName("tgl3"); // NOI18N
        tgl3.setOpaque(false);
        tgl3.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame7.add(tgl3);
        tgl3.setBounds(120, 58, 85, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("s.d.");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame7.add(jLabel28);
        jLabel28.setBounds(207, 58, 30, 23);

        tgl4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        tgl4.setDisplayFormat("dd-MM-yyyy");
        tgl4.setName("tgl4"); // NOI18N
        tgl4.setOpaque(false);
        tgl4.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame7.add(tgl4);
        tgl4.setBounds(240, 58, 85, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Asuhan Gizi : ");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame7.add(jLabel37);
        jLabel37.setBounds(0, 88, 118, 23);

        cmbPilihAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yang Sudah Mendapatkan", "Semuanya Termasuk Yang Belum" }));
        cmbPilihAsuhan.setName("cmbPilihAsuhan"); // NOI18N
        internalFrame7.add(cmbPilihAsuhan);
        cmbPilihAsuhan.setBounds(120, 88, 190, 23);

        WindowLapStatusGZanak.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asuhan Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1347));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 415, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(115, 124, 50, 23);

        Tlila.setForeground(new java.awt.Color(0, 0, 0));
        Tlila.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tlila.setName("Tlila"); // NOI18N
        Tlila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlilaKeyPressed(evt);
            }
        });
        FormInput.add(Tlila);
        Tlila.setBounds(565, 124, 50, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Ahli Gizi :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 1185, 110, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        FormInput.add(Tnip);
        Tnip.setBounds(115, 1185, 170, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(289, 1185, 390, 23);

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
        BtnPetugas.setBounds(680, 1185, 28, 23);

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
        chkSaya.setBounds(715, 1185, 87, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asuhan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        tglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        tglAsuhan.setDisplayFormat("dd-MM-yyyy");
        tglAsuhan.setName("tglAsuhan"); // NOI18N
        tglAsuhan.setOpaque(false);
        tglAsuhan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglAsuhan);
        tglAsuhan.setBounds(115, 38, 90, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 66, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 66, 620, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("ASSESMEN :");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 110, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Berat Badan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 124, 110, 23);

        label105.setForeground(new java.awt.Color(0, 0, 0));
        label105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label105.setText("Kg.    TB : ");
        label105.setName("label105"); // NOI18N
        label105.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label105);
        label105.setBounds(170, 124, 50, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(220, 124, 50, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(645, 38, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Kelamin : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(555, 38, 90, 23);

        label107.setForeground(new java.awt.Color(0, 0, 0));
        label107.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label107.setText("Cm.");
        label107.setName("label107"); // NOI18N
        label107.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label107);
        label107.setBounds(275, 124, 30, 23);

        BtnNilaiIMT.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiIMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiIMT.setMnemonic('I');
        BtnNilaiIMT.setText("Nilai IMT :");
        BtnNilaiIMT.setToolTipText("Alt+I");
        BtnNilaiIMT.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiIMT.setName("BtnNilaiIMT"); // NOI18N
        BtnNilaiIMT.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiIMTActionPerformed(evt);
            }
        });
        BtnNilaiIMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiIMTKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiIMT);
        BtnNilaiIMT.setBounds(310, 124, 93, 23);

        Timt.setEditable(false);
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Timt.setName("Timt"); // NOI18N
        FormInput.add(Timt);
        Timt.setBounds(410, 124, 68, 23);

        label108.setForeground(new java.awt.Color(0, 0, 0));
        label108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label108.setText("Kg/Cm3    LILA : ");
        label108.setName("label108"); // NOI18N
        label108.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label108);
        label108.setBounds(483, 124, 80, 23);

        label109.setForeground(new java.awt.Color(0, 0, 0));
        label109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label109.setText("Cm.   BBI :");
        label109.setName("label109"); // NOI18N
        label109.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label109);
        label109.setBounds(621, 124, 55, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Antropometri :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 109, 110, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tinggi Lutut :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 152, 110, 23);

        Ttl.setForeground(new java.awt.Color(0, 0, 0));
        Ttl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttl.setName("Ttl"); // NOI18N
        Ttl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtlKeyPressed(evt);
            }
        });
        FormInput.add(Ttl);
        Ttl.setBounds(115, 152, 50, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("Cm.    ULNA : ");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(170, 152, 67, 23);

        Tulna.setForeground(new java.awt.Color(0, 0, 0));
        Tulna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tulna.setName("Tulna"); // NOI18N
        Tulna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TulnaKeyPressed(evt);
            }
        });
        FormInput.add(Tulna);
        Tulna.setBounds(238, 152, 50, 23);

        label110.setForeground(new java.awt.Color(0, 0, 0));
        label110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label110.setText("Cm.");
        label110.setName("label110"); // NOI18N
        label110.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label110);
        label110.setBounds(293, 152, 30, 23);

        BtnNilaiTB.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiTB.setMnemonic('I');
        BtnNilaiTB.setText("Nilai TB est :");
        BtnNilaiTB.setToolTipText("Alt+I");
        BtnNilaiTB.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiTB.setName("BtnNilaiTB"); // NOI18N
        BtnNilaiTB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiTBActionPerformed(evt);
            }
        });
        BtnNilaiTB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiTBKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiTB);
        BtnNilaiTB.setBounds(325, 152, 105, 23);

        TtbEst.setForeground(new java.awt.Color(0, 0, 0));
        TtbEst.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtbEst.setName("TtbEst"); // NOI18N
        TtbEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbEstKeyPressed(evt);
            }
        });
        FormInput.add(TtbEst);
        TtbEst.setBounds(435, 152, 70, 23);

        label111.setForeground(new java.awt.Color(0, 0, 0));
        label111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label111.setText("Cm.");
        label111.setName("label111"); // NOI18N
        label111.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label111);
        label111.setBounds(510, 152, 25, 23);

        TbbKoreksi.setForeground(new java.awt.Color(0, 0, 0));
        TbbKoreksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbKoreksi.setName("TbbKoreksi"); // NOI18N
        FormInput.add(TbbKoreksi);
        TbbKoreksi.setBounds(668, 152, 75, 23);

        label112.setForeground(new java.awt.Color(0, 0, 0));
        label112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label112.setText("Kg.");
        label112.setName("label112"); // NOI18N
        label112.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label112);
        label112.setBounds(747, 152, 20, 23);

        cmbSttsGizi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Buruk", "Kurang", "Normal", "Lebih", "Obesitas", "Baik" }));
        cmbSttsGizi.setName("cmbSttsGizi"); // NOI18N
        cmbSttsGizi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSttsGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSttsGiziActionPerformed(evt);
            }
        });
        FormInput.add(cmbSttsGizi);
        cmbSttsGizi.setBounds(623, 180, 75, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Biokimia :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 292, 110, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tbiokimia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tbiokimia.setColumns(20);
        Tbiokimia.setRows(5);
        Tbiokimia.setName("Tbiokimia"); // NOI18N
        Tbiokimia.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tbiokimia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbiokimiaKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tbiokimia);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(115, 292, 620, 60);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Klinis / Fisik :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 357, 110, 23);

        ChkMual.setBackground(new java.awt.Color(255, 255, 250));
        ChkMual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMual.setForeground(new java.awt.Color(0, 0, 0));
        ChkMual.setText("Mual Muntah");
        ChkMual.setBorderPainted(true);
        ChkMual.setBorderPaintedFlat(true);
        ChkMual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMual.setName("ChkMual"); // NOI18N
        ChkMual.setOpaque(false);
        ChkMual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMual);
        ChkMual.setBounds(115, 357, 90, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri Ulu Hati");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(115, 385, 90, 23);

        ChkDiare.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiare.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiare.setText("Diare");
        ChkDiare.setBorderPainted(true);
        ChkDiare.setBorderPaintedFlat(true);
        ChkDiare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiare.setName("ChkDiare"); // NOI18N
        ChkDiare.setOpaque(false);
        ChkDiare.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiare);
        ChkDiare.setBounds(115, 413, 90, 23);

        ChkKesulitan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKesulitan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKesulitan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKesulitan.setText("Kesulitan Menelan");
        ChkKesulitan.setBorderPainted(true);
        ChkKesulitan.setBorderPaintedFlat(true);
        ChkKesulitan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKesulitan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKesulitan.setName("ChkKesulitan"); // NOI18N
        ChkKesulitan.setOpaque(false);
        ChkKesulitan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKesulitan);
        ChkKesulitan.setBounds(115, 442, 114, 23);

        ChkOedema.setBackground(new java.awt.Color(255, 255, 250));
        ChkOedema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkOedema.setForeground(new java.awt.Color(0, 0, 0));
        ChkOedema.setText("Oedema");
        ChkOedema.setBorderPainted(true);
        ChkOedema.setBorderPaintedFlat(true);
        ChkOedema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkOedema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkOedema.setName("ChkOedema"); // NOI18N
        ChkOedema.setOpaque(false);
        ChkOedema.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkOedema);
        ChkOedema.setBounds(250, 357, 72, 23);

        ChkKonstipasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkKonstipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKonstipasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkKonstipasi.setText("Konstipasi");
        ChkKonstipasi.setBorderPainted(true);
        ChkKonstipasi.setBorderPaintedFlat(true);
        ChkKonstipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKonstipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKonstipasi.setName("ChkKonstipasi"); // NOI18N
        ChkKonstipasi.setOpaque(false);
        ChkKonstipasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKonstipasi);
        ChkKonstipasi.setBounds(250, 385, 78, 23);

        ChkAnorek.setBackground(new java.awt.Color(255, 255, 250));
        ChkAnorek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAnorek.setForeground(new java.awt.Color(0, 0, 0));
        ChkAnorek.setText("Anoreksia");
        ChkAnorek.setBorderPainted(true);
        ChkAnorek.setBorderPaintedFlat(true);
        ChkAnorek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAnorek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAnorek.setName("ChkAnorek"); // NOI18N
        ChkAnorek.setOpaque(false);
        ChkAnorek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAnorek);
        ChkAnorek.setBounds(250, 413, 78, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Gigi Geligi");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.setOpaque(false);
        ChkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(250, 442, 125, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Klinis / Fisik Lainnya : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(330, 357, 110, 23);

        TklinisLain.setForeground(new java.awt.Color(0, 0, 0));
        TklinisLain.setName("TklinisLain"); // NOI18N
        FormInput.add(TklinisLain);
        TklinisLain.setBounds(440, 357, 294, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Riwayat Gizi :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 464, 110, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Riwayat Makan SMRS :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(115, 464, 120, 23);

        ChkMakanLebih3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanLebih3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanLebih3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanLebih3.setText("Makan >= 3 x Sehari");
        ChkMakanLebih3.setBorderPainted(true);
        ChkMakanLebih3.setBorderPaintedFlat(true);
        ChkMakanLebih3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanLebih3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanLebih3.setName("ChkMakanLebih3"); // NOI18N
        ChkMakanLebih3.setOpaque(false);
        ChkMakanLebih3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanLebih3);
        ChkMakanLebih3.setBounds(115, 484, 135, 23);

        ChkMakanKurang3.setBackground(new java.awt.Color(255, 255, 250));
        ChkMakanKurang3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMakanKurang3.setForeground(new java.awt.Color(0, 0, 0));
        ChkMakanKurang3.setText("Makan <= 3 x Sehari");
        ChkMakanKurang3.setBorderPainted(true);
        ChkMakanKurang3.setBorderPaintedFlat(true);
        ChkMakanKurang3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMakanKurang3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMakanKurang3.setName("ChkMakanKurang3"); // NOI18N
        ChkMakanKurang3.setOpaque(false);
        ChkMakanKurang3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMakanKurang3);
        ChkMakanKurang3.setBounds(115, 512, 135, 23);

        TriwayatLain.setForeground(new java.awt.Color(0, 0, 0));
        TriwayatLain.setName("TriwayatLain"); // NOI18N
        FormInput.add(TriwayatLain);
        TriwayatLain.setBounds(115, 540, 420, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Riwayat Gizi Lain :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 540, 110, 23);

        ChkAlergi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergi.setText("Alergi Makanan");
        ChkAlergi.setBorderPainted(true);
        ChkAlergi.setBorderPaintedFlat(true);
        ChkAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAlergi.setName("ChkAlergi"); // NOI18N
        ChkAlergi.setOpaque(false);
        ChkAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergi);
        ChkAlergi.setBounds(115, 568, 105, 23);

        Talergi.setForeground(new java.awt.Color(0, 0, 0));
        Talergi.setName("Talergi"); // NOI18N
        FormInput.add(Talergi);
        Talergi.setBounds(220, 568, 515, 23);

        ChkPantangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPantangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPantangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPantangan.setText("Pantangan Makan");
        ChkPantangan.setBorderPainted(true);
        ChkPantangan.setBorderPaintedFlat(true);
        ChkPantangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPantangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPantangan.setName("ChkPantangan"); // NOI18N
        ChkPantangan.setOpaque(false);
        ChkPantangan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPantangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPantanganActionPerformed(evt);
            }
        });
        FormInput.add(ChkPantangan);
        ChkPantangan.setBounds(115, 596, 115, 23);

        Tpantangan.setForeground(new java.awt.Color(0, 0, 0));
        Tpantangan.setName("Tpantangan"); // NOI18N
        FormInput.add(Tpantangan);
        Tpantangan.setBounds(230, 596, 505, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Asupan Makan SMRS :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(260, 464, 120, 23);

        ChkAsupanCukup.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanCukup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanCukup.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanCukup.setText("Asupan Cukup & Tidak Ada Perubahan");
        ChkAsupanCukup.setBorderPainted(true);
        ChkAsupanCukup.setBorderPaintedFlat(true);
        ChkAsupanCukup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanCukup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanCukup.setName("ChkAsupanCukup"); // NOI18N
        ChkAsupanCukup.setOpaque(false);
        ChkAsupanCukup.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanCukup);
        ChkAsupanCukup.setBounds(260, 484, 215, 23);

        ChkAsupanMenurun.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanMenurun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanMenurun.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanMenurun.setText("Asupan Menurun Tahap Ringan");
        ChkAsupanMenurun.setBorderPainted(true);
        ChkAsupanMenurun.setBorderPaintedFlat(true);
        ChkAsupanMenurun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanMenurun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanMenurun.setName("ChkAsupanMenurun"); // NOI18N
        ChkAsupanMenurun.setOpaque(false);
        ChkAsupanMenurun.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanMenurun);
        ChkAsupanMenurun.setBounds(260, 512, 215, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Riwayat Penyakit Personal :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 624, 150, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        TriwPenyakit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyakit.setColumns(20);
        TriwPenyakit.setRows(5);
        TriwPenyakit.setName("TriwPenyakit"); // NOI18N
        TriwPenyakit.setPreferredSize(new java.awt.Dimension(162, 1000));
        TriwPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyakitKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TriwPenyakit);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(155, 624, 580, 60);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("DIAGNOSA GIZI :");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 688, 110, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ P ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiagnosa.setToolTipText("");
        tbDiagnosa.setComponentPopupMenu(jPopupMenu2);
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        Scroll5.setViewportView(tbDiagnosa);

        FormInput.add(Scroll5);
        Scroll5.setBounds(115, 688, 620, 150);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Key Word :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 842, 110, 23);

        TCariDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        TCariDiagnosa.setName("TCariDiagnosa"); // NOI18N
        TCariDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(TCariDiagnosa);
        TCariDiagnosa.setBounds(115, 842, 289, 23);

        BtnCariDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariDiagnosa.setMnemonic('1');
        BtnCariDiagnosa.setToolTipText("Alt+1");
        BtnCariDiagnosa.setName("BtnCariDiagnosa"); // NOI18N
        BtnCariDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariDiagnosaActionPerformed(evt);
            }
        });
        BtnCariDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariDiagnosa);
        BtnCariDiagnosa.setBounds(410, 842, 28, 23);

        BtnAllDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllDiagnosa.setMnemonic('2');
        BtnAllDiagnosa.setText("Semua Diagnosa");
        BtnAllDiagnosa.setToolTipText("Alt+2");
        BtnAllDiagnosa.setName("BtnAllDiagnosa"); // NOI18N
        BtnAllDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllDiagnosaActionPerformed(evt);
            }
        });
        BtnAllDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllDiagnosa);
        BtnAllDiagnosa.setBounds(445, 842, 140, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Umur : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(415, 38, 50, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(465, 38, 50, 23);

        Tsttsumur.setForeground(new java.awt.Color(0, 0, 0));
        Tsttsumur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tsttsumur.setText("tahun.");
        Tsttsumur.setName("Tsttsumur"); // NOI18N
        FormInput.add(Tsttsumur);
        Tsttsumur.setBounds(520, 38, 50, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Berkaitan Dengan [ E ] :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 870, 150, 23);

        scrollPane15.setName("scrollPane15"); // NOI18N

        Tberkaitan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tberkaitan.setColumns(20);
        Tberkaitan.setRows(5);
        Tberkaitan.setName("Tberkaitan"); // NOI18N
        Tberkaitan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tberkaitan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberkaitanKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tberkaitan);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(155, 870, 580, 60);

        scrollPane16.setName("scrollPane16"); // NOI18N

        Tditandai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tditandai.setColumns(20);
        Tditandai.setRows(5);
        Tditandai.setName("Tditandai"); // NOI18N
        Tditandai.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tditandai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TditandaiKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tditandai);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(155, 936, 580, 60);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Ditandai Dengan [ S ] :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 936, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("INTERVENSI :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 997, 110, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Bentuk Makanan :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 1017, 110, 23);

        cmbBentuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbBentuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biasa", "Lunak", "Saring", "Cair", "Kombinasi" }));
        cmbBentuk.setName("cmbBentuk"); // NOI18N
        cmbBentuk.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBentuk);
        cmbBentuk.setBounds(115, 1017, 80, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Jenis Diet :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(377, 1017, 70, 23);

        TjnsDiet.setForeground(new java.awt.Color(0, 0, 0));
        TjnsDiet.setName("TjnsDiet"); // NOI18N
        FormInput.add(TjnsDiet);
        TjnsDiet.setBounds(453, 1017, 282, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Kalori (KKal) : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(595, 1101, 80, 23);

        Tkalori.setForeground(new java.awt.Color(0, 0, 0));
        Tkalori.setName("Tkalori"); // NOI18N
        Tkalori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkaloriKeyPressed(evt);
            }
        });
        FormInput.add(Tkalori);
        Tkalori.setBounds(678, 1101, 60, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Protein :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1129, 110, 23);

        Tprotein.setForeground(new java.awt.Color(0, 0, 0));
        Tprotein.setName("Tprotein"); // NOI18N
        FormInput.add(Tprotein);
        Tprotein.setBounds(190, 1129, 60, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lemak :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(250, 1129, 90, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Karbohidrat :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(510, 1129, 85, 23);

        Tlemak.setForeground(new java.awt.Color(0, 0, 0));
        Tlemak.setName("Tlemak"); // NOI18N
        FormInput.add(Tlemak);
        Tlemak.setBounds(420, 1129, 60, 23);

        Tkarbo.setForeground(new java.awt.Color(0, 0, 0));
        Tkarbo.setName("Tkarbo"); // NOI18N
        FormInput.add(Tkarbo);
        Tkarbo.setBounds(678, 1129, 60, 23);

        BtnPasteHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteHasil.setMnemonic('L');
        BtnPasteHasil.setText("Paste");
        BtnPasteHasil.setToolTipText("Alt+L");
        BtnPasteHasil.setName("BtnPasteHasil"); // NOI18N
        BtnPasteHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(740, 292, 100, 23);

        Tbbi.setForeground(new java.awt.Color(0, 0, 0));
        Tbbi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbbi.setName("Tbbi"); // NOI18N
        Tbbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbiKeyPressed(evt);
            }
        });
        FormInput.add(Tbbi);
        Tbbi.setBounds(676, 124, 45, 23);

        label113.setForeground(new java.awt.Color(0, 0, 0));
        label113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label113.setText("Kg.");
        label113.setName("label113"); // NOI18N
        label113.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label113);
        label113.setBounds(725, 124, 20, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Hasil Recall Intake : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(540, 540, 120, 23);

        cmbHasilRecal.setForeground(new java.awt.Color(0, 0, 0));
        cmbHasilRecal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "0 %", "5 %", "25 %", "50 %", "75 %", "95 %", "100 %" }));
        cmbHasilRecal.setName("cmbHasilRecal"); // NOI18N
        cmbHasilRecal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbHasilRecal);
        cmbHasilRecal.setBounds(666, 540, 68, 23);

        cmbProtein.setForeground(new java.awt.Color(0, 0, 0));
        cmbProtein.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "10 %", "15 %", "Lainnya" }));
        cmbProtein.setName("cmbProtein"); // NOI18N
        cmbProtein.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbProtein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProteinActionPerformed(evt);
            }
        });
        FormInput.add(cmbProtein);
        cmbProtein.setBounds(115, 1129, 70, 23);

        cmbLemak.setForeground(new java.awt.Color(0, 0, 0));
        cmbLemak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "20 %", "25 %", "Lainnya" }));
        cmbLemak.setName("cmbLemak"); // NOI18N
        cmbLemak.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbLemak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLemakActionPerformed(evt);
            }
        });
        FormInput.add(cmbLemak);
        cmbLemak.setBounds(345, 1129, 70, 23);

        cmbKarbo.setForeground(new java.awt.Color(0, 0, 0));
        cmbKarbo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "60 %", "65 %", "Lainnya" }));
        cmbKarbo.setName("cmbKarbo"); // NOI18N
        cmbKarbo.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKarbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKarboActionPerformed(evt);
            }
        });
        FormInput.add(cmbKarbo);
        cmbKarbo.setBounds(602, 1129, 70, 23);

        BtnNilaiBB.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiBB.setMnemonic('I');
        BtnNilaiBB.setText("Nilai BB Koreksi :");
        BtnNilaiBB.setToolTipText("Alt+I");
        BtnNilaiBB.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiBB.setName("BtnNilaiBB"); // NOI18N
        BtnNilaiBB.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiBBActionPerformed(evt);
            }
        });
        BtnNilaiBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiBBKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiBB);
        BtnNilaiBB.setBounds(535, 152, 130, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Rute Makanan :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(195, 1017, 90, 23);

        cmbRute.setForeground(new java.awt.Color(0, 0, 0));
        cmbRute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Oral", "NGT", "Kombinasi" }));
        cmbRute.setName("cmbRute"); // NOI18N
        cmbRute.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbRute);
        cmbRute.setBounds(292, 1017, 85, 23);

        ChkAsupanRendah.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanRendah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanRendah.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanRendah.setText("Asupan Rendah, Tetapi Ada Peningkatan");
        ChkAsupanRendah.setBorderPainted(true);
        ChkAsupanRendah.setBorderPaintedFlat(true);
        ChkAsupanRendah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanRendah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanRendah.setName("ChkAsupanRendah"); // NOI18N
        ChkAsupanRendah.setOpaque(false);
        ChkAsupanRendah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanRendah);
        ChkAsupanRendah.setBounds(485, 484, 230, 23);

        ChkAsupanTdkCukup.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanTdkCukup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanTdkCukup.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanTdkCukup.setText("Asupan Tidak Cukup & Menurun Tahap Berat");
        ChkAsupanTdkCukup.setBorderPainted(true);
        ChkAsupanTdkCukup.setBorderPaintedFlat(true);
        ChkAsupanTdkCukup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanTdkCukup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanTdkCukup.setName("ChkAsupanTdkCukup"); // NOI18N
        ChkAsupanTdkCukup.setOpaque(false);
        ChkAsupanTdkCukup.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanTdkCukup);
        ChkAsupanTdkCukup.setBounds(485, 512, 250, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Metode Hitungan :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 1045, 110, 23);

        cmbMetode.setForeground(new java.awt.Color(0, 0, 0));
        cmbMetode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Harris Benedict", "Perkeni" }));
        cmbMetode.setName("cmbMetode"); // NOI18N
        cmbMetode.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMetode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMetodeActionPerformed(evt);
            }
        });
        FormInput.add(cmbMetode);
        cmbMetode.setBounds(115, 1045, 110, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Faktor Aktivitas :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(225, 1045, 100, 23);

        cmbFaktorA.setForeground(new java.awt.Color(0, 0, 0));
        cmbFaktorA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Istirahat", "Ringan" }));
        cmbFaktorA.setName("cmbFaktorA"); // NOI18N
        cmbFaktorA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFaktorA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFaktorAActionPerformed(evt);
            }
        });
        FormInput.add(cmbFaktorA);
        cmbFaktorA.setBounds(330, 1045, 80, 23);

        Tfa.setEditable(false);
        Tfa.setForeground(new java.awt.Color(0, 0, 0));
        Tfa.setName("Tfa"); // NOI18N
        FormInput.add(Tfa);
        Tfa.setBounds(416, 1045, 50, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Faktor Stress :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(225, 1073, 100, 23);

        cmbFaktorS.setForeground(new java.awt.Color(0, 0, 0));
        cmbFaktorS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Stress Ringan", "Stress Berat", "Kanker", "Pasca Operasi", "Ulkus, GERD, Dispepsia" }));
        cmbFaktorS.setName("cmbFaktorS"); // NOI18N
        cmbFaktorS.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFaktorS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFaktorSActionPerformed(evt);
            }
        });
        FormInput.add(cmbFaktorS);
        cmbFaktorS.setBounds(330, 1073, 145, 23);

        Tfs.setForeground(new java.awt.Color(0, 0, 0));
        Tfs.setName("Tfs"); // NOI18N
        Tfs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfsKeyPressed(evt);
            }
        });
        FormInput.add(Tfs);
        Tfs.setBounds(481, 1073, 50, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Faktor Koreksi Tubuh : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(545, 1045, 130, 23);

        Tfk.setForeground(new java.awt.Color(0, 0, 0));
        Tfk.setName("Tfk"); // NOI18N
        Tfk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfkKeyPressed(evt);
            }
        });
        FormInput.add(Tfk);
        Tfk.setBounds(678, 1045, 60, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("BMR : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(545, 1073, 130, 23);

        Tbmr.setEditable(false);
        Tbmr.setForeground(new java.awt.Color(0, 0, 0));
        Tbmr.setName("Tbmr"); // NOI18N
        FormInput.add(Tbmr);
        Tbmr.setBounds(678, 1073, 60, 23);

        BtnNilaiKalori.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiKalori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiKalori.setMnemonic('I');
        BtnNilaiKalori.setText("Cek Nilai Kalori (KKal)");
        BtnNilaiKalori.setToolTipText("Alt+I");
        BtnNilaiKalori.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiKalori.setName("BtnNilaiKalori"); // NOI18N
        BtnNilaiKalori.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiKalori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiKaloriActionPerformed(evt);
            }
        });
        BtnNilaiKalori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNilaiKaloriKeyPressed(evt);
            }
        });
        FormInput.add(BtnNilaiKalori);
        BtnNilaiKalori.setBounds(330, 1101, 160, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Rencana Monev :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1157, 110, 23);

        ChkAsupanMakan.setBackground(new java.awt.Color(255, 255, 250));
        ChkAsupanMakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAsupanMakan.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsupanMakan.setText("Asupan Makan");
        ChkAsupanMakan.setBorderPainted(true);
        ChkAsupanMakan.setBorderPaintedFlat(true);
        ChkAsupanMakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsupanMakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAsupanMakan.setName("ChkAsupanMakan"); // NOI18N
        ChkAsupanMakan.setOpaque(false);
        ChkAsupanMakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAsupanMakan);
        ChkAsupanMakan.setBounds(115, 1157, 100, 23);

        ChkAntropometri.setBackground(new java.awt.Color(255, 255, 250));
        ChkAntropometri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAntropometri.setForeground(new java.awt.Color(0, 0, 0));
        ChkAntropometri.setText("Antropometri");
        ChkAntropometri.setBorderPainted(true);
        ChkAntropometri.setBorderPaintedFlat(true);
        ChkAntropometri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAntropometri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAntropometri.setName("ChkAntropometri"); // NOI18N
        ChkAntropometri.setOpaque(false);
        ChkAntropometri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAntropometri);
        ChkAntropometri.setBounds(220, 1157, 93, 23);

        ChkBiokimia.setBackground(new java.awt.Color(255, 255, 250));
        ChkBiokimia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBiokimia.setForeground(new java.awt.Color(0, 0, 0));
        ChkBiokimia.setText("Biokimia");
        ChkBiokimia.setBorderPainted(true);
        ChkBiokimia.setBorderPaintedFlat(true);
        ChkBiokimia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBiokimia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBiokimia.setName("ChkBiokimia"); // NOI18N
        ChkBiokimia.setOpaque(false);
        ChkBiokimia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBiokimia);
        ChkBiokimia.setBounds(320, 1157, 70, 23);

        ChkKlinis.setBackground(new java.awt.Color(255, 255, 250));
        ChkKlinis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKlinis.setForeground(new java.awt.Color(0, 0, 0));
        ChkKlinis.setText("Klinis");
        ChkKlinis.setBorderPainted(true);
        ChkKlinis.setBorderPaintedFlat(true);
        ChkKlinis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKlinis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKlinis.setName("ChkKlinis"); // NOI18N
        ChkKlinis.setOpaque(false);
        ChkKlinis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKlinis);
        ChkKlinis.setBounds(400, 1157, 60, 23);

        ChkLain_lain.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain_lain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain_lain.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain_lain.setText("Lain-lain");
        ChkLain_lain.setBorderPainted(true);
        ChkLain_lain.setBorderPaintedFlat(true);
        ChkLain_lain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLain_lain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLain_lain.setName("ChkLain_lain"); // NOI18N
        ChkLain_lain.setOpaque(false);
        ChkLain_lain.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLain_lain);
        ChkLain_lain.setBounds(470, 1157, 70, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Klasifikasi IMT :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 180, 110, 23);

        cmbKlasifikasiIMT.setForeground(new java.awt.Color(0, 0, 0));
        cmbKlasifikasiIMT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Berdasarkan Kemenkes RI", "Berdasarkan CDC" }));
        cmbKlasifikasiIMT.setName("cmbKlasifikasiIMT"); // NOI18N
        cmbKlasifikasiIMT.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbKlasifikasiIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKlasifikasiIMTActionPerformed(evt);
            }
        });
        FormInput.add(cmbKlasifikasiIMT);
        cmbKlasifikasiIMT.setBounds(115, 180, 160, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Persentase CDC :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(275, 180, 105, 23);

        ChkBbu.setBackground(new java.awt.Color(255, 255, 250));
        ChkBbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBbu.setForeground(new java.awt.Color(0, 0, 0));
        ChkBbu.setText("Indeks (BB/U)");
        ChkBbu.setBorderPainted(true);
        ChkBbu.setBorderPaintedFlat(true);
        ChkBbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBbu.setName("ChkBbu"); // NOI18N
        ChkBbu.setOpaque(false);
        ChkBbu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBbuActionPerformed(evt);
            }
        });
        FormInput.add(ChkBbu);
        ChkBbu.setBounds(115, 208, 160, 23);

        TketBbu.setForeground(new java.awt.Color(0, 0, 0));
        TketBbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketBbu.setName("TketBbu"); // NOI18N
        TketBbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketBbuKeyPressed(evt);
            }
        });
        FormInput.add(TketBbu);
        TketBbu.setBounds(280, 208, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Kategori Status Gizi :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(360, 208, 115, 23);

        cmbBbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbBbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "BB Sangat Kurang", "BB Kurang", "BB Normal", "Resiko BB Lebih" }));
        cmbBbu.setName("cmbBbu"); // NOI18N
        cmbBbu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBbu);
        cmbBbu.setBounds(480, 208, 120, 23);

        ChkPbu.setBackground(new java.awt.Color(255, 255, 250));
        ChkPbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPbu.setForeground(new java.awt.Color(0, 0, 0));
        ChkPbu.setText("Indeks (PB/U atau TB/U)");
        ChkPbu.setBorderPainted(true);
        ChkPbu.setBorderPaintedFlat(true);
        ChkPbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPbu.setName("ChkPbu"); // NOI18N
        ChkPbu.setOpaque(false);
        ChkPbu.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPbuActionPerformed(evt);
            }
        });
        FormInput.add(ChkPbu);
        ChkPbu.setBounds(115, 236, 160, 23);

        TketPbu.setForeground(new java.awt.Color(0, 0, 0));
        TketPbu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketPbu.setName("TketPbu"); // NOI18N
        TketPbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketPbuKeyPressed(evt);
            }
        });
        FormInput.add(TketPbu);
        TketPbu.setBounds(280, 236, 70, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Kategori Status Gizi :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(360, 236, 115, 23);

        cmbPbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sangat Pendek", "Pendek", "Normal", "Tinggi" }));
        cmbPbu.setName("cmbPbu"); // NOI18N
        cmbPbu.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPbu);
        cmbPbu.setBounds(480, 236, 110, 23);

        ChkBbpb.setBackground(new java.awt.Color(255, 255, 250));
        ChkBbpb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBbpb.setForeground(new java.awt.Color(0, 0, 0));
        ChkBbpb.setText("Indeks (BB/PB atau BB/TB)");
        ChkBbpb.setBorderPainted(true);
        ChkBbpb.setBorderPaintedFlat(true);
        ChkBbpb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBbpb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBbpb.setName("ChkBbpb"); // NOI18N
        ChkBbpb.setOpaque(false);
        ChkBbpb.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBbpb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBbpbActionPerformed(evt);
            }
        });
        FormInput.add(ChkBbpb);
        ChkBbpb.setBounds(115, 264, 160, 23);

        TketBbpb.setForeground(new java.awt.Color(0, 0, 0));
        TketBbpb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TketBbpb.setName("TketBbpb"); // NOI18N
        TketBbpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketBbpbKeyPressed(evt);
            }
        });
        FormInput.add(TketBbpb);
        TketBbpb.setBounds(280, 264, 70, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kategori Status Gizi :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(360, 264, 115, 23);

        cmbBbpb.setForeground(new java.awt.Color(0, 0, 0));
        cmbBbpb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Gizi Buruk", "Gizi Kurang", "Gizi Baik (Normal)", "Beresiko Gizi Lebih", "Gizi Lebih", "Obesitas" }));
        cmbBbpb.setName("cmbBbpb"); // NOI18N
        cmbBbpb.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBbpb);
        cmbBbpb.setBounds(480, 264, 130, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jenis Asuhan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(205, 38, 90, 23);

        cmbAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dewasa", "Anak" }));
        cmbAsuhan.setName("cmbAsuhan"); // NOI18N
        cmbAsuhan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAsuhan);
        cmbAsuhan.setBounds(302, 38, 75, 23);

        Tpersentase.setForeground(new java.awt.Color(0, 0, 0));
        Tpersentase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tpersentase.setName("Tpersentase"); // NOI18N
        Tpersentase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpersentaseKeyPressed(evt);
            }
        });
        FormInput.add(Tpersentase);
        Tpersentase.setBounds(385, 180, 70, 23);

        BtnCekSttsGizi.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekSttsGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnCekSttsGizi.setMnemonic('I');
        BtnCekSttsGizi.setText("Cek Status Gizi :");
        BtnCekSttsGizi.setToolTipText("Alt+I");
        BtnCekSttsGizi.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnCekSttsGizi.setName("BtnCekSttsGizi"); // NOI18N
        BtnCekSttsGizi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCekSttsGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekSttsGiziActionPerformed(evt);
            }
        });
        BtnCekSttsGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCekSttsGiziKeyPressed(evt);
            }
        });
        FormInput.add(BtnCekSttsGizi);
        BtnCekSttsGizi.setBounds(490, 180, 128, 23);

        label114.setForeground(new java.awt.Color(0, 0, 0));
        label114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label114.setText("%");
        label114.setName("label114"); // NOI18N
        label114.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label114);
        label114.setBounds(460, 180, 20, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("GIZI BURUK :");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 1213, 110, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Perhitungan Keb. Zat Gizi :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 1229, 150, 23);

        ThitungZat.setForeground(new java.awt.Color(0, 0, 0));
        ThitungZat.setName("ThitungZat"); // NOI18N
        ThitungZat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThitungZatKeyPressed(evt);
            }
        });
        FormInput.add(ThitungZat);
        ThitungZat.setBounds(155, 1229, 430, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Diagnosa dr. Sp. Gz/Ahli Gizi :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 1257, 150, 23);

        TdiagnosaDokter.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaDokter.setName("TdiagnosaDokter"); // NOI18N
        TdiagnosaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaDokterKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaDokter);
        TdiagnosaDokter.setBounds(155, 1257, 580, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Pemberian Nutrisi :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 1285, 150, 23);

        Tpemberian.setForeground(new java.awt.Color(0, 0, 0));
        Tpemberian.setName("Tpemberian"); // NOI18N
        Tpemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemberianKeyPressed(evt);
            }
        });
        FormInput.add(Tpemberian);
        Tpemberian.setBounds(155, 1285, 580, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Faskes/Puskesmas Asal :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 1313, 150, 23);

        Tfaskes.setEditable(false);
        Tfaskes.setForeground(new java.awt.Color(0, 0, 0));
        Tfaskes.setName("Tfaskes"); // NOI18N
        FormInput.add(Tfaskes);
        Tfaskes.setBounds(155, 1313, 580, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("BB - Akhir :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(585, 1229, 65, 23);

        TbbAkhir.setForeground(new java.awt.Color(0, 0, 0));
        TbbAkhir.setName("TbbAkhir"); // NOI18N
        TbbAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbAkhirKeyPressed(evt);
            }
        });
        FormInput.add(TbbAkhir);
        TbbAkhir.setBounds(655, 1229, 50, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("Kg.");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(713, 1229, 25, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnEdit);

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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

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

        internalFrame2.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbCPPT);

        FormMenu.add(Scroll4);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 450));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setToolTipText("Silahkan klik kanan utk. copy data CPPT hasil pemeriksaan");
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setToolTipText("Silahkan klik kanan utk. copy data CPPT instruksi nakes");
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Input Asuhan", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbAsuhan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsuhan.setComponentPopupMenu(jPopupMenu1);
        tbAsuhan.setName("tbAsuhan"); // NOI18N
        tbAsuhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsuhanMouseClicked(evt);
            }
        });
        tbAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsuhanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsuhan);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass9.add(BtnAll);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asuhan", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        tbStatusGZ.setToolTipText("");
        tbStatusGZ.setComponentPopupMenu(jPopupMenu4);
        tbStatusGZ.setName("tbStatusGZ"); // NOI18N
        Scroll1.setViewportView(tbStatusGZ);

        internalFrame4.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Status Gizi :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel20);

        DTPCari5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari5.setDisplayFormat("dd-MM-yyyy");
        DTPCari5.setName("DTPCari5"); // NOI18N
        DTPCari5.setOpaque(false);
        DTPCari5.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari5);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari6.setDisplayFormat("dd-MM-yyyy");
        DTPCari6.setName("DTPCari6"); // NOI18N
        DTPCari6.setOpaque(false);
        DTPCari6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari6);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('T');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+T");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari1);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnAll2);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar2);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel9);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(LCount2);

        internalFrame4.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Rekap Status Gizi Pasien Dewasa", internalFrame4);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbStatusGZ1.setToolTipText("");
        tbStatusGZ1.setComponentPopupMenu(jPopupMenu5);
        tbStatusGZ1.setName("tbStatusGZ1"); // NOI18N
        Scroll2.setViewportView(tbStatusGZ1);

        internalFrame6.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tgl. Status Gizi :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass11.add(jLabel24);

        DTPCari7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari7.setDisplayFormat("dd-MM-yyyy");
        DTPCari7.setName("DTPCari7"); // NOI18N
        DTPCari7.setOpaque(false);
        DTPCari7.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari7);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d.");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass11.add(jLabel25);

        DTPCari8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari8.setDisplayFormat("dd-MM-yyyy");
        DTPCari8.setName("DTPCari8"); // NOI18N
        DTPCari8.setOpaque(false);
        DTPCari8.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPCari8ActionPerformed(evt);
            }
        });
        panelGlass11.add(DTPCari8);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Key Word :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass11.add(jLabel11);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari3);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('T');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setToolTipText("Alt+T");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        BtnCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari3KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari3);

        BtnAll3.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll3.setMnemonic('M');
        BtnAll3.setText("Semua Data");
        BtnAll3.setToolTipText("Alt+M");
        BtnAll3.setName("BtnAll3"); // NOI18N
        BtnAll3.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll3ActionPerformed(evt);
            }
        });
        BtnAll3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll3KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnAll3);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnKeluar3);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Record :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass11.add(jLabel15);

        LCount3.setForeground(new java.awt.Color(0, 0, 0));
        LCount3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount3.setText("0");
        LCount3.setName("LCount3"); // NOI18N
        LCount3.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(LCount3);

        internalFrame6.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Rekap Status Gizi Pasien Anak", internalFrame6);

        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 200));

        tbPersentase.setToolTipText("");
        tbPersentase.setComponentPopupMenu(jPopupMenu5);
        tbPersentase.setName("tbPersentase"); // NOI18N
        Scroll3.setViewportView(tbPersentase);

        internalFrame8.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Bulan :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel29);

        cmbBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulan.setName("cmbBulan"); // NOI18N
        cmbBulan.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanActionPerformed(evt);
            }
        });
        panelGlass12.add(cmbBulan);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel35);

        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.setPreferredSize(new java.awt.Dimension(60, 23));
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        panelGlass12.add(Ttahun);

        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('T');
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setToolTipText("Alt+T");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnCari4);

        BtnCetakPersentase.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersentase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersentase.setMnemonic('K');
        BtnCetakPersentase.setText("Cetak");
        BtnCetakPersentase.setToolTipText("Alt+K");
        BtnCetakPersentase.setName("BtnCetakPersentase"); // NOI18N
        BtnCetakPersentase.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersentase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersentaseActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCetakPersentase);

        BtnKeluar4.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnKeluar4);

        internalFrame8.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Persentase Asuhan Gizi Pasien", internalFrame8);

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll8.setComponentPopupMenu(jPopupMenu1);
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);
        Scroll8.setPreferredSize(new java.awt.Dimension(452, 200));

        tbBuruk.setToolTipText("");
        tbBuruk.setComponentPopupMenu(jPopupMenu6);
        tbBuruk.setName("tbBuruk"); // NOI18N
        Scroll8.setViewportView(tbBuruk);

        internalFrame9.add(Scroll8, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Tanggal :");
        jLabel109.setName("jLabel109"); // NOI18N
        jLabel109.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass13.add(jLabel109);

        DTPCari9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari9.setDisplayFormat("dd-MM-yyyy");
        DTPCari9.setName("DTPCari9"); // NOI18N
        DTPCari9.setOpaque(false);
        DTPCari9.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass13.add(DTPCari9);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("s.d");
        jLabel110.setName("jLabel110"); // NOI18N
        jLabel110.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass13.add(jLabel110);

        DTPCari10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari10.setDisplayFormat("dd-MM-yyyy");
        DTPCari10.setName("DTPCari10"); // NOI18N
        DTPCari10.setOpaque(false);
        DTPCari10.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass13.add(DTPCari10);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel16);

        TCari4.setForeground(new java.awt.Color(0, 0, 0));
        TCari4.setName("TCari4"); // NOI18N
        TCari4.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari4KeyPressed(evt);
            }
        });
        panelGlass13.add(TCari4);

        BtnCari5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setMnemonic('T');
        BtnCari5.setText("Tampilkan Data");
        BtnCari5.setToolTipText("Alt+T");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnCari5);

        BtnAll4.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll4.setMnemonic('M');
        BtnAll4.setText("Semua");
        BtnAll4.setToolTipText("Alt+M");
        BtnAll4.setName("BtnAll4"); // NOI18N
        BtnAll4.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll4ActionPerformed(evt);
            }
        });
        BtnAll4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll4KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnAll4);

        BtnCetakPersentase1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersentase1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersentase1.setMnemonic('K');
        BtnCetakPersentase1.setText("Cetak");
        BtnCetakPersentase1.setToolTipText("Alt+K");
        BtnCetakPersentase1.setName("BtnCetakPersentase1"); // NOI18N
        BtnCetakPersentase1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersentase1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersentase1ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCetakPersentase1);

        BtnKeluar5.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar5.setMnemonic('K');
        BtnKeluar5.setText("Keluar");
        BtnKeluar5.setToolTipText("Alt+K");
        BtnKeluar5.setName("BtnKeluar5"); // NOI18N
        BtnKeluar5.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar5ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnKeluar5);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Record :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel36);

        LCount4.setForeground(new java.awt.Color(0, 0, 0));
        LCount4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount4.setText("0");
        LCount4.setName("LCount4"); // NOI18N
        LCount4.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass13.add(LCount4);

        internalFrame9.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Gizi Buruk Pasien Anak", internalFrame9);

        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass16.setLayout(new java.awt.BorderLayout());

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);
        Scroll9.setPreferredSize(new java.awt.Dimension(452, 200));

        tbBalita.setToolTipText("");
        tbBalita.setName("tbBalita"); // NOI18N
        Scroll9.setViewportView(tbBalita);

        panelGlass16.add(Scroll9, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(400, 44));
        panelGlass17.setLayout(new java.awt.GridLayout(3, 0));

        Scroll10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Rekap PerKlasifikasi Status Gizi Indeks (BB/U) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);
        Scroll10.setPreferredSize(new java.awt.Dimension(452, 250));

        tbRekapBBU.setToolTipText("");
        tbRekapBBU.setName("tbRekapBBU"); // NOI18N
        Scroll10.setViewportView(tbRekapBBU);

        panelGlass17.add(Scroll10);

        Scroll11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Rekap PerKlasifikasi Status Gizi Indeks (PB/U atau TB/U) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);
        Scroll11.setPreferredSize(new java.awt.Dimension(452, 250));

        tbRekapPBU.setToolTipText("");
        tbRekapPBU.setName("tbRekapPBU"); // NOI18N
        Scroll11.setViewportView(tbRekapPBU);

        panelGlass17.add(Scroll11);

        Scroll12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Rekap PerKlasifikasi Status Gizi Indeks (BB/PB atau BB/TB) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);
        Scroll12.setPreferredSize(new java.awt.Dimension(452, 250));

        tbRekapBBPB.setToolTipText("");
        tbRekapBBPB.setName("tbRekapBBPB"); // NOI18N
        Scroll12.setViewportView(tbRekapBBPB);

        panelGlass17.add(Scroll12);

        panelGlass16.add(panelGlass17, java.awt.BorderLayout.EAST);

        internalFrame10.add(panelGlass16, java.awt.BorderLayout.CENTER);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Tanggal :");
        jLabel111.setName("jLabel111"); // NOI18N
        jLabel111.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass15.add(jLabel111);

        DTPCari11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari11.setDisplayFormat("dd-MM-yyyy");
        DTPCari11.setName("DTPCari11"); // NOI18N
        DTPCari11.setOpaque(false);
        DTPCari11.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPCari11ActionPerformed(evt);
            }
        });
        panelGlass15.add(DTPCari11);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("s.d");
        jLabel112.setName("jLabel112"); // NOI18N
        jLabel112.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass15.add(jLabel112);

        DTPCari12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-12-2024" }));
        DTPCari12.setDisplayFormat("dd-MM-yyyy");
        DTPCari12.setName("DTPCari12"); // NOI18N
        DTPCari12.setOpaque(false);
        DTPCari12.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPCari12ActionPerformed(evt);
            }
        });
        panelGlass15.add(DTPCari12);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Key Word :");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass15.add(jLabel38);

        TCari5.setForeground(new java.awt.Color(0, 0, 0));
        TCari5.setName("TCari5"); // NOI18N
        TCari5.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari5KeyPressed(evt);
            }
        });
        panelGlass15.add(TCari5);

        BtnCari6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari6.setMnemonic('T');
        BtnCari6.setText("Tampilkan Data");
        BtnCari6.setToolTipText("Alt+T");
        BtnCari6.setName("BtnCari6"); // NOI18N
        BtnCari6.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari6ActionPerformed(evt);
            }
        });
        BtnCari6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari6KeyPressed(evt);
            }
        });
        panelGlass15.add(BtnCari6);

        BtnAll5.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll5.setMnemonic('M');
        BtnAll5.setText("Semua");
        BtnAll5.setToolTipText("Alt+M");
        BtnAll5.setName("BtnAll5"); // NOI18N
        BtnAll5.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll5ActionPerformed(evt);
            }
        });
        BtnAll5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll5KeyPressed(evt);
            }
        });
        panelGlass15.add(BtnAll5);

        BtnCetakPersentase2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersentase2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersentase2.setMnemonic('K');
        BtnCetakPersentase2.setText("Cetak");
        BtnCetakPersentase2.setToolTipText("Alt+K");
        BtnCetakPersentase2.setName("BtnCetakPersentase2"); // NOI18N
        BtnCetakPersentase2.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersentase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersentase2ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnCetakPersentase2);

        BtnKeluar6.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar6.setMnemonic('K');
        BtnKeluar6.setText("Keluar");
        BtnKeluar6.setToolTipText("Alt+K");
        BtnKeluar6.setName("BtnKeluar6"); // NOI18N
        BtnKeluar6.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar6ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnKeluar6);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Record :");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass15.add(jLabel39);

        LCount5.setForeground(new java.awt.Color(0, 0, 0));
        LCount5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount5.setText("0");
        LCount5.setName("LCount5"); // NOI18N
        LCount5.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass15.add(LCount5);

        internalFrame10.add(panelGlass15, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Klasifikasi Status Gizi Pasien Balita", internalFrame10);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbAsuhan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis asuhan gizinya..!!");
            cmbAsuhan.requestFocus();
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2 && Tpersentase.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka persentase CDC harus diisi dulu..!!");
            Tpersentase.requestFocus();
        } else {
            hitungIMT();
            cekData();
            if (Sequel.menyimpantf("asuhan_gizi_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 73, new String[]{
                        TNoRw.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(), Tlila.getText(), Ttl.getText(), Tulna.getText(),
                        TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(), Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia,
                        gangguan, TklinisLain.getText(), makanlebih, makankurang, TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun, asupanRendah,
                        asupanTidak, cmbHasilRecal.getSelectedItem().toString(), TriwPenyakit.getText(), Tberkaitan.getText(), Tditandai.getText(), cmbBentuk.getSelectedItem().toString(), TjnsDiet.getText(),
                        cmbRute.getSelectedItem().toString(), cmbMetode.getSelectedItem().toString(), Tkalori.getText(), Tbmr.getText(), Tfa.getText(), cmbFaktorA.getSelectedItem().toString(), Tfk.getText(),
                        Tfs.getText(), cmbFaktorS.getSelectedItem().toString(), cmbProtein.getSelectedItem().toString(), Tprotein.getText(), cmbLemak.getSelectedItem().toString(), Tlemak.getText(),
                        cmbKarbo.getSelectedItem().toString(), Tkarbo.getText(), Tnip.getText(), cmbKlasifikasiIMT.getSelectedItem().toString(), Tpersentase.getText(), asupanmakan,
                        antro, biokimia, klinis, lainlain, cmbAsuhan.getSelectedItem().toString(), bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(), TketBbpb.getText(), cmbBbu.getSelectedItem().toString(),
                        cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString()
                    }) == true) {

                //simpan diagnosa gizi
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan2("detail_diagnosa_asuhan_gizi", "?,?,?", 3, new String[]{
                            TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), TrgRawat.getText()});
                    }
                }
                
                simpanSttsGiziDewasa();
                simpanGiziBuruk();
                TCari.setText(TNoRw.getText());
                TabRawat.setSelectedIndex(1);
                emptTeks();
                tampil();

                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
                }
                TCariDiagnosa.setText("");
                tampilDiagnosa();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        TCariDiagnosa.setText("");
        tampil();

        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
        }
        tampilDiagnosa();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsuhan.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (Tnip.getText().equals(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 62).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh ahli gizi yang sudah menyimpan asuhan gizi pasien ini..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbAsuhan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis asuhan gizinya..!!");
            cmbAsuhan.requestFocus();
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2 && Tpersentase.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka persentase CDC harus diisi dulu..!!");
            Tpersentase.requestFocus();
        } else {
            if (tbAsuhan.getSelectedRow() > -1) {
                hitungIMT();
                user = "";
                if (akses.getadmin() == true) {
                    user = "-";
                } else {
                    user = akses.getkode();
                }

                if (akses.getadmin() == true) {
                    if (cmbAsuhan.getSelectedIndex() == 1) {
                        gantiSttsGiziDewasa(nmgedung, kodekamar, Valid.SetTgl(tglAsuhan.getSelectedItem() + ""),
                                tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString(), cmbAsuhan.getSelectedItem().toString(),
                                cmbSttsGizi.getSelectedItem().toString());
                    } else if (cmbAsuhan.getSelectedIndex() == 2) {
                        gantiGiziBuruk(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString(), cmbAsuhan.getSelectedItem().toString(),
                                cmbBbu.getSelectedItem().toString(), cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString(),
                                Tbb.getText().toString(), TbbAkhir.getText().toString(), Ttb.getText().toString(), TketBbu.getText().toString(),
                                TketBbpb.getText().toString(), TketPbu.getText().toString(), ThitungZat.getText().toString(),
                                TdiagnosaDokter.getText().toString(), Tpemberian.getText().toString(), Tfaskes.getText().toString(),
                                Valid.SetTgl(tglAsuhan.getSelectedItem() + ""));
                    }
                    gantiDisimpan();
                    ganti();
                } else {
                    if (Tnip.getText().equals(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 62).toString())) {
                        if (cmbAsuhan.getSelectedIndex() == 1) {
                            gantiSttsGiziDewasa(nmgedung, kodekamar, Valid.SetTgl(tglAsuhan.getSelectedItem() + ""),
                                    tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString(), cmbAsuhan.getSelectedItem().toString(),
                                    cmbSttsGizi.getSelectedItem().toString());
                        } else if (cmbAsuhan.getSelectedIndex() == 2) {
                            gantiGiziBuruk(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString(), cmbAsuhan.getSelectedItem().toString(),
                                    cmbBbu.getSelectedItem().toString(), cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString(),
                                    Tbb.getText().toString(), TbbAkhir.getText().toString(), Ttb.getText().toString(), TketBbu.getText().toString(),
                                    TketBbpb.getText().toString(), TketPbu.getText().toString(), ThitungZat.getText().toString(),
                                    TdiagnosaDokter.getText().toString(), Tpemberian.getText().toString(), Tfaskes.getText().toString(),
                                    Valid.SetTgl(tglAsuhan.getSelectedItem() + ""));
                        }
                        gantiDisimpan();
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh ahli gizi yang sudah menyimpan asuhan gizi pasien ini..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowRiwayat.dispose();
        WindowLapStatusGZ.dispose();
        WindowLapStatusGZanak.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsuhan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());            
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("tglasuhan", "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsuhan.getSelectedItem() + "")));
            param.put("bb", Tbb.getText() + " Kg.");
            param.put("tb", Ttb.getText() + " Cm.");
            param.put("imt", Timt.getText() + " Kg/Cm3");
            param.put("lila", Tlila.getText() + " Cm.");
            param.put("bbi", Tbbi.getText() + " Kg.");
            param.put("tl", Ttl.getText() + " Cm.");
            param.put("ulna", Tulna.getText() + " Cm.");
            param.put("tbest", TtbEst.getText() + " Cm.");
            param.put("bbkoreksi", TtbEst.getText() + " Kg.");
            param.put("biokimia", Tbiokimia.getText() + "\n");
            
            if (cmbAsuhan.getSelectedIndex() == 1) {
                param.put("statusGZ", cmbSttsGizi.getSelectedItem().toString());
            } else if (cmbAsuhan.getSelectedIndex() == 2) {
                if (ChkBbu.isSelected() == true) {
                    gizianak1 = ChkBbu.getText() + " : " + TketBbu.getText() + ", Kategori Status Gizi : " + cmbBbu.getSelectedItem().toString() + "\n";
                } else {
                    gizianak1 = "";
                }
                
                if (ChkPbu.isSelected() == true) {
                    gizianak2 = ChkPbu.getText() + " : " + TketPbu.getText() + ", Kategori Status Gizi : " + cmbPbu.getSelectedItem().toString() + "\n";
                } else {
                    gizianak2 = "";
                }
                
                if (ChkBbpb.isSelected() == true) {
                    gizianak3 = ChkBbpb.getText() + " : " + TketBbpb.getText() + ", Kategori Status Gizi : " + cmbBbpb.getSelectedItem().toString() + "\n";
                } else {
                    gizianak3 = "";
                }
                param.put("statusGZ", gizianak1 + gizianak2 + gizianak3);
            }
            
            if (ChkMual.isSelected() == true) {
                klinis1 = ChkMual.getText() + "\n";
            } else {
                klinis1 = "";
            }

            if (ChkNyeri.isSelected() == true) {
                klinis2 = ChkNyeri.getText() + "\n";
            } else {
                klinis2 = "";
            }
            
            if (ChkDiare.isSelected() == true) {
                klinis3 = ChkDiare.getText() + "\n";
            } else {
                klinis3 = "";
            }
            
            if (ChkKesulitan.isSelected() == true) {
                klinis4 = ChkKesulitan.getText();
            } else {
                klinis4 = "";
            }
            dataKlinis1 = klinis1 + klinis2 + klinis3 + klinis4;
            
            if (ChkOedema.isSelected() == true) {
                klinis5 = ChkOedema.getText() + "\n";
            } else {
                klinis5 = "";
            }
            
            if (ChkKonstipasi.isSelected() == true) {
                klinis6 = ChkKonstipasi.getText() + "\n";
            } else {
                klinis6 = "";
            }
            
            if (ChkAnorek.isSelected() == true) {
                klinis7 = ChkAnorek.getText() + "\n";
            } else {
                klinis7 = "";
            }
            
            if (ChkGangguan.isSelected() == true) {
                klinis8 = ChkGangguan.getText() + "\n";
            } else {
                klinis8 = "";
            }
            dataKlinis2 = klinis5 + klinis6 + klinis7 + klinis8 + TklinisLain.getText();
            
            if (ChkMakanLebih3.isSelected() == true) {
                riw1 = ChkMakanLebih3.getText() + "\n";
            } else {
                riw1 = "";
            }
            
            if (ChkMakanKurang3.isSelected() == true) {
                riw2 = ChkMakanKurang3.getText() + "\n";
            } else {
                riw2 = "";
            }
            
            if (TriwayatLain.getText().equals("")) {
                TriwayatLain.setText("");
            } else {
                TriwayatLain.setText(TriwayatLain.getText() + "\n");
            }
            
            if (ChkAlergi.isSelected() == true) {
                riw3 = ChkAlergi.getText() + " : " + Talergi.getText() + "\n";
            } else {
                riw3 = "";
            }
            
            if (ChkPantangan.isSelected() == true) {
                riw4 = ChkPantangan.getText() + " : " + Tpantangan.getText();
            } else {
                riw4 = "";
            }
            dataRiwayat1 = "RIWAYAT GIZI \n"
                    + "Riwayat Makan SMRS \n" + riw1 + riw2 + TriwayatLain.getText() + riw3 + riw4;
            
            if (ChkAsupanCukup.isSelected() == true) {
                riw5 = ChkAsupanCukup.getText() + "\n";
            } else {
                riw5 = "";
            }
            
            if (ChkAsupanMenurun.isSelected() == true) {
                riw6 = ChkAsupanMenurun.getText() + "\n";
            } else {
                riw6 = "";
            }
            
            if (ChkAsupanRendah.isSelected() == true) {
                riw7 = ChkAsupanRendah.getText() + "\n";
            } else {
                riw7 = "";
            }
            
            if (ChkAsupanTdkCukup.isSelected() == true) {
                riw8 = ChkAsupanTdkCukup.getText() + "\n";
            } else {
                riw8 = "";
            }
            dataRiwayat2 = "\nAsupan Makan SMRS \n" + riw5 + riw6 + riw7 + riw8 + "Hasil Recall Intake : " + cmbHasilRecal.getSelectedItem().toString();

            if (cmbProtein.getSelectedIndex() == 3) {
                param.put("protein", Tprotein.getText());
            } else {
                param.put("protein", cmbProtein.getSelectedItem().toString());
            }
            
            if (cmbLemak.getSelectedIndex() == 3) {
                param.put("lemak", Tlemak.getText());
            } else {
                param.put("lemak", cmbLemak.getSelectedItem().toString());
            }
            
            if (cmbKarbo.getSelectedIndex() == 3) {
                param.put("karbo", Tkarbo.getText());
            } else {
                param.put("karbo", cmbKarbo.getSelectedItem().toString());
            }
            
            if (ChkAsupanMakan.isSelected() == true) {
                ren1 = ChkAsupanMakan.getText() + ", ";
            } else {
                ren1 = "";
            }
            
            if (ChkAntropometri.isSelected() == true) {
                ren2 = ChkAntropometri.getText() + ", ";
            } else {
                ren2 = "";
            }
            
            if (ChkBiokimia.isSelected() == true) {
                ren3 = ChkBiokimia.getText() + ", ";
            } else {
                ren3 = "";
            }
            
            if (ChkKlinis.isSelected() == true) {
                ren4 = ChkKlinis.getText() + ", ";
            } else {
                ren4 = "";
            }
            
            if (ChkLain_lain.isSelected() == true) {
                ren5 = ChkLain_lain.getText();
            } else {
                ren5 = "";
            }
            
            if (ren1.equals("") && ren2.equals("") && ren3.equals("") && ren4.equals("") && ren5.equals("")) {
                rencanamonev = "-";
            } else {
                rencanamonev = ren1 + ren2 + ren3 + ren4 + ren5;
            }
                    
            param.put("dataklinis1", dataKlinis1);
            param.put("dataklinis2", dataKlinis2);
            param.put("dataRiwayat1", dataRiwayat1);
            param.put("dataRiwayat2", dataRiwayat2);
            param.put("riwayatpenyakit", TriwPenyakit.getText() + "\n");
            param.put("diagnosa", diagnosaPrin + "\n");
            param.put("berkaitan", Tberkaitan.getText() + "\n");
            param.put("ditandai", Tditandai.getText() + "\n");
            param.put("bentuk", cmbBentuk.getSelectedItem().toString() + ", ");
            param.put("rute", cmbRute.getSelectedItem().toString() + ", ");
            param.put("jenisdiet", TjnsDiet.getText());
            param.put("kalori", Tkalori.getText());
            param.put("rencanaMonev", rencanamonev);
            param.put("petugas", TnmPetugas.getText());

            Valid.MyReport("rptCetakAsuhanGiziRanap.jasper", "report", "::[ Asuhan Gizi Pasien Rawat Inap ]::",
                    "SELECT now() tanggal", param);

            emptTeks();    
            TabRawat.setSelectedIndex(1);
            tampil();

            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
            }
            TCariDiagnosa.setText("");
            tampilDiagnosa();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbAsuhan.requestFocus();
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbAsuhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsuhanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbAsuhan.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbAsuhanMouseClicked

    private void tbAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsuhanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsuhanKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilSGDewasa();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilSGAnak();
        } else if (TabRawat.getSelectedIndex() == 4) {
            cmbBulan.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
            angkaBulan = Sequel.cariIsi("select month(now())");
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
            tampilPersentase();
        } else if (TabRawat.getSelectedIndex() == 5) {
            tampilGiziBuruk();
        } else if (TabRawat.getSelectedIndex() == 6) {
            tampilBalita();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilDiagnosa();
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung1);
    }//GEN-LAST:event_formWindowOpened

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void ChkAsma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsma1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAsma1ActionPerformed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            BtnNilaiIMTActionPerformed(null);
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TlilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlilaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tlila.getText().contains(",") == true) {
                Tlila.setText(Tlila.getText().replaceAll(",", "."));
            }
            
            BtnNilaiBBActionPerformed(null);
            Tbbi.requestFocus();
        }
    }//GEN-LAST:event_TlilaKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());
        Valid.tabelKosong(tabMode3);
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah " + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString()
                    + " akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString().equals("DIHAPUS")) {
                    if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where "
                            + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "' and "
                            + "ruang_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore gagal, krn. sudah ada datanya dg. no. rawat & ruang rawat yg. sama..!!");
                    } else {
                        kembalikanData();
                        TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                        BtnCloseIn10ActionPerformed(null);
                        tampil();
                        emptTeks();
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    kembalikanDataDiganti();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
                }
            }
        } else {
            WindowRiwayat.setSize(1043, internalFrame1.getHeight() - 40);
            WindowRiwayat.setLocationRelativeTo(internalFrame1);
            WindowRiwayat.setAlwaysOnTop(false);
            WindowRiwayat.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsuhanGiziRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsuhanGiziRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsuhanGiziRanap");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                Tnip.setText("-");
                TnmPetugas.setText("-");
            } else {
                Tnip.setText(akses.getkode());
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
            }
        } else {
            Tnip.setText("-");
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }

            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            BtnNilaiIMTActionPerformed(null);
            Tlila.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void BtnNilaiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiIMTActionPerformed
        hitungIMT();
    }//GEN-LAST:event_BtnNilaiIMTActionPerformed

    private void BtnNilaiIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiIMTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiIMTActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnNilaiIMTKeyPressed

    private void TtlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }
            
            BtnNilaiTBActionPerformed(null);
            Tulna.requestFocus();
        }
    }//GEN-LAST:event_TtlKeyPressed

    private void TulnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TulnaKeyPressed
        Valid.pindah(evt, Ttl, TtbEst);
    }//GEN-LAST:event_TulnaKeyPressed

    private void BtnNilaiTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiTBActionPerformed
        hitungTBest();
    }//GEN-LAST:event_BtnNilaiTBActionPerformed

    private void BtnNilaiTBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiTBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiTBActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiTBKeyPressed

    private void TbiokimiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbiokimiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            ChkMual.requestFocus();
        }
    }//GEN-LAST:event_TbiokimiaKeyPressed

    private void TriwPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyakitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            tbDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyakitKeyPressed

    private void TtbEstKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbEstKeyPressed
        Valid.pindah(evt, Tulna, TbbKoreksi);
    }//GEN-LAST:event_TtbEstKeyPressed

    private void ChkAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiActionPerformed
        Talergi.setText("");
        if (ChkAlergi.isSelected() == true) {
            Talergi.setEnabled(true);
            Talergi.requestFocus();
        } else {
            Talergi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiActionPerformed

    private void ChkPantanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPantanganActionPerformed
        Tpantangan.setText("");
        if (ChkPantangan.isSelected() == true) {
            Tpantangan.setEnabled(true);
            Tpantangan.requestFocus();
        } else {
            Tpantangan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkPantanganActionPerformed

    private void BtnCariDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariDiagnosaActionPerformed
        tampilDiagnosa();
    }//GEN-LAST:event_BtnCariDiagnosaActionPerformed

    private void BtnCariDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilDiagnosa();
        }
    }//GEN-LAST:event_BtnCariDiagnosaKeyPressed

    private void BtnAllDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllDiagnosaActionPerformed
        TCariDiagnosa.setText("");
        tampilDiagnosa();
    }//GEN-LAST:event_BtnAllDiagnosaActionPerformed

    private void BtnAllDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllDiagnosaActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariDiagnosa, TCariDiagnosa);
        }
    }//GEN-LAST:event_BtnAllDiagnosaKeyPressed

    private void TCariDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariDiagnosaActionPerformed(null);
        }
    }//GEN-LAST:event_TCariDiagnosaKeyPressed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data diagnosa gizi belum ada...!!!!");
        } else {            
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
            }
            tampilDiagnosa();
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void TberkaitanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberkaitanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tditandai.requestFocus();
        }
    }//GEN-LAST:event_TberkaitanKeyPressed

    private void TditandaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TditandaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbBentuk.requestFocus();
        }
    }//GEN-LAST:event_TditandaiKeyPressed

    private void TkaloriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkaloriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkaloriKeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMAsuhanGiziRanap");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tbiokimia.getText().equals("")) {
                Tbiokimia.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tbiokimia.setText(Tbiokimia.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void TbbiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbiKeyPressed
        Valid.pindah(evt, Tlila, Ttl);
    }//GEN-LAST:event_TbbiKeyPressed

    private void cmbProteinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProteinActionPerformed
        Tprotein.setText("");
        if (cmbProtein.getSelectedIndex() == 3) {
            Tprotein.setEnabled(true);
            Tprotein.requestFocus();
        } else {
            Tprotein.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProteinActionPerformed

    private void cmbLemakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLemakActionPerformed
        Tlemak.setText("");
        if (cmbLemak.getSelectedIndex() == 3) {
            Tlemak.setEnabled(true);
            Tlemak.requestFocus();
        } else {
            Tlemak.setEnabled(false);
        }
    }//GEN-LAST:event_cmbLemakActionPerformed

    private void cmbKarboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKarboActionPerformed
        Tkarbo.setText("");
        if (cmbKarbo.getSelectedIndex() == 3) {
            Tkarbo.setEnabled(true);
            Tkarbo.requestFocus();
        } else {
            Tkarbo.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKarboActionPerformed

    private void BtnNilaiBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiBBActionPerformed
        hitungBBkoreksi();
    }//GEN-LAST:event_BtnNilaiBBActionPerformed

    private void BtnNilaiBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiBBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiBBActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiBBKeyPressed

    private void BtnNilaiKaloriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiKaloriActionPerformed
        cmbMetodeActionPerformed(null);
    }//GEN-LAST:event_BtnNilaiKaloriActionPerformed

    private void BtnNilaiKaloriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNilaiKaloriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnNilaiKaloriActionPerformed(null);
        }
    }//GEN-LAST:event_BtnNilaiKaloriKeyPressed

    private void cmbFaktorAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFaktorAActionPerformed
        if (cmbFaktorA.getSelectedIndex() == 0) {
            Tfa.setText("0");
            Tbmr.setText("0");
        } else if (cmbFaktorA.getSelectedIndex() == 1) {
            Tfa.setText("1.2");
        } else if (cmbFaktorA.getSelectedIndex() == 2) {
            Tfa.setText("1.3");
        }
        cmbMetodeActionPerformed(null);
    }//GEN-LAST:event_cmbFaktorAActionPerformed

    private void TfsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tfs.getText().contains(",") == true) {
                Tfs.setText(Tfs.getText().replaceAll(",", "."));
            }
            cmbMetodeActionPerformed(null);
        }
    }//GEN-LAST:event_TfsKeyPressed

    private void TfkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tfk.getText().contains(",") == true) {
                Tfk.setText(Tfk.getText().replaceAll(",", "."));
            }
            cmbMetodeActionPerformed(null);
        }
    }//GEN-LAST:event_TfkKeyPressed

    private void cmbFaktorSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFaktorSActionPerformed
        if (cmbFaktorS.getSelectedIndex() == 0) {
            Tfs.setText("0");
            Tbmr.setText("0");
        } else {
            if (Tfs.getText().equals("")) {
                Tfs.setText("0");
            } else {
                Tfs.setText(Tfs.getText());
            }
        }
        cmbMetodeActionPerformed(null);
    }//GEN-LAST:event_cmbFaktorSActionPerformed

    private void cmbSttsGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSttsGiziActionPerformed
        cmbMetodeActionPerformed(null);
    }//GEN-LAST:event_cmbSttsGiziActionPerformed

    private void cmbMetodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMetodeActionPerformed
        if (cmbMetode.getSelectedIndex() == 0) {
            cmbFaktorA.setSelectedIndex(0);
            Tfa.setText("0");
            cmbFaktorS.setSelectedIndex(0);
            Tfs.setText("0");
            Tfk.setText("0");
            Tbmr.setText("0");
        } else if (cmbMetode.getSelectedIndex() == 1) {
            hitungHarisKalori();
        } else if (cmbMetode.getSelectedIndex() == 2) {
            hitungPerkeniKalori();
        }
    }//GEN-LAST:event_cmbMetodeActionPerformed

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilRiwayatDiagnosa();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {                
                tampilRiwayatDiagnosa();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbRiwayatDiagnosa.getSelectedColumn() == 0)) {
                tampilRiwayatDiagnosa();
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void cmbKlasifikasiIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKlasifikasiIMTActionPerformed
        Tpersentase.setText("");
        if (cmbKlasifikasiIMT.getSelectedIndex() == 2) {
            Tpersentase.setEnabled(true);
            Tpersentase.requestFocus();
        } else {
            Tpersentase.setEnabled(false);
        }
        hitungIMT();
        cekStatusGizi();
    }//GEN-LAST:event_cmbKlasifikasiIMTActionPerformed

    private void ChkBbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBbuActionPerformed
        TketBbu.setText("");
        cmbBbu.setSelectedIndex(0);
        if (ChkBbu.isSelected() == true) {
            TketBbu.setEnabled(true);
            cmbBbu.setEnabled(true);
            TketBbu.requestFocus();
        } else {
            TketBbu.setEnabled(false);
            cmbBbu.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBbuActionPerformed

    private void ChkPbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPbuActionPerformed
        TketPbu.setText("");
        cmbPbu.setSelectedIndex(0);
        if (ChkPbu.isSelected() == true) {
            TketPbu.setEnabled(true);
            cmbPbu.setEnabled(true);
            TketPbu.requestFocus();
        } else {
            TketPbu.setEnabled(false);
            cmbPbu.setEnabled(false);
        }
    }//GEN-LAST:event_ChkPbuActionPerformed

    private void ChkBbpbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBbpbActionPerformed
        TketBbpb.setText("");
        cmbBbpb.setSelectedIndex(0);
        if (ChkBbpb.isSelected() == true) {
            TketBbpb.setEnabled(true);
            cmbBbpb.setEnabled(true);
            TketBbpb.requestFocus();
        } else {
            TketBbpb.setEnabled(false);
            cmbBbpb.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBbpbActionPerformed

    private void TketBbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketBbuKeyPressed
        Valid.pindah(evt, TketBbu, cmbBbu);
    }//GEN-LAST:event_TketBbuKeyPressed

    private void TketPbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketPbuKeyPressed
        Valid.pindah(evt, TketPbu, cmbPbu);
    }//GEN-LAST:event_TketPbuKeyPressed

    private void TketBbpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketBbpbKeyPressed
        Valid.pindah(evt, TketBbpb, cmbBbpb);
    }//GEN-LAST:event_TketBbpbKeyPressed

    private void MnHapusRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusRiwayatActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data riwayat mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {                
                if (Sequel.queryu2tf("delete from asuhan_gizi_ranap_histori where waktu_eksekusi=?", 1, new String[]{
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString()
                }) == true) {
                    Sequel.meghapus("detail_diagnosa_asuhan_gizi_histori", "waktu_eksekusi",
                            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString());

                    tampilRiwayat();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusRiwayatActionPerformed

    private void TpersentaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpersentaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tpersentase.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Angka persentase CDC harus terisi dulu..!!");
                Tpersentase.requestFocus();
            } else {
                if (Tpersentase.getText().trim().contains(",") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll(",", "."));
                }

                if (Tpersentase.getText().trim().contains("%") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll("%", ""));
                }
                cekStatusGizi();
                cmbSttsGizi.requestFocus();
            }
        }
    }//GEN-LAST:event_TpersentaseKeyPressed

    private void BtnCekSttsGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekSttsGiziActionPerformed
        cekStatusGizi();
    }//GEN-LAST:event_BtnCekSttsGiziActionPerformed

    private void BtnCekSttsGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCekSttsGiziKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCekSttsGiziActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCekSttsGiziKeyPressed

    private void MnMonevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonevActionPerformed
        if (tbAsuhan.getSelectedRow() > -1) {
            ChkAccor.setSelected(false);
            isMenu();

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsuhanGiziRanap");
            DlgMonevAsuhanGizi form = new DlgMonevAsuhanGizi(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TrgRawat.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnMonevActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } 
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilSGDewasa();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowLapStatusGZ.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu nama gedung perawatan inap dg. benar..!!!");
        } else {
            try {
                Sequel.queryu2("delete from temporary");
                ps9 = koneksi.prepareStatement("SELECT DISTINCT ki.no_rawat FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal LEFT JOIN status_gizi_inap sgi ON sgi.no_rawat = ki.no_rawat "
                        + "LEFT JOIN detail_beri_diet bd ON bd.no_rawat = ki.no_rawat where "
                        + "rp.tgl_registrasi between '" + Valid.SetTgl(tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl2.getSelectedItem() + "") + "' "
                        + "and (bd.waktu='Siang' or ifnull(bd.waktu,'') = '') AND b.nm_gedung='" + cmbGedung.getSelectedItem() + "' and ki.stts_pulang<>'Pindah Kamar'");
                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    ps10 = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, concat(ifnull(p.tmp_lahir,'-'),', ',date_format(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                        + "lower(ki.diagnosa_awal) diagnosa_awal, ifnull(agr.bb,'') bb, ifnull(agr.tb,'') tb, ifnull(sgi.status_gizi,'') sttsgz, "
                        + "ifnull(date_format(bd.tanggal,'%d-%m-%Y'),'') tgldiet, ifnull(d.nama_diet,'') nmdietSiang from kamar_inap ki "
                        + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "left join status_gizi_inap sgi on sgi.no_rawat=ki.no_rawat left join detail_beri_diet bd on bd.no_rawat=ki.no_rawat "
                        + "left join diet d on d.kd_diet=bd.kd_diet LEFT JOIN asuhan_gizi_ranap agr on agr.no_rawat=ki.no_rawat where "
                        + "rp.tgl_registrasi between '" + Valid.SetTgl(tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl2.getSelectedItem() + "") + "' "
                        + "and (bd.waktu='Siang' or ifnull(bd.waktu,'') = '') and ki.no_rawat = '" + rs9.getString("no_rawat") + "' "
                        + "AND b.nm_gedung='" + cmbGedung.getSelectedItem() + "' order by rp.no_rkm_medis, bd.tanggal");
                    rs10 = ps10.executeQuery();
                    z = 0;
                    while (rs10.next()) {
                        if (z == 0) {
                            Sequel.menyimpan("temporary", "'0','" + rs10.getString("no_rkm_medis") + "','" + rs10.getString("nm_pasien") + "','" + rs10.getString("ttl") + "',"
                                + "'" + rs10.getString("diagnosa_awal") + "','" + rs10.getString("bb") + "', '" + rs10.getString("tb") + "',"
                                + "'" + rs10.getString("sttsgz") + "','" + rs10.getString("nmdietSiang") + "','',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Laporan Gizi");
                        } else if (z == 1) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs10.getString("no_rkm_medis") + "'", "temp9='" + rs10.getString("nmdietSiang") + "'");
                        } else if (z == 2) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs10.getString("no_rkm_medis") + "'", "temp10='" + rs10.getString("nmdietSiang") + "'");
                        } else if (z == 3) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs10.getString("no_rkm_medis") + "'", "temp11='" + rs10.getString("nmdietSiang") + "'");
                        } else if (z == 4) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs10.getString("no_rkm_medis") + "'", "temp12='" + rs10.getString("nmdietSiang") + "'");
                        }
                        z++;
                    }
                }

                this.setCursor(Cursor.getDefaultCursor());
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + tgl1.getSelectedItem() + " S.D " + tgl2.getSelectedItem());
                param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
                param.put("gedung", "Laporan Status Gizi Pasien Rawat Inap Di Ruang Perawatan " + cmbGedung.getSelectedItem());
                Valid.MyReport("rptlapstatusgizi.jasper", "report", "::[ Laporan Status Gizi Pasien Dewasa Rawat Inap Per Gedung ]::", 
                        " select * from temporary", param);
                this.setCursor(Cursor.getDefaultCursor());

                BtnCloseIn4ActionPerformed(null);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void MnCetakLapRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakLapRekapActionPerformed
        tampilSGDewasa();
        if (Sequel.cariInteger("select count(-1) from status_gizi_inap sg inner join reg_periksa r on r.no_rawat=sg.no_rawat "
                + " where sg.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari6.getSelectedItem() + "") + "' "
                + " and r.status_lanjut='ranap'") == 0) {
            JOptionPane.showMessageDialog(null, "Data status gizi pasien dewasa utk. periode tgl. " + DTPCari5.getSelectedItem() + " s.d " + DTPCari6.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari5.requestFocus();
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + DTPCari5.getSelectedItem() + " S.D " + DTPCari6.getSelectedItem());
            param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            Valid.MyReport("rptrekapstatusgizi.jasper", "report", "::[ Data Rekap Status Gizi Pasien Dewasa ]::",
                " select sg.ruang_rawat, COUNT(CASE WHEN sg.status_gizi = 'BURUK' THEN 1 END) as buruk, "
                + " COUNT(CASE WHEN sg.status_gizi = 'KURANG' THEN 1 END) as kurang, "
                + " COUNT(CASE WHEN sg.status_gizi = 'NORMAL' THEN 1 END) as normal, "
                + " COUNT(CASE WHEN sg.status_gizi = 'LEBIH' THEN 1 END) as lebih, "
                + " COUNT(CASE WHEN sg.status_gizi = 'OBESITAS' THEN 1 END) as obesitas, "
                + " COUNT(sg.no_rawat) total from status_gizi_inap sg "
                + " inner join reg_periksa r on r.no_rawat=sg.no_rawat "
                + " where sg.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari5.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari6.getSelectedItem() + "") + "' "
                + " and r.status_lanjut='ranap' group by sg.ruang_rawat order by sg.ruang_rawat desc", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilSGDewasa();
            emptTeks();
            BtnKeluar2.requestFocus();
        }
    }//GEN-LAST:event_MnCetakLapRekapActionPerformed

    private void MnCetakLapStatusGZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakLapStatusGZActionPerformed
        WindowLapStatusGZ.setSize(363, 143);
        WindowLapStatusGZ.setLocationRelativeTo(internalFrame1);
        WindowLapStatusGZ.setVisible(true);
        cmbGedung.requestFocus();
        tampilSGDewasa();
    }//GEN-LAST:event_MnCetakLapStatusGZActionPerformed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        } 
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilSGAnak();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari3ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari3KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari1.setText("");
        tampilSGDewasa();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari1.setText("");
            tampilSGDewasa();
        } 
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll3ActionPerformed
        TCari3.setText("");
        tampilSGAnak();
    }//GEN-LAST:event_BtnAll3ActionPerformed

    private void BtnAll3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari3.setText("");
            tampilSGAnak();
        } 
    }//GEN-LAST:event_BtnAll3KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void MnAsesmenUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenUlangActionPerformed
        if (tbAsuhan.getSelectedRow() > -1) {
            ChkAccor.setSelected(false);
            isMenu();
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsuhanGiziRanap");
            DlgAssesmenGiziUlang form = new DlgAssesmenGiziUlang(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRw.getText(), TrgRawat.getText(), nmgedung);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnAsesmenUlangActionPerformed

    private void MnCetakLapRekap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakLapRekap1ActionPerformed
        if (Sequel.cariInteger("SELECT COUNT(-1) FROM asuhan_gizi_ranap WHERE tgl_asuhan BETWEEN "
                + " '" + Valid.SetTgl(DTPCari7.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari8.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data status gizi pasien anak utk. periode tgl. " + DTPCari7.getSelectedItem() + " s.d " + DTPCari8.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari7.requestFocus();
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + DTPCari7.getSelectedItem() + " S.D " + DTPCari8.getSelectedItem());
            param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            Valid.MyReport("rptrekapstatusgizianak.jasper", "report", "::[ Data Rekap Status Gizi Pasien Anak ]::",
                    "select b.nm_gedung, COUNT(CASE WHEN ag.stts_gizi_bbu = 'BB Sangat Kurang' THEN 1 END) as bbu_sangat, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbu = 'BB Kurang' THEN 1 END) as bbu_kurang, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbu = 'BB Normal' THEN 1 END) as bbu_normal, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbu = 'Resiko BB Lebih' THEN 1 END) as bbu_resiko, "
                    + "COUNT(CASE WHEN ag.stts_gizi_pbu = 'Sangat Pendek' THEN 1 END) as pbu_sangat, "
                    + "COUNT(CASE WHEN ag.stts_gizi_pbu = 'Pendek' THEN 1 END) as pbu_pendek, "
                    + "COUNT(CASE WHEN ag.stts_gizi_pbu = 'Normal' THEN 1 END) as pbu_normal, "
                    + "COUNT(CASE WHEN ag.stts_gizi_pbu = 'Tinggi' THEN 1 END) as pbu_tinggi, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Gizi Buruk' THEN 1 END) as bbpb_buruk, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Gizi Kurang' THEN 1 END) as bbpb_kurang, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Gizi Baik (Normal)' THEN 1 END) as bbpb_baik, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Beresiko Gizi Lebih' THEN 1 END) as bbpb_beresiko, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Gizi Lebih' THEN 1 END) as bbpb_lebih, "
                    + "COUNT(CASE WHEN ag.stts_gizi_bbpb = 'Obesitas' THEN 1 END) as bbpb_obes, "
                    + "COUNT(ag.no_rawat) total from asuhan_gizi_ranap ag inner join reg_periksa r on r.no_rawat=ag.no_rawat "
                    + "left join bangsal b on b.nm_bangsal=ag.ruang_rawat where "
                    + "r.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari7.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari8.getSelectedItem() + "") + "' "
                    + "and ag.jenis_asuhan='Anak' group by b.nm_gedung order by b.nm_gedung desc", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilSGAnak();
            emptTeks();
            BtnKeluar3.requestFocus();
        }
    }//GEN-LAST:event_MnCetakLapRekap1ActionPerformed

    private void MnCetakLapStatusGZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakLapStatusGZ1ActionPerformed
        WindowLapStatusGZanak.setSize(363, 165);
        WindowLapStatusGZanak.setLocationRelativeTo(internalFrame1);
        WindowLapStatusGZanak.setVisible(true);
        cmbGedung1.requestFocus();
    }//GEN-LAST:event_MnCetakLapStatusGZ1ActionPerformed

    private void DTPCari8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPCari8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari8ActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowLapStatusGZanak.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetak1ActionPerformed
        if (cmbGedung1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu nama gedung perawatan inap dg. benar..!!!");
        } else {
            sttsgizibbu = "";
            sttsgizipbu = "";
            sttsgizibbpb = "";
            sttsgiziprin = "";
            try {
                Sequel.queryu2("delete from temporary");
                ps12 = koneksi.prepareStatement("SELECT DISTINCT ki.no_rawat FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal LEFT JOIN asuhan_gizi_ranap ag ON ag.no_rawat = ki.no_rawat "
                        + "LEFT JOIN detail_beri_diet bd ON bd.no_rawat = ki.no_rawat where "
                        + "rp.tgl_registrasi between '" + Valid.SetTgl(tgl3.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl4.getSelectedItem() + "") + "' "
                        + "and (bd.waktu='Siang' or ifnull(bd.waktu,'') = '') AND b.nm_gedung='" + cmbGedung1.getSelectedItem() + "' and ki.stts_pulang<>'Pindah Kamar'");
                rs12 = ps12.executeQuery();
                while (rs12.next()) {
                    if (cmbPilihAsuhan.getSelectedIndex() == 0) {
                        ps13 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, concat(ifnull(p.tmp_lahir,'-' ),', ',date_format(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                                + "lower(ki.diagnosa_awal) diagnosa_awal, ifnull(agr.bb,'') bb, ifnull(agr.tb,'') tb, if(agr.indek_bbu='ya',agr.stts_gizi_bbu,'') bbu, "
                                + "if(agr.indek_pbu='ya',agr.stts_gizi_pbu,'') pbu, if(agr.indek_bbpb='ya',agr.stts_gizi_bbpb,'') bbpb, ifnull(date_format(bd.tanggal,'%d-%m-%Y'),'') tgldiet, "
                                + "ifnull(d.nama_diet,'') nmdietSiang FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat "
                                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                                + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal INNER JOIN asuhan_gizi_ranap agr ON agr.no_rawat = ki.no_rawat "
                                + "LEFT JOIN detail_beri_diet bd ON bd.no_rawat = ki.no_rawat LEFT JOIN diet d ON d.kd_diet = bd.kd_diet where "
                                + "rp.tgl_registrasi between '" + Valid.SetTgl(tgl3.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl4.getSelectedItem() + "") + "' "
                                + "and (bd.waktu='Siang' or ifnull(bd.waktu,'') = '') and ki.no_rawat = '" + rs12.getString("no_rawat") + "' "
                                + "AND b.nm_gedung='" + cmbGedung1.getSelectedItem() + "' order by rp.no_rkm_medis, bd.tanggal");
                    } else {
                        ps13 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, concat(ifnull(p.tmp_lahir,'-' ),', ',date_format(p.tgl_lahir,'%d-%m-%Y')) ttl, "
                                + "lower(ki.diagnosa_awal) diagnosa_awal, ifnull(agr.bb,'') bb, ifnull(agr.tb,'') tb, if(agr.indek_bbu='ya',agr.stts_gizi_bbu,'') bbu, "
                                + "if(agr.indek_pbu='ya',agr.stts_gizi_pbu,'') pbu, if(agr.indek_bbpb='ya',agr.stts_gizi_bbpb,'') bbpb, ifnull(date_format(bd.tanggal,'%d-%m-%Y'),'') tgldiet, "
                                + "ifnull(d.nama_diet,'') nmdietSiang FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat "
                                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                                + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal LEFT JOIN asuhan_gizi_ranap agr ON agr.no_rawat = ki.no_rawat "
                                + "LEFT JOIN detail_beri_diet bd ON bd.no_rawat = ki.no_rawat LEFT JOIN diet d ON d.kd_diet = bd.kd_diet where "
                                + "rp.tgl_registrasi between '" + Valid.SetTgl(tgl3.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(tgl4.getSelectedItem() + "") + "' "
                                + "and (bd.waktu='Siang' or ifnull(bd.waktu,'') = '') and ki.no_rawat = '" + rs12.getString("no_rawat") + "' "
                                + "AND b.nm_gedung='" + cmbGedung1.getSelectedItem() + "' order by rp.no_rkm_medis, bd.tanggal");
                    }
                    rs13 = ps13.executeQuery();
                    z = 0;
                    while (rs13.next()) {
                        if (rs13.getString("bbu").equals("")) {
                            sttsgizibbu = "";
                        } else {
                            sttsgizibbu = "Indeks (BB/U) : " + rs13.getString("bbu") + ", ";
                        }

                        if (rs13.getString("pbu").equals("")) {
                            sttsgizipbu = "";
                        } else {
                            sttsgizipbu = "Indeks (PB/U atau TB/U) : " + rs13.getString("pbu") + ", ";
                        }

                        if (rs13.getString("bbpb").equals("")) {
                            sttsgizibbpb = "";
                        } else {
                            sttsgizibbpb = "Indeks (BB/PB atau BB/TB) : " + rs13.getString("bbpb") + ", ";
                        }
                        sttsgiziprin = sttsgizibbu + sttsgizipbu + sttsgizibbpb;

                        if (z == 0) {
                            Sequel.menyimpan("temporary", "'0','" + rs13.getString("no_rkm_medis") + "','" + rs13.getString("nm_pasien") + "','" + rs13.getString("ttl") + "',"
                                    + "'" + rs13.getString("diagnosa_awal") + "','" + rs13.getString("bb") + "', '" + rs13.getString("tb") + "',"
                                    + "'" + sttsgiziprin + "','" + rs13.getString("nmdietSiang") + "','','','','','','','','','','','','','','','','','','','',"
                                    + "'','','','','','','','','',''", "Laporan Gizi Anak");
                        } else if (z == 1) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs13.getString("no_rkm_medis") + "'", "temp9='" + rs13.getString("nmdietSiang") + "'");
                        } else if (z == 2) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs13.getString("no_rkm_medis") + "'", "temp10='" + rs13.getString("nmdietSiang") + "'");
                        } else if (z == 3) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs13.getString("no_rkm_medis") + "'", "temp11='" + rs13.getString("nmdietSiang") + "'");
                        } else if (z == 4) {
                            Sequel.mengedit("temporary", "temp1 = '" + rs13.getString("no_rkm_medis") + "'", "temp12='" + rs13.getString("nmdietSiang") + "'");
                        }
                        z++;
                    }
                }

                this.setCursor(Cursor.getDefaultCursor());
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + tgl3.getSelectedItem() + " S.D " + tgl4.getSelectedItem());
                param.put("tgl_surat", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
                param.put("gedung", "Laporan Status Gizi Pasien Rawat Inap Di Ruang Perawatan " + cmbGedung1.getSelectedItem());
                Valid.MyReport("rptlapstatusgizi.jasper", "report", "::[ Laporan Status Gizi Pasien Anak Rawat Inap Per Gedung ]::", 
                        " select * from temporary", param);
                this.setCursor(Cursor.getDefaultCursor());
                
                BtnCloseIn5ActionPerformed(null);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BtnCetak1ActionPerformed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        tampilPersentase();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void cmbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanActionPerformed
        angkaBulan = "";
        if (cmbBulan.getSelectedIndex() == 0) {
            angkaBulan = "1";
        } else if (cmbBulan.getSelectedIndex() == 1) {
            angkaBulan = "2";
        } else if (cmbBulan.getSelectedIndex() == 2) {
            angkaBulan = "3";
        } else if (cmbBulan.getSelectedIndex() == 3) {
            angkaBulan = "4";
        } else if (cmbBulan.getSelectedIndex() == 4) {
            angkaBulan = "5";
        } else if (cmbBulan.getSelectedIndex() == 5) {
            angkaBulan = "6";
        } else if (cmbBulan.getSelectedIndex() == 6) {
            angkaBulan = "7";
        } else if (cmbBulan.getSelectedIndex() == 7) {
            angkaBulan = "8";
        } else if (cmbBulan.getSelectedIndex() == 8) {
            angkaBulan = "9";
        } else if (cmbBulan.getSelectedIndex() == 9) {
            angkaBulan = "10";
        } else if (cmbBulan.getSelectedIndex() == 10) {
            angkaBulan = "11";
        } else if (cmbBulan.getSelectedIndex() == 11) {
            angkaBulan = "12";
        }
    }//GEN-LAST:event_cmbBulanActionPerformed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunKeyPressed

    private void BtnCetakPersentaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersentaseActionPerformed
        if (tbPersentase.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan tampilkan datanya terlebih dulu..!!!");
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE BULAN " + cmbBulan.getSelectedItem().toString().toUpperCase() + " TAHUN " + Ttahun.getText());
            Valid.MyReport("rptPersentaseAsuhanGizi.jasper", "report", "::[ Persentase Asuhan Gizi Pasien ]::",
                    "select * from (select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_asuhan, "
                    + "(a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_asuhan, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_tersasuhan, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_terasuhan from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_keluar)=" + angkaBulan + " and YEAR(ki.tgl_keluar)=" + Ttahun.getText().trim() + " and ki.stts_pulang not in ('-','Pindah Kamar') "
                    + "GROUP BY MONTH(ki.tgl_keluar), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(ag.no_rawat) jlh_px_asuhan from asuhan_gizi_ranap ag "
                    + "inner join kamar_inap ki on ki.no_rawat=ag.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=ag.ruang_rawat "
                    + "WHERE MONTH(ag.tgl_asuhan)=" + angkaBulan + " and YEAR(ag.tgl_asuhan)=" + Ttahun.getText().trim() + " and ki.stts_pulang not in ('-','Pindah Kamar') "
                    + "and ag.jenis_asuhan='Dewasa' GROUP BY month(ag.tgl_asuhan), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung) "
                    + "union ALL "
                    + "select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_asuhan, (a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_asuhan, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_tersasuhan, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_terasuhan from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_keluar)=" + angkaBulan + " and YEAR(ki.tgl_keluar)=" + Ttahun.getText().trim() + " and ki.stts_pulang not in ('-','Pindah Kamar') "
                    + "GROUP BY MONTH(ki.tgl_keluar), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(ag.no_rawat) jlh_px_asuhan from asuhan_gizi_ranap ag "
                    + "inner join kamar_inap ki on ki.no_rawat=ag.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=ag.ruang_rawat "
                    + "WHERE MONTH(ag.tgl_asuhan)=" + angkaBulan + " and YEAR(ag.tgl_asuhan)=" + Ttahun.getText().trim() + " and ki.stts_pulang not in ('-','Pindah Kamar') "
                    + "and ag.jenis_asuhan='Anak' GROUP BY month(ag.tgl_asuhan), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilPersentase();
            emptTeks();
            BtnKeluar4.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakPersentaseActionPerformed

    private void ThitungZatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThitungZatKeyPressed
        Valid.pindah(evt, ThitungZat, TbbAkhir);
    }//GEN-LAST:event_ThitungZatKeyPressed

    private void TdiagnosaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaDokterKeyPressed
        Valid.pindah(evt, ThitungZat, Tpemberian);
    }//GEN-LAST:event_TdiagnosaDokterKeyPressed

    private void TpemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemberianKeyPressed
        Valid.pindah(evt, TdiagnosaDokter, BtnSimpan);
    }//GEN-LAST:event_TpemberianKeyPressed

    private void TbbAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbAkhirKeyPressed
        Valid.pindah(evt, ThitungZat, TdiagnosaDokter);
    }//GEN-LAST:event_TbbAkhirKeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        tampilGiziBuruk();
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari5ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void BtnCetakPersentase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersentase1ActionPerformed
        if (tbBuruk.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tampilkan datanya terlebih dulu pada tabel sesuai tgl. menyimpan data..!!");
        } else if (Sequel.cariInteger("SELECT COUNT(-1) FROM gizi_buruk WHERE tgl_input BETWEEN "
                + " '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data gizi buruk pasien utk periode tgl. " + DTPCari9.getSelectedItem() + " s.d " + DTPCari10.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari9.requestFocus();
        } else {
            String diagnosaAwal = "";            
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("periode", "PERIODE TGL. " + DTPCari9.getSelectedItem() + " S.D " + DTPCari10.getSelectedItem());
            diagnosaAwal = Sequel.cariIsi("select ki.diagnosa_awal from kamar_inap ki inner join gizi_buruk gb on gb.no_rawat=ki.no_rawat where "
                    + "gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "'");

            Valid.MyReport("rptRekapGiziBuruk.jasper", "report", "::[ Data Rekapitulasi Pasien Gizi Buruk ]::",
                    "SELECT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, '" + diagnosaAwal + "' diagnosa_awal, gb.bb_awal, "
                    + "gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + "gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan, (SELECT COUNT(*) FROM gizi_buruk where "
                    + "tgl_input BETWEEN '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "') total_px "
                    + "FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "where gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilGiziBuruk();
        }
    }//GEN-LAST:event_BtnCetakPersentase1ActionPerformed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void TCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari5.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar5.requestFocus();
        }
    }//GEN-LAST:event_TCari4KeyPressed

    private void BtnAll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll4ActionPerformed
        TCari4.setText("");
        BtnCari5ActionPerformed(null);
    }//GEN-LAST:event_BtnAll4ActionPerformed

    private void BtnAll4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari4.setText("");
            BtnCari5ActionPerformed(null);            
        }
    }//GEN-LAST:event_BtnAll4KeyPressed

    private void MnIndividuPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIndividuPxActionPerformed
        if (tbBuruk.getSelectedRow() > -1) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("diagnosa", Sequel.cariIsi("select diagnosa_awal from kamar_inap where "
                    + "no_rawat='" + tbBuruk.getValueAt(tbBuruk.getSelectedRow(), 1).toString() + "' order by tgl_masuk limit 1"));
            param.put("tglsurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            param.put("hasilLab", Sequel.cariIsi("select ifnull(biokimia,'-') from asuhan_gizi_ranap where "
                    + "no_rawat='" + tbBuruk.getValueAt(tbBuruk.getSelectedRow(), 1).toString() + "' order by tgl_asuhan limit 1"));
            
            Valid.MyReport("rptDataGiziBurukPX.jasper", "report", "::[ Data Individu Pasien Gizi Buruk ]::",
                " SELECT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, gb.bb_awal, "
                + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                + " gb.asal_rujukan FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                + " where gb.no_rawat='" + tbBuruk.getValueAt(tbBuruk.getSelectedRow(), 1).toString() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilGiziBuruk();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbBuruk.requestFocus();
        }
    }//GEN-LAST:event_MnIndividuPxActionPerformed

    private void TCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari6ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari5KeyPressed

    private void BtnCari6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari6ActionPerformed
        tampilBalita();
    }//GEN-LAST:event_BtnCari6ActionPerformed

    private void BtnCari6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari6KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari6ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari6KeyPressed

    private void BtnAll5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll5ActionPerformed
        TCari5.setText("");
        tampilBalita();
    }//GEN-LAST:event_BtnAll5ActionPerformed

    private void BtnAll5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari5.setText("");
            tampilBalita();
        }
    }//GEN-LAST:event_BtnAll5KeyPressed

    private void BtnCetakPersentase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersentase2ActionPerformed
        if (tbBalita.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan tampilkan datanya terlebih dulu..!!!");
        } else {
            if (cekTampil == 0) {
                tampilBalita();
                cetakKlasifikasiStatusGiziBalita();
            } else {
                cetakKlasifikasiStatusGiziBalita();
            }
        }
    }//GEN-LAST:event_BtnCetakPersentase2ActionPerformed

    private void BtnKeluar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar6ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar6ActionPerformed

    private void DTPCari11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPCari11ActionPerformed
        cekTampil = 0;
    }//GEN-LAST:event_DTPCari11ActionPerformed

    private void DTPCari12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPCari12ActionPerformed
        cekTampil = 0;
    }//GEN-LAST:event_DTPCari12ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsuhanGiziRanap dialog = new RMAsuhanGiziRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnAll2;
    private widget.Button BtnAll3;
    private widget.Button BtnAll4;
    private widget.Button BtnAll5;
    private widget.Button BtnAllDiagnosa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCari4;
    private widget.Button BtnCari5;
    private widget.Button BtnCari6;
    private widget.Button BtnCariDiagnosa;
    private widget.Button BtnCekSttsGizi;
    private widget.Button BtnCetak;
    private widget.Button BtnCetak1;
    private widget.Button BtnCetakPersentase;
    private widget.Button BtnCetakPersentase1;
    private widget.Button BtnCetakPersentase2;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar4;
    private widget.Button BtnKeluar5;
    private widget.Button BtnKeluar6;
    private widget.Button BtnNilaiBB;
    private widget.Button BtnNilaiIMT;
    private widget.Button BtnNilaiKalori;
    private widget.Button BtnNilaiTB;
    private widget.Button BtnNotepad;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkAlergi;
    public widget.CekBox ChkAnorek;
    public widget.CekBox ChkAntropometri;
    public widget.CekBox ChkAsupanCukup;
    public widget.CekBox ChkAsupanMakan;
    public widget.CekBox ChkAsupanMenurun;
    public widget.CekBox ChkAsupanRendah;
    public widget.CekBox ChkAsupanTdkCukup;
    public widget.CekBox ChkBbpb;
    public widget.CekBox ChkBbu;
    public widget.CekBox ChkBiokimia;
    public widget.CekBox ChkDiare;
    public widget.CekBox ChkGangguan;
    public widget.CekBox ChkKesulitan;
    public widget.CekBox ChkKlinis;
    public widget.CekBox ChkKonstipasi;
    public widget.CekBox ChkLain_lain;
    public widget.CekBox ChkMakanKurang3;
    public widget.CekBox ChkMakanLebih3;
    public widget.CekBox ChkMual;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkOedema;
    public widget.CekBox ChkPantangan;
    public widget.CekBox ChkPbu;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari10;
    private widget.Tanggal DTPCari11;
    private widget.Tanggal DTPCari12;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCari5;
    private widget.Tanggal DTPCari6;
    private widget.Tanggal DTPCari7;
    private widget.Tanggal DTPCari8;
    private widget.Tanggal DTPCari9;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.Label LCount3;
    private widget.Label LCount4;
    private widget.Label LCount5;
    private javax.swing.JMenuItem MnAsesmenUlang;
    private javax.swing.JMenuItem MnCetakLapRekap;
    private javax.swing.JMenuItem MnCetakLapRekap1;
    private javax.swing.JMenuItem MnCetakLapStatusGZ;
    private javax.swing.JMenuItem MnCetakLapStatusGZ1;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnHapusRiwayat;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnIndividuPx;
    private javax.swing.JMenuItem MnMonev;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TCari3;
    public widget.TextBox TCari4;
    public widget.TextBox TCari5;
    private widget.TextBox TCariDiagnosa;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talergi;
    private widget.TextBox Tbb;
    private widget.TextBox TbbAkhir;
    private widget.TextBox TbbKoreksi;
    private widget.TextBox Tbbi;
    private widget.TextArea Tberkaitan;
    private widget.TextArea Tbiokimia;
    private widget.TextBox Tbmr;
    private widget.TextBox TdiagnosaDokter;
    private widget.TextArea Tditandai;
    private widget.TextBox Tfa;
    private widget.TextBox Tfaskes;
    private widget.TextBox Tfk;
    private widget.TextBox Tfs;
    private widget.TextArea Thasil;
    private widget.TextBox ThitungZat;
    private widget.TextBox Timt;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjk;
    private widget.TextBox TjnsDiet;
    private widget.TextBox Tkalori;
    private widget.TextBox Tkarbo;
    private widget.TextBox TketBbpb;
    private widget.TextBox TketBbu;
    private widget.TextBox TketPbu;
    private widget.TextBox TklinisLain;
    private widget.TextBox Tlemak;
    private widget.TextBox Tlila;
    private widget.TextBox Tnip;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Tpantangan;
    private widget.TextBox Tpemberian;
    private widget.TextBox Tpersentase;
    private widget.TextBox Tprotein;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwPenyakit;
    private widget.TextBox TriwayatLain;
    private widget.Label Tsttsumur;
    private widget.TextBox Ttahun;
    private widget.TextBox Ttb;
    private widget.TextBox TtbEst;
    private widget.TextBox Ttl;
    private widget.TextBox Tulna;
    private widget.TextBox Tumur;
    public javax.swing.JDialog WindowLapStatusGZ;
    public javax.swing.JDialog WindowLapStatusGZanak;
    private javax.swing.JDialog WindowRiwayat;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbAsuhan;
    private widget.ComboBox cmbBbpb;
    private widget.ComboBox cmbBbu;
    private widget.ComboBox cmbBentuk;
    private widget.ComboBox cmbBulan;
    private widget.ComboBox cmbFaktorA;
    private widget.ComboBox cmbFaktorS;
    public widget.ComboBox cmbGedung;
    public widget.ComboBox cmbGedung1;
    private widget.ComboBox cmbHasilRecal;
    private widget.ComboBox cmbKarbo;
    private widget.ComboBox cmbKlasifikasiIMT;
    private widget.ComboBox cmbLemak;
    private widget.ComboBox cmbMetode;
    private widget.ComboBox cmbPbu;
    private widget.ComboBox cmbPilihAsuhan;
    private widget.ComboBox cmbProtein;
    private widget.ComboBox cmbRute;
    private widget.ComboBox cmbSttsGizi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
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
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private widget.Label label105;
    private widget.Label label106;
    private widget.Label label107;
    private widget.Label label108;
    private widget.Label label109;
    private widget.Label label110;
    private widget.Label label111;
    private widget.Label label112;
    private widget.Label label113;
    private widget.Label label114;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbAsuhan;
    private widget.Table tbBalita;
    private widget.Table tbBuruk;
    private widget.Table tbCPPT;
    private widget.Table tbDiagnosa;
    private widget.Table tbPersentase;
    private widget.Table tbRekapBBPB;
    private widget.Table tbRekapBBU;
    private widget.Table tbRekapPBU;
    private widget.Table tbRiwayat;
    private widget.Table tbRiwayatDiagnosa;
    private widget.Table tbStatusGZ;
    private widget.Table tbStatusGZ1;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tgl3;
    private widget.Tanggal tgl4;
    private widget.Tanggal tglAsuhan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        cekumur = "";
        ceksttsumur = "";
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pg.nama nmPetugas, date_format(ag.tgl_asuhan,'%d-%m-%Y') tgl, "
                    + "if(p.jk='L','Laki-Laki','Perempuan') jk, rp.umurdaftar, rp.sttsumur, TIMESTAMPDIFF(YEAR,p.tgl_lahir, rp.tgl_registrasi) umurTahun, "
                    + "concat(TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi),' ','Bl') umurBulan, ag.* "
                    + "FROM reg_periksa rp inner join asuhan_gizi_ranap ag on ag.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg on pg.nik=ag.nip_petugas WHERE "
                    + "ag.tgl_asuhan BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "ag.tgl_asuhan BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "ag.tgl_asuhan BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "ag.tgl_asuhan BETWEEN ? AND ? AND pg.nama LIKE ? ORDER BY ag.tgl_asuhan");
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
                    if (rs.getString("jenis_asuhan").equals("Dewasa")) {
                        cekUlangStatusGiziDewasa(rs.getString("klasifikasi_imt"), rs.getString("imt"), rs.getString("persentase_cdc"));

                        if (rs.getString("status_gizi").equals("-")) {
                            Sequel.mengedit("asuhan_gizi_ranap", "no_rawat='" + rs.getString("no_rawat") + "' and ruang_rawat='" + rs.getString("ruang_rawat") + "'",
                                    "status_gizi='" + sttsgizi + "'");
                            System.out.println("Update status gizi no. rawat " + rs.getString("no_rawat") + " & tgl. asuhan " + rs.getString("tgl") + " pada data asuhan berhasil disimpan..!!");
                        }
                    }
                    
                    if (Integer.parseInt(rs.getString("umurTahun")) <= 5) {
                        cekumur = rs.getString("umurBulan");
                        ceksttsumur = "";
                    } else {
                        if (rs.getString("sttsumur").equals("Bl") || rs.getString("sttsumur").equals("Hr")) {
                            cekumur = rs.getString("umurdaftar") + " " + rs.getString("sttsumur");
                            ceksttsumur = "";
                        } else {
                            cekumur = rs.getString("umurdaftar");
                            ceksttsumur = "Tahun.";
                        }
                    }
                    
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("umurdaftar") + " " + rs.getString("sttsumur") + ". (" + rs.getString("jenis_asuhan") + ")",
                        rs.getString("jk"),
                        rs.getString("tgl"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nmPetugas"),
                        rs.getString("tgl_asuhan"),
                        rs.getString("ruang_rawat"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("imt"),
                        rs.getString("lila"),
                        rs.getString("tinggi_lutut"),
                        rs.getString("ulna"),
                        rs.getString("tb_est"),
                        rs.getString("bb_koreksi"),
                        rs.getString("bbi"),
                        rs.getString("status_gizi"),
                        rs.getString("biokimia"),
                        rs.getString("mual_muntah"),
                        rs.getString("nyeri_ulu_hati"),
                        rs.getString("diare"),
                        rs.getString("kesulitan_menelan"),
                        rs.getString("oedema"),
                        rs.getString("konstipasi"),
                        rs.getString("anoreksia"),
                        rs.getString("gangguan_gigi_geligi"),
                        rs.getString("klinis_lainnya"),
                        rs.getString("makan_lebih_3x"),
                        rs.getString("makan_kurang_3x"),
                        rs.getString("riwayat_gizi_lainnya"),
                        rs.getString("alergi_makanan"),
                        rs.getString("ket_alergi_makanan"),
                        rs.getString("pantangan_makan"),
                        rs.getString("ket_pantangan_makan"),
                        rs.getString("asupan_cukup"),
                        rs.getString("asupan_menurun"),
                        rs.getString("asupan_rendah"),
                        rs.getString("asupan_tidak_cukup"),
                        rs.getString("hasil_recall"),
                        rs.getString("riwayat_penyakit_personal"),
                        rs.getString("berkaitan_dengan"),
                        rs.getString("ditandai_dengan"),
                        rs.getString("bentuk_makanan"),
                        rs.getString("jenis_diet"),
                        rs.getString("rute_makanan"),
                        rs.getString("metode_hitungan"),
                        rs.getString("kalori"),
                        rs.getString("bmr"),
                        rs.getString("faktor_aktivitas"),
                        rs.getString("jns_faktor_aktivitas"),
                        rs.getString("faktor_koreksi"),
                        rs.getString("faktor_stres"),
                        rs.getString("jns_faktor_stres"),
                        rs.getString("protein"),
                        rs.getString("protein_lain"),
                        rs.getString("lemak"),
                        rs.getString("lemak_lain"),
                        rs.getString("karbohidrat"),
                        rs.getString("karbohidrat_lain"),
                        rs.getString("nip_petugas"),
                        cekumur,
                        ceksttsumur,
                        rs.getString("klasifikasi_imt"),
                        rs.getString("persentase_cdc"),
                        rs.getString("monev_asupan_makan"),
                        rs.getString("monev_antropometri"),
                        rs.getString("monev_biokimia"),
                        rs.getString("monev_klinis"),
                        rs.getString("monev_lain"),
                        rs.getString("jenis_asuhan"),
                        rs.getString("indek_bbu"),
                        rs.getString("indek_pbu"),
                        rs.getString("indek_bbpb"),
                        rs.getString("ket_indek_bbu"),
                        rs.getString("ket_indek_pbu"),
                        rs.getString("ket_indek_bbpb"),
                        rs.getString("stts_gizi_bbu"),
                        rs.getString("stts_gizi_pbu"),
                        rs.getString("stts_gizi_bbpb")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        Tbb.setText("0");
        Ttb.setText("0");
        Timt.setText("0");
        Tlila.setText("0");
        TtbEst.setText("0");
        Ttl.setText("0");
        TbbKoreksi.setText("0");
        Tulna.setText("0");        
        Tbbi.setText("0");
        cmbAsuhan.setSelectedIndex(0);
        cmbKlasifikasiIMT.setSelectedIndex(0);
        Tpersentase.setText("");
        Tpersentase.setEnabled(false);
        cmbSttsGizi.setSelectedIndex(0);
        
        bbu = "";
        pbu = "";
        bbpb = "";
        TketBbu.setEnabled(false);
        TketPbu.setEnabled(false);
        TketBbpb.setEnabled(false);
        cmbBbu.setEnabled(false);
        cmbPbu.setEnabled(false);
        cmbBbpb.setEnabled(false);
        
        ChkBbu.setSelected(false);
        ChkPbu.setSelected(false);
        ChkBbpb.setSelected(false);
        TketBbu.setText("");
        TketPbu.setText("");
        TketBbpb.setText("");
        cmbBbu.setSelectedIndex(0);
        cmbPbu.setSelectedIndex(0);
        cmbBbpb.setSelectedIndex(0);
        Tbiokimia.setText("");
        
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        ChkMual.setSelected(false);
        ChkNyeri.setSelected(false);
        ChkDiare.setSelected(false);
        ChkKesulitan.setSelected(false);
        ChkOedema.setSelected(false);
        ChkKonstipasi.setSelected(false);
        ChkAnorek.setSelected(false);
        ChkGangguan.setSelected(false);
        TklinisLain.setText("");
        
        makanlebih = "";
        makankurang = "";
        asupanCukup = "";
        asupanMenurun = "";
        asupanRendah = "";
        asupanTidak = "";
        alergi = "";
        pantangan = "";
        ChkMakanLebih3.setSelected(false);
        ChkMakanKurang3.setSelected(false);
        ChkAsupanCukup.setSelected(false);
        ChkAsupanMenurun.setSelected(false);
        ChkAsupanRendah.setSelected(false);
        ChkAsupanTdkCukup.setSelected(false);
        
        cmbHasilRecal.setSelectedIndex(0);
        TriwayatLain.setText("");
        ChkAlergi.setSelected(false);
        Talergi.setText("");
        Talergi.setEnabled(false);
        ChkPantangan.setSelected(false);
        Tpantangan.setText("");
        Tpantangan.setEnabled(false);
        TriwPenyakit.setText("");
        Tberkaitan.setText("");
        Tditandai.setText("");
        cmbBentuk.setSelectedIndex(0);
        cmbRute.setSelectedIndex(0);
        TjnsDiet.setText("");
        
        cmbProtein.setSelectedIndex(0);
        cmbLemak.setSelectedIndex(0);
        cmbKarbo.setSelectedIndex(0);
        Tprotein.setText("");
        Tlemak.setText("");
        Tkarbo.setText("");
        Tprotein.setEnabled(false);
        Tlemak.setEnabled(false);
        Tkarbo.setEnabled(false);
        
        Tkalori.setText("");        
        Tnip.setText("-");
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);
        
        cmbMetode.setSelectedIndex(0);
        cmbFaktorA.setSelectedIndex(0);
        Tfa.setText("0");
        cmbFaktorS.setSelectedIndex(0);
        Tfs.setText("0");
        Tfk.setText("0");
        Tbmr.setText("0");
        
        asupanmakan = "";
        antro = "";
        biokimia = "";
        klinis = "";
        lainlain = "";
        ChkAsupanMakan.setSelected(false);
        ChkAntropometri.setSelected(false);
        ChkBiokimia.setSelected(false);
        ChkKlinis.setSelected(false);
        ChkLain_lain.setSelected(false);
        
        ThitungZat.setText("");
        TbbAkhir.setText("");
        TdiagnosaDokter.setText("");
        Tpemberian.setText("");
        Tfaskes.setText("");
    }

    private void getData() {
        variabelBersih();
        if (tbAsuhan.getSelectedRow() != -1) {
            TNoRw.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglAsuhan, tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 8).toString());
            Tumur.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 63).toString());
            Tsttsumur.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 64).toString());
            Tjk.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 4).toString());
            TrgRawat.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 9).toString());
            Tbb.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 10).toString());
            Ttb.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 11).toString());
            Timt.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 12).toString());
            Tlila.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 13).toString());
            Tbbi.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 18).toString());
            Ttl.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 14).toString());
            Tulna.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 15).toString());
            TtbEst.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 16).toString());
            TbbKoreksi.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 17).toString());
            cmbSttsGizi.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 19).toString());
            Tbiokimia.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 20).toString());
            mual = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 21).toString();
            nyeri = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 22).toString();
            diare = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 23).toString();
            kesulitan = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 24).toString();
            odema = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 25).toString();
            konstipasi = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 26).toString();
            anoreksia = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 27).toString();
            gangguan = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 28).toString();
            TklinisLain.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 29).toString());
            makanlebih = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 30).toString();
            makankurang = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 31).toString();
            TriwayatLain.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 32).toString());
            alergi = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 33).toString();
            Talergi.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 34).toString());
            pantangan = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 35).toString();
            Tpantangan.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 36).toString());
            asupanCukup = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 37).toString();
            asupanMenurun = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 38).toString();
            asupanRendah = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 39).toString();
            asupanTidak = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 40).toString();
            cmbHasilRecal.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 41).toString());
            TriwPenyakit.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 42).toString());
            Tberkaitan.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 43).toString());
            Tditandai.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 44).toString());
            cmbBentuk.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 45).toString());
            TjnsDiet.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 46).toString());
            cmbRute.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 47).toString());
            cmbMetode.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 48).toString());
            Tkalori.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 49).toString());
            Tbmr.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 50).toString());
            Tfa.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 51).toString());
            cmbFaktorA.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 52).toString());
            Tfk.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 53).toString());
            Tfs.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 54).toString());
            cmbFaktorS.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 55).toString());
            cmbProtein.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 56).toString());
            Tprotein.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 57).toString());
            cmbLemak.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 58).toString());
            Tlemak.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 59).toString());
            cmbKarbo.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 60).toString());
            Tkarbo.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 61).toString());
            Tnip.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 62).toString());
            TnmPetugas.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 7).toString());
            cmbKlasifikasiIMT.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 65).toString());
            Tpersentase.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 66).toString());
            asupanmakan = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 67).toString();
            antro = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 68).toString();
            biokimia = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 69).toString();
            klinis = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 70).toString();
            lainlain = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 71).toString();
            cmbAsuhan.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 72).toString());
            bbu = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 73).toString();
            pbu = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 74).toString();
            bbpb = tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 75).toString();
            TketBbu.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 76).toString());
            TketPbu.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 77).toString());
            TketBbpb.setText(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 78).toString());
            cmbBbu.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 79).toString());
            cmbPbu.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 80).toString());
            cmbBbpb.setSelectedItem(tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 81).toString());
            kodekamar = Sequel.cariIsi("SELECT k.kd_kamar from bangsal b inner join kamar k on k.kd_bangsal=b.kd_bangsal WHERE "
                    + "b.nm_bangsal='" + TrgRawat.getText() + "' limit 1");
            nmgedung = Sequel.cariIsi("SELECT nm_gedung from bangsal WHERE nm_bangsal='" + TrgRawat.getText() + "' limit 1");
            Tfaskes.setText(Sequel.cariIsi("select ifnull(perujuk,'') from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'"));
            dataCek();
            cekStatusGizi();
            cekGiziBuruk(TNoRw.getText());
        }
    }

    private void isRawat() {
        try {
            ps4 = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi, rp.umurdaftar, rp.sttsumur, rp.no_rawat, "
                    + "TIMESTAMPDIFF(YEAR,p.tgl_lahir, rp.tgl_registrasi) umurTahun, "
                    + "concat(TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi),' ','Bl') umurBulan "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps4.setString(1, TNoRw.getText());
                rs4 = ps4.executeQuery();
                if (rs4.next()) {
                    TNoRM.setText(rs4.getString("no_rkm_medis"));
                    TPasien.setText(rs4.getString("nm_pasien"));
                    Tjk.setText(rs4.getString("jk"));                    
                    Valid.SetTgl(tglAsuhan, rs4.getString("tgl_registrasi"));
                    DTPCari1.setDate(rs4.getDate("tgl_registrasi"));

                    if (Integer.parseInt(rs4.getString("umurTahun")) <= 5) {
                        Tumur.setText(rs4.getString("umurBulan"));
                        Tsttsumur.setText("");                        
                    } else {
                        if (rs4.getString("sttsumur").equals("Bl") || rs4.getString("sttsumur").equals("Hr")) {
                            Tumur.setText(rs4.getString("umurdaftar") + " " + rs4.getString("sttsumur"));
                            Tsttsumur.setText("");                            
                        } else {
                            Tumur.setText(rs4.getString("umurdaftar"));
                            Tsttsumur.setText("Tahun.");                            
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
    public void setData(String norwt, String rgrawat, String gedung, String kdkmr) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        kodekamar = kdkmr;
        nmgedung = gedung;
        Tfaskes.setText(Sequel.cariIsi("select ifnull(perujuk,'') from rujuk_masuk where no_rawat='" + norwt + "'"));
     
        if (gedung.equals("ANAK")) {
            cmbAsuhan.setSelectedIndex(2);
        } else if (gedung.equals("AL-HAKIM/PARU") || gedung.equals("AR-RAUDAH") || gedung.equals("AR-RAUDAH ATAS")
                || gedung.equals("AR-RAUDAH BAWAH") || gedung.equals("AS-SAMI") || gedung.equals("BEDAH")
                || gedung.equals("BERSALIN") || gedung.equals("RKPD") || gedung.equals("ZAAL")) {
            cmbAsuhan.setSelectedIndex(1);
        } else {
            cmbAsuhan.setSelectedIndex(0);
        }

        isRawat();
        hitungIMT();
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getassesmen_gizi_harian());
        BtnHapus.setEnabled(akses.getassesmen_gizi_harian());
        BtnEdit.setEnabled(akses.getassesmen_gizi_harian());
        MnRiwayatData.setEnabled(akses.getadmin());
        
        if (akses.getjml2() >= 1) {            
//            BtnPerawat.setEnabled(false);
            Tnip.setText(akses.getkode());    
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, Tnip.getText());
            if (TnmPetugas.getText().equals("")) {
                Tnip.setText("");
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asuhan_gizi_ranap", "no_rawat=? and ruang_rawat=?", "tgl_asuhan=?, ruang_rawat=?, bb=?, tb=?, imt=?, lila=?, tinggi_lutut=?, ulna=?, tb_est=?, bb_koreksi=?, "
                + "bbi=?, status_gizi=?, biokimia=?, mual_muntah=?, nyeri_ulu_hati=?, diare=?, kesulitan_menelan=?, oedema=?, konstipasi=?, "
                + "anoreksia=?, gangguan_gigi_geligi=?, klinis_lainnya=?, makan_lebih_3x=?, makan_kurang_3x=?, riwayat_gizi_lainnya=?, "
                + "alergi_makanan=?, ket_alergi_makanan=?, pantangan_makan=?, ket_pantangan_makan=?, asupan_cukup=?, asupan_menurun=?, "
                + "asupan_rendah=?, asupan_tidak_cukup=?, hasil_recall=?, riwayat_penyakit_personal=?, berkaitan_dengan=?, ditandai_dengan=?, "
                + "bentuk_makanan=?, jenis_diet=?, rute_makanan=?, metode_hitungan=?, kalori=?, bmr=?, faktor_aktivitas=?, jns_faktor_aktivitas=?, "
                + "faktor_koreksi=?, faktor_stres=?, jns_faktor_stres=?, protein=?, protein_lain=?, lemak=?, lemak_lain=?, karbohidrat=?, "
                + "karbohidrat_lain=?, nip_petugas=?, klasifikasi_imt=?, persentase_cdc=?, monev_asupan_makan=?, monev_antropometri=?, monev_biokimia=?, "
                + "monev_klinis=?, monev_lain=?, jenis_asuhan=?, indek_bbu=?, indek_pbu=?, indek_bbpb=?, ket_indek_bbu=?, ket_indek_pbu=?, ket_indek_bbpb=?, "
                + "stts_gizi_bbu=?, stts_gizi_pbu=?, stts_gizi_bbpb=?", 74, new String[]{
                    Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(), Tlila.getText(), Ttl.getText(), Tulna.getText(),
                    TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(), Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia,
                    gangguan, TklinisLain.getText(), makanlebih, makankurang, TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun, asupanRendah,
                    asupanTidak, cmbHasilRecal.getSelectedItem().toString(), TriwPenyakit.getText(), Tberkaitan.getText(), Tditandai.getText(), cmbBentuk.getSelectedItem().toString(), TjnsDiet.getText(),
                    cmbRute.getSelectedItem().toString(), cmbMetode.getSelectedItem().toString(), Tkalori.getText(), Tbmr.getText(), Tfa.getText(), cmbFaktorA.getSelectedItem().toString(), Tfk.getText(),
                    Tfs.getText(), cmbFaktorS.getSelectedItem().toString(), cmbProtein.getSelectedItem().toString(), Tprotein.getText(), cmbLemak.getSelectedItem().toString(), Tlemak.getText(),
                    cmbKarbo.getSelectedItem().toString(), Tkarbo.getText(), Tnip.getText(), cmbKlasifikasiIMT.getSelectedItem().toString(), Tpersentase.getText(),
                    asupanmakan, antro, biokimia, klinis, lainlain, cmbAsuhan.getSelectedItem().toString(), bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(), TketBbpb.getText(), cmbBbu.getSelectedItem().toString(),
                    cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString(),
                    tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString(), tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 9).toString()
                }) == true) {

            //diagnosa dihapus dulu
            Sequel.meghapus("detail_diagnosa_asuhan_gizi", "ruang_rawat='" + tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 9).toString() + "' and no_rawat",
                    tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString());

            //simpan diagnosa gizi
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("detail_diagnosa_asuhan_gizi", "?,?,?", 3, new String[]{
                        TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), TrgRawat.getText()});
                }
            }

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);

            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
            }
            TCariDiagnosa.setText("");
            tampilDiagnosa();
        }
    }
    
    private void cekData() {
        // klinis/fisik
        if (ChkMual.isSelected() == true) {
            mual = "ya";
        } else {
            mual = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkDiare.isSelected() == true) {
            diare = "ya";
        } else {
            diare = "tidak";
        }
        
        if (ChkKesulitan.isSelected() == true) {
            kesulitan = "ya";
        } else {
            kesulitan = "tidak";
        }
        
        if (ChkOedema.isSelected() == true) {
            odema = "ya";
        } else {
            odema = "tidak";
        }
        
        if (ChkKonstipasi.isSelected() == true) {
            konstipasi = "ya";
        } else {
            konstipasi = "tidak";
        }
        
        if (ChkAnorek.isSelected() == true) {
            anoreksia = "ya";
        } else {
            anoreksia = "tidak";
        }
        
        if (ChkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        //riwayat gizi
        if (ChkMakanLebih3.isSelected() == true) {
            makanlebih = "ya";
        } else {
            makanlebih = "tidak";
        }
        
        if (ChkMakanKurang3.isSelected() == true) {
            makankurang = "ya";
        } else {
            makankurang = "tidak";
        }
        
        if (ChkAsupanCukup.isSelected() == true) {
            asupanCukup = "ya";
        } else {
            asupanCukup = "tidak";
        }
        
        if (ChkAsupanMenurun.isSelected() == true) {
            asupanMenurun = "ya";
        } else {
            asupanMenurun = "tidak";
        }
        
        if (ChkAsupanRendah.isSelected() == true) {
            asupanRendah = "ya";
        } else {
            asupanRendah = "tidak";
        }
        
        if (ChkAsupanTdkCukup.isSelected() == true) {
            asupanTidak = "ya";
        } else {
            asupanTidak = "tidak";
        }
        
        if (ChkAlergi.isSelected() == true) {
            alergi = "ya";
        } else {
            alergi = "tidak";
        }
        
        if (ChkPantangan.isSelected() == true) {
            pantangan = "ya";
        } else {
            pantangan = "tidak";
        }
        
        //rencana monev
        if (ChkAsupanMakan.isSelected() == true) {
            asupanmakan = "ya";
        } else {
            asupanmakan = "tidak";
        }
        
        if (ChkAntropometri.isSelected() == true) {
            antro = "ya";
        } else {
            antro = "tidak";
        }
        
        if (ChkBiokimia.isSelected() == true) {
            biokimia = "ya";
        } else {
            biokimia = "tidak";
        }
        
        if (ChkKlinis.isSelected() == true) {
            klinis = "ya";
        } else {
            klinis = "tidak";
        }
        
        if (ChkLain_lain.isSelected() == true) {
            lainlain = "ya";
        } else {
            lainlain = "tidak";
        }
        
        //indek status gizi
        if (ChkBbu.isSelected() == true) {
            bbu = "ya";
        } else {
            bbu = "tidak";
        }
        
        if (ChkPbu.isSelected() == true) {
            pbu = "ya";
        } else {
            pbu = "tidak";
        }
        
        if (ChkBbpb.isSelected() == true) {
            bbpb = "ya";
        } else {
            bbpb = "tidak";
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            user = "";
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }

            hapusDisimpan();
            if (Sequel.queryu2tf("delete from asuhan_gizi_ranap where ruang_rawat='" + tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 9).toString() + "' and no_rawat=?", 1, new String[]{
                tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus2("detail_diagnosa_asuhan_gizi", "ruang_rawat='" + tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 9).toString() + "' and no_rawat",
                        tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString());              
                Sequel.meghapus2("status_gizi_inap", "ruang_rawat='" + nmgedung + "' and no_rawat",
                        tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString());
                Sequel.meghapus("gizi_buruk", "no_rawat", 
                        tbAsuhan.getValueAt(tbAsuhan.getSelectedRow(), 0).toString());
                tampil();
                emptTeks();

                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
                }
                TCariDiagnosa.setText("");
                tampilDiagnosa();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
            }
            TCariDiagnosa.setText("");
            tampilDiagnosa();
        }
    }
    
    private void dataCek() {
        //klinis /fisik
        if (mual.equals("ya")) {
            ChkMual.setSelected(true);
        } else {
            ChkMual.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
        } else {
            ChkNyeri.setSelected(false);
        }
        
        if (diare.equals("ya")) {
            ChkDiare.setSelected(true);
        } else {
            ChkDiare.setSelected(false);
        }
        
        if (kesulitan.equals("ya")) {
            ChkKesulitan.setSelected(true);
        } else {
            ChkKesulitan.setSelected(false);
        }
        
        if (odema.equals("ya")) {
            ChkOedema.setSelected(true);
        } else {
            ChkOedema.setSelected(false);
        }
        
        if (konstipasi.equals("ya")) {
            ChkKonstipasi.setSelected(true);
        } else {
            ChkKonstipasi.setSelected(false);
        }
        
        if (anoreksia.equals("ya")) {
            ChkAnorek.setSelected(true);
        } else {
            ChkAnorek.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }
        
        //riwayat gizi
        if (makanlebih.equals("ya")) {
            ChkMakanLebih3.setSelected(true);
        } else {
            ChkMakanLebih3.setSelected(false);
        }
        
        if (makankurang.equals("ya")) {
            ChkMakanKurang3.setSelected(true);
        } else {
            ChkMakanKurang3.setSelected(false);
        }
        
        if (asupanCukup.equals("ya")) {
            ChkAsupanCukup.setSelected(true);
        } else {
            ChkAsupanCukup.setSelected(false);
        }
        
        if (asupanMenurun.equals("ya")) {
            ChkAsupanMenurun.setSelected(true);
        } else {
            ChkAsupanMenurun.setSelected(false);
        }
        
        if (asupanRendah.equals("ya")) {
            ChkAsupanRendah.setSelected(true);
        } else {
            ChkAsupanRendah.setSelected(false);
        }
        
        if (asupanTidak.equals("ya")) {
            ChkAsupanTdkCukup.setSelected(true);
        } else {
            ChkAsupanTdkCukup.setSelected(false);
        }
        
        if (alergi.equals("ya")) {
            ChkAlergi.setSelected(true);
            Talergi.setEnabled(true);
        } else {
            ChkAlergi.setSelected(false);
            Talergi.setEnabled(false);
        }
        
        if (pantangan.equals("ya")) {
            ChkPantangan.setSelected(true);
            Tpantangan.setEnabled(true);
        } else {
            ChkPantangan.setSelected(false);
            Tpantangan.setEnabled(false);
        }
        
        //tampil diagnosa
        diagnosaPrin = "";
        Valid.tabelKosong(tabMode2);
        try {
            ps5 = koneksi.prepareStatement("select k.* from detail_diagnosa_asuhan_gizi d "
                    + "inner join katalog_diagnosa_gizi k on k.kd_diagnosa_gizi=d.kd_diagnosa_gizi where "
                    + "d.no_rawat like ? and d.ruang_rawat like ? order by k.kd_diagnosa_gizi");
            try {
                ps5.setString(1, "%" + TNoRw.getText().trim() + "%");
                ps5.setString(2, "%" + TrgRawat.getText() + "%");
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    //ditaruh di grid tabel diagnosa
                    tabMode2.addRow(new Object[]{
                        true,
                        rs5.getString("kd_diagnosa_gizi"),
                        rs5.getString("deskripsi_diagnosa")
                    });

                    //untuk diprin
                    if (diagnosaPrin.equals("")) {
                        diagnosaPrin = rs5.getString("kd_diagnosa_gizi") + " " + rs5.getString("deskripsi_diagnosa");
                    } else {
                        diagnosaPrin = diagnosaPrin + "\n" + rs5.getString("kd_diagnosa_gizi") + " " + rs5.getString("deskripsi_diagnosa");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        //rencana monev
        if (asupanmakan.equals("ya")) {
            ChkAsupanMakan.setSelected(true);
        } else {
            ChkAsupanMakan.setSelected(false);
        }
        
        if (antro.equals("ya")) {
            ChkAntropometri.setSelected(true);
        } else {
            ChkAntropometri.setSelected(false);
        }
        
        if (biokimia.equals("ya")) {
            ChkBiokimia.setSelected(true);
        } else {
            ChkBiokimia.setSelected(false);
        }
        
        if (klinis.equals("ya")) {
            ChkKlinis.setSelected(true);
        } else {
            ChkKlinis.setSelected(false);
        }
        
        if (lainlain.equals("ya")) {
            ChkLain_lain.setSelected(true);
        } else {
            ChkLain_lain.setSelected(false);
        }
        
        //indek status gizi
        if (bbu.equals("ya")) {
            ChkBbu.setSelected(true);
            TketBbu.setEnabled(true);
            cmbBbu.setEnabled(true);
        } else {
            ChkBbu.setSelected(false);
            TketBbu.setEnabled(false);
            cmbBbu.setEnabled(false);
        }
        
        if (pbu.equals("ya")) {
            ChkPbu.setSelected(true);
            TketPbu.setEnabled(true);
            cmbPbu.setEnabled(true);
        } else {
            ChkPbu.setSelected(false);
            TketPbu.setEnabled(false);
            cmbPbu.setEnabled(false);
        }
        
        if (bbpb.equals("ya")) {
            ChkBbpb.setSelected(true);
            TketBbpb.setEnabled(true);
            cmbBbpb.setEnabled(true);
        } else {
            ChkBbpb.setSelected(false);
            TketBbpb.setEnabled(false);
            cmbBbpb.setEnabled(false);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusDisimpan() {
        tglHistori = "";
        cekData();
        tglHistori = Sequel.cariIsi("select now()");

        if (Sequel.menyimpantf("asuhan_gizi_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 76, new String[]{
                    TNoRw.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(), Tlila.getText(), Ttl.getText(), Tulna.getText(),
                    TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(), Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia,
                    gangguan, TklinisLain.getText(), makanlebih, makankurang, TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun, asupanRendah,
                    asupanTidak, cmbHasilRecal.getSelectedItem().toString(), TriwPenyakit.getText(), Tberkaitan.getText(), Tditandai.getText(), cmbBentuk.getSelectedItem().toString(), TjnsDiet.getText(),
                    cmbRute.getSelectedItem().toString(), cmbMetode.getSelectedItem().toString(), Tkalori.getText(), Tbmr.getText(), Tfa.getText(), cmbFaktorA.getSelectedItem().toString(), Tfk.getText(),
                    Tfs.getText(), cmbFaktorS.getSelectedItem().toString(), cmbProtein.getSelectedItem().toString(), Tprotein.getText(), cmbLemak.getSelectedItem().toString(), Tlemak.getText(),
                    cmbKarbo.getSelectedItem().toString(), Tkarbo.getText(), Tnip.getText(), "hapus", user, tglHistori, cmbKlasifikasiIMT.getSelectedItem().toString(), Tpersentase.getText(),
                    asupanmakan, antro, biokimia, klinis, lainlain, cmbAsuhan.getSelectedItem().toString(), bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(), TketBbpb.getText(), cmbBbu.getSelectedItem().toString(),
                    cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString()
                }) == true) {
            //simpan diagnosa gizi
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("detail_diagnosa_asuhan_gizi_histori", "?,?,?,?,?,?", 6, new String[]{
                        TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), "hapus", user, tglHistori, TrgRawat.getText()});
                }
            }

            System.out.println("Asuhan Gizi Dihapus Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void gantiDisimpan() {
        tglHistori = "";
        cekData();
        tglHistori = Sequel.cariIsi("select now()");

        if (Sequel.menyimpantf("asuhan_gizi_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 76, new String[]{
                    TNoRw.getText(), Valid.SetTgl(tglAsuhan.getSelectedItem() + ""), TrgRawat.getText(), Tbb.getText(), Ttb.getText(), Timt.getText(), Tlila.getText(), Ttl.getText(), Tulna.getText(),
                    TtbEst.getText(), TbbKoreksi.getText(), Tbbi.getText(), cmbSttsGizi.getSelectedItem().toString(), Tbiokimia.getText(), mual, nyeri, diare, kesulitan, odema, konstipasi, anoreksia,
                    gangguan, TklinisLain.getText(), makanlebih, makankurang, TriwayatLain.getText(), alergi, Talergi.getText(), pantangan, Tpantangan.getText(), asupanCukup, asupanMenurun, asupanRendah,
                    asupanTidak, cmbHasilRecal.getSelectedItem().toString(), TriwPenyakit.getText(), Tberkaitan.getText(), Tditandai.getText(), cmbBentuk.getSelectedItem().toString(), TjnsDiet.getText(),
                    cmbRute.getSelectedItem().toString(), cmbMetode.getSelectedItem().toString(), Tkalori.getText(), Tbmr.getText(), Tfa.getText(), cmbFaktorA.getSelectedItem().toString(), Tfk.getText(),
                    Tfs.getText(), cmbFaktorS.getSelectedItem().toString(), cmbProtein.getSelectedItem().toString(), Tprotein.getText(), cmbLemak.getSelectedItem().toString(), Tlemak.getText(),
                    cmbKarbo.getSelectedItem().toString(), Tkarbo.getText(), Tnip.getText(), "ganti", user, tglHistori, cmbKlasifikasiIMT.getSelectedItem().toString(), Tpersentase.getText(),
                    asupanmakan, antro, biokimia, klinis, lainlain, cmbAsuhan.getSelectedItem().toString(), bbu, pbu, bbpb, TketBbu.getText(), TketPbu.getText(), TketBbpb.getText(), cmbBbu.getSelectedItem().toString(),
                    cmbPbu.getSelectedItem().toString(), cmbBbpb.getSelectedItem().toString()
                }) == true) {
            //simpan diagnosa gizi
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("detail_diagnosa_asuhan_gizi_histori", "?,?,?,?,?,?", 6, new String[]{
                        TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), "ganti", user, tglHistori, TrgRawat.getText()});
                }
            }

            System.out.println("Asuhan Gizi Diganti Berhasil Tersimpan Sebagai Data Histori..!!");
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode3);
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_asuhan, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata, a.ruang_rawat FROM asuhan_gizi_ranap_histori a "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "a.tgl_asuhan between ? and ? and pg.nama like ? or "
                    + "a.tgl_asuhan between ? and ? and a.no_rawat like ? or "
                    + "a.tgl_asuhan between ? and ? and p.no_rkm_medis like ? or "
                    + "a.tgl_asuhan between ? and ? and a.status_data like ? or "
                    + "a.tgl_asuhan between ? and ? and p.nm_pasien like ? order by a.tgl_asuhan desc");
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode1.addRow(new String[]{
                        rsrestor.getString("pelaku"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tgl_asuhan"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),
                        rsrestor.getString("ruang_rawat")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps2 = koneksi.prepareStatement("select * from asuhan_gizi_ranap_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        if (Sequel.menyimpantf("asuhan_gizi_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 73, new String[]{
                                    rs2.getString("no_rawat"),
                                    rs2.getString("tgl_asuhan"),
                                    rs2.getString("ruang_rawat"),
                                    rs2.getString("bb"),
                                    rs2.getString("tb"),
                                    rs2.getString("imt"),
                                    rs2.getString("lila"),
                                    rs2.getString("tinggi_lutut"),
                                    rs2.getString("ulna"),
                                    rs2.getString("tb_est"),
                                    rs2.getString("bb_koreksi"),
                                    rs2.getString("bbi"),
                                    rs2.getString("status_gizi"),
                                    rs2.getString("biokimia"),
                                    rs2.getString("mual_muntah"),
                                    rs2.getString("nyeri_ulu_hati"),
                                    rs2.getString("diare"),
                                    rs2.getString("kesulitan_menelan"),
                                    rs2.getString("oedema"),
                                    rs2.getString("konstipasi"),
                                    rs2.getString("anoreksia"),
                                    rs2.getString("gangguan_gigi_geligi"),
                                    rs2.getString("klinis_lainnya"),
                                    rs2.getString("makan_lebih_3x"),
                                    rs2.getString("makan_kurang_3x"),
                                    rs2.getString("riwayat_gizi_lainnya"),
                                    rs2.getString("alergi_makanan"),
                                    rs2.getString("ket_alergi_makanan"),
                                    rs2.getString("pantangan_makan"),
                                    rs2.getString("ket_pantangan_makan"),
                                    rs2.getString("asupan_cukup"),
                                    rs2.getString("asupan_menurun"),
                                    rs2.getString("asupan_rendah"),
                                    rs2.getString("asupan_tidak_cukup"),
                                    rs2.getString("hasil_recall"),
                                    rs2.getString("riwayat_penyakit_personal"),
                                    rs2.getString("berkaitan_dengan"),
                                    rs2.getString("ditandai_dengan"),
                                    rs2.getString("bentuk_makanan"),
                                    rs2.getString("jenis_diet"),
                                    rs2.getString("rute_makanan"),
                                    rs2.getString("metode_hitungan"),
                                    rs2.getString("kalori"),
                                    rs2.getString("bmr"),
                                    rs2.getString("faktor_aktivitas"),
                                    rs2.getString("jns_faktor_aktivitas"),
                                    rs2.getString("faktor_koreksi"),
                                    rs2.getString("faktor_stres"),
                                    rs2.getString("jns_faktor_stres"),
                                    rs2.getString("protein"),
                                    rs2.getString("protein_lain"),
                                    rs2.getString("lemak"),
                                    rs2.getString("lemak_lain"),
                                    rs2.getString("karbohidrat"),
                                    rs2.getString("karbohidrat_lain"),
                                    rs2.getString("nip_petugas"),
                                    rs2.getString("klasifikasi_imt"),
                                    rs2.getString("persentase_cdc"),
                                    rs2.getString("monev_asupan_makan"),
                                    rs2.getString("monev_antropometri"),
                                    rs2.getString("monev_biokimia"),
                                    rs2.getString("monev_klinis"),
                                    rs2.getString("monev_lain"),
                                    rs2.getString("jenis_asuhan"),
                                    rs2.getString("indek_bbu"),
                                    rs2.getString("indek_pbu"),
                                    rs2.getString("indek_bbpb"),
                                    rs2.getString("ket_indek_bbu"),
                                    rs2.getString("ket_indek_pbu"),
                                    rs2.getString("ket_indek_bbpb"),
                                    rs2.getString("stts_gizi_bbu"),
                                    rs2.getString("stts_gizi_pbu"),
                                    rs2.getString("stts_gizi_bbpb")
                                }) == true) {
                            //kembalikan diagnosa
                            try {
                                ps6 = koneksi.prepareStatement("select * from detail_diagnosa_asuhan_gizi_histori where "
                                        + "no_rawat='" + rs2.getString("no_rawat") + "' and status_data='" + rs2.getString("status_data") + "' "
                                        + "and waktu_eksekusi='" + rs2.getString("waktu_eksekusi") + "'");
                                try {
                                    rs6 = ps6.executeQuery();
                                    while (rs6.next()) {
                                        try {
                                            Sequel.menyimpan2("detail_diagnosa_asuhan_gizi", "?,?,?", 3, new String[]{
                                                rs6.getString("no_rawat"),
                                                rs6.getString("kd_diagnosa_gizi"),
                                                rs6.getString("ruang_rawat")
                                            });
                                        } catch (Exception e) {
                                            System.out.println("Simpan : " + e);
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
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
                            
                            if (rs2.getString("jenis_asuhan").equals("Dewasa")) {
                                //kembalikan status gizi
                                gantiSttsGiziDewasa(Sequel.cariIsi("SELECT nm_gedung from bangsal WHERE nm_bangsal='" + rs2.getString("ruang_rawat") + "' limit 1"),
                                        Sequel.cariIsi("SELECT k.kd_kamar from bangsal b inner join kamar k on k.kd_bangsal=b.kd_bangsal WHERE "
                                                + "b.nm_bangsal='" + rs2.getString("ruang_rawat") + "' limit 1"), rs2.getString("tgl_asuhan"), rs2.getString("no_rawat"),
                                        rs2.getString("jenis_asuhan"), rs2.getString("status_gizi"));
                            } else if (rs2.getString("jenis_asuhan").equals("Anak")) {
                                //kembalikan gizi buruk
                                cekGiziBuruk(rs2.getString("no_rawat"));
                                gantiGiziBuruk(rs2.getString("no_rawat"), rs2.getString("jenis_asuhan"), rs2.getString("stts_gizi_bbu"), rs2.getString("stts_gizi_pbu"),
                                        rs2.getString("stts_gizi_bbpb"), rs2.getString("bb"), TbbAkhir.getText(), rs2.getString("tb"), rs2.getString("ket_indek_bbu"),
                                        rs2.getString("ket_indek_bbpb"), rs2.getString("ket_indek_pbu"), ThitungZat.getText(), TdiagnosaDokter.getText(), Tpemberian.getText(),
                                        Sequel.cariIsi("select ifnull(perujuk,'') from rujuk_masuk where no_rawat='" + rs2.getString("no_rawat") + "'"),
                                        rs2.getString("tgl_asuhan"));
                            }

                            System.out.println("Proses mengembalikan data berhasil..!!");
                        }
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asuhan_gizi_ranap where ruang_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "' and no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            nmgedung = "";
            nmgedung = Sequel.cariIsi("SELECT nm_gedung from bangsal WHERE nm_bangsal='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "' limit 1");
            
            Sequel.meghapus2("detail_diagnosa_asuhan_gizi", "ruang_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString() + "' and no_rawat",
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            Sequel.meghapus("status_gizi_inap", "ruang_rawat='" + nmgedung + "' and no_rawat",
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void variabelBersih() {
        user = "";
        dataKonfirmasi = "";
        mual = "";
        nyeri = "";
        diare = "";
        kesulitan = "";
        odema = "";
        konstipasi = "";
        anoreksia = "";
        gangguan = "";
        makanlebih = "";
        makankurang = "";
        asupanCukup = "";
        asupanMenurun = "";
        asupanRendah = "";
        asupanTidak = "";
        alergi = "";
        pantangan = "";
        asupanmakan = "";
        antro = "";
        biokimia = "";
        klinis = "";
        lainlain = "";
        bbu = "";
        pbu = "";
        bbpb = "";
        kodekamar = "";
        nmgedung = "";
    }
    
    public void tampilDiagnosa() {
        jml = 0;
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        
        pilih = null;
        pilih = new boolean[jml];
        kode = null;
        kode = new String[jml];
        diagnosa = null;
        diagnosa = new String[jml];
        
        jml = 0;
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                pilih[jml] = true;
                kode[jml] = tbDiagnosa.getValueAt(i, 1).toString();
                diagnosa[jml] = tbDiagnosa.getValueAt(i, 2).toString();
                jml++;
            }
        }
        
        Valid.tabelKosong(tabMode2);
        for (i = 0; i < jml; i++) {
            tabMode2.addRow(new Object[]{pilih[i], kode[i], diagnosa[i]});
        }
        
        try {
            ps3 = koneksi.prepareStatement("select * from katalog_diagnosa_gizi where "
                    + "deskripsi_diagnosa like ? order by kd_diagnosa_gizi");
            try {
                ps3.setString(1, "%" + TCariDiagnosa.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs3.getString("kd_diagnosa_gizi"),
                        rs3.getString("deskripsi_diagnosa")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungIMT() {
        try {
            double A, B, C, D, Total;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }

            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }
            
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(Tbb.getText());
            B = Double.parseDouble(Ttb.getText());
            C = B / 100;
            D = C * C;

            Total = 0;
            Total = A / D;
            
            if (Valid.SetAngka4(Total).equals("NaN") || Tbb.getText().equals("") || Ttb.getText().equals("")) {
                Timt.setText("0");
            } else {
                Timt.setText(Valid.SetAngka4(Total));
            }
            cekStatusGizi();
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka berat badan & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
            Timt.setText("");
        }
    }
    
    private void hitungTBest() {
        try {
            String umurCek = "";
            double A, B, C, D, E, F, totLaki, totPerempuan;
            if (Ttl.getText().equals("")) {
                Ttl.setText("0");
            }

            if (Ttl.getText().contains(",") == true) {
                Ttl.setText(Ttl.getText().replaceAll(",", "."));
            }
            
            if (Tsttsumur.getText().equals("")) {
                umurCek = "0";
            } else {
                umurCek = Tumur.getText();
                if (umurCek.equals("")) {
                    umurCek = "0";
                }

                if (umurCek.contains(",") == true) {
                    umurCek = umurCek.replaceAll(",", ".");
                }
            }

            A = Double.parseDouble(Ttl.getText());
            B = Double.parseDouble(umurCek);
            
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : (2,02 x TL)-(0,04 x U) + 64,19
                C = 2.02 * A;
                D = 0.04 * B;
                totLaki = C - D + 64.19;

                if (Valid.SetAngka4(totLaki).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totLaki));
                }
            } else {
                // rumus perempuan : (1,83 x TL)-(0,24 x U) + 84,88
                E = 1.83 * A;
                F = 0.24 * B;
                totPerempuan = E - F + 84.88;
                
                if (Valid.SetAngka4(totPerempuan).equals("NaN")) {
                    TtbEst.setText("0");
                } else {
                    TtbEst.setText(Valid.SetAngka4(totPerempuan));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka tinggi lututnya, jika menggunakan    \n"
                    + "koma, gantilah tanda koma dengan titik sebagai komanya !!");
            TtbEst.setText("");
        }
    }
    
    private void hitungBBkoreksi() {
        try {
            double A, B, totBB;
            if (Tlila.getText().equals("")) {
                Tlila.setText("0");
            }

            if (Tlila.getText().contains(",") == true) {
                Tlila.setText(Tlila.getText().replaceAll(",", "."));
            }

            A = Double.parseDouble(Tlila.getText());
            
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : (2592 x LILA)-12902
                B = 2592 * A;
                totBB = B - 12902;
            } else {
                // rumus perempuan : (2001 x LILA) - 1223
                B = 2001 * A;
                totBB = B - 1223;
            }

            if (Valid.SetAngka4(totBB).equals("NaN")) {
                TbbKoreksi.setText("0");
            } else {
                TbbKoreksi.setText(Valid.SetAngka4(totBB));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka LILAnya, jika menggunakan koma,    \n"
                    + "gantilah tanda koma dengan titik sebagai komanya !!");
            TbbKoreksi.setText("");
        }
    }
    
    private void hitungHarisKalori() {
        try {
            String umurCek = "";
            double A, B, C, D, E, hasilBB, hasilTB, hasilUsia, totBMR, totKalori;
            // berat badan, tinggi badan nol, faktor aktivitas, faktor stres
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }
            
            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }
            
            if (Tfa.getText().equals("")) {
                Tfa.setText("0");
            }
            
            if (Tfs.getText().equals("")) {
                Tfs.setText("0");
            }

            // berat badan, tinggi badan nol, faktor aktivitas, faktor stres koma
            if (Tbb.getText().contains(",") == true) {
                Tbb.setText(Tbb.getText().replaceAll(",", "."));
            }
            
            if (Ttb.getText().contains(",") == true) {
                Ttb.setText(Ttb.getText().replaceAll(",", "."));
            }
            
            if (Tfa.getText().contains(",") == true) {
                Tfa.setText(Tfa.getText().replaceAll(",", "."));
            }
            
            if (Tfs.getText().contains(",") == true) {
                Tfs.setText(Tfs.getText().replaceAll(",", "."));
            }
            
            //umur dewasa
            if (Tsttsumur.getText().equals("")) {
                umurCek = "0";
            } else {
                umurCek = Tumur.getText();
                if (umurCek.equals("")) {
                    umurCek = "0";
                }

                if (umurCek.contains(",") == true) {
                    umurCek = umurCek.replaceAll(",", ".");
                }
            }
            
            A = Double.parseDouble(Tbb.getText());
            B = Double.parseDouble(Ttb.getText());
            C = Double.parseDouble(umurCek);
            D = Double.parseDouble(Tfa.getText());
            E = Double.parseDouble(Tfs.getText());
            
            //hitung bmr
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : 66 + (13,7 x BB) + (5 x TB) - (6,8 x USIA)
                hasilBB = 13.7 * A;
                hasilTB = 5 * B;
                hasilUsia = 6.8 * C;
                totBMR = 66 + hasilBB + hasilTB - hasilUsia;
            } else {
                // rumus perempuan : 655 + (9,6 x BB) + (1,8 x TB) - (4,7 x USIA)
                hasilBB = 9.6 * A;
                hasilTB = 1.8 * B;
                hasilUsia = 4.7 * C;
                totBMR = 655 + hasilBB + hasilTB - hasilUsia;
            }

            if (Valid.SetAngka2(totBMR).equals("NaN")) {
                Tbmr.setText("0");
            } else {
                Tbmr.setText(Valid.SetAngka2(totBMR));
            }
            
            //hitung kalori
            //rumus kalori : TEE = BMR x Fa x Fs
            totKalori = totBMR * D * E;
            if (Valid.SetAngka2(totKalori).equals("NaN")) {
                Tkalori.setText("0");
            } else {
                Tkalori.setText(Valid.SetAngka2(totKalori));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka yang dimasukkan, jika menggunakan koma,    \n"
                    + "gantilah tanda koma dengan titik sebagai komanya !!");
            Tkalori.setText("");
        }
    }
    
    private void hitungPerkeniKalori() {
        try {
            String umurCek = "";
            double A, B, C, D, E, hasilBMR, nilaiPerkeni, totKalori;
            //berat badan ideal, faktor aktivitas, faktor stres, faktor koreksi tubuh
            if (Tbbi.getText().equals("")) {
                Tbbi.setText("0");
            }
            
            if (Tfa.getText().equals("")) {
                Tfa.setText("0");
            }
            
            if (Tfs.getText().equals("")) {
                Tfs.setText("0");
            }
            
            if (Tfk.getText().equals("")) {
                Tfk.setText("0");
            }

            //berat badan ideal, faktor aktivitas, faktor stres, faktor koreksi tubuh koma
            if (Tbbi.getText().contains(",") == true) {
                Tbbi.setText(Tbbi.getText().replaceAll(",", "."));
            }
            
            if (Tfa.getText().contains(",") == true) {
                Tfa.setText(Tfa.getText().replaceAll(",", "."));
            }
            
            if (Tfs.getText().contains(",") == true) {
                Tfs.setText(Tfs.getText().replaceAll(",", "."));
            }
            
            if (Tfk.getText().contains(",") == true) {
                Tfk.setText(Tfk.getText().replaceAll(",", "."));
            }
            
            //umur dewasa
            if (Tsttsumur.getText().equals("")) {
                umurCek = "0";
            } else {
                umurCek = Tumur.getText();
                if (umurCek.equals("")) {
                    umurCek = "0";
                }

                if (umurCek.contains(",") == true) {
                    umurCek = umurCek.replaceAll(",", ".");
                }
            }

            A = Double.parseDouble(Tbbi.getText());
            B = Double.parseDouble(Tfa.getText());
            C = Double.parseDouble(Tfs.getText());
            D = Double.parseDouble(Tfk.getText());
            E = Double.parseDouble(umurCek);
            
            //hitung bmr
            if (Tjk.getText().equals("Laki-Laki")) {
                // rumus laki-laki : 30 KKAL x BBI
                hasilBMR = 30 * A;
            } else {
                // rumus perempuan : 25 KKAL X BBI
                hasilBMR = 25 * A;
            }

            if (Valid.SetAngka2(hasilBMR).equals("NaN")) {
                Tbmr.setText("0");
            } else {
                Tbmr.setText(Valid.SetAngka2(hasilBMR));
            }
            
            //hitung kalori
            //rumus kalori : TEE = BMR x Fa x Fs - Fu +/- F KOREKSI TUBUH
            nilaiPerkeni = hasilBMR * B * C - E;
            
            //jika status gizi dipilih -
            if (cmbSttsGizi.getSelectedIndex() == 0) {
                totKalori = 0;
            //jika status gizi normal
            } else if (cmbSttsGizi.getSelectedIndex() == 3) {
                totKalori = nilaiPerkeni;
            //jika status gizi obesitas
            } else if (cmbSttsGizi.getSelectedIndex() == 5) {
                totKalori = nilaiPerkeni - D;
            //jika status gizi selain obesitas & selain dipilih -
            } else {
                totKalori = nilaiPerkeni + D;
            }
            
            if (Valid.SetAngka2(totKalori).equals("NaN")) {
                Tkalori.setText("0");
            } else {
                Tkalori.setText(Valid.SetAngka2(totKalori));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka yang dimasukkan, jika menggunakan koma,    \n"
                    + "gantilah tanda koma dengan titik sebagai komanya !!");
            Tkalori.setText("");
        }
    }
    
    private void tampilRiwayatDiagnosa() {
        Valid.tabelKosong(tabMode3);
        try {
            ps7 = koneksi.prepareStatement("SELECT d.*, k.deskripsi_diagnosa, UPPER(d.status_data) stts, DATE_FORMAT(d.waktu_eksekusi,'%d/%m/%Y %H:%i:%s') tgl "
                    + "FROM detail_diagnosa_asuhan_gizi_histori d inner join katalog_diagnosa_gizi k on k.kd_diagnosa_gizi=d.kd_diagnosa_gizi where "
                    + "d.no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "' and "
                    + "d.waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "' order by k.kd_diagnosa_gizi");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode3.addRow(new String[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("kd_diagnosa_gizi"),
                        rs7.getString("deskripsi_diagnosa"),
                        rs7.getString("stts"),
                        rs7.getString("tgl")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cekStatusGizi() {
        if (cmbKlasifikasiIMT.getSelectedIndex() == 1) {
            try {
                double nilaiIMT;
                nilaiIMT = 0;
                if (Timt.getText().trim().contains(",") == true) {
                    Timt.setText(Timt.getText().trim().replaceAll(",", "."));         
                }
                
                if (Timt.getText().trim().equals("")) {
                    Timt.setText("0");
                }
                nilaiIMT = Double.parseDouble(Timt.getText());

                if (nilaiIMT < 18.5) {
                    //kurang
                    cmbSttsGizi.setSelectedIndex(2);
                } else if (nilaiIMT >= 18.5 && nilaiIMT <= 25) {
                    //normal
                    cmbSttsGizi.setSelectedIndex(3);
                } else if (nilaiIMT > 25) {
                    //obesitas
                    cmbSttsGizi.setSelectedIndex(5);
                } else {
                    //-
                    cmbSttsGizi.setSelectedIndex(0);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                cmbSttsGizi.setSelectedIndex(0);
            }
        } else if (cmbKlasifikasiIMT.getSelectedIndex() == 2) {
            try {
                double nilaiPersen;
                nilaiPersen = 0;

                if (Tpersentase.getText().trim().contains(",") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll(",", "."));
                }

                if (Tpersentase.getText().trim().contains("%") == true) {
                    Tpersentase.setText(Tpersentase.getText().trim().replaceAll("%", ""));
                }
                
                if (Tpersentase.getText().equals("")) {
                    Tpersentase.setText("0");
                }

                nilaiPersen = Double.parseDouble(Tpersentase.getText());

                if (nilaiPersen < 69.5) {
                    //gizi baik
                    cmbSttsGizi.setSelectedIndex(6);
                } else if (nilaiPersen >= 69.5 && nilaiPersen <= 89.4) {
                    //gizi kurang
                    cmbSttsGizi.setSelectedIndex(2);
                } else if (nilaiPersen >= 89.5 && nilaiPersen <= 110.4) {
                    //normal
                    cmbSttsGizi.setSelectedIndex(3);
                } else if (nilaiPersen >= 110.5 && nilaiPersen <= 120.4) {
                    //lebih
                    cmbSttsGizi.setSelectedIndex(4);
                } else if (nilaiPersen >= 120.5) {
                    //obesitas
                    cmbSttsGizi.setSelectedIndex(5);
                } else {
                    //-
                    cmbSttsGizi.setSelectedIndex(0);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                cmbSttsGizi.setSelectedIndex(0);
            }

        } else {
            cmbSttsGizi.setSelectedIndex(0);
        }
    }
    
    private void cekUlangStatusGiziDewasa(String imt, String dataIMT, String dataPersen) {
        sttsgizi = "";
        if (imt.equals("Berdasarkan Kemenkes RI")) {
            try {
                double nilaiIMT;
                nilaiIMT = 0;
                if (dataIMT.trim().contains(",") == true) {
                    dataIMT = dataIMT.trim().replaceAll(",", ".");                    
                }
                
                if (dataIMT.trim().equals("")) {
                    dataIMT = "0";
                }
                nilaiIMT = Double.parseDouble(dataIMT);

                if (nilaiIMT < 18.5) {
                    //kurang
                    sttsgizi = "Kurang";
                } else if (nilaiIMT >= 18.5 && nilaiIMT <= 25) {
                    //normal
                    sttsgizi = "Normal";
                } else if (nilaiIMT > 25) {
                    //obesitas
                    sttsgizi = "Obesitas";
                } else {
                    //-
                    sttsgizi = "-";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                sttsgizi = "-";
            }
        } else if (imt.equals("Berdasarkan CDC")) {
            try {
                double nilaiPersen;
                nilaiPersen = 0;

                if (dataPersen.trim().contains(",") == true) {
                    dataPersen = dataPersen.trim().replaceAll(",", ".");
                }

                if (dataPersen.trim().contains("%") == true) {
                    dataPersen = dataPersen.trim().replaceAll("%", "");
                }
                
                if (dataPersen.trim().equals("")) {
                    dataPersen = "0";
                }
                
                nilaiPersen = Double.parseDouble(dataPersen);

                if (nilaiPersen < 69.5) {
                    //gizi baik
                    sttsgizi = "Baik";
                } else if (nilaiPersen >= 69.5 && nilaiPersen <= 89.4) {
                    //gizi kurang
                    sttsgizi = "Kurang";
                } else if (nilaiPersen >= 89.5 && nilaiPersen <= 110.4) {
                    //normal
                    sttsgizi = "Normal";
                } else if (nilaiPersen >= 110.5 && nilaiPersen <= 120.4) {
                    //lebih
                    sttsgizi = "Lebih";
                } else if (nilaiPersen >= 120.5) {
                    //obesitas
                    sttsgizi = "Obesitas";
                } else {
                    //-
                    sttsgizi = "-";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                sttsgizi = "-";
            }

        } else {
            sttsgizi = "-";
        }
    }
    
    private void simpanSttsGiziDewasa() {
        //asuhan gizi dewasa
        if (cmbAsuhan.getSelectedIndex() == 1) {
            sttsgizi = "";
            if (cmbSttsGizi.getSelectedIndex() == 0) {
                sttsgizi = "-";
            } else if (cmbSttsGizi.getSelectedIndex() == 1) {
                sttsgizi = "BURUK";
            } else if (cmbSttsGizi.getSelectedIndex() == 2) {
                sttsgizi = "KURANG";
            } else if (cmbSttsGizi.getSelectedIndex() == 3 || cmbSttsGizi.getSelectedIndex() == 6) {
                sttsgizi = "NORMAL";
            } else if (cmbSttsGizi.getSelectedIndex() == 4) {
                sttsgizi = "LEBIH";
            } else if (cmbSttsGizi.getSelectedIndex() == 5) {
                sttsgizi = "OBESITAS";
            }

            Sequel.menyimpanIgnore("status_gizi_inap", "'" + TNoRw.getText() + "','" + Valid.SetTgl(tglAsuhan.getSelectedItem() + "") + "',"
                    + "'" + sttsgizi + "','" + nmgedung + "','" + kodekamar + "' ", "Data status gizi");
        } 
    }
    
    private void simpanGiziBuruk() {
        if (cmbAsuhan.getSelectedIndex() == 2) {
            if (cmbBbu.getSelectedIndex() == 1 || cmbBbu.getSelectedIndex() == 2
                    || cmbPbu.getSelectedIndex() == 1 || cmbPbu.getSelectedIndex() == 2
                    || cmbBbpb.getSelectedIndex() == 1 || cmbBbpb.getSelectedIndex() == 2) {
                Sequel.menyimpanIgnore("gizi_buruk", "'" + TNoRw.getText() + "','" + Tbb.getText() + "',"
                    + "'" + TbbAkhir.getText() + "','" + Ttb.getText() + "','" + TketBbu.getText() + "','" + TketBbpb.getText() + "',"
                    + "'" + TketPbu.getText() + "','" + ThitungZat.getText() + "','" + TdiagnosaDokter.getText() + "',"
                    + "'" + Tpemberian.getText() + "','','','','','" + Tfaskes.getText() + "',"
                    + "'" + Valid.SetTgl(tglAsuhan.getSelectedItem() + "") + "' ", "Data gizi buruk anak");
            }
        }
    }
    
    private void gantiSttsGiziDewasa(String gedung, String kamar, String tanggal, String norw, String jnsAsuhan, String sttsgz) {
        if (jnsAsuhan.equals("Dewasa")) {
            sttsgizi = "";
            if (sttsgz.equals("-")) {
                sttsgizi = "-";
            } else if (sttsgz.equals("Buruk")) {
                sttsgizi = "BURUK";
            } else if (sttsgz.equals("Kurang")) {
                sttsgizi = "KURANG";
            } else if (sttsgz.equals("Normal") || sttsgz.equals("Baik")) {
                sttsgizi = "NORMAL";
            } else if (sttsgz.equals("Lebih")) {
                sttsgizi = "LEBIH";
            } else if (sttsgz.equals("Obesitas")) {
                sttsgizi = "OBESITAS";
            }

            if (Sequel.cariInteger("select count(-1) from status_gizi_inap where no_rawat='" + norw + "'") > 0) {
                Sequel.mengedit("status_gizi_inap", "no_rawat='" + norw + "' and ruang_rawat='" + gedung + "'",
                        "status_gizi='" + sttsgizi + "', ruang_rawat='" + gedung + "', kd_kamar='" + kamar + "' ");
            } else {
                Sequel.menyimpanIgnore("status_gizi_inap", "'" + norw + "','" + tanggal + "',"
                    + "'" + sttsgizi + "','" + gedung + "','" + kamar + "' ", "Data status gizi");
            }
        }
    }
    
    private void gantiGiziBuruk(String norw, String jnsAsuhan, String bbu, String pbu, String bbpb, String bbAwal, 
            String bbAkhir, String tb, String nilaiBBU, String nilaiBBPB, String nilaiPBU, String zat, String diagnos,
            String pemberian, String faskes, String tanggal) {
        if (jnsAsuhan.equals("Anak")) {
            if (Sequel.cariInteger("select count(-1) from gizi_buruk where no_rawat='" + norw + "'") > 0) {
                if (bbu.equals("BB Sangat Kurang") || bbu.equals("BB Kurang")
                        || pbu.equals("Sangat Pendek") || pbu.equals("Pendek")
                        || bbpb.equals("Gizi Buruk") || bbpb.equals("Gizi Kurang")) {
                    Sequel.mengedit("gizi_buruk", "no_rawat='" + norw + "'", "bb_awal='" + bbAwal + "', "
                            + "bb_akhir='" + bbAkhir + "', pb_tb='" + tb + "', bb_u='" + nilaiBBU + "', "
                            + "bb_pb='" + nilaiBBPB + "', pb_u='" + nilaiPBU + "', penghitungan_zat_gizi='" + zat + "', "
                            + "diagnosa_dr_gizi='" + diagnos + "', pemberian_nutrisi='" + pemberian + "', "
                            + "asal_rujukan='" + faskes + "'");
                } else {
                    Sequel.meghapus("gizi_buruk", "no_rawat", norw);
                }
            } else {
                if (bbu.equals("BB Sangat Kurang") || bbu.equals("BB Kurang")
                        || pbu.equals("Sangat Pendek") || pbu.equals("Pendek")
                        || bbpb.equals("Gizi Buruk") || bbpb.equals("Gizi Kurang")) {
                    Sequel.menyimpanIgnore("gizi_buruk", "'" + norw + "','" + bbAwal + "',"
                            + "'" + bbAkhir + "','" + tb + "','" + nilaiBBU + "','" + nilaiBBPB + "',"
                            + "'" + nilaiPBU + "','" + zat + "','" + diagnos + "',"
                            + "'" + pemberian + "','','','','','" + faskes + "',"
                            + "'" + tanggal + "' ", "Data gizi buruk anak");
                }
            }
        }
    }
    
    private void tampilSGDewasa() {
        Valid.tabelKosong(tabMode4);
        try {
            ps8 = koneksi.prepareStatement("SELECT r.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + " DATE_FORMAT(sg.tgl_input,'%d/%m/%Y') tgl_input, sg.status_gizi, sg.ruang_rawat, sg.kd_kamar "
                    + " FROM status_gizi_inap sg INNER JOIN reg_periksa r ON r.no_rawat=sg.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=r.no_rkm_medis WHERE "
                    + " sg.tgl_input BETWEEN ? AND ? and sg.status_gizi like ? or "
                    + " sg.tgl_input BETWEEN ? AND ? and r.no_rkm_medis like ? or "
                    + " sg.tgl_input BETWEEN ? AND ? and sg.no_rawat like ? or "
                    + " sg.tgl_input BETWEEN ? AND ? and sg.ruang_rawat like ? or "
                    + " sg.tgl_input BETWEEN ? AND ? and p.nm_pasien like ? "
                    + " ORDER BY sg.tgl_input desc");
            try {
                ps8.setString(1, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps8.setString(2, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps8.setString(3, "%" + TCari1.getText().trim() + "%");
                ps8.setString(4, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps8.setString(5, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps8.setString(6, "%" + TCari1.getText().trim() + "%");
                ps8.setString(7, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps8.setString(8, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps8.setString(9, "%" + TCari1.getText().trim() + "%");
                ps8.setString(10, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps8.setString(11, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps8.setString(12, "%" + TCari1.getText().trim() + "%");
                ps8.setString(13, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps8.setString(14, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps8.setString(15, "%" + TCari1.getText().trim() + "%");
                rs8 = ps8.executeQuery();
                x = 1;
                while (rs8.next()) {
                    verifikasiStatusGizi(Sequel.cariIsi("select klasifikasi_imt from asuhan_gizi_ranap where no_rawat='" + rs8.getString(1) + "'"),
                            Sequel.cariIsi("select imt from asuhan_gizi_ranap where no_rawat='" + rs8.getString(1) + "'"),
                            Sequel.cariIsi("select if(persentase_cdc='','0',persentase_cdc) from asuhan_gizi_ranap where no_rawat='" + rs8.getString(1) + "'"));

                    if (rs8.getString(5).equals("-")) {
                        Sequel.mengedit("status_gizi_inap", "no_rawat='" + rs8.getString(1) + "' and ruang_rawat='" + rs8.getString(6) + "'", 
                                "status_gizi='" + sttsgizi + "'");
                    }
                    
                    tabMode4.addRow(new String[]{
                        x + ".",
                        rs8.getString(1),
                        rs8.getString(2),
                        rs8.getString(3),
                        rs8.getString(4),
                        rs8.getString(5),
                        rs8.getString(6),
                        rs8.getString(7)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilSGDewasa : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount2.setText("" + tabMode4.getRowCount());
    }
    
    private void tampilSGAnak() {
        Valid.tabelKosong(tabMode5);
        try {
            ps11 = koneksi.prepareStatement("SELECT ag.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(ag.tgl_asuhan,'%d/%m/%Y') tgl, "
                    + "if(ag.indek_bbu='ya',concat(ag.stts_gizi_bbu,' (',ag.ket_indek_bbu,')'),'') bbu, "
                    + "if(ag.indek_pbu='ya',concat(ag.stts_gizi_pbu,' (',ag.ket_indek_pbu,')'),'') pbu, "
                    + "if(ag.indek_bbpb='ya',concat(ag.stts_gizi_bbpb,' (',ag.ket_indek_bbpb,')'),'') bbpb, "
                    + "b.nm_gedung from asuhan_gizi_ranap ag inner join reg_periksa rp on rp.no_rawat=ag.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join bangsal b on b.nm_bangsal=ag.ruang_rawat WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and ag.stts_gizi_bbu like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and ag.stts_gizi_pbu like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and ag.stts_gizi_bbpb like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and p.no_rkm_medis like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and ag.no_rawat like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and b.nm_gedung like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and p.nm_pasien like ? "
                    + "ORDER BY ag.tgl_asuhan desc");
            try {
                ps11.setString(1, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(2, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(3, "%" + TCari3.getText().trim() + "%");
                ps11.setString(4, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(5, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(6, "%" + TCari3.getText().trim() + "%");
                ps11.setString(7, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(8, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(9, "%" + TCari3.getText().trim() + "%");
                ps11.setString(10, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(11, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(12, "%" + TCari3.getText().trim() + "%");
                ps11.setString(13, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(14, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(15, "%" + TCari3.getText().trim() + "%");
                ps11.setString(16, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(17, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(18, "%" + TCari3.getText().trim() + "%");
                ps11.setString(19, Valid.SetTgl(DTPCari7.getSelectedItem() + ""));
                ps11.setString(20, Valid.SetTgl(DTPCari8.getSelectedItem() + ""));
                ps11.setString(21, "%" + TCari3.getText().trim() + "%");
                rs11 = ps11.executeQuery();
                x = 1;
                while (rs11.next()) {
                    tabMode5.addRow(new String[]{
                        x + ".",
                        rs11.getString(1),
                        rs11.getString(2),
                        rs11.getString(3),
                        rs11.getString(4),
                        rs11.getString(5),
                        rs11.getString(6),
                        rs11.getString(7),
                        rs11.getString(8)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilSGAnak : " + e);
            } finally {
                if (rs11 != null) {
                    rs11.close();
                }
                if (ps11 != null) {
                    ps11.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount3.setText("" + tabMode5.getRowCount());
    }
    
    private void tampilPersentase() {
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }
        
        Valid.tabelKosong(tabMode6);
        try {
            ps14 = koneksi.prepareStatement("select * from (select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_asuhan, "
                    + "(a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_asuhan, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_tersasuhan, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_terasuhan from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulan + " and YEAR(ki.tgl_masuk)=" + Ttahun.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(ag.no_rawat) jlh_px_asuhan from asuhan_gizi_ranap ag "
                    + "inner join kamar_inap ki on ki.no_rawat=ag.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=ag.ruang_rawat "
                    + "WHERE MONTH(ag.tgl_asuhan)=" + angkaBulan + " and YEAR(ag.tgl_asuhan)=" + Ttahun.getText().trim() + " and "
                    + "ag.jenis_asuhan='Dewasa' GROUP BY month(ag.tgl_asuhan), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung) "
                    + "union ALL "
                    + "select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_asuhan, (a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_asuhan, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_tersasuhan, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_terasuhan from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulan + " and YEAR(ki.tgl_masuk)=" + Ttahun.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(ag.no_rawat) jlh_px_asuhan from asuhan_gizi_ranap ag "
                    + "inner join kamar_inap ki on ki.no_rawat=ag.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=ag.ruang_rawat "
                    + "WHERE MONTH(ag.tgl_asuhan)=" + angkaBulan + " and YEAR(ag.tgl_asuhan)=" + Ttahun.getText().trim() + " and "
                    + "ag.jenis_asuhan='Anak' GROUP BY month(ag.tgl_asuhan), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung");
            try {
                rs14 = ps14.executeQuery();
                x = 1;
                while (rs14.next()) {
                    tabMode6.addRow(new String[]{
                        x + ".",
                        rs14.getString(1),
                        rs14.getString(2),
                        rs14.getString(3),
                        rs14.getString(4),
                        rs14.getString(5),
                        rs14.getString(6)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilPersentase : " + e);
            } finally {
                if (rs14 != null) {
                    rs14.close();
                }
                if (ps14 != null) {
                    ps14.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void verifikasiStatusGizi(String imt, String dataIMT, String dataPersen) {
        sttsgizi = "";
        if (imt.equals("Berdasarkan Kemenkes RI")) {
            try {
                double nilaiIMT;
                nilaiIMT = 0;
                if (dataIMT.trim().contains(",") == true) {
                    dataIMT = dataIMT.trim().replaceAll(",", ".");                    
                }
                
                if (dataIMT.trim().equals("")) {
                    dataIMT = "0";
                }
                nilaiIMT = Double.parseDouble(dataIMT);

                if (nilaiIMT < 18.5) {
                    //kurang                    
                    sttsgizi = "KURANG";
                } else if (nilaiIMT >= 18.5 && nilaiIMT <= 25) {
                    //normal                    
                    sttsgizi = "NORMAL";
                } else if (nilaiIMT > 25) {
                    //obesitas
                    sttsgizi = "OBESITAS";
                } else {
                    //-
                    sttsgizi = "-";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                sttsgizi = "-";
            }
        } else if (imt.equals("Berdasarkan CDC")) {
            try {
                double nilaiPersen;
                nilaiPersen = 0;

                if (dataPersen.trim().contains(",") == true) {
                    dataPersen = dataPersen.trim().replaceAll(",", ".");                    
                }

                if (dataPersen.trim().contains("%") == true) {
                    dataPersen = dataPersen.trim().replaceAll("%", "");                    
                }
                
                nilaiPersen = Double.parseDouble(dataPersen);

                if (nilaiPersen < 69.5) {
                    //gizi baik
                    sttsgizi = "NORMAL";
                } else if (nilaiPersen >= 69.5 && nilaiPersen <= 89.4) {
                    //gizi kurang
                    sttsgizi = "KURANG";
                } else if (nilaiPersen >= 89.5 && nilaiPersen <= 110.4) {
                    //normal
                    sttsgizi = "NORMAL";
                } else if (nilaiPersen >= 110.5 && nilaiPersen <= 120.4) {
                    //lebih
                    sttsgizi = "LEBIH";
                } else if (nilaiPersen >= 120.5) {
                    //obesitas
                    sttsgizi = "OBESITAS";
                } else {
                    //-
                    sttsgizi = "-";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                sttsgizi = "-";
            }

        } else {
            sttsgizi = "-";
        }
    }
    
    public void tampilGiziBuruk() {     
        Valid.tabelKosong(tabMode7);
        try {
            ps15 = koneksi.prepareStatement("SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.asal_rujukan FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab where "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.no_rawat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.no_rkm_medis like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.nm_pasien like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.alamat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and rp.umurdaftar like ? or "                    
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_awal like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_akhir like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pb_tb like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.penghitungan_zat_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.diagnosa_dr_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pemberian_nutrisi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.asal_rujukan like ? ORDER BY gb.tgl_input DESC");
            try {
                ps15.setString(1, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(2, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(3, "%" + TCari4.getText().trim() + "%");
                ps15.setString(4, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(5, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(6, "%" + TCari4.getText().trim() + "%");
                ps15.setString(7, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(8, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(9, "%" + TCari4.getText().trim() + "%");
                ps15.setString(10, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(11, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(12, "%" + TCari4.getText().trim() + "%");
                ps15.setString(13, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(14, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(15, "%" + TCari4.getText().trim() + "%");
                ps15.setString(16, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(17, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(18, "%" + TCari4.getText().trim() + "%");
                ps15.setString(19, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(20, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(21, "%" + TCari4.getText().trim() + "%");
                ps15.setString(22, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(23, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(24, "%" + TCari4.getText().trim() + "%");
                ps15.setString(25, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(26, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(27, "%" + TCari4.getText().trim() + "%");
                ps15.setString(28, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(29, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(30, "%" + TCari4.getText().trim() + "%");
                ps15.setString(31, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(32, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(33, "%" + TCari4.getText().trim() + "%");
                ps15.setString(34, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                ps15.setString(35, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                ps15.setString(36, "%" + TCari4.getText().trim() + "%");
                rs15 = ps15.executeQuery();
                x = 1;
                while (rs15.next()) {
                    tabMode7.addRow(new String[]{
                        x + ".",
                        rs15.getString(1),
                        rs15.getString(2),
                        rs15.getString(3),
                        rs15.getString(4),
                        rs15.getString(5),
                        rs15.getString(6),
                        Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + rs15.getString(1) + "' limit 1"),
                        rs15.getString(7),
                        rs15.getString(8),
                        rs15.getString(9),
                        rs15.getString(10),
                        rs15.getString(11),
                        rs15.getString(12),
                        rs15.getString(13),
                        rs15.getString(14),
                        rs15.getString(15),                        
                        rs15.getString(16)
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
            } finally {
                if (rs15 != null) {
                    rs15.close();
                }
                if (ps15 != null) {
                    ps15.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount4.setText("" + tabMode7.getRowCount());
    }
    
    private void cekGiziBuruk(String norwt) {
        try {
            ps16 = koneksi.prepareStatement("select * from gizi_buruk where no_rawat like ?");
            try {
                ps16.setString(1, "%" + norwt.trim() + "%");
                rs16 = ps16.executeQuery();
                while (rs16.next()) {
                    ThitungZat.setText(rs16.getString("penghitungan_zat_gizi"));
                    TbbAkhir.setText(rs16.getString("bb_akhir"));
                    TdiagnosaDokter.setText(rs16.getString("diagnosa_dr_gizi"));
                    Tpemberian.setText(rs16.getString("pemberian_nutrisi"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs16 != null) {
                    rs16.close();
                }
                if (ps16 != null) {
                    ps16.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilBalita() {
        cekTampil = 0;
        Valid.tabelKosong(tabMode8);
        try {
            ps17 = koneksi.prepareStatement("SELECT ag.no_rawat, p.no_rkm_medis, p.nm_pasien, concat(TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi),' ','Bln.') umur, "
                    + "DATE_FORMAT(ag.tgl_asuhan,'%d/%m/%Y') tglAsuhan, ag.klasifikasi_imt, ag.status_gizi, "
                    + "if(ag.indek_bbu='ya',concat(ag.stts_gizi_bbu,' (',ag.ket_indek_bbu,')'),'') bbu, "
                    + "if(ag.indek_pbu='ya',concat(ag.stts_gizi_pbu,' (',ag.ket_indek_pbu,')'),'') pbu, "
                    + "if(ag.indek_bbpb='ya',concat(ag.stts_gizi_bbpb,' (',ag.ket_indek_bbpb,')'),'') bbpb, b.nm_gedung "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN bangsal b ON b.nm_bangsal = ag.ruang_rawat WHERE "                    
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and ag.status_gizi like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and ag.stts_gizi_bbu like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and ag.stts_gizi_pbu like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and ag.stts_gizi_bbpb like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and p.no_rkm_medis like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and ag.no_rawat like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and b.nm_gedung like ? or "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN ? AND ? and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 and p.nm_pasien like ? "
                    + "ORDER BY ag.tgl_asuhan desc");
            try {
                ps17.setString(1, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(2, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(3, "%" + TCari5.getText().trim() + "%");
                ps17.setString(4, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(5, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(6, "%" + TCari5.getText().trim() + "%");
                ps17.setString(7, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(8, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(9, "%" + TCari5.getText().trim() + "%");
                ps17.setString(10, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(11, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(12, "%" + TCari5.getText().trim() + "%");
                ps17.setString(13, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(14, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(15, "%" + TCari5.getText().trim() + "%");
                ps17.setString(16, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(17, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(18, "%" + TCari5.getText().trim() + "%");
                ps17.setString(19, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(20, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(21, "%" + TCari5.getText().trim() + "%");
                ps17.setString(22, Valid.SetTgl(DTPCari11.getSelectedItem() + ""));
                ps17.setString(23, Valid.SetTgl(DTPCari12.getSelectedItem() + ""));
                ps17.setString(24, "%" + TCari5.getText().trim() + "%");
                rs17 = ps17.executeQuery();
                x = 1;
                while (rs17.next()) {
                    tabMode8.addRow(new String[]{
                        x + ".",
                        rs17.getString(1),
                        rs17.getString(2),
                        rs17.getString(3),
                        rs17.getString(4),
                        rs17.getString(5),
                        rs17.getString(6),
                        rs17.getString(7),
                        rs17.getString(8),
                        rs17.getString(9),
                        rs17.getString(10),
                        rs17.getString(11)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilBalita : " + e);
            } finally {
                if (rs17 != null) {
                    rs17.close();
                }
                if (ps17 != null) {
                    ps17.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount5.setText("" + tabMode8.getRowCount());
        cekTampil = 1;
        tampilRekapBBU();
        tampilRekapPBU();
        tampilRekapBBPB();
    }
    
    private void tampilRekapBBU() {
        Valid.tabelKosong(tabMode9);
        try {
            ps18 = koneksi.prepareStatement("select a.stts_gizi_bbu, a.jlh_bbu, concat(ROUND(a.jlh_bbu/a.jml*100),' %') persen from ("
                    + "(SELECT if(ag.indek_bbu='ya',ag.stts_gizi_bbu,'-') stts_gizi_bbu, if(ag.indek_bbu='ya',count(ag.stts_gizi_bbu),'0') as jlh_bbu, (SELECT count(-1) "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61) as jml "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 "
                    + "GROUP BY ag.stts_gizi_bbu) as a)");
            try {
                rs18 = ps18.executeQuery();
                x = 1;
                while (rs18.next()) {
                    tabMode9.addRow(new String[]{
                        x + ".",
                        rs18.getString(1),
                        rs18.getString(2),
                        rs18.getString(3)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilRekapBBU : " + e);
            } finally {
                if (rs18 != null) {
                    rs18.close();
                }
                if (ps18 != null) {
                    ps18.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRekapPBU() {
        Valid.tabelKosong(tabMode10);
        try {
            ps19 = koneksi.prepareStatement("select a.stts_gizi_pbu, a.jlh_pbu, concat(ROUND(a.jlh_pbu/a.jml*100),' %') persen from ("
                    + "(SELECT if(ag.indek_pbu='ya',ag.stts_gizi_pbu,'-') stts_gizi_pbu, if(ag.indek_pbu='ya',count(ag.stts_gizi_pbu),'0') as jlh_pbu, (SELECT count(-1) "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61) as jml "
                    + "FROM  asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 "
                    + "GROUP BY ag.stts_gizi_pbu) as a)");
            try {
                rs19 = ps19.executeQuery();
                x = 1;
                while (rs19.next()) {
                    tabMode10.addRow(new String[]{
                        x + ".",
                        rs19.getString(1),
                        rs19.getString(2),
                        rs19.getString(3)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilRekapPBU : " + e);
            } finally {
                if (rs19 != null) {
                    rs19.close();
                }
                if (ps19 != null) {
                    ps19.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRekapBBPB() {
        Valid.tabelKosong(tabMode11);
        try {
            ps20 = koneksi.prepareStatement("select a.stts_gizi_bbpb, a.jlh_bbpb, concat(ROUND(a.jlh_bbpb/a.jml*100),' %') persen from ("
                    + "(SELECT if(ag.indek_bbpb='ya',ag.stts_gizi_bbpb,'-') stts_gizi_bbpb, if(ag.indek_bbpb='ya',count(ag.stts_gizi_bbpb),'0') as jlh_bbpb, (SELECT count(-1) "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 ) as jml "
                    + "FROM asuhan_gizi_ranap ag INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ag.jenis_asuhan='Anak' and ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari11.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari12.getSelectedItem() + "") + "' "
                    + "and TIMESTAMPDIFF(MONTH,p.tgl_lahir, rp.tgl_registrasi) <=61 "
                    + "GROUP BY ag.stts_gizi_bbpb) as a)");
            try {
                rs20 = ps20.executeQuery();
                x = 1;
                while (rs20.next()) {
                    tabMode11.addRow(new String[]{
                        x + ".",
                        rs20.getString(1),
                        rs20.getString(2),
                        rs20.getString(3)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilRekapBBPB : " + e);
            } finally {
                if (rs20 != null) {
                    rs20.close();
                }
                if (ps20 != null) {
                    ps20.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakKlasifikasiStatusGiziBalita() {
        this.setCursor(Cursor.getDefaultCursor());
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tanggal " + Valid.SetTglINDONESIA(Valid.SetTgl(DTPCari11.getSelectedItem() + "")) + " S.D " + Valid.SetTglINDONESIA(Valid.SetTgl(DTPCari12.getSelectedItem() + "")));

        Sequel.AutoComitFalse();
        Sequel.queryu("delete from temporary");
        int row = tabMode8.getRowCount();
        for (int r = 0; r < row; r++) {
            Sequel.menyimpan("temporary", "'0','"
                    + tabMode8.getValueAt(r, 0).toString() + "','"
                    + tabMode8.getValueAt(r, 1).toString() + "','"
                    + tabMode8.getValueAt(r, 2).toString() + "','"
                    + tabMode8.getValueAt(r, 3).toString() + "','"
                    + tabMode8.getValueAt(r, 4).toString() + "','"
                    + tabMode8.getValueAt(r, 5).toString() + "','"
                    + tabMode8.getValueAt(r, 6).toString() + "','"
                    + tabMode8.getValueAt(r, 7).toString() + "','"
                    + tabMode8.getValueAt(r, 8).toString() + "','"
                    + tabMode8.getValueAt(r, 9).toString() + "','"
                    + tabMode8.getValueAt(r, 10).toString() + "','"
                    + tabMode8.getValueAt(r, 11).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','',''", "Klasifikasi Status Gizi Pasien Balita");
        }

        //data rekap status gizi bbu
        cetakBBU = 0;
        for (i = 0; i < tbRekapBBU.getRowCount(); i++) {
            if (!tbRekapBBU.getValueAt(i, 0).toString().equals("")) {
                cetakBBU++;
            }
        }

        if (cetakBBU > 0) {
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Spasi");
            Sequel.menyimpan("temporary", "'0','','Rekap PerKlasifikasi Status Gizi Indeks (BB/U)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (BB/U)");
            Sequel.menyimpan("temporary", "'0','No.','Status Gizi','Jlh. Pasien','Persentase','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Judul Status Gizi Indeks (BB/U)");
            int data_bbu = tabMode9.getRowCount();
            for (int r = 0; r < data_bbu; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode9.getValueAt(r, 0).toString() + "','"
                        + tabMode9.getValueAt(r, 1).toString() + "','"
                        + tabMode9.getValueAt(r, 2).toString() + "','"
                        + tabMode9.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (BB/U)");
            }
        }

        //data rekap status gizi pbu
        cetakPBU = 0;
        for (i = 0; i < tbRekapPBU.getRowCount(); i++) {
            if (!tbRekapPBU.getValueAt(i, 0).toString().equals("")) {
                cetakPBU++;
            }
        }

        if (cetakPBU > 0) {
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Spasi");
            Sequel.menyimpan("temporary", "'0','','Rekap PerKlasifikasi Status Gizi Indeks (PB/U atau TB/U)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (PB/U atau TB/U)");
            Sequel.menyimpan("temporary", "'0','No.','Status Gizi','Jlh. Pasien','Persentase','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Judul Status Gizi Indeks (PB/U atau TB/U)");
            int data_pbu = tabMode10.getRowCount();
            for (int r = 0; r < data_pbu; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode10.getValueAt(r, 0).toString() + "','"
                        + tabMode10.getValueAt(r, 1).toString() + "','"
                        + tabMode10.getValueAt(r, 2).toString() + "','"
                        + tabMode10.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (PB/U atau TB/U)");
            }
        }

        //data rekap status gizi bbbu
        cetakBBPB = 0;
        for (i = 0; i < tbRekapBBPB.getRowCount(); i++) {
            if (!tbRekapBBPB.getValueAt(i, 0).toString().equals("")) {
                cetakBBPB++;
            }
        }

        if (cetakBBPB > 0) {
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Spasi");
            Sequel.menyimpan("temporary", "'0','','Rekap PerKlasifikasi Status Gizi Indeks (BB/PB atau BB/TB)','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (BB/PB atau BB/TB)");
            Sequel.menyimpan("temporary", "'0','No.','Status Gizi','Jlh. Pasien','Persentase','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Judul Status Gizi Indeks (BB/PB atau BB/TB)");
            int data_bbbu = tabMode11.getRowCount();
            for (int r = 0; r < data_bbbu; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode11.getValueAt(r, 0).toString() + "','"
                        + tabMode11.getValueAt(r, 1).toString() + "','"
                        + tabMode11.getValueAt(r, 2).toString() + "','"
                        + tabMode11.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Status Gizi Indeks (BB/PB atau BB/TB)");
            }
        }

        Sequel.AutoComitTrue();
        Valid.MyReport("rptKlasifikasiStatusGZBalita.jasper", "report", "::[ Klasifikasi Status Gizi Pasien Balita ]::",
                "select * from temporary", param);
        this.setCursor(Cursor.getDefaultCursor());

        tampilBalita();
        BtnKeluar6.requestFocus();
    }
}
