# Single Responsibility Principle

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch7.xhtml

- A module should be responsible to one, and only one, actor.

## Symptoms of violating SRP

### Symptom 1: Accidental Duplication

```
class Employee {
  calculatePay() { <- is specified by accounting department that reports to the CFO
    regularHours();
  }
  reportHours() { <- is specified and used by the human resources department that reports to the COO
    regularHours();
  }
  save() {} <- is specified by the database admin (DBAs) reports to the CTO

  regularHours() {}
}
```

The Employee class violates the SRP because those three methods are responsible to three different actors.
CFO team calculatePay() changes regularHours() can break COO team reportHours().
We should not put code that different actors depend on into close proximity.
Solution: The SRP says to separate the code that different actors depend on.

### Symptom 2: Merges

Imagine that merges will be common in source files that contain many different methods.
The merge puts both the CTO and the COO at risk.
Solution: once again, the way to avoid this problem is to separate code that supports different actors.

### Solutions

Move each function into different classes.

```
class PayCalculator {
  calculatePay() {}
}
class HourReporter {
  reportHours() {}
}
class EmployeeSaver {
  saveEmployee() {}
}
class EmployeeData {} <- simple data structure with no methods
```

1, Use the Facade pattern to instantiate and delete the classes

```
class EmployeeFacade {
  calculatePay() { <- is specified by accounting department that reports to the CFO
    new PayCalculator().calculatePay();
  }
  reportHours() { <- is specified and used by the human resources department that reports to the COO
    new HourReporter().reportHours();
  }
  save() { <- is specified by the database admin (DBAs) reports to the CTO
    new EmployeeSaver().saveEmployee();
  }
}
```

2, Use the original Employee class as the `Facade`

```
class Employee {
  employeeData;
  calculatePay() { <- is specified by accounting department that reports to the CFO
    new PayCalculator().calculatePay();
  }
  reportHours() { <- is specified and used by the human resources department that reports to the COO
    new HourReporter().reportHours();
  }
  save() { <- is specified by the database admin (DBAs) reports to the CTO
    new EmployeeSaver().saveEmployee();
  }   
}
```
