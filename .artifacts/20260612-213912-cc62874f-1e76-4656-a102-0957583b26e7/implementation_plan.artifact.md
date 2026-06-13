# Fix User Profile Pictures in Switch User Screen

The user reported that profile pictures disappear for users who have already logged in on the "Switch User" screen. The `primaryImageTag` is not being saved in the local database when a user authenticates.

## User Review Required

- None at this stage.

## Proposed Changes

### [Data Layer]

#### [SetupRepositoryImpl.kt](file:///D:/build/findroid-1.0.2/setup/src/main/java/dev/jdtech/jellyfin/setup/data/SetupRepositoryImpl.kt)

- Update `saveAuthenticationResult` to include `primaryImageTag` from the authentication response when creating the `User` object.

```kotlin
    private fun saveAuthenticationResult(authenticationResult: AuthenticationResult) {
        val user =
            User(
                id = authenticationResult.user!!.id,
                name = authenticationResult.user!!.name!!,
                serverId = authenticationResult.serverId!!,
                accessToken = authenticationResult.accessToken!!,
                primaryImageTag = authenticationResult.user!!.primaryImageTag // ADDED
            )

        database.insertUser(user)
        // ...
    }
```

## Verification Plan

### Automated Tests
- None specific.

### Manual Verification
- Log out of the current user.
- Go to the "Switch User" screen.
- Verify that the profile picture for the previously logged-in user is visible.
- Log in and out to ensure the profile picture persists.
