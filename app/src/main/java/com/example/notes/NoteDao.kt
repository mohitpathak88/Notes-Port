package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao    //For creating the below interface as Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)         //Type of query to perform
    //on conflict means re inserting same data again, so it would ignore in that case
    suspend fun insert(note: Note)      //For Inserting a note
    //A suspend function is a function which can be called only by a background thread or by an other suspend fn

    @Delete         //Type of query to perform
    suspend fun delete(note: Note)      //For deleting a note
    //We need these 2 function to avoid running on main threads to let it handle UI so that it doesnt lags.
    //Thats why we used suspend function to operate them by background threads

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>    //For fetching all the notes by returning list of notes
    //Live data is an architecture component which observes the data and reports the changes to the observer
    //Hence we would get to know whenever data is updated
}

//DAO handles all our operations of our database