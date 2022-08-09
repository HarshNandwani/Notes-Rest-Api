package com.example.rest_api.service.note

import com.example.rest_api.model.dto.PlainNote
import com.example.rest_api.repository.NoteRepository
import com.example.rest_api.model.entity.Note
import com.example.rest_api.model.entity.User
import com.example.rest_api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl : NoteService {

    @Autowired
    private lateinit var noteRepository: NoteRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun getAllNotes(): List<PlainNote> {
        val notes = noteRepository.getNoteByUser(getCurrentUser())
        return notes.map { PlainNote(it) }
    }

    override fun addNote(plainNote: PlainNote): Long {
        val savedNote = noteRepository.save(constructNoteWithUser(plainNote))
        return savedNote.id
    }

    override fun getNoteById(id: Long): PlainNote? {
        return noteRepository.findById(id).map { PlainNote(it) }.orElse(null)
    }

    override fun updateNote(plainNote: PlainNote): Long {
        val savedNote = noteRepository.save(constructNoteWithUser(plainNote))
        return savedNote.id
    }

    override fun deleteNote(plainNote: PlainNote): Boolean {
        noteRepository.delete(constructNoteWithUser(plainNote))
        return true
    }

    override fun deleteNoteById(id: Long): Boolean {
        val noteToDelete = noteRepository.findById(id).orElse(null)
        noteToDelete?.apply {
            noteRepository.delete(this)
            return true
        }
        return false
    }

    private fun getCurrentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.principal as String
        return userRepository.findUserByEmail(email)
    }

    private fun constructNoteWithUser(plainNote: PlainNote) = Note(plainNote, getCurrentUser())

}
