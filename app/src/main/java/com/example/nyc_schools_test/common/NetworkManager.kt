package com.example.nyc_schools_test.common

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class InternetCheck() {

    fun isConnected(): Boolean {
        return try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}