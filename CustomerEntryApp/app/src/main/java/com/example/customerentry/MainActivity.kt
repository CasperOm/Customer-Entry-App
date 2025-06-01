package com.example.customerentry

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private val serverUrl = "http://192.168.1.111:5000/fill" // Change to your desktop IP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameField = findViewById<EditText>(R.id.nameField)
        val phoneField = findViewById<EditText>(R.id.phoneField)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val name = nameField.text.toString()
            val phone = phoneField.text.toString()

            if (name.isBlank() || phone.isBlank()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val json = """
                {
                    "name": "$name",
                    "phone": "$phone"
                }
            """.trimIndent()

            val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
            val request = Request.Builder().url(serverUrl).post(body).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Failed to connect", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Data sent", Toast.LENGTH_SHORT).show()
                        nameField.text.clear()
                        phoneField.text.clear()
                    }
                }
            })
        }
    }
}