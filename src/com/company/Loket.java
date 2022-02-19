package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Loket {

    AntrianApotik head;
    private int jumlahAntrian;

    public Loket() {
        head = null;
        this.jumlahAntrian = 0;
    }

    public boolean antrianKosong(){
        if (head == null) return true;
        else return false;
    }

    public void mulaiAntri(String nomorAntrian, String nama, String obat, int umur) {
        AntrianApotik antri = new AntrianApotik(nomorAntrian, nama, obat, umur);
        if (antrianKosong()) {
            head = antri;
            head.next = null;
        } else {
            AntrianApotik bantu;
            bantu = head;
            while (bantu.next != null) {
                bantu = bantu.next;
            }
            bantu.next = antri;
        }
        jumlahAntrian++;
    }

    public void cariPrioritas(){
        if (!antrianKosong()){
            AntrianApotik current = head, index = null;

            int umur;
            String antrian, nama, obat;
            if (head == null) {
                return;
            }
            else {
                while (current != null) {
                    index = current.next;
                    while (index != null) {
                        if (current.umurPasien < index.umurPasien) {
                            umur = current.umurPasien;
                            antrian = current.nomorAntrian;
                            nama = current.namaPasien;
                            obat = current.namaObat;

                            current.umurPasien = index.umurPasien;
                            current.nomorAntrian = index.nomorAntrian;
                            current.namaPasien = index.namaPasien;
                            current.namaObat = index.namaObat;

                            index.umurPasien = umur;
                            index.nomorAntrian = antrian;
                            index.namaPasien = nama;
                            index.namaObat = obat;
                        }

                        index = index.next;
                    }
                    current = current.next;
                }
            }
        }
    }

    public void antrianSelesai(){
        if (!antrianKosong()) {
            if (head.next != null) {
                head = head.next;
            }else{
                head = null;
            }
        }
    }

    public void listSemuaAntrian() {
        System.out.println("LIST ANTRIAN APOTEK :");
        String tableFromat = "| %-4S | %-13S | %-26s | %-24s | %-12s | %n";
        System.out.format("+------+---------------+----------------------------+--------------------------+--------------+%n");
        System.out.format("| NO   | NOMOR ANTRIAN | NAMA PASIEN                | NAMA OBAT                | UMUR PASIEN  |%n");
        System.out.format("+------+---------------+----------------------------+--------------------------+--------------+%n");

        if (!antrianKosong()) {
            AntrianApotik sekarang = head;
            int i = 1;
            while (sekarang != null) {
                System.out.format(tableFromat, i, sekarang.nomorAntrian, sekarang.namaPasien, sekarang.namaObat, sekarang.umurPasien + " Tahun");
                sekarang = sekarang.next;
                i++;
            }
            System.out.format("+------+---------------+----------------------------+--------------------------+--------------+%n");
            System.out.println();
        } else {
            System.out.format("|                                   ANTRIAN KOSONG                                            |%n");
            System.out.format("+------+---------------+----------------------------+--------------------------+--------------+%n");
        }
    }

    public String view() {
        String[] tempMenu ={
                "+----------------------------------------------------+",
                "|                   SELAMAT DATANG                   |",
                "|               DI APOTEK KUSUMA JAYA                |",
                "|             JL. RAYA JEMBER-BANYUWANGI             |",
                "|====================================================|",
                "|                                                    |",
                "|           MENU UTAMA APOTEK KUSUMA JAYA            |",
                "|----------------------------------------------------|",
                "|                                                    |",
                "|   1. TAMBAH ANTRIAN                                |",
                "|   2. CARI PRIORITAS                                |",
                "|   3. LIHAT SEMUA ANTRIAN                           |",
                "|   4. PROSES ANTRIAN                                |",
                "|                                                    |",
                "|----------------------------------------------------|",
                "|   0. EXIT                                          |",
                "+----------------------------------------------------+",
                "\n"
        };
        String sResult = "";
        sResult = MenuConsoleGenerator.menuConsoleGenerator(tempMenu);
        return sResult;
    }

    public void alert(String title) {
        System.out.println("+----------------------------------------------------+");
        System.out.println("                " + title + "                         ");
        System.out.println("+----------------------------------------------------+");
    }


    public static void main(String[] args) {

        Loket loket = new Loket();
        String nomorAntrian, nama, obat;
        int umur;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        try {
            String pilihMenu = "";
            do{
                pilihMenu = loket.view();
                if (pilihMenu.equals("1")) {
                    System.out.print("Masukan Nomor Antrian : ");
                    nomorAntrian  = in.readLine();
                    System.out.print("Masukan Nama Pasien : ");
                    nama = in.readLine();
                    System.out.print("Masukan Nama Obat : ");
                    obat = in.readLine();
                    System.out.print("Masukan Umur Pasien : ");
                    umur = Integer.parseInt(in.readLine());

                    loket.mulaiAntri(nomorAntrian, nama, obat, umur);
                    loket.alert("BERHASIL MENAMBAHKAN DATA");

                } else if (pilihMenu.equals("2")) {

                    loket.cariPrioritas();
                    loket.listSemuaAntrian();
                    System.out.println("\n\nTekan ENTER untuk kembali ke MENU UTAMA ! ");
                    System.in.read();
                } else if (pilihMenu.equals("3")) {

                    loket.listSemuaAntrian();
                    System.out.println("\n\nTekan ENTER untuk kembali ke MENU UTAMA ! ");
                    System.in.read();
                } else if (pilihMenu.equals("4")) {

                    loket.antrianSelesai();
                    loket.alert("ANTRIAN BERHASIL DI PROSES");

                }
            } while(!pilihMenu.equals("0"));
            {
                System.out.println("+----------------------------------------------------+");
                System.out.println("|              --- E X I T ---                       |");
                System.out.println("|----------------------------------------------------|");
                System.out.println("| TERIMA KASIH SUDAH BERKUJUNG KE APOTEK KAMI        |");
                System.out.println("+----------------------------------------------------+");
            }

        } catch (Exception e) {
            System.err.println("ERROR !!" + e);
        }

    }
}
