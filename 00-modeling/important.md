# Recognizing Business Rules

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch08.html

Business rules come from domain experts; usually these are external clients, but they can be team members with specialized in-depth knowledge. Domain experts know the concepts behind the business being modeled, and say things like: “Some teams can only have one chair; other teams can have multiple chairs; and some teams do not require a chair at all.” Business rules define domain-specified limits for property values and when two entities can collaborate. Logic rules come from the programmers or system developers. Programmers say things like: “The document get publication accessor throws an exception if the publication date is null.”

## Principle 79: Where Rules Come From

`Business rules` come from clients; `logic rules` come from good programming practices.

## Both types of rules are important.

Your project is unlikely to be called successful by the customer if there are glaring `logic rule` errors. However, your projects will definitely not be successful if there is no way to conduct business using the product. Because of this and because logic rules tend to be easier to determine, we will concentrate on the `business rules`.
