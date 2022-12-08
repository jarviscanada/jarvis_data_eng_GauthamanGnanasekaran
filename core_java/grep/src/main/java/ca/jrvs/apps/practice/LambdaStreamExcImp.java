package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.xml.bind.SchemaOutputResolver;

public class LambdaStreamExcImp implements LambdaStreamExc{

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(s->s.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(s->!s.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  /**
   * Convert an instream to list
   * @param intStream
   * @return
   */
  @Override
  public List<Integer> toList(IntStream intStream) {
    ArrayList<Integer> list = new ArrayList<>();
    intStream.forEach(i->list.add(i));
//    ArrayList<Integer> list = intStream.collect(Collectors.toList());
    return list;
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start,end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.asDoubleStream().map(i->i = Math.sqrt(i));
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i->i%2==1);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return i-> System.out.println(prefix + i + suffix);
  }

  /**
   * Print each message with a given printer
   * Please use `getLambdaPrinter` method
   *
   * e.g.
   * String[] messages = {"a","b", "c"};
   * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
   *
   * sout:
   * msg:a!
   * msg:b!
   * msg:c!
   *
   * @param messages
   * @param printer
   */
  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    Arrays.stream(messages).forEach(printer);
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    intStream.mapToObj(String::valueOf).forEach(printer);
  }

  public static void main(String[] args) {
    final String[] string = {"hellp", "lorem", "ipsem", "sum", "How is it going"};
    final int[] ints = {1,21312,123123,2323,221,33,212,432};
    LambdaStreamExcImp lse = new LambdaStreamExcImp();

    System.out.println("createStrStream Test: ");
    Stream<String> testStream = lse.createStrStream(string);
    testStream.forEach(s-> System.out.println(s));

    System.out.println("toUpperCase test: ");
    Stream<String> testStreamUpper = lse.toUpperCase(string);

    System.out.println("filter test");
    Stream<String> filteredStream = lse.filter(lse.createStrStream(string),"i");

    System.out.println("createIntStream test: ");
    IntStream testStream2 = lse.createIntStream(ints);
    testStream2.forEach(i-> System.out.println(i));

    System.out.println("squareRootIntStream test: ");
    DoubleStream testStream3 = lse.squareRootIntStream(lse.createIntStream(ints));
    testStream3.forEach(i-> System.out.println(i));

    System.out.println("getOdd test: ");
    IntStream testStream4 = lse.getOdd(lse.createIntStream(ints));
    testStream4.forEach(i-> System.out.println(i));

    System.out.println("printMessages and lambdaPrinter test");
    Consumer<String> printer = lse.getLambdaPrinter("prefux", "suffix");
    lse.printMessages(string, printer);

    System.out.println("printOdd test");
    lse.printOdd(lse.createIntStream(ints),printer);


  }

}
