package io.github.mickie895.iralarm.ui.taskassign.adapter.ir

import io.github.mickie895.iralarm.model.data.IRFormat

data class SelectableIrTask(
    val task:IRFormat,
    val selected: Boolean
)
