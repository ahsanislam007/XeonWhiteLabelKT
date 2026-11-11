# Module responsibilities

```text
ClipStack
├── build-logic/          Gradle convention plugins (versions live once)
├── core/
│   ├── domain/           Pure Kotlin models, ports, use cases
│   ├── database/         SQLDelight schema + local cache API
│   ├── data-common/      Shared data adapters (in-memory cart)
│   ├── data-rest/        Server A style REST + cache repository (Apex)
│   ├── data-firebase/    Firebase-style repository (Bloom)
│   └── analytics/        Analytics port + NoOp / logging sinks
├── ui/common/            Shared Compose widgets (no brand colors)
├── feature/catalog/      Catalog MVI + shared screen
└── brands/
    ├── apex/             Apex Cuts application (dark + REST)
    └── bloom/            Bloom Studio application (light + Firebase-fake)
```

## `:core:domain`

**Owns:** `ServiceItem`, `Cart`, repository **interfaces**, use cases.  
**Must not depend on:** Android, Compose, OkHttp, SQLDelight, Firebase.  
**Why:** Business rules stay portable and testable.

## `:core:database`

**Owns:** SQLDelight `ServiceCache` table + `ServiceCacheLocalDataSource`.  
**Used by:** `:core:data-rest` (Apex).  
**Not used by:** Bloom (on purpose — different stack).

## `:core:data-common`

**Owns:** `InMemoryCartRepository` (demo cart).  
**Replace later** with remote cart / SQLDelight cart when productizing.

## `:core:data-rest`

**Owns:** Mock REST API, DTOs, `RestServiceMapper`, `RestCatalogRepository`.  
**Responsibility:** Talk to Server A shape + write through to SQLDelight.

## `:core:data-firebase`

**Owns:** Fake Firestore API, doc DTOs, `FirestoreServiceMapper`, `FirebaseCatalogRepository`.  
**Responsibility:** Show a second backend style without Google project setup.

## `:core:analytics`

**Owns:** `Analytics` interface, `NoOpAnalytics`, `LoggingAnalytics`.  
**Default for OSS:** logging / no-op — add paid SDKs only behind this port.

## `:ui:common`

**Owns:** Reusable composables (`ServiceCard`, `CartBar`) that read **MaterialTheme** tokens.  
**Does not own:** Brand color schemes (those live in brand modules).

## `:feature:catalog`

**Owns:** `CatalogIntent` / `CatalogUiState` / `CatalogUiEffect`, `CatalogViewModel`, `CatalogScreen`, feature Koin module.  
**Depends on:** domain ports + ui common.  
**Does not bind:** which `CatalogRepository` implementation — brands do that.

## `:brands:apex`

**Owns:** `applicationId`, `ApexTheme`, Apex Koin graph (REST + SQLDelight), launcher activity.  
**Product story:** barbershop booking skin.

## `:brands:bloom`

**Owns:** `applicationId`, `BloomTheme`, Bloom Koin graph (Firebase-fake), launcher activity.  
**Product story:** salon booking skin.

## `:build-logic`

**Owns:** Convention plugins so app/library modules don’t hardcode AGP/Kotlin/Compose versions.
