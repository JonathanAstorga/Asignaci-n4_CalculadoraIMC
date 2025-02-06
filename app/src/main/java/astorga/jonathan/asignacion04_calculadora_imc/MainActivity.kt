package astorga.jonathan.asignacion04_calculadora_imc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    var peso: Double = 0.0
    var estatura: Double = 0.0
    var resultado: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resul: TextView = findViewById(R.id.tvResultado)
        val btnCalcular: Button = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar : Button = findViewById(R.id.btnLimpiar)
        val edtPeso: EditText = findViewById(R.id.edtPeso)
        val edtEstatura: EditText = findViewById(R.id.edtEstatura)


        btnCalcular.setOnClickListener{
            var bandera: Boolean = sacarPeso_Y_Estatura()
            if (bandera == true){
                var resultadoNumero: Double = calcularIMC(peso, estatura)
                btnCalcular.visibility = View.INVISIBLE
                btnLimpiar.visibility = View.VISIBLE
                resul.setText(resultadoNumero.toString())
            }
            else{
                resul.setText(resultado)
            }
            reiniciarValores()
        }

        btnLimpiar.setOnClickListener{
            reiniciarValores()
            resul.setText("Click en Calcular IMC para tu IMC")
            edtPeso.setText("")
            edtEstatura.setText("")
            btnLimpiar.visibility = View.INVISIBLE
            btnCalcular.visibility = View.VISIBLE

        }

    }

    fun sacarPeso_Y_Estatura(): Boolean {
        var edtPeso = findViewById<EditText>(R.id.edtPeso).text.toString()
        var edtEstatura = findViewById<EditText>(R.id.edtEstatura).text.toString()

        if (edtPeso.isBlank() || edtEstatura.isBlank()){
            resultado = "Ingresa los datos requeridos..."
        }
        else{
            peso = edtPeso.toDouble()
            estatura = edtEstatura.toDouble()
            println(peso.toString())
            println(estatura.toString())
            if (verificaValores(peso, estatura) == true){
                return true
            }
        }
        return false
    }

    fun calcularIMC(peso: Double, estatura: Double): Double {
        var estaturaCuadrado = Math.pow(estatura, 2.0)
        var resultadoNum = peso/estaturaCuadrado
        return resultadoNum
    }

    fun verificaValores(peso: Double, estatura: Double): Boolean {
        if(peso > 0 || estatura > 0){
            return true
        }
        else{
            reiniciarValores()
            resultado = "Los campos deben de ser mayores a 0"
        }
        return false
    }

    fun reiniciarValores(){
        peso = 0.0
        estatura = 0.0
    }
}