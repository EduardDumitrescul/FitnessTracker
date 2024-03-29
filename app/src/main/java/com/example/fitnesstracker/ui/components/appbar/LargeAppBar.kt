package com.example.fitnesstracker.ui.components.appbar

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnesstracker.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeAppBar(
    modifier: Modifier = Modifier,
    title: String,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    MediumTopAppBar(
        modifier = modifier.background(AppTheme.colors.container),
        colors = appBarColors,
        title = { AppBarTitle(title) },
        navigationIcon = { BackNavigationIcon(onClick = { /*TODO*/ }) },
        actions = { MoreActionsIcon(onClick = { /*TODO*/ })},
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
private fun LargeAppBarPreview() {
    LargeAppBar(title = "Medium Top App Bar")
}