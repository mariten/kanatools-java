package mariten.kanatools;

/**
  * Confirms whether a given character is amongst certain types of Japanese text or not.
  */
public class KanaAppraiser
{
    // Character set lower/upper bound definitions
    //// Bounds for Hiragana
    public static final char ZENKAKU_HIRAGANA_FIRST = 'ぁ';             // U+3041
    public static final char ZENKAKU_HIRAGANA_LAST  = 'ん';             // U+3093


    //// Bounds for Katakana
    public static final char ZENKAKU_KATAKANA_FIRST = 'ァ';             // U+30A1
    public static final char ZENKAKU_KATAKANA_LAST  = 'ン';             // U+30F3


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
}
