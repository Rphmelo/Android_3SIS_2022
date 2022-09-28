package br.com.fiap.progress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.fiap.progress.databinding.ActivityFibonacciBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FibonacciActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFibonacciBinding
    private var coroutineJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFibonacciBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        var countClick = binding.labelFibonacciSequenceNumber.text.toString().toInt()
        binding.buttonIncrementFibonacci.setOnClickListener {
            countClick++
            incrementFibonacci(countClick)
            binding.labelFibonacciSequenceNumber.text = "Sequencia de fibonacci: $countClick"
        }

        binding.buttonStopFibonacci.setOnClickListener {
            stopFibonacci()
        }
    }

    private fun incrementFibonacci(fibonacciSequenceNumber: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            binding.buttonIncrementFibonacci.isEnabled = false
            binding.labelFibonacciNumber.text = "Valor do fibonacci: ${CoroutineFactory.calculateFibonacciSuspend(fibonacciSequenceNumber)}"
            binding.buttonIncrementFibonacci.isEnabled = true
        }
    }

    private fun stopFibonacci() {
        coroutineJob?.cancel()
    }

    companion object {
        fun buildIntent(context: Context) = Intent(
            context,
            FibonacciActivity::class.java
        )
    }
}