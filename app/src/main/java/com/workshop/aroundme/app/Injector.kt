package com.workshop.aroundme.app

import android.content.Context
import androidx.room.Room
import com.workshop.aroundme.data.PlaceRepository
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.remote.service.PlaceService

object Injector {

    private fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
    }

    fun providePlaceRepository(context: Context): PlaceRepository {
        return PlaceRepository(
            PlaceLocalDataSource(
                provideAppDatabase(context).placeDao()
            ),
            PlaceRemoteDataSource(
                PlaceService(
                    NetworkManager()
                )
            )
        )
    }
}