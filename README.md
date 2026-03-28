# osrs-prayer-helpper

RuneLite plugin repository for a minimal Prayer Helper feature.

## Current feature scope

This plugin only provides a low-prayer warning:
- It listens to the Prayer skill value.
- It shows an overlay warning when prayer is at or below a configured threshold.

Out of scope:
- no boss-specific behavior
- no animation-based recommendations
- no automatic actions or input simulation

## Build

```bash
./gradlew clean build
```

## Tests

```bash
./gradlew test
```

## Local run

```bash
./gradlew runRuneLite
```

## Compliance notes

The plugin is intentionally narrow to keep behavior easy to review.
It only reads prayer stat events and renders a warning overlay.
It does not interact with menu actions, input events, or any automation flow.

## Reviewer-facing PR description (for next submission)

Title:
Add osrs-prayer-helpper (minimal low-prayer warning overlay)

Body:
This submission intentionally contains one minimal feature:
- show an overlay warning when Prayer points are at or below a configurable threshold.

Safety/compliance notes:
- no boss logic
- no animation parsing
- no prayer switching recommendations
- no input automation or click simulation

Repository:
https://github.com/jehkis/osrs-prayer-helpper.git
