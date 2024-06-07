package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    @SerialName("taskId")
    val taskId: String? = null,
    @SerialName("taskType")
    val taskType: String? = null,
    @SerialName("timeOfDay")
    val timeOfDay: String? = null,
    @SerialName("taskGroup")
    val taskGroup: String? = null,
    @SerialName("action")
    val action: String? = null,
    @SerialName("order")
    val order: String? = null,
    @SerialName("priority")
    val priority: String? = null,
    @SerialName("taskDetailRef")
    val taskDetailRef: String? = null,
    @SerialName("hourOfDay")
    val hourOfDay: String? = null,
    @SerialName("supportLevel")
    val supportLevel: String? = null,
    @SerialName("taskStartedOn")
    val taskStartedOn: String? = null,
    @SerialName("taskEndedOn")
    val taskEndedOn: String? = null,
    @SerialName("taskDate")
    val taskDate: String? = null,
    @SerialName("workStatus")
    val workStatus: String? = null,
    @SerialName("isAssigned")
    val isAssigned: Boolean? = null,
    @SerialName("supportPersonnel")
    val supportPersonnel: String? = null,
    @SerialName("noSupportPersonnel")
    val noSupportPersonnel: String? = null,
    @SerialName("userId")
    val userId: Int? = null,
    @SerialName("taskScheduleId")
    val taskScheduleId: String? = null,
    @SerialName("taskAssignments")
    val taskAssignments: List<TaskAssignmentsDto> = emptyList()
)