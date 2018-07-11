# Tests as Documentation

https://www.safaribooksonline.com/library/view/pragmatic-unit-testing/9781680500769/f_0037.html

Rework test names and code to tell stories, instead of introducing explanatory comments.

> DOCUMENTING OUR TESTS WITH CONSISTENT NAMES

| bad name | good name  |
|----------|------------|
| makeSingleWithdrawal     | withdrawalReducesBalanceByWithdrawnAmount        |
| attemptToWithdrawTooMuch | withdrawalOfMoreThanAvailableFundsGeneratesError |
| multipleDeposits         | multipleDepositsIncreaseBalanceBySumOfDeposits   |

> KEEPING OUR TESTS MEANINGFUL

If others have a tough time understanding what a test is doing, don't simply add comments.

- Improve test name
- Improve any local-variable names
- Introduce meaningful constants
- Prefer assertj assertions
- Split larger tests into smaller, more-focused tests
- Move test clutter to helper methods and @Before methods 
