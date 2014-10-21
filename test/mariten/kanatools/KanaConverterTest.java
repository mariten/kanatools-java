package mariten.kanatools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import mariten.kanatools.KanaConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class KanaConverterTest
{
    //{{{ testErrorCases()
    @Test
    public void testErrorCases()
    {
        assertNull(KanaConverter.mbConvertKana(null, KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA));
    }
    //}}}


    //{{{ testOpFormats()
    @Test
    public void testOpFormats()
    {
        // Single op (flag-style)
        this.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC, "Ａ", "A");

        // Multiple ops (flag-style)
        int flag_ops = 0;
        flag_ops |= KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC;
        flag_ops |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
        flag_ops |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(flag_ops, "Ａ", "A");

        // Single op (PHP mb_convert_kana style)
        this.assertConverted("a", "Ａ", "A");

        // Multiple ops (PHP mb_convert_kana style)
        this.assertConverted("KVa", "Ａ", "A");
    }
    //}}}


    //{{{ 'A': testHankakuAlphanumericToZenkakuAlphanumeric()
    @Test
    public void testHankakuAlphanumericToZenkakuAlphanumeric()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " ！\"＃＄％＆'（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［\\］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝~"
        );
    }
    //}}}


    //{{{ 'a': testZenkakuAlphanumericToHankakuAlphanumeric()
    @Test
    public void testZenkakuAlphanumericToHankakuAlphanumeric()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC,
            " ！”＃＄％＆’（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～",
            " !”#$%&’()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[＼]^_`abcdefghijklmnopqrstuvwxyz{|}～"
        );
    }
    //}}}


    //{{{ 'C': testZenkakuHiraganaToZenkakuKatakana()
    @Test
    public void testZenkakuHiraganaToZenkakuKatakana()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA,
            "〶 | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん | ゔゕゖゝゞ",
            "〶 | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲン | ゔゕゖゝゞ"
        );
    }
    //}}}


    //{{{ 'c': testZenkakuKatakanaToZenkakuHiragana()
    @Test
    public void testZenkakuKatakanaToZenkakuHiragana()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "んゔゕゖゝゞ | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴ | ヵヶヷヸヹヺ・ーヽヾ",
            "んゔゕゖゝゞ | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんヴ | ヵヶヷヸヹヺ・ーヽヾ"
        );
    }
    //}}}


    //{{{ 'H': testHankakuKatakanaToZenkakuHiragana()
    @Test
    public void testHankakuKatakanaToZenkakuHiragana()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこか゛き゛く゛け゛こ゛さしすせそさ゛し゛す゛せ゛そ゛たちつてとた゛ち゛つ゛て゛と゛なにぬねのはひふへほは゛ひ゛ふ゛へ゛ほ゛は゜ひ゜ふ゜へ゜ほ゜まみむめもやゆよらりるれろわをん゛゜ﾡ"
        );
    }
    //}}}


    //{{{ 'HV': testHankakuKatakanaToZenkakuHiraganaWithCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuHiraganaWithCollapse()
    {
        int op_flags = KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA | KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        this.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aそざざ゛じすぱぱ゜ひ1-"
        );
    }
    //}}}


    //{{{ 'h': testZenkakuHiraganaToHankakuKatakana()
    @Test
    public void testZenkakuHiraganaToHankakuKatakana()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろゎわゐゑをんゔ゛゜ﾡゕゖゝゞヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝゔﾞﾟﾡゕゖゝゞヽヾ"
        );

        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "Aそざざ゛じすぱぱ゜ひ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );
    }
    //}}}


    //{{{ 'K': testHankakuKatakanaToZenkakuKatakana()
    @Test
    public void testHankakuKatakanaToZenkakuKatakana()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコカ゛キ゛ク゛ケ゛コ゛サシスセソサ゛シ゛ス゛セ゛ソ゛タチツテトタ゛チ゛ツ゛テ゛ト゛ナニヌネノハヒフヘホハ゛ヒ゛フ゛ヘ゛ホ゛ハ゜ヒ゜フ゜ヘ゜ホ゜マミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );
    }
    //}}}


    //{{{ 'KV': testHankakuKatakanaToZenkakuKatakanaWithCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuKatakanaWithCollapse()
    {
        int op_flags = KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA | KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        this.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aソザザ゛ジスパパ゜ヒ1-"
        );
    }
    //}}}


    //{{{ 'k': testZenkakuKatakanaToHankakuKatakana()
    @Test
    public void testZenkakuKatakanaToHankakuKatakana()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロヮワヰヱヲンヴ゛゜ﾡヵヶヷヸヹヺヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞﾞﾟﾡヵヶヷヸヹヺヽヾ"
        );

        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "Aソザザ゛ジスパパ゜ヒ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );
    }
    //}}}


    //{{{ 'N': testHankakuNumberToZenkakuNumber()
    @Test
    public void testHankakuNumberToZenkakuNumber()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );
    }
    //}}}


    //{{{ 'n': testZenkakuNumberToHankakuNumber()
    @Test
    public void testZenkakuNumberToHankakuNumber()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );
    }
    //}}}


    //{{{ 'R': testHankakuLetterToZenkakuLetter()
    @Test
    public void testHankakuLetterToZenkakuLetter()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " !\"#$%&'()*+,-./0123456789:;<=>?@ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ[\\]^_`ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ{|}~"
        );
    }
    //}}}


    //{{{ 'r': testZenkakuLetterToHankakuLetter()
    @Test
    public void testZenkakuLetterToHankakuLetter()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER,
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［￥］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝〜",
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ABCDEFGHIJKLMNOPQRSTUVWXYZ［￥］＾＿｀abcdefghijklmnopqrstuvwxyz｛｜｝〜"
        );
    }
    //}}}


    //{{{ 'S': testHankakuSpaceToZenkakuSpace()
    @Test
    public void testHankakuSpaceToZenkakuSpace()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE,
            "Little spaces　and big spaces",
            "Little　spaces　and　big　spaces"
        );
    }
    //}}}


    //{{{ 's': testZenkakuSpaceToHankakuSpace()
    @Test
    public void testZenkakuSpaceToHankakuSpace()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE,
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ　ａｎｄ ｂｉｇ　ｓｐａｃｅｓ",
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ ａｎｄ ｂｉｇ ｓｐａｃｅｓ"
        );

        // Test that converted spaces are trimmed by the "trim" function
        String trimmed_test = KanaConverter.mbConvertKana("　テスト　　", KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE).trim();
        assertEquals("テスト", trimmed_test);
    }
    //}}}


    //{{{ assertConverted()
    private void assertConverted(int conv_flags, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, KanaConverter.mbConvertKana(str_to_convert, conv_flags));
        assertConvertedUsingPHP(conv_flags, str_to_convert, expected_result);
    }
    private void assertConverted(String conv_flags_string, String str_to_convert, String expected_result)
    {
        int conv_flags = KanaConverter.createOpsArrayFromString(conv_flags_string);
        this.assertConverted(conv_flags, str_to_convert, expected_result);
    }
    //}}}


    //{{{ assertConvertedUsingPHP()
    private void assertConvertedUsingPHP(int conv_flags, String str_to_convert, String expected_result)
    {
        // Use single quotes in PHP code, so "encode" single quotes in string input
        // Break up input string and concatenate a single quote character using PHP syntax
        String str_to_convert_for_php = str_to_convert.replace("'", "' . \"'\" . '");

        // Create PHP string indicating conversion operations
        String conv_ops_for_php = makeOperationStringForPHP(conv_flags);

        // Create shell command with each param as element of String array
        String[] command_for_php = new String[] {
            "php",
            "-r",
            "echo(mb_convert_kana('" + str_to_convert_for_php + "', '" + conv_ops_for_php + "', 'UTF-8'));"
        };

        Process php_process = null;
        String php_result = "";
        try {
            // Execute command
            php_process = Runtime.getRuntime().exec(command_for_php);
            php_process.waitFor();

            // Get standard output from executed command
            BufferedReader process_output = new BufferedReader(new InputStreamReader(php_process.getInputStream()));
            php_result = process_output.readLine();
        } catch(Exception e) {
            System.out.println("Exception running PHP command:");
            System.out.println(e.getStackTrace().toString());
        }

        // Check results
        assertEquals(expected_result, php_result);
    }
    //}}}


    //{{{ makeOperationStringForPHP()
    private String makeOperationStringForPHP(int conv_flags)
    {
        StringBuilder php_conv_flags = new StringBuilder();
        for(Entry<Character, Integer> op_map_item : KanaConverter.LETTER_OP_CODE_LOOKUP.entrySet()) {
            char op_char = op_map_item.getKey();
            int  op_flag = op_map_item.getValue();
            if((conv_flags & op_flag) != 0) {
                php_conv_flags.append(op_char);
            }
        }

        return php_conv_flags.toString();
    }
    //}}}
}
