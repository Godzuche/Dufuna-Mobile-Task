package com.caresaas.caresaasmobiletask.core.data.remote.mapper

import com.caresaas.caresaasmobiletask.core.data.remote.model.UserDto
import com.caresaas.caresaasmobiletask.core.data.remote.model.UserTokenDto
import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserToken

fun UserDto.toUserEntity() = User(
    sub = sub,
    isEmailVerified = isEmailVerified,
    realmAccessRoles = realmAccess.roles,
    name = name,
    organization = organization,
    groups = groups,
    preferredUsername = preferredUsername,
    givenName = givenName,
    id = id,
    familyName = familyName,
    email = email,
    lastRole = lastRole,
    careHome = careHome,
)

fun UserTokenDto.toUserToken() =
    UserToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
    )