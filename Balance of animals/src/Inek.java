import java.util.Random;

class Inek extends Hayvan {
    public Inek(String cinsiyet, int alanGenislik, int alanUzunluk) {
        super(cinsiyet, alanGenislik, alanUzunluk);
    }

    @Override
    public void hareketEt() {
        if (canli) {
            Random random = new Random();
            int hareketX = random.nextBoolean() ? -2 : 2; // X ekseni için -2 veya 2 seçimi
            int hareketY = random.nextBoolean() ? -2 : 2; // Y ekseni için -2 veya 2 seçimi
            int newX = x + hareketX;
            int newY = y + hareketY;
            x = Math.max(0, Math.min(newX, alanGenislik - 1)); // Alanın sınırlarını kontrol etme
            y = Math.max(0, Math.min(newY, alanUzunluk - 1)); // Alanın sınırlarını kontrol etme
            x = newX;  y = newY;
        }
    }
}
