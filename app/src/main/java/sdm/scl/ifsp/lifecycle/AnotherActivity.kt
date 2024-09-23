package sdm.scl.ifsp.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import sdm.scl.ifsp.lifecycle.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {
    private val activityAnotherBinding:ActivityAnotherBinding by lazy {
        ActivityAnotherBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityAnotherBinding.root)

        // Configura a Toolbar como a ActionBar
        setSupportActionBar(activityAnotherBinding.toolbarIn.toolbar)
        // Define o subtítulo da ActionBar
        supportActionBar?.subtitle = "AnotherActivity"

        Log.v(getString(R.string.app_name), "Another - onCreate():inicio COMPLETO")
    }
    //Metodos do ciclo de vida seguindo na ordem onCreate , onStart ... completa dos ciclos
    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "Another - onStart():inicio VISIVEL")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "Another - onResume():inicio PRIMEIRO PLANO")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "Another - onPause():fim PRIMEIRO PLANO")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "Another - onStop():fim VISIVEL")
    }
    //o ultimo ciclo
    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "Another - onDestooy():fim COMPLETO")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "Another - onRestart():preparando onStart")
    }
}