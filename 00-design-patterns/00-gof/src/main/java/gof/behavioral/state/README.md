# State

## State transition table

> turnstile

| Given State | Event  | Next State | Action    |
|-------------|--------|------------|-----------|
| Locked      | Coin   | Unlocked   | Unlock    |
| Locked      | Pass   | Locked     | Alarm     |
| Unlocked    | Coin   | Unlocked   | Thank you |
| Unlocked    | Pass   | Locked     | Lock      |
