package com.example.fetchexercise.network

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    @StringRes val id: Int,
    @StringRes val listId: Int,
    val name: String?
)

data class Item(
    @StringRes val id: Int,
    @StringRes val listId: Int,
    val name: String
)

fun formatListItems(list: List<ListItem>): List<ListItem> {
    val listWithoutBlankNames: List<ListItem> = list.filter { it.name !== null && it.name.isNotEmpty() }

    return listWithoutBlankNames.sortedWith(compareBy<ListItem> { it.listId }.thenBy { it.name })
}
/*
// remove null or blank names

// make into a map

fun removeNullOrBlankNames(list: List<ListItem>): List<Item> {

}
*/
