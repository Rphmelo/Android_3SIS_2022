package br.com.fiap.progress

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

object CoroutineFactory {
    private val listNumbers: List<Int> = listOf(1, 1, 2, 3, 5, 8, 13, 21)
    private val listDelays: List<Long> = listOf(2000L, 3000L, 1000L, 800L)

    suspend fun getRandomNumbers(): Int {
        return withContext(Dispatchers.Default) {
            this.async {
                listNumbers[(listNumbers.indices).random()].also {
                    Log.d("RANDOM NUMBER", it.toString())
                }

            }
        }.await()
    }

    suspend fun getRandomDelays(): Long {
        return withContext(Dispatchers.Default) {
            this.async {
                listDelays[(listDelays.indices).random()].also {
                    Log.d("RANDOM delay", it.toString())
                }
            }
        }.await()
    }

    suspend fun calculateFibonacciSuspend(x: Int): Int {
        return withContext(Dispatchers.Main) {
            this.async {
                calculateFibonacci(x)
            }
        }.await()
    }

    private fun calculateFibonacci(x: Int): Int =
        if (x <= 1) x else calculateFibonacci(x - 1) + calculateFibonacci(x - 2)

}