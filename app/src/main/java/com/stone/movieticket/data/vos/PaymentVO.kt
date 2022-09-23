package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "paymentMethod")
data class PaymentVO(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id:Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name:String?,

    @ColumnInfo(name="description")
    @SerializedName("description")
    val description:String?,

    var isSelected:Boolean=false

)