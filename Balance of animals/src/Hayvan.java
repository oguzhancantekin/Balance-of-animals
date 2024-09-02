import java.util.Random;

abstract class Hayvan {
    protected String cinsiyet;
    protected int x, y;
    protected int alanGenislik;
    protected int alanUzunluk;
    protected boolean canli = true;

    public Hayvan(String cinsiyet, int alanGenislik, int alanUzunluk) {
        this.cinsiyet = cinsiyet;
        this.alanGenislik = alanGenislik;
        this.alanUzunluk = alanUzunluk;
        Random random = new Random();
        this.x = random.nextInt(alanGenislik);
        this.y = random.nextInt(alanUzunluk);
    }

    public abstract void hareketEt();

	
}
