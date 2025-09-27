Assignment 1 – Divide and Conquer Algorithms
Project Structure

Metrics.java – tracks time, comparisons, allocations, and recursion depth.

InsertionSort.java – basic sort for small arrays.

MergeSort.java – merge sort with reusable buffer and cut-off.

QuickSort.java – quick sort with random pivot and smaller-first recursion.

DeterministicSelect.java – k-th element selection using Median-of-Medians.

ClosestPair.java – closest pair in 2D points (O(n log n)).

CSVWriter.java – writes metrics to results.csv.

Main.java – runs all algorithms and generates CSV.

Architecture Notes

All algorithms use safe recursion patterns.

MergeSort and QuickSort use cut-off for small arrays to reduce recursion depth.

Metrics tracks time, depth, comparisons, and allocations.

ClosestPair uses x/y split and strip scan by neighbors.

Recurrence Analysis

MergeSort: T(n) = 2T(n/2) + Θ(n) → Θ(n log n) (Master Theorem Case 2)

QuickSort: random pivot, expected depth O(log n), Θ(n log n) average

DeterministicSelect: T(n) = T(n/5) + T(7n/10) + Θ(n) → Θ(n) (Median-of-Medians)

ClosestPair: T(n) = 2T(n/2) + Θ(n) → Θ(n log n)

Results

Results are saved in results.csv.

Include: algorithm, size n, execution time, comparisons, allocations, and max recursion depth.

Plots

(insert charts: time vs n, depth vs n, e.g., screenshots from Excel)

Summary

Theoretical estimates match experimental data.

Small arrays are processed faster due to cut-off and InsertionSort.

Randomized QuickSort maintains recursion depth around O(log n).