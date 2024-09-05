package com.example.fcsocketserver

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.ServerSocket
import java.net.Socket
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        Thread {
            try {
                val socket = Socket("10.0.2.2", 8080)
                val printer = PrintWriter(socket.getOutputStream())
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))


                printer.println("GET / HTTP/1.1")
                printer.println("Host: 127.0.0.1:8080")
                printer.println("User-Agent: android")
                printer.println("\r\n")
                printer.flush()

                var input: String? = "-1"
                while(input != null) {
                    input = reader.readLine()
                    Log.e("Client", input)
                }

                reader.close()
                printer.close()
                socket.close()
            } catch (e: Exception) {
                Log.e("Client", e.toString())
            }

        }.start()


    }
}