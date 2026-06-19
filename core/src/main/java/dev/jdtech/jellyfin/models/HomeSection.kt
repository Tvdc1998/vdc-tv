<<<<<<< HEAD
package com.vdc.tv.models
=======
package dev.jdtech.jellyfin.models
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import java.util.UUID

data class HomeSection(val id: UUID, val name: UiText, var items: List<FindroidItem>)
