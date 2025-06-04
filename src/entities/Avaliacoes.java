package entities;

import java.util.Date;

public class Avaliacoes {

    private static int incremento = 0;

    private int id_avaliacao;
    private float nota;
    private String descricao;
    private Date data;
    private Comprador comprador;
    private Pedido pedido;

    public Avaliacoes(float nota, String descricao, Comprador comprador, Pedido pedido) {
        this.id_avaliacao = incremento++;
        this.nota = nota;
        this.descricao = descricao;
        this.data = new Date();
        this.comprador = comprador;
        this.pedido = pedido;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void exibirAvaliacao() {
        System.out.println("--- Detalhes da Avaliação ---");
        System.out.println("ID da Avaliação: " + this.id_avaliacao);
        System.out.println("Nota: " + String.format("%.1f", this.nota) + " / 10");
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Data: " + this.data);
        System.out.println("Avaliado por: " + (comprador != null ? comprador.getNome() : "N/A"));
        System.out.println("Referente ao Pedido ID: " + (pedido != null ? pedido.getIdPedido() : "N/A"));
        System.out.println("-----------------------------");
    }

    @Override
    public String toString() {
        return "ID Avaliação: " + id_avaliacao + " | Nota: " + String.format("%.1f", nota) + "/10 | Descrição: '"
                + descricao +
                "' | Por: " + (comprador != null ? comprador.getNome() : "N/A") +
                " | Pedido: " + (pedido != null ? pedido.getIdPedido() : "N/A");
    }
}
