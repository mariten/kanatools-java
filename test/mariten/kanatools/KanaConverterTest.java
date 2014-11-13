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
        int single_op_flag = KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC;
        this.assertConverted(single_op_flag, "Ａ", "A");

        // Multiple ops (flag-style)
        int multi_op_flags = 0;
        multi_op_flags |= KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC;
        multi_op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
        multi_op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(multi_op_flags, "Ａ", "A");

        // Single op (PHP mb_convert_kana style)
        String single_op = "a";
        this.assertConverted(single_op, "Ａ", "A");

        // Multiple ops (PHP mb_convert_kana style)
        String multi_ops = "KVa";
        this.assertConverted(multi_ops, "Ａ", "A");
    }
    //}}}


    //{{{ 'A': testHankakuAlphanumericToZenkakuAlphanumeric()
    @Test
    public void testHankakuAlphanumericToZenkakuAlphanumeric()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " ！\"＃＄％＆'（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［\\］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝~"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " ！０：Ａ＾ａ｜\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 ＃１；Ｂ＿ｂ｝\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'a': testZenkakuAlphanumericToHankakuAlphanumeric()
    @Test
    public void testZenkakuAlphanumericToHankakuAlphanumeric()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC,
            "　！”＃＄％＆’（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～",
            "　!”#$%&’()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[＼]^_`abcdefghijklmnopqrstuvwxyz{|}～"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　!0:A^a|”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　#1;B_b}＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'C': testZenkakuHiraganaToZenkakuKatakana()
    @Test
    public void testZenkakuHiraganaToZenkakuKatakana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA,
            "〶 | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん | ゔゕゖゝゞ",
            "〶 | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲン | ゔゕゖゝゞ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”アガパヰゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・キジピヱゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'c': testZenkakuKatakanaToZenkakuHiragana()
    @Test
    public void testZenkakuKatakanaToZenkakuHiragana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "んゔゕゖゝゞ | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴ | ヵヶヷヸヹヺ・ーヽヾ",
            "んゔゕゖゝゞ | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんヴ | ヵヶヷヸヹヺ・ーヽヾ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝあがぱゐヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞきじぴゑヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'H': testHankakuKatakanaToZenkakuHiragana()
    @Test
    public void testHankakuKatakanaToZenkakuHiragana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこか゛き゛く゛け゛こ゛さしすせそさ゛し゛す゛せ゛そ゛たちつてとた゛ち゛つ゛て゛と゛なにぬねのはひふへほは゛ひ゛ふ゛へ゛ほ゛は゜ひ゜ふ゜へ゜ほ゜まみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。あか゛は゜゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・きし゛ひ゜゜字"
        );
    }
    //}}}


    //{{{ 'HV': testHankakuKatakanaToZenkakuHiraganaWithCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuHiraganaWithCollapse()
    {
        int op_flags = 0;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;

        // Check all target chars
        this.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        this.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aそざざ゛じすぱぱ゜ひ1-"
        );

        // Check mixed input
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。あがぱ゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・きじぴ゜字"
        );
    }
    //}}}


    //{{{ 'h': testZenkakuHiraganaToHankakuKatakana()
    @Test
    public void testZenkakuHiraganaToHankakuKatakana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろゎわゐゑをんゔ゛゜ﾡゕゖゝゞヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝゔﾞﾟﾡゕゖゝゞヽヾ"
        );

        // Check logic for affixing diacritic marks
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "Aそざざ゛じすぱぱ゜ひ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”ｱｶﾞﾊﾟｲゔゕゝアガパヰヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼･ｷｼﾞﾋﾟｴゖゞキジピヱヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'K': testHankakuKatakanaToZenkakuKatakana()
    @Test
    public void testHankakuKatakanaToZenkakuKatakana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコカ゛キ゛ク゛ケ゛コ゛サシスセソサ゛シ゛ス゛セ゛ソ゛タチツテトタ゛チ゛ツ゛テ゛ト゛ナニヌネノハヒフヘホハ゛ヒ゛フ゛ヘ゛ホ゛ハ゜ヒ゜フ゜ヘ゜ホ゜マミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アカ゛ハ゜゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・キシ゛ヒ゜゜字"
        );
    }
    //}}}


    //{{{ 'KV': testHankakuKatakanaToZenkakuKatakanaWithCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuKatakanaWithCollapse()
    {
        // Check all target chars
        int op_flags = 0;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        this.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aソザザ゛ジスパパ゜ヒ1-"
        );

        // Check mixed input
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アガパ゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・キジピ゜字"
        );
    }
    //}}}


    //{{{ 'k': testZenkakuKatakanaToHankakuKatakana()
    @Test
    public void testZenkakuKatakanaToHankakuKatakana()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロヮワヰヱヲンヴ゛゜ﾡヵヶヷヸヹヺヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞﾞﾟﾡヵヶヷヸヹヺヽヾ"
        );

        // Check logic for affixing diacritic marks
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "Aソザザ゛ジスパパ゜ヒ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝｱｶﾞﾊﾟｲヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼･きじぴゑゖゞｷｼﾞﾋﾟｴヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'N': testHankakuNumberToZenkakuNumber()
    @Test
    public void testHankakuNumberToZenkakuNumber()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !０:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #１;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'n': testZenkakuNumberToHankakuNumber()
    @Test
    public void testZenkakuNumberToHankakuNumber()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！0：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃1；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'R': testHankakuLetterToZenkakuLetter()
    @Test
    public void testHankakuLetterToZenkakuLetter()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " !\"#$%&'()*+,-./0123456789:;<=>?@ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ[\\]^_`ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ{|}~"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:Ａ^ａ|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;Ｂ_ｂ}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'r': testZenkakuLetterToHankakuLetter()
    @Test
    public void testZenkakuLetterToHankakuLetter()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER,
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［￥］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝〜",
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ABCDEFGHIJKLMNOPQRSTUVWXYZ［￥］＾＿｀abcdefghijklmnopqrstuvwxyz｛｜｝〜"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：A＾a｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；B＿b｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'S': testHankakuSpaceToZenkakuSpace()
    @Test
    public void testHankakuSpaceToZenkakuSpace()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE,
            "Little spaces　and big spaces",
            "Little　spaces　and　big　spaces"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            "　!0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢　#1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 's': testZenkakuSpaceToHankakuSpace()
    @Test
    public void testZenkakuSpaceToHankakuSpace()
    {
        // Check all target chars
        this.assertConverted(KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE,
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ　ａｎｄ ｂｉｇ　ｓｐａｃｅｓ",
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ ａｎｄ ｂｉｇ ｓｐａｃｅｓ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\" ！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\ ＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );

        // Test that converted spaces are trimmed by the "trim" function
        String trimmed_test = KanaConverter.mbConvertKana("　テスト　　", KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE).trim();
        assertEquals("テスト", trimmed_test);
    }
    //}}}


    //{{{ 'V': testCollapseHankakuMarkOnly()
    @Test
    public void testCollapseHankakuMarkOnly()
    {
        // This option is illegal, no conversion should be performed
        this.assertConverted(KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ"
        );

        // Check mixed input
        this.assertConverted(KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ testMixedOps()
    @Test
    public void testMixedOps()
    {
        // han2zen kata-kata, zen2han alphanumeric
        int op_flags = 0;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        op_flags |= KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC;
        op_flags |= KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\" !0:A^a|”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アガパ゛漢 #1;B_b}\\ #1;B_b}＼・きじぴゑゖゞキジピヱヹヶヾ゜・キジピ゜字"
        );

        // zen2han hira-kata, zen2han kata-kata
        this.assertConverted("hk",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”ｱｶﾞﾊﾟｲゔゕゝｱｶﾞﾊﾟｲヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼･ｷｼﾞﾋﾟｴゖゞｷｼﾞﾋﾟｴヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );

        // han2zen hira, zen2han alpha-only
        op_flags = 0;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA;
        op_flags |= KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：A＾a｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。あがぱ゛漢 #1;B_b}\\　＃１；B＿b｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・きじぴ゜字"
        );

        // zen2zen hira-kata, han2zen num-only
        op_flags = 0;
        op_flags |= KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA;
        op_flags |= KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !０:A^a|\"　！０：Ａ＾ａ｜”アガパヰゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #１;B_b}\\　＃１；Ｂ＿ｂ｝＼・キジピヱゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );

        // zen2zen kata-hira, han2zen kata-hira, han2zen alphanumeric
        op_flags = 0;
        op_flags |= KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        op_flags |= KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " ！０：Ａ＾ａ｜\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝあがぱゐヸヷヵヽ゛。あがぱ゛漢 ＃１；Ｂ＿ｂ｝\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞきじぴゑヹヶヾ゜・きじぴ゜字"
        );

        // zen2han kata, han2zen num-only, alpha-only
        op_flags = 0;
        op_flags |= KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA;
        op_flags |= KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER;
        op_flags |= KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !０:Ａ^ａ|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝｱｶﾞﾊﾟｲヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #１;Ｂ_ｂ}\\　＃１；Ｂ＿ｂ｝＼･きじぴゑゖゞｷｼﾞﾋﾟｴヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ testConflictingOps()
    @Test
    public void testConflictingOps()
    {
        // han2zen-kata, han2zen-hira
        int op_flags = 0;
        op_flags |= KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA;
        op_flags |= KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA;
        this.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アガパ゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・キジピ゜字"
        );

        // alpha, alpha-only, num-only
        this.assertConverted("arn",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　!0:A^a|”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　#1;B_b}＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );

        // han2zen alpha, zen2han alpha
        this.assertConverted("aA",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " ！０：Ａ＾ａ｜\"　!0:A^a|”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 ＃１；Ｂ＿ｂ｝\\　#1;B_b}＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );

        // zen2zen kata-hira, zen2zen hira-kata
        this.assertConverted("Cc",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”アガパヰゔゕゝあがぱゐヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・キジピヱゖゞきじぴゑヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
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
