package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.SpecInfo

class IgnoreSuperSpecFeaturesExtension extends AbstractAnnotationDrivenExtension<IgnoreSuperSpecFeatures> {

    @Override
    void visitSpecAnnotation(IgnoreSuperSpecFeatures annotation, SpecInfo spec) {
        def ignored = annotation.value()

        if (!spec.isTopSpec && ignored) {
            def ignoredFeatures = spec.superSpec.features
                    .findAll { it.name in ignored }
                    .each { it.setSkipped(true) }
            println "Ignoring features from $spec.superSpec.name: $ignoredFeatures.name"
        }
    }
}
