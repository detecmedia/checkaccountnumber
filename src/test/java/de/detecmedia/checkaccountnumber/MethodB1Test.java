/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.detecmedia.checkaccountnumber;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Markus Potthast <mpt@detecmedia.de>
 */
public class MethodB1Test {

    public MethodB1Test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of test method, of class MethodB1.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        MethodB1 instance = new MethodB1();
        instance.setAccountNumber("8347251693");
        boolean expResult = true;
        boolean result = instance.test();
        assertEquals(expResult, result);

    }

    /**
     * Test of getFlag method, of class MethodB1.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        MethodB1 instance = new MethodB1();
        char[] expResult = "B1".toCharArray();
        char[] result = instance.getFlag();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of variant1 method, of class MethodB1.
     */
    @Test
    public void testVariant1() {
        System.out.println("variant1");
        int[] number = {1, 4, 3, 4, 2, 5, 3, 1, 5, 0};
        MethodB1 instance = new MethodB1();
        boolean expResult = true;
        boolean result = instance.variant1(number);
        assertEquals(expResult, result);

    }

    /**
     * Test of variant2 method, of class MethodB1.
     */
    @Test
    public void testVariant2() {
        System.out.println("variant2");
        int[] number = {7, 4, 1, 4, 3, 9, 8, 2, 6, 0};
        MethodB1 instance = new MethodB1();
        boolean expResult = true;
        boolean result = instance.variant2(number);
        assertEquals(expResult, result);

    }

}
