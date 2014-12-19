v1.0.0 :: Dec 19 2014
======================
* Initial fully-operational version of **KanaConverter**
* Include space character conversion in zenkaku/hankaku ascii conversions (breaking with `mb_convert_kana`, but more convenient)
* Collapse hankaku diacritic marks by default (breaking with `mb_convert_kana`, but more convenient).  Removed operation to collapse marks (`OP_COLLAPSE_HANKAKU_VOICE_MARKS`), and then added new operation to request not collapsing diacritic marks (`OP_KEEP_DIACRITIC_MARKS_SEPARATE`).
* Shortened names of operation constants, makes for easier calling
* Do not exclude conversion of `'`, `"`, and `\` when performing zenkaku or hankaku ASCII conversions (breaking with and PHP's `mb_convert_kana`).  This is an unnecessary safety check (MySQL and/or programmatic escaping should be left to user), and prevented users from being able to convert those three characters using this library.
* Added EN/JA documentation website using GitHub Pages
* Wrote basic download/install/usage documentation in README

v0.9.0 :: Nov 17 2014
======================
* Improved build process, make direct command-line based PHP testing optional (off by default for `ant test`)
* Fixed bugs found in some multi-method conversions, and optimized main loop of the `convertKana` function
* Added multi-method unit tests (include those unusual cases where conflicting methods are unnaturally mixed together) and error cases.  Also split up unit tests into multiple files.
* Cleaned up code, added Javadocs where helpful
* Wrote basic download/install/usage documentation in README

v0.5.0 :: Oct 31 2014
======================
* Beta version of **KanaConverter**
* Implemented remaining methods, including those related to Hankaku Katakana handling
* Re-organized code for more modularity, more effectively spliting up different logic into different functions
* Unit tests for each single method that cover each conversion types full character span, as well as a mixed input test string

v0.1.0 :: Jun 11 2014
======================
* Alpha implementation of **KanaConverter**
* Partial functionality, handles most conversion methods except those related to Hankaku Katakana
* Unit tests for each method via JUnit
