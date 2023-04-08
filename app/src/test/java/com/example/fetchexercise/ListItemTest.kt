package com.example.fetchexercise

import com.example.fetchexercise.network.ItemMap
import com.example.fetchexercise.network.ItemNoListId
import com.example.fetchexercise.network.ListItem
import com.example.fetchexercise.network.rawListToMap
import org.junit.Test

import org.junit.Assert.*

class ListItemTest {
    @Test
    fun listMapping_isCorrect() {
        val oneNullNameAndOneUniqueId = listOf(
            ListItem(id = 1, listId = 2, name = null),
            ListItem(id = 2, listId = 3, name = "Item 2")
        )

        val multipleUniqueIds = listOf(
            ListItem(id = 12, listId = 2, name = "Item 12"),
            ListItem(id = 11, listId = 3, name = "Item 11"),
            ListItem(id = 10, listId = 2, name = "Item 10"),
        )

        val multipleUniqueIdsAndEmptyNames = listOf(
            ListItem(id = 13, listId = 1, name = "Item 13"),
            ListItem(id = 11, listId = 3, name = ""),
            ListItem(id = 12, listId = 2, name = null),
            ListItem(id = 10, listId = 1, name = "Item 10"),
            ListItem(id = 14, listId = 2, name = "Item 14"),
            ListItem(id = 15, listId = 3, name = "Item 15")
        )

        val mapWithOneItem = rawListToMap(oneNullNameAndOneUniqueId)
        val mapWithTwoItems = rawListToMap(multipleUniqueIds)
        val mapWithMultipleItems = rawListToMap(multipleUniqueIdsAndEmptyNames)

        val expectedMapOneItem: ItemMap = mutableMapOf(3 to mutableListOf(ItemNoListId(id = 2, name = "Item 2")))
        val expectedMapTwoItems: ItemMap = mutableMapOf(
            2 to mutableListOf(
                ItemNoListId(id = 10, name = "Item 10"),
                ItemNoListId(id = 12, name = "Item 12")
            ),
            3 to mutableListOf(
                ItemNoListId(id = 11, name = "Item 11")
            ),
        )
        val expectedMapMultipleItems: ItemMap = mutableMapOf(
            1 to mutableListOf(
                ItemNoListId(id = 10, name = "Item 10"),
                ItemNoListId(id = 13, name = "Item 13")
            ),
            2 to mutableListOf(
                ItemNoListId(id = 14, name = "Item 14")
            ),
            3 to mutableListOf(
                ItemNoListId(id = 15, name = "Item 15")
            ),
        )

        assertEquals(expectedMapOneItem, mapWithOneItem)
        assertEquals(expectedMapTwoItems, mapWithTwoItems)
        assertEquals(expectedMapMultipleItems, mapWithMultipleItems)
    }
}