package net.tack.art_art.RowModel

import io.realm.RealmObject
import java.io.Serializable

class RowModel_FavoriteEx : Serializable, RealmObject(){

    var title : String = ""

    var date : String = ""

    var nameOfMuseum : String = ""

    var museumAddress : String = ""

    var urlOfMuseum : String = ""

    var isFavorite: Boolean = false

}