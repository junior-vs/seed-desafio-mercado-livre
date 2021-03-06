package tech.vsj.mercadolivre.persistence.model;

import static javax.persistence.GenerationType.SEQUENCE;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import tech.vsj.mercadolivre.form.CaracteristicaRequestForm;

@Entity
@NamedQueries({@NamedQuery(name = "Produto.buscaPorIdeUsuario",
    query = "SELECT p FROM Produto p WHERE p.id = :id AND p.usuarioDono = :usuarioDono")})
public class Produto {

  @Id
  @GeneratedValue(generator = "PRODUTO_ID_SQ", strategy = SEQUENCE)
  @SequenceGenerator(name = "PRODUTO_ID_SQ", sequenceName = "PRODUTO_ID_SQ", allocationSize = 1)
  private Long id;
  private @NotBlank String nome;
  private @Positive BigDecimal valor;
  private @Positive Long qtDisponive;

  @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
  private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

  private @NotBlank @Size(max = 1000, min = 0) String descricao;

  @ManyToOne
  private Categoria categoria;

  private LocalDateTime tsCriacao;

  @ManyToOne
  private Usuario usuarioDono;

  @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
  private Set<ImagemProduto> imagens = new HashSet<>();

  public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive Long qtDisponive,
      @Size(min = 3) @NotNull List<CaracteristicaRequestForm> caracteristicas, Categoria categoria,
      @NotBlank @Size(max = 1000, min = 0) String descricao, Usuario usuarioDono) {
    this.nome = nome;
    this.valor = valor;
    this.qtDisponive = qtDisponive;
    this.descricao = descricao;
    this.tsCriacao = LocalDateTime.now();
    this.usuarioDono = usuarioDono;
    this.categoria = categoria;
    this.caracteristicas =
        caracteristicas.stream().map(c -> c.toModel(this)).collect(Collectors.toSet());
  }

  @Deprecated
  public Produto() {
    // Auto-generated constructor stub
  }

  public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive Long qtDisponive,
      Function<Produto, Set<CaracteristicaProduto>> caracteristicas, Categoria categoria,
      @NotBlank @Size(max = 1000, min = 0) String descricao, Usuario usuarioDono) {
    this.nome = nome;
    this.valor = valor;
    this.qtDisponive = qtDisponive;
    this.descricao = descricao;
    this.tsCriacao = LocalDateTime.now();
    this.usuarioDono = usuarioDono;
    this.categoria = categoria;
    this.caracteristicas = caracteristicas.apply(this);

  }

  public Long getId() {
    return id;
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

  public Set<CaracteristicaProduto> getCaracteristicas() {
    return caracteristicas;
  }

  public String getDescricao() {
    return descricao;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public LocalDateTime getTsCriacao() {
    return tsCriacao;
  }

  public Usuario getUsuarioDono() {
    return usuarioDono;
  }

  public Set<ImagemProduto> getImagens() {
    return imagens;
  }
  @Override
  public String toString() {
    return String
        .format(
            "Produto [id=%s, nome=%s, valor=%s, qtDisponive=%s, caracteristicas=%s, descricao=%s, categoria=%s, tsCriacao=%s, usuarioDono=%s]",
            id, nome, valor, qtDisponive, caracteristicas, descricao, categoria, tsCriacao,
            usuarioDono);
  }

  public void associaImagem(Set<String> links) {
    Set<ImagemProduto> imagens =
        links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
    this.imagens.addAll(imagens);
  }


}
