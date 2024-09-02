import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Alan {
    private int genislik;
    private int uzunluk;
    private List<Hayvan> hayvanlar;
    private Map<String, List<Hayvan>> hayvanTuruGruplari; // Hayvanları türlerine göre gruplandırmak için harita

    public Alan(int genislik, int uzunluk) {
        this.genislik = genislik;
        this.uzunluk = uzunluk;
        this.hayvanlar = new ArrayList<>();
        this.hayvanTuruGruplari = new HashMap<>();
    }

    public void hayvanEkle(Hayvan hayvan) {
        if (hayvan != null) {
            hayvanlar.add(hayvan);
            
            // Hayvanı türüne göre ilgili gruba ekle ve optimizasyon yap
//Onceki kodda her hayvan tek tek kontrol ediliyordu etrafindaki hayvanlara gore ama simdi kendi turune ait hayvanlarla mesafesi vs olculuyor sadece
            String tur = hayvan.getClass().getSimpleName();
            hayvanTuruGruplari.putIfAbsent(tur, new ArrayList<>());
            hayvanTuruGruplari.get(tur).add(hayvan);
        } else {
            System.err.println("Hata: Eklenen hayvan null referansına sahip olamaz.");
        }
    }

    public void hareketEt() {
        for (Hayvan hayvan : hayvanlar) {
            if (hayvan != null) {
                hayvan.hareketEt();
            }
        }
        // avlan();   // basta boyle kullandim ama olen bir hayvanin tekrar caismamasi icin iptal ettim bunu ve mainden sirayla calistirdim daha saglikli olsun diye
        // yavruDogur();
    }

    public void avlan() {
        List<Hayvan> avlanacaklar = new ArrayList<>();
        
        for (Hayvan avci : hayvanlar) {
            if ((avci instanceof Kurt || avci instanceof Aslan || avci instanceof Avci) && avci.canli) { // Sadece Kurt, Aslan veya Avci avlanabilir
                for (Hayvan av : hayvanlar) {
                    if (av != null && !av.equals(avci) && av.canli) { // Kendi kendini avlayamaz ve av canlı olmalı
                        int mesafe = mesafeyiOlcer(avci, av);
                        
                        // Kurt avlanma kontrolü
                        if (avci instanceof Kurt && (av instanceof Koyun || av instanceof Tavuk) && mesafe <= 4) {
                            avlanacaklar.add(av);
                        }
                        // Aslan avlanma kontrolü
                        else if (avci instanceof Aslan && (av instanceof Koyun || av instanceof Inek) && mesafe <= 5) {
                            avlanacaklar.add(av);
                        }
                        // Avci avlama kontrolü
                        else if (avci instanceof Avci && !(av instanceof Avci) && mesafe <= 5) {
                            avlanacaklar.add(av);
                        } 
                    }
                }
            }
        }
        
        // Avlanan hayvanları listeden kaldırma ve canlılık durumlarını güncelleme
        for (Hayvan av : avlanacaklar) {
            hayvanlar.remove(av);
            av.canli = false;
            
            // Hayvani tür grubundan da çıkar
            String tur = av.getClass().getSimpleName();
            hayvanTuruGruplari.get(tur).remove(av);
        }
    }

    public void yavruDogur() {
        List<Hayvan> yeniDoganlar = new ArrayList<>();
        
        // Her tür için ayrı ayrı çiftleşme kontrolü
        for (String tur : hayvanTuruGruplari.keySet()) {
            List<Hayvan> ayniTurHayvanlar = hayvanTuruGruplari.get(tur); //ayni tur hayvanlari bir liste cekmek ve her dongu degistiginde 
            
            for (int i = 0; i < ayniTurHayvanlar.size(); i++) {
                Hayvan anne = ayniTurHayvanlar.get(i);
                
                if (anne != null && !(anne instanceof Avci) && anne.canli) {
                    for (int j = i + 1; j < ayniTurHayvanlar.size(); j++) {
                        Hayvan baba = ayniTurHayvanlar.get(j);
                        
                        if (baba != null && anne != null && !baba.equals(anne) &&
                            !baba.cinsiyet.equals(anne.cinsiyet) && baba.canli && 
                            mesafeyiOlcer(anne, baba) <= 3) {
                            
                            Hayvan yeniDogan = yeniHayvanUret(anne.getClass().getSimpleName(), genislik, uzunluk);
                            yeniDoganlar.add(yeniDogan);
                        }
                    }
                }
            }
        }
        
        hayvanlar.addAll(yeniDoganlar); // Listeye yeni doğanları ekle

        // Hayvan türlerine göre grupları güncelle
        for (Hayvan yeniDogan : yeniDoganlar) {
            String tur = yeniDogan.getClass().getSimpleName();
            hayvanTuruGruplari.putIfAbsent(tur, new ArrayList<>());
            hayvanTuruGruplari.get(tur).add(yeniDogan);
        }
    }

    public int mesafeyiOlcer(Hayvan birinci, Hayvan ikinci) {//istenen iki hayvanlar arasindaki mesafeyi olcen metot
        int xFarki = Math.abs(birinci.x - ikinci.x);
        int yFarki = Math.abs(birinci.y - ikinci.y);
        return Math.max(xFarki, yFarki);
    }

    public Hayvan yeniHayvanUret(String tur, int genislik, int uzunluk) {
        Random random = new Random();
        String cinsiyet = random.nextBoolean() ? "erkek" : "dişi"; //Tur random olarak ataniyor yuzde 50 ihtimal
        switch (tur) {
            case "Koyun":
                return new Koyun(cinsiyet, genislik, uzunluk);
            case "Inek":
                return new Inek(cinsiyet, genislik, uzunluk);
            case "Tavuk":
                return new Tavuk(cinsiyet, genislik, uzunluk);
            case "Aslan":
                return new Aslan(cinsiyet, genislik, uzunluk);
            case "Kurt":
                return new Kurt(cinsiyet, genislik, uzunluk);
            default:
                return null;
        }
    }

    public void hayvanSayisiniYazdir() {
        Random random = new Random();
        int koyunSayisi = 0;
        int inekSayisi = 0;
        int tavukSayisi = 0;
        int kurtSayisi = 0;
        int aslanSayisi = 0;
        int avciSayisi = 0;
        int horozsayisi = 0;

        for (Hayvan hayvan : hayvanlar) {
            if (hayvan instanceof Koyun) {
                koyunSayisi++;
            } else if (hayvan instanceof Tavuk) {
                tavukSayisi++;
                // Eğer tavuk erkekse horoz olarak say.
                if (hayvan.cinsiyet.equals("erkek")) {
                    horozsayisi++;
                }
                
            } 
            else if (hayvan instanceof Inek) {
                inekSayisi++;
            }
            else if (hayvan instanceof Kurt) {
                kurtSayisi++;
            } else if (hayvan instanceof Aslan) {
                aslanSayisi++;
            } else if (hayvan instanceof Avci) {
                avciSayisi++;
            }
        }
        

        int sonTavuk = tavukSayisi - horozsayisi;  // Erkek olmayan tavuk sayısı

        System.out.println("Koyun sayısı: " + koyunSayisi);
        System.out.println("Inek sayısı: " + inekSayisi);
        System.out.println("Tavuk sayısı: " + sonTavuk);
        System.out.println("Horoz sayısı: " + horozsayisi);
        System.out.println("Kurt sayısı: " + kurtSayisi);
        System.out.println("Aslan sayısı: " + aslanSayisi);
        System.out.println("Avci sayısı: " + avciSayisi);
    }
}
