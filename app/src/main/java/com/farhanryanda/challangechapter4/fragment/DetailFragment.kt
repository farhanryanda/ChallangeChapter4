package com.farhanryanda.challangechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanryanda.challangechapter4.Room.Note
import com.farhanryanda.challangechapter4.Room.NoteDatabase
import com.farhanryanda.challangechapter4.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    var dbNote: NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var getDataNote = arguments?.getSerializable("dataedit") as Note

        binding.tvJudul.setText(getDataNote.title)
        binding.tvContent.setText(getDataNote.content)
    }
}