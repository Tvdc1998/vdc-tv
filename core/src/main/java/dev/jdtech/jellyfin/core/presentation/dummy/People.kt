<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.FindroidImages
import com.vdc.tv.models.FindroidItemPerson
import com.vdc.tv.models.FindroidItemPersonImage
import com.vdc.tv.models.FindroidPerson
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.FindroidImages
import dev.jdtech.jellyfin.models.FindroidItemPerson
import dev.jdtech.jellyfin.models.FindroidItemPersonImage
import dev.jdtech.jellyfin.models.FindroidPerson
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID
import org.jellyfin.sdk.model.api.PersonKind

val dummyPerson =
    FindroidItemPerson(
        id = UUID.randomUUID(),
        name = "Su Shangqing",
        type = PersonKind.ACTOR,
        role = "Cheng Xiaoshi (voice)",
        image = FindroidItemPersonImage(uri = null, blurHash = null),
    )

val dummyPersonDetail =
    FindroidPerson(
        id = UUID.randomUUID(),
        name = "Rosa Salazar",
        overview =
            "Rosa Bianca Salazar (/ˈsæləzɑːr/; born July 16, 1985) is an American actress. She had roles in the NBC series Parenthood and the FX anthology series American Horror Story: Murder House. She played the title character in the film Alita: Battle Angel. She appeared in The Divergent Series: Insurgent, Maze Runner: The Scorch Trials and Maze Runner: The Death Cure and also appeared in the Netflix films The Kindergarten Teacher and Bird Box. Rosa Bianca Salazar was born on July 16, 1985. She is of Peruvian and French descent. She grew up in Washington, D.C. and nearby Greenbelt, Maryland. Salazar attended Eleanor Roosevelt High School in Greenbelt, and was active in the school theatre program.",
        images = FindroidImages(),
    )
