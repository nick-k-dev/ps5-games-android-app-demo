package com.example.nkomarnicki.ps5gameswebscraper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.nodes.Document

class ScraperViewModel(
    private val gamesScraper: GamesScraper
): ViewModel() {

    fun getGames(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gamesScraper.getGames(url)
            when (result) {
                is Result.Success<Document> -> {
                    MainActivity.dataContainer = gamesScraper.parseData(result.data)
                }
                else -> println("Error loading data")
            }
        }
    }
}