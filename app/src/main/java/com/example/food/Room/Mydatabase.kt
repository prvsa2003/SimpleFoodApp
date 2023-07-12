package com.example.food.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(version = 1 , exportSchema = false , entities =[Food::class] )
abstract class Mydatabase : RoomDatabase (){
    abstract val foodDao : FoodDao

    companion object{

        private var database : Mydatabase?=null
        fun getdatabase(context: Context):Mydatabase{
            var  instance = database
            if (instance==null){
                instance = Room.databaseBuilder(context.applicationContext , Mydatabase::class.java,"myDatabase.db").build()
            }
            return instance
        }

    }


}