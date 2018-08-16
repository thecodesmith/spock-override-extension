package com.thecodesmith.spock.extensions

import spock.lang.Shared
import spock.lang.Specification

/**
 * This is the superclass from which we will inherit and selectively
 * ignore or override its feature methods. Note that this also works
 * with non-abstract classes.
 *
 * See the test classes below for the actual example usage of the annotations.
 */
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

    def 'and me'() {
        expect: false
    }
}

/**
 * This is an example of inheriting from another Spec (which itself
 * extends {@code spock.lang.Specification} and selectively ignoring
 * or overriding some of its feature methods. Note that the feature
 * methods from {@code BaseSpec} are still run, except for the ignored
 * or overridden features.
 */
@IgnoreSuperSpecFeatures(['ignore me', 'and me'])
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

    @OverrideSuperSpec
    def 'override non-existent feature works but prints warning'() {
        expect: true
    }
}

/**
 * This class just tests that warnings are printed when applying these
 * annotations to a class that inherits directly from {@code spock.lang.Specification}.
 * All the feature methods in this class still run as normal.
 * There should be no reason to do this in the real world.
 */
@IgnoreSuperSpecFeatures('non-existent feature')
class NormalSpec extends Specification {

    def 'normal feature'() {
        expect: true
    }

    @OverrideSuperSpec
    def 'not an actual override works but prints warning'() {
        expect: true
    }
}

/**
 * This class tests that an empty array of feature method names
 * prints a warning, but still runs the features in this class.
 * There should be no reason to do this in the real world.
 */
@IgnoreSuperSpecFeatures([])
class SubSpec extends NormalSpec {

    def 'sub feature'() {
        expect: true
    }
}

