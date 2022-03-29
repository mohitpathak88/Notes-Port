package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {     //Extending with recycler view adapter
    //NVH?

    val allNotes = ArrayList<Note>()        //Data which would be used to pass in the adapter
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){     //inner class is a class within a class
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {     //fun returns NoteViewHolder
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text     //inserting current note in the holder
    }

    fun updateList(newsList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newsList)

        notifyDataSetChanged()      //Now updating recycler view
    }

    override fun getItemCount(): Int {
        return allNotes.size

    }

}

interface INotesRVAdapter {     //To handle onclicks
    fun onItemClicked(note: Note)

}