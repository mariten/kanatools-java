package mariten.kanatools;

/**
  * Confirms whether a given character is amongst certain types of Japanese text or not.
  */
public class KanaAppraiser
{
    // Character set lower/upper bound definitions
    //// Bounds for Hiragana
    public static final char ZENKAKU_HIRAGANA_FIRST = 'ぁ';             // U+3041
    public static final char ZENKAKU_HIRAGANA_LAST_FOR_CONVERT  = 'ん'; // U+3093
    public static final char ZENKAKU_HIRAGANA_LAST  = 'ゖ';             // U+3096


    //// Bounds for Katakana
    public static final char HANKAKU_KATAKANA_FIRST = 'ｦ';              // U+FF66
    public static final char HANKAKU_KATAKANA_LAST  = 'ﾝ';              // U+FF9D

    public static final char ZENKAKU_KATAKANA_FIRST = 'ァ';             // U+30A1
    public static final char ZENKAKU_KATAKANA_LAST_FOR_CONVERT  = 'ン'; // U+30F3
    public static final char ZENKAKU_KATAKANA_LAST  = 'ヺ';             // U+30FA


    //// Bounds for Numeric
    public static final char HANKAKU_NUMBER_FIRST = '0';                // U+0030
    public static final char HANKAKU_NUMBER_LAST  = '9';                // U+0039

    public static final char ZENKAKU_NUMBER_FIRST = '０';               // U+FF10
    public static final char ZENKAKU_NUMBER_LAST  = '９';               // U+FF19


    //// Bounds for Alphabetic
    public static final char HANKAKU_LETTER_UPPER_FIRST = 'A';          // U+0041
    public static final char HANKAKU_LETTER_UPPER_LAST  = 'Z';          // U+005A
    public static final char HANKAKU_LETTER_LOWER_FIRST = 'a';          // U+0061
    public static final char HANKAKU_LETTER_LOWER_LAST  = 'z';          // U+007A

    public static final char ZENKAKU_LETTER_UPPER_FIRST = 'Ａ';         // U+FF21
    public static final char ZENKAKU_LETTER_UPPER_LAST  = 'Ｚ';         // U+FF3A
    public static final char ZENKAKU_LETTER_LOWER_FIRST = 'ａ';         // U+FF41
    public static final char ZENKAKU_LETTER_LOWER_LAST  = 'ｚ';         // U+FF5A


    // Bounds for All Alphanumeric and Symbol ASCII
    public static final char HANKAKU_SPACE = ' ';                       // U+0020
    public static final char HANKAKU_ASCII_FIRST = '!';                 // U+0021
    public static final char HANKAKU_ASCII_LAST  = '~';                 // U+007E

    public static final char ZENKAKU_SPACE = '　';                      // U+3000
    public static final char ZENKAKU_ASCII_FIRST = '！';                // U+FF01
    public static final char ZENKAKU_ASCII_LAST  = '～';                // U+FF5E


    //{{{ boolean isZenkakuHiragana(char)
    public static boolean isZenkakuHiragana(char eval_char)
    {
        if(eval_char >= ZENKAKU_HIRAGANA_FIRST
        && eval_char <= ZENKAKU_HIRAGANA_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuHiraganaWithKatakanaEquivalent(char)
    public static boolean isZenkakuHiraganaWithKatakanaEquivalent(char eval_char)
    {
        if(eval_char >= ZENKAKU_HIRAGANA_FIRST
        && eval_char <= ZENKAKU_HIRAGANA_LAST_FOR_CONVERT) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuKatakana(char)
    public static boolean isHankakuKatakana(char eval_char)
    {
        if(eval_char >= HANKAKU_KATAKANA_FIRST
        && eval_char <= HANKAKU_KATAKANA_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuKatakana(char)
    public static boolean isZenkakuKatakana(char eval_char)
    {
        if(eval_char >= ZENKAKU_KATAKANA_FIRST
        && eval_char <= ZENKAKU_KATAKANA_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuKatakanaWithHiraganaEquivalent(char)
    public static boolean isZenkakuKatakanaWithHiraganaEquivalent(char eval_char)
    {
        if(eval_char >= ZENKAKU_KATAKANA_FIRST
        && eval_char <= ZENKAKU_KATAKANA_LAST_FOR_CONVERT) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuNumber(char)
    public static boolean isHankakuNumber(char eval_char)
    {
        if(eval_char >= HANKAKU_NUMBER_FIRST
        && eval_char <= HANKAKU_NUMBER_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuNumber(char)
    public static boolean isZenkakuNumber(char eval_char)
    {
        if(eval_char >= ZENKAKU_NUMBER_FIRST
        && eval_char <= ZENKAKU_NUMBER_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuLetter(char)
    public static boolean isHankakuLetter(char eval_char)
    {
        if(eval_char >= HANKAKU_LETTER_UPPER_FIRST
        && eval_char <= HANKAKU_LETTER_UPPER_LAST) {
            return true;
        }
        if(eval_char >= HANKAKU_LETTER_LOWER_FIRST
        && eval_char <= HANKAKU_LETTER_LOWER_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuLetter(char)
    public static boolean isZenkakuLetter(char eval_char)
    {
        if(eval_char >= ZENKAKU_LETTER_UPPER_FIRST
        && eval_char <= ZENKAKU_LETTER_UPPER_LAST) {
            return true;
        }
        if(eval_char >= ZENKAKU_LETTER_LOWER_FIRST
        && eval_char <= ZENKAKU_LETTER_LOWER_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuAscii(char)
    public static boolean isHankakuAscii(char eval_char)
    {
        if(eval_char >= HANKAKU_ASCII_FIRST
        && eval_char <= HANKAKU_ASCII_LAST) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuAscii(char)
    public static boolean isZenkakuAscii(char eval_char)
    {
        if(eval_char >= ZENKAKU_ASCII_FIRST
        && eval_char <= ZENKAKU_ASCII_LAST) {
            return true;
        }
        return false;
    }
    //}}}
}
