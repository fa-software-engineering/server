docker exec -it docker-services-keycloak-1 /opt/jboss/keycloak/bin/standalone.sh \
-Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=export \
-Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=resource-accounting-system \
-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
-Dkeycloak.migration.file=/keycloak/config/realm-export.json