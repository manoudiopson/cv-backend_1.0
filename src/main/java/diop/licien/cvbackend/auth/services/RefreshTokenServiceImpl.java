package diop.licien.cvbackend.auth.services;

import diop.licien.cvbackend.auth.RefreshToken;
import diop.licien.cvbackend.auth.RefreshTokenRepository;
import diop.licien.cvbackend.auth.RefreshTokenRequest;
import diop.licien.cvbackend.auth.RefreshTokenResponse;
import diop.licien.cvbackend.enums.TokenType;
import diop.licien.cvbackend.exceptions.TokenException;
import diop.licien.cvbackend.jwt.JwtService;
import diop.licien.cvbackend.user.User;
import diop.licien.cvbackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
@Slf4j @Service @RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    @Value("${application.security.jwt.refresh-token.expration}")
    private long refreshExpiration;

    @Override
    public RefreshToken createRefreshToken(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        RefreshToken refreshToken = RefreshToken.builder()
                .revoked(false)
                .user(user)
                .token(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .experyDate(Instant.now().plusMillis(refreshExpiration))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token == null){
            log.error("Token is null");
            throw new TokenException(null, "Token is null");
        }
        if (token.getExperyDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new TokenException(token.getToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return token;
    }

    @Override
    public RefreshTokenResponse generateNewToken(RefreshTokenRequest request) {
        User user = refreshTokenRepository.findByToken(request.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .orElseThrow(() -> new TokenException(request.getRefreshToken(), "Refresh token does not exist"));

        String token = jwtService.generateToken(user);
        return RefreshTokenResponse.builder()
                .accessToken(token)
                .refreshToken(request.getRefreshToken())
                .tokenType(TokenType.BEARER.name())
                .build();
    }
}
