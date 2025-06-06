package entities.checkout;

import java.util.Date;

public class NotaFiscal {

    private int id_nf;
    private int nfNumero;
    private String nomeDestinatario;
    private String cpfCnpjDestinatario;
    private String enderecoDestinatario;
    private String emailDestinatario;
    private String nomeRazaoEmitente;
    private String cpfCnpjEmitente;
    private int ie_im;
    private String emailEmitente;
    private int codigoProduto;
    private float valorTotal;
    private Date dataEmissao;

    private boolean emitida = false;
    private boolean cancelada = false;

    private Pedido pedido;

    public NotaFiscal(int id_nf, int nfNumero, String nomeDestinatario, String cpfCnpjDestinatario,
            String enderecoDestinatario,
            String emailDestinatario, String nomeRazaoEmitente, String cpfCnpjEmitente, int ie_im,
            String emailEmitente, int codigoProduto, float valorTotal, Date dataEmissao, Pedido pedido) {
        this.id_nf = id_nf;
        this.nfNumero = nfNumero;
        this.nomeDestinatario = nomeDestinatario;
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
        this.enderecoDestinatario = enderecoDestinatario;
        this.emailDestinatario = emailDestinatario;
        this.nomeRazaoEmitente = nomeRazaoEmitente;
        this.cpfCnpjEmitente = cpfCnpjEmitente;
        this.ie_im = ie_im;
        this.emailEmitente = emailEmitente;
        this.codigoProduto = codigoProduto;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.pedido = pedido;
        this.emitida = true;

        System.out.println("Nota fiscal " + nfNumero + " emitida com sucesso.");
    }

    public void cancelarNota() {
        if (this.emitida && !this.cancelada) {
            this.cancelada = true;
            System.out.println("Nota fiscal " + nfNumero + " cancelada com sucesso.");
        } else {
            System.out.println("Não foi possível cancelar a nota fiscal.");
        }
    }

    public void exibirNota() {
        System.out.println("=== Nota Fiscal ===");
        System.out.println("ID NF: " + id_nf);
        System.out.println("Número: " + nfNumero);
        System.out.println("Destinatário: " + nomeDestinatario + " | CPF/CNPJ: " + cpfCnpjDestinatario);
        System.out.println("Endereço: " + enderecoDestinatario + " | E-mail: " + emailDestinatario);
        System.out.println("Emitente: " + nomeRazaoEmitente + " | CPF/CNPJ: " + cpfCnpjEmitente + " | IE/IM: " + ie_im);
        System.out.println("E-mail Emitente: " + emailEmitente);
        System.out.println("Código Produto: " + codigoProduto);
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("Data de Emissão: " + dataEmissao);
        System.out.println("Status: " + (cancelada ? "Cancelada" : "Emitida"));
        System.out.println("Pedido associado ID: " + (pedido != null ? pedido.hashCode() : "Nenhum"));
    }

    public void editarNota(int nfNumero, String nomeDestinatario, String cpfCnpjDestinatario,
            String enderecoDestinatario,
            String emailDestinatario, String nomeRazaoEmitente, String cpfCnpjEmitente, int ie_im,
            String emailEmitente, int codigoProduto, float valorTotal, Date dataEmissao) {

        this.nfNumero = nfNumero;
        this.nomeDestinatario = nomeDestinatario;
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
        this.enderecoDestinatario = enderecoDestinatario;
        this.emailDestinatario = emailDestinatario;
        this.nomeRazaoEmitente = nomeRazaoEmitente;
        this.cpfCnpjEmitente = cpfCnpjEmitente;
        this.ie_im = ie_im;
        this.emailEmitente = emailEmitente;
        this.codigoProduto = codigoProduto;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;

        System.out.println("Nota fiscal " + nfNumero + " editada com sucesso.");
    }
}