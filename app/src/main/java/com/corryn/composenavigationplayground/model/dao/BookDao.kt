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
    fun insertAll(vararg books: BookEntity)

    @Delete
    fun delete(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getAll(): List<BookEntity>

}