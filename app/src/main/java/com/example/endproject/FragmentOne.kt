package com.example.endproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.endproject.data.MemberViewModel
import com.example.endproject.databinding.FragmentOneBinding


class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentOneBinding>(inflater, R.layout.fragment_one, container, false)

        val memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        val bundle = arguments
        binding.fragmentName.setText(bundle?.getString("first"))
        binding.fragmentLastName.setText(bundle?.getString("last"))

        return binding.root
    }


}