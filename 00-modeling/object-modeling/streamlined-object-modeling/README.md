# Streamlined Object Modeling: Patterns, Rules, and Implementation

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/

## Determine Mine

answers requests for current state information

## Person Java DIAPER

DIAPER

- Define
- Initialize
- Access
- Print
- Equals
- Run

## Define: Person Profile Interface

The IPersonProfile interface is implemented by any class that has objects that assume a person profile.
Both person objects and team member objects exhibit this interface.

```
interface IPersonProfile {
  // Accessors - get properties
  String getName();
  String getTitle();
  String getEmail();

  // Determine Mine: answers requests for current information
  boolean hasValidEmail();
}
```

## Define: Person Conduct Business Interface

Person class implements the IPerson interface.

> IPerson consists following

- The methods that change the state of object by setting properties or adding collaborators.
- The collaboration accessors that are not object inherited.
- The accessors to get the team members are included in the conduct business interface because child objects do not object inherit their parent's other children.
- Collaborators refer to each other through their conduct business interfaces, so the collaboration accessors expect the team member conduct business interface as a parameter.
