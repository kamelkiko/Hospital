package com.ashraf.hospital.ui.screen.report

import com.ashraf.hospital.domain.usecase.ManageReportUseCa
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val reports: ManageReportUseCa,
) : BaseViewModel<ReportsUiState, Nothing>(ReportsUiState()) {
    init {
        getAllReports()
    }

    fun getAllReports() {
        tryToExecute(
            function = { reports.getAllReports() },
            onSuccess = { list ->
                updateState {
                    it.copy(
                        reports = list.map { rep ->
                            ReportUiState(
                                name = rep.name,
                                desc = rep.description,
                                date = rep.date,
                                id = rep.id,
                                isDone = rep.isDone
                            )
                        },
                        isLoading = false,
                        errorUiState = null,
                        errorMessage = ""
                    )
                }
            },
            onError = { err ->
                updateState {
                    it.copy(
                        isLoading = false,
                        errorUiState = err
                    )
                }
            },
        )
    }
}