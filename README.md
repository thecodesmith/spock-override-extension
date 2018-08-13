# Spock Override Extension

_This extension enables selective override or ignore of inherited Spock features_

[![Download](https://api.bintray.com/packages/thecodesmith/maven/spock-override-extension/images/download.svg)](https://bintray.com/thecodesmith/maven/spock-override-extension/_latestVersion)
[![Build Status](https://travis-ci.org/thecodesmith/spock-override-extension.svg?branch=master)](https://travis-ci.org/thecodesmith/spock-override-extension)
[![Coveralls Coverage Status](https://coveralls.io/repos/github/thecodesmith/spock-override-extension/badge.svg?branch=master)](https://coveralls.io/github/thecodesmith/spock-override-extension?branch=master)

## Get Started

To use this extension, just add it as a test dependency and use the provided
annotations.

Add the library to your `build.gradle` dependencies:

    testCompile 'com.thecodesmith.spock:spock-override-extension:1.0.0'

These annotations will be available:

- `@IgnoreSuperSpecFeatures` - This class-level annotation takes an array of
  method names (features) from the parent class to ignore
- `@OverrideSuperSpec` - This method-level annotation overrides the parent
  feature method of the same method name

## Use Case

Sometimes when using [Spock](http://spockframework.org) you need to inherit
feature methods from another `Specification` class, but need to override or
ignore a few. This library provides the override/ignore capability through
Spock's extension mechanism.

Here is an example.

```groovy
abstract class BaseSpec extends Specification {

    @Shared String thing

    def 'base feature'() {
        expect: thing != null
    }

    def 'override me'() {
        expect: false
    }

    def 'ignore me'() {
        expect: false
    }
}
```

```groovy
@IgnoreSuperSpecFeatures(['ignore me'])
class DerivedSpec extends BaseSpec {

    def setupSpec() {
        thing = 'foo'
    }

    def 'derived feature'() {
        expect: true
    }

    @OverrideSuperSpec
    def 'override me'() {
        expect: true
    }
}
```

Running `DerivedSpec` results in a green build:
```
√ (passed)  base feature
√ (passed)  derived feature
√ (passed)  override me
o (ignored) ignore me
Tests passed: 3, ignored: 1 of 4 tests
```

## Disclaimer

Most of the time there are better ways to structure tests than using
inheritance, and this mechanism isn't necessary. The specific use case behind
this extension was the need to run common tests across many modules in a
multi-module project. Structuring the tests in this way allows the common tests
to be included as a dependency in other modules, and customized as needed to
fit the needs to the module. So, think twice before using this extension, but
if you need it, you need it!

## Contributing

Pull requests are welcome! If you see a missing feature, create a pull request
and I will work to get it merged into the project.  Reporting issues is a big
help as well.

## License

This library is licensed under the terms of the [Apache License, Version
2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
