# CACHING

## Cache Introduction

**1.1 Cache Introduction**

- A cache is a hardware or Software component that temporarily stores data
- Future requests for that data can be served faster
- The data in cache:
  - A copy of data from data source
  - The result of an earlier computation
- Cache is a shield for DB
- Trade-off
  - Performance vs Cost ( Space )
  - Performance vs Consistency ( sometime )

**1.2 Where is cache used**

- Cache used:
  ![alt text](image-2.png)

## Cach Strategies

**2.1 Read Strategies**

- Read Strategies:

  - Read-Through
  - **Read-Aside**

- Read-Aside

  - Pros:
    - Tolerate cache failures
    - Flexible for data models
  - Cons:

    - Complex for app
    - Data inconsistency

    ![alt text](image-1.png)

**2.2 Write Strategies**

- Write Strategies:

  - Write-Through
  - Write-Back
  - **Write-Around**

- Write-Around

  - **Invalid cache asynchronously**
  - Pros:
    - Decoupling cache and storage systems
  - Cons:

    - Inefficient for Frequently Accessed Data
    - Data inconsistency

    ![alt text](image-3.png)

**2.3 Data inconsistency**

- The common problem: Data Inconsistency

- Solution: **Cache Invalidation**
- Cache Invalidation is removing data that is no longer valid or useful
- Types of Cache Invalidation:
  - **Time-based**
  - **Command-based**
  - **Event-based**
  - Group-based

![alt text](image-4.png)

**2.4 Write-Around**

![alt text](image-5.png)

**2.5 The First Try: Update Cache**

![alt text](image-6.png)

- Case 1: `Update cache first`
  ![alt text](image-7.png)

- Case 2: `Update cache later`
  ![alt text](image-8.png)

**2.6 The Second Try: Delete Cache**

![alt text](image-9.png)

**2.7 The Third Try: Read Aside + Delete Write Around**

![alt text](image-10.png)

- Case 1: `Delete cache first`
  ![alt text](image-11.png)

- Case 2: `Delete cache later`
  ![alt text](image-12.png)

**Answer**

![alt text](image-13.png)

`How can we mitigate the impact?`

> Add **short TTL** to cache data

## Challenges

**3.1 Reliability Chalenges**

---

**_3.1.1 Problem 01: No Atomicity_**

![alt text](image-14.png)

> Solutions :

> **Retry**

> Subscribe to binlog of DB

---

**_3.1.2 Problem 02: Cache Avalanche_**

![alt text](image-15.png)

![alt text](image-16.png)

---

**_3.1.3 Problem 03: Cache Breakdown_**

![alt text](image-17.png)

---

**_3.1.4 Problem 04: Cache Penetration_**

![alt text](image-18.png)

---

**3.2 High Traffic Challenges**

**_3.2.1 Problem 05: Hot keys_**

![alt text](image-19.png)

---

**_3.2.2 Problem 06: Large keys_**

![alt text](image-20.png)

---

**3.3 Cache replacement**

![alt text](image-21.png)

## Practice

![alt text](image-22.png)
