### Item2：當建構物件的參數過多時，改成Builder Pattern來建構物件

考慮底下的例子：
``` Java
class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
```

在建構這樣的物件時，通常會想到底下兩種做法，第一種是上面的例子，直接用建構子一次把所有的屬性設定好，但這樣的做法參數順序很容易搞混，這樣可能會建構出錯誤的物件。

第二種作法則是常見的`getter`、`setter`：

``` Java
public static void main(Stringp[] args) {
    NutritionFacts n = new NutritionFacts();
    n.setServingSize(240);
    // ...
    // ...
    n.setCarbohydrate(27);
}
```

然而這樣的做法就代表著在建構物件時這些程式碼一定是綁定在一起的，而且還有可能發生執行緒的問題，因此作者建議可以改用Builder Pattern來建構物件，這樣在物件建構好之前，都還會是Builder物件：

``` Java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;
        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
```

即使使用Builder Pattern的形式，物件還是可以提供給其他類去進行繼承，其子類別也可以使用Builder Pattern去創建物個。不過使用Builder Pattern可能會讓物件變得更冗長，因此建議只有在參數過多時在用Builder Pattern。

Lombok API有提供這樣的annotation可供使用，下次使用時不一定要單純地使用`@Data`了，可以改用`@Builder`。
