package com.example.appevc03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appevc03.databinding.FragmentFormularioBinding
import com.example.appevc03.databinding.FragmentListadoBinding


class ListadoFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListadoBinding.inflate(inflater, container, false)
        return binding.root
    }
}