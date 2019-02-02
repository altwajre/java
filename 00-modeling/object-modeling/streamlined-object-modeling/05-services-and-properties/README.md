# Services and Properties

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/ch05.html

## Doing and Knowing

streamlined object modeling boils object-oriented concepts down to the bare essentials necessary to fully model a complex domain: `collaboration patterns`, `collaboration rules`, and `object services`

> Three categories:

- conduct business
- determine mine
- analyze transaction services

## Objective vs Subjective Descriptions

Business requirements from `what` can support all manners of `how` because the focus is on the people, places, things, and events that happen in the process, not a particular order in which they appear in the process.

When asked to describe a process, people naturally describe it subjectively, in terms of “how I would do it,” step-by-step. Some even detail imaginary interactions with a computer system, speaking in terms of “how I see the system,” and focusing on “my favorite features.” Right off the bat, you should notice that these user-centric process descriptions focus on `how` and not `what`. Business requirements from “how” are only good as long as the “how” does not change, or until someone else wants a different `how`

Business requirements derived from individual experiences are difficult to verify as complete, since it is possible not all points of view were covered.

> Principle 23: Be Objective with Processes

Be objective when asking about processes. Talk instead about the objects - people, places, things, and events - involved in the process and the actions on these objects, rather than asking clients how they "want to do it"

# Objects Doing

## Strategies for Finding Services

One clear lesson in the peanut-butter-and-jelly sandwich example is that actions people do to sandwich ingredients become in the object world actions the ingredients do themselves. For example, the ingredient applies itself to a bread slice. To state it more generally, actions upon a real-world entity become services provided by the object that represents the entity. The rationale here is simple: All the information needed to act on an object is inside the object, so `let the object do the work`. Putting actions with the data they act on follows the software engineering principle of encapsulation that says package related things because they tend to change together. Keeping related things together decreases the likelihood they will grow out of sync.

> Principle 24: Do It Myself

Objects that are acted upon by others in the real world do the work themselves in the object world.

> Principle 25: Do It with Data

Objects encapsulate data representing an entity together with the services that act on it.

## Real-World Service Examples

`Real world`: A person checks out books from a library
`Object world`: A library book checks itself out
`Rationale`: A library book knows any restrictions it has, plus its future reservations and its past checkout history, so let the book decide if a particular library customer can check it out

`Real world`: A person applies a sandwich ingredient to a bread slice.
`Object world`: An ingredient applies itself to a bread slice in a given amount
`Rationale`: An ingredient knows how much of it remains in inventory, and knows how to create application of itself on the bread slice.

# DISTRIBUTING WORK

“do it myself” principle is that real-world entities are modeled by the collaboration patterns as two or more objects, so an action performed on the entity maps to potentially many objects. One of these objects must be designated as the owner of the action; call this object the director. Although the director object has the service corresponding to the real-world action, it may delegate large chunks of the work to its collaborators.

> Principle 26: Director Principle

Real-world actions on entities map to one of the objects representing that entity. This object is called the director of the action because it directs itself and its collaborators in carrying out the action.

## Entity Collaborations: Who Directs?

With entity collaboration patterns involving people, places, and things, two objects are representing one real-world entity, or an aggregation of entities. As noted in the previous chapter, one of the pattern players is more particular—more specific, more local, or more detailed—than the other.6 These more particular pattern players direct the work of checking collaboration rules since they know more about the context, location, or details than their more general, more global, or more aggregate pattern players.

EXAMPLE— A video store allows customers to reserve a video 48 hours in advance. To allow the reservation, the video title must have a videotape in stock or due back within 24 hours. This videotape is moved to the reservation shelf immediately, or when it is returned, to prevent its rental. Designate the video title as the director object, and add to it a `reserve` service. The video title reserve service proceeds by asking each videotape to reserve itself, and stops when one succeeds in doing so. The videotape has the responsibility of checking its availability and changing its status to prevent rental, so the videotape has the bulk of the work. The video title merely directs the walking through of the list of videotapes.

> Principle 27: Most Specific Directs

When a real-world action maps to two collaborators representing a single entity or an aggregation of entities, the director is the most specific, local, or detailed pattern player

## Event Collaborations: Who Directs?

With event collaborations, some actions require coordination between all the collaborators of the event. These actions are naturally directed by the event, as it is the only one with knowledge of all the others.

EXAMPLE— A flight has a scheduled arrival at a gate in an airport. Airport scheduling software polls the `scheduled arrival` for its arrival gate status, which is either clear or gate-conflict. The scheduled arrival determines its arrival gate status by asking its gate for its availability for the flight. The gate uses its own schedule of arrivals and departures and the flight’s current expected arrival time to answer whether it will be clear or have a conflict when the flight arrives

> Principle 28: Events Direct the Work

When an action requires cooperation among the collaborating entities of an event, the event directs the action.

# TYPES OF SERVICES

1. conduct business
2. determine mine
3. analyze transactions services

> Principle 29: Let the Director Conduct

Use the `specific directs` and `event directs` principles to find the director of a process. Assign the director a conduct business service to initiate the process.

# CONDUCT BUSINESS SERVICES

Actions Not Questions

- Services that `kick off` processes are called conduct business services.
- `accomplishes an action` rather than answers a question.
- `not returning results` distinguished from determine mine and analyze transactions services.
- Create new objects, or change objects states.
- Create new objects, establish or dissolve collaborations, notify collaborators, and set property values.
- `Kick off` new processes or threads

## Creating New Objects

Some conduct business services trigger the creation of new objects. This frequently occurs when a transaction is created to record an interaction initiated by a conduct business service. The director of the conduct business service does not necessarily create the transaction. Any of the objects (roles, places or specific items) involved in the transaction can create it. Good object think suggests allowing the object most knowledgeable about the interaction to create the transaction. Here, the most knowledgeable object is either the one supplying most of the details in the interaction or the object with the most constraints restricting its participation in the interaction. Choosing the most knowledgeable object is a judgment call. When no object leaps out, consider extensibility issues and then just pick one. What matters most is that you distribute the work and put the service near the information required to make the transaction. Some examples might help.

EXAMPLE— A team member nominates a document for publication on the team Web page. The document is not published to the Web page until the nomination is approved. Since the document is the entity being acted upon, it is the director of the nominate service. Also, since the document has the most restrictions on whether it can participate in the nomination, it creates the nomination object. To do this, the document nominate service requires a team member as a parameter. The Document (specific item) creates a Nomination.

> Principle 30: Most Knowledgeable Is Responsible

When a `role` acts on a `specific item` at a given `place` and the event is recorded, given the most knowledgeable or restrictive object a conduct business service that establishes the `transaction`.

## Collaboration Accessor Services

Services for establishing and dissolving Collaborations are also `conduct business services`. Known as accessor services, these services `add or remove` Collaborations between two objects; they also enforce Collaboration rules.

EXAMPLE- A team member nominates a document for publication on the team Web page.

The `transaction` has two Collaboration accessors, addDocument and addTeamMember, which establish collaborations with document and team member respectively.

> The document collaboration rules

The `document` has a collaboration accessor service, addNomination, which establishes the collaboration with a nomination and enforces the following collaboration rules for the nomination.

- A document must have a title to be nominated
- A document cannot be nominated after it is published
- A document cannot be nominated again while it is pending

> The team member collaboration rules

The `team member` has a collaboration accessor service, addNomination, which establishes the collaboration with a nomination and enforces the following collaboration rules for the nomination

- A team member must have nomination privileges
- A team member has an upper limit to the number of nominations in a month

## Notifying Collaborators

Publish-subscribe notification, which is popular in many object-oriented frameworks, is another type of conduct business service. Java's event listeners use publish-subscribe notification.

EXAMPLE- When a new resource is posted to an online message board topic, the topic notifies its subscribers.

## Write Accessors

A write accessor is a conduct business service that sets a property. Similar to collaboration accessors, these services enforce property rules governing the validities of their property values.

EXAMPLE- In the document nomination example, a nomination has the following properties: status, reviewer's comments, and date nominated. Accessors to set the values of these properties are not shown on the nomination object, but are assumed to exist.

# DETERMINE MINE SERVICES

## Providing Answers

Determine mine services satisfy requests for `current information`.
Unlike an analyze transactions service, which returns historical or future state information about an object, a determine mine service tells the state of the object, right now.
Unlike a conduct business service, which starts a particular business process, a determine mine service can be part of many business processes.
Lime a conduct business service, a determine mine request might request the object to coordinate work among its collaborators. However, determine mine services should never alter the states of any objects.
Typical determine mine services include returning property values and collaborators, working with collaborators to determine an aggregate value, and performing a search.

> Principle 31: Let an Object Determine Mine

Provide an object with determine mine services so it may answer requests for current information.

## Read Accessors

Read accessors are special determine mine services that return the values of properties and collaborators.16 As with write accessors for properties, read accessors are not depicted on the object model, but are assumed to exist for each property and collaboration.

EXAMPLE— In the document nomination example, a nomination has properties for status, reviewer’s comments, and date nominated, and collaborations with a document and a team member. Read accessor services return the nomination’s property values and collaborations.

## Not Always Simple

Determine mine services can be complex and involve many objects. Similar to a conduct business service, a determine mine service can have a director that coordinates work with its collaborators to establish a result, or a determine mine service can be state-dependent. A state-dependent determine mine signals an error if the object is not in the proper state to answer the request. An object can be in an improper state if properties are unset or collaborators are missing. For this reason, state-dependent accessors imply the need for other accessors to determine state.

EXAMPLE— An order recalculates its total when a product is added to it, and sets its completion date after all products have shipped. To determine its total, the order sums up the results of directing its order line items to determine their own totals. The order signals an error if asked for its completion date before it is set.

# ANALYZE TRANSACTIONS SERVICES

## Time-Sensitive Information

Analyze transactions services access information captured in associated events. As with a conduct business service, an analyze transactions service starts with a director object. The director in the analyze transactions service directs its transaction collaborators to determine a result. Because they return results and do not generate side-effects in the system, analyze transactions services resemble determine mine services; however, unlike a determine mine service, an analyze transactions service `relies on historical or future events` to reach its results. This distinction, which matters more during the system design phase, means that determine mine services can be answered by an object and its immediate collaborators, whereas analyze transactions services `require doing work across the object's event collaborators from possibly a long time back`. A common mistake to avoid when specifying an analyze transactions service is putting too much work in the director object; instead, the analyze transactions service should request one determine mine service of each transaction collaborator involved.

> Document.java

```
// Analyze Transactions
@Override
public INomination getApprovedNomination() throws BusinessRuleException {
  CollectionDetector approvedDetector = new CollectionDetector() {
    @Override
    public boolean detectBlock(Object listElement, Object keyValue) {
      return ((INomination)listElement).isStatusApproved();
    }
  };
  INomination nomination = (INomination)approvedDetector.detect(this.nominations);
  if(nomination == null) {
    throw new BusinessRuleException("Document has no approved nomination.");
  }
  return nomination;
}
```

## Business Objects Direct

Another mistake is to ignore `analyze transactions service` entirely. Some do this because they think analyze transactions services can be implemented much `more efficiently in the database`. That may well be true, but where the implementations of `analyze transactions services` reside should be worked out during the design or implementation activities. Regardless of where the implementations reside, all analyze transactions services should start in the affected business objects. These objects have the business rules and security privileges for deciding if the `analyze transactions services can run and under what constraints`. Once a business object verifies that it can run an analyze transaction service, the work can be delegated to a data manager object to invoke the appropriate database routines. Putting analyze transactions services in the object model ensures business rules govern their uses and the object model is sufficiently detailed to handle most analyze transactions queries.

> Principle 32: Let an Object Access Events

Provide an object with analyze transactions services so it may access its historical information, past events, and future scheduled events.

## Historical Information

Some analyze transactions services summarize and survey an object’s `historical information`, which is data recorded periodically about the object, such as monthly performance measurements or price changes. These analyze transactions services assess the historical information by computing sums, averages, and other statistical results and invoking information queries.

EXAMPLE— Every month, a bottling plant records the production log for each of its bottling lines. A production log knows the number of liters produced by the bottling line, the total production time spent, and the stoppage time lost; the production log uses this information to determine the line’s efficiency. A bottling line has an analyze transactions service that works across its production logs to determine its average line efficiency for a given range of time periods.

## Summarizing Events

Another category of analyze transactions services examines events to deduce summary behavior and statistics. These are similar to services that assess historical data; however, unlike historical information, events include collaborations with people, places, and things, so services assessing events may summarize information about their collaborators as well.

EXAMPLE— A retail clothing store tracks the point-of-sale orders of customers buying with a proprietary credit card. Proprietary card owners earn discounts according to the number of different items purchased with the card during given date ranges. A determine mine service provided by the order returns its number of distinct items during a date range, or zero if the order did not occur during the date range. An analyze transactions service of the proprietary card owner gets the total number of distinct items purchased during the date range.

## Future Scheduling

Yet another type of analyze transactions service assesses future scheduled events. Common forms of these services include looking to see if an event has been scheduled, and searching for conflicting events. Services that locate conflicting events are often used in verifying conflict collaboration rules.

EXAMPLE— A meeting room can be reserved on a given date in blocks of 30 minutes. Analyze transactions services on the meeting room check if it is available at a given date/time by asking each room reservation if it conflicts with the given date/time.

# OBJECTS KNOWING

In object modeling, properties are lowest priority. Getting objects, collaborations, and behaviors properly modeled are far more pressing concerns and take more object thinking skills. This is an important part of streamlined object modeling. You end up with streamlined objects containing only those properties necessary for effective communication. Once the skeleton of the object model is built, filling in the properties is the finishing touch. In object think, properties are "what I know" and they reflect the characteristics of the real-world entity. This section covers the following categories of properties often encountered in object modeling.

> Descriptive

Domain and tracking properties

> Time

Date or time properties

> Lifecycle state

One-way state transitions (e.g. nomination status: pending, in review, approved, rejected)

> Operating state

Two-way state transaction (e.g. sensor state: off, on)

> Role

Classifies people (e.g. team member role: chair, admin, member)

> Type

Classifies places, things, and event (e.g. store type: physical, online, phone)

## Descriptive Properties

Descriptive properties are the most obvious because they come from real-world characteristics of the object. Real-world objects have loads of characteristics, so the challenge is to separate the relevant ones from the irrelevant ones. Good object modelers identify properties by talking to domain experts and examining legacy databases and information architectures when they are available. During design, some of the complex descriptive properties, like address, may be fleshed out into reusable objects.

EXAMPLE— Interesting characteristics of a person include weight, height, hair color(s), piercings, and tattoos; however, an online banking system is only interested in the person’s name, address, Social Security Number, and date of birth.

## Tracking Properties

Tracking properties are special descriptive properties used to distinguish objects and give them unique identities. During the object modeling sessions, ask domain experts how they track and distinguish between customers, orders, students, employees, shipments, parts, etc. Analysis object models should not include non-descriptive keys or object IDs in the object diagram. These may be added later during design.

EXAMPLE—An online e-commerce site tracks its registered customers by their email addresses. A college tracks its students by their Social Security Numbers. A fulfillment center tracks its shipments by carrier tracking numbers

## Assumed Property Accessors

Unless explicitly stated otherwise, assume that each property shown in the object diagram has a read and a write accessor, which return and change the property value, respectively. Accessors are usually not shown on the model because they clutter the diagram, and clutter defeats communication. Properties help communicate the domain by showing what an object knows, but accessors don’t add any new information.

## Properties Can Be Derived (calculated)

It is tempting to imagine an object property as simply a field holding a data value, and many properties are implemented that way, but some properties are derived or calculated. Ultimately, whether a property is stored or derived is an implementation decision, but even during object modeling, some of what an object needs to know is obviously derived from other information. Derived properties are listed in the service section as services, usually with a prefix of “get” or “determine.”

EXAMPLE— A person’s age is a derived property. Storing a person’s age is highly impractical since it would require continual updating. The simpler approach is to model the person’s date of birth as a stored property and the person’s age as a derived property

## Time-Dependent Properties

Time and date properties are necessary for transaction objects that record time-based events. A non-transaction object can also have time and date properties when it needs to remember a non-repeatable occurrence or repeatable occurrences where history is not required; otherwise, the object should use historical properties. To reduce object model clutter, smash the time and date properties together into one “dateTime” property.

EXAMPLE— A nomination is a moment-in-time transaction with a property indicating the date and time of the nomination. A timeshare condo reservation is a time-interval transaction with properties indicating its start and end dates and times. A document is a specific item with a creation date-time property, which is a non-repeatable occurrence. A user is a role with a last login date-time, which is a repeatable occurrence and its previous values are not important.

## Historical Properties (history object)

Most properties can change values, but for some properties, the history of past values is important to the business processing. When an object has an historical property, the property is promoted to a history object collaborator, the default read accessor retrieves the value in the most current history object, a special read accessor retrieves the value on a given date, and the write accessor creates a new history object.

EXAMPLE—An e-commerce site needs to remember price changes for its products. Each product has one or more price history objects, which include a price and its effective dates.

> Principle 34: Track But Don't Key

Keep keys and object IDs off the diagram. Include identifying properties only if they come from the domain.

> Principle 35: Hide Redundant Accessors

Assume each property listed in the object definition has a read and write accessor, but don't put them in the diagram.

> Principle 36: Show Derived (calculated) Accessors

Represent a derived (calculated) property with a read accessor in the service section.

> Principle 33: Make it Real and Relevant

Descriptive properties come from an object's relevant real-world characteristics. Use domain experts, legacy databases, and information architectures to locate relevant descriptive properties.

> Principle 37: Always Date Events

`Transaction` objects always include date and/or time properties.

> Principle 38: Date Objects with Special Occurrences

Put date and/or time properties in non-transaction objects to record a non-repeatable occurrence or a repeatable occurrence that does not require history.

> Principle 39: Historical Properties Need Objects

Use history event objects to keep an audit trail of values for a property. Treat the property like a derived (calculated) one; include a special accessor to read the property value for a given date.

# STATE PROPERTIES

## Lifecycle State Properties

Some objects go through a one-way lifecycle, from an initial state to a final state. With people, places, and things, lifecycle states depend on their interactions and so are derived properties determined by analyze transactions services. Events actually record state, usually in read-only properties that are changed during conduct business services.

EXAMPLE— In the document nomination example, a document progresses through lifecycle states of un-nominated, pending, approved, and rejected. Which state a document is in depends on its nomination collaborators.

## Caching Properties

Objects with derived (calculated) properties can a lifecycle state may reach a final state when the derived properties cannot change. The conduct business service that puts the object into its final state can then determine the derived properties and cache their values. Future requests for the derived property would return the cached value.

## Operational States

Lifecycle states are one-way, but often objects flip back and forth between different states, and which state an object is in influences how it behaves. these are called the object's operational states. As with lifecycle states, operational states can only be set during conduct business services.

EXAMPLE- A team member can be in the either an active or inactive state. A store can be open or closed. A sensor can be off, initialized, on, failed. A product can be available or unavailable.

> Principle 40: Knowing Where in the lifecycle

In a person, place, or thing object, make the lifecycle state a property derived from event collaborators. In an event, make the lifecycle state a property, unless it is derived from follow-up events.

> Principle 41: Knowing Which Operational State

Put an operating state property in any person, place, or thing object that switches between different operational modes.

> Principle 42: Cache When Final

When an object reaches one of its final lifecycle states, consider caching its derived (calculated) properties.

> Principle 43: Only Change State When Conducting Business

Allow only conduct business services to change an object's lifecycle or operational state properties.

# COMPLEX PROPERTIES

## Clutter Objects

Clutter defeats communication. A sure way of reducing clutter is to depict complex information as properties. Defer until design fleshing these properties out as full-blown objects. To find clutter objects, look for object connections that are one-to-one and do not fit into any of the 12 collaboration patterns. This is another important part of streamlined object modeling: you end up with a streamlined diagram containing only those objects necessary for effective communication.

EXAMPLE- A person's name, Social Security number, and address are collapsible objects that can be depicted as properties but are implemented as individual objects.

During design, properties may be fleshed out into objects.

## Role Classifications

While participation in a context is modeled with a role object, often there are different levels or classifications of participation. Depending on their complexities, different levels can be modeled as separate objects or as properties. If the classifications are solely for bookkeeping reasons, do not require history, and have no special behaviors, then add a role classification property to the role object to indicate the different classifications. Otherwise, use a subsequent role to follow up the original role.

EXAMPLE- In the document nomination example, team members are classified as members, admins, or chairs. Histories of past chairs and admins are not retained for the team or for individual team members. The business process for nominating a document is the same for all team members, regardless of their classification, but the collaboration rules allow chairs to nominate more documents. Because there are no history requirements, and the behavior is the same, a role property is added to the team member object to classify a team member as a chair, admin, or member.

## Role Classification Accessors

A role classification property is typically designed and implemented as an enumerated type, meaning that it can take on one of a few symbolic values. Such properties lack the usual read and write accessors; instead, the object has services that check if the property is a particular value or command the object to set the property to a particular value. These services are assumed unless the role is a derived (calculated) property.

EXAMPLE- The team member in the document nomination system has read accessor services, isRoleChair, isRoleAdmin, and isRoleMember, which return true or false, dependinging on the role property; team member also has write accessor services, setRoleChair, setRoleAdmin, and setRoleMember, which set the role property accordingly.

## Role History

If a role classification does not have special behaviors, but needs history, then expend it into a role history object, much like the price property was expanded to a price history.

EXAMPLE- If a team member is required to know his current and past team roles, then expand the role classification property into a role history object.

## Subsequent Roles

If a role classification has properties and services not present in others, then expand it into a further role object.

EXAMPLE- A team member keeps history of his role classifications. If the team member becomes a chair, he can use the online system to call meetings, and must supply a secondary contact to handle meeting inquiries.

## Type Properties

Just as roles can be classified, places, things, and events can be typed. The same rules apply. If the type classifications are solely for bookkeeping reasons, do not require history, and have no special behaviors, then just add a type classification property to the object. Otherwise, consider defining type history and subsequent type objects.

EXAMPLE- A hotel reservation system keeps a description of each hotel room that includes whether the room is smoking or non-smoking.

> Principle 44: Collapse Clutter Objects

Collapse objects whose only purpose is to represent complex information into properties.

> Principle 45: Classify Roles

Use a role classification property to distinguish different levels of participation only if the participation level requires no history and no additional properties, behaviors, or collaborations.

> Principle 46: Classify Types

Use a type classification property to distinguish different object types only if the type requires no history and has no additional properties, behaviors, or collaborations.
