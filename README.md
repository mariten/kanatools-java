# Japanese Kana Utilities for Java
**kanatools-java** is a small library that contains utilities for working with Japanese text like handling hiragana/katakana converesion as well as issues involved in handling *zenkaku* (full-width) and *hankaku* (half-width) characters specifically for Java.

# 総合カナ変換用ユーティリティ関数
**kanatools-java** とは、Java におけるひらがな、カタカナ、ローマ字のあらゆる全角・半角変換および考慮を自動で行うぷちライブラリー

# KanaConverter
**KanaConverter** is a Java port of the [`mb_convert_kana`](http://www.php.net/manual/en/function.mb-convert-kana.php) function, which is a function that can perform every possible type of kana or Japanese text related conversion on a given input string in just a single line of code.

Fully supports all conversions between hankaku and zenkaku hiragana, katakana, and alphanumeric characters. Specify the input string and the desired conversion methods and it will do the rest.

### Usage
1. Include in your code with the `import mariten.kanatools.KanaConverter;` statement
2. Determine which conversion operations you want to perform: [Conversion Operation List](https://github.com/mariten/kanatools-java/blob/master/src/mariten/kanatools/KanaConverter.java#L11)
3. Call the static function wherever you like
```java
int conversion_ops = 0;
conversion_ops |= DESIRED_OP_1;
conversion_ops |= DESIRED_OP_2;
conversion_ops |= DESIRED_OP_3;
String new_string = KanaConverter.convertKana(input_string, conversion_ops);
```

### Examples
##### Converting Between Katakana and Hiragana
* Convert *zen-kaku* hiragana to *zen-kaku* katakana
```java
String testing_hira = "てすてぃんぐ";

// Set the single conversion flag in a flag-based integer
int conversion_flag = KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA;

// Convert the string
String testing_kata = KanaConverter.convertKana(testing_kata, conversion_flag);
System.out.println(testing_kata);
```
Prints:
```
テスティング
```

##### Use Standard Katakana and Alphanumeric Characters
* Convert *han-kaku* katakana characters to *zen-kaku*
* Convert *zen-kaku* alphanumeric characters to *han-kaku*
```java
String address = "東京都北区赤羽６−３０−１　赤羽ﾋﾙｽﾞ"

// Set the necessary conversion flags in a flag-based integer
int conversion_flags = 0;
conversion_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
conversion_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
conversion_flags |= KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC;
conversion_flags |= KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE;

// Convert the string
String standardized_address = KanaConverter.convertKana(address, conversion_flags);
System.out.println(standardized_address);
```
Prints:
```
東京都北区赤羽6-30-1 赤羽ヒルズ
```


# Download and Install
### Quicker Option: Download Pre-Built JAR from GitHub
1. Fetch the [**JAR**](https://github.com/mariten/kanatools-java/blob/master/compiled/jar/kanatools.jar) from the **master** branch of this repository
2. Add to your lib directory or somewhere that is included in your Java CLASSPATH

### Slower Option: Clone this Repository and Build the JAR Using Ant
Perform the following process on the command line
```bash
git clone https://github.com/mariten/kanatools-java.git
cd kanatools-java
ant jar

# ...
# Wait for the "BUILD SUCCESSFUL" message
# ...

cp compiled/jar/kanatools.jar wherever/your/Java/CLASSPATH/is
```

# Development
### Requirements
* Java 6+
* Ant
* Japanese font for your editor or console

### Making Pull Requests
**I welcome and await pull requests from any and all!** However, I request you follow the below process:

If you are considering major changes that will take you a lot of time, please consult me before starting (even just a quick message with a short summary is fine).

1. Fork this repository
2. Create a new branch name for your development branch (something unique, not `develop`)
3. Make your changes.  If adding new functionality, please add unit tests that cover this new functionality.
4. **Ensure that `ant test` completes successfully**.  Pull requests with failing or erroroneous unit tests will be rejected.
5. Send the pull request and include a simple explanation of what your change accomplishes.  If it is a complex change, please document it accordingly.
6. After a code review, your change will be merged into the master branch.
