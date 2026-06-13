# Android TV Feature Parity (Round 7)

The previous changes (Branding, Subtitle Refresh, Thumbnails) were primarily focused on the Phone app. This round ensures they work correctly on Android TV as well.

## Proposed Changes

### [TV UI Components]

#### [ItemPoster.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/ui/components/ItemPoster.kt)

- Sync logic with Phone version:
    - Add `viewType: LibraryViewType` parameter.
    - Prioritize `item.images.thumb` when `viewType` is `LibraryViewType.THUMBNAIL`.
    - Ensure direction-based overrides (like Backdrop for Movies in Horizontal) don't overwrite the Thumbnail in Thumbnail view.

#### [ItemCard.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/ui/components/ItemCard.kt)

- Add `viewType: LibraryViewType` parameter.
- Pass `viewType` down to `ItemPoster`.
- Adjust `width` and `direction` logic to support `THUMBNAIL` view (260dp width, HORIZONTAL direction).

### [TV Screens]

#### [LibraryScreen.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/presentation/film/LibraryScreen.kt)

- Add a "View Type" button to the header (similar to Phone) to allow switching between Poster and Thumbnail.
- Adjust `GridCells` columns based on `viewType` (e.g., 5 for Poster, 3 for Thumbnail).
- Pass `state.viewType` to `ItemCard`.

#### [PlayerScreen.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/ui/PlayerScreen.kt)

- Verify that `SubtitleDownloadDialog` uses the new `subtitle_downloaded` string (already done in Round 1, but I'll double check the TV implementation).

### [Branding]

#### [MainScreen.kt](file:///D:/build/findroid-1.0.2/app/tv/src/main/java/dev/jdtech/jellyfin/ui/MainScreen.kt)

- The TV app uses `ic_logo_source` in the header. I will verify if this needs replacing with the new `vdcstudios_banner` or if it's already using a custom logo.

## Verification Plan

### Manual Verification
- **Android TV Build**: `./gradlew :app:tv:assembleLibreDebug`
- **Branding**: Open app on TV, verify welcome text and logos.
- **Subtitles**: Download a subtitle while playing on TV, verify it appears in the selection list.
- **Library**: Switch to "Thumbnail" view on TV, verify images are correct thumbnails.
