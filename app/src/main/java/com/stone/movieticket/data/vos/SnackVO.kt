package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "snacks")
data class SnackVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    var quantity: Int = 0,

    var currentValue: Int = 0

) : Serializable {
}