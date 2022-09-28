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
                stopProgress()
            }
        }
    }

    private fun startProgress() {
        var currentProgress = 0
        var countDelay = 0L
        var countDelayTotal = 0L

        launch = lifecycleScope.launch(Dispatchers.Main) {
            while(currentProgress < 100) {
                currentProgress += CoroutineFactory.getRandomNumbers()
                countDelay = CoroutineFactory.getRandomDelays()
                Log.d("RANDOM FINISH", "RANDOM FINISH")
                countDelayTotal += countDelay
                binding.launchProgressBar.progress = currentProgress
                binding.labelPercentage.text = "$currentProgress%"
                if(currentProgress < 100) {
                    delay(countDelay)
                    Log.d("APOS DELAY", "APOS DELAY")
                }
            }
            Log.d("APOS WHILE", "APOS WHILE")
            binding.labelPercentage.text = "Finalizado em ${countDelayTotal / 1000} segundos"
        }
    }

    private fun stopProgress() {
        launch?.cancel()
    }

    companion object {
        fun buildIntent(context: Context) = Intent(
            context,
            ProgressActivity::class.java
        )
    }
}