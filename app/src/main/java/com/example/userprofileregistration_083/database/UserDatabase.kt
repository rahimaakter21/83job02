package com.example.userprofileregistration_083.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userprofileregistration_083.Models.UserProfile
import com.example.userprofileregistration_083.dao.UserProfileDao

@Database(entities = [UserProfile::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract  fun _userProfileDao(): UserProfileDao

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null
        fun getDatabase(context: Context):UserDatabase{

            return  INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_profile_database"

                ).build()
                INSTANCE =instance
                instance
            }
        }
    }


}