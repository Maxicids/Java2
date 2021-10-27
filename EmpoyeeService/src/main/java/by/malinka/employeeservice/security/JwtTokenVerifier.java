package by.malinka.employeeservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private final SecretKey secretKey;

    public JwtTokenVerifier(JwtConfiguration jwtConfiguration, SecretKey secretKey) {
        this.jwtConfiguration = jwtConfiguration;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfiguration.getAuthorizationHeader());
        if (Strings.isEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfiguration.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace(jwtConfiguration.getTokenPrefix(), "");

        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token).getBody();
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            body.getSubject(), //username
                            null,
                            //Set<SimpleGrantedAuthority>
                            ((List<Map<String, String>>) body.get("authorities")).stream()
                                    .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                                    .collect(Collectors.toSet())
                    )
            );
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new RuntimeException("Format does not match the format expected by the application", unsupportedJwtException);
        } catch (MalformedJwtException malformedJwtException) {
            throw new RuntimeException("Jwt not correctly constructed and should be rejected", malformedJwtException);
        } catch (SignatureException exception) {
            throw new RuntimeException("Jwt signature validation fails", exception);
        } catch (ExpiredJwtException exception) {
            throw new RuntimeException("Jwt has an expiration time before the time this method is invoked", exception);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("Jwt string is null or has only whitespaces", exception);
        }

        filterChain.doFilter(request, response);
    }
}
