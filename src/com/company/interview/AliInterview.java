package com.company.interview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AliInterview {


    //笔试题目:
//1、在分布式场景下，完善以下invoke方法，当用户同一次请求（即请求数据一样）在5分钟内多次发起的时候，能把之前的结果(即getResult方法的结果，getResult不用实现)直接返回回去。
//data -- 请求数据体
//    @RequestMapping("/invoke")
//    @ResponseBody
    public String invoke(String data) {
        String redisKey = "Invoke_Cache_key_";
        // data key 可能需要进行压缩
//        String cacheResult = redisClient.get(redisKey+data);
//        if(cacheResult != null ){
//            return cacheResult;
//        }
        // 假设data长度在1k~1M不等。
        String result = getResult(data);
        // 设置超时时间5分钟
//        redisClient.set(redisKey+data);
        //....
        return result;
    }


    //业务逻辑，不需要修改。该方法会调用外部HTTP服务获取相关信息，请求一次收费2元。
    private String getResult(String data) {
        return "业务逻辑，不需要修改。";
    }


//2、代码重构。
//以下代码在可读性、时间复杂度、代码冗余、健壮性等方面都存在一些问题，尤其是运行在线上并发环境下容易导致bug产生，请你针对这些问题进行代码重构.

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    public static boolean doCalCondition(String preContent, Integer calculationMethod, String value) {
//        if (calculationMethod.equals(CalculationMethod.STRING_EQUAL.getValue())) {
//            return preContent.equals(value);
//        } else if (calculationMethod.equals(CalculationMethod.STRING_CONTAIN.getValue())) {
//            return preContent.contains(value);
//        } else if (calculationMethod.equals(CalculationMethod.STRING_START_WITH.getValue())) {
//            return preContent.startsWith(value);
//        } else if (calculationMethod.equals(CalculationMethod.NOT_INCLUDE.getValue()) && value != null) {
//            if (value != null) {
//                String[] valItems = value.split("\\|");
//                for (String item : valItems) {
//                    if (item.equals(preContent)) {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        } else if (calculationMethod.equals(CalculationMethod.TIME_BEFORE.getValue())) {
//            long time = Long.parseLong(preContent);
//            int val = Integer.parseInt(value);
//            if (time < val) {
//                return true;
//            }
//        } else if (calculationMethod.equals(CalculationMethod.DATE_BEFORE.getValue())) {
//            try {
//                Date date = simpleDateFormat.parse(preContent);
//                Date date2 = simpleDateFormat.parse(value);
//                return date.before(date2);
//            } catch (ParseException e) {
//                throw new IllegalArgumentException("cannot parse data");
//            }
//        }
//        return true;
//    }


    //3、在线接口对qps有严格限制，请用java实现一个限流组件，需要在并发场景下兼顾性能，可以使用你熟悉的组件工具或者中间件来实现。
    class Request{
        String requestId;
        String data;
    }


    public boolean canEntry(Request request, Integer maxQps){
        //在线上分布式场景下，实现一套限流逻辑。
//        redisClient.setNx(RedisKey);
        return true;
    }

    //4、两个有序链表L1和L2，假设长度分别是n和m，请找到中位数，并评估其时空复杂度。
    public Integer findMid(List<Integer> list1, List<Integer> list2){
        //返回中间大的那个值！
        int n= list1.size();
        int m=list2.size();
        int totalLength = n+m;

        if(totalLength == 0){
            throw new RuntimeException("");
        }
        // 目标是第k+1 个元素
        int k = totalLength/2;
        // 双指针指向列表头
        int i=0;
        int j=0;
        Integer prev = 0;
        Integer current = 0;
        for(int count = 0;count <=k;count++){
            prev = current;
            if(i<n && (j>=m || list1.get(i) <= list2.get(j))){
                current = list1.get(i);
                i++;
            }else{
                current = list2.get(j);
                j++;
            }
        }

        if(totalLength % 2==1){
            return current;
        }else{
            return (prev + current)/2;
        }

    }

}
