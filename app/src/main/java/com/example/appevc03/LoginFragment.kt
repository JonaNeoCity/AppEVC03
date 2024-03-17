package com.example.appevc03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.appevc03.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        //LOGICA DE VALIDACION DE INICIO DE SESION AL HACER CLICK

        binding.btnacceder.setOnClickListener {
            val username = binding.etemail.text.toString()
            val password = binding.etcontraseA.text.toString()



            if (isValidCredentials(username, password)) {
                navigateToNextFragment()
            } else {
                Toast.makeText(requireContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    //VALIDANDO CAMPOS POR EMAIL Y CONTRASEÑA
    private fun isValidCredentials(username: String, password: String): Boolean {

        val validUsername = "jonathan@idat.edu.pe"

        if (username == validUsername) {

            return password == "SYS123"

        } else {
            return false
        }
    }

    //METODO PARA IR AL OTRO FRAGMENTO "FORMULARIO" AL DARLE CLICK AL BOTON ACCEDER
    private fun navigateToNextFragment() {

        findNavController().navigate(R.id.formularioFragment)
    }

}