package inhatc.cse.resumehong.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.csrf(AbstractHttpConfigurer::disable); CSRF 보안 설정 비활성화

        // CSRF 비활성화: 정적 리소스 요청에 대해 CSRF 검사 제외
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/css/**", "/images/**", "/img/**", "src/main/resources/static/images/"));

        http.formLogin(form -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login/error")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
        );
        http.logout(Customizer.withDefaults());

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**", "/img/**", "/images/**","/error", "src/main/resources/static/images/").permitAll()
                .requestMatchers("/", "/member/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );

        //http.exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint((req, res, ex) -> {
                    System.out.println("Authentication required for: " + req.getRequestURI());
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                })
        );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
