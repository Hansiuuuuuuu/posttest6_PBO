package ilmu;

import java.util.ArrayList;
import java.util.Scanner;

class Tiket {
    protected String namaPembeli;
    protected String jenisTiket;
    protected int jumlahTiket;

    
    public Tiket(String namaPembeli, String jenisTiket, int jumlahTiket) {
        this.namaPembeli = namaPembeli;
        this.jenisTiket = jenisTiket;
        this.jumlahTiket = jumlahTiket;
    }

  
    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    
    public String getJenisTiket() {
        return jenisTiket;
    }

    public void setJenisTiket(String jenisTiket) {
        this.jenisTiket = jenisTiket;
    }

    
    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }

    @Override
    public String toString() {
        return "Nama Pembeli: " + namaPembeli + ", Jenis Tiket: " + jenisTiket + ", Jumlah Tiket: " + jumlahTiket;
    }
}

interface Pembayaran {
    double hitungTotalPembayaran();
    void konfirmasiPembayaran();
}

class TiketBiasa extends Tiket implements Pembayaran {
   
    private static final double HARGA_TIKET_BIASA = 100.0;

    private boolean makananGratis;

    public TiketBiasa(String namaPembeli, String jenisTiket, int jumlahTiket, boolean makananGratis) {
        super(namaPembeli, jenisTiket, jumlahTiket);
        this.makananGratis = makananGratis;
    }

    @Override
    public double hitungTotalPembayaran() {
        double total = jumlahTiket * HARGA_TIKET_BIASA;
        if (makananGratis) {
            total += 50.0; // Biaya tambahan untuk makanan gratis
        }
        return total;
    }

    @Override
    public void konfirmasiPembayaran() {
        System.out.println("Pembayaran Tiket Biasa telah dikonfirmasi.");
    }

    // Metode static untuk menghitung total pembayaran tanpa membuat objek TiketBiasa
    public static double hitungTotalPembayaran(int jumlahTiket, boolean makananGratis) {
        double total = jumlahTiket * HARGA_TIKET_BIASA;
        if (makananGratis) {
            total += 50.0; 
        }
        return total;
    }
   
    public boolean isMakananGratis() {
        return makananGratis;
    }

    public void setMakananGratis(boolean makananGratis) {
        this.makananGratis = makananGratis;
    }

    @Override
    public String toString() {
        return super.toString() + ", Makanan Gratis: " + (makananGratis ? "Ya" : "Tidak");
    }
    public void setMakananGratis() {
        this.makananGratis = true;
    }
}


class TiketVIP extends Tiket implements Pembayaran {
    
    private static final double HARGA_TIKET_VIP = 200.0;

    private String fasilitasVIP;

    public TiketVIP(String namaPembeli, String jenisTiket, int jumlahTiket, String fasilitasVIP) {
        super(namaPembeli, jenisTiket, jumlahTiket);
        this.fasilitasVIP = fasilitasVIP;
    }

    
    @Override
    public double hitungTotalPembayaran() {
        return jumlahTiket * HARGA_TIKET_VIP;
    }

    @Override
    public void konfirmasiPembayaran() {
        System.out.println("Pembayaran Tiket VIP telah dikonfirmasi.");
    }

    public static double hitungTotalPembayaran(int jumlahTiket) {
        return jumlahTiket * HARGA_TIKET_VIP;
    }

 
    public String getFasilitasVIP() {
        return fasilitasVIP;
    }

    public void setFasilitasVIP(String fasilitasVIP) {
        this.fasilitasVIP = fasilitasVIP;
    }

    @Override
    public String toString() {
        return super.toString() + ", Fasilitas VIP: " + fasilitasVIP;
    }

    public void setJumlahTiket(int jumlahTiket) {
        if (jumlahTiket >= 2) {
            this.jumlahTiket = jumlahTiket;
        } else {
            System.out.println("Jumlah tiket VIP minimal 2.");
        }
    }
}

public class Posttest {
    private static ArrayList<Tiket> daftarTiket = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\nPilih operasi yang ingin Anda lakukan:");
            System.out.println("1. Tambah Tiket Biasa");
            System.out.println("2. Tambah Tiket VIP");
            System.out.println("3. Lihat Daftar Tiket");
            System.out.println("4. Update Tiket");
            System.out.println("5. Hapus Tiket");
            System.out.println("6. Keluar");

            int pilihan = scanner.nextInt();
            scanner.nextLine();  

            switch (pilihan) {
                case 1:
                    tambahTiketBiasa();
                    break;
                case 2:
                    tambahTiketVIP();
                    break;
                case 3:
                    lihatDaftarTiket();
                    break;
                case 4:
                    updateTiket();
                    break;
                case 5:
                    hapusTiket();
                    break;
                case 6:
                    berjalan = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void tambahTiketBiasa() {
        System.out.print("Masukkan nama pembeli: ");
        String namaPembeli = scanner.nextLine();

        System.out.print("Masukkan jenis tiket: ");
        String jenisTiket = scanner.nextLine();

        System.out.print("Masukkan jumlah tiket: ");
        int jumlahTiket = scanner.nextInt();

        System.out.print("Apakah tiket ini termasuk makanan gratis? (Ya/Tidak): ");
        boolean makananGratis = scanner.next().equalsIgnoreCase("Ya");

        TiketBiasa tiketBiasaBaru = new TiketBiasa(namaPembeli, jenisTiket, jumlahTiket, makananGratis);
        daftarTiket.add(tiketBiasaBaru);

        System.out.println("Tiket Biasa berhasil ditambahkan!");
    }

    private static void tambahTiketVIP() {
        System.out.print("Masukkan nama pembeli: ");
        String namaPembeli = scanner.nextLine();

        System.out.print("Masukkan jenis tiket: ");
        String jenisTiket = scanner.nextLine();

        System.out.print("Masukkan jumlah tiket: ");
        int jumlahTiket = scanner.nextInt();

        scanner.nextLine(); 

        System.out.print("Masukkan fasilitas VIP: ");
        String fasilitasVIP = scanner.nextLine();

        TiketVIP tiketVIPBaru = new TiketVIP(namaPembeli, jenisTiket, jumlahTiket, fasilitasVIP);
        daftarTiket.add(tiketVIPBaru);

        System.out.println("Tiket VIP berhasil ditambahkan!");
    }

    private static void lihatDaftarTiket() {
        if (daftarTiket.isEmpty()) {
            System.out.println("Daftar tiket kosong.");
        }
        else {
            System.out.println("\nDaftar Tiket:");
            for (Tiket tiket : daftarTiket) {
                System.out.println(tiket);
            }
        }
    }

    private static void updateTiket() {
        lihatDaftarTiket();
        System.out.print("Masukkan nomor tiket yang ingin diupdate: ");
        int nomorTiket = scanner.nextInt();

        if (nomorTiket >= 1 && nomorTiket <= daftarTiket.size()) {
            Tiket tiketTerpilih = daftarTiket.get(nomorTiket - 1);

            if (tiketTerpilih instanceof TiketBiasa) {
                System.out.print("Masukkan nama pembeli baru: ");
                String namaPembeli = scanner.next();
                tiketTerpilih.setNamaPembeli(namaPembeli);

                System.out.print("Masukkan jenis tiket baru: ");
                String jenisTiket = scanner.next();
                tiketTerpilih.setJenisTiket(jenisTiket);

                System.out.print("Masukkan jumlah tiket baru: ");
                int jumlahTiket = scanner.nextInt();
                tiketTerpilih.setJumlahTiket(jumlahTiket);

                System.out.print("Apakah makanan gratis? (Ya/Tidak): ");
                boolean makananGratis = scanner.next().equalsIgnoreCase("Ya");
                ((TiketBiasa) tiketTerpilih).setMakananGratis(makananGratis);
                
            } else if (tiketTerpilih instanceof TiketVIP) {
                System.out.print("Masukkan nama pembeli baru: ");
                String namaPembeli = scanner.next();
                tiketTerpilih.setNamaPembeli(namaPembeli);

                System.out.print("Masukkan jenis tiket baru: ");
                String jenisTiket = scanner.next();
                tiketTerpilih.setJenisTiket(jenisTiket);

                System.out.print("Masukkan jumlah tiket baru: ");
                int jumlahTiket = scanner.nextInt();
                tiketTerpilih.setJumlahTiket(jumlahTiket);

                scanner.nextLine(); // Membersihkan newline di buffer

                System.out.print("Masukkan fasilitas VIP baru: ");
                String fasilitasVIP = scanner.nextLine();
                ((TiketVIP) tiketTerpilih).setFasilitasVIP(fasilitasVIP);
            }

            System.out.println("Tiket berhasil diupdate!");
        } else {
            System.out.println("Nomor tiket tidak valid.");
        }
    }

    private static void hapusTiket() {
        lihatDaftarTiket();
        System.out.print("Masukkan nomor tiket yang ingin dihapus: ");
        int nomorTiket = scanner.nextInt();

        if (nomorTiket >= 1 && nomorTiket <= daftarTiket.size()) {
            daftarTiket.remove(nomorTiket - 1);
            System.out.println("Tiket berhasil dihapus!");
        } else {
            System.out.println("Nomor tiket tidak valid.");
        }
    }
}
