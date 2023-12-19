package diop.licien.cvbackend.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
