// node first-class-functions.js
// () means execute the function

// assign a fuction to a variable
function square(x) {
  return x * x;
}

var f = square; // assign a fuction square to a variable f

console.log(square)
console.log(f(3)) // execute the function

// Higher-order functions
// pass a fucntion as argument, and return function as result of other function
function my_map(func, arg_list) {
  result = [];
  arg_list.forEach(function(i){
    result.push(func(i));
  })
  // for (var i = 0; i < arg_list.length; i++) {
  //   result.push(func(arg_list[i]));
  // }
  return result;
}

var squares = my_map(square, [1,2,3]);
console.log(squares);

// Closures
// Return a function
function html_tag(tag) {
  function wrap_text(msg) {
    console.log(`<${tag}>${msg}</${tag}>`)
  }
  return wrap_text;
}

print_h1 = html_tag('h1');
print_h1("Headline 1");
print_h1("Another Headline");
