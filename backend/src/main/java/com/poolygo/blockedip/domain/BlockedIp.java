package com.poolygo.blockedip.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockedIp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blocked_ip_id")
    private Long id;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String description;

}
