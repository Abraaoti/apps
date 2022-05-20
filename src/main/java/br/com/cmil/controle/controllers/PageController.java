
package br.com.cmil.controle.controllers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author cmil
 */
public class PageController {
   public String fileDownloadUrl(final String fileName, final String baseURL){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(baseURL)
                .path(fileName).toUriString();
    }  
}
