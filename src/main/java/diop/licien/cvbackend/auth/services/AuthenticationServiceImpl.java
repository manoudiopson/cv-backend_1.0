package diop.licien.cvbackend.auth.services;

import diop.licien.cvbackend.auth.AuthenticationRequest;
import diop.licien.cvbackend.auth.AuthenticationResponse;
import diop.licien.cvbackend.auth.RegisterRequest;
import diop.licien.cvbackend.enums.TokenType;
import diop.licien.cvbackend.jwt.JwtService;
import diop.licien.cvbackend.user.User;
import diop.licien.cvbackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional @RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getFirstname())
                .username(request.getLastname())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        user = userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getIdUser());

        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .email(user.getEmail())
                .id(user.getIdUser())
                .refreshToken(refreshToken.getToken())
                .roles(roles)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user  = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getIdUser());

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .email(user.getEmail())
                .id(user.getIdUser())
                .refreshToken(refreshToken.getToken())
                .tokenType(TokenType.BEARER.name())
                .build();
    }
}
