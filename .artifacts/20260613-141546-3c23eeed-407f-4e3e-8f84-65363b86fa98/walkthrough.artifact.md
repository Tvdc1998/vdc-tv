# Subtitle Refresh Fix Walkthrough

This task addresses the issue where newly downloaded subtitles do not appear in the selection menu until the player is restarted.

## Changes

### 1. Fix for MPVPlayer Crash
Implemented the missing `replaceMediaItems` method in [MPVPlayer.kt](file:///D:/build/findroid-1.0.2/player/local/src/main/java/dev/jdtech/jellyfin/player/local/mpv/MPVPlayer.kt). Previously, calling this method (which `PlayerViewModel` does to refresh the track list) triggered a `TODO` crash, forcing a player restart. The new implementation updates the internal list and notifies listeners, allowing live updates.

### 2. Live Subtitle Refresh Logic
Updated `addSubtitle` in [PlayerViewModel.kt](file:///D:/build/findroid-1.0.2/player/local/src/main/java/dev/jdtech/jellyfin/player/local/presentation/PlayerViewModel.kt) to handle both players correctly:
- **MPVPlayer**: Now uses `subAdd` for immediate injection into the running MPV engine. It then calls `replaceMediaItem` to update the Media3 state *without* triggering a file reload, ensuring the selection menu shows the new subtitle instantly without interrupting playback.
- **ExoPlayer**: Since ExoPlayer requires a reload to see new external subtitle configurations, it continues to use the `replaceMediaItem` + `seekTo` + `play` flow to refresh the tracks while preserving the user's position.

### 3. Improved User Feedback
- Added a new localized string `subtitle_downloaded` ("Subtitle downloaded" / "Ondertiteling gedownload") to [strings.xml](file:///D:/build/findroid-1.0.2/core/src/main/res/values/strings.xml).
- Updated the success toast in [SubtitleDownloadBottomSheet.kt](file:///D:/build/findroid-1.0.2/app/phone/src/main/java/dev/jdtech/jellyfin/presentation/player/SubtitleDownloadBottomSheet.kt) and [SubtitleDownloadDialog.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/ui/dialogs/SubtitleDownloadDialog.kt) to use this new string instead of the generic "Download subtitles".

## Verification Summary

### Static Analysis
- Verified that `MPVPlayer` now overrides `replaceMediaItems` correctly.
- Verified that `PlayerViewModel` branches logic based on the player type.
- Verified that strings are correctly defined and used.

### Build Verification
- Successfully built `:player:local:assembleDebug`.
- Encountered temporary file locking issues for the full app build, but verified the logic in the affected files via `analyze_file`, which confirmed no syntax or structural errors in the new implementations.
