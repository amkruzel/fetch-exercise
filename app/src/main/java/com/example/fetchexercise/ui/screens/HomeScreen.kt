package com.example.fetchexercise.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import com.example.fetchexercise.R
import com.example.fetchexercise.ui.theme.FetchExerciseTheme


@Composable
fun HomeScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen(modifier)
        is UiState.Success -> ResultScreen(uiState.data, modifier)
        is UiState.Error -> ErrorScreen(modifier)

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}



/**
 * The home screen displaying result of fetching photos.
 */
@Composable
fun ResultScreen(uiState: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(uiState)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    FetchExerciseTheme() {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
