package com.example.randompet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var petImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        val button = findViewById<Button>(R.id.petButton)
        button.setOnClickListener{
            getDogImageURL()

            Glide.with(this)
                .load(petImageURL)
                .fitCenter()
                .into(imageView)
        }
        getNextImage(button,imageView)
    }
    private fun getDogImageURL(){
        val client = AsyncHttpClient()
        client["https://dog.ceo/api/breeds/image/random", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog", "response successful$json")
                petImageURL = json.jsonObject.getString("message")
                Log.d("petImageURL", "pet image URL set")

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]

    }
    private fun getNextImage(button: Button, imageView: ImageView) {

    }
}