package com.example.endproject.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemberViewModel(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Member>>
    private val memberRepository: MemberRepository


    init {
        val memberDao = MemberDatabase.getDatabase(application).memberDao()
        memberRepository = MemberRepository(memberDao)
        readAllData = memberRepository.readAllDAta
    }

    fun addMember(member:Member){
        viewModelScope.launch(Dispatchers.IO) {
            memberRepository.addMember(member)
        }
    }


}