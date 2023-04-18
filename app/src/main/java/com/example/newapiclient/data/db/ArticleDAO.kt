package com.example.newapiclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

}