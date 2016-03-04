# Japanese Kana Utilities for Java
[![Download](https://api.bintray.com/packages/mariten/maven/kanatools/images/download.svg)](https://bintray.com/mariten/maven/kanatools)
[![Build Status](https://travis-ci.org/mariten/kanatools-java.svg?branch=master)](https://travis-ci.org/mariten/kanatools-java)
[![Coverage Status](https://coveralls.io/repos/github/mariten/kanatools-java/badge.svg?branch=master)](https://coveralls.io/github/mariten/kanatools-java)

### 日本語
* **kanatools-java** とは、Java にて日本語文字処理を簡単にやってくれる小さなライブラリです。
* 数行のコードだけで、仮名やローマ字の変換等の処理が素早くできます。
* Java 1.5 以上で使用できます。
* [詳細を見る...](http://mariten.github.io/kanatools-java/ja/)

### English
* **kanatools-java** is a small collection of utilities that make your life easier when dealing with Japanese text.
* Quickly process text containing kana characters with just a few lines of code.
* Works with Java 1.5 and above
* [Read more...](http://mariten.github.io/kanatools-java/en/)

# Classes
### `KanaConverter`
* [日本語の参考資料](http://mariten.github.io/kanatools-java/ja/kana-converter/)
* [English Documentation](http://mariten.github.io/kanatools-java/en/kana-converter/)

##### Example
Code:
```java
import com.mariten.kanatools.KanaConverter;
...

String before = "かな変換ﾃｽﾃｨｰﾝｸﾞ｡　１－２－３";

int conv_op_flags = 0;
conv_op_flags |= KanaConverter.OP_HAN_KATA_TO_ZEN_KATA;    //半角カタカナを全角カタカナに変換
conv_op_flags |= KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII;  //全角英数字を半角英数字に変換

String after = KanaConverter.convertKana(before, conv_op_flags);
```

Effect:
```
before: "かな変換ﾃｽﾃｨｰﾝｸﾞ｡　１－２－３"
after:  "カナ変換テスティーング。 1-2-3"
```

# Download and Install
### Option 1: Auto-Include as Dependency in Build Tool
This library is available on the large public JAR repositories [**JCenter**](https://bintray.com/mariten/maven/kanatools) and [**Maven Central**](http://search.maven.org/#search|ga|1|com.mariten.kanatools)

#### Package Metadata
* **Group**
    * `com.mariten`
* **Artifact**
    * `kanatools`
* **Version**
    * [![Version](https://api.bintray.com/packages/mariten/maven/kanatools/images/download.svg)](https://bintray.com/mariten/maven/kanatools)

### Option 2: Directly Download JAR File
1. The JAR for each stable version release is hosted on Bintray - [**Download the latest here**](https://dl.bintray.com/mariten/maven/com/mariten/kanatools/)
2. Add to your lib directory or somewhere that is included in your Java CLASSPATH

# Development
### Requirements
* :coffee: Java
    * **JDK 1.5+ for users** (those who just want to include the JAR in their own project)
    * **JDK 1.6+ for contributors** (those who want to build and test the code in this library)
* :ant: Ant
    * :elephant: Gradle 2.1+ builds are supported as well
* :u6709: Japanese text display for your editor or console

### Setup
Perform the following process on the command line
```bash
git clone https://github.com/mariten/kanatools-java.git
cd kanatools-java
git checkout staging

# Compile and test with Ant
ant test

# Compile and test with Gradle
./gradlew check
```

When you see the `BUILD SUCCESSFUL` message you are good to go :thumbsup:

### Making Pull Requests
Please follow this process:

0. If you are considering major changes, please consult me before starting (even just briefly)
1. Fork this repository
2. Make your changes in a new branch.  Please add or update unit tests accordingly.
3. Ensure that `ant test` and `./gradlew check` complete successfully.  Pull requests with failing or buggy unit tests will be rejected.
4. Send the pull request to the **staging** branch and include an explanation of what your change accomplishes.  Please do not commit the Kanatools JAR file in your pull request, that is only done when a new version is released.
5. After a code review and upon passing all tests, your change will be merged into `staging`.
6. When the next version is released these changes will be reflected into the **master** branch and subsequently included to the JAR published to JCenter and Maven Central.
7. Depending on the nature of the change, updating the documentation in the `gh-pages` branch would be appreciated as well.

**Looking forward to your pull requests!** :memo: :octocat:
