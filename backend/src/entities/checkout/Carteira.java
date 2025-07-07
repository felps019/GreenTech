package entities.checkout;

public class Carteira {

    private static int incremento = 0;

    private int idCarteira;
    private float saldo;

    public Carteira() {
        this.idCarteira = incremento++;
        this.saldo = 0;
    }

    public int getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(int idCarteira) {
        this.idCarteira = idCarteira;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void exibirDados() {
        System.out.println("Carteira: " + this.idCarteira + "Saldo: " + this.saldo);
    }

    public void saque(float valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido. O valor deve ser maior que zero.");
            return;
        }

        float saldoAtual = this.getSaldo();

        if (saldoAtual < valor) {
            System.out.println("Saldo insuficiente. Saldo atual: R$" + String.format("%.2f", saldoAtual));
            return;
        }

        this.setSaldo(saldoAtual - valor);
        System.out.println("Saque de R$" + String.format("%.2f", valor) + " efetuado. Novo saldo: R$"
                + String.format("%.2f", this.getSaldo()));
    }

    public void adicionarSaldo(float valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para depósito. O valor deve ser positivo.");
            return;
        }
        float saldoAtual = this.getSaldo();
        this.setSaldo(saldoAtual + valor);
        System.out.println("Depósito de R$" + String.format("%.2f", valor) + " realizado com sucesso! Novo saldo: R$"
                + String.format("%.2f", this.getSaldo()));
    }
}