#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

if command -v gradle >/dev/null 2>&1; then
  exec gradle runRuneLite
elif [[ -x "./gradlew" ]]; then
  exec ./gradlew runRuneLite
else
  echo "Gradle not found. Install gradle or use a compatible wrapper." >&2
  exit 1
fi
