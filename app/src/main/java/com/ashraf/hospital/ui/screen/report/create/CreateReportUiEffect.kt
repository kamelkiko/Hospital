package com.ashraf.hospital.ui.screen.report.create

sealed interface CreateReportUiEffect {
    data object Back:CreateReportUiEffect
}