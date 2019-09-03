### Difference between a Test and a Property-based Test

A test is a concrete example of how a program should behave in a particular
situation, while a property is an abstract, general specification.

In property-based testing, you don't reason about usage examples. Instead, you
try to capture the desired code behavior in a general sense, by abstracting
over input parameters and states.

In actuality, the abstractions in ScalaCheck is conceptually one-level higher
than in JUnit. ScalaCheck basically acts on the properties you feed it and many
concrete tests will be generated behind the scenes.

### Using ScalaCheck

[ScalaCheck](http://scalacheck.org) is inspired by [QuickCheck](http://hackage.haskell.org/package/QuickCheck) (Haskell's default property-based testing framework) and many others in various programming languages are inspired by such.

You define _properties_ instead of tests. To define a set of properties for our
library under test, we extend `org.scalacheck.Properties` class. The
`Prop.forAll` method is a common way of creating properties. The `forAll`
method is known as a _universal quantifier_ and when ScalaCheck runs, it tries
to falsify it by assigning different values to the parameters of the provided
function, and evaluating the boolean result. An important thing to take note is
that if ScalaCheck does not locate a set of arguments that makes the property
false, then ScalaCheck will regard the property as _passed_.

Example of a test run:

```
... // more tests are omitted ...
[info] + Combining Teenager/Parent properties.Teenager properties.A child should be below the age of 14: OK, passed 100 tests.
[info] + Examples of Conditional Quantification.Capacity of created list should equal the desired capacity: OK, passed 100 tests.
[info] + All about Cars.A sedan car should have 2 axles & 4 wheels: OK, passed 100 tests.
[info] + Combining Teenager/Parent properties.Teenager properties.A teenager should be between 13 and 19: OK, passed 100 tests.
[info] + String.concatenate: OK, passed 100 tests.
[info] + Examples of Universal Quantification.concating two strings does not alter the order: OK, passed 100 tests.
[info] + Teenager properties.A child should be below the age of 14: OK, passed 100 tests.
[info] + String.startsWith: OK, passed 100 tests.
[info] + String.substring: OK, passed 100 tests.
[info] + StringUtils.contains: OK, passed 100 tests.
[info] + Teenager properties.A teenager should be between 13 and 19: OK, passed 100 tests.
[info] + Parent properties.Parent(s) must be older than the child: OK, passed 100 tests.
[info] + Combining Teenager/Parent properties.Parent properties.Parent(s) must be older than the child: OK, passed 100 tests.
[info] + Examples of Universal Quantification.Double reversing a list will equal the original list: OK, passed 100 tests.
[info] + InsertionSort.A sorted list will have the minimal element at the head: OK, passed 100 tests.
[info] + InsertionSort.Validating the sorting algorithm: OK, passed 100 tests.
```
You can see that each _property_ specification generated _100 tests_ for
validation and i think its a great idea to have a framework that does the heavy
lifting for the developer ☺

### Using Junit-Quickcheck

Like in Scala, the library allows the developer to craft property tests for
Java code that you write. The powerful feature this library brings to the table
is the fact that it allows the developer to develop custom data generators
using the Java Generics programming language features. It doesn't rely on any
other library which means it is quite self-contained.

```
... // more tests are omitted
[debug] Test run started
[debug] Test MathProperties.minimumMustHold started
[debug] Test MathProperties.minimumMustHold finished, took 0.103 sec
[debug] Test MathProperties.maximumMustHold started
[debug] Test MathProperties.maximumMustHold finished, took 0.015 sec
[debug] Test run finished: 0 failed, 0 ignored, 2 total, 0.123s
[debug] Test run started
[debug] Test StringUtilProperties.tokenizeByComma started
[debug] Test StringUtilProperties.tokenizeByComma finished, took 0.108 sec
[debug] Test StringUtilProperties.concatenationLength started
[debug] Test StringUtilProperties.concatenationLength finished, took 0.026 sec
[debug] Test StringUtilProperties.tokenizeBySemiColon started
[debug] Test StringUtilProperties.tokenizeBySemiColon finished, took 0.012 sec
[debug] Test run finished: 0 failed, 0 ignored, 3 total, 0.151s
```

## How to clone this project and run the examples

Before you go on, you should be familiar with the Linux ecosystem and working
in the command line; of course you are welcomed to use Java IDEs. This project
is configured to be a _template_ leveraging _giter8_; for installation
instructions do refer to this [Setup giter8](http://www.foundweekends.org/giter8/setup.html)

You would need the following installed:

- JDK 1.8 (at least) and we don't care whether its Oracle JDK or Open JDK
  - You need the compiler i.e. `javac` in addition to the runtime `java` engine
- Install [giter8](http://www.foundweekends.org/giter8/setup.html)
- Install [sbt](https://www.scala-sbt.org/1.x/docs/index.html) from this
  [page](https://www.scala-sbt.org/1.x/docs/Setup.html)
- **NOT WORKING** Clone this repository to a directory of your choice using _giter8_:
  - `g8 git@gitlab.thalesdigital.io:Raymond.tay/propertybasedtesting --name MyTest --force`
- **TRY THIS** Clone this repository to a directory of your choice using _git clone_:
  - Navigate to the root directory of the project and clone the project via
    _giter8_:
      - `g8 file://<this repo> --name=<project name>`; you should see `<project
        name>` created locally.
- You have 2 options to run this:
  - Navigate to the directory (i.e. `<project name>`) and fire `sbt test` (this should be on your `PATH`)
  - Navigate to the directory (i.e. `<project name>`) and fire `sbt` and run `test` from within the sbt
    console
  - Regardless of which option you chose, you should see the above output as in
    [java-quickcheck](#using-junit-quickcheck) and
    [scala-quickcheck](#using-scalacheck)



