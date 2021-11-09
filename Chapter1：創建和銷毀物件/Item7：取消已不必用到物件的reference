### 取消已不必用到物件的reference

來看一個作者刻意製作的範例，當`Stack` `pop(..)`資料出來時，並沒有真的把資料給清除掉，表示Java的gc機制沒有辦法對其進行回收，這時會造成memory leakage的問題：

``` Java
// Can you spot the "memory leak"?
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    /**
    * Ensure space for at least one more element, roughly
    * doubling the capacity each time the array needs to grow.
    */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

如果要讓它可以被回收，要先將用不到的物件替換成`null`：

``` Java
public Object pop() {
    if (size == 0)
        throw new EmptyStackException();
    Object result = elements[--size];
    elements[size] = null; // Eliminate obsolete reference
    return result;
}
```

這除了讓物件可以被回收外，也可以在物件被不正確的使用時能拋出`NullPointerException`，而不是默默地繼續執行。

另外兩個容易發生這種memory leakage分別是cache跟事件監聽器，cache有時在註冊了某些物件後，會遺忘它在內存中的存在，甚至已經不會使用了（這種cache問題，作者有提供另一種`WeakHashMap`作法，也就是弱引用，如果外部沒有其他物件參考到內部的key，JVM會在GC時順便把這個沒被參考的物件給回收掉）。而註冊事件監聽器則是擔心註冊後別人沒有取消註冊。
