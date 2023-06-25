package io.github.mickie895.iralarm.ui.taskassign.adapter.ir

import io.github.mickie895.iralarm.model.data.IRFormat

interface IrTaskEditListener {
    fun onTaskSelected(task: IRFormat)
    fun onTaskEditClicked(task: IRFormat)
    fun onTaskDeleteClicked(task: IRFormat)
}