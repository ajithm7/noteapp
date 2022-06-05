package com.example.noteapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.database.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById(R.id.add)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        setData()

        add.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNote::class.java))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        val database = NoteDatabase.getInstance(applicationContext)
        val noteDao = database!!.noteDao()
        val llm = LinearLayoutManager(this)

//        database operation should not be in main thread. so use corooutine
//        val list=noteDao?.getAllNotes()
        CoroutineScope(Dispatchers.IO).launch {
            val list = noteDao.getAllNotes()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity,"inside withContext adapter creation", Toast.LENGTH_SHORT).show()
                adapter = NoteAdapter(list)
                recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()


            }
        }

    }
}