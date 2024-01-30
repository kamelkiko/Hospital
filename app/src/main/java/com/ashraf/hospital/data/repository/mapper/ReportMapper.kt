package com.ashraf.hospital.data.repository.mapper

import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.domain.model.Report

fun ReportEntity.toReport(): Report = Report(
    id, name, description, date, isDone
)