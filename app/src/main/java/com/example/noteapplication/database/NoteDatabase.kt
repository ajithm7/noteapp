package com.example.noteapplication.database
import androidx.room.*

import android.content.Context

@Database(entities = [Note::class],version=1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase() {
    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
                            .build()
                    return INSTANCE
                }
            }
            return INSTANCE
        }
    }

    abstract fun noteDao(): NoteDao
}
