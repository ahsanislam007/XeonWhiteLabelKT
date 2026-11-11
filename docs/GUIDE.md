# XeonWhiteLabelKT guide (easy read)

## 1. What you get

One Android repo → **two installable apps**:

1. **Apex Cuts** — men’s barbershop vibe, loads services from a **REST-shaped** API, caches with **SQLDelight**.
2. **Bloom Studio** — salon vibe, loads services from a **Firebase/Firestore-shaped** source (fake so you don’t need a Google project).

Both apps share the same booking catalog feature. Only **theme config + DI bindings** change.

```text
UI (Compose + WhiteLabelTheme / Material 3)
  → ViewModel (MVI intents/state/effects)
    → UseCases
      → Repository ports (interfaces in :core:domain)
        → Brand adapters (:core:data-rest OR :core:data-firebase)
```

That is **Clean Architecture** + **Dependency Inversion (SOLID)**.

---

## 2. Run the demos

```bash
./gradlew :brands:apex:installDebug
./gradlew :brands:bloom:installDebug
```

On the catalog screen:

- Subtitle = data source (`REST + SQLDelight` or `Firebase-style`)
- Top-right chip = **Local theme** then **Server theme** after remote refresh

Add services → cart bar appears → **Book** is a stub (wire your checkout later).

---

## 3. Material 3 design system (local + server)

1. Bundled `ThemeConfig` paints immediately (`ThemeSource.LOCAL`).
2. `HybridThemeRepository.refreshFromRemote()` loads CMS/API tokens.
3. `ThemeConfigToMaterialMapper` builds M3 `ColorScheme` / `Typography` / `Shapes`.
4. `WhiteLabelTheme(config) { … }` applies them app-wide.

See [theme-contract.md](theme-contract.md) for the JSON shape.

Tap the theme chip to force another remote refresh.

---

## 4. How UI is segregated (detailed)

### Shared vs brand-owned

| Layer | Shared? | Example |
|-------|---------|---------|
| Screen structure | Yes | `CatalogScreen` in `:feature:catalog` |
| Card chrome | Yes | `ServiceCard` in `:ui:common` |
| M3 tokens | Config-driven | `ThemeConfig` → `WhiteLabelTheme` |
| CTA copy | Per brand arg | `"Book chair"` vs `"Reserve"` |
| Data / theme source | Per brand Koin | REST vs Firestore adapters |

### Pattern

```kotlin
WhiteLabelTheme(config = theme) {
  CatalogScreen(
    viewModel = catalogViewModel,
    title = theme.displayName,
    themeSource = theme.source,
    onRefreshTheme = themeViewModel::refresh,
    addLabel = "Book chair",
  )
}
```

**Do not** fork `CatalogScreen` per brand. Fork **bundled theme + strings + DI**.

If a brand needs a totally different layout (e.g. map-first), create a new screen in that brand module and still call the same use cases.

---

## 5. How data is segregated

Domain only knows:

```kotlin
interface CatalogRepository {
  suspend fun getServices(forceRefresh: Boolean = false): List<ServiceItem>
}
```

| Brand | Binding | Cache |
|-------|---------|-------|
| Apex | `RestCatalogRepository` | SQLDelight (`ServiceCache`) |
| Bloom | `FirebaseCatalogRepository` | none (remote-only demo) |

Mappers stay in dedicated objects (`RestServiceMapper`, `FirestoreServiceMapper`) — never inside DTOs, ViewModels, or Composables.

### Swap fakes for production

**Apex / REST**

1. Replace `MockRestCatalogApi` with OkHttp call to your Server A.
2. Keep `RestServiceMapper` + `RestCatalogRepository`.

**Bloom / Firebase**

1. Replace `FakeFirestoreCatalogApi` with Firestore SDK reads.
2. Keep `FirestoreServiceMapper` + `FirebaseCatalogRepository`.
3. Add `google-services.json` only in `:brands:bloom`.

---

## 6. MVI contract (one screen)

```text
UI --CatalogIntent--> CatalogViewModel --CatalogUiState--> UI
                         |
                         +--CatalogUiEffect--> snackbar / navigation
```

- Composables emit intents only (no business rules).
- ViewModel calls use cases.
- One-shot effects via `Channel`.

---

## 7. Add a third brand in ~10 minutes

1. Copy `brands/apex` → `brands/nova`.
2. Change `applicationId`, theme colors, app name.
3. Pick a data module (reuse REST, Firebase, or add `:core:data-graphql`).
4. In Koin, bind `CatalogRepository` to your adapter.
5. `include(":brands:nova")` in `settings.gradle.kts`.

You should **not** copy feature ViewModels.

---

## 8. SOLID cheat-sheet in this repo

| Principle | Where |
|-----------|--------|
| **S**ingle responsibility | One module / mapper / use case job |
| **O**pen/closed | New brand = new binding, not edit domain |
| **L**iskov | Any `CatalogRepository` works with `GetServicesUseCase` |
| **I**nterface segregation | Small ports (`CatalogRepository`, `CartRepository`) |
| **D**ependency inversion | Domain defines ports; data implements |

---

## 9. What this is not

- Not a full POS / payments product (checkout is stubbed).
- Not a copy of any private client codebase.
- Not requiring Room — **SQLDelight** is the local cache example (Room works the same behind a port if you prefer).

---

## Next upgrades (when selling)

- Real checkout + calendar booking
- Auth per brand
- Maestro smoke per `applicationId`
- Remote feature flags per brand
