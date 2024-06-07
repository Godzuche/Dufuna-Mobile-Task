package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("sub")
    val sub: String,
    @SerialName("email_verified")
    val isEmailVerified: Boolean,
    @SerialName("realm_access")
    val realmAccess: RealAccessDto,
    @SerialName("organization")
    val organization: String,
    @SerialName("name")
    val name: String,
    @SerialName("groups")
    val groups: List<String>,
    @SerialName("preferred_username")
    val preferredUsername: String,
    @SerialName("given_name")
    val givenName: String,
    @SerialName("family_name")
    val familyName: String,
    @SerialName("userId")
    val id: String,
    @SerialName("email")
    val email: String,
    @SerialName("lastRole")
    val lastRole: String,
    @SerialName("careHome")
    val careHome: String? = null,
)