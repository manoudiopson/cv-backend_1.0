package diop.licien.cvbackend.auth.services;

import diop.licien.cvbackend.auth.RefreshToken;
import diop.licien.cvbackend.auth.RefreshTokenRequest;
import diop.licien.cvbackend.auth.RefreshTokenResponse;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(Long idUser);
    public RefreshToken verifyExpiration(RefreshToken token);
    public RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
}
