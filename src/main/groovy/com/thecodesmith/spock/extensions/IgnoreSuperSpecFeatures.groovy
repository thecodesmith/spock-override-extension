package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Indicates that the specified feature methods from the superclass
 * (which inherits from {@code spock.lang.Specification}) should not be run.
 * <p>
 * To override a specific feature method (rather than ignoring it),
 * use {@link OverrideSuperSpec}.
 *
 * @see OverrideSuperSpec
 *
 * @author Brian Stewart
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtensionAnnotation(IgnoreSuperSpecFeaturesExtension)
@interface IgnoreSuperSpecFeatures {
    String[] value()
}