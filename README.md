# osrs-prayer-helpper

RuneLite Prayer Helper -pluginin lahdekoodi.

## Vaatimukset

- Java 17 (JDK)
- Gradle (tai vaihtoehtoisesti gradle-wrapper, jos lisataan myohemmin)

## Build

```bash
gradle clean build
```

## Testit

```bash
gradle test
```

## Aja paikallisesti RuneLitessa

```bash
gradle runRuneLite
```

Tai wrapperilla:

```bash
./gradlew runRuneLite
```

## Huomio

Tama repository kayttaa nyt Gradle-projektirakennetta, mutta lahdekoodi sijaitsee kansiossa `PrayerHelper`.

Jagex Launcher + RuneLite -kaytossa plugin ei tule suoraan paikallisesta kehitysprojektista.
Jotta plugin nakyy pysyvasti Launcherista avatussa RuneLitessa, plugin tulee julkaista Plugin Hubin kautta.
