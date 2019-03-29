/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

/**
 *
 * @author ASUS
 */
public class Angka {
    float angka;
    
    public Angka (float angka) {
        this.angka = angka;
    }
    
    public float getAngka() {
        return angka;
    }
    
    public void cetak() {
        System.out.println(angka+" ");
    }
}
