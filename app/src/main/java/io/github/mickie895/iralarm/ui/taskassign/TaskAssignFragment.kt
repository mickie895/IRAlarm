package io.github.mickie895.iralarm.ui.taskassign

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskAssignFragment: Fragment() {
    private val viewModel : TaskAssignViewModel by viewModels()
}