package diop.licien.cvbackend.auth.services;

import diop.licien.cvbackend.auth.AuthenticationRequest;
import diop.licien.cvbackend.auth.AuthenticationResponse;
import diop.licien.cvbackend.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
