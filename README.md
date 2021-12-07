# Advent Of Code 2021

https://adventofcode.com/2021/

## Tech
- Kotlin 1.6
- Gradle 7
- JUnit 5

## Requirements
- JDK 12

# How to run a day program?

Simply execute following command:

```shell
./gradlew run -Pday=42
```

# How to initialize a new day?

Simply execute following command:

```shell
./gradlew newDay -Pday=42
```

Fails if provided day is already initialized.

# How to run tests?

Simply execute one of the following commands:

### All tests
```shell
./gradlew test
```

### Single day tests
```shell
./gradlew test --tests com.adventofcode.day1.Day1Test
```