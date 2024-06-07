package com.caresaas.caresaasmobiletask.core.domain.repository

import com.caresaas.caresaasmobiletask.core.domain.model.Task

interface TasksRepository {
    suspend fun getAllAssignedTasks(): List<Task>
}