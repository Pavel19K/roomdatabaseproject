package com.example.endproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.endproject.data.MemberViewModel
import com.example.endproject.databinding.FragmentOneBinding


@GlideModule
class AppGlideModule : AppGlideModule()


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
        binding.fragmentBornYear.setText(bundle?.getString("year"))


        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg")
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.fragmentImage)
        Log.d("testImage", "${bundle?.getString("url")}")
        return binding.root
    }


}