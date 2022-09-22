package com.stone.movieticket.data.vos

import java.io.Serializable

data class DateVO(
    val id:Int,
    val date: String,
    val day: String,
    val time: String,
    var isSelected:Boolean
): Serializable