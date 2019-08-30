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

Like in Scala, this library allows the developer to craft property tests for
Java code that you write.

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
