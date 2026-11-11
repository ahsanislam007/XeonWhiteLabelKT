# Remote theme contract

Server / CMS / BFF should return a payload that maps to `RemoteThemePayload`.

## Example JSON

```json
{
  "brandId": "apex",
  "displayName": "Apex Cuts",
  "isDark": true,
  "colors": {
    "primary": "#F0C27A",
    "onPrimary": "#1A1208",
    "primaryContainer": "#6D4C2F",
    "onPrimaryContainer": "#FFF0D4",
    "secondary": "#BCAAA4",
    "onSecondary": "#1A1208",
    "secondaryContainer": "#4E342E",
    "onSecondaryContainer": "#EFEBE9",
    "tertiary": "#80CBC4",
    "onTertiary": "#00332E",
    "background": "#0E0E0E",
    "onBackground": "#F5F0E8",
    "surface": "#1A1A1A",
    "onSurface": "#F5F0E8",
    "surfaceVariant": "#2E2A26",
    "onSurfaceVariant": "#D7CCC8",
    "outline": "#A1887F"
  },
  "shapes": {
    "smallDp": 8,
    "mediumDp": 14,
    "largeDp": 28
  },
  "typography": {
    "displayFamily": "serif",
    "bodyFamily": "sans",
    "scale": "1.0"
  }
}
```

## Rules

1. App always starts from a **local** bundled theme (offline).
2. Remote refresh **overrides** when the call succeeds.
3. Failed remote calls leave the last good theme in place.
4. Hex colors: `#RRGGBB` or `#AARRGGBB`.
5. `displayFamily` / `bodyFamily`: `sans` | `serif` | `rounded` | `mono`.

Wire real endpoints by replacing `MockRestThemeApi` or `FakeFirestoreThemeApi` — keep `HybridThemeRepository` and the Material mapper.
