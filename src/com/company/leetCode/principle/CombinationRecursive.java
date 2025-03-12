package com.company.leetCode.principle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author suhe17
 * @since 2025/3/11
 */
public class CombinationRecursive {

    /**
     * 从给定字符串中选取k个字符的所有组合
     * @param input 输入字符串
     * @param k 需要选取的字符数
     * @return 所有可能的组合列表
     */
    public static List<String> combine(String input, int k) {
        List<String> result = new ArrayList<>();
        if (input == null || input.length() < k || k <= 0) return result;
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        backtrack(chars, 0, k, sb, result);
        return result;
    }

    private static void backtrack(char[] chars, int start, int k, StringBuilder current, List<String> result) {
        if (k == 0) {
            result.add(current.toString());
            return;
        }
        for (int i = start; i <= chars.length - k; i++) {
            current.append(chars[i]);
            backtrack(chars, i + 1, k - 1, current, result);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }


    /**
     * 生成从n个元素中选k个的所有组合
     * @param n 总元素数
     * @param k 选取元素数
     * @return 组合索引列表
     */
    public static List<List<Integer>> generateCombinations(int n, int k) {
        return IntStream.range(0, 1 << n)
                .filter(i -> Integer.bitCount(i) == k)
                .mapToObj(i -> IntStream.range(0, n)
                        .filter(j -> ((i >> j) & 1) != 0)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<String> combineStream(String input, int k) {
        List<List<Integer>> indices = generateCombinations(input.length(), k);
        return indices.stream()
                .map(indexList -> indexList.stream()
                        .map(input::charAt)
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String input = "abcde";
        List<String> combinations = combine(input, 3);
        combinations.forEach(System.out::println);

        List<String> combinations1 =combineStream(input, 3);

        combinations1.forEach(System.out::println);
    }


}
