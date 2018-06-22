# concepts and algorithms

## math

### is prime number

https://en.wikipedia.org/wiki/Prime_number
It is a natural number greater than 1 that has no positive divisors other than 1 and itself.

static boolean isPrime(int number) {
    if (number == 2) return true;
    if (number < 2) return false;

    if (number % 2 == 0) return false;

    for (int i = 3; i < number; i++) {
        if (number % i == 0) return false;
    }
    return true;
}

### prime factor

https://en.wikipedia.org/wiki/Prime_factor

// divided by primes from small to large. when number divided by 2 has remainder, move to next prime 3
static List<Integer> primes = new ArrayList<>();
static void printPrimeFactor(int number){
    // preprocess: add prime numbers in number to list
    for(int i = 2; i <= Math.sqrt(number); i++){
        if(isPrime(i)){
            primes.add(i);
        }
    }

    for(int i = 0; i < primes.size(); i++){
        while(number % primes.get(i) == 0){ // number can be divided by prime number without remainder
            System.out.printf("%s ", primes.get(i));
            number /= primes.get(i);
            System.out.println(number);
        }
    }
}

### factorial

static int factorial(int n){
    int result = 1;
    for(int i = 1; i <= n; i++){
        result = result * i;
    }
    return result;
}

### fibonacci

https://en.wikipedia.org/wiki/Fibonacci_number

fibonacci: 1, 1, 2, 3, 5, 8, 13, 21, 34

static int recursion(int numberOfTerms){
    if(numberOfTerms == 1 || numberOfTerms == 2){
        return 1;
    }
    return recursion(numberOfTerms - 1) + recursion(numberOfTerms - 2);
}

### square root

static int sqrt(int n){
    int start = 0;
    int end = n;
    while(start < end){
        int mid = (start + end) / 2;
        int multiply = mid * mid;
        if(multiply == n) return mid;
        else if (multiply > n) end = mid;
        else start = mid;
    }
    return -1; // not found
}

## recursion and dynamic programming

### fibonacci dynamic programming

static int recursionDP(int n, int[] map) {
    if (n == 1 || n == 2) {
        return 1;
    } else if (map[n] > -1) {
        return map[n];
    } else {
        map[n] = recursionDP(n - 1, map) + recursionDP(n - 2, map);
        return map[n];
    }
}

### find item in array

// linear search
static int linearSearch(int[] array) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == i) {
            return i;
        }
    }
    return -1;
}

// binary search
static int binarySearch(int[] array, int startIndex, int endIndex) {
    if (endIndex < startIndex || startIndex < 0 || endIndex >= array.length) return -1;
    int mid = (startIndex + endIndex) / 2;
    if (array[mid] == mid) return mid;
    else if (array[mid] > mid) return binarySearch(array, startIndex, mid - 1);
    else return binarySearch(array, mid + 1, endIndex);
}

### permutations

Q: compute all permutations of a string
permutations of abc are [abc, bac, bca, acb, cab, cba]

 Solution: insert nth item into every spot in each of substring n-1
   call stack:
   getPerms("abc") - first=a, words=[bc,cb], return [abc,bac,bca,acb,cab,cba]
   getPerms("bc")  - first=b, words=[c],     return [bc,cb]
   getPerms("c")   - first=c, words=[""],    return [c]
   getPerms("")    - str.len==0              return [""]

    input: getPerms("abc")
   output: [abc, bac, bca, acb, cab, cba]

static ArrayList<String> getPerms(String str){
    if(str == null) return null;
    ArrayList<String> permutations = new ArrayList<String>();
    if(str.length() == 0){ // base case
        permutations.add("");
        return permutations;
    }
    char first = str.charAt(0); // get the first character
    String remainder = str.substring(1); // remove the 1st character
    ArrayList<String> words = getPerms(remainder);
    for(String word : words){
        for(int i = 0; i <= word.length(); i++){
            String s = insertCharAt(word, first, i);
            permutations.add(s);
        }
    }
    return permutations;
}

## sort and search

### group strings in anagrams

use map to group sorted strings

static void sort(String[] array){
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    // Group words by anagram
    for(String s : array){
        String key = sortChars(s);
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<String>());
        }
        ArrayList<String> anagrams = map.get(key);
        anagrams.add(s);
    }
    // Convert hash table to array
    int index = 0;
    for(String key : map.keySet()){
        ArrayList<String> list = map.get(key);
        for(String t : list){
            array[index] = t;
            index++;
        }
    }
}
