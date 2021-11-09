### 利用依賴注入來取代寫死的內部資源

有些`class`會使用內部的其他`class`當作一種底層的元件，如果將其直接寫死會缺乏彈性，這時會傾向用依賴注入的方式來進行管理：

``` Java
// Inappropriate use of static utility - inflexible & untestable!
public class SpellChecker {
    private static final Lexicon dictionary = ...;
    private SpellChecker() {} // Noninstantiable
    public static boolean isValid(String word) { ... }
    public static List<String> suggestions(String typo) { ... }
}
```

``` Java
// Inappropriate use of static utility - inflexible & untestable!
public class SpellChecker {
    private final Lexicon dictionary = ...;
    private SpellChecker(...) {}
    public static INSTANCE = new SpellChecker(...);
    public boolean isValid(String word) { ... }
    public List<String> suggestions(String typo) { ... }
}
```

上面的兩種作法都缺乏了彈性，如果要達到真正的彈性，應該使用以下的方式進行注入，畢竟不一定都只會使用同一本字典來查找答案：

``` Java
// Dependency injection provides flexibility and testability
public class SpellChecker {
    private final Lexicon dictionary;
    public SpellChecker(Lexicon dictionary) {
    this.dictionary = Objects.requireNonNull(dictionary);
    }
    public boolean isValid(String word) { ... }
    public List<String> suggestions(String typo) { ... }
}
```
