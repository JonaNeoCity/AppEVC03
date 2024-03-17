package com.example.appevc03.mensajesform

import android.view.View
import androidx.core.content.ContextCompat
import com.example.appevc03.R
import com.google.android.material.snackbar.Snackbar

class AppMensaje {
    fun enviarMensaje(vista: View, mensaje: String, tipoMensaje: TipoMensaje) {
        val snackBar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        if(tipoMensaje == TipoMensaje.ERROR) {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(
                    MiApp.instance,
                    R.color.errorColor
                )
            )
        } else if (tipoMensaje == TipoMensaje.SUCCESFULL) {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(
                    MiApp.instance,
                    R.color.exitoColor
                )
            )
        } else if (tipoMensaje == TipoMensaje.INFO) {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(
                    MiApp.instance,
                    R.color.infoColor
                )
            )
        } else {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(
                    MiApp.instance,
                    R.color.advertenciaColor
                )
            )
        }
        snackBar.show()
    }
}