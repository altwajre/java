# Finite State Machines

## youtube

### Finite State Machines explained

https://www.youtube.com/watch?v=hJIST1cEf6A

> `Stop light` Finite State Machine

Input: Time

- Truth Table

Red: 00
Green: 01
Yellow: 10

|Time | State  | Next-State | Output |
|-----|--------|------------|--------|
| 0   | R (00) | R          | 00     |
| 1   | R (00) | G          | 01     |
| 0   | G (01) | G          | 01     |
| 1   | G (01) | Y          | 10     |
| 0   | Y (10) | Y          | 10     |
| 1   | Y (10) | R          | 00     |

> `Stop-Go` Finite State Machine

Input: Time and Truck-Waiting

Detect: Output is Pollution = 01 (0 time and the truck is waiting) at Stop state

|Time | Truck-Waiting | State | Next-State | Pollution (output) |
|-----|---------------|-------|------------|--------------------|
| 0   | 0             | Stop  | Stop       | 0                  |
| 0   | 0             | Go    | Go         | 0                  |
| 0   | 1             | Stop  | Stop       | 1                  |
| 0   | 1             | Go    | Go         | 0                  |
| 1   | 0             | Stop  | Go         | 0                  |
| 1   | 0             | Go    | Stop       | 0                  |
| 1   | 1             | Stop  | Go         | 0                  |
| 1   | 1             | Go    | Stop       | 0                  |
