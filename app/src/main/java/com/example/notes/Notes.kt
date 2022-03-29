package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Notes is the table for our database
@Entity(tableName = "notes_table")
//Annotating the entity which means converting the class 'Notes' into a table in SQLite
class Note(@ColumnInfo(name = "text") val text: String){
            @PrimaryKey(autoGenerate = true) var id = 0
}
//The constructors(arguments) in class 'notes' would be the columns of the table
//ColumnInfo annotation helps in changing the name of the column
//PrimaryKey Annotation helps in auto incrementing the id

//Now we can only access these tables through DAO(Data access Object). Dao also helps in performing
//operations on database