package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo

class OverrideSuperSpecExtension extends AbstractAnnotationDrivenExtension<OverrideSuperSpec> {

    @Override
    void visitFeatureAnnotation(OverrideSuperSpec annotation, FeatureInfo feature) {
        if (feature.spec.isTopSpec) {
            System.err.println "[$feature.spec.name] WARNING: Annotation '@OverrideSuperSpec' is present " +
                "but $feature.spec.name does not inherit from another Specification class"
            return
        }

        def ignored = feature.spec.superSpec.features.find { it.name == feature.name }

        if (!ignored) {
            System.err.println "[$feature.spec.name] WARNING: Annotation '@OverrideSuperSpec' is present " +
                    "but no method exists named '$feature.name' in parent class $feature.spec.superSpec.name"
            return
        }

        ignored.setExcluded(true)
        println "[$feature.spec.name] Overriding feature from " +
                "parent class $feature.spec.superSpec.name: $feature.name"
    }
}
