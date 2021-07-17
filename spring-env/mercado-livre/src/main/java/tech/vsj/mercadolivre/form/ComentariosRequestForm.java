package tech.vsj.mercadolivre.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonCreator;
import tech.vsj.mercadolivre.persistence.model.Comentario;
import tech.vsj.mercadolivre.persistence.model.Produto;
import tech.vsj.mercadolivre.persistence.model.Usuario;

public class ComentariosRequestForm {

  @Max(5)
  @Min(1)
  private Integer nota;
  @NotBlank
  private String titulo;
  @NotBlank
  @Size(max = 500)
  private String descricao;


  @JsonCreator
  public ComentariosRequestForm(@Max(5) @Min(1) Integer nota, @NotBlank String titulo,
      @NotBlank @Size(max = 500) String descricao) {
    super();
    this.nota = nota;
    this.titulo = titulo;
    this.descricao = descricao;
  }



  public Comentario toModel(Produto produto, Usuario usuario) {

    return new Comentario(this.nota, this.titulo, this.descricao, produto, usuario);

  }

}
