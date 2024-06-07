package com.caresaas.caresaasmobiletask.core.domain.model

import com.caresaas.caresaasmobiletask.core.data.remote.model.TaskAssignmentsDto

data class Task(
    val taskId: String? = null,
    val taskType: String? = null,
    val timeOfDay: String? = null,
    val taskGroup: String? = null,
    val action: String? = null,
    val order: String? = null,
    val priority: String? = null,
    val taskDetailRef: String? = null,
    val hourOfDay: String? = null,
    val supportLevel: String? = null,
    val taskStartedOn: String? = null,
    val taskEndedOn: String? = null,
    val taskDate: String? = null,
    val workStatus: String? = null,
    val isAssigned: Boolean? = null,
    val supportPersonnel: String? = null,
    val noSupportPersonnel: String? = null,
    val userId: Int? = null,
    val taskScheduleId: String? = null,
    val taskAssignments: List<TaskAssignmentsDto> = emptyList()
)