package pe.edu.ulima.pm.practica1.clases8locos

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import pe.edu.ulima.pm.practica1.R

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
        when (palo + "_" + numero) {
            "corazones_1" -> setImageResource(R.drawable.card314);
            "corazones_2" -> setImageResource(R.drawable.card302);
            "corazones_3" -> setImageResource(R.drawable.card303);
            "corazones_4" -> setImageResource(R.drawable.card304);
            "corazones_5" -> setImageResource(R.drawable.card305);
            "corazones_6" -> setImageResource(R.drawable.card306);
            "corazones_7" -> setImageResource(R.drawable.card307);
            "corazones_8" -> setImageResource(R.drawable.card308);
            "corazones_9" -> setImageResource(R.drawable.card309);
            "corazones_10" -> setImageResource(R.drawable.card310);
            "corazones_11" -> setImageResource(R.drawable.card311);
            "corazones_12" -> setImageResource(R.drawable.card312);
            "corazones_13" -> setImageResource(R.drawable.card313);
            "diamantes_1" -> setImageResource(R.drawable.card114);
            "diamantes_2" -> setImageResource(R.drawable.card102);
            "diamantes_3" -> setImageResource(R.drawable.card103);
            "diamantes_4" -> setImageResource(R.drawable.card104);
            "diamantes_5" -> setImageResource(R.drawable.card105);
            "diamantes_6" -> setImageResource(R.drawable.card106);
            "diamantes_7" -> setImageResource(R.drawable.card107);
            "diamantes_8" -> setImageResource(R.drawable.card108);
            "diamantes_9" -> setImageResource(R.drawable.card109);
            "diamantes_10" -> setImageResource(R.drawable.card110);
            "diamantes_11" -> setImageResource(R.drawable.card111);
            "diamantes_12" -> setImageResource(R.drawable.card112);
            "diamantes_13" -> setImageResource(R.drawable.card113);
            "trebol_1" -> setImageResource(R.drawable.card214);
            "trebol_2" -> setImageResource(R.drawable.card202);
            "trebol_3" -> setImageResource(R.drawable.card203);
            "trebol_4" -> setImageResource(R.drawable.card204);
            "trebol_5" -> setImageResource(R.drawable.card205);
            "trebol_6" -> setImageResource(R.drawable.card206);
            "trebol_7" -> setImageResource(R.drawable.card207);
            "trebol_8" -> setImageResource(R.drawable.card208);
            "trebol_9" -> setImageResource(R.drawable.card209);
            "trebol_10" -> setImageResource(R.drawable.card210);
            "trebol_11" -> setImageResource(R.drawable.card211);
            "trebol_12" -> setImageResource(R.drawable.card212);
            "trebol_13" -> setImageResource(R.drawable.card213);
            "espadas_1" -> setImageResource(R.drawable.card414);
            "espadas_2" -> setImageResource(R.drawable.card402);
            "espadas_3" -> setImageResource(R.drawable.card403);
            "espadas_4" -> setImageResource(R.drawable.card404);
            "espadas_5" -> setImageResource(R.drawable.card405);
            "espadas_6" -> setImageResource(R.drawable.card406);
            "espadas_7" -> setImageResource(R.drawable.card407);
            "espadas_8" -> setImageResource(R.drawable.card408);
            "espadas_9" -> setImageResource(R.drawable.card409);
            "espadas_10" -> setImageResource(R.drawable.card410);
            "espadas_11" -> setImageResource(R.drawable.card411);
            "espadas_12" -> setImageResource(R.drawable.card412);
            "espadas_13" -> setImageResource(R.drawable.card413);
        }
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