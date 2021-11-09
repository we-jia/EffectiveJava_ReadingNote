### Item3：考慮用`enum`來建構Singleton

建構Singleton通常會想到兩種作法：

1. 直接建立`public static final`物件供外部使用：
   
``` Java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() { ... }
    public void leaveTheBuilding() { ... }
}
```

2. 使用一個`static` method進行lazy loading：

``` Java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { ... }
    public static Elvis getInstance() { return INSTANCE; }
    public void leaveTheBuilding() { ... }
    }
```

這兩種作法都可以辦到Singleton（第二種靜態工廠方法可能還更有彈性），不過若Client若使用映射，是可以打破`private` method的限制，或者今天經過以下程式碼序列化，Singleton的結構就會被打破：

``` Java
public class Cellphone implements Serializable {
    public static Cellphone singleton;

    public int number = 886;

    public transient int price = 100;

    private Cellphone() {
    }

    public static Cellphone getCellphone(){
        if(singleton == null){
            singleton = new Cellphone();
        }
        return singleton;
    }
}

class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cellphone c1 = Cellphone.getCellphone();

        FileOutputStream fileOutputStream
                = new FileOutputStream("yourfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(c1);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("yourfile.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Cellphone c2 = (Cellphone) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(c2.number);
        System.out.println(c2.price); // transient的屬性會消失，值會變成0

        System.out.println(c1 == c2);
    }
}
```

因此建議可以嘗試`enum`來做Singleton（雖然使用起來可能沒那麼直觀，但可以防止上述的問題）：

``` Java
public enum Elvis {
    INSTANCE;
    public void leaveTheBuilding() { ... }
}
```
