# Strategic Design with Bounded Contexts and the Ubiquitous Language

https://www.safaribooksonline.com/library/view/domain-driven-design-distilled/9780134434964/ch02.html

- In the end the `code is the domain model` and the `domain model is the code`.

- DDD is primarily about modeling a Ubiquitous Language in an explicitly Bounded Context

> Core Domain

A Core Domain is a software model that ranks among the most important.
A Core Domain is developed to distinguish your organization competitively from all others.
At the very least it addresses a major line of business
Your organization can't excel at everything and shouldn't even try.
So you choose wisely what should be part your Core Domain and what should not.
This is the primary value proposition of DDD, and you want to invest appropriately by committing your best resources to a Core Domain

## Domain Experts and Business Drivers

Differences in Policies by department (function)

> Policy in Underwriting: In the area of `expertise that is focused on underwriting`, a policy is created based on the evaluation of the risks of the insured entity. For example, when working in underwriting for property insurance, the underwriters would assess the risks associated with a given property in order to calculate the premium for the policy to cover the property asset.

> Policy in Inspections: Again, if we are working in the property insurance field, the insurance organization will likely have an `inspections area of expertise` that is to be insured. The underwriters are somewhat dependent on the information found during inspection, but only from the standpoint that the property is in the condition asserted by the insured. Assuming that a property will be insured, the inspection details - photos and notes - are associated with a policy in the inspections area, and its data can be referenced by underwriting to negotiate the final premium cost in the underwriting area.

> Policy in Claims: A policy in the claims area of `expertise tracks the request for payment` by the insured based on the terms of the policy created by the underwriting area. The claims policy will need to make some references to the underwriting policy but will be focused on, for example, damages to the insured property and reviews performed by the claims personnel to determine the payment, if any, that should be made.

## Fundamental Strategic Design Needed

Need two fundamental strategic design tools: Bounded Context and Ubiquitous Language

> Bounded Context

- What is `core`?
- Bounded Context should hold closely all concepts that are core to the strategic initiative and push out all others.

> Ubiquitous Language

The concepts that remain are part of the team

> In Context vs Out of Context

The concepts that survive this stringent application of core-only filtering are part of the Ubiquitous Language of the team that owns the Bounded Context. The boundary emphasizes the rigor inside.

- Some concepts will be `in context` and be clearly included in the team's language
- Other concepts will be `out of context`.

> Testing Benefits

Because Bounded Contexts are not monolithic, other benefits are experienced when they are used. One such benefit is that `tests` will be focused on one model and thus be fewer in number and will run more quickly.

> Focus on Business Complexity, Not Technical Complexity

You are using DDD because the business model complexity is high. We never want to make the domain model more complex than it should be. Still, you are using DDD because the business model is more complex than the technical aspects of the project. That's why the developers have to dig into the business model with `Domain Experts`!

Both developers and Domain Experts should reject allowing documents to rule over conversation. The best Ubiquitous Language will be developed by a collaborative feedback loop that drives out the combined mental model of the team. `Open conversation, exploration, and challenges to your current knowledge` base result in deeper insights about the `Core Domain`.

## Developing a Ubiquitous Language

Nouns are important, but often software developers put too much emphasis on the nouns within a domain model, forgetting that `spoken language is composed of far more than nouns alone`.

The most important benefit and empowering feature is that you can actually have conversations about the domain model works - its design.

### Scenarios

In the end the `code is the domain model` and the `domain model is the code`.

Be careful about the time spent in your domain-modeling efforts when it comes to keeping documents with written scenarios and drawings and diagrams up-to-date over the long haul. Those things are not the domain model. Rather, they are just tools to help you develop a domain model.

Consider expressing your Core Domain as a set of concrete scenarios about what the domain model is supposed to do. Scenarios are not use cases or user stories. Scenarios is how the domain model should work - what the various components do.

> Example: Scenario of Ubiquitous Language of Scrum

```
Allow each backlog item to be committed to a sprint. The backlog item may be committed only if it is already scheduled for release. If it is already committed to a different sprint, it must be uncommitted first. When the commit completes, notify interested parties.
```

The scenario is a description of how the very `real software model components` are used to support the management of a Scrum-based project.

## Architecture

### Inside a Bounded Context

> Input Adapters

- User interface controllers
- REST endpoints
- Message listeners

> Application Services

Orchestrate use cases and manage transactions

- Security
- Transactions
- Task Coordination
- Use Case Controller

> Core Domain Model

- Entities
- Business Logic
- Domain Events

> Output Adapters

- Repositories - persistence management
- Documents
- Cache
- Message senders

> Technology-Free Domain Model

Although there will be technology scattered throughout your architecture, the domain model should be free of technology. For one thing, that's why transactions are managed by the application services and not by the domain model.
