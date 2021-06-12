package tech.vsj.mercadolivre.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.vsj.mercadolivre.persistence.model.Categoria;
import tech.vsj.mercadolivre.persistence.model.Produto;
import tech.vsj.mercadolivre.persistence.model.Usuario;
import tech.vsj.mercadolivre.validation.ExistValue;

public class ProdutoRequestForm {

  @NotBlank
  private String nome;

  @Positive
  @NotNull
  private BigDecimal valor;

  @Positive
  @NotNull
  private Long qtDisponivel;

  @Size(min = 3)
  @NotNull
  @JsonProperty(value = "caracteristicas")
  @Valid
  private List<CaracteristicaRequestForm> caracteristicas = new ArrayList<>();

  @NotBlank
  @Size(max = 1000, min = 0)
  private String descricao;

  @NotNull
  @ExistValue(domainClass = Categoria.class, fieldName = "id")
  private Long categoria;

  public ProdutoRequestForm(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
      @NotNull @Positive Long qtDisponivel,
      @Size(min = 3) @NotNull List<CaracteristicaRequestForm> caracteristicas,
      @NotBlank @Size(max = 1000, min = 0) String descricao,
      @NotNull @ExistValue(domainClass = Categoria.class, fieldName = "id") Long categoria) {
    this.nome = nome;
    this.valor = valor;
    this.qtDisponivel = qtDisponivel;
    this.caracteristicas.addAll(caracteristicas);
    this.descricao = descricao;
    this.categoria = categoria;
  }

  public String getNome() {
    return nome;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public Long getQtDisponive() {
    return qtDisponivel;
  }

  public List<CaracteristicaRequestForm> getCaracteristicas() {
    return caracteristicas;
  }

  public String getDescricao() {
    return descricao;
  }

  public Long getCategoria() {
    return categoria;
  }

  @Override
  public String toString() {
    return String
        .format(
            "ProdutoRequestForm [nome=%s, valor=%s, qtDisponive=%s, caracteristicas=%s, descricao=%s, categoria=%s]",
            nome, valor, qtDisponivel, caracteristicas, descricao, categoria);
  }

  public Produto map(EntityManager manager, Usuario usuarioLogado) {
    var categoriaFound = manager.find(Categoria.class, this.categoria);

    return new Produto(this.nome, this.valor, this.qtDisponivel, this.caracteristicas,
        categoriaFound, this.descricao, usuarioLogado);
  }

  public Set<String> buscaCaracteristicasIguas() {

    HashSet<String> nomeIguais = new HashSet<>();
    HashSet<String> resultado = new HashSet<>();

    for (CaracteristicaRequestForm caracteristicaRequestForm : caracteristicas) {
      var nomeCaracteristica = caracteristicaRequestForm.getNome();
      if (!nomeIguais.add(nomeCaracteristica)) {
        resultado.add(nomeCaracteristica);
      }
    }
    return resultado;
  }

}
