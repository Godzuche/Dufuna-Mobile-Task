package com.caresaas.caresaasmobiletask.core.designsystem.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.caresaas.caresaasmobiletask.R

/**
 * CareSaaS icons. Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object CareSaaSIcons {
    val CareSaaSLogo = R.drawable.caresaas_logo
    val ArrowCircleUp = R.drawable.arrow_alt_circle_up
    val Bed = R.drawable.bed_solid
    val Clock = R.drawable.clock_solid
    val DoorOpen = R.drawable.door_open_solid
    val Home = R.drawable.home_solid
    val Search = R.drawable.search_solid
    val User = R.drawable.user
    val ProfilePic = R.drawable.profile_pic
    val Bell = R.drawable.bell_solid
}

sealed interface CareSaaSIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : CareSaaSIcon
    data class DrawableResourceIcon(val resourceId: Int) : CareSaaSIcon
}