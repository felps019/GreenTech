package entities;

public class ItemCarrinho {
  private Produto produto;
  private int quantidadeUnidades;

  public ItemCarrinho(Produto produto, int quantidadeUnidades) {
    this.produto = produto;
    this.quantidadeUnidades = quantidadeUnidades;
  }

  public Produto getProduto() {
    return produto;
  }

  public int getQuantidadeUnidades() {
    return quantidadeUnidades;
  }

  public void setQuantidadeUnidades(int quantidadeUnidades) {
    this.quantidadeUnidades = quantidadeUnidades;
  }

  public float getSubtotal() {
    return produto.getPreco() * quantidadeUnidades;
  }

  public int getTotalKwhItem() {
    return produto.getCapacidadeKwhPorUnidade() * quantidadeUnidades;
  }

  @Override
  public String toString() {
    return quantidadeUnidades + "x " + produto.getNome_prod() +
        " (" + produto.getCapacidadeKwhPorUnidade() + " kWh cada) | Subtotal: R$ "
        + String.format("%.2f", getSubtotal());
  }
}