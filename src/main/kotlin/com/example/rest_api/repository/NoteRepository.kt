package com.example.rest_api.repository

import com.example.rest_api.model.entity.Note
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository: CrudRepository<Note, Long>
