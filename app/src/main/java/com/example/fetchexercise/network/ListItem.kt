package com.example.fetchexercise.network

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    @StringRes val id: Int,
    @StringRes val listId: Int,
    val name: String?
)

data class ItemNoListId(
    val id: Int,
    val name: String
)

typealias ListId = Int
typealias ItemMap = MutableMap<ListId, MutableList<ItemNoListId>>

private fun sortListItems(list: List<ListItem>): List<ListItem> {
    return list.sortedWith(compareBy<ListItem> { it.listId }.thenBy { getIntFromName(it.name) })
}

private fun getIntFromName(name: String?): Int {
    // sort values with null or empty name together - these get removed
    // when the list is converted to a map
    if (name.isNullOrEmpty()) return -1

    return name.split(" ")[1].toIntOrNull() ?: -1
}

fun convertRawListToMap(list: List<ListItem>): ItemMap {
    val returnMap: ItemMap = mutableMapOf(1 to arrayListOf(), 2 to arrayListOf(), 3 to arrayListOf(), 4 to arrayListOf())
    val sortedList = sortListItems(list)

    // we still need to remove the items where name is null or empty
    for (item in sortedList) {
        if (item.name.isNullOrEmpty()) continue

        returnMap[item.listId]?.add(ItemNoListId(item.id, item.name))
    }

    return returnMap
}

