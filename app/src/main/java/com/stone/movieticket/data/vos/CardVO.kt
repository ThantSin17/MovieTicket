package com.stone.movieticket.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cards")
data class CardVO(
    @SerializedName("id")
    @PrimaryKey
    val id:Int,

    @ColumnInfo(name ="card_holder" )
    @SerializedName("card_holder")
    val cardHolder:String,

    @ColumnInfo(name = "card_number")
    @SerializedName("card_number")
    val cardNumber:String,

    @ColumnInfo(name = "expiration_date")
    @SerializedName("expiration_date")
    val expirationDate:String,

    @ColumnInfo(name = "card_type")
    @SerializedName("card_type")
    val cardType:String
) {
    fun getFourthSlot():String{
        return cardNumber.substring(cardNumber.length-4,cardNumber.length)
    }
}