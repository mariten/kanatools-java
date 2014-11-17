package mariten.kanatools.KanaConverterTests;

import mariten.kanatools.KanaConverter;
import mariten.kanatools.KanaConverterTester;
import org.junit.Test;
import static org.junit.Assert.*;

public class SingleOpTest extends KanaConverterTester
{
    //{{{ 'A': testHankakuAlphanumericToZenkakuAlphanumeric()
    @Test
    public void testHankakuAlphanumericToZenkakuAlphanumeric()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " ！\"＃＄％＆'（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［\\］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝~"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_ALPHANUMERIC_TO_ZENKAKU_ALPHANUMERIC,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC,
            "　！”＃＄％＆’（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～",
            "　!”#$%&’()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[＼]^_`abcdefghijklmnopqrstuvwxyz{|}～"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_ALPHANUMERIC_TO_HANKAKU_ALPHANUMERIC,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA,
            "〶 | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん | ゔゕゖゝゞ",
            "〶 | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲン | ゔゕゖゝゞ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_ZENKAKU_KATAKANA,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "んゔゕゖゝゞ | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴ | ヵヶヷヸヹヺ・ーヽヾ",
            "んゔゕゖゝゞ | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんヴ | ヵヶヷヸヹヺ・ーヽヾ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
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
        super.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこか゛き゛く゛け゛こ゛さしすせそさ゛し゛す゛せ゛そ゛たちつてとた゛ち゛つ゛て゛と゛なにぬねのはひふへほは゛ひ゛ふ゛へ゛ほ゛は゜ひ゜ふ゜へ゜ほ゜まみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_HIRAGANA,
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
        super.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        super.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aそざざ゛じすぱぱ゜ひ1-"
        );

        // Check mixed input
        super.assertConverted(op_flags,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろゎわゐゑをんゔ゛゜ﾡゕゖゝゞヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝゔﾞﾟﾡゕゖゝゞヽヾ"
        );

        // Check logic for affixing diacritic marks
        super.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
            "Aそざざ゛じすぱぱ゜ひ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_HIRAGANA_TO_HANKAKU_KATAKANA,
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
        super.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコカ゛キ゛ク゛ケ゛コ゛サシスセソサ゛シ゛ス゛セ゛ソ゛タチツテトタ゛チ゛ツ゛テ゛ト゛ナニヌネノハヒフヘホハ゛ヒ゛フ゛ヘ゛ホ゛ハ゜ヒ゜フ゜ヘ゜ホ゜マミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_KATAKANA_TO_ZENKAKU_KATAKANA,
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
        super.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        super.assertConverted(op_flags,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aソザザ゛ジスパパ゜ヒ1-"
        );

        // Check mixed input
        super.assertConverted(op_flags,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロヮワヰヱヲンヴ゛゜ﾡヵヶヷヸヹヺヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞﾞﾟﾡヵヶヷヸヹヺヽヾ"
        );

        // Check logic for affixing diacritic marks
        super.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
            "Aソザザ゛ジスパパ゜ヒ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_KATAKANA_TO_HANKAKU_KATAKANA,
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
        super.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
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
        super.assertConverted(KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " !\"#$%&'()*+,-./0123456789:;<=>?@ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ[\\]^_`ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ{|}~"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_LETTER_TO_ZENKAKU_LETTER,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER,
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［￥］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝〜",
            "　！”＃＄％＆’（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ABCDEFGHIJKLMNOPQRSTUVWXYZ［￥］＾＿｀abcdefghijklmnopqrstuvwxyz｛｜｝〜"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_LETTER_TO_HANKAKU_LETTER,
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
        super.assertConverted(KanaConverter.OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE,
            "Little spaces　and big spaces",
            "Little　spaces　and　big　spaces"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HANKAKU_SPACE_TO_ZENKAKU_SPACE,
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
        super.assertConverted(KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE,
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ　ａｎｄ ｂｉｇ　ｓｐａｃｅｓ",
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ ａｎｄ ｂｉｇ ｓｐａｃｅｓ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZENKAKU_SPACE_TO_HANKAKU_SPACE,
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
        super.assertConverted(KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_COLLAPSE_HANKAKU_VOICE_MARKS,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}
}
