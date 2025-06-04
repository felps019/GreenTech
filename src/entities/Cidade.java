package entities;

public class Cidade {
    private static int incremento = 0;

    private int id_cidade;
    private String cidade;

    public Cidade(String cidade) {
        this.id_cidade = incremento++;
        this.cidade = cidade;
    }

    public int getIdcidade() {
        return id_cidade;
    }

    public String getcidade() {
        return cidade;
    }

    public void setcidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return cidade;
    }
}