package com.qt.commonutils;

import org.testng.Assert;

import java.util.List;

/**
 * This Class has all the Objects related to AssertUtils.
 *
 * @author
 */
public class AssertUtils {

    /**
     * Asserts that two lists are equal.
     *
     * @param actualList   The actual list.
     * @param expectedList The expected list.
     * @param listName     The name of the list being compared.
     */
    public void assertListEquals(List actualList, List expectedList, String listName) {
        assertListContains(actualList, expectedList, listName);
        Assert.assertEquals(actualList.size(), expectedList.size(),
                "Value count in list" + listName + "  didn't match. \nExpected list values:  " + expectedList + ", \nActual List values:  " + actualList);
    }

    /**
     * Asserts that two lists are equal, considering the order of elements.
     *
     * @param actualList   The actual list.
     * @param expectedList The expected list.
     * @param listName     The name of the list (for better error reporting).
     */
    public void assertListEqualsWithOrder(List actualList, List expectedList, String listName) {
        assertListEquals(actualList, expectedList, listName);
        Assert.assertEquals(actualList, expectedList,
                "List order didn't match, \nExpected: " + expectedList + "\nActual: " + actualList);
    }

    /**
     * Asserts that all elements in the expected list are present in the actual list.
     *
     * @param actualList   The actual list.
     * @param expectedList The expected list.
     * @param listName     The name of the list (for better error reporting).
     */

    public void assertListContains(List actualList, List expectedList, String listName) {
        for (Object expectedValue : expectedList) {
            Assert.assertTrue(actualList.contains(expectedValue),
                    "Value (" + expectedValue + ") is not present in List '" + listName + "'. \nActual list values: "
                            + actualList + "\nExpected list values: " + expectedList);
        }
    }

    /**
     * Asserts that none of the elements in the expected list are present in the actual list.
     *
     * @param actualList   The actual list.
     * @param expectedList The list of values not expected to be present.
     * @param listName     The name of the list (for better error reporting).
     */

    public void assertListNotContains(List actualList, List expectedList, String listName) {
        for (Object expectedValue : expectedList) {
            Assert.assertTrue(!actualList.contains(expectedValue), "Value (" + expectedValue + ") is present in List '"
                    + listName + "'. \nActual list values: " + actualList + "\nExpected list values: " + expectedList);
        }
    }
}
