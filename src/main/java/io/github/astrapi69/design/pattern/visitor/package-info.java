/**
 * Provides a set of interfaces for implementing the Visitor design pattern in a generic way.
 * <p>
 * The Visitor design pattern allows you to add further operations to objects without having to
 * modify them. This package includes both a basic implementation
 * ({@link io.github.astrapi69.design.pattern.visitor.Visitor},
 * {@link io.github.astrapi69.design.pattern.visitor.Acceptable}) and a more restrictive generic
 * implementation ({@link io.github.astrapi69.design.pattern.visitor.GenericVisitor},
 * {@link io.github.astrapi69.design.pattern.visitor.GenericAcceptable}), which enforces type safety
 * by using generics.
 * </p>
 *
 * <p>
 * The interfaces in this package are:
 * <ul>
 * <li>{@link io.github.astrapi69.design.pattern.visitor.Acceptable} - To be implemented by all
 * classes that can accept a visitor.</li>
 * <li>{@link io.github.astrapi69.design.pattern.visitor.Visitor} - To be implemented by all classes
 * that represent a visitor and define a custom algorithm.</li>
 * <li>{@link io.github.astrapi69.design.pattern.visitor.GenericAcceptable} - A more restrictive
 * version of {@link io.github.astrapi69.design.pattern.visitor.Acceptable} using generics for type
 * safety.</li>
 * <li>{@link io.github.astrapi69.design.pattern.visitor.GenericVisitor} - A more restrictive
 * version of {@link io.github.astrapi69.design.pattern.visitor.Visitor} using generics for type
 * safety.</li>
 * </ul>
 * </p>
 *
 * <p>
 * These interfaces provide a flexible way to implement the Visitor pattern in scenarios where
 * objects need to be visited by various visitors, potentially applying different operations,
 * without modifying the objects themselves.
 * </p>
 */
package io.github.astrapi69.design.pattern.visitor;
