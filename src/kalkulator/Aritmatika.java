/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Aritmatika {
    char state = 'A';
    String string;
    
    ArrayList<Angka> listAngka = new ArrayList<>();
    ArrayList<Operator> listoprtr = new ArrayList<>();
    
    public Aritmatika(String string) {
        this.string = string;
        controlState();
    }
    
    void controlState() {
        if(isStringDiterima()) {
            pisahString();
            kalkulasi();
        } else {
            JOptionPane.showMessageDialog(null, "String tidak diterima oleh mesin!");
        }
    }
    
    private float sum = 0;
    
    void kalkulasi() {
        sum = listAngka.get(0).getAngka();
        
        sum = pembagian();
        sum = perkalian();
        sum = pertambahan();
        sum = pengurangan();
        
        JOptionPane.showMessageDialog(null, sum);
    }
    
    private float pembagian() {
        for(int i=0;i<listoprtr.size();i++){
            System.out.println(i);
            if(listoprtr.get(i).getOprtr()=='/'){
                sum=bagi(listAngka.get(i).getAngka(),listAngka.get(i+1).getAngka());
                removeListOprtr(i);
                changeListAngka(i, new Angka(sum));
                removeListAngka(i+1);
                i=-1;
            }
        }
            return sum;
    }
        
    private float perkalian() {
        for(int i=0;i<listoprtr.size();i++){
            System.out.println(i);
            if(listoprtr.get(i).getOprtr()=='*') {
                sum=kali(listAngka.get(i).getAngka(),listAngka.get(i+1).getAngka());
                removeListOprtr(i);
                changeListAngka(i, new Angka(sum));
                removeListAngka(i+1);
                i=-1;
            }
        }
        return sum;
    }
        
    private float pertambahan(){
        for(int i=0;i<listoprtr.size();i++){
            System.out.println(i);
            if(listoprtr.get(i).getOprtr()=='+'){
                sum=tambah(listAngka.get(i).getAngka(),listAngka.get(i+1).getAngka());
                removeListOprtr(i);
                changeListAngka(i, new Angka(sum));
                removeListAngka(i+1);
                i=-1;
            }
        }
        return sum;
    }
     
    private float pengurangan(){
        for(int i=0;i<listoprtr.size();i++){
            System.out.println(i);
            if(listoprtr.get(i).getOprtr()=='-'){
                sum=kurang(listAngka.get(i).getAngka(),listAngka.get(i+1).getAngka());
                removeListOprtr(i);
                changeListAngka(i, new Angka(sum));
                removeListAngka(i+1);
                i=-1;
            }
        }
        return sum; 
    }
    
    float bagi(float a1, float a2){
        return a1/a2;
    } 
    
    float kali(float a1, float a2){
        return a1*a2;
    }
    
    float tambah(float a1, float a2){
        return a1+a2;
    }
     
    float kurang(float a1, float a2){
        return a1-a2;
    }
    
    void printAngkaOprtr(){
        for(int i=0;i<listAngka.size();i++){
            System.out.print(i+". "+listAngka.get(i).angka+" | ");
        }
        System.out.println();
        for(int i=0;i<listoprtr.size();i++){
            System.out.print(i+". "+listoprtr.get(i).oprtr+" | ");
        }
        System.out.println();
    }
    
    void pisahString(){
        int batasAwal=0,batasAkhir=0;
        String subString;
        int indexAngka;
        
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)=='+'||string.charAt(i)=='='||string.charAt(i)=='-'||string.charAt(i)=='*'||string.charAt(i)=='/'){
                batasAkhir=i;
                subString=string.substring(batasAwal, batasAkhir);
                addListAngka(new Angka(Float.parseFloat(subString)));
                addListOprtr(new Operator(string.charAt(i)));
                batasAwal=i+1;
                System.out.println("temp= "+ subString);
            }
            
        }
        
    }
    
    private void changeListAngka(int i, Angka a){
        listAngka.set(i, a);
    }
    private void addListAngka(Angka a){
        listAngka.add(a);
    }
    private void addListOprtr(Operator oprtr){
        listoprtr.add(oprtr);
    }
    private void removeListAngka(int a){
        listAngka.remove(a);
    }
    private void removeListOprtr(int a){
        listoprtr.remove(a);
    }
    
    boolean isStringDiterima(){
        char[] charString = string.toCharArray();
        for(int i=0; i<charString.length; i++){
            System.out.println(charString[i]);
            if(!isCharDiterima(charString[i])){
                return false;
            }else{
                moveState(charString[i]);
            }
        }
        System.out.println(state);
        if(state=='D'){
            return true;
        }else{
            return false;
        }
    }
    
    void moveState(char s){
        if(state=='A'){
            if(s>='1' && s<='9'){
                state= 'B';
            }
        }else if(state=='B'){
            if(s>='0' && s<='9'){
                state= 'B';
            }else if(s=='*' || s=='/' || s=='+' || s=='-'){
                state='C';
            }else if(s=='='){
                state='D';
            }
        }else if(state=='C'){
            if(s>='1' && s<='9'){
                state='B';
            }
        }
    }
    
    boolean isCharDiterima(char s){
        switch (state) {
            case 'A':
                if(s>='1' && s<'9'){
                    return true;
                }else{
                    System.out.println(s);
                    return false;
                }
            case 'B':
                if(s>='0' && s<='9'){
                    return true;
                }else if(s=='*' || s=='/' || s=='+' || s=='-' || s=='='){
                    return true;
                }else{
                    return false;
                }
            case 'C':
                if(s>='1' && s<='9'){
                    return true;
                }else{
                    return false;
                }
            default:
                return false;
        }
    }
}
