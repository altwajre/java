# count-ways-two-steps

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
#recursive recursiveStepByStep
(1 steps: 1 2 3 1 ways)
(2 steps: 4 5 6 7 8 2 ways)
(3 steps: 9 10 11 12 13 14 15 16 17 3 ways)
(4 steps: 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 5 ways)
(5 steps: 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 8 ways)
#recursive for recursiveLoop
(1 steps: {fn calls: 58 59 60 } 1 ways)
(2 steps: {fn calls: 61 62 63 64 65 } 2 ways)
(3 steps: {fn calls: 66 67 68 69 70 71 72 73 74 } 3 ways)
(4 steps: {fn calls: 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 } 5 ways)
#dp loop
(1 steps: {fn calls 1 2 3 } 1 ways)
(2 steps: {fn calls 4 5 6 7 8 } 2 ways)
(3 steps: {fn calls 9 10 11 12 13 14 15 } 3 ways)
(4 steps: {fn calls 16 17 18 19 20 21 22 23 24 } 5 ways)
```
