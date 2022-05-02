package pe.edu.ulima.pm.practica1.clases8locos

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class Jugadorpartida(context: Context, atrs: AttributeSet) : LinearLayout(context, atrs)
{
    lateinit var nombre: String;
    var gano: Boolean = false;
    var cartasjuego = mutableListOf<Cartajuego>();

    fun getCartaSelect(): Cartajuego? {
        var cartajuegoS: Cartajuego? = null;
        for (carta in cartasjuego) {
            if (carta.isSelect) {
                cartajuegoS = carta;
            }
        }
        return cartajuegoS;
    }

    fun removeCarta() {
        for (i in cartasjuego.indices) {
            if (cartasjuego[i].isSelect) {
                cartasjuego.removeAt(i);
                break;
            }
        }
    }

    fun addCarta(cartajuego: Cartajuego) {
        this.cartasjuego.add(cartajuego);
    }

    fun addCartas(cartajuegos: MutableList<Cartajuego>) {
        this.cartasjuego.let { list1 -> cartajuegos.let(list1::addAll) }
    }
}