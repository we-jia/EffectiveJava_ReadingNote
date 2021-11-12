### 始終要覆寫toString

雖然不是強制規定，不過在編寫`class`時作者會建議也一併覆寫`toString(..)`method，`toString(..)`method容易在各個地方自動呼叫，像是各種`print(..)`method，比起對我們來說沒什麼意義的hashcode，覆寫掉它轉換為有意義的文字會更好，比如以`PhoneNumber`來說，我們會期待看到`707-867-5309`而不是`PhoneNumber@163b91`，底下是直接將其覆寫的範例：

指定格式的版本：

``` Java
/**
* Returns the string representation of this phone number.
* The string consists of twelve characters whose format is
* "XXX-YYY-ZZZZ", where XXX is the area code, YYY is the
* prefix, and ZZZZ is the line number. Each of the capital
* letters represents a single decimal digit.
*
* If any of the three parts of this phone number is too small
* to fill up its field, the field is padded with leading zeros.
* For example, if the value of the line number is 123, the last
* four characters of the string representation will be "0123".
*/
@Override
public String toString() {
    return String.format("%03d-%03d-%04d",
        areaCode, prefix, lineNum);
}
```

不指定格式，只敘述意圖：

``` Java
/**
* Returns a brief description of this potion. The exact details
* of the representation are unspecified and subject to change,
* but the following may be regarded as typical:
*
* "[Potion #9: type=love, smell=turpentine, look=india ink]"
*/
@Override
public String toString() { ... }
```

這兩種版本都沒問題，只不過如果詳細地敘述格式，那麼之後若更改了規格，開發人員必須自行承擔這樣的結果。
