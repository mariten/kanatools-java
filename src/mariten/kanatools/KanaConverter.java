package mariten.kanatools;

import java.util.HashMap;
import java.util.Map;

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

    //// Maintain backwards compatibility (based on mb_convert_kana's "$option" parameter from PHP)
    //// Details: http://php.net/manual/en/function.mb-convert-kana.php
    public static final Map<Character, Integer> LETTER_OP_CODE_LOOKUP;
    static {
        LETTER_OP_CODE_LOOKUP = new HashMap<Character, Integer>();
        LETTER_OP_CODE_LOOKUP.put('A', OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC);
        LETTER_OP_CODE_LOOKUP.put('a', OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC);
        LETTER_OP_CODE_LOOKUP.put('C', OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA);
        LETTER_OP_CODE_LOOKUP.put('c', OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA);
        LETTER_OP_CODE_LOOKUP.put('H', OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA);
        LETTER_OP_CODE_LOOKUP.put('h', OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA);
        LETTER_OP_CODE_LOOKUP.put('K', OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA);
        LETTER_OP_CODE_LOOKUP.put('k', OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA);
        LETTER_OP_CODE_LOOKUP.put('N', OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER);
        LETTER_OP_CODE_LOOKUP.put('n', OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER);
        LETTER_OP_CODE_LOOKUP.put('R', OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER);
        LETTER_OP_CODE_LOOKUP.put('r', OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER);
        LETTER_OP_CODE_LOOKUP.put('S', OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE);
        LETTER_OP_CODE_LOOKUP.put('s', OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE);
        LETTER_OP_CODE_LOOKUP.put('V', OP_COLLAPSE_HANKAKU_VOICE_MARKS);
    }


    //{{{ mbConvertKana()
    /**
      * Port of PHP's "mb_convert_kana" function for Java.
      * @details http://www.php.net/manual/en/function.mb-convert-kana.php
      *
      * @param  original_string  Input string to perform conversion on
      * @param  conversion_ops   Flag-based integer indicating which type of conversions to perform
      * @return                  Content of "original_string" with specified conversions performed
      */
    public static String mbConvertKana(String original_string, int conversion_ops)
    {
        // Ensure function received non-empty input string
        if(original_string == null
        || original_string.equals("")) {
            return null;
        }

        if(conversion_ops == 0) {
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

            if((conversion_ops & OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC)  != 0) {
                current_char = convertHankakuAlphanumericToZenkakuAlphanumeric(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC)  != 0) {
                current_char = convertZenkakuAlphanumericToHankakuAlphanumeric(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA)          != 0) {
                current_char = convertZenkakuHiraganaToZenkakuKatakana(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA)          != 0) {
                current_char = convertZenkakuKatakanaToZenkakuHiragana(current_char);
            }

            if((conversion_ops & OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA)          != 0) {
            }

            if((conversion_ops & OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA)          != 0) {
            }

            if((conversion_ops & OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA)          != 0) {
            }

            if((conversion_ops & OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA)          != 0) {
            }

            if((conversion_ops & OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER)              != 0) {
                current_char = convertHankakuNumberToZenkakuNumber(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER)              != 0) {
                current_char = convertZenkakuNumberToHankakuNumber(current_char);
            }

            if((conversion_ops & OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER)              != 0) {
                current_char = convertHankakuLetterToZenkakuLetter(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER)              != 0) {
                current_char = convertZenkakuLetterToHankakuLetter(current_char);
            }

            if((conversion_ops & OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE)                != 0) {
                current_char = convertHankakuSpaceToZenkakuSpace(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE)                != 0) {
                current_char = convertZenkakuSpaceToHankakuSpace(current_char);
            }

            if((conversion_ops & OP_COLLAPSE_HANKAKU_VOICE_MARKS)                  != 0) {
                // Collapse voiced characters (hankaku) to voiced-kana-chars (zenkaku).  Use with 'K' or 'H'
            }

            new_string.append(current_char);
            i++;
        }

        return new_string.toString();
    }

    /**
      * Same as "mbConvertKana()" above, but takes the conversion ops as a string (PHP-style)
      *
      * @param  original_string         Input string to perform conversion on
      * @param  conversion_op_string    PHP mb_convert_kana style string specifying desired conversions
      * @return                         original_string with specified conversion performed
      */
    public static String mbConvertKana(String original_string, String conversion_ops_string)
    {
        int conversion_ops = createOpsArrayFromString(conversion_ops_string);
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


    //{{{ createOpsArrayFromString()
    public static int createOpsArrayFromString(String php_style_options_string)
    {
        int char_op_count = php_style_options_string.length();
        int conversion_op_flags = 0;
        for(int i = 0; i < char_op_count; i++) {
            char php_style_op_code = php_style_options_string.charAt(i);
            if(LETTER_OP_CODE_LOOKUP.containsKey(php_style_op_code)) {
                conversion_op_flags |= LETTER_OP_CODE_LOOKUP.get(php_style_op_code);
            }
        }
        return conversion_op_flags;
    }
    //}}}
}
