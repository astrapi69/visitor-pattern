/**
 * The MIT License
 *
 * Copyright (C) 2022 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.design.pattern.visitor;

/**
 * The interface {@link GenericVisitor} must be implemented by all classes that want to be visitor
 * objects and provide a custom algorithm. This interface is restrictive for the visitor and the
 * acceptable objects. If this level of restriction is not required, then use the less restrictive
 * {@link Visitor} interface. This interface is the counterpart of the {@link GenericAcceptable}
 * interface
 *
 * @param <VISITOR>
 *            the generic type of the visitor
 * @param <ACCEPTABLE>
 *            the generic type of the object to visit, also referred to as 'visitable' or
 *            'acceptable'
 */
public interface GenericVisitor<VISITOR extends GenericVisitor<VISITOR, ACCEPTABLE>, ACCEPTABLE extends GenericAcceptable<VISITOR, ACCEPTABLE>>
{

	/**
	 * Visits the given acceptable object
	 *
	 * @param visitable
	 *            the acceptable object to visit
	 */
	void visit(final ACCEPTABLE visitable);
}
