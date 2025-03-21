package com.company.interview;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class Node{
        int key;
        int value;
        Node prev; // 前驱节点
        Node next; // 后继节点
        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;// 容量

    private final Map<Integer,Node> map;// 哈希表：键到节点的映射

    private Node head;// 头节点

    private Node tail;// 尾节点

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }


    // 获取值，节点移动到头部
    public Integer get(int key){
        Node node = map.get(key);
        if (node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    // 移动到头部
    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }
    // 删除链表指定节点
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.prev = head; // 新节点的prev指向头节点
        node.next = head.next; // 新节点的next指向原头节点的下一个节点
        head.next.prev = node;// 原头节点的下一个节点的prev指向新节点
        head.next = node;// 头节点的next指向新节点
    }

    public void put(int key,int value){
        Node node = map.get(key);
        if(node == null){
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            addToHead(newNode);
            if (map.size() > capacity){
                Node tailNode = removeTail();
                map.remove(tailNode.key);
            }
        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    // 移除最久未使用
    public Node removeTail(){
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

}
