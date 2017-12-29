## Cracking the Coding Interview - ctci github

https://github.com/gaylemcd/ctci

## data structure

### arrays and strings

#### is rotation

use contains() only once to check if s2 is a rotation of s1

static boolean isRotation(String s1, String s2) {
    int len = s1.length();
    // check that s1 and s2 are equal length and not empty
    if (len == s2.length() && len > 0) {
        String s1s1 = s1 + s1;  // concatenate s1 and s1
        return s1s1.contains(s2);  // use built-in contains() instead of isSubstring()
    }
    return false;
}

#### shuffle cards

static void shuffle(int[] cards) {
    for (int i = 0; i < cards.length; i++) {
        Random rand = new Random();
        int randomNum = rand.nextInt(cards.length - 1);
        int temp = cards[i];
        cards[i] = cards[randomNum];
        cards[randomNum] = temp;
    }
}

## concepts and algorithms

### math

#### is prime number

static boolean isPrime(int number){
    if(number == 2) return true;
    if(number < 2) return false;
    // return false if it is an even number
    if(number % 2 == 0) return false;
    for(int i = 3; i <= Math.sqrt(number); i+=2){
        if(number % i == 0) return false;
    }
    return true;
}

#### factorial

static int factorial(int n){
    int result = 1;
    for(int i = 1; i <= n; i++){
        result = result * i;
    }
    return result;
}

#### fibonacci

https://en.wikipedia.org/wiki/Fibonacci_number

fibonacci: 1, 1, 2, 3, 5, 8, 13, 21, 34

static int recursion(int numberOfTerms){
    if(numberOfTerms == 1 || numberOfTerms == 2){
        return 1;
    }
    return recursion(numberOfTerms - 1) + recursion(numberOfTerms - 2);
}
