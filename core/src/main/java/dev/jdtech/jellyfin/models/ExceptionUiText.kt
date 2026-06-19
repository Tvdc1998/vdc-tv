<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

data class ExceptionUiText(val uiText: UiText) : Exception()

data class ExceptionUiTexts(val uiTexts: Collection<UiText>) : Exception()
