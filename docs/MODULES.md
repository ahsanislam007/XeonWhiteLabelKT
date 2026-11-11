# Module responsibilities

```text
XeonWhiteLabelKT
├── build-logic/          Gradle convention plugins (versions live once)
├── core/
│   ├── domain/           Pure Kotlin models, ports, use cases
│   ├── database/         SQLDelight schema + local cache API
│   ├── data-common/      Cart + hybrid theme repository + bundled themes
│   ├── data-rest/        REST catalog + REST theme API (Apex)
│   ├── data-firebase/    Firestore-style catalog + theme (Bloom)
│   └── analytics/        Analytics port + NoOp / logging sinks
├── ui/common/            Material 3 design system + shared components
├── feature/catalog/      Catalog MVI + ThemeViewModel + shared screen
└── brands/
    ├── apex/             Apex Cuts application (REST + hybrid theme)
    └── bloom/            Bloom Studio application (Firebase-fake + hybrid theme)
```


## `:core:domain`

**Owns:** `ServiceItem`, `Cart`, `ThemeConfig`, repository **interfaces**, use cases.  
**Must not depend on:** Android, Compose, OkHttp, SQLDelight, Firebase.  
**Why:** Business rules stay portable and testable.

## `:core:database`

**Owns:** SQLDelight `ServiceCache` table + `ServiceCacheLocalDataSource`.  
**Used by:** `:core:data-rest` (Apex).  
**Not used by:** Bloom (on purpose — different stack).

## `:core:data-common`

**Owns:** `InMemoryCartRepository`, `HybridThemeRepository`, `BundledThemes`, remote theme payload mapper.  
**Replace later** with remote cart / SQLDelight cart when productizing.

## `:core:data-rest`

**Owns:** Mock REST API, DTOs, `RestServiceMapper`, `RestCatalogRepository`, `MockRestThemeApi`.  
**Responsibility:** Talk to Server A shape + write through to SQLDelight; fetch remote theme.

## `:core:data-firebase`

**Owns:** Fake Firestore API, doc DTOs, `FirestoreServiceMapper`, `FirebaseCatalogRepository`.  
**Responsibility:** Show a second backend style without Google project setup.

## `:core:analytics`

**Owns:** `Analytics` interface, `NoOpAnalytics`, `LoggingAnalytics`.  
**Default for OSS:** logging / no-op — add paid SDKs only behind this port.

## `:ui:common`

**Owns:** Material 3 design system (`WhiteLabelTheme`, mappers, spacing) + reusable composables (`ServiceCard`, `CartBar`, `ThemeSourceChip`).  
**Does not own:** Brand-specific hex values (those live in `ThemeConfig` from local/remote).

## `:feature:catalog`

**Owns:** `CatalogIntent` / `CatalogUiState` / `CatalogUiEffect`, `CatalogViewModel`, `ThemeViewModel`, `CatalogScreen`, feature Koin module.  
**Depends on:** domain ports + ui common.  
**Does not bind:** which `CatalogRepository` / `ThemeRepository` implementation — brands do that.

## `:brands:apex`

**Owns:** `applicationId`, Apex Koin graph (REST + SQLDelight + hybrid theme), launcher activity.  
**Product story:** barbershop booking skin.

## `:brands:bloom`

**Owns:** `applicationId`, Bloom Koin graph (Firebase-fake + hybrid theme), launcher activity.  
**Product story:** salon booking skin.

## `:build-logic`

**Owns:** Convention plugins so app/library modules don’t hardcode AGP/Kotlin/Compose versions.
