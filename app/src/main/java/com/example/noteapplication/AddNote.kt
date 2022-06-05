package com.example.noteapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.noteapplication.database.Note
import com.example.noteapplication.database.NoteDao
import com.example.noteapplication.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    private lateinit var title:EditText
    private lateinit var desc:EditText
    private lateinit var save:Button
    private lateinit var titleText:String
    private lateinit var descText:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setUI()
        save.setOnClickListener{
            titleText=title.text.toString()
            descText=desc.text.toString()
            if(validate()){
                saveNote()
            }
        }
    }


    private fun saveNote() {
        val database: NoteDatabase?=NoteDatabase.getInstance(context = this)
        val noteDao:NoteDao?=database?.noteDao()
        val date:String=
            SimpleDateFormat("EEE, DD MMM yy").format(Date())
        Toast.makeText(this,"$date",Toast.LENGTH_LONG).show()
        val note= Note(0,titleText,descText,date)
        GlobalScope.launch{
            noteDao?.insert(note)
            withContext(Dispatchers.Main){
                Toast.makeText(this@AddNote,"Note saved $date",Toast.LENGTH_LONG).show()
                finish()
            }
        }
//        noteDao?.insert(note)

    }

    fun validate():Boolean{
        if(titleText.trim().isBlank() or descText.trim().isBlank()){
            Toast.makeText(this,"please enter title and description",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun setUI() {
        title=findViewById(R.id.title)
        desc=findViewById(R.id.desc)
        save=findViewById(R.id.save)

    }
}