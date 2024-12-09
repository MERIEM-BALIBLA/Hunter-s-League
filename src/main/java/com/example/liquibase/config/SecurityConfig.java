package com.example.liquibase.config;

import com.example.liquibase.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration //Configuration des beans comme config.XML
//Active la configuration de sécurité web de Spring Security
//Permet de personnaliser la sécurité des applications web
//Initialise les filtres de sécurité et les configurations par défaut
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) //Active la sécurité au niveau des méthodes (@PreAuthorize/@PostAuthorize)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable())
                //Définit les autorisations pour différents endpoints
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/users/**").hasRole("ADMIN")
                        .requestMatchers("api/competitions/**").hasAnyRole("ADMIN", "MEMBER")
                        .requestMatchers("api/species/**").permitAll()
                        .requestMatchers("api/hunt/**").permitAll()
                        .requestMatchers("api/participation/**").permitAll()
                        .requestMatchers("api/auth/**").permitAll()
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
                //Authorization: Basic base64(username:password)
                .httpBasic(Customizer.withDefaults())
                //Configure la gestion des sessions comme STATELESS
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Ajoute un filtre JWT personnalisé avant le filtre d'authentification standard
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

// AuthenticationProvider est une interface responsable de la logique d'authentification d'un utilisateur

/* Il sert de composant principal (dans ce cas de type implementation de UserDetails) pour valider
 les informations d'identification fournies par un utilisateur (username et password)*/

// return un objet authentication valide

// Il vérifie les informations fournies à l'aide d'une source de données ou d'une logique personnalisée

// objet Authentication contient : Le principal représente l'identité de l'utilisateur authentifié / Les credentials contiennent généralement les informations de connexion fournies par l'utilisateur, comme un mot de passe.
//                                   /Les authorities représentent les rôles ou permissions attribués à l'utilisateur authentifié.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
