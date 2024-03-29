package com.example.endproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class], version = 3, exportSchema = false)
abstract class MemberDatabase: RoomDatabase() {

    abstract fun memberDao(): MemberDao

    companion object{
        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getDatabase(context: Context):MemberDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemberDatabase::class.java,
                    "member_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}