# 🛡️ Spring Security con JWT – Configurazione e Funzionamento

Questo documento descrive come è stata configurata la sicurezza nell'applicazione usando Spring Security e JWT (JSON Web Token).

---

## 📁 1. SecurityConfig.java

### ✨ Scopo
Definisce tutte le regole di sicurezza: quali rotte sono pubbliche, quali sono protette, come viene gestito il token JWT, e consente l'accesso alla console H2.

### 🔧 Codice di configurazione

```java
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public AuthenticationManager authenticatorManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
```

### 📌 Comportamento

| Componente                          | Funzione                                                |
|------------------------------------|----------------------------------------------------------|
| `csrf().disable()`                 | Disabilita protezione CSRF per API stateless             |
| `frameOptions().sameOrigin()`      | Abilita la console H2 che usa `<iframe>`                |
| `sessionCreationPolicy(STATELESS)` | Nessuna sessione salvata lato server                    |
| `.requestMatchers(...).permitAll()`| Rotte accessibili senza autenticazione                  |
| `.anyRequest().authenticated()`    | Tutte le altre richiedono autenticazione                |
| `.addFilterBefore(...)`            | Inserisce il filtro JWT nella catena di filtri          |
| `.httpBasic(...)`                  | Necessario per accedere alla console H2                 |

---

## 🧱 2. JwtAuthFilter.java

### ✨ Scopo
Filtro personalizzato che intercetta ogni richiesta:

1. Verifica la presenza del token JWT
2. Estrae l'email dal token
3. Recupera l'utente dal DB
4. Imposta l'autenticazione nel SecurityContextHolder

### 🔧 Codice semplificato

```java
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUserEmail(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = UserMapper.toModel(userService.findByEmail(userEmail).orElse(null));

            if (user != null && jwtService.isTokenValid(jwt)) {
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

---

## 🧠 Come funziona tutto insieme

```
[Richiesta HTTP con JWT]
       ↓
[JwtAuthFilter] → Verifica token, recupera utente, autentica
       ↓
[SecurityFilterChain] → Verifica permessi
       ↓
[Controller] → Recupera l'utente autenticato dal SecurityContext
```

---

## ✅ Risultato finale

- Tutti gli endpoint protetti richiedono un JWT valido
- Nessuna sessione server-side
- Console H2 accessibile per sviluppo

---

## 📚 Prossimi passi

1. Integrare Swagger/OpenAPI per documentare le API
2. Usare Postman per testare la sicurezza
3. Aggiungere scadenza e refresh token
4. Integrare frontend con token (es. React, Vue)

---

Questo file può essere salvato come `SECURITY.md` nella root del progetto per uso documentativo.

