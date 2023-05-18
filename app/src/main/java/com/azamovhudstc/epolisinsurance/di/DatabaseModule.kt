//package com.azamovhudstc.epolisinsurance.di
//
//import android.content.Context
//import androidx.room.Room
//import com.azamovhudstc.epolisinsurance.data.local.room.dao.ProfileDao
//import com.azamovhudstc.epolisinsurance.data.local.room.database.AppDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//
//class DatabaseModule {
//    @[Provides Singleton]
//    fun provideVideosDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
//            .build()
//
//    }
//
//    @Provides
//    fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
//        return appDatabase.profileDao()
//    }
////    @Provides
////    fun provideBookMark(appDatabase: AppDatabase): MoviesDao {
////        return appDatabase.moviesDao()
////    }
//}