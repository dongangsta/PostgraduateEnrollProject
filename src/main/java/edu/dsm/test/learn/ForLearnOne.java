package edu.dsm.test.learn;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: ForLearnOne
 * Description:
 * Author: dong
 * Date: 2024/2/29 11:25
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ForLearnOne {
    private static final Logger logger = LoggerFactory.getLogger(ForLearnOne.class);

    @Test
    public void fuc1001IterateArrayList() {
        // 遍历ArrayList的三种方法
        List<String> list = new ArrayList<>();
        list.add("小");
        list.add("问");
        list.add("题");
        //第一种遍历方法使用 For-Each 或for循环遍历 List
        for (String str:list) {logger.info(str);}
        //第二种遍历，把链表变为数组相关的内容进行遍历
        String[] strArray = list.toArray(new String[0]);
        for (String s : strArray) {logger.info(s);}
        //第三种遍历，使用迭代器进行相关遍历
        Iterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            logger.info(iterator.next());
        }
        //第三点五种遍历，forEachRemaining本质是 while(iterator.hasNext()){iterator.next()} 操作
        Iterator<String> iterator2 = list.listIterator();
        iterator2.forEachRemaining(logger::info);
    }

    public static void main(String[] args) {
        // 遍历ArrayList的三种方法
        List<String> list = new ArrayList<>();
        list.add("小");
        list.add("问");
        list.add("题");
        //第一种遍历方法使用 For-Each 或for循环遍历 List
        for (String str:list) {
            System.out.println(str);
        }
        //第二种遍历，把链表变为数组相关的内容进行遍历
        String[] strArray = list.toArray(new String[0]);
        for (String s : strArray) {
            System.out.println(s);
        }
        //第三种遍历，使用迭代器进行相关遍历
        Iterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //第三点五种遍历，forEachRemaining本质是 while(iterator.hasNext()){iterator.next()} 操作
        Iterator<String> iterator2 = list.listIterator();
        iterator2.forEachRemaining(System.out::println);
    }

    /* List提供了一个将List转为数组的一个非常方便的方法toArray。
    toArray有两个重载的方法：
    1. Object[] toArray() 返回按适当顺序包含列表中的所有元素的数组（从第一个元素到最后一个元素）。
        public Object[] toArray() {
            return Arrays.copyOf(elementData, size);
        }
    2. <T> T[]	toArray(T[] a) 返回按适当顺序（从第一个元素到最后一个元素）包含列表中所有元素的数组；
        public <T> T[] toArray(T[] a) {
            // 传入的数组长度小于实际数组长度重新分配空间并复制数组
            if (a.length < size) return (T[]) Arrays.copyOf(elementData, size, a.getClass());
            //复制数组
            System.arraycopy(elementData, 0, a, 0, size);
            //旧数组长度作为索引，指向新数组的空间赋值为null
            if (a.length > size) a[size] = null;
            return a;
        }
        返回数组的运行时类型是指定数组的运行时类型。
    */
    // myNote 此处可以解释为什么list.toArray(new String[0])就可以把List转换成对应的数组，根据toArray的源码，传入的数组长度小于实际长度重新分配空间并复制数组
    // myNote a[size] = null是为了处理当传入的数组a比列表的大小size要大的情况 gpt表示是为了避免内存泄漏：如果数组a之前已经包含了对象引用，而这些对象不再需要被使用，将对应位置设置为null可以帮助垃圾收集器回收这些不再需要的对象，从而避免内存泄漏。

    @SuppressWarnings("unchecked")
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }
    /*
    如果判断为真,即原数组是Object类型数组，则直接创建一个给定长度newLength的新的Object数组
    (创建新数组是因为这个函数目的就是复制一个数组的指定部分到一个新数组)
    如果判断为假,即原数组不是Object类型数组,则调用(T[])Array.newInstance(newType.getComponentType(), newLength)
    这个方法是新建一个数组,数组类型为newType中元素的类型(默认返回Object类型,可强制转换为正确类型),长度为指定的newLength.

    Array.newInstance内部直接调用Array.newArray，newArray为本地方法，由虚拟机实现
    */
    /*
    Java数组的复制操作可以分为深度复制和浅度复制，简单来说深度复制，可以将对象的值和对象的内容复制；浅复制是指对对象引用的复制。
    System中提供了一个native静态方法arraycopy()，可以使用这个方法来实现数组之间的复制。
    对于一维数组来说，这种复制属性值传递，修改副本不会影响原来的值。
    对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
    */
    // myNote 我理解这部分which means——只能复制一个维度，二维及以上则是浅复制，只有在一维的是深复制
    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    @Test
    public void func1002TestCopyOf(){
        Object o = Array.newInstance(String.class, 10);
        // Array.newInstance的返回虽然是Object类型，但是它实质上是String数组，可以强制转换成String[]
        logger.info(String.valueOf(o.getClass()));
        logger.info(String.valueOf(String.class));
    }

    /* 对比foreach和iterator：
    逻辑角度的：
    1.  当需要删除序列元素时，foreach循环会发生错误
    如果在循环的过程中调用集合的remove()方法，就会导致循环出错，因为循环过程中list.size()的大小变化了，就导致了错误。
    所以，如果想在循环语句中删除集合中的某个元素，就要用迭代器iterator的remove()方法，因为它的remove()方法不仅会删除元素，
    还会维护一个标志，用来记录目前是不是可删除状态，例如，你不能连续两次调用它的remove()方法，调用之前至少有一次next()方法的调用。
    效率角度的：
    2.  采用ArrayList对随机访问比较快，而for循环中的get()方法，采用的即是随机访问的方法，因此在ArrayList里，for循环较快
        采用LinkedList则是顺序访问比较快，iterator中的next()方法，采用的即是顺序访问的方法，因此在LinkedList里，使用iterator较快
    3.  使用 Iterator 的好处在于可以使用相同方式去遍历集合中元素，而不用考虑集合类的内部实现（只要它实现了 java.lang.Iterable 接口）
        如果使用 Iterator 来遍历集合中元素，一旦不再使用 List 转而使用 Set 来组织数据，那遍历元素的代码不用做任何修改
        如果使用 for 来遍历，那所有遍历此集合的算法都得做相应调整,因为List有序,Set无序,结构不同,他们的访问算法也不一样
    */
    /* 逻辑角度的原因——Iterator next方法实现源码：
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
               throw new ConcurrentModificationException();
        }
    */
    /*
    在next方法中首先调用了checkForComodification，该方法会判断modCount是否等于expectedModCount，不等于就会抛出java.util.ConcurrentModificationExcepiton。
    注：modCount是ArrayList的一个属性，继承自抽象类AbstractList，用于表示ArrayList对象被修改次数（add、remove、clear、ensureCapacityInternal均会改变modCount值）。
    在创建Iterator的时候会将modCount赋值给expectedModCount，之后expectedModCount不再改变。
    设置该检查的目的是为了阻止程序员在不允许修改的时候修改对象，Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变。
    当索引指针往后移动的时候就找不到要迭代的对象，所以禁止迭代时修改对象，于是会抛出错误。
    在执行next方法时，将会检查modCount与expectedModCount值，由于foreach方法中，其内部由iterator实现，也会调用next()方法，
    但是，每次删除元素时均会改变modCount值，因此，调用序列自身的remove()方法后再次调用next()方法时，
    经检查modCount != expectedModCount，因此抛出ConcurrentModificationException异常
　　然而，在使用迭代器时，对迭代中的元素进行删除并不会抛出错误，原因是：
    在iterator.remove()方法中，同样调用了ArrayList自身的remove方法，但是调用完之后并非就return了，
    而是expectedModCount = modCount重置了expectedModCount值，使二者的值继续保持相等。
    同时，Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性，不会导致索引指针移动时找不到迭代对象。
    下面使用迭代器的语句同样会抛出ConcurrentModificationException异常：
    Iterator iter  = subjects.iterator();
    while(iter.hasNext()){
           subject = iter.next();
           if(subject.startsWith("6.")){
               subjects.remove(subject);//仅将iter.remove()更改为subjects.remove(subject)
           }
    }
    * */
    @Test
    public void func1003(){

    }

}
