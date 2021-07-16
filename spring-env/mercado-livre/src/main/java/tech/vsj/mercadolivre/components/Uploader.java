package tech.vsj.mercadolivre.components;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

  Set<String> envia(@Valid List<MultipartFile> novasImagens);

}
