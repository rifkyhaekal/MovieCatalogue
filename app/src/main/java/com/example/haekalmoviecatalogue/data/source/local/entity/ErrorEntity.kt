package com.example.haekalmoviecatalogue.data.source.local.entity

import androidx.annotation.DrawableRes

data class ErrorEntity (
    var visible: Boolean = false,
    var infoText: String? = null,
    @DrawableRes var infoImg: Int? = -1
)