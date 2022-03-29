package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
///'Database' annotation would tell that the below class is actually a database. In the annotation,
//we ll first tell our entity. Entity here is 'Note'
abstract class NoteDatabase: RoomDatabase() {       //Extending with RoomDatabase

    abstract fun getNoteDao(): NoteDao      //Function would return a NoteDao

    //Now we have to make our database Singleton to prevent multiple accesses of same data at same time
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null      //Instance is NoteDatabase. Initiating it with null

        fun getDatabase(context: Context): NoteDatabase {     //getDatabase calls our instance
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {     //'Synchronized' ensures race condition dont exists
        //Above line checks if the instance is null or not. If it is, bracket part would be executed otherwise not
                val instance = Room.databaseBuilder(        //To create instance of our abstract class indirectly
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()               //Builds the instance
                INSTANCE = instance
                instance
                // return instance
            }
        }
    }
}

//Abstract class means we cannot create an object for it but can create one for its children