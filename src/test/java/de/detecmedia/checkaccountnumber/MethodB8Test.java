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
public class MethodB8Test {

    public MethodB8Test() {
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
     * Test of test method, of class MethodB8.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        MethodB8 instance = new MethodB8();
        instance.setAccountNumber("5999999999");
        boolean expResult = true;
        boolean result = instance.test();
        assertEquals(expResult, result);

    }

    /**
     * Test of getFlag method, of class MethodB8.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        MethodB8 instance = new MethodB8();
        char[] expResult = "B8".toCharArray();
        char[] result = instance.getFlag();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of variant1 method, of class MethodB8.
     */
    @Test
    public void testVariant1() {
        System.out.println("variant1");
        int[] number = {0, 7, 3, 4, 1, 9, 2, 6, 5, 7};
        MethodB8 instance = new MethodB8();
        boolean expResult = true;
        boolean result = instance.variant1(number);
        assertEquals(expResult, result);

    }

    /**
     * Test of variant2 method, of class MethodB8.
     */
    @Test
    public void testVariant2() {
        System.out.println("variant2");
        int[] number = {3, 1, 4, 5, 8, 6, 3, 0, 2, 9};
        MethodB8 instance = new MethodB8();
        boolean expResult = true;
        boolean result = instance.variant2(number);
        assertEquals(expResult, result);

    }

    /**
     * Test of variant3 method, of class MethodB8.
     */
    @Test
    public void testVariant3() {
        System.out.println("variant3");
        int[] number = {5, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        MethodB8 instance = new MethodB8();
        boolean expResult = true;
        boolean result = instance.variant3(number);
        assertEquals(expResult, result);

    }

}
