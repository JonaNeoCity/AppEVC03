package com.example.appevc03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.appevc03.databinding.FragmentFormularioBinding
import com.example.appevc03.mensajesform.AppMensaje
import com.example.appevc03.mensajesform.TipoMensaje

class FormularioFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFormularioBinding? = null
    private val binding get() = _binding!!
    private var tiposervicio = ""
    private var cantidadperro = ""
    private var listaReservas = ArrayList<String>()
    private val appMensaje = AppMensaje()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)
        binding.btnReservar.setOnClickListener(this)
        ArrayAdapter.createFromResource(requireContext(), R.array.tipo_servicio, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spServicio.adapter = adapter }
        ArrayAdapter.createFromResource(requireContext(), R.array.cantidad_perro, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spCantidad.adapter = adapter }
        binding.spServicio.onItemSelectedListener = this
        binding.spCantidad.onItemSelectedListener = this
        return binding.root
    }

    override fun onClick(v: View?) {
        binding.btnReservar.setOnClickListener {
            dirigirAInfoReservasFragment()
        }
    }

    private fun dirigirAInfoReservasFragment() {
        findNavController().navigate(R.id.infoReservasFragment)
    }

    fun registrarReserva() {
        if(validarFormulario()) {
            val infoReserva = tiposervicio + " " +
                    cantidadperro + " " +
                    binding.etdFechaIngreso.text.toString() + " " +
                    binding.etdFechaSalida.text.toString()
            listaReservas.add(infoReserva)
            appMensaje.enviarMensaje(binding.root, "Reserva Registrada Correctamente", TipoMensaje.SUCCESFULL)
            setearControles()
        }
    }

    private fun setearControles() {
        listaReservas.clear()
        binding.spServicio.setSelection(0)
        binding.spCantidad.setSelection(0)
        binding.etdFechaIngreso.setText("")
        binding.etdFechaSalida.setText("")
        binding.spServicio.isFocusableInTouchMode = true
        binding.spServicio.requestFocus()
    }

    fun validarFormulario(): Boolean {
        var respuesta = false
        if(!validarTipoServicio()) {
            appMensaje.enviarMensaje(binding.root, "Seleccione un servicio", TipoMensaje.ERROR)
        } else if (!validarCantidadPerro()) {
            appMensaje.enviarMensaje(binding.root, "Seleccione una cantidad", TipoMensaje.ERROR)
        } else if (!validarFechadeIngreso()) {
            appMensaje.enviarMensaje(binding.root, "Ingrese una fecha de ingreso", TipoMensaje.ERROR)
        } else if (!validarFechadeSalida()) {
            appMensaje.enviarMensaje(binding.root, "Ingrese una fecha de ingreso", TipoMensaje.ERROR)
        } else {
            respuesta = true
        }
        return respuesta
    }

    fun validarFechadeIngreso() : Boolean {
        var respuesta = true
        if (binding.etdFechaIngreso.text.toString().trim().isEmpty()) {
            binding.etdFechaIngreso.isFocusableInTouchMode = true
            binding.etdFechaIngreso.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun validarFechadeSalida() : Boolean {
        var respuesta = true
        if(binding.etdFechaSalida.text.toString().trim().isEmpty()) {
            binding.etdFechaSalida.isFocusableInTouchMode = true
            binding.etdFechaSalida.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun validarTipoServicio(): Boolean {
        return tiposervicio != ""
    }

    fun validarCantidadPerro(): Boolean {
        return cantidadperro != ""
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        tiposervicio = if(position > 0) {
            parent!!.getItemAtPosition(position).toString()
        } else ""
        cantidadperro = if(position > 0) {
            parent!!.getItemAtPosition(position).toString()
        } else ""
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}