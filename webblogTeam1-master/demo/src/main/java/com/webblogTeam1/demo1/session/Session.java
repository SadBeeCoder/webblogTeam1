package com.webblogTeam1.demo1.session;

import com.webblogTeam1.demo1.user.BlogUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Session {

    @Id
    private final String id = UUID.randomUUID().toString();


    @ManyToOne
    private BlogUser user;

    private Instant expiresAt;

    public Session() {
    }

    public Session(BlogUser user, Instant expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
