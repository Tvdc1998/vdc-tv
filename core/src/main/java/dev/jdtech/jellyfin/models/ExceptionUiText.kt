package com.vdc.tv.models

data class ExceptionUiText(val uiText: UiText) : Exception()

data class ExceptionUiTexts(val uiTexts: Collection<UiText>) : Exception()
