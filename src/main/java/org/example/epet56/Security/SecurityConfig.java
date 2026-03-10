package org.example.epet56.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración para la seguridad de la aplicación utilizando Spring Security.
 * Define las reglas de autorización para los endpoints y configura un usuario en memoria.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad que define qué peticiones son permitidas o denegadas.
     *
     * @param http el objeto {@link HttpSecurity} para configurar la seguridad web.
     * @return el {@link SecurityFilterChain} construido.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita CSRF ya que es una API REST sin estado y no se usan formularios web.
                .csrf(csrf -> csrf.disable())
                // Configura las reglas de autorización para las peticiones HTTP.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/store/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/store/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/store/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/store/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // Habilita la autenticación básica HTTP.
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Define un {@link UserDetailsService} que provee un usuario en memoria para la autenticación.
     * Este es un método simple para desarrollo y pruebas. En producción, se debería usar una base de datos.
     *
     * @return un {@link InMemoryUserDetailsManager} con un usuario 'admin'.
     */
    @Bean
    public UserDetailsService users() {
        // Crea un usuario 'admin' con contraseña 'admin' y rol 'ADMIN'.
        // User.withDefaultPasswordEncoder() es inseguro para producción.
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
