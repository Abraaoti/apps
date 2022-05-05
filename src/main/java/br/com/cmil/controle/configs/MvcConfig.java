package br.com.cmil.controle.configs;

import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author cmilseg
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    final Environment environment;

    public MvcConfig(Environment environment) {
        this.environment = environment;
    }
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         String location = environment.getProperty("app.file.upload-dir");
        registry.addResourceHandler("/static/docs/**")
                .addResourceLocations(location);
        exposeDirectory("classpath:/static/docs/", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        java.nio.file.Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) {
            dirName = dirName.replace("../", "");
        }

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
    }
}
