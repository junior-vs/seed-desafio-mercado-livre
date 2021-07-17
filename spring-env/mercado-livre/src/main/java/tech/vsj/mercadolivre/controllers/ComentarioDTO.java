package tech.vsj.mercadolivre.controllers;

import javax.validation.constraints.Size;
import tech.vsj.mercadolivre.persistence.model.Comentario;

public class ComentarioDTO {


  private @Size(max = 5, min = 1, message = "Nota deve ser de 1 a 5") Integer nota;

  private String titulo;

  private String descricao;

  private Long produto;


  public ComentarioDTO(Comentario comentarioEntity) {
    super();
    this.nota = comentarioEntity.getNota();
    this.titulo = comentarioEntity.getTitulo();
    this.descricao = comentarioEntity.getDescricao();
    this.produto = comentarioEntity.getProduto().getId();

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


  public Long getProduto() {
    return produto;
  }



}
