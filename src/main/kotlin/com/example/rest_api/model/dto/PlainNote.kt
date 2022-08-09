package com.example.rest_api.model.dto

import com.example.rest_api.model.entity.Note

data class PlainNote(
    val id: Long? = null,
    val title: String,
    val content: String
) {
    constructor(note: Note) : this(note.id, note.title, note.content)
}
