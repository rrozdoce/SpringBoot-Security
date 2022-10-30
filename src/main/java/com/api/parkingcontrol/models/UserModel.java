package com.api.parkingcontrol.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel {
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
}
