package com.gabodev.widetech

import android.content.Intent
import retrofit2.Call
import android.os.Bundle
import android.view.Menu
import retrofit2.Response
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list_products, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_contact -> {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}