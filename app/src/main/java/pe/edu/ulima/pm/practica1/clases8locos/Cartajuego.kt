package pe.edu.ulima.pm.practica1.clases8locos

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class Cartajuego (var numero: Int, var palo: String, var efecto: Efectocarta?, context: Context,
                  atrs: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, atrs)
{
    var isSelect: Boolean = false;
    init
    {
        this.setImg(this.numero, this.palo);
        this.setSize(200,200);
    }

    fun setSize(w: Int, h: Int)
    {
        this.layoutParams = ViewGroup.LayoutParams(w, h);
    }

    fun setImg(numero: Int, palo: String)
    {
        palo + "_" + numero
    }

    fun isEspecial(): Boolean
    {
        return this.efecto != null;
    }

    fun compatible(cartajuego: Cartajuego): Boolean
    {
        return this.palo == cartajuego.palo
                || (this.numero == cartajuego.numero && !this.isEspecial() && !cartajuego.isEspecial())
                || (this.isEspecial() && cartajuego.isEspecial() && this.efecto == cartajuego.efecto);
    }
}