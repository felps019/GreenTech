package entities.address;

public class Uf {
    private static int incremento = 0;

    private int id_uf;
    private String uf;

    public Uf(String uf) {
        this.id_uf = incremento++;
        this.uf = uf;
    }

    public int getIdUf() {
        return id_uf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return uf;
    }
}