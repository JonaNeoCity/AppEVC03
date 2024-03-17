package com.example.appevc03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevc03.databinding.FragmentFormularioBinding
import com.example.appevc03.databinding.FragmentLoginBinding

class FormularioFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFormularioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onClick(v: View?) {

    }
}