# ClipStack

**Open-source white-label Android template** for multi-vendor booking apps (barbers, salons, spas).

Two demo brands ship from one codebase:

| App | Package | Look | Data stack |
|-----|---------|------|------------|
| **Apex Cuts** | `dev.clipstack.apex` | Dark barbershop | REST (mock Server A) + **SQLDelight** cache |
| **Bloom Studio** | `dev.clipstack.bloom` | Light salon | **Firebase-style** remote (fake Firestore, clone-and-run) |

Shared: Clean Architecture, SOLID ports, **Koin**, Compose, MVVM + MVI.

> No client brand names. Safe to fork for your next white-label product.

## Docs

| Doc | What it covers |
|-----|----------------|
| [docs/GUIDE.md](docs/GUIDE.md) | Start here — architecture, how to add a brand, UI segregation |
| [docs/MODULES.md](docs/MODULES.md) | What each Gradle module is responsible for |

## Quick start

```bash
./gradlew :brands:apex:assembleDebug
./gradlew :brands:bloom:assembleDebug
```

Open either app in Android Studio (two run configurations).

## Why this sells as a product

- Clients often need **same features, different skin + backend**
- Template shows the seams: theme module, Koin data bindings, shared feature UI
- Swap fake REST / fake Firestore for real APIs without rewriting domain or screens

## License

MIT — see [LICENSE](LICENSE).
