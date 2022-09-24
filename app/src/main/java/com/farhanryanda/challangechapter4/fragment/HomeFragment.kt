package com.farhanryanda.challangechapter4.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhanryanda.challangechapter4.R
import com.farhanryanda.challangechapter4.Room.Note
import com.farhanryanda.challangechapter4.Room.NoteDatabase
import com.farhanryanda.challangechapter4.adapter.NoteAdapter
import com.farhanryanda.challangechapter4.databinding.FragmentHomeBinding
import com.farhanryanda.challangechapter4.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var noteVm : NoteViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("datauser", Context.MODE_PRIVATE)
        binding.tvWelcome.text = "Welcome, " + sharedPreferences.getString("username","")

        NoteDB = NoteDatabase.getInstance(requireActivity())

        setRecycler()
        noteVm = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteVm.getAllNoteObservers().observe(this.requireActivity(), {
            adapterNote.setNoteData(it as ArrayList<Note>)
        })

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        binding.btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(activity, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

    }

    fun setRecycler() {
        adapterNote = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapterNote
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }
    }

}