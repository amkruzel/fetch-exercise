package com.example.fetchexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.model.Item
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
fun IdCard(i: ListItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Row {
            Text(
                text = i.listId.toString(),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )

            Text(
                text = i.id.toString(),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )

            i.name?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun ItemsList(itemsList: List<ListItem>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(itemsList){ item ->
            IdCard(item)
        }
    }
}
