package com.example.newsfeedapp.common

import com.example.newsfeedapp.data.model.Article
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.Assert.*

class UtilTest {
    @Test
    fun dateFormat_theDateIsEmpty_returnEmptyString() {
        val result = "".dateFormat()
        assertThat(result, `is`(""))
    }
    @Test
    fun dateFormat_theDateIsNull_returnEmptyString() {
        val result = null.dateFormat()
        assertThat(result, `is`(""))
    }
    @Test
    fun dateFormat_returnTheNewDate() {
        val date = "2020-06-08T22:56:04Z"
        val result = date.dateFormat()
        assertThat(result, `is`("Mon, 8 Jun 2020"))
    }
    @Test
    fun searchQuery_addFakeList_returnTheSizeOfNewListWithMatchedTitle (){
         val fakeList = mutableListOf<Article>().apply {
        add(Article(title = "one",urlToImage = "",author = "",description = "" , publishedAt = "" , url = ""))
        add(Article(title = "two",urlToImage = "",author = "",description = "" , publishedAt = "" , url = ""))
        add(Article(title = "three",urlToImage = "",author = "",description = "" , publishedAt = "" , url = ""))
        add(Article(title = "two",urlToImage = "",author = "",description = "" , publishedAt = "" , url = ""))
         }
        val result="o".searchQuery(fakeList)
        assertThat(result?.size, `is`(3))
    }
    @Test
    fun searchQuery_addNullList_returnZeroSize (){
        val result="o".searchQuery(null)
        assertThat(result?.size, `is`(0))
    }
    @Test
    fun searchQuery_addEmptyList_returnZeroSize (){
        val result="o".searchQuery(emptyList())
        assertThat(result?.size, `is`(0))
    }
}