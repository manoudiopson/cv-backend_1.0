package diop.licien.cvbackend.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface JwtService {
    public String extractUserName(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);

    String getJwtFromCookies(HttpServletRequest request);
}
