# Japanese Kana Utilities for Java
**kanatools-java** is a small library that contains utilities for working with Japanese text like handling hiragana/katakana converesion as well as issues involved in handling *zenkaku* (full-width) and *hankaku* (half-width) characters specifically for Java.

# 総合カナ変換用ユーティリティ関数
**kanatools-java** とは、Java におけるひらがな、カタカナ、ローマ字のあらゆる全角・半角変換および考慮を自動で行うぷちライブラリー

# KanaConverter
**KanaConverter** is a Java port of the [`mb_convert_kana`](http://www.php.net/manual/en/function.mb-convert-kana.php) function, which is a function that can perform every possible type of kana or Japanese text related conversion on a given input string in just a single line of code.

Fully supports all conversions between hankaku and zenkaku hiragana, katakana, and alphanumeric characters. Specify the input string and the desired conversion methods and it will do the rest.

### Download
1. Fetch the [**JAR**](https://github.com/mariten/kanatools-java/blob/master/compiled/jar/kanatools.jar) from the **master** branch of this repository
2. Add to your lib directory or somewhere that is included in your Java CLASSPATH
3. Include in your code with the `import mariten.kanatools.KanaConverter;` statement
4. Determine which conversion operations you want to perform: [Conversion Operation List](https://github.com/mariten/kanatools-java/blob/master/src/mariten/kanatools/KanaConverter.java#L11)
5. Call the static function wherever you like
```java
int conversion_ops = 0;
conversion_ops |= DESIRED_OP_1;
conversion_ops |= DESIRED_OP_2;
conversion_ops |= DESIRED_OP_3;
String new_string = KanaConverter.convertKana(input_string, conversion_ops);
```
