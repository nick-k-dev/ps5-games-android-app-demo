package com.example.nkomarnicki.ps5gameswebscraper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val url = "https://www.playstation.com/en-ca/ps5/games/"

    companion object {
        var dataContainer = GamesScraper.DataContainer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scraperVM = ScraperViewModel(GamesScraper())
        val context = this
        scraperVM.getGames(url, object:ScraperViewModel.ScraperResponse{
            override fun onFinishedLoading() {
                println("print dataContainer")
                println(dataContainer.titles.count())
                viewManager = LinearLayoutManager(context)
                viewAdapter = MyRecyclerAdapter(dataContainer)

                recyclerView = findViewById<RecyclerView>(R.id.gamesRecycler).apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    //Add item decoration specifically used to add spacing between elements
                    addItemDecoration(MyRecyclerAdapter.MyItemDecorator())
                    // specify an viewAdapter (see also next example)
                    adapter = viewAdapter
                }
            }
        })
    }
}