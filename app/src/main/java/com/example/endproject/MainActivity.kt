package com.example.endproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.endproject.data.Member
import com.example.endproject.data.MemberViewModel
import com.example.endproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MemberAdapter.OnItemClickListener{
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var adapter: MemberAdapter
    private lateinit var allMembers: LiveData<List<Member>>

    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        binding.button.setOnClickListener { insert() }

        binding.memberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(this, this)
        binding.memberRecyclerView.adapter = adapter

        mFragmentManager = supportFragmentManager
        mFragmentManager.beginTransaction().replace(R.id.myNavHostGragment,FragmentOne()).commit()

        //MemberAdapter(MemberOfParliament.ParliamentMembersData.members)

        allMembers = memberViewModel.readAllData

        memberViewModel.members.observe(this){
            for (member in it){
                memberViewModel.addMember(member)
            }
        }

        allMembers.observe(this){
            Log.d("observer", "observing")
            adapter.submitList(it)
        }


    }

    fun insert(){
        /*
        for (member in MemberOfParliament.ParliamentMembersData.members){
            memberViewModel.addMember(member)
        }*/
        memberViewModel.readMembers()

    }

    override fun onItemClick(position: Int) {
        /*
        Toast.makeText(this, "this $position", Toast.LENGTH_SHORT).show()
        val m: String = allMembers.value?.get(position)?.first.toString()
        Log.d("item","$m")
         */

        val mBundle = Bundle()
        mBundle.putString("first",allMembers.value?.get(position)?.first.toString())
        mBundle.putString("last",allMembers.value?.get(position)?.last.toString())

        val mFragment = FragmentOne()
        mFragment.arguments = mBundle
        val mFragmentTransaction = mFragmentManager.beginTransaction()


        mFragmentTransaction.replace(R.id.myNavHostGragment,mFragment).commit()

    }
}

