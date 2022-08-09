package com.example.rest_api.model.entity

import javax.persistence.*

@Entity
class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    val user: User? = null,
    val title: String,
    @Column(nullable = false)
    val content: String
)
