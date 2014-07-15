package com.github.talberto.easycacheable.api;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class CacheableTest {

  public static class FibonacciComputer {
    
    @Cacheable
    public int computeNthTerm(int n) {
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
    FibonacciComputer computerSpy = spy(computer);
    
    computerSpy.computeNthTerm(5);
    
    // Verify computeNthTerm is called only once for each i in 1 .. n
    for(int i=1; i<=5; i++) {
      verify(computerSpy, times(1)).computeNthTerm(i);
    }
  }
}
