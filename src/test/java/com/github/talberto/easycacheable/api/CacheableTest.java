package com.github.talberto.easycacheable.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CacheableTest {

  @Cacheable
  public static class FibonacciComputer {
    
    int callsToCompute = 0;
    
    @Cacheable
    public int computeNthTerm(int n) {
      callsToCompute++;
      if (n == 1) { // First term is 1
        return 1;
      } else if (n == 2) { // Second term is 2
        return 2;
      } else {
        return computeNthTerm(n - 1) + computeNthTerm(n - 2);
      }
    }
  }

  @Test
  public void testCacheable() {
    FibonacciComputer computer = new FibonacciComputer();

    // The first time the function is called it will store all the results in the cache
    computer.computeNthTerm(3);
    int callsNo = computer.callsToCompute;
    
    // This time we will only call the real function once, for n=4), since the values for
    // n=1..3 are already calculated and stored in the cache
    computer.computeNthTerm(4);
    assertEquals(callsNo + 1, computer.callsToCompute);
  }
}
