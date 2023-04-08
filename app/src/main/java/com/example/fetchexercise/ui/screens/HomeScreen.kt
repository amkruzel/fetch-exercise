package com.example.fetchexercise.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.R
import com.example.fetchexercise.model.ItemMap
import com.example.fetchexercise.model.ItemNoListId
import com.example.fetchexercise.model.ListId


@Composable
fun HomeScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen(modifier)
        is UiState.Success -> ResultScreen(uiState.data, modifier)
        is UiState.Error -> ErrorScreen(uiState.message, modifier)

    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading))
    }
}

@Composable
private fun ErrorScreen(message: String?, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        val error = if (message == "") "" else ": $message"
        Text(stringResource(id = R.string.loading_failed) + error)
    }
}



/**
 * The home screen displaying result of fetching the data.
 */
@Composable
private fun ResultScreen(data: ItemMap, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        ItemsList(itemsList = data)
    }
}

@Composable
private fun IdCard(id: ListId, items: MutableList<ItemNoListId>, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "List ID: $id",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }

            Row {
                Text(
                    text = "Name",
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp),
                    style = MaterialTheme.typography.h5,
                )

                Text(
                    text = "ID",
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp),
                    style = MaterialTheme.typography.h5,
                )
            }

            for (item in items) {
                Row {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .width(100.dp),
                        style = MaterialTheme.typography.body1
                    )

                    Text(
                        text = item.id.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemsList(itemsList: ItemMap, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item(itemsList) {
            itemsList.forEach { entry -> IdCard(entry.key, entry.value ) }
        }
    }
}
