package leetcode

class P0146_LruCache(val capacity: Int) {
    /*
    146. LRU Cache

    https://leetcode.com/problems/lru-cache/

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists.
    Otherwise, add the key-value pair to the cache. If the number of keys exceeds
    the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:
    1 <= capacity <= 3000
    0 <= key <= 104
    0 <= value <= 105
    At most 2 * 105 calls will be made to get and put.

    Time complexity: O(1)
    Space complexity: O(N)
    */

    var size: Int = 0
    val cache = mutableMapOf<Int, Node>()
    val lruList = LruList()

    fun get(key: Int): Int {
        val existingNode = cache[key]
        if (existingNode != null) {
            lruList.remove(existingNode)
            lruList.addLast(existingNode)
            return existingNode.value
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        if (capacity == 0) return

        val existingNode = cache[key]
        if (existingNode != null) {
            existingNode.value = value
            lruList.remove(existingNode)
            lruList.addLast(existingNode)
            return
        }

        if (size == capacity) {
            val removed = lruList.removeFirst()
            cache.remove(removed.key)
            size--
        }

        val node = Node(key, value)
        cache[key] = node
        lruList.addLast(node)
        size++
    }

}

class Node(val key: Int, var value: Int, var prev: Node? = null, var next: Node? = null)

class LruList {
    val head = Node(-1, -1)
    val tail = Node(-1, -1)

    init {
        head.next = tail
        tail.prev = head
    }

    fun addLast(node: Node) {
        tail.prev!!.next = node
        node.prev = tail.prev
        node.next = tail
        tail.prev = node
    }

    fun removeFirst(): Node {
        val nodeToRemove = head.next!!
        head.next!!.next!!.prev = head
        head.next = head.next!!.next
        return nodeToRemove
    }

    fun remove(node: Node) {
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }
}