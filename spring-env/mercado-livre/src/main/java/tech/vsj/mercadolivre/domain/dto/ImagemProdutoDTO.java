package tech.vsj.mercadolivre.domain.dto;

import tech.vsj.mercadolivre.persistence.model.ImagemProduto;

public class ImagemProdutoDTO {

  private Long id;
  private String link;

  public ImagemProdutoDTO(ImagemProduto imagemProduto) {
    super();
    this.id = imagemProduto.getId();
    this.link = imagemProduto.getLink();
  }

  public Long getId() {
    return id;
  }

  public String getLink() {
    return link;
  }

  @Override
  public String toString() {
    return String.format("ImagemProdutoDTO [id=%s, link=%s]", id, link);
  }
}
