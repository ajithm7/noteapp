package com.example.noteapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.database.Note

class NoteAdapter(var list:List<Note>):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(view: View):RecyclerView.ViewHolder(view){
        var title:TextView=view.findViewById(R.id.title)
        var desc:TextView=view.findViewById(R.id.desc)
        var date:TextView=view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=list[position]
        holder.title.text=note.title
        holder.desc.text=note.desc
        holder.date.text=note.date
    }

    override fun getItemCount(): Int {
       return list.size
    }
}