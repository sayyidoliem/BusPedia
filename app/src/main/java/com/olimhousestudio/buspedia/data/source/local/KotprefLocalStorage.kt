package com.olimhousestudio.buspedia.data.source.local

import com.chibatching.kotpref.KotprefModel

object KotprefLocalStorage : KotprefModel() {

    var username: String by stringPref("")

    var email: String by stringPref("")

    var accessToken: String by stringPref("")

    var userUid: String by stringPref("")

    var isAdmin: Boolean by booleanPref(false)

    var created: String by stringPref("")

    var isSplash: Boolean by booleanPref(false)
}