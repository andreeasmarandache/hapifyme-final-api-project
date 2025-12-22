package com.hapifyme.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    private String token;
    private UserDetails user;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public UserDetails getUser() { return user; }
    public void setUser(UserDetails user) { this.user = user; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserDetails {
        private String id;
        private String username;
        private String email;

        public UserDetails() {
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
