package com.thecodesmith.spock.extensions

import spock.lang.Shared
import spock.lang.Specification

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

@IgnoreSuperSpecFeatures('non-existent feature')
class NormalSpec extends Specification {

    def 'normal feature'() {
        expect: true
    }
}

@IgnoreSuperSpecFeatures([])
class SubSpec extends NormalSpec {

    def 'sub feature'() {
        expect: true
    }
}

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
