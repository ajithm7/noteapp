package com.example.noteapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note:Note)
    @Update
    fun update(note:Note)
//    @Delete
//    fun delete()
    @Query("DELETE FROM note_table" )
    fun clear()
    @Query("SELECT * FROM note_table")
    fun getAllNotes():List<Note>
}