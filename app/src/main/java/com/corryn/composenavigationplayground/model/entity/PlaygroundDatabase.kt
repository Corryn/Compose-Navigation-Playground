package com.corryn.composenavigationplayground.model.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.corryn.composenavigationplayground.model.dao.BookDao

@Database(
    version = 1,
    entities = [BookEntity::class]
)
abstract class PlaygroundDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        const val PLAYGROUND_DB_NAME = "playground-db"
    }

}