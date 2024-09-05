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
import java.net.ServerSocket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Thread {
            val port = 8080
            val server = ServerSocket(port)

            val socket = server.accept()

            Log.d("Server", "IP ${socket.localSocketAddress}")

            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val printer = PrintWriter(socket.getOutputStream())

            var input: String? = "-1"
            while (input != null && input != "") {
                input = reader.readLine()
            }

            Log.e("SERVER", "READ DATA $input")

            printer.println("HTTP/1.1 200 OK")
            printer.println("Content-Type: text/html\r\n")

            printer.println("<h1>Hello World</h1>")
            printer.println("\r\n")
            printer.flush()
            printer.close()

            reader.close()
            socket.close()
        }.start()



    }
}