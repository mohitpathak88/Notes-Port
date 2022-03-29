package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    //Main Activity only interacts with viewModel
    //Now we have to create an instance of viewModel
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter          //Linking with adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        //ViewModelProvider is a class which provides us with models
        //owner is Main Activity which means it handles the lifecycle(Live Data) of view Model

        //Now to get data from view Model
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                //"?." checks for nullability. If the list is not null only then it would update it
                adapter.updateList(it)      //for updating anytime there is a change in data
            }

        })
        //allnotes here is Live Data
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        //Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val input: EditText = findViewById(R.id.input)
        val noteText = input.text.toString()        //for extracting text which was input by submit button
        if(noteText.isNotEmpty())   {       //To ensure whether the text is not empty
            viewModel.insertNote(Note(noteText))
           // Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_LONG).show()
        }
    }
}