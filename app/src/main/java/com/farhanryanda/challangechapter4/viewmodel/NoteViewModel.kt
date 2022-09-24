package com.farhanryanda.challangechapter4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.farhanryanda.challangechapter4.Room.Note
import com.farhanryanda.challangechapter4.Room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app : Application): AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<Note>>

    init{
        allNote = MutableLiveData()
        getAllNote()
    }
    fun getAllNoteObservers(): MutableLiveData<List<Note>> {
        return allNote
    }





    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }

    fun insertNote(entity: Note){
        val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getAllNote()
    }

    fun deleteNote(entity: Note){
        val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
        userDao?.deleteNote(entity)
        getAllNote()
    }

    fun updateNote(entity: Note){
        val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getAllNote()
    }



}