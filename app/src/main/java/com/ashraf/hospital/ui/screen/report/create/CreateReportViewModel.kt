package com.ashraf.hospital.ui.screen.report.create

import com.ashraf.hospital.domain.usecase.ManageReportUseCa
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateReportViewModel @Inject constructor(
    private val reports: ManageReportUseCa,
) : BaseViewModel<CreateReportUiState, CreateReportUiEffect>(CreateReportUiState()) {

    fun addReport() {
        tryToExecute(
            function = { reports.addReport(state.value.name, state.value.desc, "30-01-2024") },
            onSuccess = { sendNewEffect(CreateReportUiEffect.Back) },
            onError = {}
        )
    }

    fun onNameChanged(name: String) {
        updateState { it.copy(name = name) }
    }

    fun onDescChanged(desc: String) {
        updateState { it.copy(desc = desc) }
    }
}