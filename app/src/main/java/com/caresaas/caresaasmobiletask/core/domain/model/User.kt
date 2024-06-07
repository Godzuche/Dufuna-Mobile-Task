package com.caresaas.caresaasmobiletask.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val sub: String,
    val isEmailVerified: Boolean,
    val realmAccessRoles: List<String>,
    val name: String,
    val organization: String,
    val groups: List<String>,
    val preferredUsername: String,
    val givenName: String,
    val id: String,
    val familyName: String,
    val email: String,
    val lastRole: String,
    val careHome: String?,
)