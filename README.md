# Japanese Kana Utilities for Java
### 日本語
* **kanatools-java** とは、Java にて日本語文字処理を簡単にやってくれる小さなライブラリです。
* 数行のコードだけで、仮名やローマ字の変換等の処理が素早くできます。
* [詳細を見る...](http://mariten.github.io/kanatools-java/ja/)

### English
* **kanatools-java** is a small collection of utilities that make your life easier when dealing with Japanese text.
* Quickly process text containing kana characters with just a few lines of code.
* [Read more...](http://mariten.github.io/kanatools-java/en/)

# KanaConverter
* [日本語の参考資料](http://mariten.github.io/kanatools-java/ja/kana-converter/)
* [English Documentation](http://mariten.github.io/kanatools-java/en/kana-converter/)

# Download and Install
1. Fetch the [**JAR**](https://github.com/mariten/kanatools-java/blob/master/compiled/jar/kanatools.jar) from the **master** branch of this repository
2. Add to your lib directory or somewhere that is included in your Java CLASSPATH

# Development
### Requirements
* :coffee: Java 1.6+
* :ant: Ant
* :u6709: Japanese font for your editor or console

### Setup
Perform the following process on the command line
```bash
git clone https://github.com/mariten/kanatools-java.git
cd kanatools-java
ant test
```

When you see the `BUILD SUCCESSFUL` message you are good to go :thumbsup:

### Making Pull Requests
Please follow this process:

0. If you are considering major changes, please consult me before starting (even just briefly)
1. Fork this repository
2. Create a new branch name for your development branch (something unique, not `develop`)
3. Make your changes.  Please add or update unit tests accordingly.
4. Ensure that `ant test` completes successfully.  Pull requests with failing or buggy unit tests will be rejected.
5. Send the pull request and include an explanation of what your change accomplishes.
6. After a code review, your change will be merged into the master branch.
7. Depending on your change, I would appreicate it if you updated the documentation on the `gh-pages` branch as well.

**Looking forward to your pull requests!** :memo: :octocat:
