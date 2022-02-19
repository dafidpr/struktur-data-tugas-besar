package com.company;

public class AntrianApotik {
    String namaPasien, nomorAntrian, namaObat;
    int umurPasien;
    AntrianApotik next;

    public AntrianApotik(String  antrian, String nama, String obat, int umur) {
        nomorAntrian = antrian;
        namaPasien = nama;
        namaObat = obat;
        umurPasien = umur;
        next = null;
    }
}
