package com.example.rest_api.controller

import com.example.rest_api.model.entity.Note
import com.example.rest_api.model.response.NoteSuccessResponse
import com.example.rest_api.service.note.NoteService
import com.example.rest_api.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty

@RestController
@RequestMapping(Constants.NOTES_END_POINT)
class NoteController {

    @Autowired
    private lateinit var noteService: NoteService

    @GetMapping
    fun getAllNotes() = noteService.getAllNotes()

    @GetMapping("/{noteId}")
    fun getNote(@PathVariable noteId: Long): ResponseEntity<Note> {
        val note = noteService.getNoteById(noteId)
        note?.apply {
            return ResponseEntity.ok(this)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun addNote(@RequestBody note: Note): ResponseEntity<NoteSuccessResponse> {
        val id = noteService.addNote(note)
        return ResponseEntity(
            NoteSuccessResponse(success = true, id = id),
            HttpStatus.CREATED
        )
    }

    @PutMapping
    fun updateNote(@RequestBody note: Note): ResponseEntity<NoteSuccessResponse> {
        val id = noteService.updateNote(note)
        return if (id == note.id) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(
                NoteSuccessResponse(success = true, id = id),
                HttpStatus.CREATED
            )
        }
    }

    @DeleteMapping("/{noteId}")
    fun deleteNoteById(@PathVariable noteId: Long): ResponseEntity<Empty> {
        val deleted = noteService.deleteNoteById(noteId)
        return if (deleted) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
