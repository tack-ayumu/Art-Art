package net.tack.art_art

import org.jsoup.Jsoup

class UrlGetThred(url:String) {
    val document = Jsoup.connect(url).get()





}