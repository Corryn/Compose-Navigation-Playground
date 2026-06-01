package com.corryn.composenavigationplayground.repository

import com.corryn.composenavigationplayground.model.entity.BookEntity
import com.corryn.composenavigationplayground.model.entity.PlaygroundDatabase
import javax.inject.Inject

class BookRepository @Inject constructor(
    database: PlaygroundDatabase
) {

    private val bookDao = database.bookDao()

    suspend fun insertBooks(books: List<BookEntity>) {
        bookDao.insertAll(books)
    }

    suspend fun getBooks(): List<BookEntity> {
        return bookDao.getAll()
    }

    suspend fun deleteBook(book: BookEntity) {
        bookDao.delete(book)
    }

}