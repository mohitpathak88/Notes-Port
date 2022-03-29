package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao){

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()    //Getting all notes from the fun in NoteDao

    suspend fun insert(note: Note)  {
        noteDao.insert(note)    //For inserting note by calling insert function in NoteDao
    }

    suspend fun delete(note: Note)  {
        noteDao.delete(note)
    }
}

//Repository Job is to handle all the data from different sources into single source and providing
// it to our Model. Sources can be of database,apis,etc
//Here we are communicating with DAO for the database