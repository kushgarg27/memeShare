package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.memeshare.databinding.ActivityMainBinding

private lateinit var Binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        loadmeme()

        Binding.button.setOnClickListener()
        {
             val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Hey checkout this cool meme from memeshare:$currentImageUrl")
            val chooser= Intent.createChooser(intent, "share this meme link using :")
            startActivity(chooser)

        }
    }




     private fun loadmeme(){
        Binding.progressBar.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this)
        val url = " https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                currentImageUrl = response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                        Binding.progressBar.visibility = View.GONE
                        return false

                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Binding.progressBar.visibility = View.GONE
                        return false

                        TODO("Not yet implemented")
                    }
                }).into(Binding.imageView)
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"something went wromg",Toast.LENGTH_LONG).show()
                // TODO: Handle error
            })

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)

    }

    fun nextmeme(view: View) {
        loadmeme()
    }
}