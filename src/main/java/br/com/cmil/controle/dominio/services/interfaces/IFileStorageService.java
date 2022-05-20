package br.com.cmil.controle.dominio.services.interfaces;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author cmil
 */
public interface IFileStorageService {

    String storeFile(MultipartFile file) throws IOException;

    Resource loadFileAsResource(String fileName);

     String getFileExtension(String fileName);
}
