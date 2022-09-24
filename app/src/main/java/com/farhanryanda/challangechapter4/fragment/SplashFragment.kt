package com.farhanryanda.challangechapter4.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.farhanryanda.challangechapter4.R
import com.farhanryanda.challangechapter4.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().applicationContext.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)

        Handler().postDelayed ({
            if (sharedPreferences.getString("username","") == "" && sharedPreferences.getString("password","") == ""){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment2)
            }else {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

        }, 3000)
    }

}