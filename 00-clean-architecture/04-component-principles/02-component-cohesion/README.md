# Component Cohesion

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch13.xhtml

> Component Cohesion Principles

- REP: The Reuse/Release Equivalence Principle, Group for reusers
- CCP: The Common Closure Principle, Group for maintenance
- CRP: The Common Reuse Principle, Split to avoid unneeded releases

## The Reuse/Release Equivalence Principle

Reuse software Components should be tracked through a release process and are given release numbers.

## The Common Closure Principle

Gather into Components those classes that change for the same reasons and at the same times.
Separate into different Components those classes that change at different times and for different reasons.

This is the Single Responsibility Principle restated for Components.
SRP: a class should not contain multiples reasons to change.
CCP: a Component should not have multiples reasons to change.

This Principle is closely associated with the Open-Closed Principle (OCP)

> Similarity with SRP

The CCP is the Component form of the SRP.
The SRP separates methods into different classes, if they change for different reasons.
The CCP separates classes into different Components, if they change for different reasons.

## The Common Reuse Principle

Don't force users of a Component to depend on things they don't need.

> Relation to ISP

The CRP is the generic version of the ISP. The ISP advises us not to depend on classes that have methods we don't use.
The CRP advises us not to depend on Components that have classes we don't use.
