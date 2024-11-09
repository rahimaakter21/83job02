package com.example.userprofileregistration_083.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration_083.R
import com.example.userprofileregistration_083.adapter.ProfileAdapter
import com.example.userprofileregistration_083.viewmodel.UserProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileListActivity : AppCompatActivity() {

    private  lateinit var  profileViewHolder: UserProfileViewModel
    private  lateinit var  profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)

        profileViewHolder =  ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        profileAdapter = ProfileAdapter()

        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        profileViewHolder.getUserProfiles().observe(this, Observer { profiles ->
            profileAdapter.submitList(profiles)
        })

        profileAdapter.setOnItemClickListener { userProfiless ->
            val intent = Intent(this, SingleProfileActivity::class.java)
            intent.putExtra("USER_PROFILE",userProfiless)
            startActivity(intent)
        }

        profileAdapter.setOnUpdateClickListener { userProfiless ->
            val intent = Intent(this, UpdateProfileActivity::class.java)
            intent.putExtra("USER_PROFILE",userProfiless)
            startActivity(intent)
        }
        profileAdapter.setOnDeleteClickListener { userProfiless ->
            profileViewHolder.deleteUserProfile(userProfiless)

        }

        findViewById<FloatingActionButton>(R.id.addProfileBtn).setOnClickListener {
            val intent = Intent(this, AddProfileActivity::class.java)
            startActivity(intent)





        }
    }
}