package lamarao.jose.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import timber.log.Timber
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //set Timber
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}