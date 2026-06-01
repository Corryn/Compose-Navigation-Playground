package com.corryn.composenavigationplayground

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationViewModel: ViewModel() {

    private var _backstack: MutableStateFlow<List<AppDestination>> = MutableStateFlow(listOf(AppDestination.Home))
    val backstack = _backstack.asStateFlow()


}