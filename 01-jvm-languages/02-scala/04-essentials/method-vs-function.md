# method vs function

http://jim-mcbeath.blogspot.com/2009/05/scala-functions-vs-methods.html

When we treat a method as a function, such as by assigning it to a variable, Scala actually creates a function object whose apply method calls the original method, and that is the object that gets assigned to the variable. Defining a function object and assigning it to an instance variable this way consumes more memory than just defining the functionally equivalent method because of the additional instance variable and the overhead of another object instance for the function. Thus you would not want every method to be a function.
