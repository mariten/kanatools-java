package mariten.kanatools;

public class KanaConverter
{
    // Conversion Operations Types
    //// Matched numeric values to originals in PHP's source code
    //// https://github.com/php/php-src/blob/a84e5dc37dc0ff8c313164d9db141d3d9f2b2730/ext/mbstring/mbstring.c#L3434
    public static final int OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC  = 0x00000001;
    public static final int OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC  = 0x00000010;
    public static final int OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA          = 0x00010000;
    public static final int OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA          = 0x00020000;
    public static final int OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA          = 0x00000200;
    public static final int OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA          = 0x00002000;
    public static final int OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA          = 0x00000100;
    public static final int OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA          = 0x00001000;
    public static final int OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER              = 0x00000004;
    public static final int OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER              = 0x00000040;
    public static final int OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER              = 0x00000002;
    public static final int OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER              = 0x00000020;
    public static final int OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE                = 0x00000008;
    public static final int OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE                = 0x00000080;
    public static final int OP_COLLAPSE_HANKAKU_VOICE_MARKS                  = 0x00000800;


    //{{{ mbConvertKana()
    /**
      * Port of PHP's "mb_convert_kana" function for Java.
      * @details http://www.php.net/manual/en/function.mb-convert-kana.php
      *
      * @param  original_string  Input string to perform conversion on
      * @param  conversion_ops   Array indicating which type of conversion to perform
      * @return                  Content of "original_string" with specified conversions performed
      */
    public static String mbConvertKana(String original_string, int[] conversion_ops)
    {
        // Ensure function received strings, not nulls
        if(original_string == null
        || conversion_ops == null) {
            return null;
        }

        int flag_count = conversion_ops.length;
        if(flag_count < 1) {
            // Return original if no conversion requested
            return original_string;
        }

        int char_count = original_string.length();
        StringBuffer new_string = new StringBuffer();
        int i = 0;
        while(i < char_count) {
            // Init char holders for this round
            char this_char = original_string.charAt(i);
            char next_char = 0;
            if(i < (char_count - 1)) {
                next_char = original_string.charAt(i + 1);
            }

            char current_char = this_char;
            for(int j = 0; j < flag_count; j++) {
                int current_flag = conversion_ops[j];

                switch(current_flag) {
                case OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC:
                    current_char = convertHankakuAlphanumericToZenkakuAlphanumeric(current_char);
                    break;
                case OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC:
                    current_char = convertZenkakuAlphanumericToHankakuAlphanumeric(current_char);
                    break;
                case OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA:
                    current_char = convertZenkakuHiraganaToZenkakuKatakana(current_char);
                    break;
                case OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA:
                    current_char = convertZenkakuKatakanaToZenkakuHiragana(current_char);
                    break;
                case OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA:
                    break;
                case OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA:
                    break;
                case OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA:
                    break;
                case OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA:
                    break;
                case OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER:
                    current_char = convertHankakuNumberToZenkakuNumber(current_char);
                    break;
                case OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER:
                    current_char = convertZenkakuNumberToHankakuNumber(current_char);
                    break;
                case OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER:
                    current_char = convertHankakuLetterToZenkakuLetter(current_char);
                    break;
                case OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER:
                    current_char = convertZenkakuLetterToHankakuLetter(current_char);
                    break;
                case OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE:
                    current_char = convertHankakuSpaceToZenkakuSpace(current_char);
                    break;
                case OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE:
                    current_char = convertZenkakuSpaceToHankakuSpace(current_char);
                    break;
                case OP_COLLAPSE_HANKAKU_VOICE_MARKS:
                    // Collapse voiced characters (hankaku) to voiced-kana-chars (zenkaku).  Use with 'K' or 'H'
                    break;
                }
            }

            new_string.append(current_char);
            i++;
        }

        return new_string.toString();
    }

    /**
      * Same as "mbConvertKana()" above, but with second param as single int
      *
      * @param  original_string  Input string to perform conversion on
      * @param  conversion_op    One integer indicating which type of conversion to perform
      * @return                  original_string with specified conversion performed
      */
    public static String mbConvertKana(String original_string, int conversion_op)
    {
        int[] conversion_ops = new int[] {conversion_op};
        return mbConvertKana(original_string, conversion_ops);
    }
    //}}}


    // Numeric constants
    protected static final char HANKAKU_NUMBER_FIRST = '0';
    protected static final char HANKAKU_NUMBER_LAST  = '9';
    protected static final char ZENKAKU_NUMBER_FIRST = '０';
    protected static final char ZENKAKU_NUMBER_LAST  = '９';

    // Alphabetic constants
    protected static final char HANKAKU_LETTER_UPPER_FIRST = 'A';
    protected static final char HANKAKU_LETTER_UPPER_LAST  = 'Z';
    protected static final char HANKAKU_LETTER_LOWER_FIRST = 'a';
    protected static final char HANKAKU_LETTER_LOWER_LAST  = 'z';
    protected static final char ZENKAKU_LETTER_UPPER_FIRST = 'Ａ';
    protected static final char ZENKAKU_LETTER_UPPER_LAST  = 'Ｚ';
    protected static final char ZENKAKU_LETTER_LOWER_FIRST = 'ａ';
    protected static final char ZENKAKU_LETTER_LOWER_LAST  = 'ｚ';

    // Punctuation constants
    public static final char HANKAKU_ALPHANUMERIC_FIRST = '!';
    public static final char HANKAKU_ALPHANUMERIC_LAST  = '}';
    public static final char ZENKAKU_ALPHANUMERIC_FIRST = '！';
    public static final char ZENKAKU_ALPHANUMERIC_LAST  = '｝';

    // Hiragana constants
    public static final char HIRAGANA_FIRST = 'ぁ';
    public static final char HIRAGANA_LAST  = 'ん';

    // Katakana constants
    public static final char ZENKAKU_KATAKANA_FIRST = 'ァ';
    public static final char ZENKAKU_KATAKANA_LAST  = 'ン';

    // Other constants
    protected static final char HANKAKU_SPACE = ' ';
    protected static final char ZENKAKU_SPACE = '　';


    //{{{ 'A': convertHankakuAlphanumericToZenkakuAlphanumeric()
    protected static char convertHankakuAlphanumericToZenkakuAlphanumeric(char target)
    {
        if(target >= HANKAKU_ALPHANUMERIC_FIRST && target <= HANKAKU_ALPHANUMERIC_LAST) {
            switch(target) {
            case '\'':
            case '\"':
            case '\\':
                break;
            default:
                return (char)(target + (ZENKAKU_LETTER_UPPER_FIRST - HANKAKU_LETTER_UPPER_FIRST));
            }
        }

        return target;
    }
    //}}}


    //{{{ 'a': convertZenkakuAlphanumericToHankakuAlphanumeric()
    protected static char convertZenkakuAlphanumericToHankakuAlphanumeric(char target)
    {
        if(target >= ZENKAKU_ALPHANUMERIC_FIRST && target <= ZENKAKU_ALPHANUMERIC_LAST) {
            switch(target) {
            case '’':
            case '”':
            case '＼':
                break;
            default:
                return (char)(target - (ZENKAKU_LETTER_UPPER_FIRST - HANKAKU_LETTER_UPPER_FIRST));
            }
        }

        return target;
    }
    //}}}


    //{{{ 'C': convertZenkakuHiraganaToZenkakuKatakana()
    protected static char convertZenkakuHiraganaToZenkakuKatakana(char target)
    {
        if(target >= HIRAGANA_FIRST && target <= HIRAGANA_LAST) {
            // Offset by difference in hira/kata kana char-code position
            return (char)(target + (ZENKAKU_KATAKANA_FIRST - HIRAGANA_FIRST));
        } else {
            return target;
        }
    }
    //}}}


    //{{{ 'c': convertZenkakuKatakanaToZenkakuHiragana()
    protected static char convertZenkakuKatakanaToZenkakuHiragana(char target)
    {
        if(target >= ZENKAKU_KATAKANA_FIRST && target <= ZENKAKU_KATAKANA_LAST) {
            // Offset by difference in hira/kata kana char-code position
            return (char)(target - (ZENKAKU_KATAKANA_FIRST - HIRAGANA_FIRST));
        } else {
            return target;
        }
    }
    //}}}


    //{{{ 'N': convertHankakuNumberToZenkakuNumber()
    protected static char convertHankakuNumberToZenkakuNumber(char target)
    {
        if(target >= HANKAKU_NUMBER_FIRST && target <= HANKAKU_NUMBER_LAST) {
            // Offset by difference in char-code position
            return (char)(target + (ZENKAKU_NUMBER_FIRST - HANKAKU_NUMBER_FIRST));
        } else {
            return target;
        }
    }
    //}}}


    //{{{ 'n': convertZenkakuNumberToHankakuNumber()
    protected static char convertZenkakuNumberToHankakuNumber(char target)
    {
        if(target >= ZENKAKU_NUMBER_FIRST && target <= ZENKAKU_NUMBER_LAST) {
            // Offset by difference in char-code position
            return (char)(target - (ZENKAKU_NUMBER_FIRST - HANKAKU_NUMBER_FIRST));
        } else {
            return target;
        }
    }
    //}}}


    //{{{ 'R': convertHankakuLetterToZenkakuLetter()
    protected static char convertHankakuLetterToZenkakuLetter(char target)
    {
        if(target >= HANKAKU_LETTER_LOWER_FIRST && target <= HANKAKU_LETTER_LOWER_LAST) {
            // Offset by difference in lower-case char-code position
            return (char)(target + (ZENKAKU_LETTER_LOWER_FIRST - HANKAKU_LETTER_LOWER_FIRST));
        }
        else if(target >= HANKAKU_LETTER_UPPER_FIRST && target <= HANKAKU_LETTER_UPPER_LAST) {
            // Offset by difference in upper-case char-code position
            return (char)(target + (ZENKAKU_LETTER_UPPER_FIRST - HANKAKU_LETTER_UPPER_FIRST));
        }
        else {
            return target;
        }
    }
    //}}}


    //{{{ 'r': convertZenkakuLetterToHankakuLetter()
    protected static char convertZenkakuLetterToHankakuLetter(char target)
    {
        if(target >= ZENKAKU_LETTER_LOWER_FIRST && target <= ZENKAKU_LETTER_LOWER_LAST) {
            // Offset by difference in lower-case char-code position
            return (char)(target - (ZENKAKU_LETTER_LOWER_FIRST - HANKAKU_LETTER_LOWER_FIRST));
        }
        else if(target >= ZENKAKU_LETTER_UPPER_FIRST && target <= ZENKAKU_LETTER_UPPER_LAST) {
            // Offset by difference in upper-case char-code position
            return (char)(target - (ZENKAKU_LETTER_UPPER_FIRST - HANKAKU_LETTER_UPPER_FIRST));
        }
        else {
            return target;
        }
    }
    //}}}


    //{{{ 'S': convertHankakuSpaceToZenkakuSpace()
    protected static char convertHankakuSpaceToZenkakuSpace(char target)
    {
        if(target == HANKAKU_SPACE) {
            return ZENKAKU_SPACE;
        } else {
            return target;
        }
    }
    //}}}


    //{{{ 's': convertZenkakuSpaceToHankakuSpace()
    protected static char convertZenkakuSpaceToHankakuSpace(char target)
    {
        if(target == ZENKAKU_SPACE) {
            return HANKAKU_SPACE;
        } else {
            return target;
        }
    }
    //}}}
}
