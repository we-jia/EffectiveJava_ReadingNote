### 使用`private` constructor來讓物件不能實例化

有時有些util的`class`，它所有的method、property全都設定為`static`（比如`Arrays`、`Collections`...），這種`class`其實相對起來不希望別人去進行實例化，因此可以直接將constructor設定為private讓別人直接呼叫即可，只是這樣就不能夠被其他人繼承。

另一種作法是將其定義為`abstract class`，這樣的做法可能會造成別人的誤解，以為這樣的`class`是要提供給其他人繼承（可以取捨要用第一種還是第二種）。
