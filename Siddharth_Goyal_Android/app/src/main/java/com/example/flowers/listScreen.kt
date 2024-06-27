package com.example.flowers

import Flower
import FlowerAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers.APIClient
import com.example.flowers.ApiInterface
import com.example.flowers.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FlowerAdapter
    private lateinit var apiInterface: ApiInterface
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_screen)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        apiInterface = APIClient.getClient().create(ApiInterface::class.java)

        loadFlowers()

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadFlowers() {
        progressBar.visibility = View.VISIBLE
        apiInterface.getFlowers().enqueue(object : Callback<List<Flower>> {
            override fun onResponse(call: Call<List<Flower>>, response: Response<List<Flower>>) {
                progressBar.visibility = View.GONE
                val flowers = response.body()
                if (response.isSuccessful) {
                    Log.d("ListScreen", "Response code: ${response.code()}")
                    if (flowers != null) {
                        adapter = FlowerAdapter(flowers)
                        recyclerView.adapter = adapter
                    } else {
                        Log.w("ListScreen", "Flowers list is null or empty")
                        Toast.makeText(this@ListScreen, "No flowers found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ListScreen", "Error loading flowers: ${response.errorBody()}")
                    Toast.makeText(this@ListScreen, "Error loading flowers", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Flower>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("ListScreen", "Error loading flowers", t)
                Log.e("ListScreen", "Error message: ${t.message}")
                Toast.makeText(this@ListScreen, "Error loading flowers", Toast.LENGTH_SHORT).show()
            }
        })
    }
}