
package br.com.cmil.controle.configs;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class CustomizationBean implements   WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8080);
       
    }
    
}
