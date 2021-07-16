package tech.vsj.mercadolivre.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import tech.vsj.mercadolivre.persistence.model.ImagemProduto;
import tech.vsj.mercadolivre.persistence.model.Produto;

public class ProdutoDTO {

  private String nome;
  private BigDecimal valor;
  private Long qtDisponive;
  private Set<CaracteristicaProdutoDTO> caracteristicas = new HashSet<>();
  private String descricao;
  private CategoriaDTO categoria;
  private LocalDateTime tsCriacao;
  private String usuarioDono;
  private Set<ImagemProdutoDTO> imagens = new HashSet<>();

  public ProdutoDTO(Produto produto) {

    this.nome = produto.getNome();
    this.valor = produto.getValor();
    this.qtDisponive = produto.getQtDisponive();
    Set<CaracteristicaProdutoDTO> collect = produto
        .getCaracteristicas().stream().map(CaracteristicaProdutoDTO::new)
        .collect(Collectors.toSet());
    this.caracteristicas.addAll(collect);
    this.descricao = produto.getDescricao();
    this.categoria = new CategoriaDTO(produto.getCategoria());
    this.tsCriacao = produto.getTsCriacao();
    this.usuarioDono = produto.getUsuarioDono().getUsername();
    this.imagens =
        produto.getImagens().stream().map(ImagemProdutoDTO::new).collect(Collectors.toSet());
  }

  public String getNome() {
    return nome;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public Long getQtDisponive() {
    return qtDisponive;
  }

  public Set<CaracteristicaProdutoDTO> getCaracteristicas() {
    return caracteristicas;
  }

  public String getDescricao() {
    return descricao;
  }

  public CategoriaDTO getCategoria() {
    return categoria;
  }

  public LocalDateTime getTsCriacao() {
    return tsCriacao;
  }

  public String getUsuarioDono() {
    return usuarioDono;
  }

  public Set<ImagemProdutoDTO> getImagens() {
    return imagens;
  }

  @Override
  public String toString() {
    return String
        .format(
            "ProdutoDTO [nome=%s, valor=%s, qtDisponive=%s, caracteristicas=%s, descricao=%s, categoria=%s, tsCriacao=%s, usuarioDono=%s, imagens=%s]",
            nome, valor, qtDisponive, caracteristicas, descricao, categoria, tsCriacao, usuarioDono,
            imagens);
  }



}
