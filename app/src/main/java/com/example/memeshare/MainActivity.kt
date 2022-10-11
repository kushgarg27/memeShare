package com.example.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeshare.databinding.ActivityMainBinding

private lateinit var Binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        val queue = Volley.newRequestQueue(this)
        val url = " https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val url = response.getString("url")
                Glide.with(this).load(url).into(Binding.imageView)
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"something went wromg",Toast.LENGTH_LONG).show()
                // TODO: Handle error
            })

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)

    }
}