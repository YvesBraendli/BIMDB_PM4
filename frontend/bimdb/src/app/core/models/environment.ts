import { KeycloakConfig } from 'keycloak-js';

export interface Environment {
	apiBaseUrl: string;
	keycloackConfig: KeycloakConfig;
}
