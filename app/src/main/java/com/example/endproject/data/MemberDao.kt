package com.example.endproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMember(member: Member)

    @Query("SELECT * FROM member_table ORDER BY memberId ASC")
    fun readAllData(): LiveData<List<Member>>
}