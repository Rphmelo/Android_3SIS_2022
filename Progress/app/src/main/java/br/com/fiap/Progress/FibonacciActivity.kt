package br.com.fiap.progress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.com.fiap.progress.databinding.ActivityFibonacciBinding
import br.com.fiap.progress.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FibonacciActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFibonacciBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFibonacciBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        binding.buttonShowToast.setOnClickListener {
            Toast.makeText(
                this,
                "Main Thread não está bloqueada!!! Uhuuul!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.buttonStartFibonacci.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                CoroutineFactory.fibonacci(100000)
            }
        }
    }

    companion object {
        fun buildIntent(context: Context) = Intent(
            context,
            FibonacciActivity::class.java
        )
    }
}