//  ========================================================================
//  Copyright (c) 2017 Direct Solution Software Builders (DSSB).
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
package nawaman.failable;

import static nawaman.failablej.Failables.r;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import lombok.val;
import nawaman.failablej.FailableException;
import nawaman.failablej.Failable.Function2;

/**
 * In case, there are future diversion between the default implementation an this implementation
 * these tests ensure that they behave as expected.
 * 
 * @author NawaMan <nawaman@dssb.io>
 **/
@SuppressWarnings("javadoc")
public class FailableRunnableTest {
    
    private final FailableRunnableSpec spec = new FailableRunnableSpec();
    
    @Test
    public void testRunGracefully_nonRuntimeException() {
        spec.testRunGracefully_nonFailableException(r(()->{
            throw new IOException();
        }));
    }
    
    @Test
    public void testRunGracefully_runtimeException() {
        spec.testRunGracefully_failableException(r(()->{
            throw new FailableException(new NullPointerException());
        }));
    }
    
    @Test
    public void testCurrying() {
        val division = (Function2<Integer, Integer, Integer, RuntimeException>)(a,b)->a / b;
        assertEquals(5, division.of(10).of(2).intValue());
        
        val half = division.flip().asFunctionFor(2);
        assertEquals(5, half.of(10).intValue());
        
        val halfOf10 = half.asSupplierFor(10);
        assertEquals(5, halfOf10.value().intValue());
    }
    
}
