package com.example.endproject.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.endproject.api.MemberApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemberViewModel(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Member>>
    private val memberRepository: MemberRepository
    val members: MutableLiveData<List<Member>> = MutableLiveData()

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

    fun readMembers(){
        viewModelScope.launch {
            try {
                members.value = MemberApi.retrofitService.getMemberList()
                println("Read players from NW with great success.")
            } catch (e: Exception) {
                println("No luck in reading players from NW: ${e}")
            }

        }
    }


}