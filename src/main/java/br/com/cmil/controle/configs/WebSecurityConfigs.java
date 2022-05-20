package br.com.cmil.controle.configs;

import br.com.cmil.controle.dominio.enuns.PerfilTipo;
import br.com.cmil.controle.dominio.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author ti
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigs extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String ADMINISTRATIVO = PerfilTipo.ADMINISTRATIVO.getDesc();
    private static final String DIRETORIA = PerfilTipo.DIRETORIA.getDesc();
    private static final String ENGENHEIRO = PerfilTipo.ENGENHEIRO.getDesc();
    private static final String FINANCEIRO = PerfilTipo.FINANCEIRO.getDesc();
    private static final String RH = PerfilTipo.RH.getDesc();
    private static final String TECNICO = PerfilTipo.TECNICO.getDesc();

    @Autowired
    private IUsuarioService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/webjars/**", "/css**", "/js/**", "/image/**", "/docs/**", "/error**").permitAll()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/u/novo/cadastro", "/u/cadastro/financeiro/salvar", "/u/cadastro/realizado").permitAll()
                .antMatchers("/u/confirmacao/cadastro").permitAll()
                .antMatchers("/u/p/**").permitAll()
                //acessos privados admin
                .antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FINANCEIRO, ADMINISTRATIVO)
                .antMatchers("/u/**").hasAuthority(ADMIN)
                //acessos privados medico

                .antMatchers("/medicos/especialidade/titulo/*").hasAuthority(FINANCEIRO)
                .antMatchers("/diretoria/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                .antMatchers("/medicos/**").hasAuthority(ADMINISTRATIVO)
                //acessos privados especialidades
                .antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(ADMINISTRATIVO, ADMIN)
                .antMatchers("/especialidades/titulo").hasAnyAuthority(ADMINISTRATIVO, ADMIN, FINANCEIRO)
                .antMatchers("/especialidades/**").hasAuthority(ADMIN)
                //acessos privados pacientes
                .antMatchers("/agendamentos//horario/medico/**").hasAuthority(ADMINISTRATIVO)
                .antMatchers("/financeiro/contapagar/**").hasAuthority(FINANCEIRO)
                .antMatchers("/administrativo/**").hasAuthority(ADMINISTRATIVO)
                .antMatchers("/diretoria/**").hasAuthority(DIRETORIA)
                .antMatchers("/engenheiro/**").hasAuthority(ENGENHEIRO)
                .antMatchers("/financeiro/**").hasAuthority(FINANCEIRO)
                .antMatchers("/rh/**").hasAuthority(RH)
                .antMatchers("/tecnico/**").hasAuthority(TECNICO)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.usernameParameter("username")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SessionRegistry sessionRegistry() {

        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {

        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
