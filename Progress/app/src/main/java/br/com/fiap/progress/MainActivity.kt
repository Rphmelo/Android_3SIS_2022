package br.com.fiap.progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.progress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoToProgress.setOnClickListener {
            startActivity(ProgressActivity.buildIntent(this))
        }

        binding.buttonGoToFibonacci.setOnClickListener {
            startActivity(FibonacciActivity.buildIntent(this))
        }
    }
}