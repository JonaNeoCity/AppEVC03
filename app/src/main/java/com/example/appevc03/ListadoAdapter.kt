package com.example.appevc03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appevc03.databinding.FragmentListadoBinding

class ListadoAdapter (val listaAmigos: List<Listado>)
    : RecyclerView.Adapter<ListadoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentListadoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentListadoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listaAmigos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listaAmigos[position]){
                binding
            }
        }
    }
}