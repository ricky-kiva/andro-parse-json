package com.rickyslash.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
