package com.techelevator;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillTest {

    private Bill bill;

    @Before
    public void setup() {
        bill = new Bill(2000, "$20.00");
    }

    @Test
    public void testGetAmount() {
        assertEquals("Amount should be 2000", 2000, bill.getAmount());
    }

    @Test
    public void testSetAmount() {
        bill.setAmount(500);
        assertEquals("Amount should be updated to 500",500,bill.getAmount());
    }

    @Test
    public void testGetName() {
        assertEquals("Name should be $20.00","$20.00",bill.getName());
    }

    @Test
    public void testSetName() {
        bill.setName("$5.00");
        assertEquals("Name should be updated to $5.00", "$5.00", bill.getName());
    }
}
