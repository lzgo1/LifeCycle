package sdm.scl.ifsp.lifecycle

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import sdm.scl.ifsp.lifecycle.databinding.ActivityMainBinding
import sdm.scl.ifsp.lifecycle.databinding.TilePhoneBinding
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //conta quantas x foi alterado
    private var filledChar: Int = 0

    //nao teve erro no Thread por isso nao coloquei a segunda opçao
    //FALTA IMPLEMENTAR VIDEO 55:40MIN...

    //PARA SALVAR No BUNDLE E RESTAURAR
    companion object {
        const val FILLED_CHARS = "FILLED_CHARS"
        const val PHONES = "PHONES"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // Configura a Toolbar como a ActionBar fora do apply
        setSupportActionBar(activityMainBinding.toolbarIn.toolbar)
        // Define o subtítulo da ActionBar
        supportActionBar?.subtitle = "MainActivity"

        // Configura o botão fora do bloco apply
        activityMainBinding.addPhoneBt.setOnClickListener {
            val tilePhoneBinding = TilePhoneBinding.inflate(layoutInflater)
            activityMainBinding.phonesLl.addView(tilePhoneBinding.root)
        }

        // Atualiza filledChars e exibe o texto atualizado
        activityMainBinding.nameEt.doAfterTextChanged {
            filledChar = it?.length ?: 0
            activityMainBinding.filledCharsTv.text =
                "${getString(R.string.filled_chars)} $filledChar"
        }
        // Configura o botão openAnotherActivityBt fora do bloco apply
        activityMainBinding.openAnotherActivityBt.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnotherActivity::class.java))
        }

        //abaixo versao da toolbar com apply (so assim funciona o assPhoneBt)
        /*activityMainBinding.apply {
            setSupportActionBar(toolbarIn.toolbar)

            nameEt.doAfterTextChanged{
            "${getString(R.string.filled_chars)} ${++filledChars}".also {
            filledCharsTv.text=it}
            }

            addPhoneBt.setOnClickListener{
                val tilePhoneBinding = TilePhoneBinding.inflate(layoutInflater)
                phonesLl.addView(tilePhoneBinding.root)
            }
            openAnotherActivityBt.setOnClickListener {
            startActivity(Intent(this@MainActivity, AnotherActivity::class.java))
        }
        }
        supportActionBar?.subtitle=getString(R.string.main)*/
        //logcat
        Log.v(getString(R.string.app_name), "Main - onCreate():inicio COMPLETO")

        //nao teve erro em nenhuma opçao abaixo conforme o video
        Thread{
            sleep(3000)
            runOnUiThread {  activityMainBinding.nameEt.setText("S D M ")}
            //activityMainBinding.nameEt.setText("S D M ")
        }.start()
    }

    //criada para o companion object para salvar e resgatar
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(FILLED_CHARS, filledChar)

        val phones = mutableListOf<String>()
        activityMainBinding.phonesLl.children.forEachIndexed { index, view ->
            if (index != 0) {
                (view as EditText).text.toString().let {
                    phones.add(it)
                }
            }
        }
        outState.putStringArray(PHONES, phones.toTypedArray())
    }

    //resgatar
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt(FILLED_CHARS, 0).let {
            filledChar = it
            "${getString(R.string.filled_chars)} $filledChar".also { fc ->
                activityMainBinding.filledCharsTv.text = fc
            }
        }
        savedInstanceState.getStringArray(PHONES)?.forEach { phone ->
            TilePhoneBinding.inflate(layoutInflater).apply {
                root.setText(phone)
                activityMainBinding.phonesLl.addView(root)
            }
        }
    }


    //Metodos do ciclo de vida seguindo na ordem onCreate , onStart ... completa dos ciclos
    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "Main - onStart():inicio VISIVEL")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "Main - onResume():inicio PRIMEIRO PLANO")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "Main - onPause():fim PRIMEIRO PLANO")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "Main - onStop():fim VISIVEL")
    }

    //o ultimo ciclo
    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "Main - onDestooy():fim COMPLETO")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "Main - onRestart():preparando onStart")
    }
}

