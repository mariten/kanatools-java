package com.mariten.kanatools;

/**
  * Confirms whether a given character is amongst certain types of Japanese text or not.
  *
  * <p>Supports checking whether a character is amongst the following types of text:</p>
  * <ul>
  *   <li>Hiragana</li>
  *   <li>Katakana</li>
  *   <li>Japanese punctuation (<i>kutoten</i>)</li>
  *   <li>ASCII (numbers, letters, or all)</li>
  *   <li>Can check on half-width or full-width for all the above</li>
  * </ul>
  * <p>All input characters are expected to be ASCII or UTF-8 encoding</p>
  *
  * @author Jeff Case (mariten)
  */
public class KanaAppraiser
{
    //{{{ Character set lower/upper bound definitions
    //// Bounds for Hiragana
    /** U+3041 */   public static final char ZENKAKU_HIRAGANA_FIRST             = 'ぁ';
    /** U+3093 */   public static final char ZENKAKU_HIRAGANA_LAST_FOR_CONVERT  = 'ん';
    /** U+3096 */   public static final char ZENKAKU_HIRAGANA_LAST              = 'ゖ';


    //// Bounds for Katakana
    /** U+FF66 */   public static final char HANKAKU_KATAKANA_FIRST = 'ｦ';
    /** U+FF9D */   public static final char HANKAKU_KATAKANA_LAST  = 'ﾝ';

    /** U+30A1 */   public static final char ZENKAKU_KATAKANA_FIRST = 'ァ';
    /** U+30F3 */   public static final char ZENKAKU_KATAKANA_LAST_FOR_CONVERT  = 'ン';
    /** U+30FA */   public static final char ZENKAKU_KATAKANA_LAST  = 'ヺ';


    //// Bounds for Punctuation (kutoten)
    /** U+FF61 */   public static final char HANKAKU_PUNCTUATION_FIRST  = '｡';
    /** U+FF9F */   public static final char HANKAKU_PUNCTUATION_LAST   = 'ﾟ';
    /** U+FF70 */   public static final char HANKAKU_PUNCTUATION_ONBIKI = 'ｰ';

    /** U+3001 */   public static final char ZENKAKU_PUNCTUATION_FIRST    = '、';
    /** U+301C */   public static final char ZENKAKU_PUNCTUATION_LAST     = '〜';
    /** U+309B */   public static final char ZENKAKU_PUNCTUATION_HG_FIRST = '゛';
    /** U+309E */   public static final char ZENKAKU_PUNCTUATION_HG_LAST  = 'ゞ';
    /** U+30FB */   public static final char ZENKAKU_PUNCTUATION_KK_FIRST = '・';
    /** U+30FE */   public static final char ZENKAKU_PUNCTUATION_KK_LAST  = 'ヾ';


    //// Bounds for Numeric
    /** U+0030 */   public static final char HANKAKU_NUMBER_FIRST = '0';
    /** U+0039 */   public static final char HANKAKU_NUMBER_LAST  = '9';

    /** U+FF10 */   public static final char ZENKAKU_NUMBER_FIRST = '０';
    /** U+FF19 */   public static final char ZENKAKU_NUMBER_LAST  = '９';


    //// Bounds for Alphabetic
    /** U+0041 */   public static final char HANKAKU_LETTER_UPPER_FIRST = 'A';
    /** U+005A */   public static final char HANKAKU_LETTER_UPPER_LAST  = 'Z';
    /** U+0061 */   public static final char HANKAKU_LETTER_LOWER_FIRST = 'a';
    /** U+007A */   public static final char HANKAKU_LETTER_LOWER_LAST  = 'z';

    /** U+FF21 */   public static final char ZENKAKU_LETTER_UPPER_FIRST = 'Ａ';
    /** U+FF3A */   public static final char ZENKAKU_LETTER_UPPER_LAST  = 'Ｚ';
    /** U+FF41 */   public static final char ZENKAKU_LETTER_LOWER_FIRST = 'ａ';
    /** U+FF5A */   public static final char ZENKAKU_LETTER_LOWER_LAST  = 'ｚ';


    // Bounds for All Alphanumeric and Symbol ASCII
    /** U+0020 */   public static final char HANKAKU_SPACE = ' ';
    /** U+0021 */   public static final char HANKAKU_ASCII_FIRST = '!';
    /** U+007E */   public static final char HANKAKU_ASCII_LAST  = '~';

    /** U+3000 */   public static final char ZENKAKU_SPACE = '　';
    /** U+FF01 */   public static final char ZENKAKU_ASCII_FIRST = '！';
    /** U+FF5E */   public static final char ZENKAKU_ASCII_LAST  = '～';
    //}}}


    //{{{ boolean isZenkakuHiragana(char)
    /**
      * Checks whether a character is full-width (<i>zenkaku</i>) hiragana.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is full-width (<i>zenkaku</i>) hiragana and that it has a canonical one-to-one katakana equivalent.
      * @since              1.3.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is half-width (<i>hankaku</i>) katakana.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
    public static boolean isHankakuKatakana(char eval_char)
    {
        if(eval_char >= HANKAKU_KATAKANA_FIRST
        && eval_char <= HANKAKU_KATAKANA_LAST
        && eval_char != HANKAKU_PUNCTUATION_ONBIKI) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuKatakana(char)
    /**
      * Checks whether a character is full-width (<i>zenkaku</i>) katakana.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is full-width (<i>zenkaku</i>) katakana and that it has a canonical one-to-one hiragana equivalent.
      * @since              1.3.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
    public static boolean isZenkakuKatakanaWithHiraganaEquivalent(char eval_char)
    {
        if(eval_char >= ZENKAKU_KATAKANA_FIRST
        && eval_char <= ZENKAKU_KATAKANA_LAST_FOR_CONVERT) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuKutoten(char)
    /**
      * Checks whether a character is half-width (<i>hankaku</i>) Japanese punctuation (<i>kutoten</i>).
      * @since              1.3.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
    public static boolean isHankakuKutoten(char eval_char)
    {
        if(eval_char >= HANKAKU_PUNCTUATION_FIRST
        && eval_char <= HANKAKU_PUNCTUATION_LAST
        && !isHankakuKatakana(eval_char)) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isZenkakuKutoten(char)
    /**
      * Checks whether a character is full-width (<i>zenkaku</i>) Japanese punctuation (<i>kutoten</i>).
      * @since              1.3.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
    public static boolean isZenkakuKutoten(char eval_char)
    {
        if((eval_char >= ZENKAKU_PUNCTUATION_FIRST    && eval_char <= ZENKAKU_PUNCTUATION_LAST)
        || (eval_char >= ZENKAKU_PUNCTUATION_HG_FIRST && eval_char <= ZENKAKU_PUNCTUATION_HG_LAST)
        || (eval_char >= ZENKAKU_PUNCTUATION_KK_FIRST && eval_char <= ZENKAKU_PUNCTUATION_KK_LAST)) {
            return true;
        }
        return false;
    }
    //}}}


    //{{{ boolean isHankakuNumber(char)
    /**
      * Checks whether a character is a standard-width (<i>hankaku</i>) number.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is a double-width (<i>zenkaku</i>) number.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is a standard-width (<i>hankaku</i>) alphabetic letter.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is a double-width (<i>zenkaku</i>) alphabetic letter.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is a standard-width (<i>hankaku</i>) ASCII.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
    /**
      * Checks whether a character is a double-width (<i>zenkaku</i>) ASCII.
      * @since              1.2.0
      * @param  eval_char   UTF-8 character to evaluate
      * @return             True or false
      */
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
