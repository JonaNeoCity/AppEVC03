package com.example.appevc03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.appevc03.databinding.FragmentInfoReservasBinding

class InfoReservasFragment : Fragment() {

    private var _binding: FragmentInfoReservasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoReservasBinding.inflate(inflater, container, false)
        val listaReservas = arguments?.getStringArrayList("listaReservas")

        if (listaReservas != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listaReservas)
            binding.lvReservas.adapter = adapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}