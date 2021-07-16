package tech.vsj.mercadolivre.components;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {

  @Override
  public Set<String> envia(@Valid List<MultipartFile> novasImagens) {
    return novasImagens
        .stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
        .collect(Collectors.toSet());
  }


}
