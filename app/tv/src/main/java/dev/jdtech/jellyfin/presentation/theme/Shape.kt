<<<<<<< HEAD
package com.vdc.tv.presentation.theme
=======
package dev.jdtech.jellyfin.presentation.theme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Shapes as ShapesTv

val shapes = Shapes(extraSmall = RoundedCornerShape(10.dp), small = RoundedCornerShape(10.dp))

val shapesTv = ShapesTv(extraSmall = shapes.extraSmall, small = shapes.small)
