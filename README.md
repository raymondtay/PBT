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


