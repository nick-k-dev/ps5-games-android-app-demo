package com.example.nkomarnicki.ps5gameswebscraper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.nodes.Document

//ViewModel class to handle loading the data off of the main thread
class ScraperViewModel(
    private val gamesScraper: GamesScraper
): ViewModel() {
    //Launch a new thread to utilize the GameScraper off of the main thread
    fun getGames(url: String, scraperResponse: ScraperResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gamesScraper.getGames(url)
            when (result) {
                is Result.Success<Document> -> {
                    MainActivity.dataContainer = gamesScraper.parseData(result.data)
                    //Callback must be called back on the main thread
                    viewModelScope.launch(Dispatchers.Main) {
                        scraperResponse.onFinishedLoading()
                    }
                }
                else -> println("Error loading data")
            }
        }
    }

    // Callback interface to handle notifying main when data is loaded
    interface ScraperResponse {
        fun onFinishedLoading()
    }
}