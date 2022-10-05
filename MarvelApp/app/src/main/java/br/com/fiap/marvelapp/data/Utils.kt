package br.com.fiap.marvelapp.data

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    fun md5(input: String) = hashString("MD5", input)

    private fun hashString(type: String, input: String): String {
        val bytes = MessageDigest
            .getInstance(type)
        return BigInteger(1, bytes.digest(input.toByteArray())).toString(16)
    }
}