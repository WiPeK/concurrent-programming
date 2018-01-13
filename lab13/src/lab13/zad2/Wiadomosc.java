package lab13.zad2;

public class Wiadomosc {
    private String opis;

    public Wiadomosc(String opis) {
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "opis='" + opis + '\'' +
                '}';
    }
}
