# Custom Detekt Rule for Composable Functions

This repository contains a custom rule for Detekt, a static code analysis tool for Kotlin. The purpose of this rule is to ensure that all composable functions within a file are compliant with specific conditions to promote better practices while working with Jetpack Compose.

## Rule Logic

The custom rule enforces the following conditions:

1. If a file contains a function annotated with @Composable, it must also contain at least one function annotated with either @Composable or @Preview. If neither is present, an error will be reported.
2. If a @Composable function has the @ReadOnlyComposable annotation, no error will be reported, even if there are no @Preview annotated functions present.

## Project Structure

The project consists of the following modules:

- app: An Android application using Jetpack Compose, which contains examples that demonstrate the possible violations of the custom rule.

- customcomposerules: This module is where the ruleSetProvider and the custom rule itself are defined.

- conventionplugins: This module bridges Detekt with the rules from customcomposerules and configures Detekt accordingly.

## Example

Consider the following example that demonstrates the rule in action:
```kotlin
@Composable
fun MyComposableFunction() {
// This will trigger an error since no @Preview annotated function is present
}

@Composable
fun AnotherComposableFunction() {
// This function is acceptable and does not cause an error
}

// This function satisfies the rule because it has the @ReadOnlyComposable annotation
@ReadOnlyComposable
fun ReadOnlyComposableFunction() {
// Some read-only composable logic
}

```
In the above example, MyComposableFunction will report an error because there are no @Preview or other @Composable functions in the file.


## Contributions

Contributions are welcome! If you have any suggestions or improvements, please feel free to submit a pull request.

---

By adhering to these practices, we can ensure a more robust, maintainable, and consistent usage of composable functions within our Jetpack Compose applications.