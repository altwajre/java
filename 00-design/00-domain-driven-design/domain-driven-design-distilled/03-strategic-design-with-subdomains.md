# Strategic Design with Subdomains

https://www.safaribooksonline.com/library/view/domain-driven-design-distilled/9780134434964/ch03.html

One Subdomain per Bounded Context, and one Bounded Context per Subdomain.

## What is a Subdomain?

- A Subdomain is a sub-part of your overall business domain.
- You can think of a Subdomain as representing a single, logical domain model.
- Subdomains can be used to logically break up your whole business domain so that you can understand your problem space on a large, complex project.
- Subdomain is that it is a clear area of expertise, assuming that it is responsible for providing a solution to a core area of your business.

## Types of Subdomains

There are three primary types of Subdomains within a project

> Core Domain

- This is where you are making a strategic investment in a single, well-defined domain model, committing significant resources for carefully crafting your Ubiquitous Language in an explicit Bounded Context.
- Core Domain demarcates where it must excel.

> Supporting Subdomain

- Custom development because an off-the-shelf solution doesn't exist.
- You will not make the kind of investment that you have made for your Core Domain.
- Consider outsourcing this kind of Bounded Context to avoid mistaking it for something strategically distinguishing.
- This is still an important software model, because your Core Domain cannot be successful without it.

> Generic Subdomain

- This kind of solution may be available for purchase off the shelf but may also be outsourced or even developed in house by a team that doesn't have the kind of elite developers that you assign to your Core Domain.

## Dealing with Complexity

- Need to reason about legacy systems when they have an impact on your Core Domain project. Use Subdomains as a tool for discussing your problem space.
- When we are discussing a legacy system there are probably some, even many, logical domain models that exist inside that one legacy system. Think of each of those logical domain models as a Subdomain.
- Thinking about and discussing such legacy systems using Subdomains helps us to deal with the harsh realities of a large entangled model.
- Context Mapping help us understand the associations and dependencies between Subdomains.
- A Bounded Context should align one-to-one (1:1) with a single Subdomain.
- If you must create a second model in the same Bounded Context (within your Core Domain), you should segregate the secondary model from your Core Domain using a completely separate Module.
