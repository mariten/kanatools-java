2014 Oct 31 :: 0.5.0
======================
* Initial usable version of `KanaConverter`, a Java port of the `mb_convert_kana` function from PHP.
* With one line of code, performs any desired zenkaku/hankaku related conversions for Hiragana, Katakana, as well as ASCII alphanumeric and symbol characters.
* The public function, `mbConvertKana`, is designed so that users specify which of all the available conversions they want performed on a given string.
* Unit tests for each single method that cover each conversion types full character span, as well as a mixed input test string

2014 Jun 11 :: 0.1.0
======================
* Initial beta implementation of `KanaConverter`, a Java port of the `mb_convert_kana` function from PHP.
* Partial functionality, handles most conversion methods except those related to Hankaku Katakana
* Unit tests for each method via JUnit
