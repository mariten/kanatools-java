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

        boolean do_collapse_on_hankaku_diacritic = false;
        if((conversion_ops & OP_COLLAPSE_HANKAKU_VOICE_MARKS) != 0) {
            // Collapse voiced characters (hankaku) to voiced-kana-chars (zenkaku).  Use with 'K' or 'H'
            do_collapse_on_hankaku_diacritic = true;
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

            // Order of conversion operations written to be similar to original PHP
            //// Source: https://github.com/php/php-src/blob/128eda843f7dff487fff529a384fee3c5494e0f6/ext/mbstring/libmbfl/filters/mbfilter_tl_jisx0201_jisx0208.c#L41
            if((conversion_ops & OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC)  != 0) {
                current_char = convertHankakuAlphanumericToZenkakuAlphanumeric(current_char);
            }

            if((conversion_ops & OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER)              != 0) {
                current_char = convertHankakuLetterToZenkakuLetter(current_char);
            }

            if((conversion_ops & OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE)                != 0) {
                current_char = convertHankakuSpaceToZenkakuSpace(current_char);
            }

            if((conversion_ops & OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER)              != 0) {
                current_char = convertHankakuNumberToZenkakuNumber(current_char);
            }

            if((conversion_ops & OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA)          != 0) {
                char collapsed_char_for_check = current_char;
                if(do_collapse_on_hankaku_diacritic) {
                    // Check if current character requires the collapsing of a diacritic mark
                    collapsed_char_for_check = convertDiacriticHankakuKanaToZenkaku(current_char, next_char);
                }

                if(collapsed_char_for_check != current_char) {
                    // Use collapsed result
                    current_char = collapsed_char_for_check;

                    // Do not include next character in final result string because
                    // it is a hankaku-only diacritic mark that isn't needed after conversion to zenkaku
                    i++;
                }
                else {
                    // Use result from hankaku-kana unvoiced mapping
                    current_char = convertUnvoicedHankakuKanaToZenkaku(current_char);
                }
            }

            if((conversion_ops & OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA)          != 0) {
            }

            if((conversion_ops & OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC)  != 0) {
                current_char = convertZenkakuAlphanumericToHankakuAlphanumeric(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER)              != 0) {
                current_char = convertZenkakuLetterToHankakuLetter(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER)              != 0) {
                current_char = convertZenkakuNumberToHankakuNumber(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE)                != 0) {
                current_char = convertZenkakuSpaceToHankakuSpace(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA)          != 0) {
            }

            if((conversion_ops & OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA)          != 0) {
            }

            if((conversion_ops & OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA)          != 0) {
                current_char = convertZenkakuHiraganaToZenkakuKatakana(current_char);
            }

            if((conversion_ops & OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA)          != 0) {
                current_char = convertZenkakuKatakanaToZenkakuHiragana(current_char);
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


    //{{{ Character constants
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

    // Diacritic constants
    public static final char HANKAKU_VOICED_MARK    = 'ﾞ';  // dakuten
    public static final char HANKAKU_ASPIRATED_MARK = 'ﾟ';  // handakuten

    // Other constants
    protected static final char HANKAKU_SPACE = ' ';
    protected static final char ZENKAKU_SPACE = '　';
    //}}}


    //{{{ Hankaku Katakana related mappings
    public static final Map<Character, Character> MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED;
    static {
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED = new HashMap<Character, Character>();
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('｡', '。');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('｢', '「');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('｣', '」');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('､', '、');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('･', '・');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｦ', 'ヲ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｧ', 'ァ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｨ', 'ィ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｩ', 'ゥ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｪ', 'ェ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｫ', 'ォ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｬ', 'ャ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｭ', 'ュ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｮ', 'ョ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｯ', 'ッ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｰ', 'ー');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｱ', 'ア');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｲ', 'イ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｳ', 'ウ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｴ', 'エ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｵ', 'オ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｶ', 'カ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｷ', 'キ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｸ', 'ク');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｹ', 'ケ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｺ', 'コ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｻ', 'サ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｼ', 'シ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｽ', 'ス');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｾ', 'セ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ｿ', 'ソ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾀ', 'タ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾁ', 'チ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾂ', 'ツ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾃ', 'テ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾄ', 'ト');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾅ', 'ナ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾆ', 'ニ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾇ', 'ヌ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾈ', 'ネ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾉ', 'ノ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾊ', 'ハ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾋ', 'ヒ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾌ', 'フ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾍ', 'ヘ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾎ', 'ホ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾏ', 'マ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾐ', 'ミ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾑ', 'ム');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾒ', 'メ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾓ', 'モ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾔ', 'ヤ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾕ', 'ユ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾖ', 'ヨ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾗ', 'ラ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾘ', 'リ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾙ', 'ル');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾚ', 'レ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾛ', 'ロ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾜ', 'ワ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾝ', 'ン');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾞ', '゛');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.put('ﾟ', '゜');
    }

    public static final Map<Character, Character> MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED;
    static {
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED = new HashMap<Character, Character>();
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｶ', 'ガ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｷ', 'ギ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｸ', 'グ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｹ', 'ゲ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｺ', 'ゴ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｻ', 'ザ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｼ', 'ジ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｽ', 'ズ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｾ', 'ゼ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ｿ', 'ゾ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾀ', 'ダ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾁ', 'ヂ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾂ', 'ヅ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾃ', 'デ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾄ', 'ド');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾊ', 'バ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾋ', 'ビ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾌ', 'ブ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾍ', 'ベ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.put('ﾎ', 'ボ');
    }

    public static final Map<Character, Character> MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED;
    static {
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED = new HashMap<Character, Character>();
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.put('ﾊ', 'パ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.put('ﾋ', 'ピ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.put('ﾌ', 'プ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.put('ﾍ', 'ペ');
        MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.put('ﾎ', 'ポ');
    }
    //}}}


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


    //{{{ convertUnvoicedHankakuKanaToZenkaku()
    protected static char convertUnvoicedHankakuKanaToZenkaku(char target)
    {
        if(MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.containsKey(target)) {
            // Return character from *unvoiced* han-to-zen mapping
            return MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_UNVOICED.get(target);
        }
        else {
            return target;
        }
    }
    //}}}


    //{{{ convertDiacriticHankakuKanaToZenkaku()
    protected static char convertDiacriticHankakuKanaToZenkaku(char target, char diacritic_mark)
    {
        if(diacritic_mark == HANKAKU_VOICED_MARK
        && MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.containsKey(target)) {
            // Use character from *voiced* han-to-zen mapping
            return MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_VOICED.get(target);
        }

        if(diacritic_mark == HANKAKU_ASPIRATED_MARK
        && MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.containsKey(target)) {
            // Use character from *aspirated* han-to-zen mapping
            return MAPPING_HANKAKU_TO_ZENKAKU_KATAKANA_ASPIRATED.get(target);
        }

        // Not a voiced/aspirated hankaku katakana character, use original
        return target;
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
