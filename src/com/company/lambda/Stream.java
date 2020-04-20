package com.company.lambda;

import com.company.Trader;
import com.company.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {

    public static void main(String[] args) {
	// write your code here
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(raoul, 2011, 500),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
        List<Transaction> l = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(l);

        List<String> l2= transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(l2);

        List<Trader> l3= transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(trader -> trader.getName()))
                .collect(Collectors.toList());
        System.out.println(l3);

        String a = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1,n2)->n1+n2);

        System.out.println(a);

        boolean aa = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .filter(c->c.equals("Cambridge"))
                .findAny()
                .isPresent();

        System.out.println(aa);

        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        Optional<Integer> i = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(i.get());

        transactions.stream()
                .reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2);

        IntStream even = IntStream.rangeClosed(1,100)
                .filter(n->n%2==0);
        System.out.println(even.count());






    }
}
