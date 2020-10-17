package com.gabodev.widetech

import retrofit2.Call
import android.os.Bundle
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_products.*

class ListProductsActivity : AppCompatActivity() {

    private val list = ArrayList<ProductResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_products)

        title = this.resources.getString(R.string.list_page_title)

        rvProduct.setHasFixedSize(true)
        rvProduct.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getProducts().enqueue(object : retrofit2.Callback<ArrayList<ProductResponse>> {

            override fun onFailure(call: Call<ArrayList<ProductResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<ArrayList<ProductResponse>>, response: Response<ArrayList<ProductResponse>>) {
                response.body()?.let { list.addAll(it) }
                val adapter = ProductAdapter(list)
                rvProduct.adapter = adapter
            }
        })
    }
}