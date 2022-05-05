package br.com.cmil.controle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleApplication {

    public static void main(String[] args) {
        // System.out.println("password:\t "+new BCryptPasswordEncoder().encode("123"));
        SpringApplication.run(ControleApplication.class, args);
        // SpringApplication app = new SpringApplication(ControleApplication.class);
        //app.setBanner((environment, sourceClass, out) -> {
        //   out.println("======= Bem-vindo ao Controle de Sistema Financeiro M&E =======");
        // });
        // app.run(args);
    }

}
