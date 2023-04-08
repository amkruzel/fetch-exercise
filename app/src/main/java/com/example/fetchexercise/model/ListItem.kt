package com.example.fetchexercise.model

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val id: Int,
    val listId: Int,
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

    // This is okay because we know the name will either be null, "", or "Item ###"
    return name.split(" ")[1].toIntOrNull() ?: -1
}

fun rawListToMap(list: List<ListItem>): ItemMap {
    val returnMap: ItemMap = mutableMapOf()
    val sortedList = sortListItems(list)

    for (item in sortedList) {
        // we still need to remove the items where name is null or empty
        if (item.name.isNullOrEmpty()) continue

        // we started with an empty Map, so if this key doesn't exist yet, add it
        returnMap.putIfAbsent(item.listId, mutableListOf())

        returnMap[item.listId]?.add(ItemNoListId(item.id, item.name))
    }

    return returnMap
}

