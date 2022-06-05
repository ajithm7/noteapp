package com.example.noteapplication.database

import androidx.room.*
import java.util.*

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="desc")
    var desc:String,
    @ColumnInfo(name="date")
    var date:String
        )