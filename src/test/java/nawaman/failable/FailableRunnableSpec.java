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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import nawaman.failablej.Failable;
import nawaman.failablej.FailableException;

/**
 * This spec helps document the desired behaviors of any Failable runnable.
 * 
 * @author NawaMan -- nawaman@dssb.io
 */
public class FailableRunnableSpec {
    
    //== gracefully() and toRunnable() =
    
    /**
     * When called with 'gracefully()' the runnable throw failable if the body throws a non-runtime exception.
     * 
     * @param runnable  the runnable.
     */
    public void testRunGracefully_nonFailableException(Failable.Runnable<Throwable> runnable) {
        test_nonFailableException(runnable, ()->runnable.gracefully().run());
        test_nonFailableException(runnable, ()->runnable.toRunnable().run());
    }
    
    /**
     * When called with 'gracefully()' the runnable throw the runtime if the body throws a exception.
     * 
     * @param runnable  the runnable.
     */
    public void testRunGracefully_failableException(Failable.Runnable<Throwable> runnable) {
        test_failableException(runnable, ()->runnable.gracefully().run());
        test_failableException(runnable, ()->runnable.toRunnable().run());
    }
    
    
    
    /**
     * When called with 'gracefully()' the runnable throw failable if the body throws a non-runtime exception.
     * 
     * @param runnable  the runnable.
     */
    private void test_nonFailableException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        precondition_theRunnableMustThrowANonFailableException(runnable);
        
        specification_whenRunGrafullyRunnableThrowFailException(runnable, testBody);
    }
    
    private void precondition_theRunnableMustThrowANonFailableException(Failable.Runnable<Throwable> runnable) {
        // Check to make sure the runnable throw some exception that is not a FailableException.
        try {
            runnable.run();
            fail("Precondision rejected: The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            fail("Precondision rejected: The runnable throw a fail exception so we cannot be sure if that was from the implementation of 'gracefully'");
        } catch (Throwable e) {
            // This is expected.
        }
    }
    
    private void specification_whenRunGrafullyRunnableThrowFailException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        try {
            testBody.run();
            fail("The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            // This is expected.
        } catch (Throwable e) {
            fail("Expect a failable exception to be thrown not anything else.");
        }
    }
    
    

    /**
     * When called with 'gracefully()' the runnable throw the runtime if the body throws a exception.
     * 
     * @param runnable  the runnable.
     */
    private void test_failableException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        FailableException exception = precondition_theRunnableMustThrowAFailableException(runnable);
        
        specification_whenRunGrafullyRunnableThrowTheFailableException(runnable, testBody, exception);
    }
    
    private FailableException precondition_theRunnableMustThrowAFailableException(Failable.Runnable<Throwable> runnable) {
        // Check to make sure the runnable throw some exception that is not a FailableException.
        try {
            runnable.run();
            fail("Precondision rejected: The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            // This is expected.
            return e;
        } catch (Throwable e) {
            fail("Precondision rejected: The runnable throw a non failable exception - this is not a focus for this test");
        }
        return null;
    }
    
    private void specification_whenRunGrafullyRunnableThrowTheFailableException(Failable.Runnable<Throwable> runnable, Runnable testBody, FailableException exception) {
        try {
            testBody.run();
            fail("The runnable is expected to throw an exception for this test to make sense.");
        } catch (RuntimeException e) {
            // This is expected.
            assertEquals(exception.getMessage(),       e.getMessage());
            assertEquals(exception.getClass(),         e.getClass());
            assertEquals(exception.getStackTrace()[0], e.getStackTrace()[0]);
        } catch (Throwable e) {
            fail("Expect a runtime exception to be thrown not anything else.");
        }
    }
    
}
