package com.thecodesmith.spock.extensions

import spock.lang.Specification

@IgnoreSuperSpecFeatures(['ignore me'])
class DerivedSpec extends BaseSpec {

    def 'derived feature'() {
        expect: true
    }

    @OverrideSuperSpec
    def 'override me'() {
        expect: true
    }
}

@IgnoreSuperSpecFeatures('something bogus')
class NormalSpec extends Specification {

    def 'normal feature'() {
        expect: true
    }
}

abstract class BaseSpec extends Specification {

    def 'base feature'() {
        expect: true
    }

    def 'override me'() {
        expect: false
    }

    def 'ignore me'() {
        expect: false
    }
}
