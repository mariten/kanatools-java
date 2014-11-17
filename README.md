# Japanese Kana Utilities for Java
**kanatools-java** is a small library that contains utilities for working with Japanese text like handling hiragana/katakana converesion as well as issues involved in handling *zenkaku* (full-width) and *hankaku* (half-width) characters specifically for Java.

# 総合カナ変換用ユーティリティ関数
**kanatools-java** とは、Java におけるひらがな、カタカナ、ローマ字のあらゆる全角・半角変換および考慮を自動で行うぷちライブラリー

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
