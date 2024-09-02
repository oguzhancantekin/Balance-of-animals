import java.util.Random;

class Avci extends Hayvan {
    public Avci( int alanGenislik, int alanUzunluk) {
		super("Erkek", alanGenislik, alanUzunluk);
	}
	
 
    @Override
    public void hareketEt() {
        if (canli) {
            Random random = new Random();
            int hareketX = random.nextBoolean() ? -1 : 1; // X ekseni için -1 veya 1 seçimi
            int hareketY = random.nextBoolean() ? -1 : 1; // Y ekseni için -1 veya 1 seçimi
            int newX = x + hareketX;
            int newY = y + hareketY;
            x = Math.max(0, Math.min(newX, alanGenislik - 1)); // Alanın sınırlarını kontrol etme
            y = Math.max(0, Math.min(newY, alanUzunluk - 1)); // Alanın sınırlarını kontrol etme
            x = newX;  y = newY;
        }
    }
}
