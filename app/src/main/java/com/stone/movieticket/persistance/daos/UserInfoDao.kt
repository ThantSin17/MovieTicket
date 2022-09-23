package com.stone.movieticket.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.movieticket.data.vos.UserVO

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserVO)

    @Query("SELECT * FROM user")
    fun getUser(): UserVO?

    @Query("DELETE FROM user")
    fun deleteUser()

    @Query("SELECT token FROM user")
    fun getToken(): String?

}