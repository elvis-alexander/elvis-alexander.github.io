package test;

import IteratorIterable.SampleIterable;
import org.junit.Assert;

import java.util.Iterator;

/**
 * Created by kingfernandez on 10/24/17.
 */
public class SampleIterableTest {

    @org.junit.Test
    public void iterableTest() {
        SampleIterable iterable = new SampleIterable(2);
        iterable.addItem(23);
        iterable.addItem(30);
        Iterator iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(23, iterator.next());
        Assert.assertEquals(30, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
