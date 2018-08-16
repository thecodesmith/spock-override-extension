package com.thecodesmith.spock.extensions

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Indicates that the target feature method overrides a method from the superclass
 * (which inherits from {@code spock.lang.Specification}). Spock's default behavior
 * is to run both the superclass feature method and subclass feature method.
 * This annotation causes the overridden superclass feature method to not be run.
 * <p>
 * To ignore superclass feature methods (rather than overriding them),
 * use {@link IgnoreSuperSpecFeatures}.
 *
 * @see IgnoreSuperSpecFeatures
 *
 * @author Brian Stewart
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtensionAnnotation(OverrideSuperSpecExtension)
@interface OverrideSuperSpec {

}
