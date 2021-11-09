### 考慮使用靜態工廠方法建構物件

```Java
public class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }
}

class Main {
    public static void main(String[] args) {
        Product pr = new Product("手機");
    }
}
```

建構物件時，除了採用上面的方式去進行建構外，還可使用另一種方式，先將建構子用`private`隱藏，接著使用靜態方法來建構物件：

```Java
public class Product {
    private String name;

    private Product(String name) {
        this.name = name;
    }
    
    public static Product getPhone(){
        return new Product("手機");
    }
}

class Main {
    public static void main(String[] args) {
        Product pr = Product.getPhone();
    }
}
```

這麼做有幾個優點：

1. **靜態工廠方法，可以擁有清楚的命名方式**。使用建構子建構物件時不能帶有有意義的名字，全部都會是`class`的名稱，唯一能夠辨別的方式只能夠參數來辨別，這樣對於閱讀起來極為不方便，靜態工廠方法可改善這個問題，尤其當想要設定大量建構子的時候，這做法的好處會特別明顯。

2. **不一定要真正的創建物件，可以使用就已經存在的物件**，可以用來實踐Singleton或是Fly Weight（很常用在UI繪圖上），大量省下創建物件的成本。Java API中`Boolean.valueOf(boolean)`就是這樣的例子。

3. **與建構子不同，可以回傳子類別當作建構後的結果**，這樣可以用來實踐類似多型的效果：

```Java
public abstract class Cellphone {
    protected String name;

    protected Cellphone() {
    }

    public static Cellphone getIPhone(){
        return new iPhone("iPhone");
    }

    public static Cellphone getPixel(){
        return new Pixel("Pixel");
    }
}

class iPhone extends Cellphone {
    public iPhone(String name){
        super();
        this.name = name;
    }
}

class Pixel extends Cellphone {
    public Pixel(String name){
        super();
        this.name = name;
    }
}

class Main {
    public static void main(String[] args) {
        Cellphone iPhone = Cellphone.getIPhone();
        Cellphone pixel = Cellphone.getIPhone();
    }
}
```

不過除了優點外，也有一些缺點存在：

1. **如果`class`在定義時只有`private`建構子，那麼這樣的物件就沒有辦法被繼承**。不過換個角度想的話，可以說變相地鼓勵大家使用複合而不是繼承。
   
2. **相對起建構子，他們在API中並不會特別顯現出來**，這樣的話可以改用一些命名方式去特別標注，讓開發者清楚這些方法屬於靜態工廠方法，
像是`getInstance(..)、valueOf(..)`...



