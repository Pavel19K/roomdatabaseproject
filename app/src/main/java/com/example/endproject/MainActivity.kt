package com.example.endproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.endproject.data.MemberViewModel
import com.example.endproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var memberViewModel: MemberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        binding.button.setOnClickListener { insert() }
    }

    fun insert(){
        for (member in MemberOfParliament.ParliamentMembersData.members){
            memberViewModel.addMember(member)
        }

    }
}

