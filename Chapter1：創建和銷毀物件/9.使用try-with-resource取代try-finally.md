### 使用try-with-resource取代try-finally

與其使用`finally`來自行關閉資源，使用`try-with-resource`來關閉資源會是更好的選擇，有一個最大的優勢在於coding block的可讀性，大幅提升不少（書中還有提到一個會互相蓋掉`Exception`的問題，不過目前看不懂XD，另外還有很多PG其實會呼叫到錯誤的`close` method），可以比較一下兩者的程式碼：

```Java
// try-finally is ugly when used with more than one resource!
static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
        OutputStream out = new FileOutputStream(dst);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }
}
```

``` Java
// try-with-resources on multiple resources - short and sweet
static void copy(String src, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src);
         OutputStream out = new FileOutputStream(dst)) {
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(buf)) >= 0)
            out.write(buf, 0, n);
    }
}
```
