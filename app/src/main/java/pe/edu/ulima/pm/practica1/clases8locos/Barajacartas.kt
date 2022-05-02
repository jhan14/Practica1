package pe.edu.ulima.pm.practica1.clases8locos

import android.content.Context
import android.util.AttributeSet

class Barajacartas(context: Context, atrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageView(context, atrs) {

    private val atrs: AttributeSet = atrs;
    private var cartas = mutableListOf<Cartajuego>();
    private val limitePalos: Int = 13;
    private val numeroCartas: Int = 52;
    private val paloscartas = arrayOf("espadas", "corazones", "diamantes", "trebol");
    var cartajuegoCentral: Cartajuego;

    init
    {
        this.crearBaraja();
        this.barajar();
        this.cartajuegoCentral = this.getFirstCarta();
    }

    fun crearBaraja() {
        for (i in paloscartas.indices) {
            for (j in 0 until limitePalos) {
                when (j) {
                    12 -> this.cartas.add(
                        Cartajuego(
                            j + 1,
                            paloscartas[i],
                            Efectocarta.MASTRES,
                            this.context,
                            this.atrs
                        )
                    );
                    10 -> this.cartas.add(
                        Cartajuego(
                            j + 1,
                            paloscartas[i],
                            Efectocarta.SALTO,
                            this.context,
                            this.atrs
                        )
                    );
                    else -> {
                        this.cartas.add(Cartajuego(j + 1, paloscartas[i], null, this.context, this.atrs));
                    }
                }
            }
        }
    }

    fun barajar()
    {
        var posicionAleatorea: Int = 0;
        var c: Cartajuego?;
        for (i in 0 until cartas.size) {
            posicionAleatorea = (0 until numeroCartas).random();
            c = cartas[i];
            cartas[i] = cartas[posicionAleatorea];
            cartas[posicionAleatorea] = c;
        }
    }

    fun addCarta(cartajuego: Cartajuego)
    {
        this.cartas.add(cartajuego);
    }

    fun getFirstCarta(): Cartajuego
    {
        var c: Cartajuego = cartas[0];
        cartas.removeAt(0)
        return c;
    }

    fun getListofCartas(numeroCartas: Int): MutableList<Cartajuego>?
    {
        return if (numeroCartas > this.cartas.size)
        {
            null;
        }
        else
        {
            var mazo = mutableListOf<Cartajuego>();
            for (i in 0 until numeroCartas)
            {
                mazo.add(this.getFirstCarta());
            }
            mazo;
        }
    }

}