package tech.build.smartstock.service;

import org.springframework.stereotype.Service;
import tech.build.smartstock.client.AuthClient;
import tech.build.smartstock.client.dto.AuthRequest;
import tech.build.smartstock.config.AppConfig;
import tech.build.smartstock.exception.SmartStockException;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static String GRANT_TYPE = "client_credentials";
    private static String token;
    private static LocalDateTime expiresIn;

    private final AuthClient authClient;
    private final AppConfig appConfig;

    public AuthService(AuthClient authClient, AppConfig appConfig) {
        this.authClient = authClient;
        this.appConfig = appConfig;
    }

    public String getToken() {
        if (token == null) {
            genereteToken();
        } else if (expiresIn.isBefore(LocalDateTime.now())) {
            genereteToken();
        }

        return token;
    }

    private void genereteToken() {

        var request = new AuthRequest(
                GRANT_TYPE,
                appConfig.getClientId(),
                appConfig.getClientSecret()
        );

        var response = authClient.authenticate(request);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new SmartStockException("cannot generate token, " +
                    "status: " + response.getStatusCode() +
                    "response: " + response.getBody());
        }

        token = response.getBody().accessToken();
        expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
    }
}
