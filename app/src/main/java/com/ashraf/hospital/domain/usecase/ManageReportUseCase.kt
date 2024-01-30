package com.ashraf.hospital.domain.usecase

import com.ashraf.hospital.domain.repository.IHospitalRepository
import javax.inject.Inject

class ManageReportUseCa @Inject constructor(
    private val authRepository: IHospitalRepository,
) {
    suspend fun addReport(name: String, desc: String, date: String) =
        authRepository.createReport(name, desc, date)

    suspend fun getAllReports() = authRepository.getAllReports()
}