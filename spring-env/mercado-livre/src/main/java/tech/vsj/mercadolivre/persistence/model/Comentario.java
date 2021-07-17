package tech.vsj.mercadolivre.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Comentario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMENTARIO_ID_SEQ")
  @SequenceGenerator(allocationSize = 1, name = "COMENTARIO_ID_SEQ",
      sequenceName = "COMENTARIO_ID_SEQ")
  private Long id;

  @Min(1)
  @Max(5)
  private Integer nota;

  @NotBlank
  private String titulo;

  @NotBlank
  @Size(max = 500)
  private String descricao;

  @ManyToOne
  private Produto produto;

  @ManyToOne
  private Usuario usuario;

  public Comentario(@NotBlank @Size(max = 500) Integer nota, @NotBlank String titulo,
      @NotBlank @Size(max = 500) String descricao, Produto produto, Usuario usuario) {
    this.nota = nota;
    this.titulo = titulo;
    this.descricao = descricao;
    this.produto = produto;
    this.usuario = usuario;
  }

  @Deprecated
  public Comentario() {
    // Auto-generated constructor stub
  }

  public Long getId() {
    return id;
  }

  public Integer getNota() {
    return nota;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Produto getProduto() {
    return produto;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  @Override
  public String toString() {
    return "Comentario [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao="
        + descricao + ", produto=" + produto.getId() + ", usuario=" + usuario.getUsername() + "]";
  }



}
