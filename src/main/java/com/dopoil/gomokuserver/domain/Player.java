package com.dopoil.gomokuserver.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;
import java.util.UUID;

@Entity
@Data
public class Player {
    @Id
    private UUID id;
    private String user;
    private URL networkId;
    private int token;
}
