package com.example.rest_api.service.note

import com.example.rest_api.model.entity.Note
import com.example.rest_api.model.response.NoteSuccessResponse

interface NoteService {

    fun addNote(note: Note): Long

    fun getAllNotes(): List<Note>

    fun getNoteById(id: Long): Note?

    fun updateNote(note: Note): Long

    fun deleteNote(note: Note): Boolean

    fun deleteNoteById(id: Long): Boolean
}
