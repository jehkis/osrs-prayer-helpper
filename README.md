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

## Plugin Hub submission (copy-paste)

### Full template

Plugin name:
Prayer Helper

Repository URL:
https://github.com/jehkis/osrs-prayer-helpper

Support URL:
https://github.com/jehkis/osrs-prayer-helpper/issues

Author:
jehkis

Tags:
prayer,helper,overlay,boss,pvm

Short description:
Prayer Helper provides prayer-switch hints and warning overlays for selected PvM encounters.

Detailed description:
Prayer Helper helps with defensive prayer timing in boss encounters by showing contextual overlay hints based on NPC animations and player state.

Current functionality includes:
- Prayer switch hints for supported encounters
- Warning overlays for low Hitpoints and low Prayer
- Configurable overlay visibility and display behavior
- Lightweight client-side guidance only (no automation or input simulation)

The plugin reads game state and renders overlays locally in RuneLite.
It does not perform actions, clicks, or automated gameplay behavior.

Reviewer note:
This plugin is guidance-only.
It uses RuneLite event subscriptions (animation/stat/game tick events) to show UI overlays and warnings.
No input events are sent and no automation is performed.

### Minimal template

Plugin name:
Prayer Helper

Repository URL:
https://github.com/jehkis/osrs-prayer-helpper

Support URL:
https://github.com/jehkis/osrs-prayer-helpper/issues

Short description:
Prayer Helper shows prayer-switch hints and warning overlays for selected PvM encounters.

Detailed description:
Prayer Helper provides client-side guidance overlays based on boss animations and player stats.
It shows protection prayer hints and low HP/prayer warnings.
No automation or input simulation is performed.

Tags:
prayer,helper,overlay,boss,pvm

Reviewer note:
This plugin is guidance-only and overlay-based.
It reads game state and renders UI hints locally.
It does not automate gameplay or send input events.

### Ultra-short template

Prayer Helper is a client-side overlay plugin that shows prayer-switch hints and low HP/prayer warnings for selected PvM encounters.
No automation, no input simulation, and no gameplay actions are performed.
