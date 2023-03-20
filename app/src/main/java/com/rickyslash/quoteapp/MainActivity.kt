package com.rickyslash.quoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.rickyslash.quoteapp.databinding.ActivityMainBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRandomQuote()

        // add 'onClickListener' to move to 'ListQuotesActivity'
        binding.btnAllQuotes.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListQuotesActivity::class.java))
        }
    }

    private fun getRandomQuote() {
        binding.progressBar.visibility = View.VISIBLE // 'progressBar' 'visible' on the 'first call'
        val client = AsyncHttpClient() // instantiating 'LoopJ' 'AsyncHttpClient'
        val url = "https://quote-api.dicoding.dev/random"
        // do 'API request' and 'pass function'
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                binding.progressBar.visibility = View.INVISIBLE // 'progressBar' 'invisible' on 'success request'

                val result = String(responseBody!!)
                Log.d(TAG, result)
                try {
                    // get & 'assigning' data from API to 'View'
                    val responseObject = JSONObject(result)
                    val quote = responseObject.getString("en")
                    val author = responseObject.getString("author")

                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

}

// Web API (Application Programming Interface): 'service' to 'allow to be connected' to a 'network', so we could 'send' and 'get' data from the service
// REST (Representation State Transfer): an 'API architecture' that could 'send data' in format text, JSON, or XML via 'HTTP communication'
// HTTP: a 'protocol' that allows to 'exchange data' between 'server' & 'client'
// HTTP Methods:
// - POST: to 'create' data
// - GET: to 'read' data
// - PUT: to 'update' data
// - DELETE: to 'delete' data

// example of an API URL: https://reqres.in/api/users?page=1&per_page=10
// Path: 'users'
// Query 1: 'page' = '1'
// Query 2: 'per_page' = '10'
// use '?' for 'first parameter'
// use '&' for 'other parameter'
// use '=' to 'pass value to query'

// Postman: is a 'GUI' 'API Caller' 'app' that could 'insert parameter/body' 'at ease by form'
