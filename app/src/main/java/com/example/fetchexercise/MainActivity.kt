package com.example.fetchexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.model.Item
import com.example.fetchexercise.network.ItemMap
import com.example.fetchexercise.network.ItemNoListId
import com.example.fetchexercise.network.ListId
import com.example.fetchexercise.network.ListItem
import com.example.fetchexercise.ui.FetchApp
import com.example.fetchexercise.ui.theme.FetchExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchExerciseTheme {
                FetchApp()
            }
        }
    }
}

@Composable
fun IdCard(id: ListId, items: MutableList<ItemNoListId>, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Row {
                Text(
                    text = "List ID: $id",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h4
                )
            }

            for (item in items) {
                Row {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .width(200.dp),
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = item.id.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsList(itemsList: ItemMap, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(itemsList.size) {
            itemsList.forEach { entry ->
                IdCard(entry.key, entry.value )
            }
        }
    }
}
