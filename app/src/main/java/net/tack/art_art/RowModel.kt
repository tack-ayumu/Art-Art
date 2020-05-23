package net.tack.art_art

import io.realm.RealmObject
import java.io.Serializable

open class RowModel:Serializable,RealmObject(){

    var title : String = ""

    var date : String = ""

    var nameOfMuseum : String = ""

    var museumAddress : String = ""

    var urlOfMuseum : String = ""

//    var isFavorite: Boolean = false

}



