package com.example.rest_api.model.entity

import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val notes: List<Note>? = null,
    @Column(nullable = false)
    val name: String,
    @Column(unique = true, nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
)
