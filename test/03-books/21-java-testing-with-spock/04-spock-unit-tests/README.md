# Writing unit tests with Spock

**Available Spock blocks**

| Spock block | Description                         | Expected usage |
|-------------|-------------------------------------|----------------|
| given:      | Creates initial conditions          | 85%            |
| setup:      | An alternative name for given       | 0% (use given) |
| when:       | Triggers action that will be tested | 99%            |
| then:       | Examines results of test            | 99%            |
| and:        | Cleaner expression of other blocks  | 60%            |
| expect:     | Simpler version of then:            | 20%            |
| where:      | Parameterized tests                 | 40%            |
| cleanup:    | Releases resources                  | 5%             |

**note**
- when: block with no text description and unclear trigger code

```
def "Adding two and three results in 5"(){
    given:
    int a = 3
    int b = 2

    when: // when: block with no text description and unclear trigger code
    int result = a + b

    then:
    result == 5
}
```
