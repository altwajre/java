# OBJECT MODEL DESCRIPTION

## Scoping the Requirements

## Object Model Description - Subscriber Management

1. Subscriber Management (Component)

1.1 `Subscription Plan` (Object)

`SubscriptionPlan` {
  status
  duration
  discountPolicy
  renewalPolicy
  ---
  createSubscription
}

> Properties

A `subscription plan` knows these properties:

- Status          - Pending, active, inactive
- Duration        - Number of months subscription is valid
- Fee policy      - Policy for determining fee for subscriber
- Renewal policy  - Policy for deciding if subscribers can renew
- Discount policy - Policy for determining discounts for subscribers

> Services

A `subscription plan` knows how to create a `subscription` for a given `subscriber`

- A new `subscription` object is created for the `subscriber` and associated with the `subscription plan`.
- The `subscription` is destroyed if any collaboration rules among `subscription`, `subscriber`, and `subscription plan` fail.

> Collaborations

A `subscription plan` has collaborations with:

- Some number of `subscriptions` whose terms of participation in the system are defined by the `subscription plan`
- Some number of `promotions`

> Collaboration Rules

A `subscription plan` has the following business rules with its collaborators:

- A `subscription plan` that is pending or inactive cannot collaborate with a `subscription` or `promotion`
- A `subscription plan` has a collaboration conflict with a subscriber whose current subscription belongs to the subscription plan and the plan is not renewable.
- A subscription plan has a collaboration conflict with a subscriber if the subscriber does not have valid or adequate payment information to cover the fee for the subscription.

1.2. `Subscription` (Object)

`Subscription` {
  status
  paymentInfo
  activateDate
  inactiveDate
  expirationDate
  ---
  renew
  expire
  convert
  activate
  inactivate
}

> Properties

A subscription knows these properties:

- status          - Pending: waiting to be activated
                    Active: activated and not yet expired
                    Expired: expiration date reached; in grace period for renewal
                    Inactive: can no longer participate
- Activate date   - Date time when subscription is to be activated
- Expiration date - Date time when subscription expires
- Inactivate date - Date time when subscription made inactive

> Services

A subscription knows how to `activate` itself

- Fails if activate date not reached
- Fails if status is not "pending"
- Sets status to "active"

A subscription knows how to `inactivate` itself

- Fails if it is "inactive"
- Sets status to "inactive"

A subscription knows how to `expire` itself

- Fails if expiration date not reached
- Fails if status is "inactive" or "expired"
- Sets status to "expired"

A subscription knows how to `renew` itself

- Fails if subscription is inactive
- Fails if its renewal policy (from subscription plan) does not allow renewal
- Asks its `subscription plan` to create a new `subscription` with its own `subscriber`
- If successful, sets the new subscription's activate date to its own expiration date

A subscription knows how to `convert` itself to a new subscription plan

- Fail if subscription is inactive
- Asks new `subscription plan` to create a new `subscription` with its own `subscriber`
- If successful, inactivates itself

> Collaborations

A `subscription` has collaborations with:

- Exactly one `subscription plan` that acts as a parent object
- Exactly one `subscriber` that acts as a parent object

> Collaboration Rules

A `subscription` has the following business rules with its collaborators

- A `subscription` cannot collaborate with a `subscription plan` if it already has one.
- A `subscription` cannot collaborate with a `subscriber` if it already has one.
