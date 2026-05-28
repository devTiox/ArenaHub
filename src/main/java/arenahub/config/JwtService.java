package arenahub.config;

import arenahub.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${JWT_KEY:k6U3a0ZObyg0Cb88S8FqLTxfVn66NIpXBhExMRwXaCc}") String key){
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }
    public String generateToken(CustomUserDetails user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("accountType", user.getType())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(secretKey)
                .compact();
    }

    public Long extractAccountId(String token) {
        Claims claims = extractClaims(token);
        String subject = claims.getSubject();

        if (subject != null) {
            return Long.parseLong(subject);
        }

        return null;
    }

    public boolean isTokenValid(String token, CustomUserDetails userDetails) {
        Long accountId = extractAccountId(token);
        return accountId.equals(userDetails.getId()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
