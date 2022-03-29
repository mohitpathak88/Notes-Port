package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ViewModel interacts with the Repository
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    //Extending NoteViewModel with AndroidViewModel
    //'application' is the variable and 'Application' is the data type

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        //Getting dao through fun getNoteDao()

        repository = NoteRepository(dao)
        allNotes = repository.allNotes
        //Now we can get all notes in our Main Activity
    }

    //Now we ll make a delete function which would call the delete function of the repository.
    //delete fn of repository is a suspend function which means it can be accessed only by another suspend fn or by a background thread.
    //Here we ll access it by thread using coroutineScope
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)     //This would work in background thread
        //IO here is input output
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}