package com.corryn.composenavigationplayground.di

import android.content.Context
import androidx.room.Room
import com.corryn.composenavigationplayground.model.entity.PlaygroundDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlaygroundModule {

    @Singleton
    @Provides
    fun providePlaygroundDatabase(
        @ApplicationContext context: Context
    ): PlaygroundDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PlaygroundDatabase::class.java,
            name = PlaygroundDatabase.PLAYGROUND_DB_NAME
        ).build()
    }
    
}