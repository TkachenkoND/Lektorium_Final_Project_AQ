package com.example.autoquest.domain.models

import android.net.Uri

data class GoogleUserData(
    val displayName: String?,
    val photoUrl: Uri?,
    val tenantId: String?
)
