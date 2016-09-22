package com.example.filip.movielist.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by Filip Babic @cobe
 */
open class MovieGenre(@SerializedName("name") var genre: String?) : RealmObject() {

    constructor() : this("")
}