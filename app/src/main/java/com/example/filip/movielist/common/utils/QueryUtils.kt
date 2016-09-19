package com.example.filip.movielist.common.utils

import com.example.filip.movielist.constants.Constants
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class QueryUtils {

    companion object {

        fun createMoviesQueryMap(pageToLoad: Int, apiKey: String): Map<String, String> {
            val queryMap = HashMap<String, String>()
            queryMap.put(Constants.PAGE_QUERY_KEY, pageToLoad.toString())
            queryMap.put(Constants.API_QUERY_KEY, apiKey)
            return queryMap
        }
    }
}