package com.farhanryanda.challangechapter4.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.farhanryanda.challangechapter4.MainActivity
import com.farhanryanda.challangechapter4.R
import com.farhanryanda.challangechapter4.room.Note
import com.farhanryanda.challangechapter4.room.NoteDatabase
import com.farhanryanda.challangechapter4.databinding.ItemViewBinding
import com.farhanryanda.challangechapter4.fragment.HomeFragment
import com.farhanryanda.challangechapter4.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class NoteAdapter(var listNote : List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var DBNote : NoteDatabase? = null
    lateinit var noteVm : NoteViewModel

    class ViewHolder(var binding : ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindnote(noteItem: Note) {
            binding.note = noteItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        noteVm = ViewModelProvider(holder.itemView.context as AppCompatActivity).get(NoteViewModel::class.java)

        val data = listNote[position]
        holder.bindnote(data)
        holder.binding.btnDelete.setOnClickListener{
            DBNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                noteVm.deleteNote(listNote[position])
                (holder.itemView.context as MainActivity).runOnUiThread{
                    (holder.itemView.findFragment<HomeFragment>()).getAllNote()
                }
            }
        }

        holder.binding.btnEdit.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable("dataedit", listNote[position])
            it.findNavController().navigate(R.id.action_homeFragment_to_editFragment,bundle)
        }
        holder.binding.cvNote.setOnClickListener{
            var bundle = Bundle()
            bundle.putSerializable("dataedit", listNote[position])
            it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return  listNote.size
    }

    fun setNoteData(listNote: ArrayList<Note>)
    {
        this.listNote=listNote
    }
}