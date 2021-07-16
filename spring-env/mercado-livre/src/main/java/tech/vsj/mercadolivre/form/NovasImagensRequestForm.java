package tech.vsj.mercadolivre.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.istack.NotNull;

public class NovasImagensRequestForm {
  
  @Size(min = 1)
  @NotNull
  private List<MultipartFile> imagens = new ArrayList<>();

  @JsonCreator
  public NovasImagensRequestForm(@Size(min = 1) List<MultipartFile> imagens) {
    super();
    this.imagens = imagens;
  }

  @Override
  public String toString() {
    return String.format("NovasImagensRequestForm Imgens size: " + imagens.size());
  }
  
  public List<MultipartFile> getImagens() {
    return imagens;
  }
  
  
  
  

}
