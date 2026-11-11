# ClipStack

**White-label Android template for multi-brand booking apps**  
Barbers · salons · spas · any service business that needs *one codebase, many skins*.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-7F52FF?logo=kotlin&logoColor=white)](#)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white)](#)
[![Architecture](https://img.shields.io/badge/Clean%20Architecture-SOLID-222)](#)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

---

## Why ClipStack exists

Most “multi-brand” Android projects become a mess of copy-pasted screens, hardcoded colors, and one giant networking layer.

ClipStack shows a **production-shaped alternative**:

| Pain | What ClipStack demonstrates |
|------|-----------------------------|
| New brand = rewrite the app | Add a `:brands:*` module; reuse features |
| Design locked in code | **Material 3 design system** with **local + server-driven** themes |
| Backend differences break UI | Shared domain ports; swap REST / Firebase adapters |
| Offline after first load | SQLDelight cache on the REST brand |
| Hard to onboard engineers | Module map + guide written for humans |

Fork it for a client MVP, a portfolio piece, or the skeleton of a sellable white-label product.

---

## What’s ready-made

### Two installable demo brands

| App | Package | Look | Catalog data | Theme |
|-----|---------|------|--------------|-------|
| **Apex Cuts** | `dev.clipstack.apex` | Dark barbershop | Mock **REST** + **SQLDelight** cache | Local M3 → upgrades from mock theme API |
| **Bloom Studio** | `dev.clipstack.bloom` | Light salon | Fake **Firestore** | Local M3 → upgrades from fake theme doc |

Same catalog feature. Different skin, copy, and data stack.

### Architecture (ready)

- **Clean Architecture** — UI → ViewModel → UseCase → ports ← data adapters  
- **SOLID** — small repository interfaces; brand modules only bind implementations  
- **MVVM + MVI** — `Intent` / `UiState` / `UiEffect` on the catalog screen  
- **Koin** DI — one graph per brand app  
- **Dedicated mappers** — DTOs / theme payloads never map inside screens  

### Design system (Material 3)

- Full **ColorScheme** (light/dark), **Typography**, **Shapes**, **spacing** tokens  
- `ClipStackTheme(config)` drives the whole UI from a single `ThemeConfig`  
- **Local-first**: bundled tokens paint instantly (offline)  
- **Server-driven**: remote payload can rebrand without shipping a new UI layer  
- Chip in the app bar shows `Local theme` → `Server theme` after refresh  

### Modules you get out of the box

```text
:core:domain          Models, ports, use cases (pure Kotlin)
:core:database        SQLDelight service cache
:core:data-common     Cart + hybrid theme repository
:core:data-rest       REST catalog + REST theme API (Apex)
:core:data-firebase   Firestore-style catalog + theme (Bloom)
:core:analytics       Analytics port (logging / NoOp)
:ui:common            M3 design system + shared components
:feature:catalog      Catalog MVI + screen (brand-agnostic)
:brands:apex          Apex Cuts application
:brands:bloom         Bloom Studio application
:build-logic          Gradle convention plugins
```

### Docs

| Doc | Purpose |
|-----|---------|
| [docs/GUIDE.md](docs/GUIDE.md) | How themes, UI segregation, and new brands work |
| [docs/MODULES.md](docs/MODULES.md) | Responsibility of each module |
| [docs/theme-contract.md](docs/theme-contract.md) | Remote theme JSON shape for CMS / BFF |

---

## Quick start

```bash
git clone https://github.com/ahsanislam007/ClipStack.git
cd ClipStack

./gradlew :brands:apex:installDebug
./gradlew :brands:bloom:installDebug
```

Open either app → wait ~0.5s → theme chip flips to **Server theme** (remote tokens applied).  
Tap the chip anytime to refresh.

---

## How the theme system helps you

```text
Bundled ThemeConfig (LOCAL)
        │
        ▼
 HybridThemeRepository ──refresh──► Remote payload (REST or Firestore-shaped)
        │
        ▼
 ThemeConfigToMaterialMapper  →  Material 3 ColorScheme / Type / Shapes
        │
        ▼
 ClipStackTheme { CatalogScreen … }
```

**Product win:** marketing can change primary color, corner radius, and type scale from a CMS.  
**Engineering win:** features stay dumb about brands; they only read `MaterialTheme`.

---

## How to add a third brand

1. Copy `:brands:apex` → `:brands:nova`  
2. Change `applicationId`, strings, bundled theme  
3. Bind `CatalogRepository` + `ThemeRepository` in Koin  
4. `include(":brands:nova")` in `settings.gradle.kts`  

You should **not** fork `CatalogScreen`.

---

## What this is (and isn’t)

**Is:** a serious template for white-label booking / ordering shells.  
**Isn’t:** a full payments/POS product (checkout is stubbed on purpose).  
**Isn’t:** tied to any private client codebase.

---

## License

MIT © Ahsan Islam — see [LICENSE](LICENSE).
