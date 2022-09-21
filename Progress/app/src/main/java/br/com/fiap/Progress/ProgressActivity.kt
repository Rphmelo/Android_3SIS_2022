package br.com.fiap.progress

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import br.com.fiap.progress.databinding.ActivityProgressBinding
import kotlinx.coroutines.*

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding
    private var launch: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        with(binding) {
            buttonStartProgress.setOnClickListener {
                startProgress()
            }

            buttonStopProgress.setOnClickListener {
                launch?.cancel()
            }
        }
    }

    private fun startProgress() {
        var currentProgress = 0
        var countDelayTotal = 0L
        var countDelay: Long

        launch = lifecycleScope.launch(Dispatchers.Main) {
            while(currentProgress < 100) {
                currentProgress += CoroutineFactory.getRandomNumbers()
                countDelay = CoroutineFactory.getRandomDelays()
                countDelayTotal += countDelay
                Log.d("Finalizou o ASYNC", "Finalizou o ASYNC")
                binding.launchProgressBar.progress = currentProgress
                binding.labelPercentage.text = "$currentProgress%"
                if(currentProgress < 100) {
                    delay(countDelay)
                    Log.d("Finalizou o DELAY", "Finalizou o DELAY")
                }
            }
            binding.labelPercentage.text = "Finalizado em ${countDelayTotal / 1000} segundos."
        }

        Log.d("Fora do while", "Fora do while")
    }

    companion object {
        fun buildIntent(context: Context) = Intent(
            context,
            ProgressActivity::class.java
        )
    }
}