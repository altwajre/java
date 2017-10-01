# Data Types
https://www.youtube.com/watch?v=4OlN9oianT4&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=41
Data types are often classified into `Dates`, `Numeric`, and `String`. 

`Strings` are characters (letters and numbers). Even numbers alone can be considered a string. These numbers will be used differently than numeric numbers because they are of string type. 
It is a common practice to illustrate a string by putting quotes around it such as "Hey!" Most databases classify strings as char, varchar, or text. 
These can sometimes be broken up further to bigtext, smalltext, etc...

`Numeric` data types are numbers. The data types usually include int (for integer), decimal, and float/double. Ints work with only whole numbers. 
Decimal, float, and double all work with numbers with data after the decimal point. Decimal is usually more accurate for calculations. 
Another thing to think about is whether the number is signed or unsigned. An unsigned number can only store positive numbers. 
This is good for numbers that are only positive, such as a surrogate primary key. Signed numbers can be negative but keep in mind that the max value 
is cut in half because you must include negative numbers now.

`Dates` are split up into datetime, date, time, and timestamp. Datetime is a combination of date and time. time stamp is a data type the stores the 
exact moment of time and is usually updated automatically when a row is entered or updated.
