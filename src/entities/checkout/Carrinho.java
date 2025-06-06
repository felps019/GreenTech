package entities.checkout;

import java.util.ArrayList;
import java.util.List;

import entities.product.Produto;

public class Carrinho {
  private List<ItemCarrinho> itens;

  public Carrinho() {
    this.itens = new ArrayList<>();
  }

  public void adicionarItem(Produto produto, int quantidadeUnidades) {
    if (quantidadeUnidades <= 0) {
      System.out.println("Quantidade de unidades inválida.");
      return;
    }
    for (ItemCarrinho item : itens) {
      if (item.getProduto().getId_prod() == produto.getId_prod()) {
        item.setQuantidadeUnidades(item.getQuantidadeUnidades() + quantidadeUnidades);
        System.out.println(quantidadeUnidades + " unidades de " + produto.getNome_prod()
            + " adicionadas ao carrinho. Total para o item: " + item.getQuantidadeUnidades());
        return;
      }
    }
    itens.add(new ItemCarrinho(produto, quantidadeUnidades));
    System.out.println(quantidadeUnidades + " unidades de " + produto.getNome_prod() + " adicionadas ao carrinho.");
  }

  public void removerItem(int idProduto) {
    itens.removeIf(item -> item.getProduto().getId_prod() == idProduto);
    System.out.println("Item com ID " + idProduto + " removido do carrinho.");
  }

  public List<ItemCarrinho> getItens() {
    return itens;
  }

  public float getValorTotalCarrinho() {
    float total = 0;
    for (ItemCarrinho item : itens) {
      total += item.getSubtotal();
    }
    return total;
  }

  public int getTotalKwhCarrinho() {
    int totalKwh = 0;
    for (ItemCarrinho item : itens) {
      totalKwh += item.getTotalKwhItem();
    }
    return totalKwh;
  }

  public boolean estaVazio() {
    return itens.isEmpty();
  }

  public void limparCarrinho() {
    itens.clear();
    System.out.println("Carrinho limpo.");
  }

  public void exibirCarrinho() {
    if (itens.isEmpty()) {
      System.out.println("Seu carrinho está vazio.");
      return;
    }
    System.out.println("\n--- ITENS NO SEU CARRINHO ---");
    for (int i = 0; i < itens.size(); i++) {
      System.out.println((i + 1) + ". " + itens.get(i));
    }
    System.out.println("-----------------------------");
    System.out.println("Total de kWh no carrinho: " + getTotalKwhCarrinho() + " kWh");
    System.out.println("Valor Total do Carrinho: R$ " + String.format("%.2f", getValorTotalCarrinho()));
    System.out.println("-----------------------------");
  }
}