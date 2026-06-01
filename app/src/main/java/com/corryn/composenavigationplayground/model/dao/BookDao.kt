package com.corryn.composenavigationplayground.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.corryn.composenavigationplayground.model.entity.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Delete
    suspend fun delete(book: BookEntity)

    @Query("SELECT * FROM books")
    suspend fun getAll(): List<BookEntity>

}