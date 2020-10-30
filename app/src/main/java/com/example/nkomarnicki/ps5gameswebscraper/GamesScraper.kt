package com.example.nkomarnicki.ps5gameswebscraper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class GamesScraper {
    suspend fun getGames(url: String
    ): Result<Document> {
        try {
            println("in getMoviesByGenre")
            return withContext(Dispatchers.IO) {
                println("in withContext Call")
                val doc = Jsoup.connect(url).get()
                println("made it past Jsoup call")
                return@withContext Result.Success(doc)
            }
        }
        catch (ex: Exception) {
            println("uh oh")
            println(ex)
            return Result.Error(ex)
        }
    }

    fun parseData(doc: Document): DataContainer {
        var dataContainer = DataContainer()
        val titles = doc.select(".box")
        println("in parseData")
        titles.forEach { item ->
            var title = item.select("h2, h3, h4").text()
            var synopsis = item.select("p").text()
            var img = item.select(".image-block__figure").select("[data-srcset]").first()?.attr("data-srcset")
            if (title != "" && title != null){
                dataContainer.titles.add(title)
            }
            if (synopsis != "" && synopsis != null){
                dataContainer.synopsis.add(synopsis)
            }
            if (img != "" && img != null){
                dataContainer.images.add(img)
            }
        }

        dataContainer.titles.removeAt(0)
        dataContainer.titles.removeAt(8)
        dataContainer.titles.removeAt(15)
        dataContainer.titles.removeAt(29)
        dataContainer.titles.removeAt(45)
        dataContainer.titles.removeAt(44)
        dataContainer.titles.removeAt(43)


        dataContainer.synopsis.removeAt(15)
        dataContainer.synopsis.removeAt(45)
        dataContainer.synopsis.removeAt(44)
        dataContainer.synopsis.removeAt(43)

        dataContainer.images.removeAt(0)
        dataContainer.images.removeAt(43)

        var x = 0
        while (x < dataContainer.synopsis.count()) {
            println(x.toString() + " - " + dataContainer.titles[x] + ": " + dataContainer.synopsis[x])
            println(dataContainer.images[x])
            x++
        }

        return dataContainer
    }

    class DataContainer(
        var titles: ArrayList<String> = ArrayList<String>(),
        var synopsis: ArrayList<String> = ArrayList<String>(),
        var images: ArrayList<String> = ArrayList<String>()
    ) {}
}