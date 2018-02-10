//  Copyright (c) 2017 Nawapunth Manusitthipol (NawaMan).
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

/**
 * Failable actions.
 * 
 * @author NawaMan -- nawa@nawaman.net
 */
public class Failables {
    
    private Failables() {
    }
    
    /**
     * Failable runnable.
     *   
     * @param <T> the throwable type.
     **/
    @FunctionalInterface
    public static interface Runnable<T extends Throwable> extends Failable.Runnable<T> {
        
        /**
         * Convenient factory method to allow lambda 
         * 
         * @param runnable  the runnable.
         * @return the failable runnable.
         * 
         * @param <T>  the type of the thrown exception.
         **/
        public static <T extends Throwable> Runnable<T> of(Runnable<T> runnable) {
            return runnable;
        }
        
    }
    
    /**
     * Failable consumer.
     * 
     * @param <V>  the type of the return value.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Supplier<V, T extends Throwable> extends Failable.Supplier<V, T> {
        
        /**
         * Convenient factory method to allow lambda
         *  
         * @param supplier the failable supplier
         * @return a failable supplier.
         * 
         * @param <V> the input data type.
         * @param <T>  the type of the thrown exception.
         **/
        public static <V, T extends Throwable> Supplier<V, T> of(Supplier<V, T> supplier) {
            return supplier;
        }
        
    }
    
    /**
     * Failable consumer. 
     * 
     * @param <V>  the value data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Consumer<V, T extends Throwable> extends Failable.Consumer<V, T> {
        
        /**
         * Convenient factory method to allow lambda.
         * 
         * @param consumer  the failable consumer.
         * @return  the failable consumer.
         * 
         * @param <V> the input data type.
         * @param <T>  the type of the thrown exception.
         **/
        public static <V, T extends Throwable> Consumer<V, T> of(Consumer<V, T> consumer) {
            return consumer;
        }
        
    }
    
    /**
     * Failable function.
     * 
     * @param <V>  the input data type.
     * @param <R>  the returned data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Function<V, R, T extends Throwable> extends Failable.Function<V, R, T>  {
        
        /**
         * Convenient factory method to allow lambda.
         * 
         * @param function  the failable function.
         * @return  the failable function.
         * 
         * @param <V> the input data type.
         * @param <R>  the returned data type.
         * @param <T>  the type of the thrown exception.
         **/
        public static <V, R, T extends Throwable> Function<V, R, T> of(Function<V, R, T> function) {
            return function;
        }
    }
    
    /**
     * Failable bifunction.
     * 
     * @param <V1> the input data type.
     * @param <V2> the input data type.
     * @param <R>  the returned data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface BiFunction<V1, V2, R, T extends Throwable> extends Failable.BiFunction<V1, V2, R, T>  {
        
        /**
         * Convenient factory method to allow lambda.
         * 
         * @param function  the failable bifunction.
         * @return  the failable bifunction.
         * 
         * @param <V1> the input data type.
         * @param <V2> the input data type.
         * @param <R>  the returned data type.
         * @param <T>  the type of the thrown exception.
         **/
        public static <V1, V2, R, T extends Throwable> BiFunction<V1, V2, R, T> of(BiFunction<V1, V2, R, T> function) {
            return function;
        }
    }
    
}
