Exercise-1:
-----------------------------------------------------------------------------------------------------------
1. Understand the Problem
Why Data Structures and Algorithms Are Essential:
Data structures and algorithms are crucial for managing large inventories due to the following reasons:
Efficiency: Efficient data structures enable quick data retrieval and updates, which are essential for handling large amounts of inventory data.
Scalability: As inventory grows, the system needs to scale efficiently. Proper data structures help in managing this growth effectively.
Performance: Algorithms dictate the speed of operations like searching, sorting, and updating data. Optimized algorithms reduce the time and resource usage.
Suitable Data Structures
For an inventory management system, the following data structures can be used:
ArrayList: Useful for storing a list of products. It allows dynamic resizing but has slower search and update operations as it requires linear time complexity.
HashMap: Ideal for quick access and retrieval based on keys (e.g., productId). It provides average O(1) time complexity for insert, update, and delete operations.
In this scenario, a HashMap is suitable because it provides fast access to products based on their productId.
package com.example.inventory;

public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
package com.example.inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Product> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    // Add a product
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Update a product
    public void updateProduct(String productId, Product newProduct) {
        if (inventory.containsKey(productId)) {
            inventory.put(productId, newProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Retrieve a product
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    // Display all products
    public void displayAllProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}
Analysis:
Data Structure: HashMap is used for efficient storage and retrieval.
Operations: addProduct, updateProduct, deleteProduct, and getProduct have average time complexity of O(1).
Optimizations: Consider initial capacity settings and concurrency handling to further optimize performance.
---------------------------------------------------------------------------------------------------------------
Exercise-2:
---------------------------------------------------------------------------------------------------------------
1. Understand Asymptotic Notation
Big O Notation: Big O Notation is a mathematical notation used to describe the upper bound of the time complexity of an algorithm, which gives an idea of how the algorithm's runtime grows relative to the size of the input.

Purpose: It helps in comparing the efficiency of algorithms and understanding how they scale with larger inputs.

Search Operations:
Best Case: The search operation finds the desired element in the best possible scenario. For linear search, this occurs when the element is at the beginning of the array. For binary search, this occurs when the middle element is the desired element.

Average Case: This is the expected time complexity for a typical search operation. For linear search, it's approximately O(n/2), which simplifies to O(n). For binary search, it's O(log n).

Worst Case: The search operation takes the longest possible time. For linear search, it occurs when the element is at the end of the array or not present at all, resulting in O(n). For binary search, it occurs when the element is not found after all divisions, resulting in O(log n).
2.set up:
--------
package com.example.search;

public class Product {
    private String productId;
    private String productName;
    private String category;
    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getCategory() {
        return category;
    }
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
Linear search algo:
------------------
package com.example.search;
public class SearchUtil {
    public static Product linearSearch(Product[] products, String searchId) {
        for (Product product : products) {
            if (product.getProductId().equals(searchId)) {
                return product;
            }
        }
        return null; // Not found
    }
}
Binary Search Algorithm:
-----------------------
package com.example.search;

import java.util.Arrays;
import java.util.Comparator;
public class SearchUtil {
    public static Product binarySearch(Product[] products, String searchId) {
        int left = 0;
        int right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(searchId);
            if (comparison == 0) {
                return products[mid];
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }
}
4.Analysis:
-----------
For an e-commerce platform where search performance is critical, binary search is preferred if the data can be sorted or if you can maintain a sorted order. For smaller datasets or when data is unsorted, linear search might be sufficient. Implementing efficient data handling and maintaining sorted data can greatly improve search performance in the platform.
--------------------------------------------------------------------------------------------------------------
Exercise-3:
--------------------------------------------------------------------------------------------------------------
1. Understand Sorting Algorithms
---------------------------------
Bubble Sort:
Description: Repeatedly compares adjacent elements and swaps if needed.
Time Complexity:
Best Case: O(n) (already sorted)
Average/Worst Case: O(n²)
Space Complexity: O(1)
Quick Sort:
Description: Divides the array using a pivot and sorts recursively.
Time Complexity:
Best/Average Case: O(n log n)
Worst Case: O(n²) (poor pivot choice)
Space Complexity: O(log n) (recursion stack)
2.set up
--------
public class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;
    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() { return totalPrice; }
    public String toString() {
        return "Order{" + "orderId='" + orderId + '\'' + ", customerName='" + customerName + '\'' + ", totalPrice=" + totalPrice + '}';
    }
}
3.Implement:
------------
public class SortUtil {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
}
public class SortUtil {
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}
4.Analysis:
-----------Bubble Sort:
Average/Worst Case: O(n²)
Best Case: O(n)
Quick Sort:
Average/Best Case: O(n log n)
Worst Case: O(n²)
Why Quick Sort is Preferred:

Efficiency: Quick Sort is generally faster with an average time complexity of O(n log n) compared to Bubble Sort’s O(n²).
Scalability: Better performance with larger datasets.
------------------------------------------------------------------------------------------------------------
Exercise-4:
------------------------------------------------------------------------------------------------------------
1. Understand Array Representation
----------------------------------
Array Representation:
In Memory: Arrays are stored in contiguous memory locations, which allows for efficient indexing and retrieval.
Advantages:
Constant-Time Access: O(1) time complexity for accessing elements by index.
Simple Structure: Easy to implement and use for small or fixed-size datasets.
2.set up:
---------
public class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;
    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
3.Implement:
------------
public class EmployeeManager {
    private Employee[] employees;
    private int size;
    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }
    public Employee searchEmployeeById(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }
    public void displayAllEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }
    public void deleteEmployeeById(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null; // Remove reference
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
4.Analysis:
-----------
Time Complexity:
Add: O(1) (if there’s space in the array)
Search: O(n) (linear search)
Traverse: O(n) (needs to visit each element)
Delete: O(n) (linear search + shifting elements)
Limitations of Arrays:
Fixed Size: Arrays have a fixed size, which may lead to inefficiencies if the number of employees changes frequently.
Inflexibility: Inserting or deleting elements requires shifting elements, which can be inefficient.
When to Use Arrays:

Fixed or Small Dataset: Ideal for small or fixed-size datasets where dynamic resizing isn’t needed.
Simple Operations: Suitable for scenarios where the dataset does not frequently change.
-----------------------------------------------------------------------------------------------------------
Exercise-5:
-----------------------------------------------------------------------------------------------------------
1. Understand Linked Lists
Singly Linked List:
-------------------
Structure: Each node contains data and a reference to the next node.
Operations:
Insertion: O(1) at head, O(n) at end.
Deletion: O(1) if node is known, O(n) to find node.
Traversal: O(n) to visit all nodes.
Doubly Linked List:

Structure: Each node contains data, a reference to the next node, and a reference to the previous node.
Operations:
Insertion: O(1) at head or tail, O(n) to find position.
Deletion: O(1) if node is known, O(n) to find node.
Traversal: O(n) forward and backward
2.set up:
---------
public class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
3.Implementation:
-----------------
class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}
public class TaskManager {
    private Node head;

    public TaskManager() {
        head = null;
    }
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    public Task searchTaskById(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId() == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
  }
    public void displayAllTasks() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }
    public void deleteTaskById(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.task.getTaskId() != taskId) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        } else {
            System.out.println("Task not found.");
        }
    }
}
4.Analysis:
-----------
Time Complexity:

Add: O(1) if adding at head, O(n) if adding at end (finding the end of the list).
Search: O(n) (need to traverse the list).
Traverse: O(n) (need to visit all nodes).
Delete: O(n) (finding the node to delete requires traversal).
Advantages of Linked Lists:

Dynamic Size: Can grow or shrink as needed without predefined size constraints.
Efficient Insertions/Deletions: More efficient than arrays for adding or removing elements, especially at the beginning or middle.
-------------------------------------------------------------------------------------------------------------
Exercise-6:
-------------------------------------------------------------------------------------------------------------
1. Understand Search Algorithms
-------------------------------
Linear Search:

Concept: Sequentially checks each book until the target book is found or the list ends.
Time Complexity: O(n) (where n is the number of books).
Binary Search:

Concept: Repeatedly divides the sorted list in half to find the target book.
Time Complexity: O(log n) (where n is the number of books), but requires a sorted list.
2.set up:
---------
public class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
3.Implementation:
-----------------
import java.util.List;

public class Library {
       public Book linearSearchByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; 
    }
}
import java.util.List;

public class Library {
        public Book binarySearchByTitle(List<Book> books, String title) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; 
    }
}
4.Analysis:
-----------
Linear Search:
Time Complexity: O(n). Suitable for unsorted lists or small datasets.
Binary Search:
Time Complexity: O(log n). Requires sorted lists. More efficient for large datasets.
Usage:
Linear Search: Use when the dataset is small or unsorted.
Binary Search: Use when the dataset is large and sorted.
------------------------------------------------------------------------------------------------------------
Exercise-7:
------------------------------------------------------------------------------------------------------------
1. Understand Recursive Algorithms
Recursion:
Concept: A method calls itself to solve smaller instances of the same problem.
Usage: Simplifies problems that can be divided into similar subproblems, such as calculating factorials or Fibonacci numbers.

2.set up:
---------
public class FinancialForecast {
    // Recursive method to calculate future value based on past growth rates
    public double predictFutureValue(double initialValue, double growthRate, int years) {
        if (years == 0) {
            return initialValue;
        }
        return predictFutureValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }
}
3.Implement:
------------
public class FinancialForecast {
    public double predictFutureValue(double initialValue, double growthRate, int years) {
        if (years == 0) {
            return initialValue;
        }
        return predictFutureValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        FinancialForecast forecast = new FinancialForecast();
        double futureValue = forecast.predictFutureValue(1000, 0.05, 10); // $1000, 5% growth, 10 years
        System.out.println("Future Value: " + futureValue);
    }
}
4.Analysis:
-----------
Time Complexity:

Recursive Method: O(n) (where n is the number of recursive calls, which is the number of years).
Optimization:

Memoization: Store results of previous computations to avoid redundant calculations.
Tail Recursion: Optimize recursion to iterative form if possible, though Java doesn’t optimize tail recursion.

Recursion simplifies problems involving repeated subproblems but can lead to inefficiencies or stack overflow for deep recursion. For financial forecasting, recursion can be effective for small datasets but may need optimization for larger datasets or deeper recursion levels.