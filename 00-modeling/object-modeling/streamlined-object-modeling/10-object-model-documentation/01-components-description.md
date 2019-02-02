# COMPONENTS DESCRIPTION

## Components Description Example

EXAMPLE— A Web-enabled application offers expert information and accredited training videos to professionals seeking certification who enroll as subscribers to the site. Individuals who join by responding to a promotion are enrolled under the subscription plan associated with the promotion. Persons may renew as subscribers under the same or a different subscription plan.

### System Components Description

1. Subscriber Management (Component)

The Subscriber Management `Component` allows site visitors to enroll as subscribers, renew their subscription, convert their subscription to a different plan, and update their personal information.

The Subscriber Management Component allows site administrators to create, delete, and edit subscription plans; create a promotion with a subscription plan; and access subscriber and subscription information.

1.1. Subscriber Enrollment (Feature)

The subscriber enrollment feature allows visitors to the site to enroll as `subscribers` either by:
- responding to a promotion
- or selecting one of the standard `subscription plans` presented on the site.

1.1.1 `Promotion` (Object)

A promotion allows a site visitor with the proper code to enroll under a special subscription plan.

Each promotion involves exactly one subscription plan.

> Each promotion includes the following information:

- Code - Visitor must enter to access promotion
- Start date - When promotion becomes active
- End date - When promotion expires

1.1.2. `Subscription Plan` (Object)

A `Subscription plan` defines the enrollment terms for a `subscriber`.

> Each subscription plan includes the following information:

- Status - Active or inactive
- Duration - Length in months of subscriber's enrollment
- Fee policy - How much to charge for the subscription and how frequently payment is collected
- Renewal policy - Whether subscriber can renew under this plan

Each subscription plan has some number of subscriptions. A subscription describes the exact terms of enrollment for an individual subscriber.

1.1.3. `Subscription` (Object)

A subscription `records` a subscriber's participation in the system under a given subscription plan. A subscriber gets a new subscription when they convert subscription plans or renew under the same or a different subscription plan. The collection of subscriptions associated to a subscriber show that individual's `history` of participation in the system.

> Each subscription includes the following information:

- Status - Active or inactive
- Activate date time - When subscription activated for subscriber
- Inactivate date time - When subscriber's participation in system through this subscription is turned off either by termination or renewal.
- Expiration date - When subscriber access is scheduled to end under this subscription

> Each subscription collaborate with:

- a `subscription plan` that defines its enrollment terms
- a `subscriber` that defines the individual data for the subscription

1.1.4. `Subscriber` (Object)

...

1.1.5 `Enrolling Subscribers Functionality` (Functionality)

Visitors to the site can enroll as subscribers either by selecting one of the site subscription plan available or by responding to a promotion.

> The system enrolls a visitor with a selected subscription plan by:

- creating a subscriber object from information entered by the visitor
- using the selected `subscription plan` to create a subscription and set the enrollment terms for the subscriber
- requesting and verifying payment information from the visitor
- associating the new subscription to the new subscriber object

> The system enrolls a visitor responding to a promotion by:

- prompting the visitor for the promotion code
- verifying that the code is for an active promotion
- registering the visitor with the subscription plan associated with the promotion
