# Walkthrough: Fix User Profile Pictures in Switch User Screen

## Goal
The goal was to fix an issue where user profile pictures disappeared on the "Switch User" screen for users who had previously logged in.

## Changes
Updated `SetupRepositoryImpl.kt` to save the `primaryImageTag` from the authentication response into the local database.

```diff
-                accessToken = authenticationResult.accessToken!!,
-            )
+                accessToken = authenticationResult.accessToken!!,
+                primaryImageTag = authenticationResult.user!!.primaryImageTag
+            )
```

## Verification Summary
- The code change correctly maps the `primaryImageTag` from the API response to the `User` entity.
- The `User` model already included the `primaryImageTag` field, so no database migration was needed for the field itself.
- **Manual Verification (Required by User)**:
  - Log out of the current user.
  - Go to the "Switch User" screen.
  - Verify that the profile picture for the previously logged-in user is visible.
  - Log in and out to ensure the profile picture persists.
