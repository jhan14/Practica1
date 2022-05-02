package pe.edu.ulima.pm.practica1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.practica1.clases8locos.Barajacartas
import pe.edu.ulima.pm.practica1.clases8locos.Cartajuego
import pe.edu.ulima.pm.practica1.clases8locos.Efectocarta
import pe.edu.ulima.pm.practica1.clases8locos.Jugadorpartida

class GameActivity : AppCompatActivity()
{
    var rondaJuego: Int = 1;
    var turnojugador: Int = 0;
    lateinit var barajacartas: Barajacartas;
    var jugadores: MutableList<Jugadorpartida> = mutableListOf();


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        inicioJuego();

        findViewById<Barajacartas>(R.id.idBarajaCartas).setOnClickListener { v: View ->
            robarCarta();
            setManoJugadores();
            setManoOnClick();
        }

        findViewById<Button>(R.id.butPasar).setOnClickListener { v: View ->
            pasarJugador();
            setManoJugadores();
            setManoOnClick();
        }

        findViewById<Button>(R.id.butCancelar).setOnClickListener { v: View ->
            setResult(RESULT_CANCELED)
            finish()
        }

        findViewById<Button>(R.id.butPasar).setEnabled(false);
    }

    fun inicioJuego() {
        barajacartas = findViewById(R.id.idBarajaCartas);

        var jugador1 = findViewById<Jugadorpartida>(R.id.Jugador1);
        jugador1.nombre = "Jhan";
        var jugador2 = findViewById<Jugadorpartida>(R.id.Jugador2);
        jugador2.nombre = "Boot 1";
        var jugador3 = findViewById<Jugadorpartida>(R.id.Jugador3);
        jugador3.nombre = "Boot 2";
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);
        repartirCartas();
        setManoJugadores();
        SetCartaCentral();

        if (barajacartas.cartajuegoCentral.isEspecial())
        {
            efectoCarta(true);
        }
        setManoOnClick();
    }

    fun SetCartaCentral() {
        var lineaC = findViewById<LinearLayout>(R.id.linearCartaCentro);
        barajacartas.cartajuegoCentral.setSize(500, 500)
        lineaC.removeAllViews();
        lineaC.addView(barajacartas.cartajuegoCentral);
    }

    fun setManoOnClick() {
        for (jugador in jugadores)
        {
            for (carta in jugador.cartasjuego)
            {
                setOnclickCarta(carta);
            }
        }
    }

    fun setOnclickCarta(Cartajuego: Cartajuego)
    {
        Cartajuego.setOnClickListener { v: View ->
            Cartajuego.isSelect = true;
            cartasCompatibles();
        }
    }

    fun setManoJugadores()
    {
        for (jugador in jugadores)
        {
            jugador.removeAllViews();
            for (carta in jugador.cartasjuego)
            {
                jugador.addView(carta);
            }
        }
    }

    fun repartirCartas()
    {
        for (jugador in jugadores)
        {
            barajacartas.getListofCartas(8)?.let { jugador.addCartas(it) };
        }
    }

    fun jugadorActual(): Jugadorpartida
    {
        return jugadores[turnojugador];
    }

    fun getCartasJugadorActual(): MutableList<Cartajuego>
    {
        return jugadorActual().cartasjuego;
    }

    fun numCartasJugadorActual(): Int
    {
        return jugadorActual().cartasjuego.size;
    }

    fun siguienteJugador(): Jugadorpartida
    {
        return if (turnojugador == jugadores.size - 1)
        {
            jugadores[0];
        }

        else
        {
            jugadores[turnojugador + 1];
        }
    }

    fun cargarCartasJugador()
    {
        for (jugador in jugadores)
        {
            jugador.visibility = View.GONE;
        }

        jugadorActual().visibility = View.VISIBLE;
        findViewById<TextView>(R.id.txtJugador).text = jugadorActual().nombre;

    }

    fun pasarJugador()
    {
        cambioTurno();
        cargarCartasJugador();
        findViewById<Button>(R.id.butPasar).setEnabled(false);
    }

    fun cambioTurno()
    {
        if (turnojugador == jugadores.size - 1)
        {
            turnojugador = 0;
        }

        else
        {
            turnojugador++;
        }
    }

    fun efectoCarta(inicio: Boolean)
    {
        var cartajuegoC: Cartajuego = barajacartas.cartajuegoCentral;
        var sigJugador: Jugadorpartida;
        var cartajuegos: MutableList<Cartajuego>;
        if (cartajuegoC.efecto === Efectocarta.MASTRES)
        {
            sigJugador = if (inicio) jugadorActual() else siguienteJugador();
            cartajuegos = barajacartas.getListofCartas(3)!!;
            sigJugador.addCartas(cartajuegos);
            setManoOnClick();
        }

        else
        {
            cambioTurno();
        }
    }

    fun cartasCompatibles()
    {
        if (iscartasCompatibles())
        {
            setManoJugadores();
            SetCartaCentral();
            pasarJugador();
        }

        else
        {
            Toast.makeText(this, "Cartas no compatibles", Toast.LENGTH_LONG).show()
        }

    }

    fun iscartasCompatibles(): Boolean
    {
        var cartajuegoJ: Cartajuego = jugadorActual().getCartaSelect()!!;
        var cartajuegoC: Cartajuego = barajacartas.cartajuegoCentral;
        return if (cartajuegoJ.compatible(cartajuegoC))
        {
            if (jugadorActual().cartasjuego.size - 1 === 1)
            {
                Toast.makeText(this, "El jugador: " + jugadorActual().nombre + " tiene una carta",
                    Toast.LENGTH_LONG
                ).show()
            }

            else
            {
                if (jugadorActual().cartasjuego.size - 1 == 0)
                {
                    jugadorActual().gano = true;
                    Toast.makeText(
                        this,
                        "El ganador es:  " + jugadorActual().nombre,
                        Toast.LENGTH_LONG
                    ).show()
                    setResult(RESULT_OK)
                    finish()
                }
            }

            jugadorActual().removeCarta();
            cartajuegoJ.isSelect = false;
            barajacartas.cartajuegoCentral = cartajuegoJ;
            barajacartas.addCarta(cartajuegoC);

            if (cartajuegoJ.isEspecial())
            {
                efectoCarta(false);
            }
            true;
        }

        else
        {
            cartajuegoJ.isSelect = false;
            false;
        }
    }

    fun robarCarta()
    {
        var cartajuego: Cartajuego = barajacartas.getFirstCarta();
        jugadorActual().addCarta(cartajuego);
        findViewById<Button>(R.id.butPasar).setEnabled(true);
    }

}