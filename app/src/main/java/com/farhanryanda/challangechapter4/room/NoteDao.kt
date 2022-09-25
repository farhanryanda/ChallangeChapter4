package com.farhanryanda.challangechapter4.room

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY id DESC ")
    fun getDataNote() : List<Note>

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)



}