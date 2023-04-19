package com.ramces.catspragma.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.ramces.catspragma.R
import com.ramces.catspragma.entities.Breed
import com.ramces.catspragma.view.adapters.BreedAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var breedAdapter: BreedAdapter

    private val breeds = mutableListOf<Breed>()

    private val url = "https://api.thecatapi.com/v1/breeds"
    private val apiKey = "bda53789-d59e-46cd-9bc4-2936630fde39"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        breedAdapter = BreedAdapter(breeds)
        recyclerView.adapter = breedAdapter
        fetchBreeds()
    }

    private fun fetchBreeds() {
        val requestQueue = Volley.newRequestQueue(this)
        val request = object : JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val newBreeds = mutableListOf<Breed>()
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val id = item.getString("id")
                    val name = item.getString("name")
                    val origin = item.getString("origin")
                    val intelligence = item.getInt("intelligence")
                    val imageUrl = if (item.has("image")) item.getJSONObject("image").getString("wikipedia_url") else "" //Tuve un problema aqui
                    newBreeds.add(Breed(id, name, origin, intelligence, imageUrl))
                }
                breeds.addAll(newBreeds)
                breedAdapter.setBreeds(breeds)
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-api-key"] = "bda53789-d59e-46cd-9bc4-2936630fde39"
                return headers
            }
        }
        requestQueue.add(request)
    }
}
