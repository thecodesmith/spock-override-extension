package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo

class OverrideSuperSpecExtension extends AbstractAnnotationDrivenExtension<OverrideSuperSpec> {

    @Override
    void visitFeatureAnnotation(OverrideSuperSpec annotation, FeatureInfo feature) {
        if (!feature.spec.isTopSpec) {
            def ignore = feature.spec.superSpec.features.find { it.name == feature.name }

            if (ignore) {
                ignore.setExcluded(true)
                println "Overriding feature from parent $feature.spec.superSpec.name: $ignore.name"
            }
        }
    }
}
