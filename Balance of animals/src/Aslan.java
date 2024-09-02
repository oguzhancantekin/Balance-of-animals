import java.util.Random;

class Aslan extends Hayvan {
    public Aslan(String cinsiyet, int alanGenislik, int alanUzunluk) {
        super(cinsiyet, alanGenislik, alanUzunluk);
    }
   
    @Override
    public void hareketEt() {
        if (canli) {
            Random random = new Random();
            int hareketX = random.nextBoolean() ? -4 : 4; // X ekseni için -4 veya 4 seçimi
            int hareketY = random.nextBoolean() ? -4 : 4; // Y ekseni için -4 veya 4 seçimi
            int newX = x + hareketX;
            int newY = y + hareketY;
            x = Math.max(0, Math.min(newX, alanGenislik - 1)); // Alanın sınırlarını kontrol etme
            y = Math.max(0, Math.min(newY, alanUzunluk - 1)); // Alanın sınırlarını kontrol etme
            x = newX;  y = newY;
        }
    }
}
