package entities.address;

public class Bairro {

    private static int incremento = 0;

    private int id_bairro;
    private String bairro;

    public Bairro(String bairro) {
        this.id_bairro = incremento++;
        this.bairro = bairro;
    }

    public int getIdBairro() {
        return id_bairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return bairro;
    }
}