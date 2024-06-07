package com.caresaas.caresaasmobiletask.core.data.remote.mapper

import com.caresaas.caresaasmobiletask.core.data.remote.model.TaskDto
import com.caresaas.caresaasmobiletask.core.domain.model.Task

fun TaskDto.toTask() =
    Task(
        taskId = taskId,
        taskType = taskType,
        timeOfDay = timeOfDay,
        taskGroup = taskGroup,
        action = action,
        order = order,
        priority = priority,
        taskDetailRef = taskDetailRef,
        hourOfDay = hourOfDay,
        supportLevel = supportLevel,
        taskStartedOn = taskStartedOn,
        taskEndedOn = taskEndedOn,
        taskDate = taskDate,
        workStatus = workStatus,
        isAssigned = isAssigned,
        supportPersonnel = supportPersonnel,
        noSupportPersonnel = noSupportPersonnel,
        userId = userId,
        taskScheduleId = taskScheduleId,
        taskAssignments = taskAssignments,
    )
