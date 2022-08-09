package com.example.rest_api.service.note

import com.example.rest_api.model.dto.PlainNote

interface NoteService {

    fun addNote(plainNote: PlainNote): Long

    fun getAllNotes(): List<PlainNote>

    fun getNoteById(id: Long): PlainNote?

    fun updateNote(plainNote: PlainNote): Long

    fun deleteNote(plainNote: PlainNote): Boolean

    fun deleteNoteById(id: Long): Boolean
}
