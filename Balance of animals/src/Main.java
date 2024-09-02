import java.time.chrono.HijrahEra;
import java.util.Random;

public class Main {
	Random random=new Random();

    public static void main(String[] args) {
    	 int hareketSayisi = 1;// BURADAN HAREKET SAYISINI KONTROL EDEBİLİRSİNİZ YANİ KAÇ TUR BU SİSTEM ÇALIŞACAĞINI
         //hareket sayısını kullanıcıdan da talep edebilirdik ama burdan manuel girerek gelistirmek daha rahatti ve gerek duymadim
         
        int alanGenislik = 100;
        int alanUzunluk = 100;
        int koyunSayisi = 10;
        int inekSayisi = 20;
        int tavukSayisi = 30; //Tavuk sayinin yarisi kadar horoz ve tavuk uretiyor horoz erkek tavuk olarak adlandiriliyor ve sonunda erkek tavuklar horoz sayini olusturuyor
        int kurtSayisi = 10;
        int aslanSayisi = 8;
        int avciSayisi = 1;
        
        Alan alan = new Alan(alanGenislik, alanUzunluk);

        for (int i = 0; i < koyunSayisi / 2; i++) { //Bu kisimlarda bastaki degerlere hayvan uretimi yapiliyor
            alan.hayvanEkle(new Koyun("erkek", alanGenislik, alanUzunluk));
            alan.hayvanEkle(new Koyun("dişi", alanGenislik, alanUzunluk));
        }

        for (int i = 0; i < inekSayisi / 2; i++) {
            alan.hayvanEkle(new Inek("erkek", alanGenislik, alanUzunluk));
            alan.hayvanEkle(new Inek("dişi", alanGenislik, alanUzunluk));
        }

        for (int i = 0; i < tavukSayisi / 2; i++) {
            alan.hayvanEkle(new Tavuk("erkek", alanGenislik, alanUzunluk));
            alan.hayvanEkle(new Tavuk("dişi", alanGenislik, alanUzunluk));
        }

        for (int i = 0; i < kurtSayisi / 2; i++) {
            alan.hayvanEkle(new Kurt("erkek", alanGenislik, alanUzunluk));
            alan.hayvanEkle(new Kurt("dişi", alanGenislik, alanUzunluk));
        }

       
        for (int i = 0; i < aslanSayisi/ 2; i++) {
            alan.hayvanEkle(new Aslan("erkek", alanGenislik, alanUzunluk));
            alan.hayvanEkle(new Aslan("dişi", alanGenislik, alanUzunluk));
        }

        for (int i = 0; i < avciSayisi; i++) {
            alan.hayvanEkle(new Avci( alanGenislik, alanUzunluk));
        }

       System.out.println(hareketSayisi + " tur çalıştırıldı.(Değer artırımını değiştirmek için değişken değerini değiştiriniz)");
        for (int i = 0; i < hareketSayisi; i++) {
            alan.hareketEt();
            alan.avlan();
            alan.yavruDogur(); //Her harekette once hareket et sonra avlan sonra da yavru dogurma mekanizmasi calisir
        }

        alan.hayvanSayisiniYazdir();
    }
}
