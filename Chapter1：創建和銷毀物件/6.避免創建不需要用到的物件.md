### 避免創建不需要用到的物件

來看一個相當極端的例子：

``` Java
String s = new String("bikini"); // DON'T DO THIS!
```

正常來說，創建這種`String`物件會直接用字面表達值去創建，也就是像`s = "bikini";`這樣的形式。除了這種極端的例子外，另外像是`Boolean.valueOf(..)`vs.`new Boolean(..)`（用`valueOf(..)`來創建有著更好的效能）或者是正則表達式的`Pattern`的物件，都具有這樣的特性（`Pattern`物件在構造時有極大的成本），底下是`Pattern`物件優化過後的情況：

``` Java
// Performance can be greatly improved!
static boolean isRomanNumeral(String s) {
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}

// Reusing expensive object for improved performance
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile(
        "^(?=.)M*(C[MD]|D?C{0,3})"
        + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```

最後則是基本型別以及裝箱過後的物件，能使用基本型別就直接使用基本型別：

``` Java
// Hideously slow! Can you spot the object creation?
private static long sum() {
    Long sum = 0L;
    for (long i = 0; i <= Integer.MAX_VALUE; i++)
        sum += i;
    return sum;
}
```
