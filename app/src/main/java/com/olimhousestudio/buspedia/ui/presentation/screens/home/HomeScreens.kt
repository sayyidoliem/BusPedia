package com.olimhousestudio.buspedia.ui.presentation.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.helper.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreens(
    viewModel: HomeViewModel = viewModel(
        factory = viewModelFactory {
            HomeViewModel(BusPediaApplication.appModule.getRepository)
        }
    )
) {

    val configuration = LocalConfiguration.current

    val navigator = rememberListDetailPaneScaffoldNavigator()

    BackHandler {
        navigator.canNavigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "BusPedia") })
        }
    ) { paddingValues ->
        ListDetailPaneScaffold(
            modifier = Modifier.padding(paddingValues),
            directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            listPane = {
                AnimatedPane {
                    LazyColumn {
                        items(2) {
                            ListItem(headlineContent = { Text(text = "") })
                        }
                    }
                }
            },
            detailPane = {
                AnimatedPane {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Card(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .aspectRatio(1 / 1f)
                                    .padding(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    model = "",
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }

                        }
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                            text = "",
                            fontSize = 32.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "")
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                text = " | ",
                            )
                        }
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                            text = ""
                        )
                    }
                }
            }
        )
    }
}