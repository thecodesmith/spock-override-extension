package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.SpecInfo

class IgnoreSuperSpecFeaturesExtension extends AbstractAnnotationDrivenExtension<IgnoreSuperSpecFeatures> {

    @Override
    void visitSpecAnnotation(IgnoreSuperSpecFeatures annotation, SpecInfo spec) {
        def ignored = annotation.value()

        if (!ignored) {
            System.err.println "[$spec.name] WARNING: Annotation '@IgnoreSuperSpecFeatures' " +
                    "is present but does not name any feature methods to ignore"
            return
        }

        if (spec.isTopSpec) {
            System.err.println "[$spec.name] WARNING: Annotation '@IgnoreSuperSpecFeatures' is present " +
                    "but $spec.name does not inherit from another Specification class"
            return
        }

        def ignoredFeatures = spec.superSpec.features
                .findAll { it.name in ignored }
                .each { it.setSkipped(true) }
        println "[$spec.name] Ignoring features from parent class $spec.superSpec.name: $ignoredFeatures.name"
    }
}
