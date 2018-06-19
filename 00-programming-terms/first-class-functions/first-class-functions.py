# python first-class-functions.py
import sys           # Load a library module

# Assign a fuction to a variable
def square(x):
    return x * x

f = square # assign a fuction square to a variable f

print(square)
print(f(5))

# Higher-order functions
# Pass a fucntion as argument, and return function as result of other function
def my_map(func, arg_list):
    result = []
    for i in arg_list:
        result.append(func(i))
    return result

squares = my_map(square, [1,2,3])
print(squares)

# Closures
# Return a function
def html_tag(tag):
    def wrap_text(msg):
        print('<{0}>{1}</{0}>'.format(tag, msg))
    return wrap_text # return a function

print_h1 = html_tag('h1')
print_h1("Headline 1")
print_h1("Another Headline")

print_p = html_tag('p')
print_p('Paragraph 1')
