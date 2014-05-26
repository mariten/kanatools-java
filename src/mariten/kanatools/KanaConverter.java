package mariten.kanatools;

public class KanaConverter
{
    // Conversion Operations Types
    public static final char OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC = 'A';
    public static final char OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC = 'a';
    public static final char OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA         = 'C';
    public static final char OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA         = 'c';
    public static final char OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA         = 'H';
    public static final char OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA         = 'h';
    public static final char OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA         = 'K';
    public static final char OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA         = 'k';
    public static final char OP_HANKAKU_NUMERIC_TO_ZENKAKU_NUMERIC           = 'N';
    public static final char OP_ZENKAKU_NUMERIC_TO_HANKAKU_NUMERIC           = 'n';
    public static final char OP_HANKAKU_ALPHABET_TO_ZENKAKU_ALPHABET         = 'R';
    public static final char OP_ZENKAKU_ALPHABET_TO_HANKAKU_ALPHABET         = 'r';
    public static final char OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE               = 'S';
    public static final char OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE               = 's';
    public static final char OP_COLLAPSE_HANKAKU_VOICE_MARKS                 = 'V';


    //{{{ mbConvertKana()
    /**
      * Port of PHP's "mb_convert_kana" function for Java.
      * @details http://www.php.net/manual/en/function.mb-convert-kana.php
      *
      * @param  original_string  Input string to perform conversion on
      * @param  conversion_ops   One or more characters indicating which type of conversion to perform
      * @param  encoding         Character encoding of input string
      * @return                  Content of "original_string" with specified conversions performed
      */
    public String mbConvertKana(String original_string, String conversion_ops, String encoding)
    {
        // Ensure function received strings, not nulls
        if(original_string == null
        || conversion_ops == null
        || encoding == null) {
            return null;
        }

        int flag_count = conversion_ops.length();
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
                next_char = original_string.charAt(i);
            }

            char current_char = this_char;
            for(int j = 0; j < flag_count; j++) {
                char current_flag = conversion_ops.charAt(j);

                switch(current_flag) {
                case OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC:
                    break;
                case OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC:
                    break;
                case OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA:
                    break;
                case OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA:
                    break;
                case OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA:
                    break;
                case OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA:
                    break;
                case OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA:
                    break;
                case OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA:
                    break;
                case OP_HANKAKU_NUMERIC_TO_ZENKAKU_NUMERIC:
                    break;
                case OP_ZENKAKU_NUMERIC_TO_HANKAKU_NUMERIC:
                    break;
                case OP_HANKAKU_ALPHABET_TO_ZENKAKU_ALPHABET:
                    break;
                case OP_ZENKAKU_ALPHABET_TO_HANKAKU_ALPHABET:
                    break;
                case OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE:
                    break;
                case OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE:
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
    //}}}
}


