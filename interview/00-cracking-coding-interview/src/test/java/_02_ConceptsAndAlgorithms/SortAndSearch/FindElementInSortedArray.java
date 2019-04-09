package _02_ConceptsAndAlgorithms.SortAndSearch;

import org.junit.Test;

/*
 Q: Given a rotated sorted (increasing order) array, find an element in the array.

 A:
 if array a[leftmost] < a[mid], search left half because left is normally ordered
 if array a[mid] < a[rightmost], search right half because right is normally ordered
  Array1: {10, 15, 20, 0, 5}
  Array2: {50, 5, 20, 30, 40}

  #when dups are allowed
  if a[leftmost] = a[mid], left all dups or right all dups
     if a[mid] != a[rightmost] search right half because right is normally ordered
     else search both halves
        search left half
        if not found, search right half
 */
public class FindElementInSortedArray {

    @Test
    public void Test() {
        int[] a = {10, 15, 20, 0, 5};
        System.out.println(search(a, 0, a.length - 1, 5)); // 4

        int[] b = {50, 5, 20, 30, 40};
        System.out.println(search(b, 0, b.length - 1, 5)); // 1

        int[] c = {2, 3, 2, 2, 2, 2};
        System.out.println(search(c, 0, c.length - 1, 2)); // 2
        System.out.println(search(c, 0, c.length - 1, 3)); // 1
        System.out.println(search(c, 0, c.length - 1, 8)); // -1
    }

    static int search(int[] a, int left, int right, int x) {
        int mid = (left + right) / 2;
        if (x == a[mid]) return mid; // Found element
        if (right < left) return -1;
        if (a[left] < a[mid]) { // Left is normally ordered
            if (x >= a[left] && x <= a[mid]) return search(a, left, mid - 1, x);
            else return search(a, mid + 1, right, x);
        } else if (a[mid] < a[left]) { // Right is normally ordered
            if (x >= a[mid] && x <= a[right]) return search(a, mid + 1, right, x);
            else return search(a, left, mid - 1, x);
        } else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) return search(a, mid + 1, right, x); // If right half is different, search there
            else { // Else, we have to search both halves
                int result = search(a, left, mid - 1, x);
                if (result == -1) return search(a, mid + 1, right, x);
                else return result;
            }
        }
        return -1;
    }
}
/*
output:
4
1
2
1
-1
 */
