package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//Puntos: Jorge Miranda (1), Samuel (1), Omar condori(1),Marcos Valeriano(1),Fabricio(1), Yesenia(1), Cardesnas(1/2), Coila(1), mamani callata(1), Ciro Mamani(1)
//Mery(1), Luis Fernando(1), Quispe Huarilloclla(1), Francklin(1), ()
class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado:EditText
    private var valAnt=0.0
    private var valAct=0.0
    private var operador=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado=findViewById(R.id.txtResult)
        botones();

    }

    fun botones(){
        var buttons= arrayOf(R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnMult, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btnSum, R.id.btnIgual)
            for (button in buttons){
                var btn=findViewById<Button>(button)
                btn.setOnClickListener { onClikListener(btn) }
            }
    }

    fun onClikListener(view:View){
        var botonX=findViewById<Button>(view.id)
        when(view.id){
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9->{
                appentContent(botonX.text.toString()) }
            R.id.btnMult, R.id.btnSum->{
                setOperador(botonX.text.toString())
            }
            R.id.btnIgual->{
                operacion()
            }

        }
    }

    fun appentContent(valor:String){
        txtResultado.append(valor)
    }

    fun setOperador(oper:String){
        operador=oper
        valAnt=txtResultado.text.toString().toDouble()
        txtResultado.text.clear()
    }

    fun operacion(){
        valAct=txtResultado.text.toString().toDouble()
        var resultx=when(operador){
            "*"->valAnt*valAct
            "+"->valAnt+valAct
            else->valAct
        }
        txtResultado.setText(resultx.toString())
        operador=""
    }

}