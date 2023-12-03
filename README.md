## 1. Top-n email domains

### Solution Overview
The complete solution is accessible within the repository.

### Code Location
You can find the source code in the `src/` directory of the repository.

### Complexity Analysis
This solution is optimal because it uses efficient data structures (HashMap and PriorityQueue) to keep track of domain counts and retrieve the top domains. **The time complexity is O(n log k)**, where n is the number of email addresses and k is the number of distinct domains. **The space complexity is O(k).**

## 2. Auto parts compatibility
### Introduction:
The objective is to design a system capable of efficiently managing the compatibility of automobile parts within the platform, emphasizing scalability, performance, and adaptability to changes in the parts catalog.

### Data Model:
The solution is to store compatibility information directly in the parts table using a list structure to represent relationships. Each automobile part in the catalog will include key attributes like id, name, serial number, manufacturer, weight, and a field for compatibility data — a list of part IDs that are compatible with the respective part, creating a flexible and dynamic structure. The use of the list for compatibility allows for a streamlined structure that scales with the increasing volume of data. Traditional many-to-many relationships, especially when stored in a separate compatibility table, may introduce complexities in database optimization as the dataset expands. The approach simplifies the optimization process, ensuring scalability without compromising performance.

Example part entry in the catalog:

```json
{
"id": 567,
"name": "Engine",
"serial_number": "123567",
"manufacturer": "Subaru Ltd.",
"weight": 300,
"compatibility_ids": [456, 789, 1234],
"version": 1
}
```

### Scalability, Sharding, and Replicas:
To enhance scalability and performance, the approach involves implementing database sharding. NoSQL is considered for its ease of sharding setup and superior scalability. Sharding distributes data across multiple nodes, reducing the load on individual databases and improving overall performance.

To optimize sharding and achieve better performance, considerations can be made based on attributes such as creation To optimize sharding and achieve superior performance, considerations can be made based on attributes such as creation date, manufacturer, and part type. This strategic sharding approach ensures a more efficient distribution of data, further enhancing the system's scalability and responsiveness. Additionally, introducing replicas can contribute to performance optimization by allowing for parallelized read operations, further reducing the load on the system during peak usage.


### Cons and Optimistic Locking:
However, a challenge arises when adding or removing parts, necessitating updates to all compatible parts associated with the affected ones. To address potential performance issues, an optimistic locking mechanism is suggested. This strategy allows for concurrent updates by assuming that conflicts are rare. When updating compatibility information, the system checks for changes since the data was last read, reducing the risk of conflicts and improving overall performance during high-frequency catalog changes.

### Summary:
The proposed data storage model, along with NoSQL, database sharding, and replicas, presents a solution for handling the dynamic nature of automobile parts catalogs. Optimistic locking further enhances performance during frequent catalog updates, ensuring scalability and responsiveness in the face of evolving catalog requirements.
