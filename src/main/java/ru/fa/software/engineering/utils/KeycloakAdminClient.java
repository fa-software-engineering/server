package ru.fa.software.engineering.utils;

import org.keycloak.admin.client.Keycloak;
import ru.fa.software.engineering.vars.KeycloakProps;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KeycloakAdminClient {
    @Inject
    KeycloakProps keycloakProps;

    public Keycloak getKeycloak() {
        return Keycloak.getInstance(
                keycloakProps.serverUrl(),
                keycloakProps.realm(),
                keycloakProps.adminUsername(),
                keycloakProps.adminPassword(),
                keycloakProps.clientId(),
                keycloakProps.clientSecret());
    }

    public String getAccessToken(String username, String password) {
        return Keycloak.getInstance(
                keycloakProps.serverUrl(),
                keycloakProps.realm(),
                username,
                password,
                keycloakProps.clientId(),
                keycloakProps.clientSecret()).tokenManager().getAccessToken().getToken();
    }
}
