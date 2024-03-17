package com.example.appevc03

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.appevc03.databinding.FragmentFormularioBinding
import com.example.appevc03.mensajesform.AppMensaje
import com.example.appevc03.mensajesform.TipoMensaje
import java.text.SimpleDateFormat
import java.util.*

class FormularioFragment : Fragment(), AdapterView.OnItemSelectedListener {

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
        ArrayAdapter.createFromResource(requireContext(), R.array.tipo_servicio, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spServicio.adapter = adapter }
        ArrayAdapter.createFromResource(requireContext(), R.array.cantidad_perro, android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spCantidad.adapter = adapter }
        binding.spServicio.onItemSelectedListener = this
        binding.spCantidad.onItemSelectedListener = this

        binding.etdFechaIngreso.setOnClickListener {
            mostrarDatePicker(binding.etdFechaIngreso)
        }

        binding.etdFechaSalida.setOnClickListener {
            mostrarDatePicker(binding.etdFechaSalida)
        }

        binding.btnReservar.setOnClickListener {
            if (validarFormulario()) {
                registrarReserva()
                dirigirAInfoReservasFragment()
            }
        }
        return binding.root
    }

    private fun dirigirAInfoReservasFragment() {
        val bundle = Bundle().apply {
            putStringArrayList("listaReservas", listaReservas)
        }
        findNavController().navigate(R.id.infoReservasFragment, bundle)
    }

    fun registrarReserva() {
        if(validarFormulario()) {
            val infoReserva = "Servicio: " + tiposervicio + "\nCantidad: " +
                    cantidadperro + "\nFecha de Ingreso: " +
                    binding.etdFechaIngreso.text.toString() + "\nFecha de Salida: " +
                    binding.etdFechaSalida.text.toString()
            listaReservas.add(infoReserva)
            appMensaje.enviarMensaje(binding.root, "Reserva Registrada Correctamente", TipoMensaje.SUCCESFULL)
            setearControles()
        }
    }

    private fun setearControles() {
        binding.spServicio.setSelection(0)
        binding.spCantidad.setSelection(0)
        binding.etdFechaIngreso.text.clear()
        binding.etdFechaSalida.text.clear()
        binding.spServicio.isFocusableInTouchMode = true
        binding.spServicio.requestFocus()
    }

    fun validarFormulario(): Boolean {
        var respuesta = true
        if (!validarTipoServicio()) {
            AppMensaje().enviarMensaje(binding.root, "Seleccione un servicio", TipoMensaje.ERROR)
            respuesta = false
        }
        else if (!validarCantidadPerro()) {
            AppMensaje().enviarMensaje(binding.root, "Seleccione una cantidad", TipoMensaje.ERROR)
            respuesta = false
        }
        else if (!validarFechas()) {
            respuesta = false
        }
        return respuesta
    }

    private fun validarFechas(): Boolean {
        val fechaIngreso = binding.etdFechaIngreso.text.toString()
        val fechaSalida = binding.etdFechaSalida.text.toString()

        // Verificar si ambas fechas están vacías
        return if (fechaIngreso.isEmpty()) {
            // Solo fecha de ingreso vacía
            AppMensaje().enviarMensaje(binding.root, "Ingrese una fecha de ingreso", TipoMensaje.ERROR)
            false
        } else if (fechaSalida.isEmpty()) {
            // Solo fecha de salida vacía
            AppMensaje().enviarMensaje(binding.root, "Ingrese una fecha de salida", TipoMensaje.ERROR)
            false
        } else {
            // Ambas fechas están ingresadas
            true
        }
    }

    private fun mostrarDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            editText.setText(dateFormat.format(selectedDate.time))
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    fun validarTipoServicio(): Boolean {
        tiposervicio = if (binding.spServicio.selectedItemPosition > 0) {
            binding.spServicio.selectedItem.toString()
        } else ""
        return tiposervicio.isNotEmpty()
    }

    fun validarCantidadPerro(): Boolean {
        cantidadperro = if (binding.spCantidad.selectedItemPosition > 0) {
            binding.spCantidad.selectedItem.toString()
        } else ""
        return cantidadperro.isNotEmpty()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}