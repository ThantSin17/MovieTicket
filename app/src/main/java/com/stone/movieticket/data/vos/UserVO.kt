package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.stone.movieticket.persistance.typeconverters.CardTypeConverter

@Entity(tableName = "user")
@TypeConverters(
    CardTypeConverter::class
)
data class UserVO(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String?,

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    val phone: String?,

    @ColumnInfo(name = "google_id")
    @SerializedName("google_id")
    val googleId: String?,

    @ColumnInfo(name = "facebook_id")
    @SerializedName("facebook_id")
    val facebookId: String?,

    @ColumnInfo(name = "total_expense")
    @SerializedName("total_expense")
    val totalExpense: Int?,

    @ColumnInfo(name = "profile_image")
    @SerializedName("profile_image")
    val profileImage: String?,

    @ColumnInfo(name = "cards")
    @SerializedName("cards")
    val cards: List<CardVO>?,

    @ColumnInfo(name = "token")
    @SerializedName("token")
    var token: String?,
)