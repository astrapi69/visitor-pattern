# Generic Visitor Design Pattern

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/visitor-pattern/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/visitor-pattern/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/visitor-pattern/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/visitor-pattern)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/visitor-pattern.svg?style=flat)](https://github.com/astrapi69/visitor-pattern/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/visitor-pattern/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/visitor-pattern)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/visitor-pattern.svg)](http://www.javadoc.io/doc/io.github.astrapi69/visitor-pattern)
[![MIT License](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/visitor-pattern?branch=develop)](https://hitsofcode.com/github/astrapi69/visitor-pattern/view?branch=develop)

</div>

This repository provides a set of interfaces to implement the Visitor design pattern in a flexible and generic way. The
Visitor design pattern is a behavioral design pattern that allows you to add further operations to objects without
having to modify their structure. This is achieved by separating the algorithm from the object structure on which it
operates.

## Overview

The generic Visitor design pattern is particularly useful when you have a collection of objects with different types,
and you want to perform operations on these objects without modifying their classes. By defining visitor interfaces, you
allow the implementation of new operations simply by creating new visitor classes.

### Key Interfaces

- **`Acceptable<V>`**: This interface should be implemented by all classes that need to accept a visitor. It defines a
  single `accept` method that takes a visitor as a parameter.

- **`Visitor<T>`**: This interface should be implemented by visitor classes that provide a custom algorithm for
  processing elements. It defines a `visit` method that operates on the object passed to it.

- **`GenericAcceptable<VISITOR, ACCEPTABLE>`**: A more restrictive version of `Acceptable`, enforcing type safety
  through generics. This is useful when you need to strictly control the types of visitors and visitable objects.

- **`GenericVisitor<VISITOR, ACCEPTABLE>`**: A more restrictive version of `Visitor`, which works in tandem
  with `GenericAcceptable` to provide type-safe visitor operations.

## Example Usage

### Key Components

- **Menu**: Represents a menu that can contain menu items and sub-menus. Implements the `MenuAcceptableObject` interface
  to accept visitors.
- **MenuItem**: Represents a single menu item with a name and an action command. Implements the `MenuAcceptableObject`
  interface to accept visitors.
- **MenuVisitor**: A specialized visitor interface for visiting `Menu` and `MenuItem` objects. Extends `GenericVisitor`
  to enforce type safety.
- **PrintActionCommandsMenuVisitor**: An implementation of `MenuVisitor` that prints the action commands of
  visited `MenuItem` objects and the names of `Menu` objects.

## Example Usage

The following example demonstrates how to use the Visitor pattern with the provided classes:

### DemonstrateVisitorPattern

This example builds a simple menu structure with sub-menus and menu items, and then uses
the `PrintActionCommandsMenuVisitor` to print out the action commands of all menu items in the structure.

#### Code Example:

```java
public class DemonstrateVisitorPattern {
    public static void main(final String[] args) {
        // The main menu.
        final Menu mainMenu = new Menu("Main", new ArrayList<MenuAcceptableObject>());
        // Sub menu 'new' from main menu.
        final Menu mainMenuNew = new Menu("New", new ArrayList<MenuAcceptableObject>());
        // Sub menuitems from the sub menu 'new'.
        mainMenuNew.getChildren().add(new MenuItem("File", "File action"));
        mainMenuNew.getChildren().add(new MenuItem("Folder", "Folder action"));
        // Sub menuitems from the main menu.
        mainMenu.getChildren().add(mainMenuNew);
        mainMenu.getChildren().add(new MenuItem("Open", "Open action"));
        mainMenu.getChildren().add(new MenuItem("Save", "Save action"));
        mainMenu.getChildren().add(new MenuItem("Print", "Print action"));

        // Test the PrintActionCommandsMenuVisitor...
        final MenuVisitor menuVisitor = new PrintActionCommandsMenuVisitor();
        menuVisitor.visit(mainMenu);
    }
}
```

## File Visitor Example

In this example, we demonstrate the Visitor pattern applied to a file system structure. The FileVisitor visits files and
directories, counting them and printing their paths.
Key Components:

* FileAcceptable: Represents a file or directory that can be visited. It implements the GenericAcceptable interface.
* FileVisitor: A concrete visitor that counts the number of files and directories and prints their absolute paths.

DemonstrateVisitorPattern

This example traverses a directory structure starting from a given directory and counts the number of files and
directories using the FileVisitor.
Code Example:

```java
public class DemonstrateVisitorPattern {
    public static void main(final String[] args) throws URISyntaxException, IOException {
        final FileVisitor visitor = new FileVisitor();

        File directory = ClassExtensions.getResourceAsFile("DemonstrateVisitorPattern.class",
                new DemonstrateVisitorPattern());
        directory = directory.getParentFile();
        directory = PathFinder.getProjectDirectory();
        final FileAcceptable visitable = new FileAcceptable(directory);
        visitor.visit(visitable);
        System.out.println(visitor.getFilesCounted());
    }
}
```

This example shows how FileVisitor visits each file and directory in the specified directory, printing their paths and
counting them.

## Tree Node Visitor Example

This example demonstrates the Visitor pattern applied to a tree structure. The SimpleTreeNode class represents nodes in
a tree, and visitors are used to traverse and display node values or collect nodes.

### Key Components:

* SimpleTreeNode: Represents a node in a tree structure. Implements the Acceptable interface to accept visitors.
* DisplayValueOfSimpleTreeNodeVisitor: A visitor that prints the value of each SimpleTreeNode.
* TraverseSimpleTreeNodeVisitor: A visitor that collects all nodes in the tree into a set.

SimpleTreeNodeTest

This test case sets up a tree structure and uses the visitors to perform operations on the tree nodes.
Code Example:

```java
public class SimpleTreeNodeTest {
    @Test
    public void testVisitor() {
        // Setup tree structure
        SimpleTreeNode<String> root = new SimpleTreeNode<>("I'm root");
        SimpleTreeNode<String> firstChild = new SimpleTreeNode<>("I'm the first child");
        root.setLeftMostChild(firstChild);
        SimpleTreeNode<String> secondChild = new SimpleTreeNode<>("I'm the second child");
        firstChild.setRightSibling(secondChild);

        // Use DisplayValueOfSimpleTreeNodeVisitor to print node values
        root.accept(new DisplayValueOfSimpleTreeNodeVisitor<>());

        // Use TraverseSimpleTreeNodeVisitor to collect all nodes
        TraverseSimpleTreeNodeVisitor<String> traverseVisitor = new TraverseSimpleTreeNodeVisitor<>();
        root.accept(traverseVisitor);
        Set<SimpleTreeNode<String>> allTreeNodes = traverseVisitor.getAllTreeNodes();
        assertEquals(2, allTreeNodes.size());
    }
}
```

This example shows how to use visitors to traverse a tree structure and perform operations on its nodes.

## Additional Test Classes

* MenuAcceptableObject: Interface representing any object in the menu structure that can accept a MenuVisitor.
* PrintActionCommandsMenuVisitor: A concrete implementation of MenuVisitor that prints action commands for menu items
  and the names of menus.
* FileAcceptable: Represents files and directories in the file system, capable of being visited by a FileVisitor.
* FileVisitor: A visitor that processes FileAcceptable objects, counting files and directories and printing their paths.
* SimpleTreeNode: Represents nodes in a tree structure, capable of being visited by visitors.
* DisplayValueOfSimpleTreeNodeVisitor: A visitor that displays the values of tree nodes.
* TraverseSimpleTreeNodeVisitor: A visitor that collects tree nodes during traversal.

These examples provide a solid foundation for understanding how the generic Visitor pattern can be applied in various
scenarios, such as menus, file systems, and tree structures, while maintaining type safety and flexibility.

> Please support this project by simply putting a
> Github <a class="github-button" href="https://github.com/astrapi69/visitor-pattern" data-icon="octicon-star" aria-label="Star astrapi69/visitor-pattern on GitHub">
> Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project
> [![Donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making visitor-pattern great for all types of applications.

## Import dependencies to your project

<details>
  <summary>gradle (click to expand)</summary>

## gradle dependency

Replace the variable ${latestVersion} with the current latest
version: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/visitor-pattern/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/visitor-pattern)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of visitor-pattern:

define version in file gradle.properties

```
visitorPatternVersion=${latestVersion}
```

or in build.gradle ext area

```
    visitorPatternVersion = "${latestVersion}"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:visitor-pattern:$visitorPatternVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
visitor-pattern-version= "${latestVersion}"

[libraries]
visitor-pattern = { module = "io.github.astrapi69:visitor-pattern", version.ref = "visitor-pattern-version" }
```

then add the dependency to the dependencies area

```
    implementation libs.visitor.pattern
```

</details>

<details>
  <summary>Maven (click to expand)</summary>

## Maven dependency

Maven dependency is now on sonatype.
Check
out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~visitor-pattern~~~)
for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of visitor-pattern:

Then you can add the dependency to your dependencies:

    <properties>
        ...

```xml
        <!-- visitor-pattern version -->
<visitor-pattern.version>${latestVersion}</visitor-pattern.version>
```

        ...
    </properties>
        ...
        <dependencies>
        ...

```xml
            <!-- visitor-pattern DEPENDENCY -->
<dependency>
    <groupId>io.github.astrapi69</groupId>
    <artifactId>visitor-pattern</artifactId>
    <version>${visitor-pattern.version}</version>
</dependency>
```

        ...
        </dependencies>

</details>


<details>
  <summary>Snapshots (click to expand)</summary>

## 📸 Snapshots

[![Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://oss.sonatype.org/service/local/repositories/snapshots/content/io/github/astrapi69/visitor-pattern/maven-metadata.xml&label=snapshot&color=red&query=.//versioning/latest)](https://oss.sonatype.org/content/repositories/snapshots/io/github/astrapi69/visitor-pattern/)

This section describes how to import snapshot versions into your project.
Add the following code snippet to your gradle file in the repositories section:

```
repositories {
   //...
```

```groovy
    maven {
    name "Sonatype Nexus Snapshots"
    url "https://oss.sonatype.org/content/repositories/snapshots"
    mavenContent {
        snapshotsOnly()
    }
}
```

```
}
```

</details>

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal:
<br>
<br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif"
alt="PayPal this"
title="PayPal – The safer, easier way to pay online!"
style="border: none" />
</a>
<br>
<br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over the donation buttons at the top.

## Semantic Versioning

The versions of visitor-pattern are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit
the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## What can i do to support this project

You can donate or contribute solve issues or pull request. Every support are welcome.

## Want to Help and improve it? ###

The source code for visitor-pattern are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/visitor-pattern/fork](https://github.com/astrapi69/visitor-pattern/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/visitor-pattern/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you have any suggestions or
improvements.

## Contacting the Developers

Do not hesitate to contact the visitor-pattern developers with your questions, concerns, comments, bug reports, or
feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/visitor-pattern/issues).
