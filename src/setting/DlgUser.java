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

package setting;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariPoli;

/**
 *
 * @author perpustakaan
 */
public class DlgUser extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgUpdateUser personal=new DlgUpdateUser(null,false);
    private PreparedStatement ps, ps1;
    private ResultSet rs;
    private String user = "", jabatan = "", mencari, cari1 = "", cari2 = "",
            cari3 = "", cari4 = "", cari5 = "", cari6 = "", cari7 = "", cari8 = "";
    private int i = 0;

    /** Creates new form DlgUser
     * @param parent
     * @param modal */
    public DlgUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(706,674);

        Object[] row={"ID User","Nama User","Jabatan","Password","[I]ICD 10","[I]Obat Penyakit","[C]Dokter","[A]Jadwal Praktek","[C]Petugas","[L]Pasien","[A]Registrasi","[A]Tindakan Ralan",
                    "[A]Kamar Inap","[A]Tindakan Ranap","[A]Operasi","[A]Rujukan Keluar","[A]Rujukan Masuk","[A]Beri Obat, Alkes & BHP","[A]Resep Pulang",
                    "[L]Pasien Meninggal","[A]Diet Pasien","[L]Kelahiran Bayi","[A]Periksa Lab","[A]Periksa Radiologi","[A]Kasir Ralan",
                    "[J]Deposit Pasien","[J]Piutang Pasien","[L]Peminjaman Berkas RM","[C]Barcode Presensi","[C]Presensi Harian","[C]Presensi Bulanan",
                    "[C]Pegawai Admin","[C]Pegawai User","[D]Suplier Obat/Alkes/BHP","[D]Satuan Barang","[D]Konversi Satuan","[D]Jenis Obat/Alkes/BHP","[D]Obat, Alkes & BHP",
                    "[D]Stok Opname Apotek","[D]Stok Obat Pasien","[D]Pengadaan Obat, Alkes & BHP","[D]Pemesanan Obat, Alkes & BHP","[D]Penjualan Obat, Alkes & BHP","[D]Piutang Obat, Alkes & BHP",
                    "[D]Retur Ke Suplier","[D]Retur Dari Pembeli","[D]Retur Obat, Alkes & BHP Ranap","[D]Retur Piutang Pembeli","[D]Keuntungan Penjualan",
                    "[D]Keuntungan Beri Obat, Alkes & BHP","[D]Sirkulasi Obat, Alkes & BHP","[E]Barang Non Medis","[E]Pengadaan Barang Nonmedis","[E]Stok Keluar Non Medis",
                    "[E]Rekap Pengadaan Non Medis","[E]Rekap Stok Keluar Non Medis","[E]Biaya Pengadaan Non Medis","[F]Jenis Inventaris",
                    "[F]Kategori Inventaris","[F]Merk Inventaris","[F]Ruang Inventaris","[F]Produsen Inventaris","[F]Koleksi Inventaris",
                    "[F]Inventaris","[F]Sirkulasi Inventaris","[G]Jenis Parkir","[G]Parkir Masuk","[G]Parkir Keluar","[G]Rekap Parkir Harian",
                    "[G]Rekap Parkir Bulanan","[A]Informasi Kamar","[H]Harian Dokter Poli","[H]Obat Per Poli","[H]Obat Per Kamar",
                    "[H]Obat Per Dokter Ralan","[H]Obat Per Dokter Ranap","[H]Harian Dokter","[H]Bulanan Dokter","[H]Harian Paramedis",
                    "[H]Bulanan Paramedis","[H]Pembayaran Ralan","[H]Pembayaran Ranap","[H]Rekap Pembayaran Ralan","[H]Rekap Pembayaran Ranap",
                    "[H]Tagihan Masuk","[H]Tambahan Biaya","[H]Potongan Biaya","[A]No.Resep","[L]Riwayat Perawatan","[I]Frekuensi Penyakit Ralan","[I]Frekuensi Penyakit Ranap",
                    "[J]Kamar","[J]Tarif Ralan","[J]Tarif Ranap","[J]Tarif Lab","[J]Tarif Radiologi","[J]Tarif Operasi","[J]Akun Rekening","[J]Rekening Tahun",
                    "[J]Posting Jurnal","[J]Buku Besar","[J]Cash Flow","[J]Keuangan","[J]Pengeluaran Harian","[O]Set P.J. Unit Penunjang","[O]Set Oto Lokasi","[O]Set Kamar Inap",
                    "[O]Set Embalase & Tuslah","[O]Tracer Login","[O]Set Antrian","[O]Set Harga Obat","[O]Set Penggunaan Tarif","[O]Set Oto Ralan","[O]Biaya Harian Kamar",
                    "[O]Biaya Masuk Sekali","[O]Set RM","[A]Billing Ralan","[A]Billing Ranap","[H]Detail JM Dokter","[A]IGD","[B]Barcode Ralan","[B]Barcode Ranap",
                    "[O]Set Harga Obat Ralan","[O]Set Harga Obat Ranap","[I]Penyakit AFP & PD3I","[I]Surveilans AFP & PD3I","[I]Surveilans Ralan","[L]Diagnosa Pasien",
                    "[I]Surveilans Ranap","[I]Pny.Tdk Menular Ranap","[I]Pny.Tdk Menular Ralan","[I]Kunjungan Ralan","[I]RL 3.2 Rawat Darurat","[I]RL 3.3 Gigi dan Mulut","[I]RL 3.7 Radiologi","[I]RL 3.8 Laboratorium","[H]Harian Dokter Ralan",
                    "[C]SMS Gateway","[C]Sidik Jari","[C]Jam Presensi","[C]Jadwal Pegawai","[G]Barcode Parkir","[O]Set Billing","[A]DPJP Ranap","[D]Mutasi Obat/Alkes/BHP","[I]RL 3.4 Kebidanan","[I]RL 3.6 Pembedahan",
                    "[H]Fee Visit Dokter","[H]Fee Bacaan EKG","[H]Fee Rujukan Rontgen","[H]Fee Rujukan Ranap","[H]Fee Periksa Ralan","[J]Akun Bayar","[J]Bayar Pesan Obat",
                    "[H]Obat Per Dokter Peresep","[E]Jenis Non Medis","[J]Pemasukkan Lain-Lain","[J]Pengaturan Rekening","[O]Closing Kasir","[O]Set Keterlambatan Presensi",
                    "[O]Set Harga Kamar","[H]Rekap Per Shift","[K]Cek NIK","[K]Cek No.Kartu","[K]Riwayat Peserta","[H]Obat Per Cara Bayar","[I]Kunjungan Ranap",
                    "[J]Bayar Piutang","[H]Payment Point","[K]Cek No.Rujukan Pcare","[I]ICD 9","[D]Darurat Stok","[L]Retensi Data R.M.","[C]Temporary Presensi",
                    "[J]Jurnal Harian","[D]Sirkulasi Obat, Alkes & BHP 2","[A]Edit Registrasi","[K]Referensi Diagnosa BPJS","[K]Referensi Poli BPJS","[D]Industri Farmasi",
                    "[H]Harian J.S.","[H]Bulanan J.S.","[H]Harian BHP Medis/Paket Obat","[H]Bulanan BHP Medis/Paket Obat","[J]Piutang Belum Lunas","[K]Referensi Faskes BPJS",
                    "[K]Data Bridging SEP BPJS","[D]Pengambilan BHP UTD","[J]Tarif UTD","[M]Pengambilan BHP Medis","[M]BHP Medis Rusak","[E]Pengambilan UTD","[M]Pengambilan BHP Non Medis",
                    "[M]BHP Non Medis Rusak","[E]Suplier Non Medis","[M]Donor Darah","[K]Monitoring Verifikasi Klaim","[M]Cekal Darah","[M]Komponen Darah","[M]Stok Darah","[M]Pemisahan Darah",
                    "[H]Harian Kamar","[J]Rincian Piutang Pasien","[D]Keuntungan Beri Obat, Alkes & BHP 2","[K]Reklasifikasi Ralan","[K]Reklasifikasi Ranap","[M]Penyerahan Darah",
                    "[J]Hutang Obat & BHP","[D]Riwayat Obat, Alkes & BHP","[I]Sensus Harian Poli","[I]RL 4A Sebab Morbiditas Ranap","[K]Referensi Kamar Aplicare","[K]Ketersediaan Kamar Aplicare",
                    "[K]Klaim Baru Otomatis INACBG","[K]Klaim Baru Manual INACBG","[K]Coder NIK INACBG","[L]Mutasi Berkas RM","[J]Akun Piutang","[H]Harian KSO","[H]Bulanan KSO",
                    "[H]Harian Menejemen","[H]Bulanan Menejemen","[K]Cek Eligibilitas Inhealth","[K]Referensi Ruang Rawat Inhealth","[K]Referensi Poli Inhealth","[K]Referensi Faskes Inhealth",
                    "[K]Data Bridging SJP Inhealth","[H]Piutang Ralan","[H]Piutang Ranap","[J]Piutang Per Cara Bayar","[I]Lama Pelayanan Ralan","[L]Catatan Pasien","[I]RL 4B Sebab Morbiditas Ralan",
                    "[I]RL 4A Morbiditas Ralan","[I]RL 4B Morbiditas Ralan","[L]Data HAIs","[I]Harian HAIs","[I]Bulanan HAIs","[I]Hitung BOR","[L]Instansi/Perusahaan Pasien","[D]Resep Dokter",
                    "[I]Lama Pelayanan Apotek","[I]Hitung ALOS","[H]Detail Tindakan","[A]Rujukan Poli Internal","[H]Rekap Poli Anak","[N]Kunjungan Reg Per Poli","[N]Kunjungan Reg Per Dokter",
                    "[N]Kunjungan Reg Per Pekerjaan","[N]Kunjungan Reg Per Pendidikan","[N]Kunjungan Reg Per Tahun","[L]Berkas Digital Perawatan","[I]Pny Menular Ranap","[I]Pny Menular Ralan",
                    "[N]Kunjungan Reg Per Bulan","[N]Kunjungan Reg Per Tanggal","[N]Demografi Registrasi","[N]Reg Lama Per Tahun","[N]Reg Baru Per Tahun","[N]Reg Lama Per Bulan","[N]Reg Baru Per Bulan",
                    "[N]Reg Lama Per Tanggal","[N]Reg Baru Per Tanggal","[N]Batal Periksa Per Tahun","[N]Batal Periksa Per Bulan","[K]Referensi Diagnosa Pcare","[N]Batal Periksa Per Tanggal",
                    "[D]Kategori Obat/Alkes/BHP","[D]Golongan Obat/Alkes/BHP","[D]Obat/Alkes/BHP Per Tanggal","[D]Penjualan Bebas Per Tanggal","[K]SKDP BPJS","[K]Rujukan Keluar VClaim BPJS",
                    "[A]Booking Registrasi","[K]Riwayat Rujukan Pcare VClaim","[K]Riwayat Rujukan RS VClaim","[K]Cek Rujukan RS BPJS Sesuai No.Kartu","[K]Cek Tgl. Rujukan VClaim",
                    "[K]Cek No.Rujukan RS VClaim","[K]Cek Rujukan Kartu Pcare VClaim","[K]Cek Referensi Kelas Rawat VClaim","[K]Cek Referensi Prosedur VClaim","[K]Cek Referensi DPJP VClaim",
                    "[K]Cek Referensi Dokter BPJS","[K]Cek Referensi Spesialistik VClaim","[K]Cek Referensi Ruang Rawat VClaim","[K]Cek Referensi Cara Keluar VClaim","[K]Cek Referensi Pasca Pulang VClaim",
                    "[K]Cek Referensi Provinsi VClaim","[K]Cek Referensi Kabupaten VClaim","[K]Cek Referensi Kecamatan VClaim","[A]Permintaan Periksa Lab.","[A]Permintaan Periksa Radiologi",
                    "[A]Selisih Tarif Naik Kls. Rawat BPJS","[A]Edit Data Kematian","[A]Bridging SEP Jamkesda","[A]Masuk, Pindah, Pulang (Ranap)","[A]Masuk & Pindah (Ranap)",
                    "[I]Jumlah Macam Diet","[I]Jumlah Porsi Diet","[I]Status Gizi","[I]Gizi Buruk","[A]Master Faskes/Perujuk","[A]Set Status Reg. Ralan","[I]Telusur Kunjungan Pasien",
                    "[K]Sisrute Rujukan Keluar","[K]Sisrute Rujukan Masuk","[K]Sisrute Referensi Diagnosa","[K]Sisrute Referensi Alasan Rujuk","[K]Sisrute Referensi Faskes","[F]Barang CSSD",
                    "[A]Status Pulang Ranap","[L]Data Persalinan","[L]Data Ponek","[A]Registrasi Booking Dikasir","[A]Suku Pasien","[A]Bahasa Pasien","[I]Harian HAIs Ranap","[I]Bulanan HAIs Ranap",
                    "[I]Harian HAIs Ralan","[I]Bulanan HAIs Ralan","[L]Ringkasan Pulang Ranap","[D]Laporan Farmasi","[L]Master Masalah Keperawatan","[L]Penilaian Awal Keperawatan Ralan",
                    "[L]Master Triase Skala 1","[L]Master Triase Skala 2","[L]Master Triase Skala 3","[L]Master Triase Skala 4","[L]Master Triase Skala 5","[L]Master Triase Macam Kasus",
                    "[L]Master Triase Pemeriksaan","[L]Rekam Medis Triase IGD","[L]Master Cara Bayar","[C]Status Kerja Dokter","[K]Bridging Pasien Corona","[K]Bridging Diagnosa Pasien Corona",
                    "[K]Perawatan Pasien Corona", "[K]Klaim Baru Manual INACBG 2","[I]Indikator Ranap","[A]Sensus Ranap","[L]Review RM IGD","[L]Review RM Ranap H+1","[L]Review RM Ranap Pulang",
                    "[L]Review RM Laporan","[A]Assesment Gizi Harian","[A]Assesment Gizi Ulang","[O]Tombol Nota Billing","[O]Tombol Simpan Hasil Rad.","[A]Monitoring Evaluasi Asuhan Gizi",
                    "[K]Bridging Eklaim INACBG RAZA","[K]Pengajuan Klaim INACBG RAZA","[A]Copy Pemeriksaan Dokter Ke Perawat/Bidan","[K]INACBG JKN Belum Diklaim","[L]Input Kode ICD",
                    "[L]Indikator Mutu Unit","[K]Kendali Mutu Kendali Biaya INACBG","[D]Dashboard e-Resep","[K]Cek SEP Internal BPJS","[K]Kemenkes SITB","[K]Rencana Kontrol JKN","[K]SPRI JKN",
                    "[K]Hapus SEP","[L]Penilaian Awal Medis Kebidanan & Kandungan Ralan","[L]Penilaian Awal Keperawatan Kebidanan Ralan","[L]Ikhtisar Perawatan HIV & Terapi ART",
                    "[L]Survey Kepuasan","[K]Kemenkes Kanker","[O]Set Bridging","[O]Operator Antrian","[L]Penilaian Awal Medis Ralan THT","[I]Rekam Psikologis",
                    "[L]Penilaian Awal Medis & Tambahan Pasien Geriatri","[L]Penilaian Awal Medis Ralan Mata","[L]Surat Sakit","[L]Surat Keterangan KIR/MCU","[L]Asesmen Medik Dewasa Ranap",
                    "[D]Pemberian Obat Pasien","[L]CPPT","[K]Bridging SatuSehat","[L]Kemoterapi","[J]Cek Piutang","[L]Asesmen Medik Anak Ranap", "[B]Checklist Pra Operasi",
                    "[L]Asesmen Medik Bedah Ranap"
        };
        
        tabMode=new DefaultTableModel(null,row){
              @Override 
              public boolean isCellEditable(int rowIndex, int colIndex){
                  boolean a = true;
                    if ((colIndex==0)||(colIndex==1)||(colIndex==2) ||(colIndex==3)) {
                        a=false;
                    }
                    return a;
              }              
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, 
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbUser.setModel(tabMode);
        tbUser.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 385; i++) {
            TableColumn column = tbUser.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(180);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(55);
            } else if (i == 5) {
                column.setPreferredWidth(88);
            } else if (i == 6) {
                column.setPreferredWidth(55);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(63);
            } else if (i == 9) {
                column.setPreferredWidth(53);
            } else if (i == 10) {
                column.setPreferredWidth(73);
            } else if (i == 11) {
                column.setPreferredWidth(95);
            } else if (i == 12) {
                column.setPreferredWidth(84);
            } else if (i == 13) {
                column.setPreferredWidth(103);
            } else if (i == 14) {
                column.setPreferredWidth(66);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(135);
            } else if (i == 18) {
                column.setPreferredWidth(94);
            } else if (i == 19) {
                column.setPreferredWidth(110);
            } else if (i == 20) {
                column.setPreferredWidth(82);
            } else if (i == 21) {
                column.setPreferredWidth(93);
            } else if (i == 22) {
                column.setPreferredWidth(82);
            } else if (i == 23) {
                column.setPreferredWidth(107);
            } else if (i == 24) {
                column.setPreferredWidth(81);
            } else if (i == 25) {
                column.setPreferredWidth(95);
            } else if (i == 26) {
                column.setPreferredWidth(95);
            } else if (i == 27) {
                column.setPreferredWidth(135);
            } else if (i == 28) {
                column.setPreferredWidth(110);
            } else if (i == 29) {
                column.setPreferredWidth(103);
            } else if (i == 30) {
                column.setPreferredWidth(108);
            } else if (i == 31) {
                column.setPreferredWidth(100);
            } else if (i == 32) {
                column.setPreferredWidth(92);
            } else if (i == 33) {
                column.setPreferredWidth(136);
            } else if (i == 34) {
                column.setPreferredWidth(98);
            } else if (i == 35) {
                column.setPreferredWidth(105);
            } else if (i == 36) {
                column.setPreferredWidth(87);
            } else if (i == 37) {
                column.setPreferredWidth(114);
            } else if (i == 38) {
                column.setPreferredWidth(127);
            } else if (i == 39) {
                column.setPreferredWidth(110);
            } else if (i == 40) {
                column.setPreferredWidth(170);
            } else if (i == 41) {
                column.setPreferredWidth(170);
            } else if (i == 42) {
                column.setPreferredWidth(163);
            } else if (i == 43) {
                column.setPreferredWidth(153);
            } else if (i == 44) {
                column.setPreferredWidth(101);
            } else if (i == 45) {
                column.setPreferredWidth(115);
            } else if (i == 46) {
                column.setPreferredWidth(178);
            } else if (i == 230) {
                column.setPreferredWidth(125);
            } else if (i == 231) {
                column.setPreferredWidth(100);
            } else if (i == 232) {
                column.setPreferredWidth(165);
            } else if (i == 233) {
                column.setPreferredWidth(133);
            } else if (i == 234) {
                column.setPreferredWidth(133);
            } else if (i == 235) {
                column.setPreferredWidth(73);
            } else if (i == 236) {
                column.setPreferredWidth(80);
            } else if (i == 237) {
                column.setPreferredWidth(82);
            } else if (i == 238) {
                column.setPreferredWidth(75);
            } else if (i == 239) {
                column.setPreferredWidth(155);
            } else if (i == 240) {
                column.setPreferredWidth(90);
            } else if (i == 241) {
                column.setPreferredWidth(135);
            } else if (i == 242) {
                column.setPreferredWidth(80);
            } else if (i == 243) {
                column.setPreferredWidth(96);
            } else if (i == 244) {
                column.setPreferredWidth(120);
            } else if (i == 245) {
                column.setPreferredWidth(98);
            } else if (i == 246) {
                column.setPreferredWidth(133);
            } else if (i == 247) {
                column.setPreferredWidth(149);
            } else if (i == 248) {
                column.setPreferredWidth(165);
            } else if (i == 249) {
                column.setPreferredWidth(167);
            } else if (i == 250) {
                column.setPreferredWidth(147);
            } else if (i == 251) {
                column.setPreferredWidth(139);
            } else if (i == 252) {
                column.setPreferredWidth(110);
            } else if (i == 253) {
                column.setPreferredWidth(107);
            } else if (i == 254) {
                column.setPreferredWidth(142);
            } else if (i == 255) {
                column.setPreferredWidth(155);
            } else if (i == 256) {
                column.setPreferredWidth(120);
            } else if (i == 257) {
                column.setPreferredWidth(120);
            } else if (i == 258) {
                column.setPreferredWidth(116);
            } else if (i == 259) {
                column.setPreferredWidth(116);
            } else if (i == 260) {
                column.setPreferredWidth(113);
            } else if (i == 261) {
                column.setPreferredWidth(128);
            } else if (i == 262) {
                column.setPreferredWidth(125);
            } else if (i == 263) {
                column.setPreferredWidth(134);
            } else if (i == 264) {
                column.setPreferredWidth(130);
            } else if (i == 265) {
                column.setPreferredWidth(143);
            } else if (i == 266) {
                column.setPreferredWidth(142);
            } else if (i == 267) {
                column.setPreferredWidth(140);
            } else if (i == 268) {
                column.setPreferredWidth(144);
            } else if (i == 269) {
                column.setPreferredWidth(157);
            } else if (i == 270) {
                column.setPreferredWidth(160);
            } else if (i == 271) {
                column.setPreferredWidth(80);
            } else if (i == 272) {
                column.setPreferredWidth(160);
            } else if (i == 273) {
                column.setPreferredWidth(120);
            } else if (i == 274) {
                column.setPreferredWidth(170);
            } else if (i == 275) {
                column.setPreferredWidth(160);
            } else if (i == 276) {
                column.setPreferredWidth(210);
            } else if (i == 277) {
                column.setPreferredWidth(150);
            } else if (i == 278) {
                column.setPreferredWidth(150);
            } else if (i == 279) {
                column.setPreferredWidth(177);
            } else if (i == 280) {
                column.setPreferredWidth(187);
            } else if (i == 281) {
                column.setPreferredWidth(175);
            } else if (i == 282) {
                column.setPreferredWidth(150);
            } else if (i == 283) {
                column.setPreferredWidth(150);
            } else if (i == 284) {
                column.setPreferredWidth(180);
            } else if (i == 285) {
                column.setPreferredWidth(190);
            } else if (i == 286) {
                column.setPreferredWidth(181);
            } else if (i == 287) {
                column.setPreferredWidth(188);
            } else if (i == 288) {
                column.setPreferredWidth(165);
            } else if (i == 289) {
                column.setPreferredWidth(180);
            } else if (i == 290) {
                column.setPreferredWidth(180);
            } else if (i == 291) {
                column.setPreferredWidth(140);
            } else if (i == 292) {
                column.setPreferredWidth(160);
            } else if (i == 293) {
                column.setPreferredWidth(185);
            } else if (i == 294) {
                column.setPreferredWidth(120);
            } else if (i == 295) {
                column.setPreferredWidth(135);
            } else if (i == 296) {
                column.setPreferredWidth(170);
            } else if (i == 297) {
                column.setPreferredWidth(140);
            } else if (i == 298) {
                column.setPreferredWidth(120);
            } else if (i == 299) {
                column.setPreferredWidth(100);
            } else if (i == 300) {
                column.setPreferredWidth(80);
            } else if (i == 301) {
                column.setPreferredWidth(70);
            } else if (i == 302) {
                column.setPreferredWidth(135);
            } else if (i == 303) {
                column.setPreferredWidth(130);
            } else if (i == 304) {
                column.setPreferredWidth(150);
            } else if (i == 305) {
                column.setPreferredWidth(130);
            } else if (i == 306) {
                column.setPreferredWidth(130);
            } else if (i == 307) {
                column.setPreferredWidth(150);
            } else if (i == 308) {
                column.setPreferredWidth(170);
            } else if (i == 309) {
                column.setPreferredWidth(140);
            } else if (i == 310) {
                column.setPreferredWidth(85);
            } else if (i == 311) {
                column.setPreferredWidth(120);
            } else if (i == 312) {
                column.setPreferredWidth(100);
            } else if (i == 313) {
                column.setPreferredWidth(90);
            } else if (i == 314) {
                column.setPreferredWidth(150);
            } else if (i == 315) {
                column.setPreferredWidth(90);
            } else if (i == 316) {
                column.setPreferredWidth(90);
            } else if (i == 317) {
                column.setPreferredWidth(120);
            } else if (i == 318) {
                column.setPreferredWidth(120);
            } else if (i == 319) {
                column.setPreferredWidth(120);
            } else if (i == 320) {
                column.setPreferredWidth(120);
            } else if (i == 321) {
                column.setPreferredWidth(150);
            } else if (i == 322) {
                column.setPreferredWidth(100);
            } else if (i == 323) {
                column.setPreferredWidth(170);
            } else if (i == 324) {
                column.setPreferredWidth(200);
            } else if (i == 325) {
                column.setPreferredWidth(130);
            } else if (i == 326) {
                column.setPreferredWidth(130);
            } else if (i == 327) {
                column.setPreferredWidth(130);
            } else if (i == 328) {
                column.setPreferredWidth(130);
            } else if (i == 329) {
                column.setPreferredWidth(130);
            } else if (i == 330) {
                column.setPreferredWidth(170);
            } else if (i == 331) {
                column.setPreferredWidth(160);
            } else if (i == 332) {
                column.setPreferredWidth(140);
            } else if (i == 333) {
                column.setPreferredWidth(110);
            } else if (i == 334) {
                column.setPreferredWidth(125);
            } else if (i == 335) {
                column.setPreferredWidth(130);
            } else if (i == 336) {
                column.setPreferredWidth(180);
            } else if (i == 337) {
                column.setPreferredWidth(150);
            } else if (i == 338) {
                column.setPreferredWidth(160);
            } else if (i == 339) {
                column.setPreferredWidth(100);
            } else if (i == 340) {
                column.setPreferredWidth(100);
            } else if (i == 341) {
                column.setPreferredWidth(100);
            } else if (i == 342) {
                column.setPreferredWidth(130);
            } else if (i == 343) {
                column.setPreferredWidth(143);
            } else if (i == 344) {
                column.setPreferredWidth(118);
            } else if (i == 345) {
                column.setPreferredWidth(130);
            } else if (i == 346) {
                column.setPreferredWidth(130);
            } else if (i == 347) {
                column.setPreferredWidth(127);
            } else if (i == 348) {
                column.setPreferredWidth(140);
            } else if (i == 349) {
                column.setPreferredWidth(180);
            } else if (i == 350) {
                column.setPreferredWidth(165);
            } else if (i == 351) {
                column.setPreferredWidth(170);
            } else if (i == 352) {
                column.setPreferredWidth(235);
            } else if (i == 353) {
                column.setPreferredWidth(150);
            } else if (i == 354) {
                column.setPreferredWidth(100);
            } else if (i == 355) {
                column.setPreferredWidth(115);
            } else if (i == 356) {
                column.setPreferredWidth(200);
            } else if (i == 357) {
                column.setPreferredWidth(120);
            } else if (i == 358) {
                column.setPreferredWidth(130);
            } else if (i == 359) {
                column.setPreferredWidth(100);
            } else if (i == 360) {
                column.setPreferredWidth(135);
            } else if (i == 361) {
                column.setPreferredWidth(75);
            } else if (i == 362) {
                column.setPreferredWidth(75);
            } else if (i == 363) {
                column.setPreferredWidth(280);
            } else if (i == 364) {
                column.setPreferredWidth(250);
            } else if (i == 365) {
                column.setPreferredWidth(200);
            } else if (i == 366) {
                column.setPreferredWidth(110);
            } else if (i == 367) {
                column.setPreferredWidth(110);
            } else if (i == 368) {
                column.setPreferredWidth(90);
            } else if (i == 369) {
                column.setPreferredWidth(110);
            } else if (i == 370) {
                column.setPreferredWidth(170);
            } else if (i == 371) {
                column.setPreferredWidth(100);
            } else if (i == 372) {
                column.setPreferredWidth(270);
            } else if (i == 373) {
                column.setPreferredWidth(180);
            } else if (i == 374) {
                column.setPreferredWidth(80);
            } else if (i == 375) {
                column.setPreferredWidth(160);
            } else if (i == 376) {
                column.setPreferredWidth(180);
            } else if (i == 377) {
                column.setPreferredWidth(150);
            } else if (i == 378) {
                column.setPreferredWidth(50);
            } else if (i == 379) {
                column.setPreferredWidth(120);
            } else if (i == 380) {
                column.setPreferredWidth(80);
            } else if (i == 381) {
                column.setPreferredWidth(80);
            } else if (i == 382) {
                column.setPreferredWidth(160);
            } else if (i == 383) {
                column.setPreferredWidth(135);
            } else if (i == 384) {
                column.setPreferredWidth(164);
            } else {
                column.setPreferredWidth(130);
            }
        }
        tbUser.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"KD Baris", "NIP/Kode User", "Nama User", 
            "Kode Unit", "Nama Unit/Poli/Ruang"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbUser1.setModel(tabMode1);
        tbUser1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbUser1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbUser1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50);
            } else if (i == 1) {
                column.setPreferredWidth(155);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            }
        }
        tbUser1.setDefaultRenderer(Object.class, new WarnaTable());        

        TKd.setDocument(new batasInput((byte)30).getKata(TKd));
        TPass.setDocument(new batasInput((byte)50).getKata(TPass));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));       
                
        dlgpetugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dlgpetugas.getTable().getSelectedRow()!= -1){                   
                    TKd.setText(dlgpetugas.getTable().getValueAt(dlgpetugas.getTable().getSelectedRow(),11).toString());
                    TNmUser.setText(dlgpetugas.getTable().getValueAt(dlgpetugas.getTable().getSelectedRow(),1).toString());
                    TPass.setText(dlgpetugas.getTable().getValueAt(dlgpetugas.getTable().getSelectedRow(),0).toString());
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    nmpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                }                      
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {poli.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        TKd.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                isUser();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isUser();
            }
        });
    }
    
    DlgCariPetugas dlgpetugas=new DlgCariPetugas(null,false);
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do falseT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kodeBaris = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSetUser = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TKd = new widget.TextBox();
        TNmUser = new widget.TextBox();
        BtnSeek1 = new widget.Button();
        jLabel4 = new widget.Label();
        TPass = new widget.TextBox();
        BtnKeluar = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        TabAkses = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbUser = new widget.Table();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbUser1 = new widget.Table();
        panelisi2 = new widget.panelisi();
        cekUnit = new widget.CekBox();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        NmRuangan = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel10 = new widget.Label();
        cmbSemua = new widget.ComboBox();
        panelGlass7 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();

        kodeBaris.setForeground(new java.awt.Color(0, 0, 0));
        kodeBaris.setHighlighter(null);
        kodeBaris.setName("kodeBaris"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSetUser.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSetUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/loginorg.png"))); // NOI18N
        MnSetUser.setText("Set Personal");
        MnSetUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSetUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSetUser.setName("MnSetUser"); // NOI18N
        MnSetUser.setPreferredSize(new java.awt.Dimension(125, 26));
        MnSetUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSetUserActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSetUser);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup User ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Dokter/Petugas :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass5.add(jLabel3);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N
        TKd.setPreferredSize(new java.awt.Dimension(200, 24));
        TKd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdKeyPressed(evt);
            }
        });
        panelGlass5.add(TKd);

        TNmUser.setEditable(false);
        TNmUser.setForeground(new java.awt.Color(0, 0, 0));
        TNmUser.setName("TNmUser"); // NOI18N
        TNmUser.setPreferredSize(new java.awt.Dimension(300, 24));
        panelGlass5.add(TNmUser);

        BtnSeek1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek1.setMnemonic('2');
        BtnSeek1.setToolTipText("Alt+2");
        BtnSeek1.setName("BtnSeek1"); // NOI18N
        BtnSeek1.setPreferredSize(new java.awt.Dimension(28, 26));
        BtnSeek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek1ActionPerformed(evt);
            }
        });
        BtnSeek1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek1KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnSeek1);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Password :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass5.add(jLabel4);

        TPass.setForeground(new java.awt.Color(0, 0, 0));
        TPass.setName("TPass"); // NOI18N
        TPass.setPreferredSize(new java.awt.Dimension(200, 24));
        TPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPassKeyPressed(evt);
            }
        });
        panelGlass5.add(TPass);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));
        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TabAkses.setForeground(new java.awt.Color(0, 0, 0));
        TabAkses.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabAkses.setName("TabAkses"); // NOI18N
        TabAkses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabAksesMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbUser.setAutoCreateRowSorter(true);
        tbUser.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbUser.setComponentPopupMenu(jPopupMenu1);
        tbUser.setName("tbUser"); // NOI18N
        tbUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUserMouseClicked(evt);
            }
        });
        tbUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUserKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbUser);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(48, 48));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass6.add(BtnSimpan);

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
        panelGlass6.add(BtnBatal);

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
        panelGlass6.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti/Paste Hak Akses");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(175, 30));
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
        panelGlass6.add(BtnPrint);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass6.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(LCount);

        internalFrame2.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(48, 48));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel6.setRequestFocusEnabled(false);
        panelisi1.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelisi1.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
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
        panelisi1.add(BtnAll);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.PAGE_START);

        TabAkses.addTab(".: Akses Umum", internalFrame2);
        internalFrame2.getAccessibleContext().setAccessibleName(",: Akses Umum");

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbUser1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbUser1.setName("tbUser1"); // NOI18N
        tbUser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUser1MouseClicked(evt);
            }
        });
        tbUser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUser1KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbUser1);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(48, 48));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        cekUnit.setForeground(new java.awt.Color(0, 0, 0));
        cekUnit.setText("Unit R. Inap : RUANGAN");
        cekUnit.setName("cekUnit"); // NOI18N
        cekUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekUnitActionPerformed(evt);
            }
        });
        panelisi2.add(cekUnit);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi2.add(kdpoli);

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setName("nmpoli"); // NOI18N
        nmpoli.setPreferredSize(new java.awt.Dimension(215, 23));
        panelisi2.add(nmpoli);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelisi2.add(BtnSeek2);

        NmRuangan.setForeground(new java.awt.Color(0, 0, 0));
        NmRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        NmRuangan.setName("NmRuangan"); // NOI18N
        NmRuangan.setPreferredSize(new java.awt.Dimension(170, 23));
        panelisi2.add(NmRuangan);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel8.setRequestFocusEnabled(false);
        panelisi2.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi2.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCari1KeyReleased(evt);
            }
        });
        panelisi2.add(BtnCari1);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Semua : ");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel10.setRequestFocusEnabled(false);
        panelisi2.add(jLabel10);

        cmbSemua.setForeground(new java.awt.Color(0, 0, 0));
        cmbSemua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Unit RALAN", "Unit RANAP", "Unit RALAN & RANAP" }));
        cmbSemua.setName("cmbSemua"); // NOI18N
        cmbSemua.setPreferredSize(new java.awt.Dimension(140, 23));
        panelisi2.add(cmbSemua);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(48, 48));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass7.add(BtnBatal1);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass7.add(BtnHapus1);

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
        panelGlass7.add(BtnAll1);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass7.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass7.add(LCount1);

        internalFrame3.add(panelGlass7, java.awt.BorderLayout.PAGE_END);

        TabAkses.addTab(".: Akses Unit/Poli/Ruang Inap", internalFrame3);

        internalFrame1.add(TabAkses, java.awt.BorderLayout.CENTER);
        TabAkses.getAccessibleContext().setAccessibleName(".: Akses Umum");

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TKd.getText().trim().equals("") || TNmUser.getText().trim().equals("")) {
            Valid.textKosong(TKd, "User");
        } else if (TPass.getText().trim().equals("")) {
            Valid.textKosong(TPass, "Password");
        } else {
            if (Sequel.menyimpantf("user", "AES_ENCRYPT('" + TKd.getText() + "','nur'),AES_ENCRYPT('" + TPass.getText() + "','windi'),'false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false',"
                    + "'false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false','false'", "User") == true) {
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TPass,BtnHapus);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TKd.requestFocus();
        } else if (TPass.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!TPass.getText().trim().equals("")) {
            Sequel.queryu("delete from user where id_user=AES_ENCRYPT('" + TKd.getText() + "','nur')");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TKd.getText().trim().equals("") || TNmUser.getText().trim().equals("")) {
            Valid.textKosong(TKd, "User");
        } else if (TPass.getText().trim().equals("")) {
            Valid.textKosong(TPass, "Password");
        } else {
            i = tbUser.getSelectedRow();
            if (i != -1) {
                Sequel.mengedit("user", "id_user=AES_ENCRYPT('" + TKd.getText() + "','nur')",
                        "id_user=AES_ENCRYPT('" + TKd.getText() + "','nur'),"
                        + "password=AES_ENCRYPT('" + TPass.getText() + "','windi'),"
                        + "penyakit='" + tbUser.getValueAt(i, 4).toString() + "', "
                        + "obat_penyakit='" + tbUser.getValueAt(i, 5).toString() + "',"
                        + "dokter='" + tbUser.getValueAt(i, 6).toString() + "',"
                        + "jadwal_praktek='" + tbUser.getValueAt(i, 7).toString() + "',"
                        + "petugas='" + tbUser.getValueAt(i, 8).toString() + "',"
                        + "pasien='" + tbUser.getValueAt(i, 9).toString() + "',"
                        + "registrasi='" + tbUser.getValueAt(i, 10).toString() + "',"
                        + "tindakan_ralan='" + tbUser.getValueAt(i, 11).toString() + "',"
                        + "kamar_inap='" + tbUser.getValueAt(i, 12).toString() + "',"
                        + "tindakan_ranap='" + tbUser.getValueAt(i, 13).toString() + "',"
                        + "operasi='" + tbUser.getValueAt(i, 14).toString() + "',"
                        + "rujukan_keluar='" + tbUser.getValueAt(i, 15).toString() + "',"
                        + "rujukan_masuk='" + tbUser.getValueAt(i, 16).toString() + "',"
                        + "beri_obat='" + tbUser.getValueAt(i, 17).toString() + "',"
                        + "resep_pulang='" + tbUser.getValueAt(i, 18).toString() + "',"
                        + "pasien_meninggal='" + tbUser.getValueAt(i, 19).toString() + "',"
                        + "diet_pasien='" + tbUser.getValueAt(i, 20).toString() + "',"
                        + "kelahiran_bayi='" + tbUser.getValueAt(i, 21).toString() + "',"
                        + "periksa_lab='" + tbUser.getValueAt(i, 22).toString() + "',"
                        + "periksa_radiologi='" + tbUser.getValueAt(i, 23).toString() + "',"
                        + "kasir_ralan='" + tbUser.getValueAt(i, 24).toString() + "',"
                        + "deposit_pasien='" + tbUser.getValueAt(i, 25).toString() + "',"
                        + "piutang_pasien='" + tbUser.getValueAt(i, 26).toString() + "',"
                        + "peminjaman_berkas='" + tbUser.getValueAt(i, 27).toString() + "',"
                        + "barcode='" + tbUser.getValueAt(i, 28).toString() + "',"
                        + "presensi_harian='" + tbUser.getValueAt(i, 29).toString() + "',"
                        + "presensi_bulanan='" + tbUser.getValueAt(i, 30).toString() + "',"
                        + "pegawai_admin='" + tbUser.getValueAt(i, 31).toString() + "',"
                        + "pegawai_user='" + tbUser.getValueAt(i, 32).toString() + "',"
                        + "suplier='" + tbUser.getValueAt(i, 33).toString() + "',"
                        + "satuan_barang='" + tbUser.getValueAt(i, 34).toString() + "',"
                        + "konversi_satuan='" + tbUser.getValueAt(i, 35).toString() + "',"
                        + "jenis_barang='" + tbUser.getValueAt(i, 36).toString() + "',"
                        + "obat='" + tbUser.getValueAt(i, 37).toString() + "',"
                        + "stok_opname_obat='" + tbUser.getValueAt(i, 38).toString() + "',"
                        + "stok_obat_pasien='" + tbUser.getValueAt(i, 39).toString() + "',"
                        + "pengadaan_obat='" + tbUser.getValueAt(i, 40).toString() + "',"
                        + "pemesanan_obat='" + tbUser.getValueAt(i, 41).toString() + "',"
                        + "penjualan_obat='" + tbUser.getValueAt(i, 42).toString() + "',"
                        + "piutang_obat='" + tbUser.getValueAt(i, 43).toString() + "',"
                        + "retur_ke_suplier='" + tbUser.getValueAt(i, 44).toString() + "',"
                        + "retur_dari_pembeli='" + tbUser.getValueAt(i, 45).toString() + "',"
                        + "retur_obat_ranap='" + tbUser.getValueAt(i, 46).toString() + "',"
                        + "retur_piutang_pasien='" + tbUser.getValueAt(i, 47).toString() + "',"
                        + "keuntungan_penjualan='" + tbUser.getValueAt(i, 48).toString() + "',"
                        + "keuntungan_beri_obat='" + tbUser.getValueAt(i, 49).toString() + "',"
                        + "sirkulasi_obat='" + tbUser.getValueAt(i, 50).toString() + "',"
                        + "ipsrs_barang='" + tbUser.getValueAt(i, 51).toString() + "',"
                        + "ipsrs_pengadaan_barang='" + tbUser.getValueAt(i, 52).toString() + "',"
                        + "ipsrs_stok_keluar='" + tbUser.getValueAt(i, 53).toString() + "',"
                        + "ipsrs_rekap_pengadaan='" + tbUser.getValueAt(i, 54).toString() + "',"
                        + "ipsrs_rekap_stok_keluar='" + tbUser.getValueAt(i, 55).toString() + "',"
                        + "ipsrs_pengeluaran_harian='" + tbUser.getValueAt(i, 56).toString() + "',"
                        + "inventaris_jenis='" + tbUser.getValueAt(i, 57).toString() + "',"
                        + "inventaris_kategori='" + tbUser.getValueAt(i, 58).toString() + "',"
                        + "inventaris_merk='" + tbUser.getValueAt(i, 59).toString() + "',"
                        + "inventaris_ruang='" + tbUser.getValueAt(i, 60).toString() + "',"
                        + "inventaris_produsen='" + tbUser.getValueAt(i, 61).toString() + "',"
                        + "inventaris_koleksi='" + tbUser.getValueAt(i, 62).toString() + "',"
                        + "inventaris_inventaris='" + tbUser.getValueAt(i, 63).toString() + "',"
                        + "inventaris_sirkulasi='" + tbUser.getValueAt(i, 64).toString() + "',"
                        + "parkir_jenis='" + tbUser.getValueAt(i, 65).toString() + "',"
                        + "parkir_in='" + tbUser.getValueAt(i, 66).toString() + "',"
                        + "parkir_out='" + tbUser.getValueAt(i, 67).toString() + "',"
                        + "parkir_rekap_harian='" + tbUser.getValueAt(i, 68).toString() + "',"
                        + "parkir_rekap_bulanan='" + tbUser.getValueAt(i, 69).toString() + "',"
                        + "informasi_kamar='" + tbUser.getValueAt(i, 70).toString() + "',"
                        + "harian_tindakan_poli='" + tbUser.getValueAt(i, 71).toString() + "',"
                        + "obat_per_poli='" + tbUser.getValueAt(i, 72).toString() + "',"
                        + "obat_per_kamar='" + tbUser.getValueAt(i, 73).toString() + "',"
                        + "obat_per_dokter_ralan='" + tbUser.getValueAt(i, 74).toString() + "',"
                        + "obat_per_dokter_ranap='" + tbUser.getValueAt(i, 75).toString() + "',"
                        + "harian_dokter='" + tbUser.getValueAt(i, 76).toString() + "',"
                        + "bulanan_dokter='" + tbUser.getValueAt(i, 77).toString() + "',"
                        + "harian_paramedis='" + tbUser.getValueAt(i, 78).toString() + "',"
                        + "bulanan_paramedis='" + tbUser.getValueAt(i, 79).toString() + "',"
                        + "pembayaran_ralan='" + tbUser.getValueAt(i, 80).toString() + "',"
                        + "pembayaran_ranap='" + tbUser.getValueAt(i, 81).toString() + "',"
                        + "rekap_pembayaran_ralan='" + tbUser.getValueAt(i, 82).toString() + "',"
                        + "rekap_pembayaran_ranap='" + tbUser.getValueAt(i, 83).toString() + "',"
                        + "tagihan_masuk='" + tbUser.getValueAt(i, 84).toString() + "',"
                        + "tambahan_biaya='" + tbUser.getValueAt(i, 85).toString() + "',"
                        + "potongan_biaya='" + tbUser.getValueAt(i, 86).toString() + "',"
                        + "resep_obat='" + tbUser.getValueAt(i, 87).toString() + "',"
                        + "resume_pasien='" + tbUser.getValueAt(i, 88).toString() + "',"
                        + "penyakit_ralan='" + tbUser.getValueAt(i, 89).toString() + "',"
                        + "penyakit_ranap='" + tbUser.getValueAt(i, 90).toString() + "',"
                        + "kamar='" + tbUser.getValueAt(i, 91).toString() + "',"
                        + "tarif_ralan='" + tbUser.getValueAt(i, 92).toString() + "',"
                        + "tarif_ranap='" + tbUser.getValueAt(i, 93).toString() + "',"
                        + "tarif_lab='" + tbUser.getValueAt(i, 94).toString() + "',"
                        + "tarif_radiologi='" + tbUser.getValueAt(i, 95).toString() + "',"
                        + "tarif_operasi='" + tbUser.getValueAt(i, 96).toString() + "',"
                        + "akun_rekening='" + tbUser.getValueAt(i, 97).toString() + "',"
                        + "rekening_tahun='" + tbUser.getValueAt(i, 98).toString() + "',"
                        + "posting_jurnal='" + tbUser.getValueAt(i, 99).toString() + "',"
                        + "buku_besar='" + tbUser.getValueAt(i, 100).toString() + "',"
                        + "cashflow='" + tbUser.getValueAt(i, 101).toString() + "',"
                        + "keuangan='" + tbUser.getValueAt(i, 102).toString() + "',"
                        + "pengeluaran='" + tbUser.getValueAt(i, 103).toString() + "',"
                        + "setup_pjlab='" + tbUser.getValueAt(i, 104).toString() + "',"
                        + "setup_otolokasi='" + tbUser.getValueAt(i, 105).toString() + "',"
                        + "setup_jam_kamin='" + tbUser.getValueAt(i, 106).toString() + "',"
                        + "setup_embalase='" + tbUser.getValueAt(i, 107).toString() + "',"
                        + "tracer_login='" + tbUser.getValueAt(i, 108).toString() + "',"
                        + "display='" + tbUser.getValueAt(i, 109).toString() + "',"
                        + "set_harga_obat='" + tbUser.getValueAt(i, 110).toString() + "',"
                        + "set_penggunaan_tarif='" + tbUser.getValueAt(i, 111).toString() + "',"
                        + "set_oto_ralan='" + tbUser.getValueAt(i, 112).toString() + "',"
                        + "biaya_harian='" + tbUser.getValueAt(i, 113).toString() + "',"
                        + "biaya_masuk_sekali='" + tbUser.getValueAt(i, 114).toString() + "',"
                        + "set_no_rm='" + tbUser.getValueAt(i, 115).toString() + "',"
                        + "billing_ralan='" + tbUser.getValueAt(i, 116).toString() + "',"
                        + "billing_ranap='" + tbUser.getValueAt(i, 117).toString() + "',"
                        + "jm_ranap_dokter='" + tbUser.getValueAt(i, 118).toString() + "',"
                        + "igd='" + tbUser.getValueAt(i, 119).toString() + "',"
                        + "barcoderalan='" + tbUser.getValueAt(i, 120).toString() + "',"
                        + "barcoderanap='" + tbUser.getValueAt(i, 121).toString() + "',"
                        + "set_harga_obat_ralan='" + tbUser.getValueAt(i, 122).toString() + "',"
                        + "set_harga_obat_ranap='" + tbUser.getValueAt(i, 123).toString() + "',"
                        + "penyakit_pd3i='" + tbUser.getValueAt(i, 124).toString() + "',"
                        + "surveilans_pd3i='" + tbUser.getValueAt(i, 125).toString() + "',"
                        + "surveilans_ralan='" + tbUser.getValueAt(i, 126).toString() + "',"
                        + "diagnosa_pasien='" + tbUser.getValueAt(i, 127).toString() + "',"
                        + "surveilans_ranap='" + tbUser.getValueAt(i, 128).toString() + "',"
                        + "pny_takmenular_ranap='" + tbUser.getValueAt(i, 129).toString() + "',"
                        + "pny_takmenular_ralan='" + tbUser.getValueAt(i, 130).toString() + "',"
                        + "kunjungan_ralan='" + tbUser.getValueAt(i, 131).toString() + "',"
                        + "rl32='" + tbUser.getValueAt(i, 132).toString() + "',"
                        + "rl33='" + tbUser.getValueAt(i, 133).toString() + "',"
                        + "rl37='" + tbUser.getValueAt(i, 134).toString() + "',"
                        + "rl38='" + tbUser.getValueAt(i, 135).toString() + "',"
                        + "harian_tindakan_dokter='" + tbUser.getValueAt(i, 136).toString() + "',"
                        + "sms='" + tbUser.getValueAt(i, 137).toString() + "',"
                        + "sidikjari='" + tbUser.getValueAt(i, 138).toString() + "',"
                        + "jam_masuk='" + tbUser.getValueAt(i, 139).toString() + "',"
                        + "jadwal_pegawai='" + tbUser.getValueAt(i, 140).toString() + "',"
                        + "parkir_barcode='" + tbUser.getValueAt(i, 141).toString() + "',"
                        + "set_nota='" + tbUser.getValueAt(i, 142).toString() + "',"
                        + "dpjp_ranap='" + tbUser.getValueAt(i, 143).toString() + "',"
                        + "mutasi_barang='" + tbUser.getValueAt(i, 144).toString() + "',"
                        + "rl34='" + tbUser.getValueAt(i, 145).toString() + "',"
                        + "rl36='" + tbUser.getValueAt(i, 146).toString() + "',"
                        + "fee_visit_dokter='" + tbUser.getValueAt(i, 147).toString() + "',"
                        + "fee_bacaan_ekg='" + tbUser.getValueAt(i, 148).toString() + "',"
                        + "fee_rujukan_rontgen='" + tbUser.getValueAt(i, 149).toString() + "',"
                        + "fee_rujukan_ranap='" + tbUser.getValueAt(i, 150).toString() + "',"
                        + "fee_ralan='" + tbUser.getValueAt(i, 151).toString() + "',"
                        + "akun_bayar='" + tbUser.getValueAt(i, 152).toString() + "',"
                        + "bayar_pemesanan_obat='" + tbUser.getValueAt(i, 153).toString() + "',"
                        + "obat_per_dokter_peresep='" + tbUser.getValueAt(i, 154).toString() + "',"
                        + "ipsrs_jenis_barang='" + tbUser.getValueAt(i, 155).toString() + "',"
                        + "pemasukan_lain='" + tbUser.getValueAt(i, 156).toString() + "',"
                        + "pengaturan_rekening='" + tbUser.getValueAt(i, 157).toString() + "',"
                        + "closing_kasir='" + tbUser.getValueAt(i, 158).toString() + "',"
                        + "keterlambatan_presensi='" + tbUser.getValueAt(i, 159).toString() + "',"
                        + "set_harga_kamar='" + tbUser.getValueAt(i, 160).toString() + "',"
                        + "rekap_per_shift='" + tbUser.getValueAt(i, 161).toString() + "',"
                        + "bpjs_cek_nik='" + tbUser.getValueAt(i, 162).toString() + "',"
                        + "bpjs_cek_kartu='" + tbUser.getValueAt(i, 163).toString() + "',"
                        + "bpjs_cek_riwayat='" + tbUser.getValueAt(i, 164).toString() + "',"
                        + "obat_per_cara_bayar='" + tbUser.getValueAt(i, 165).toString() + "',"
                        + "kunjungan_ranap='" + tbUser.getValueAt(i, 166).toString() + "',"
                        + "bayar_piutang='" + tbUser.getValueAt(i, 167).toString() + "',"
                        + "payment_point='" + tbUser.getValueAt(i, 168).toString() + "',"
                        + "bpjs_cek_nomor_rujukan='" + tbUser.getValueAt(i, 169).toString() + "',"
                        + "icd9='" + tbUser.getValueAt(i, 170).toString() + "',"
                        + "darurat_stok='" + tbUser.getValueAt(i, 171).toString() + "',"
                        + "retensi_rm='" + tbUser.getValueAt(i, 172).toString() + "',"
                        + "temporary_presensi='" + tbUser.getValueAt(i, 173).toString() + "',"
                        + "jurnal_harian='" + tbUser.getValueAt(i, 174).toString() + "',"
                        + "sirkulasi_obat2='" + tbUser.getValueAt(i, 175).toString() + "',"
                        + "edit_registrasi='" + tbUser.getValueAt(i, 176).toString() + "',"
                        + "bpjs_referensi_diagnosa='" + tbUser.getValueAt(i, 177).toString() + "',"
                        + "bpjs_referensi_poli='" + tbUser.getValueAt(i, 178).toString() + "',"
                        + "industrifarmasi='" + tbUser.getValueAt(i, 179).toString() + "',"
                        + "harian_js='" + tbUser.getValueAt(i, 180).toString() + "',"
                        + "bulanan_js='" + tbUser.getValueAt(i, 181).toString() + "',"
                        + "harian_paket_bhp='" + tbUser.getValueAt(i, 182).toString() + "',"
                        + "bulanan_paket_bhp='" + tbUser.getValueAt(i, 183).toString() + "',"
                        + "piutang_pasien2='" + tbUser.getValueAt(i, 184).toString() + "',"
                        + "bpjs_referensi_faskes='" + tbUser.getValueAt(i, 185).toString() + "',"
                        + "bpjs_sep='" + tbUser.getValueAt(i, 186).toString() + "',"
                        + "pengambilan_utd='" + tbUser.getValueAt(i, 187).toString() + "',"
                        + "tarif_utd='" + tbUser.getValueAt(i, 188).toString() + "',"
                        + "pengambilan_utd2='" + tbUser.getValueAt(i, 189).toString() + "',"
                        + "utd_medis_rusak='" + tbUser.getValueAt(i, 190).toString() + "',"
                        + "pengambilan_penunjang_utd='" + tbUser.getValueAt(i, 191).toString() + "',"
                        + "pengambilan_penunjang_utd2='" + tbUser.getValueAt(i, 192).toString() + "',"
                        + "utd_penunjang_rusak='" + tbUser.getValueAt(i, 193).toString() + "',"
                        + "suplier_penunjang='" + tbUser.getValueAt(i, 194).toString() + "',"
                        + "utd_donor='" + tbUser.getValueAt(i, 195).toString() + "',"
                        + "bpjs_monitoring_klaim='" + tbUser.getValueAt(i, 196).toString() + "',"
                        + "utd_cekal_darah='" + tbUser.getValueAt(i, 197).toString() + "',"
                        + "utd_komponen_darah='" + tbUser.getValueAt(i, 198).toString() + "',"
                        + "utd_stok_darah='" + tbUser.getValueAt(i, 199).toString() + "',"
                        + "utd_pemisahan_darah='" + tbUser.getValueAt(i, 200).toString() + "',"
                        + "harian_kamar='" + tbUser.getValueAt(i, 201).toString() + "',"
                        + "rincian_piutang_pasien='" + tbUser.getValueAt(i, 202).toString() + "',"
                        + "keuntungan_beri_obat_nonpiutang='" + tbUser.getValueAt(i, 203).toString() + "',"
                        + "reklasifikasi_ralan='" + tbUser.getValueAt(i, 204).toString() + "',"
                        + "reklasifikasi_ranap='" + tbUser.getValueAt(i, 205).toString() + "',"
                        + "utd_penyerahan_darah='" + tbUser.getValueAt(i, 206).toString() + "',"
                        + "hutang_obat='" + tbUser.getValueAt(i, 207).toString() + "',"
                        + "riwayat_obat_alkes_bhp='" + tbUser.getValueAt(i, 208).toString() + "',"
                        + "sensus_harian_poli='" + tbUser.getValueAt(i, 209).toString() + "',"
                        + "rl4a='" + tbUser.getValueAt(i, 210).toString() + "',"
                        + "aplicare_referensi_kamar='" + tbUser.getValueAt(i, 211).toString() + "',"
                        + "aplicare_ketersediaan_kamar='" + tbUser.getValueAt(i, 212).toString() + "',"
                        + "inacbg_klaim_baru_otomatis='" + tbUser.getValueAt(i, 213).toString() + "',"
                        + "inacbg_klaim_baru_manual='" + tbUser.getValueAt(i, 214).toString() + "',"
                        + "inacbg_coder_nik='" + tbUser.getValueAt(i, 215).toString() + "',"
                        + "mutasi_berkas='" + tbUser.getValueAt(i, 216).toString() + "',"
                        + "akun_piutang='" + tbUser.getValueAt(i, 217).toString() + "',"
                        + "harian_kso='" + tbUser.getValueAt(i, 218).toString() + "',"
                        + "bulanan_kso='" + tbUser.getValueAt(i, 219).toString() + "',"
                        + "harian_menejemen='" + tbUser.getValueAt(i, 220).toString() + "',"
                        + "bulanan_menejemen='" + tbUser.getValueAt(i, 221).toString() + "',"
                        + "inhealth_cek_eligibilitas='" + tbUser.getValueAt(i, 222).toString() + "',"
                        + "inhealth_referensi_jenpel_ruang_rawat='" + tbUser.getValueAt(i, 223).toString() + "',"
                        + "inhealth_referensi_poli='" + tbUser.getValueAt(i, 224).toString() + "',"
                        + "inhealth_referensi_faskes='" + tbUser.getValueAt(i, 225).toString() + "',"
                        + "inhealth_sjp='" + tbUser.getValueAt(i, 226).toString() + "',"
                        + "piutang_ralan='" + tbUser.getValueAt(i, 227).toString() + "',"
                        + "piutang_ranap='" + tbUser.getValueAt(i, 228).toString() + "',"
                        + "detail_piutang_penjab='" + tbUser.getValueAt(i, 229).toString() + "',"
                        + "lama_pelayanan_ralan='" + tbUser.getValueAt(i, 230).toString() + "',"
                        + "catatan_pasien='" + tbUser.getValueAt(i, 231).toString() + "',"
                        + "rl4b='" + tbUser.getValueAt(i, 232).toString() + "',"
                        + "rl4asebab='" + tbUser.getValueAt(i, 233).toString() + "',"
                        + "rl4bsebab='" + tbUser.getValueAt(i, 234).toString() + "',"
                        + "data_HAIs='" + tbUser.getValueAt(i, 235).toString() + "',"
                        + "harian_HAIs='" + tbUser.getValueAt(i, 236).toString() + "',"
                        + "bulanan_HAIs='" + tbUser.getValueAt(i, 237).toString() + "',"
                        + "hitung_bor='" + tbUser.getValueAt(i, 238).toString() + "',"
                        + "perusahaan_pasien='" + tbUser.getValueAt(i, 239).toString() + "',"
                        + "resep_dokter='" + tbUser.getValueAt(i, 240).toString() + "',"
                        + "lama_pelayanan_apotek='" + tbUser.getValueAt(i, 241).toString() + "',"
                        + "hitung_alos='" + tbUser.getValueAt(i, 242).toString() + "',"
                        + "detail_tindakan='" + tbUser.getValueAt(i, 243).toString() + "',"
                        + "rujukan_poli_internal='" + tbUser.getValueAt(i, 244).toString() + "',"
                        + "rekap_poli_anak='" + tbUser.getValueAt(i, 245).toString() + "',"
                        + "grafik_kunjungan_poli='" + tbUser.getValueAt(i, 246).toString() + "',"
                        + "grafik_kunjungan_perdokter='" + tbUser.getValueAt(i, 247).toString() + "',"
                        + "grafik_kunjungan_perpekerjaan='" + tbUser.getValueAt(i, 248).toString() + "',"
                        + "grafik_kunjungan_perpendidikan='" + tbUser.getValueAt(i, 249).toString() + "',"
                        + "grafik_kunjungan_pertahun='" + tbUser.getValueAt(i, 250).toString() + "',"
                        + "berkas_digital_perawatan='" + tbUser.getValueAt(i, 251).toString() + "',"
                        + "penyakit_menular_ranap='" + tbUser.getValueAt(i, 252).toString() + "',"
                        + "penyakit_menular_ralan='" + tbUser.getValueAt(i, 253).toString() + "',"
                        + "grafik_kunjungan_perbulan='" + tbUser.getValueAt(i, 254).toString() + "',"
                        + "grafik_kunjungan_pertanggal='" + tbUser.getValueAt(i, 255).toString() + "',"
                        + "grafik_kunjungan_demografi='" + tbUser.getValueAt(i, 256).toString() + "',"
                        + "grafik_kunjungan_statusdaftartahun='" + tbUser.getValueAt(i, 257).toString() + "',"
                        + "grafik_kunjungan_statusdaftartahun2='" + tbUser.getValueAt(i, 258).toString() + "',"
                        + "grafik_kunjungan_statusdaftarbulan='" + tbUser.getValueAt(i, 259).toString() + "',"
                        + "grafik_kunjungan_statusdaftarbulan2='" + tbUser.getValueAt(i, 260).toString() + "',"
                        + "grafik_kunjungan_statusdaftartanggal='" + tbUser.getValueAt(i, 261).toString() + "',"
                        + "grafik_kunjungan_statusdaftartanggal2='" + tbUser.getValueAt(i, 262).toString() + "',"
                        + "grafik_kunjungan_statusbataltahun='" + tbUser.getValueAt(i, 263).toString() + "',"
                        + "grafik_kunjungan_statusbatalbulan='" + tbUser.getValueAt(i, 264).toString() + "',"
                        + "pcare_cek_penyakit='" + tbUser.getValueAt(i, 265).toString() + "',"
                        + "grafik_kunjungan_statusbataltanggal='" + tbUser.getValueAt(i, 266).toString() + "',"
                        + "kategori_barang='" + tbUser.getValueAt(i, 267).toString() + "',"
                        + "golongan_barang='" + tbUser.getValueAt(i, 268).toString() + "',"
                        + "pemberian_obat_pertanggal='" + tbUser.getValueAt(i, 269).toString() + "',"
                        + "penjualan_obat_pertanggal='" + tbUser.getValueAt(i, 270).toString() + "',"
                        + "skdp_bpjs='" + tbUser.getValueAt(i, 271).toString() + "',"
                        + "rujukan_keluar_vclaim_bpjs='" + tbUser.getValueAt(i, 272).toString() + "',"
                        + "booking_registrasi='" + tbUser.getValueAt(i, 273).toString() + "',"
                        + "bpjs_cek_riwayat_rujukan_pcare='" + tbUser.getValueAt(i, 274).toString() + "',"
                        + "bpjs_cek_riwayat_rujukan_rs='" + tbUser.getValueAt(i, 275).toString() + "',"
                        + "bpjs_cek_rujukan_kartu_rs='" + tbUser.getValueAt(i, 276).toString() + "',"
                        + "bpjs_cek_tanggal_rujukan='" + tbUser.getValueAt(i, 277).toString() + "',"
                        + "bpjs_cek_no_rujukan_rs='" + tbUser.getValueAt(i, 278).toString() + "',"
                        + "bpjs_cek_rujukan_kartu_pcare='" + tbUser.getValueAt(i, 279).toString() + "',"
                        + "bpjs_cek_referensi_kelas_rawat='" + tbUser.getValueAt(i, 280).toString() + "',"
                        + "bpjs_cek_referensi_prosedur='" + tbUser.getValueAt(i, 281).toString() + "',"
                        + "bpjs_cek_referensi_dpjp='" + tbUser.getValueAt(i, 282).toString() + "',"
                        + "bpjs_cek_referensi_dokter='" + tbUser.getValueAt(i, 283).toString() + "',"
                        + "bpjs_cek_referensi_spesialistik='" + tbUser.getValueAt(i, 284).toString() + "',"
                        + "bpjs_cek_referensi_ruang_rawat='" + tbUser.getValueAt(i, 285).toString() + "',"
                        + "bpjs_cek_referensi_cara_keluar='" + tbUser.getValueAt(i, 286).toString() + "',"
                        + "bpjs_cek_referensi_pasca_pulang='" + tbUser.getValueAt(i, 287).toString() + "',"
                        + "bpjs_cek_referensi_propinsi='" + tbUser.getValueAt(i, 288).toString() + "',"
                        + "bpjs_cek_referensi_kabupaten='" + tbUser.getValueAt(i, 289).toString() + "',"
                        + "bpjs_cek_referensi_kecamatan='" + tbUser.getValueAt(i, 290).toString() + "',"
                        + "permintaan_lab='" + tbUser.getValueAt(i, 291).toString() + "',"
                        + "permintaan_radiologi='" + tbUser.getValueAt(i, 292).toString() + "',"
                        + "selisih_tarif_bpjs='" + tbUser.getValueAt(i, 293).toString() + "',"
                        + "edit_data_kematian='" + tbUser.getValueAt(i, 294).toString() + "',"
                        + "bridging_jamkesda='" + tbUser.getValueAt(i, 295).toString() + "',"
                        + "masuk_pindah_pulang_inap='" + tbUser.getValueAt(i, 296).toString() + "',"
                        + "masuk_pindah_inap='" + tbUser.getValueAt(i, 297).toString() + "',"
                        + "jumlah_macam_diet='" + tbUser.getValueAt(i, 298).toString() + "',"
                        + "jumlah_porsi_diet='" + tbUser.getValueAt(i, 299).toString() + "',"
                        + "status_gizi='" + tbUser.getValueAt(i, 300).toString() + "',"
                        + "gizi_buruk='" + tbUser.getValueAt(i, 301).toString() + "',"
                        + "master_faskes='" + tbUser.getValueAt(i, 302).toString() + "',"
                        + "set_status_registrasi_ralan='" + tbUser.getValueAt(i, 303).toString() + "',"
                        + "telusur_kunjungan_pasien='" + tbUser.getValueAt(i, 304).toString() + "',"
                        + "sisrute_rujukan_keluar='" + tbUser.getValueAt(i, 305).toString() + "',"
                        + "sisrute_rujukan_masuk='" + tbUser.getValueAt(i, 306).toString() + "',"
                        + "sisrute_referensi_diagnosa='" + tbUser.getValueAt(i, 307).toString() + "',"
                        + "sisrute_referensi_alasanrujuk='" + tbUser.getValueAt(i, 308).toString() + "',"
                        + "sisrute_referensi_faskes='" + tbUser.getValueAt(i, 309).toString() + "',"
                        + "barang_cssd='" + tbUser.getValueAt(i, 310).toString() + "',"
                        + "status_pulang_inap='" + tbUser.getValueAt(i, 311).toString() + "',"
                        + "data_persalinan='" + tbUser.getValueAt(i, 312).toString() + "',"
                        + "data_ponek='" + tbUser.getValueAt(i, 313).toString() + "',"
                        + "registrasi_booking_dikasir='" + tbUser.getValueAt(i, 314).toString() + "',"
                        + "bahasa_pasien='" + tbUser.getValueAt(i, 315).toString() + "',"
                        + "suku_pasien='" + tbUser.getValueAt(i, 316).toString() + "',"
                        + "harian_hais_ranap='" + tbUser.getValueAt(i, 317).toString() + "',"
                        + "bulanan_hais_ranap='" + tbUser.getValueAt(i, 318).toString() + "',"
                        + "harian_hais_ralan='" + tbUser.getValueAt(i, 319).toString() + "',"
                        + "bulanan_hais_ralan='" + tbUser.getValueAt(i, 320).toString() + "',"
                        + "ringkasan_pulang_ranap='" + tbUser.getValueAt(i, 321).toString() + "',"
                        + "laporan_farmasi='" + tbUser.getValueAt(i, 322).toString() + "',"
                        + "master_masalah_keperawatan='" + tbUser.getValueAt(i, 323).toString() + "',"
                        + "penilaian_awal_keperawatan_ralan='" + tbUser.getValueAt(i, 324).toString() + "',"
                        + "master_triase_skala1='" + tbUser.getValueAt(i, 325).toString() + "',"
                        + "master_triase_skala2='" + tbUser.getValueAt(i, 326).toString() + "',"
                        + "master_triase_skala3='" + tbUser.getValueAt(i, 327).toString() + "',"
                        + "master_triase_skala4='" + tbUser.getValueAt(i, 328).toString() + "',"
                        + "master_triase_skala5='" + tbUser.getValueAt(i, 329).toString() + "',"
                        + "master_triase_macamkasus='" + tbUser.getValueAt(i, 330).toString() + "',"
                        + "master_triase_pemeriksaan='" + tbUser.getValueAt(i, 331).toString() + "',"
                        + "data_triase_igd='" + tbUser.getValueAt(i, 332).toString() + "',"
                        + "master_cara_bayar='" + tbUser.getValueAt(i, 333).toString() + "',"
                        + "status_kerja_dokter='" + tbUser.getValueAt(i, 334).toString() + "',"
                        + "pasien_corona='" + tbUser.getValueAt(i, 335).toString() + "',"
                        + "diagnosa_pasien_corona='" + tbUser.getValueAt(i, 336).toString() + "',"
                        + "perawatan_pasien_corona='" + tbUser.getValueAt(i, 337).toString() + "',"
                        + "inacbg_klaim_baru_manual2='" + tbUser.getValueAt(i, 338).toString() + "',"
                        + "indikator_ranap='" + tbUser.getValueAt(i, 339).toString() + "',"
                        + "sensus_inap='" + tbUser.getValueAt(i, 340).toString() + "',"
                        + "review_rm_igd='" + tbUser.getValueAt(i, 341).toString() + "',"
                        + "review_rm_ruangan_h1='" + tbUser.getValueAt(i, 342).toString() + "',"
                        + "review_rm_ruangan_pulang='" + tbUser.getValueAt(i, 343).toString() + "',"
                        + "review_rm_laporan='" + tbUser.getValueAt(i, 344).toString() + "',"
                        + "assesmen_gizi_harian='" + tbUser.getValueAt(i, 345).toString() + "',"
                        + "assesmen_gizi_ulang='" + tbUser.getValueAt(i, 346).toString() + "',"
                        + "tombol_nota_billing='" + tbUser.getValueAt(i, 347).toString() + "',"
                        + "tombol_simpan_hasil_radiologi='" + tbUser.getValueAt(i, 348).toString() + "',"                                
                        + "monev_asuhan_gizi='" + tbUser.getValueAt(i, 349).toString() + "',"
                        + "inacbg_klaim_raza='" + tbUser.getValueAt(i, 350).toString() + "',"
                        + "pengajuan_klaim_inacbg_raza='" + tbUser.getValueAt(i, 351).toString() + "',"
                        + "copy_pemeriksaan_dokter_kepetugas_ralan='" + tbUser.getValueAt(i, 352).toString() + "',"
                        + "jkn_belum_diproses_klaim='" + tbUser.getValueAt(i, 353).toString() + "',"
                        + "input_kode_icd='" + tbUser.getValueAt(i, 354).toString() + "',"
                        + "indikator_mutu_unit='" + tbUser.getValueAt(i, 355).toString() + "',"
                        + "kendali_Mutu_kendali_Biaya_INACBG='" + tbUser.getValueAt(i, 356).toString() + "',"
                        + "dashboard_eResep='" + tbUser.getValueAt(i, 357).toString() + "',"
                        + "bpjs_sep_internal='" + tbUser.getValueAt(i, 358).toString() + "',"
                        + "kemenkes_sitt='" + tbUser.getValueAt(i, 359).toString() + "',"
                        + "rencana_kontrol_jkn='" + tbUser.getValueAt(i, 360).toString() + "',"
                        + "spri_jkn='" + tbUser.getValueAt(i, 361).toString() + "',"
                        + "hapus_sep='" + tbUser.getValueAt(i, 362).toString() + "',"
                        + "penilaian_awal_medis_ralan_kebidanan='" + tbUser.getValueAt(i, 363).toString() + "',"
                        + "penilaian_awal_keperawatan_kebidanan='" + tbUser.getValueAt(i, 364).toString() + "',"
                        + "ikhtisar_perawatan_hiv='" + tbUser.getValueAt(i, 365).toString() + "',"
                        + "survey_kepuasan='" + tbUser.getValueAt(i, 366).toString() + "',"
                        + "kemenkes_kanker='" + tbUser.getValueAt(i, 367).toString() + "',"
                        + "aktivasi_bridging='" + tbUser.getValueAt(i, 368).toString() + "',"
                        + "operator_antrian='" + tbUser.getValueAt(i, 369).toString() + "',"
                        + "penilaian_awal_medis_ralan_tht='" + tbUser.getValueAt(i, 370).toString() + "',"
                        + "rekam_psikologis='" + tbUser.getValueAt(i, 371).toString() + "',"
                        + "penilaian_pasien_geriatri='" + tbUser.getValueAt(i, 372).toString() + "',"
                        + "penilaian_awal_medis_ralan_mata='" + tbUser.getValueAt(i, 373).toString() + "',"
                        + "surat_sakit='" + tbUser.getValueAt(i, 374).toString() + "',"
                        + "surat_keterangan_kir_mcu='" + tbUser.getValueAt(i, 375).toString() + "',"
                        + "asesmen_medik_dewasa_ranap='" + tbUser.getValueAt(i, 376).toString() + "',"
                        + "pemberian_obat='" + tbUser.getValueAt(i, 377).toString() + "',"
                        + "cppt='" + tbUser.getValueAt(i, 378).toString() + "',"
                        + "bridging_satu_sehat='" + tbUser.getValueAt(i, 379).toString() + "',"
                        + "kemoterapi='" + tbUser.getValueAt(i, 380).toString() + "',"
                        + "cek_piutang='" + tbUser.getValueAt(i, 381).toString() + "',"
                        + "asesmen_medik_anak_ranap='" + tbUser.getValueAt(i, 382).toString() + "',"
                        + "kegiatan_operasi='" + tbUser.getValueAt(i, 383).toString() + "',"
                        + "asesmen_medik_bedah_ranap='" + tbUser.getValueAt(i, 384).toString() + "'");
            }
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TKdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
        Valid.pindah(evt,BtnSimpan,TPass);
}//GEN-LAST:event_TKdKeyPressed

    private void TPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPassKeyPressed
        Valid.pindah(evt,TKd,BtnSimpan);
}//GEN-LAST:event_TPassKeyPressed

    private void BtnSeek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek1ActionPerformed
        dlgpetugas.emptTeks();
        dlgpetugas.isCek();
        dlgpetugas.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
        dlgpetugas.setLocationRelativeTo(internalFrame1);
        dlgpetugas.setVisible(true);
}//GEN-LAST:event_BtnSeek1ActionPerformed

    private void BtnSeek1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek1KeyPressed
        Valid.pindah(evt,TKd,TPass);
}//GEN-LAST:event_BtnSeek1KeyPressed

    private void tbUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUserMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbUserMouseClicked

    private void tbUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUserKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbUser.getSelectedColumn() > 4) {
                    if (tbUser.getSelectedRow() != -1) {
                        if (tbUser.getValueAt(tbUser.getSelectedRow(), tbUser.getSelectedColumn()).toString().equals("false")) {
                            tbUser.setValueAt(true, tbUser.getSelectedRow(), tbUser.getSelectedColumn());
                        } else {
                            tbUser.setValueAt(false, tbUser.getSelectedRow(), tbUser.getSelectedColumn());
                        }

                    }
                }
            }
        }
}//GEN-LAST:event_tbUserKeyPressed

private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TKd.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Sequel.queryu("delete from temporary");
            int row=tabMode.getRowCount();
            for(i=0;i<row;i++){  
                Sequel.menyimpan("temporary","'0','"+
                                tabMode.getValueAt(i,0).toString()+"','"+
                                tabMode.getValueAt(i,1).toString()+"','"+
                                tabMode.getValueAt(i,3).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Data User"); 
            }
            Valid.MyReport("rptUser.jasper","report","::[ Data User ]::",
                "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc");
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus,BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", NmRuangan);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbUser.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TKd);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void tbUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUser1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbUser1MouseClicked

    private void tbUser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUser1KeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbUser1KeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari1ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari1.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbUser1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampil1();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyReleased

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampil1();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TKd.getText().trim().equals("") || TNmUser.getText().trim().equals("")) {
            Valid.textKosong(TKd, "User");
        } else {
            if (cmbSemua.getSelectedIndex() == 0 && cekUnit.isSelected() == true) {
                if (kdpoli.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu polinya dulu...!!!!");
                } else if (!kdpoli.getText().equals("")) {
                    Sequel.menyimpan("hak_akses_unit", "'" + TKd.getText() + "','" + kdpoli.getText() + "','" + Sequel.cariIsi("select ifnull(MAX(kd_baris)+1,1) from hak_akses_unit") + "' ", "Hak akses unit");
                }
            } else if (cmbSemua.getSelectedIndex() == 0 && cekUnit.isSelected() == false) {
                if (NmRuangan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Silakan pilih salah satu nama ruangannya dulu...!!!!");
                    NmRuangan.requestFocus();
                } else {
                    Sequel.menyimpan("hak_akses_unit", "'" + TKd.getText() + "','" + NmRuangan.getSelectedItem().toString() + "','" + Sequel.cariIsi("select ifnull(MAX(kd_baris)+1,1) from hak_akses_unit") + "' ", "Hak akses unit");
                }
            } else if (cmbSemua.getSelectedIndex() == 1) {
                Sequel.menyimpan("hak_akses_unit", "'" + TKd.getText() + "','semua ralan','" + Sequel.cariIsi("select ifnull(MAX(kd_baris)+1,1) from hak_akses_unit") + "' ", "Hak akses unit");
            } else if (cmbSemua.getSelectedIndex() == 2) {
                Sequel.menyimpan("hak_akses_unit", "'" + TKd.getText() + "','semua ranap','" + Sequel.cariIsi("select ifnull(MAX(kd_baris)+1,1) from hak_akses_unit") + "' ", "Hak akses unit");
            } else if (cmbSemua.getSelectedIndex() == 3) {
                Sequel.menyimpan("hak_akses_unit", "'" + TKd.getText() + "','semua ralan ranap','" + Sequel.cariIsi("select ifnull(MAX(kd_baris)+1,1) from hak_akses_unit") + "' ", "Hak akses unit");
            }
            tampil1();
            emptTeks();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan1, BtnHapus1);
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            BtnSeek1.requestFocus();
        } else if (kodeBaris.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!kodeBaris.getText().trim().equals("")) {
            Sequel.queryu("delete from hak_akses_unit where kd_baris='" + kodeBaris.getText() + "'");
            tampil1();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnHapus1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void TabAksesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabAksesMouseClicked
        if (TabAkses.getSelectedIndex() == 0) {
            tampil();
        } else if (TabAkses.getSelectedIndex() == 1) {
            tampil1();
        } 
    }//GEN-LAST:event_TabAksesMouseClicked

    private void cekUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekUnitActionPerformed
        if (cekUnit.isSelected() == true) {
            cekUnit.setText("Unit R. Jalan : POLIKLINIK");
            kdpoli.setText("");
            nmpoli.setText("");
            kdpoli.setVisible(true);
            nmpoli.setVisible(true);
            BtnSeek2.setVisible(true);
            NmRuangan.setVisible(false);

        } else if (cekUnit.isSelected() == false) {
            cekUnit.setText("Unit R. Inap : RUANGAN");
            kdpoli.setVisible(false);
            nmpoli.setVisible(false);
            BtnSeek2.setVisible(false);
            NmRuangan.setVisible(true);
            NmRuangan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cekUnitActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed

    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        poli.isCek();
        poli.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setAlwaysOnTop(false);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void MnSetUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSetUserActionPerformed
        if (tbUser.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            personal.isUser(TKd.getText(), TNmUser.getText(), TPass.getText());
            personal.setSize(773, this.getHeight() - 50);
            personal.setLocationRelativeTo(internalFrame1);
            personal.setAlwaysOnTop(false);
            personal.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu nama user..!!");
        }
    }//GEN-LAST:event_MnSetUserActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgUser dialog = new DlgUser(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek1;
    private widget.Button BtnSeek2;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnSetUser;
    private widget.ComboBox NmRuangan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TKd;
    private widget.TextBox TNmUser;
    private widget.TextBox TPass;
    private widget.TabPane TabAkses;
    private widget.CekBox cekUnit;
    private widget.ComboBox cmbSemua;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdpoli;
    private widget.TextBox kodeBaris;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.Table tbUser;
    private widget.Table tbUser1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {        
        try{    
            Valid.tabelKosong(tabMode);
            ps = koneksi.prepareStatement("select AES_DECRYPT(id_user,'nur'),AES_DECRYPT(password,'windi'),"
                    + "penyakit, obat_penyakit, dokter, jadwal_praktek, petugas, pasien, registrasi, tindakan_ralan,"
                    + "kamar_inap, tindakan_ranap, operasi, rujukan_keluar, rujukan_masuk, beri_obat, resep_pulang, "
                    + "pasien_meninggal, diet_pasien, kelahiran_bayi, periksa_lab, periksa_radiologi, kasir_ralan, "
                    + "deposit_pasien, piutang_pasien, peminjaman_berkas, barcode, presensi_harian, presensi_bulanan, "
                    + "pegawai_admin, pegawai_user, suplier, satuan_barang, konversi_satuan, jenis_barang, obat, "
                    + "stok_opname_obat, stok_obat_pasien, pengadaan_obat, pemesanan_obat, penjualan_obat, piutang_obat, "
                    + "retur_ke_suplier, retur_dari_pembeli, retur_obat_ranap, retur_piutang_pasien, keuntungan_penjualan, "
                    + "keuntungan_beri_obat, sirkulasi_obat, ipsrs_barang, ipsrs_pengadaan_barang, ipsrs_stok_keluar, "
                    + "ipsrs_rekap_pengadaan, ipsrs_rekap_stok_keluar, ipsrs_pengeluaran_harian, inventaris_jenis, "
                    + "inventaris_kategori, inventaris_merk, inventaris_ruang, inventaris_produsen, inventaris_koleksi,"
                    + "inventaris_inventaris, inventaris_sirkulasi, parkir_jenis, parkir_in, parkir_out, parkir_rekap_harian, "
                    + "parkir_rekap_bulanan, informasi_kamar, harian_tindakan_poli, obat_per_poli, obat_per_kamar, "
                    + "obat_per_dokter_ralan, obat_per_dokter_ranap, harian_dokter, bulanan_dokter, harian_paramedis,"
                    + "bulanan_paramedis, pembayaran_ralan, pembayaran_ranap, rekap_pembayaran_ralan, rekap_pembayaran_ranap,"
                    + "tagihan_masuk, tambahan_biaya, potongan_biaya, resep_obat, resume_pasien, penyakit_ralan, penyakit_ranap, "
                    + "kamar, tarif_ralan, tarif_ranap, tarif_lab, tarif_radiologi, tarif_operasi, akun_rekening, rekening_tahun, "
                    + "posting_jurnal, buku_besar, cashflow, keuangan, pengeluaran, setup_pjlab, setup_otolokasi, setup_jam_kamin, "
                    + "setup_embalase, tracer_login, display, set_harga_obat, set_penggunaan_tarif, set_oto_ralan, biaya_harian, "
                    + "biaya_masuk_sekali, set_no_rm, billing_ralan, billing_ranap, jm_ranap_dokter, igd, barcoderalan, barcoderanap, "
                    + "set_harga_obat_ralan,set_harga_obat_ranap,penyakit_pd3i,surveilans_pd3i,surveilans_ralan,diagnosa_pasien, "
                    + "surveilans_ranap,pny_takmenular_ranap,pny_takmenular_ralan,kunjungan_ralan,rl32,rl33,rl37,rl38,harian_tindakan_dokter,sms, "
                    + "sidikjari,jam_masuk,jadwal_pegawai,parkir_barcode,set_nota,dpjp_ranap,mutasi_barang,rl34,rl36,"
                    + "fee_visit_dokter,fee_bacaan_ekg,fee_rujukan_rontgen,fee_rujukan_ranap,fee_ralan,akun_bayar,bayar_pemesanan_obat,"
                    + "obat_per_dokter_peresep,ipsrs_jenis_barang,pemasukan_lain,pengaturan_rekening,closing_kasir,keterlambatan_presensi,"
                    + "set_harga_kamar,rekap_per_shift,bpjs_cek_nik,bpjs_cek_kartu,bpjs_cek_riwayat,obat_per_cara_bayar,kunjungan_ranap,"
                    + "bayar_piutang,payment_point,bpjs_cek_nomor_rujukan,icd9,darurat_stok,retensi_rm,temporary_presensi,jurnal_harian, "
                    + "sirkulasi_obat2,edit_registrasi,bpjs_referensi_diagnosa,bpjs_referensi_poli,industrifarmasi,harian_js,bulanan_js,"
                    + "harian_paket_bhp,bulanan_paket_bhp,piutang_pasien2,bpjs_referensi_faskes,bpjs_sep,pengambilan_utd,tarif_utd, "
                    + "pengambilan_utd2,utd_medis_rusak,pengambilan_penunjang_utd,pengambilan_penunjang_utd2,utd_penunjang_rusak,"
                    + "suplier_penunjang,utd_donor,bpjs_monitoring_klaim,utd_cekal_darah,utd_komponen_darah,utd_stok_darah, "
                    + "utd_pemisahan_darah,harian_kamar,rincian_piutang_pasien,keuntungan_beri_obat_nonpiutang,reklasifikasi_ralan, "
                    + "reklasifikasi_ranap,utd_penyerahan_darah,hutang_obat,riwayat_obat_alkes_bhp,sensus_harian_poli,rl4a,aplicare_referensi_kamar, "
                    + "aplicare_ketersediaan_kamar,inacbg_klaim_baru_otomatis,inacbg_klaim_baru_manual,inacbg_coder_nik,mutasi_berkas, "
                    + "akun_piutang,harian_kso,bulanan_kso,harian_menejemen,bulanan_menejemen,inhealth_cek_eligibilitas,inhealth_referensi_jenpel_ruang_rawat, "
                    + "inhealth_referensi_poli,inhealth_referensi_faskes,inhealth_sjp,piutang_ralan,piutang_ranap,detail_piutang_penjab, "
                    + "lama_pelayanan_ralan,catatan_pasien,rl4b,rl4asebab,rl4bsebab,data_HAIs,harian_HAIs,bulanan_HAIs,hitung_bor,perusahaan_pasien, "
                    + "resep_dokter,lama_pelayanan_apotek,hitung_alos,detail_tindakan,rujukan_poli_internal,rekap_poli_anak,grafik_kunjungan_poli, "
                    + "grafik_kunjungan_perdokter,grafik_kunjungan_perpekerjaan,grafik_kunjungan_perpendidikan,grafik_kunjungan_pertahun,"
                    + "berkas_digital_perawatan,penyakit_menular_ranap,penyakit_menular_ralan,grafik_kunjungan_perbulan,grafik_kunjungan_pertanggal, "
                    + "grafik_kunjungan_demografi,grafik_kunjungan_statusdaftartahun,grafik_kunjungan_statusdaftartahun2, "
                    + "grafik_kunjungan_statusdaftarbulan,grafik_kunjungan_statusdaftarbulan2,grafik_kunjungan_statusdaftartanggal,"
                    + "grafik_kunjungan_statusdaftartanggal2,grafik_kunjungan_statusbataltahun,grafik_kunjungan_statusbatalbulan,"
                    + "pcare_cek_penyakit,grafik_kunjungan_statusbataltanggal,kategori_barang,golongan_barang,pemberian_obat_pertanggal,"
                    + "penjualan_obat_pertanggal,skdp_bpjs,rujukan_keluar_vclaim_bpjs,booking_registrasi,"
                    + "bpjs_cek_riwayat_rujukan_pcare,bpjs_cek_riwayat_rujukan_rs,bpjs_cek_rujukan_kartu_rs,"
                    + "bpjs_cek_tanggal_rujukan,bpjs_cek_no_rujukan_rs,bpjs_cek_rujukan_kartu_pcare,"
                    + "bpjs_cek_referensi_kelas_rawat,bpjs_cek_referensi_prosedur,bpjs_cek_referensi_dpjp,"
                    + "bpjs_cek_referensi_dokter,bpjs_cek_referensi_spesialistik,bpjs_cek_referensi_ruang_rawat,"
                    + "bpjs_cek_referensi_cara_keluar,bpjs_cek_referensi_pasca_pulang,bpjs_cek_referensi_propinsi,bpjs_cek_referensi_kabupaten,"
                    + "bpjs_cek_referensi_kecamatan,permintaan_lab,permintaan_radiologi,selisih_tarif_bpjs,edit_data_kematian,"
                    + "bridging_jamkesda,masuk_pindah_pulang_inap,masuk_pindah_inap,jumlah_macam_diet,jumlah_porsi_diet,status_gizi,"
                    + "gizi_buruk,master_faskes,set_status_registrasi_ralan,telusur_kunjungan_pasien,sisrute_rujukan_keluar,sisrute_rujukan_masuk,"
                    + "sisrute_referensi_diagnosa,sisrute_referensi_alasanrujuk,sisrute_referensi_faskes,barang_cssd,status_pulang_inap,data_persalinan,"
                    + "data_ponek,registrasi_booking_dikasir,bahasa_pasien,suku_pasien,harian_hais_ranap,bulanan_hais_ranap,harian_hais_ralan,"
                    + "bulanan_hais_ralan,ringkasan_pulang_ranap,laporan_farmasi,master_masalah_keperawatan,penilaian_awal_keperawatan_ralan,master_triase_skala1,"
                    + "master_triase_skala2,master_triase_skala3,master_triase_skala4,master_triase_skala5,master_triase_macamkasus,master_triase_pemeriksaan,"
                    + "data_triase_igd,master_cara_bayar,status_kerja_dokter,pasien_corona,diagnosa_pasien_corona,perawatan_pasien_corona,inacbg_klaim_baru_manual2,"
                    + "indikator_ranap,sensus_inap,review_rm_igd,review_rm_ruangan_h1,review_rm_ruangan_pulang,review_rm_laporan,assesmen_gizi_harian,"
                    + "assesmen_gizi_ulang,tombol_nota_billing,tombol_simpan_hasil_radiologi,monev_asuhan_gizi,inacbg_klaim_raza,"
                    + "pengajuan_klaim_inacbg_raza,copy_pemeriksaan_dokter_kepetugas_ralan,jkn_belum_diproses_klaim,input_kode_icd,indikator_mutu_unit,"
                    + "kendali_Mutu_kendali_Biaya_INACBG,dashboard_eResep,bpjs_sep_internal,kemenkes_sitt,rencana_kontrol_jkn,spri_jkn,hapus_sep,"
                    + "penilaian_awal_medis_ralan_kebidanan,penilaian_awal_keperawatan_kebidanan,ikhtisar_perawatan_hiv,survey_kepuasan,kemenkes_kanker,"
                    + "aktivasi_bridging,operator_antrian,penilaian_awal_medis_ralan_tht,rekam_psikologis,penilaian_pasien_geriatri,penilaian_awal_medis_ralan_mata,"
                    + "surat_sakit,surat_keterangan_kir_mcu,asesmen_medik_dewasa_ranap,pemberian_obat,cppt,bridging_satu_sehat,kemoterapi,cek_piutang,"
                    + "asesmen_medik_anak_ranap, kegiatan_operasi, asesmen_medik_bedah_ranap from user order by AES_DECRYPT(id_user,'nur')");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    user = "";
                    
                    if (user.equals("")) {
                        if (Sequel.cariInteger("select count(-1) from petugas where nip='" + rs.getString(1) + "'") > 0) {
                            user = Sequel.cariIsi("select nama from petugas where nip=?", rs.getString(1));
                            jabatan = Sequel.cariIsi("select nm_jbtn from jabatan where kd_jbtn=?", Sequel.cariIsi("select kd_jbtn from petugas where nip=?", rs.getString(1)));
                        } else if (Sequel.cariInteger("select count(-1) from petugas where user_id='" + rs.getString(1) + "'") > 0) {
                            user = Sequel.cariIsi("select nama from petugas where user_id=?", rs.getString(1));
                            jabatan = Sequel.cariIsi("select nm_jbtn from jabatan where kd_jbtn=?", Sequel.cariIsi("select kd_jbtn from petugas where user_id=?", rs.getString(1)));
                        }
                    }
                    
                    try {
                        if (rs.getString(1).toLowerCase().contains(TCari.getText().toLowerCase())
                                || user.toLowerCase().contains(TCari.getText().toLowerCase()) 
                                || jabatan.toLowerCase().contains(TCari.getText().toLowerCase())) {
                            tabMode.addRow(new Object[]{rs.getString(1),
                                user, jabatan, rs.getString(2),
                                rs.getBoolean("penyakit"),
                                rs.getBoolean("obat_penyakit"),
                                rs.getBoolean("dokter"),
                                rs.getBoolean("jadwal_praktek"),
                                rs.getBoolean("petugas"),
                                rs.getBoolean("pasien"),
                                rs.getBoolean("registrasi"),
                                rs.getBoolean("tindakan_ralan"),
                                rs.getBoolean("kamar_inap"),
                                rs.getBoolean("tindakan_ranap"),
                                rs.getBoolean("operasi"),
                                rs.getBoolean("rujukan_keluar"),
                                rs.getBoolean("rujukan_masuk"),
                                rs.getBoolean("beri_obat"),
                                rs.getBoolean("resep_pulang"),
                                rs.getBoolean("pasien_meninggal"),
                                rs.getBoolean("diet_pasien"),
                                rs.getBoolean("kelahiran_bayi"),
                                rs.getBoolean("periksa_lab"),
                                rs.getBoolean("periksa_radiologi"),
                                rs.getBoolean("kasir_ralan"),
                                rs.getBoolean("deposit_pasien"),
                                rs.getBoolean("piutang_pasien"),
                                rs.getBoolean("peminjaman_berkas"),
                                rs.getBoolean("barcode"),
                                rs.getBoolean("presensi_harian"),
                                rs.getBoolean("presensi_bulanan"),
                                rs.getBoolean("pegawai_admin"),
                                rs.getBoolean("pegawai_user"),
                                rs.getBoolean("suplier"),
                                rs.getBoolean("satuan_barang"),
                                rs.getBoolean("konversi_satuan"),
                                rs.getBoolean("jenis_barang"),
                                rs.getBoolean("obat"),
                                rs.getBoolean("stok_opname_obat"),
                                rs.getBoolean("stok_obat_pasien"),
                                rs.getBoolean("pengadaan_obat"),
                                rs.getBoolean("pemesanan_obat"),
                                rs.getBoolean("penjualan_obat"),
                                rs.getBoolean("piutang_obat"),
                                rs.getBoolean("retur_ke_suplier"),
                                rs.getBoolean("retur_dari_pembeli"),
                                rs.getBoolean("retur_obat_ranap"),
                                rs.getBoolean("retur_piutang_pasien"),
                                rs.getBoolean("keuntungan_penjualan"),
                                rs.getBoolean("keuntungan_beri_obat"),
                                rs.getBoolean("sirkulasi_obat"),
                                rs.getBoolean("ipsrs_barang"),
                                rs.getBoolean("ipsrs_pengadaan_barang"),
                                rs.getBoolean("ipsrs_stok_keluar"),
                                rs.getBoolean("ipsrs_rekap_pengadaan"),
                                rs.getBoolean("ipsrs_rekap_stok_keluar"),
                                rs.getBoolean("ipsrs_pengeluaran_harian"),
                                rs.getBoolean("inventaris_jenis"),
                                rs.getBoolean("inventaris_kategori"),
                                rs.getBoolean("inventaris_merk"),
                                rs.getBoolean("inventaris_ruang"),
                                rs.getBoolean("inventaris_produsen"),
                                rs.getBoolean("inventaris_koleksi"),
                                rs.getBoolean("inventaris_inventaris"),
                                rs.getBoolean("inventaris_sirkulasi"),
                                rs.getBoolean("parkir_jenis"),
                                rs.getBoolean("parkir_in"),
                                rs.getBoolean("parkir_out"),
                                rs.getBoolean("parkir_rekap_harian"),
                                rs.getBoolean("parkir_rekap_bulanan"),
                                rs.getBoolean("informasi_kamar"),
                                rs.getBoolean("harian_tindakan_poli"),
                                rs.getBoolean("obat_per_poli"),
                                rs.getBoolean("obat_per_kamar"),
                                rs.getBoolean("obat_per_dokter_ralan"),
                                rs.getBoolean("obat_per_dokter_ranap"),
                                rs.getBoolean("harian_dokter"),
                                rs.getBoolean("bulanan_dokter"),
                                rs.getBoolean("harian_paramedis"),
                                rs.getBoolean("bulanan_paramedis"),
                                rs.getBoolean("pembayaran_ralan"),
                                rs.getBoolean("pembayaran_ranap"),
                                rs.getBoolean("rekap_pembayaran_ralan"),
                                rs.getBoolean("rekap_pembayaran_ranap"),
                                rs.getBoolean("tagihan_masuk"),
                                rs.getBoolean("tambahan_biaya"),
                                rs.getBoolean("potongan_biaya"),
                                rs.getBoolean("resep_obat"),
                                rs.getBoolean("resume_pasien"),
                                rs.getBoolean("penyakit_ralan"),
                                rs.getBoolean("penyakit_ranap"),
                                rs.getBoolean("kamar"),
                                rs.getBoolean("tarif_ralan"),
                                rs.getBoolean("tarif_ranap"),
                                rs.getBoolean("tarif_lab"),
                                rs.getBoolean("tarif_radiologi"),
                                rs.getBoolean("tarif_operasi"),
                                rs.getBoolean("akun_rekening"),
                                rs.getBoolean("rekening_tahun"),
                                rs.getBoolean("posting_jurnal"),
                                rs.getBoolean("buku_besar"),
                                rs.getBoolean("cashflow"),
                                rs.getBoolean("keuangan"),
                                rs.getBoolean("pengeluaran"),
                                rs.getBoolean("setup_pjlab"),
                                rs.getBoolean("setup_otolokasi"),
                                rs.getBoolean("setup_jam_kamin"),
                                rs.getBoolean("setup_embalase"),
                                rs.getBoolean("tracer_login"),
                                rs.getBoolean("display"),
                                rs.getBoolean("set_harga_obat"),
                                rs.getBoolean("set_penggunaan_tarif"),
                                rs.getBoolean("set_oto_ralan"),
                                rs.getBoolean("biaya_harian"),
                                rs.getBoolean("biaya_masuk_sekali"),
                                rs.getBoolean("set_no_rm"),
                                rs.getBoolean("billing_ralan"),
                                rs.getBoolean("billing_ranap"),
                                rs.getBoolean("jm_ranap_dokter"),
                                rs.getBoolean("igd"),
                                rs.getBoolean("barcoderalan"),
                                rs.getBoolean("barcoderanap"),
                                rs.getBoolean("set_harga_obat_ralan"),
                                rs.getBoolean("set_harga_obat_ranap"),
                                rs.getBoolean("penyakit_pd3i"),
                                rs.getBoolean("surveilans_pd3i"),
                                rs.getBoolean("surveilans_ralan"),
                                rs.getBoolean("diagnosa_pasien"),
                                rs.getBoolean("surveilans_ranap"),
                                rs.getBoolean("pny_takmenular_ranap"),
                                rs.getBoolean("pny_takmenular_ralan"),
                                rs.getBoolean("kunjungan_ralan"),
                                rs.getBoolean("rl32"),
                                rs.getBoolean("rl33"),
                                rs.getBoolean("rl37"),
                                rs.getBoolean("rl38"),
                                rs.getBoolean("harian_tindakan_dokter"),
                                rs.getBoolean("sms"),
                                rs.getBoolean("sidikjari"),
                                rs.getBoolean("jam_masuk"),
                                rs.getBoolean("jadwal_pegawai"),
                                rs.getBoolean("parkir_barcode"),
                                rs.getBoolean("set_nota"),
                                rs.getBoolean("dpjp_ranap"),
                                rs.getBoolean("mutasi_barang"),
                                rs.getBoolean("rl34"),
                                rs.getBoolean("rl36"),
                                rs.getBoolean("fee_visit_dokter"),
                                rs.getBoolean("fee_bacaan_ekg"),
                                rs.getBoolean("fee_rujukan_rontgen"),
                                rs.getBoolean("fee_rujukan_ranap"),
                                rs.getBoolean("fee_ralan"),
                                rs.getBoolean("akun_bayar"),
                                rs.getBoolean("bayar_pemesanan_obat"),
                                rs.getBoolean("obat_per_dokter_peresep"),
                                rs.getBoolean("ipsrs_jenis_barang"),
                                rs.getBoolean("pemasukan_lain"),
                                rs.getBoolean("pengaturan_rekening"),
                                rs.getBoolean("closing_kasir"),
                                rs.getBoolean("keterlambatan_presensi"),
                                rs.getBoolean("set_harga_kamar"),
                                rs.getBoolean("rekap_per_shift"),
                                rs.getBoolean("bpjs_cek_nik"),
                                rs.getBoolean("bpjs_cek_kartu"),
                                rs.getBoolean("bpjs_cek_riwayat"),
                                rs.getBoolean("obat_per_cara_bayar"),
                                rs.getBoolean("kunjungan_ranap"),
                                rs.getBoolean("bayar_piutang"),
                                rs.getBoolean("payment_point"),
                                rs.getBoolean("bpjs_cek_nomor_rujukan"),
                                rs.getBoolean("icd9"),
                                rs.getBoolean("darurat_stok"),
                                rs.getBoolean("retensi_rm"),
                                rs.getBoolean("temporary_presensi"),
                                rs.getBoolean("jurnal_harian"),
                                rs.getBoolean("sirkulasi_obat2"),
                                rs.getBoolean("edit_registrasi"),
                                rs.getBoolean("bpjs_referensi_diagnosa"),
                                rs.getBoolean("bpjs_referensi_poli"),
                                rs.getBoolean("industrifarmasi"),
                                rs.getBoolean("harian_js"),
                                rs.getBoolean("bulanan_js"),
                                rs.getBoolean("harian_paket_bhp"),
                                rs.getBoolean("bulanan_paket_bhp"),
                                rs.getBoolean("piutang_pasien2"),
                                rs.getBoolean("bpjs_referensi_faskes"),
                                rs.getBoolean("bpjs_sep"),
                                rs.getBoolean("pengambilan_utd"),
                                rs.getBoolean("tarif_utd"),
                                rs.getBoolean("pengambilan_utd2"),
                                rs.getBoolean("utd_medis_rusak"),
                                rs.getBoolean("pengambilan_penunjang_utd"),
                                rs.getBoolean("pengambilan_penunjang_utd2"),
                                rs.getBoolean("utd_penunjang_rusak"),
                                rs.getBoolean("suplier_penunjang"),
                                rs.getBoolean("utd_donor"),
                                rs.getBoolean("bpjs_monitoring_klaim"),
                                rs.getBoolean("utd_cekal_darah"),
                                rs.getBoolean("utd_komponen_darah"),
                                rs.getBoolean("utd_stok_darah"),
                                rs.getBoolean("utd_pemisahan_darah"),
                                rs.getBoolean("harian_kamar"),
                                rs.getBoolean("rincian_piutang_pasien"),
                                rs.getBoolean("keuntungan_beri_obat_nonpiutang"),
                                rs.getBoolean("reklasifikasi_ralan"),
                                rs.getBoolean("reklasifikasi_ranap"),
                                rs.getBoolean("utd_penyerahan_darah"),
                                rs.getBoolean("hutang_obat"),
                                rs.getBoolean("riwayat_obat_alkes_bhp"),
                                rs.getBoolean("sensus_harian_poli"),
                                rs.getBoolean("rl4a"),
                                rs.getBoolean("aplicare_referensi_kamar"),
                                rs.getBoolean("aplicare_ketersediaan_kamar"),
                                rs.getBoolean("inacbg_klaim_baru_otomatis"),
                                rs.getBoolean("inacbg_klaim_baru_manual"),
                                rs.getBoolean("inacbg_coder_nik"),
                                rs.getBoolean("mutasi_berkas"),
                                rs.getBoolean("akun_piutang"),
                                rs.getBoolean("harian_kso"),
                                rs.getBoolean("bulanan_kso"),
                                rs.getBoolean("harian_menejemen"),
                                rs.getBoolean("bulanan_menejemen"),
                                rs.getBoolean("inhealth_cek_eligibilitas"),
                                rs.getBoolean("inhealth_referensi_jenpel_ruang_rawat"),
                                rs.getBoolean("inhealth_referensi_poli"),
                                rs.getBoolean("inhealth_referensi_faskes"),
                                rs.getBoolean("inhealth_sjp"),
                                rs.getBoolean("piutang_ralan"),
                                rs.getBoolean("piutang_ranap"),
                                rs.getBoolean("detail_piutang_penjab"),
                                rs.getBoolean("lama_pelayanan_ralan"),
                                rs.getBoolean("catatan_pasien"),
                                rs.getBoolean("rl4b"),
                                rs.getBoolean("rl4asebab"),
                                rs.getBoolean("rl4bsebab"),
                                rs.getBoolean("data_HAIs"),
                                rs.getBoolean("harian_HAIs"),
                                rs.getBoolean("bulanan_HAIs"),
                                rs.getBoolean("hitung_bor"),
                                rs.getBoolean("perusahaan_pasien"),
                                rs.getBoolean("resep_dokter"),
                                rs.getBoolean("lama_pelayanan_apotek"),
                                rs.getBoolean("hitung_alos"),
                                rs.getBoolean("detail_tindakan"),
                                rs.getBoolean("rujukan_poli_internal"),
                                rs.getBoolean("rekap_poli_anak"),
                                rs.getBoolean("grafik_kunjungan_poli"),
                                rs.getBoolean("grafik_kunjungan_perdokter"),
                                rs.getBoolean("grafik_kunjungan_perpekerjaan"),
                                rs.getBoolean("grafik_kunjungan_perpendidikan"),
                                rs.getBoolean("grafik_kunjungan_pertahun"),
                                rs.getBoolean("berkas_digital_perawatan"),
                                rs.getBoolean("penyakit_menular_ranap"),
                                rs.getBoolean("penyakit_menular_ralan"),
                                rs.getBoolean("grafik_kunjungan_perbulan"),
                                rs.getBoolean("grafik_kunjungan_pertanggal"),
                                rs.getBoolean("grafik_kunjungan_demografi"),
                                rs.getBoolean("grafik_kunjungan_statusdaftartahun"),
                                rs.getBoolean("grafik_kunjungan_statusdaftartahun2"),
                                rs.getBoolean("grafik_kunjungan_statusdaftarbulan"),
                                rs.getBoolean("grafik_kunjungan_statusdaftarbulan2"),
                                rs.getBoolean("grafik_kunjungan_statusdaftartanggal"),
                                rs.getBoolean("grafik_kunjungan_statusdaftartanggal2"),
                                rs.getBoolean("grafik_kunjungan_statusbataltahun"),
                                rs.getBoolean("grafik_kunjungan_statusbatalbulan"),
                                rs.getBoolean("pcare_cek_penyakit"),
                                rs.getBoolean("grafik_kunjungan_statusbataltanggal"),
                                rs.getBoolean("kategori_barang"),
                                rs.getBoolean("golongan_barang"),
                                rs.getBoolean("pemberian_obat_pertanggal"),
                                rs.getBoolean("penjualan_obat_pertanggal"),
                                rs.getBoolean("skdp_bpjs"),
                                rs.getBoolean("rujukan_keluar_vclaim_bpjs"),
                                rs.getBoolean("booking_registrasi"),
                                rs.getBoolean("bpjs_cek_riwayat_rujukan_pcare"),
                                rs.getBoolean("bpjs_cek_riwayat_rujukan_rs"),
                                rs.getBoolean("bpjs_cek_rujukan_kartu_rs"),
                                rs.getBoolean("bpjs_cek_tanggal_rujukan"),
                                rs.getBoolean("bpjs_cek_no_rujukan_rs"),
                                rs.getBoolean("bpjs_cek_rujukan_kartu_pcare"),
                                rs.getBoolean("bpjs_cek_referensi_kelas_rawat"),
                                rs.getBoolean("bpjs_cek_referensi_prosedur"),
                                rs.getBoolean("bpjs_cek_referensi_dpjp"),
                                rs.getBoolean("bpjs_cek_referensi_dokter"),
                                rs.getBoolean("bpjs_cek_referensi_spesialistik"),
                                rs.getBoolean("bpjs_cek_referensi_ruang_rawat"),
                                rs.getBoolean("bpjs_cek_referensi_cara_keluar"),
                                rs.getBoolean("bpjs_cek_referensi_pasca_pulang"),
                                rs.getBoolean("bpjs_cek_referensi_propinsi"),
                                rs.getBoolean("bpjs_cek_referensi_kabupaten"),
                                rs.getBoolean("bpjs_cek_referensi_kecamatan"),
                                rs.getBoolean("permintaan_lab"),
                                rs.getBoolean("permintaan_radiologi"),
                                rs.getBoolean("selisih_tarif_bpjs"),
                                rs.getBoolean("edit_data_kematian"),
                                rs.getBoolean("bridging_jamkesda"),
                                rs.getBoolean("masuk_pindah_pulang_inap"),
                                rs.getBoolean("masuk_pindah_inap"),
                                rs.getBoolean("jumlah_macam_diet"),
                                rs.getBoolean("jumlah_porsi_diet"),
                                rs.getBoolean("status_gizi"),
                                rs.getBoolean("gizi_buruk"),
                                rs.getBoolean("master_faskes"),
                                rs.getBoolean("set_status_registrasi_ralan"),
                                rs.getBoolean("telusur_kunjungan_pasien"),
                                rs.getBoolean("sisrute_rujukan_keluar"),
                                rs.getBoolean("sisrute_rujukan_masuk"),
                                rs.getBoolean("sisrute_referensi_diagnosa"),
                                rs.getBoolean("sisrute_referensi_alasanrujuk"),
                                rs.getBoolean("sisrute_referensi_faskes"),
                                rs.getBoolean("barang_cssd"),
                                rs.getBoolean("status_pulang_inap"),
                                rs.getBoolean("data_persalinan"),
                                rs.getBoolean("data_ponek"),
                                rs.getBoolean("registrasi_booking_dikasir"),
                                rs.getBoolean("bahasa_pasien"),
                                rs.getBoolean("suku_pasien"),
                                rs.getBoolean("harian_hais_ranap"),
                                rs.getBoolean("bulanan_hais_ranap"),
                                rs.getBoolean("harian_hais_ralan"),
                                rs.getBoolean("bulanan_hais_ralan"),
                                rs.getBoolean("ringkasan_pulang_ranap"),
                                rs.getBoolean("laporan_farmasi"),
                                rs.getBoolean("master_masalah_keperawatan"),
                                rs.getBoolean("penilaian_awal_keperawatan_ralan"),
                                rs.getBoolean("master_triase_skala1"),
                                rs.getBoolean("master_triase_skala2"),
                                rs.getBoolean("master_triase_skala3"),
                                rs.getBoolean("master_triase_skala4"),
                                rs.getBoolean("master_triase_skala5"),
                                rs.getBoolean("master_triase_macamkasus"),
                                rs.getBoolean("master_triase_pemeriksaan"),
                                rs.getBoolean("data_triase_igd"),
                                rs.getBoolean("master_cara_bayar"),
                                rs.getBoolean("status_kerja_dokter"),
                                rs.getBoolean("pasien_corona"),
                                rs.getBoolean("diagnosa_pasien_corona"),
                                rs.getBoolean("perawatan_pasien_corona"),
                                rs.getBoolean("inacbg_klaim_baru_manual2"),                                
                                rs.getBoolean("indikator_ranap"),
                                rs.getBoolean("sensus_inap"),
                                rs.getBoolean("review_rm_igd"),
                                rs.getBoolean("review_rm_ruangan_h1"),
                                rs.getBoolean("review_rm_ruangan_pulang"),
                                rs.getBoolean("review_rm_laporan"),
                                rs.getBoolean("assesmen_gizi_harian"),
                                rs.getBoolean("assesmen_gizi_ulang"),
                                rs.getBoolean("tombol_nota_billing"),
                                rs.getBoolean("tombol_simpan_hasil_radiologi"),
                                rs.getBoolean("monev_asuhan_gizi"),
                                rs.getBoolean("inacbg_klaim_raza"),
                                rs.getBoolean("pengajuan_klaim_inacbg_raza"),
                                rs.getBoolean("copy_pemeriksaan_dokter_kepetugas_ralan"),
                                rs.getBoolean("jkn_belum_diproses_klaim"),
                                rs.getBoolean("input_kode_icd"),
                                rs.getBoolean("indikator_mutu_unit"),
                                rs.getBoolean("kendali_Mutu_kendali_Biaya_INACBG"),
                                rs.getBoolean("dashboard_eResep"),
                                rs.getBoolean("bpjs_sep_internal"),
                                rs.getBoolean("kemenkes_sitt"),
                                rs.getBoolean("rencana_kontrol_jkn"),
                                rs.getBoolean("spri_jkn"),
                                rs.getBoolean("hapus_sep"),
                                rs.getBoolean("penilaian_awal_medis_ralan_kebidanan"),
                                rs.getBoolean("penilaian_awal_keperawatan_kebidanan"),
                                rs.getBoolean("ikhtisar_perawatan_hiv"),
                                rs.getBoolean("survey_kepuasan"),
                                rs.getBoolean("kemenkes_kanker"),
                                rs.getBoolean("aktivasi_bridging"),
                                rs.getBoolean("operator_antrian"),
                                rs.getBoolean("penilaian_awal_medis_ralan_tht"),
                                rs.getBoolean("rekam_psikologis"),
                                rs.getBoolean("penilaian_pasien_geriatri"),
                                rs.getBoolean("penilaian_awal_medis_ralan_mata"),
                                rs.getBoolean("surat_sakit"),
                                rs.getBoolean("surat_keterangan_kir_mcu"),
                                rs.getBoolean("asesmen_medik_dewasa_ranap"),
                                rs.getBoolean("pemberian_obat"),
                                rs.getBoolean("cppt"),
                                rs.getBoolean("bridging_satu_sehat"),
                                rs.getBoolean("kemoterapi"),
                                rs.getBoolean("cek_piutang"),
                                rs.getBoolean("asesmen_medik_anak_ranap"),
                                rs.getBoolean("kegiatan_operasi"),
                                rs.getBoolean("asesmen_medik_bedah_ranap")
                            });
                        }
                    } catch (Exception e) {
                        tabMode.addRow(new Object[]{rs.getString(1),
                            "Turn Out", "Jabatan", rs.getString(2),
                            rs.getBoolean("penyakit"),
                            rs.getBoolean("obat_penyakit"),
                            rs.getBoolean("dokter"),
                            rs.getBoolean("jadwal_praktek"),
                            rs.getBoolean("petugas"),
                            rs.getBoolean("pasien"),
                            rs.getBoolean("registrasi"),
                            rs.getBoolean("tindakan_ralan"),
                            rs.getBoolean("kamar_inap"),
                            rs.getBoolean("tindakan_ranap"),
                            rs.getBoolean("operasi"),
                            rs.getBoolean("rujukan_keluar"),
                            rs.getBoolean("rujukan_masuk"),
                            rs.getBoolean("beri_obat"),
                            rs.getBoolean("resep_pulang"),
                            rs.getBoolean("pasien_meninggal"),
                            rs.getBoolean("diet_pasien"),
                            rs.getBoolean("kelahiran_bayi"),
                            rs.getBoolean("periksa_lab"),
                            rs.getBoolean("periksa_radiologi"),
                            rs.getBoolean("kasir_ralan"),
                            rs.getBoolean("deposit_pasien"),
                            rs.getBoolean("piutang_pasien"),
                            rs.getBoolean("peminjaman_berkas"),
                            rs.getBoolean("barcode"),
                            rs.getBoolean("presensi_harian"),
                            rs.getBoolean("presensi_bulanan"),
                            rs.getBoolean("pegawai_admin"),
                            rs.getBoolean("pegawai_user"),
                            rs.getBoolean("suplier"),
                            rs.getBoolean("satuan_barang"),
                            rs.getBoolean("konversi_satuan"),
                            rs.getBoolean("jenis_barang"),
                            rs.getBoolean("obat"),
                            rs.getBoolean("stok_opname_obat"),
                            rs.getBoolean("stok_obat_pasien"),
                            rs.getBoolean("pengadaan_obat"),
                            rs.getBoolean("pemesanan_obat"),
                            rs.getBoolean("penjualan_obat"),
                            rs.getBoolean("piutang_obat"),
                            rs.getBoolean("retur_ke_suplier"),
                            rs.getBoolean("retur_dari_pembeli"),
                            rs.getBoolean("retur_obat_ranap"),
                            rs.getBoolean("retur_piutang_pasien"),
                            rs.getBoolean("keuntungan_penjualan"),
                            rs.getBoolean("keuntungan_beri_obat"),
                            rs.getBoolean("sirkulasi_obat"),
                            rs.getBoolean("ipsrs_barang"),
                            rs.getBoolean("ipsrs_pengadaan_barang"),
                            rs.getBoolean("ipsrs_stok_keluar"),
                            rs.getBoolean("ipsrs_rekap_pengadaan"),
                            rs.getBoolean("ipsrs_rekap_stok_keluar"),
                            rs.getBoolean("ipsrs_pengeluaran_harian"),
                            rs.getBoolean("inventaris_jenis"),
                            rs.getBoolean("inventaris_kategori"),
                            rs.getBoolean("inventaris_merk"),
                            rs.getBoolean("inventaris_ruang"),
                            rs.getBoolean("inventaris_produsen"),
                            rs.getBoolean("inventaris_koleksi"),
                            rs.getBoolean("inventaris_inventaris"),
                            rs.getBoolean("inventaris_sirkulasi"),
                            rs.getBoolean("parkir_jenis"),
                            rs.getBoolean("parkir_in"),
                            rs.getBoolean("parkir_out"),
                            rs.getBoolean("parkir_rekap_harian"),
                            rs.getBoolean("parkir_rekap_bulanan"),
                            rs.getBoolean("informasi_kamar"),
                            rs.getBoolean("harian_tindakan_poli"),
                            rs.getBoolean("obat_per_poli"),
                            rs.getBoolean("obat_per_kamar"),
                            rs.getBoolean("obat_per_dokter_ralan"),
                            rs.getBoolean("obat_per_dokter_ranap"),
                            rs.getBoolean("harian_dokter"),
                            rs.getBoolean("bulanan_dokter"),
                            rs.getBoolean("harian_paramedis"),
                            rs.getBoolean("bulanan_paramedis"),
                            rs.getBoolean("pembayaran_ralan"),
                            rs.getBoolean("pembayaran_ranap"),
                            rs.getBoolean("rekap_pembayaran_ralan"),
                            rs.getBoolean("rekap_pembayaran_ranap"),
                            rs.getBoolean("tagihan_masuk"),
                            rs.getBoolean("tambahan_biaya"),
                            rs.getBoolean("potongan_biaya"),
                            rs.getBoolean("resep_obat"),
                            rs.getBoolean("resume_pasien"),
                            rs.getBoolean("penyakit_ralan"),
                            rs.getBoolean("penyakit_ranap"),
                            rs.getBoolean("kamar"),
                            rs.getBoolean("tarif_ralan"),
                            rs.getBoolean("tarif_ranap"),
                            rs.getBoolean("tarif_lab"),
                            rs.getBoolean("tarif_radiologi"),
                            rs.getBoolean("tarif_operasi"),
                            rs.getBoolean("akun_rekening"),
                            rs.getBoolean("rekening_tahun"),
                            rs.getBoolean("posting_jurnal"),
                            rs.getBoolean("buku_besar"),
                            rs.getBoolean("cashflow"),
                            rs.getBoolean("keuangan"),
                            rs.getBoolean("pengeluaran"),
                            rs.getBoolean("setup_pjlab"),
                            rs.getBoolean("setup_otolokasi"),
                            rs.getBoolean("setup_jam_kamin"),
                            rs.getBoolean("setup_embalase"),
                            rs.getBoolean("tracer_login"),
                            rs.getBoolean("display"),
                            rs.getBoolean("set_harga_obat"),
                            rs.getBoolean("set_penggunaan_tarif"),
                            rs.getBoolean("set_oto_ralan"),
                            rs.getBoolean("biaya_harian"),
                            rs.getBoolean("biaya_masuk_sekali"),
                            rs.getBoolean("set_no_rm"),
                            rs.getBoolean("billing_ralan"),
                            rs.getBoolean("billing_ranap"),
                            rs.getBoolean("jm_ranap_dokter"),
                            rs.getBoolean("igd"),
                            rs.getBoolean("barcoderalan"),
                            rs.getBoolean("barcoderanap"),
                            rs.getBoolean("set_harga_obat_ralan"),
                            rs.getBoolean("set_harga_obat_ranap"),
                            rs.getBoolean("penyakit_pd3i"),
                            rs.getBoolean("surveilans_pd3i"),
                            rs.getBoolean("surveilans_ralan"),
                            rs.getBoolean("diagnosa_pasien"),
                            rs.getBoolean("surveilans_ranap"),
                            rs.getBoolean("pny_takmenular_ranap"),
                            rs.getBoolean("pny_takmenular_ralan"),
                            rs.getBoolean("kunjungan_ralan"),
                            rs.getBoolean("rl32"),
                            rs.getBoolean("rl33"),
                            rs.getBoolean("rl37"),
                            rs.getBoolean("rl38"),
                            rs.getBoolean("harian_tindakan_dokter"),
                            rs.getBoolean("sms"),
                            rs.getBoolean("sidikjari"),
                            rs.getBoolean("jam_masuk"),
                            rs.getBoolean("jadwal_pegawai"),
                            rs.getBoolean("parkir_barcode"),
                            rs.getBoolean("set_nota"),
                            rs.getBoolean("dpjp_ranap"),
                            rs.getBoolean("mutasi_barang"),
                            rs.getBoolean("rl34"),
                            rs.getBoolean("rl36"),
                            rs.getBoolean("fee_visit_dokter"),
                            rs.getBoolean("fee_bacaan_ekg"),
                            rs.getBoolean("fee_rujukan_rontgen"),
                            rs.getBoolean("fee_rujukan_ranap"),
                            rs.getBoolean("fee_ralan"),
                            rs.getBoolean("akun_bayar"),
                            rs.getBoolean("bayar_pemesanan_obat"),
                            rs.getBoolean("obat_per_dokter_peresep"),
                            rs.getBoolean("ipsrs_jenis_barang"),
                            rs.getBoolean("pemasukan_lain"),
                            rs.getBoolean("pengaturan_rekening"),
                            rs.getBoolean("closing_kasir"),
                            rs.getBoolean("keterlambatan_presensi"),
                            rs.getBoolean("set_harga_kamar"),
                            rs.getBoolean("rekap_per_shift"),
                            rs.getBoolean("bpjs_cek_nik"),
                            rs.getBoolean("bpjs_cek_kartu"),
                            rs.getBoolean("bpjs_cek_riwayat"),
                            rs.getBoolean("obat_per_cara_bayar"),
                            rs.getBoolean("kunjungan_ranap"),
                            rs.getBoolean("bayar_piutang"),
                            rs.getBoolean("payment_point"),
                            rs.getBoolean("bpjs_cek_nomor_rujukan"),
                            rs.getBoolean("icd9"),
                            rs.getBoolean("darurat_stok"),
                            rs.getBoolean("retensi_rm"),
                            rs.getBoolean("temporary_presensi"),
                            rs.getBoolean("jurnal_harian"),
                            rs.getBoolean("sirkulasi_obat2"),
                            rs.getBoolean("edit_registrasi"),
                            rs.getBoolean("bpjs_referensi_diagnosa"),
                            rs.getBoolean("bpjs_referensi_poli"),
                            rs.getBoolean("industrifarmasi"),
                            rs.getBoolean("harian_js"),
                            rs.getBoolean("bulanan_js"),
                            rs.getBoolean("harian_paket_bhp"),
                            rs.getBoolean("bulanan_paket_bhp"),
                            rs.getBoolean("piutang_pasien2"),
                            rs.getBoolean("bpjs_referensi_faskes"),
                            rs.getBoolean("bpjs_sep"),
                            rs.getBoolean("pengambilan_utd"),
                            rs.getBoolean("tarif_utd"),
                            rs.getBoolean("pengambilan_utd2"),
                            rs.getBoolean("utd_medis_rusak"),
                            rs.getBoolean("pengambilan_penunjang_utd"),
                            rs.getBoolean("pengambilan_penunjang_utd2"),
                            rs.getBoolean("utd_penunjang_rusak"),
                            rs.getBoolean("suplier_penunjang"),
                            rs.getBoolean("utd_donor"),
                            rs.getBoolean("bpjs_monitoring_klaim"),
                            rs.getBoolean("utd_cekal_darah"),
                            rs.getBoolean("utd_komponen_darah"),
                            rs.getBoolean("utd_stok_darah"),
                            rs.getBoolean("utd_pemisahan_darah"),
                            rs.getBoolean("harian_kamar"),
                            rs.getBoolean("rincian_piutang_pasien"),
                            rs.getBoolean("keuntungan_beri_obat_nonpiutang"),
                            rs.getBoolean("reklasifikasi_ralan"),
                            rs.getBoolean("reklasifikasi_ranap"),
                            rs.getBoolean("utd_penyerahan_darah"),
                            rs.getBoolean("hutang_obat"),
                            rs.getBoolean("riwayat_obat_alkes_bhp"),
                            rs.getBoolean("sensus_harian_poli"),
                            rs.getBoolean("rl4a"),
                            rs.getBoolean("aplicare_referensi_kamar"),
                            rs.getBoolean("aplicare_ketersediaan_kamar"),
                            rs.getBoolean("inacbg_klaim_baru_otomatis"),
                            rs.getBoolean("inacbg_klaim_baru_manual"),
                            rs.getBoolean("inacbg_coder_nik"),
                            rs.getBoolean("mutasi_berkas"),
                            rs.getBoolean("akun_piutang"),
                            rs.getBoolean("harian_kso"),
                            rs.getBoolean("bulanan_kso"),
                            rs.getBoolean("harian_menejemen"),
                            rs.getBoolean("bulanan_menejemen"),
                            rs.getBoolean("inhealth_cek_eligibilitas"),
                            rs.getBoolean("inhealth_referensi_jenpel_ruang_rawat"),
                            rs.getBoolean("inhealth_referensi_poli"),
                            rs.getBoolean("inhealth_referensi_faskes"),
                            rs.getBoolean("inhealth_sjp"),
                            rs.getBoolean("piutang_ralan"),
                            rs.getBoolean("piutang_ranap"),
                            rs.getBoolean("detail_piutang_penjab"),
                            rs.getBoolean("lama_pelayanan_ralan"),
                            rs.getBoolean("catatan_pasien"),
                            rs.getBoolean("rl4b"),
                            rs.getBoolean("rl4asebab"),
                            rs.getBoolean("rl4bsebab"),
                            rs.getBoolean("data_HAIs"),
                            rs.getBoolean("harian_HAIs"),
                            rs.getBoolean("bulanan_HAIs"),
                            rs.getBoolean("hitung_bor"),
                            rs.getBoolean("perusahaan_pasien"),
                            rs.getBoolean("resep_dokter"),
                            rs.getBoolean("lama_pelayanan_apotek"),
                            rs.getBoolean("hitung_alos"),
                            rs.getBoolean("detail_tindakan"),
                            rs.getBoolean("rujukan_poli_internal"),
                            rs.getBoolean("rekap_poli_anak"),
                            rs.getBoolean("grafik_kunjungan_poli"),
                            rs.getBoolean("grafik_kunjungan_perdokter"),
                            rs.getBoolean("grafik_kunjungan_perpekerjaan"),
                            rs.getBoolean("grafik_kunjungan_perpendidikan"),
                            rs.getBoolean("grafik_kunjungan_pertahun"),
                            rs.getBoolean("berkas_digital_perawatan"),
                            rs.getBoolean("penyakit_menular_ranap"),
                            rs.getBoolean("penyakit_menular_ralan"),
                            rs.getBoolean("grafik_kunjungan_perbulan"),
                            rs.getBoolean("grafik_kunjungan_pertanggal"),
                            rs.getBoolean("grafik_kunjungan_demografi"),
                            rs.getBoolean("grafik_kunjungan_statusdaftartahun"),
                            rs.getBoolean("grafik_kunjungan_statusdaftartahun2"),
                            rs.getBoolean("grafik_kunjungan_statusdaftarbulan"),
                            rs.getBoolean("grafik_kunjungan_statusdaftarbulan2"),
                            rs.getBoolean("grafik_kunjungan_statusdaftartanggal"),
                            rs.getBoolean("grafik_kunjungan_statusdaftartanggal2"),
                            rs.getBoolean("grafik_kunjungan_statusbataltahun"),
                            rs.getBoolean("grafik_kunjungan_statusbatalbulan"),
                            rs.getBoolean("pcare_cek_penyakit"),
                            rs.getBoolean("grafik_kunjungan_statusbataltanggal"),
                            rs.getBoolean("kategori_barang"),
                            rs.getBoolean("golongan_barang"),
                            rs.getBoolean("pemberian_obat_pertanggal"),
                            rs.getBoolean("penjualan_obat_pertanggal"),
                            rs.getBoolean("skdp_bpjs"),
                            rs.getBoolean("rujukan_keluar_vclaim_bpjs"),
                            rs.getBoolean("booking_registrasi"),
                            rs.getBoolean("bpjs_cek_riwayat_rujukan_pcare"),
                            rs.getBoolean("bpjs_cek_riwayat_rujukan_rs"),
                            rs.getBoolean("bpjs_cek_rujukan_kartu_rs"),
                            rs.getBoolean("bpjs_cek_tanggal_rujukan"),
                            rs.getBoolean("bpjs_cek_no_rujukan_rs"),
                            rs.getBoolean("bpjs_cek_rujukan_kartu_pcare"),
                            rs.getBoolean("bpjs_cek_referensi_kelas_rawat"),
                            rs.getBoolean("bpjs_cek_referensi_prosedur"),
                            rs.getBoolean("bpjs_cek_referensi_dpjp"),
                            rs.getBoolean("bpjs_cek_referensi_dokter"),
                            rs.getBoolean("bpjs_cek_referensi_spesialistik"),
                            rs.getBoolean("bpjs_cek_referensi_ruang_rawat"),
                            rs.getBoolean("bpjs_cek_referensi_cara_keluar"),
                            rs.getBoolean("bpjs_cek_referensi_pasca_pulang"),
                            rs.getBoolean("bpjs_cek_referensi_propinsi"),
                            rs.getBoolean("bpjs_cek_referensi_kabupaten"),
                            rs.getBoolean("bpjs_cek_referensi_kecamatan"),
                            rs.getBoolean("permintaan_lab"),
                            rs.getBoolean("permintaan_radiologi"),
                            rs.getBoolean("selisih_tarif_bpjs"),
                            rs.getBoolean("edit_data_kematian"),
                            rs.getBoolean("bridging_jamkesda"),
                            rs.getBoolean("masuk_pindah_pulang_inap"),
                            rs.getBoolean("masuk_pindah_inap"),
                            rs.getBoolean("jumlah_macam_diet"),
                            rs.getBoolean("jumlah_porsi_diet"),
                            rs.getBoolean("status_gizi"),
                            rs.getBoolean("gizi_buruk"),
                            rs.getBoolean("master_faskes"),
                            rs.getBoolean("set_status_registrasi_ralan"),
                            rs.getBoolean("telusur_kunjungan_pasien"),
                            rs.getBoolean("sisrute_rujukan_keluar"),
                            rs.getBoolean("sisrute_rujukan_masuk"),
                            rs.getBoolean("sisrute_referensi_diagnosa"),
                            rs.getBoolean("sisrute_referensi_alasanrujuk"),
                            rs.getBoolean("sisrute_referensi_faskes"),
                            rs.getBoolean("barang_cssd"),
                            rs.getBoolean("status_pulang_inap"),
                            rs.getBoolean("data_persalinan"),
                            rs.getBoolean("data_ponek"),
                            rs.getBoolean("registrasi_booking_dikasir"),
                            rs.getBoolean("bahasa_pasien"),
                            rs.getBoolean("suku_pasien"),
                            rs.getBoolean("harian_hais_ranap"),
                            rs.getBoolean("bulanan_hais_ranap"),
                            rs.getBoolean("harian_hais_ralan"),
                            rs.getBoolean("bulanan_hais_ralan"),
                            rs.getBoolean("ringkasan_pulang_ranap"),
                            rs.getBoolean("laporan_farmasi"),
                            rs.getBoolean("master_masalah_keperawatan"),
                            rs.getBoolean("penilaian_awal_keperawatan_ralan"),
                            rs.getBoolean("master_triase_skala1"),
                            rs.getBoolean("master_triase_skala2"),
                            rs.getBoolean("master_triase_skala3"),
                            rs.getBoolean("master_triase_skala4"),
                            rs.getBoolean("master_triase_skala5"),
                            rs.getBoolean("master_triase_macamkasus"),
                            rs.getBoolean("master_triase_pemeriksaan"),
                            rs.getBoolean("data_triase_igd"),
                            rs.getBoolean("master_cara_bayar"),
                            rs.getBoolean("status_kerja_dokter"),
                            rs.getBoolean("pasien_corona"),
                            rs.getBoolean("diagnosa_pasien_corona"),
                            rs.getBoolean("perawatan_pasien_corona"),
                            rs.getBoolean("inacbg_klaim_baru_manual2"),
                            rs.getBoolean("indikator_ranap"),
                            rs.getBoolean("sensus_inap"),
                            rs.getBoolean("review_rm_igd"),
                            rs.getBoolean("review_rm_ruangan_h1"),
                            rs.getBoolean("review_rm_ruangan_pulang"),
                            rs.getBoolean("review_rm_laporan"),
                            rs.getBoolean("assesmen_gizi_harian"),
                            rs.getBoolean("assesmen_gizi_ulang"),
                            rs.getBoolean("tombol_nota_billing"),
                            rs.getBoolean("tombol_simpan_hasil_radiologi"),
                            rs.getBoolean("monev_asuhan_gizi"),
                            rs.getBoolean("inacbg_klaim_raza"),
                            rs.getBoolean("pengajuan_klaim_inacbg_raza"),
                            rs.getBoolean("copy_pemeriksaan_dokter_kepetugas_ralan"),
                            rs.getBoolean("jkn_belum_diproses_klaim"),
                            rs.getBoolean("input_kode_icd"),
                            rs.getBoolean("indikator_mutu_unit"),
                            rs.getBoolean("kendali_Mutu_kendali_Biaya_INACBG"),
                            rs.getBoolean("dashboard_eResep"),
                            rs.getBoolean("bpjs_sep_internal"),
                            rs.getBoolean("kemenkes_sitt"),
                            rs.getBoolean("rencana_kontrol_jkn"),
                            rs.getBoolean("spri_jkn"),
                            rs.getBoolean("hapus_sep"),
                            rs.getBoolean("penilaian_awal_medis_ralan_kebidanan"),
                            rs.getBoolean("penilaian_awal_keperawatan_kebidanan"),
                            rs.getBoolean("ikhtisar_perawatan_hiv"),
                            rs.getBoolean("survey_kepuasan"),
                            rs.getBoolean("kemenkes_kanker"),
                            rs.getBoolean("aktivasi_bridging"),
                            rs.getBoolean("operator_antrian"),
                            rs.getBoolean("penilaian_awal_medis_ralan_tht"),
                            rs.getBoolean("rekam_psikologis"),
                            rs.getBoolean("penilaian_pasien_geriatri"),
                            rs.getBoolean("penilaian_awal_medis_ralan_mata"),
                            rs.getBoolean("surat_sakit"),
                            rs.getBoolean("surat_keterangan_kir_mcu"),
                            rs.getBoolean("asesmen_medik_dewasa_ranap"),
                            rs.getBoolean("pemberian_obat"),
                            rs.getBoolean("cppt"),
                            rs.getBoolean("bridging_satu_sehat"),
                            rs.getBoolean("kemoterapi"),
                            rs.getBoolean("cek_piutang"),
                            rs.getBoolean("asesmen_medik_anak_ranap"),
                            rs.getBoolean("kegiatan_operasi"),
                            rs.getBoolean("asesmen_medik_bedah_ranap")
                        });
                    }                                             
                 }
                LCount.setText(""+tabMode.getRowCount());
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
                        
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    private void isUser() {
        try {
            rs = koneksi.prepareStatement("select nm_dokter from dokter where kd_dokter='" + TKd.getText() + "'").executeQuery();
            if (rs.next()) {
                TNmUser.setText(rs.getString(1));
            } else if (!rs.next()) {
                rs = koneksi.prepareStatement("select nama from petugas where nip='" + TKd.getText() + "' or user_id='" + TKd.getText() + "'").executeQuery();
                if (rs.next()) {
                    TNmUser.setText(rs.getString(1));
                } else if (!rs.next()) {
                    TNmUser.setText("");
                }
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        TKd.setText("");
        TPass.setText("");
        TCari.requestFocus();
        TCari.setText("");
        TCari1.setText("");
        kodeBaris.setText("");

        cekUnit.setSelected(false);
        cekUnit.setText("Unit R. Inap : RUANGAN");
        kdpoli.setVisible(false);
        nmpoli.setVisible(false);
        BtnSeek2.setVisible(false);
        NmRuangan.setVisible(true);
        NmRuangan.setSelectedIndex(0);
        cmbSemua.setSelectedIndex(0);
    }

    private void getData() {
        i=tbUser.getSelectedRow();
        if(i!= -1){
            TKd.setText(tbUser.getValueAt(i,0).toString());
            TPass.setText(tbUser.getValueAt(i,3).toString());
        }
    }
    
    private void tampil1() { 
        cari1 = "h.kd_baris like '%" + TCari1.getText() + "%'";
        cari2 = "h.nip like '%" + TCari1.getText() + "%'";
        cari3 = "p.nama like '%" + TCari1.getText() + "%'";
        cari4 = "h.kode_unit like '%" + TCari1.getText() + "%'";
        cari5 = "pl.nm_poli like '%" + TCari1.getText() + "%'";        
        cari6 = "(IF(h.kode_unit=pl.kd_poli,'Rawat Jalan','Rawat Inap')) like '%" + TCari1.getText() + "%'";        
                        
        Valid.tabelKosong(tabMode1);
        try {
            if (TCari1.getText().equals("")) {
                ps1 = koneksi.prepareStatement("SELECT h.kd_baris, h.nip, p.nama, h.kode_unit, "
                        + "UPPER(if(h.kode_unit=pl.kd_poli,pl.nm_poli,h.kode_unit)) unit "
                        + " FROM hak_akses_unit h INNER JOIN petugas p ON p.user_id = h.nip "
                        + " LEFT JOIN poliklinik pl ON pl.kd_poli = h.kode_unit ORDER BY h.kd_baris");

            } else if (!TCari1.getText().equals("")) {
                mencari = cari1 + " or "
                        + cari2 + " or "
                        + cari3 + " or "
                        + cari4 + " or "
                        + cari5 + " or "
                        + cari6;

                ps1 = koneksi.prepareStatement("SELECT h.kd_baris, h.nip, p.nama, h.kode_unit, "
                        + "UPPER(if(h.kode_unit=pl.kd_poli,pl.nm_poli,h.kode_unit)) unit "
                        + " FROM hak_akses_unit h INNER JOIN petugas p ON p.user_id = h.nip "
                        + " LEFT JOIN poliklinik pl ON pl.kd_poli = h.kode_unit "
                        + " WHERE " + mencari + " ORDER BY h.kd_baris");
            }
            
            try {                
                rs = ps1.executeQuery();
                while (rs.next()) {                    
                    tabMode1.addRow(new Object[]{
                        rs.getString(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)                        
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgUser.tampil1() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        int b = tabMode1.getRowCount();
        LCount1.setText("" + b);
    }

    public void getData1() {
        if (tbUser1.getSelectedRow() != -1) {
            kodeBaris.setText(tbUser1.getValueAt(tbUser1.getSelectedRow(), 0).toString());
            TKd.setText(tbUser1.getValueAt(tbUser1.getSelectedRow(), 1).toString());
            TNmUser.setText(tbUser1.getValueAt(tbUser1.getSelectedRow(), 2).toString());
        }
    }
}
