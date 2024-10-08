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
package io.github.astrapi69.design.pattern.visitor.example.first;

/**
 * The Class MenuItem.
 */
public class MenuItem implements MenuAcceptableObject
{

	/** The name. */
	private final String name;

	/** The action command. */
	private final String actionCommand;

	/**
	 * Instantiates a new menu item.
	 *
	 * @param name
	 *            the name
	 * @param actionCommand
	 *            the action command
	 */
	public MenuItem(final String name, final String actionCommand)
	{
		super();
		this.name = name;
		this.actionCommand = actionCommand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(final MenuVisitor visitor)
	{
		visitor.visit(this);
	}

	/**
	 * Gets the action command.
	 *
	 * @return the action command
	 */
	public String getActionCommand()
	{
		return actionCommand;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName()
	{
		return name;
	}

}
