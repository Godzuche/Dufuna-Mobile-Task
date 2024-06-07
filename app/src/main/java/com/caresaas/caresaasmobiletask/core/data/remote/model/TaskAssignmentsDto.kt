package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskAssignmentsDto(
    @SerialName("assignee")
    val assignee: AssigneeDto? = AssigneeDto(),
    @SerialName("assignmentStatus")
    val assignmentStatus: String? = null
)