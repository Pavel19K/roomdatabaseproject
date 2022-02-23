package com.example.endproject.data

import androidx.lifecycle.LiveData

class MemberRepository(private val memberDao: MemberDao) {

    val readAllDAta: LiveData<List<Member>> = memberDao.readAllData()

    suspend fun addMember(member: Member){
        memberDao.addMember(member)
    }
}