package com.ashraf.hospital.ui.screen.report

import com.ashraf.hospital.ui.base.ErrorUiState

data class ReportsUiState(
    val reports: List<ReportUiState> = emptyList(),
    val isLoading: Boolean = true,
    val errorUiState: ErrorUiState? = null,
    val errorMessage: String = "",
)

data class ReportUiState(
    val name: String = "N/A",
    val desc: String = "N/A",
    val date: String = "N/A",
    val id: Int = 0,
    val isDone:Boolean=false,
)
