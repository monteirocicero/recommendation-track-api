package com.orecic.recommentationtrackapi.infrastructure.data.spotify;

import feign.form.FormProperty;

public class SpofifyTokenRequest {

    @FormProperty("client_id")
    private String clientId;

    @FormProperty("client_secret")
    private String secretClient;

    @FormProperty("grant_type")
    private String grantType;


    public SpofifyTokenRequest(String clientId, String secretClient, String grantType) {
        this.clientId = clientId;
        this.secretClient = secretClient;
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecretClient() {
        return secretClient;
    }

    public void setSecretClient(String secretClient) {
        this.secretClient = secretClient;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
