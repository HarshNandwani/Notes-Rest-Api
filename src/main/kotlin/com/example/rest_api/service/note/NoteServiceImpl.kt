package com.example.rest_api.service.note

import com.example.rest_api.repository.NoteRepository
import com.example.rest_api.model.entity.Note
import com.example.rest_api.model.response.NoteSuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl : NoteService {

    @Autowired
    private lateinit var dao: NoteRepository

    override fun getAllNotes(): List<Note> {
        return dao.findAll().toList()
    }

    override fun addNote(note: Note): Long {
        val savedNote = dao.save(note)
        return savedNote.id
    }

    override fun getNoteById(id: Long): Note? {
        return dao.findById(id).orElse(null)
    }

    override fun updateNote(note: Note): Long {
        val savedNote = dao.save(note)
        return savedNote.id
    }

    override fun deleteNote(note: Note): Boolean {
        dao.delete(note)
        return true
    }

    override fun deleteNoteById(id: Long): Boolean {
        val noteToDelete = dao.findById(id).orElse(null)
        noteToDelete?.apply {
            dao.delete(this)
            return true
        }
        return false
    }
}
