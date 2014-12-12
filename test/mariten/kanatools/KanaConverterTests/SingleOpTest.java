package mariten.kanatools.KanaConverterTests;

import mariten.kanatools.KanaConverter;
import mariten.kanatools.KanaConverterTester;
import org.junit.Test;
import static org.junit.Assert.*;

public class SingleOpTest extends KanaConverterTester
{
    //{{{ 'A': testHankakuAsciiToZenkakuAscii()
    @Test
    public void testHankakuAsciiToZenkakuAscii()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_ASCII_TO_ZEN_ASCII,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            "　！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝~"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_ASCII_TO_ZEN_ASCII,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            "　！０：Ａ＾ａ｜＂　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢　＃１；Ｂ＿ｂ｝＼　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'a': testZenkakuAsciiToHankakuAscii()
    @Test
    public void testZenkakuAsciiToHankakuAscii()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII,
            "　！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～",
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}～"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\" !0:A^a|\"あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\ #1;B_b}\\・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'C': testZenkakuHiraganaToZenkakuKatakana()
    @Test
    public void testZenkakuHiraganaToZenkakuKatakana()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA,
            "〶 | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん | ゔゕゖゝゞ",
            "〶 | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲン | ゔゕゖゝゞ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂アガパヰゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・キジピヱゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'c': testZenkakuKatakanaToZenkakuHiragana()
    @Test
    public void testZenkakuKatakanaToZenkakuHiragana()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_KATA_TO_ZEN_HIRA,
            "んゔゕゖゝゞ | ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴ | ヵヶヷヸヹヺ・ーヽヾ",
            "んゔゕゖゝゞ | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんヴ | ヵヶヷヸヹヺ・ーヽヾ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_KATA_TO_ZEN_HIRA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝあがぱゐヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞきじぴゑヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'H': testHankakuKatakanaToZenkakuHiragana()
    @Test
    public void testHankakuKatakanaToZenkakuHiragana()
    {
        // Check all target chars
        int op_flags = 0;
        op_flags |= KanaConverter.OP_HAN_KATA_TO_ZEN_HIRA;
        op_flags |= KanaConverter.OP_KEEP_DIACRITIC_MARKS_APART;
        super.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこか゛き゛く゛け゛こ゛さしすせそさ゛し゛す゛せ゛そ゛たちつてとた゛ち゛つ゛て゛と゛なにぬねのはひふへほは゛ひ゛ふ゛へ゛ほ゛は゜ひ゜ふ゜へ゜ほ゜まみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check mixed input
        super.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛。あか゛は゜゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・きし゛ひ゜゜字"
        );
    }
    //}}}


    //{{{ 'HV': testHankakuKatakanaToZenkakuHiraganaWithoutCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuHiraganaWithoutCollapse()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_HIRA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろわをん゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_HIRA,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aそざざ゛じすぱぱ゜ひ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_HIRA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛。あがぱ゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・きじぴ゜字"
        );
    }
    //}}}


    //{{{ 'h': testZenkakuHiraganaToHankakuKatakana()
    @Test
    public void testZenkakuHiraganaToHankakuKatakana()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_HAN_KATA,
            "～'。「」、・ぁぃぅぇぉゃゅょっーあいうえおかきくけこがぎぐげごさしすせそざじずぜぞたちつてとだぢづでどなにぬねのはひふへほばびぶべぼぱぴぷぺぽまみむめもやゆよらりるれろゎわゐゑをんゔ゛゜ﾡゕゖゝゞヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝゔﾞﾟﾡゕゖゝゞヽヾ"
        );

        // Check logic for affixing diacritic marks
        super.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_HAN_KATA,
            "Aそざざ゛じすぱぱ゜ひ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_HAN_KATA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂ｱｶﾞﾊﾟｲゔゕゝアガパヰヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼･ｷｼﾞﾋﾟｴゖゞキジピヱヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'K': testHankakuKatakanaToZenkakuKatakana()
    @Test
    public void testHankakuKatakanaToZenkakuKatakana()
    {
        // Check all target chars
        int op_flags = 0;
        op_flags |= KanaConverter.OP_HAN_KATA_TO_ZEN_KATA;
        op_flags |= KanaConverter.OP_KEEP_DIACRITIC_MARKS_APART;
        super.assertConverted(op_flags,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコカ゛キ゛ク゛ケ゛コ゛サシスセソサ゛シ゛ス゛セ゛ソ゛タチツテトタ゛チ゛ツ゛テ゛ト゛ナニヌネノハヒフヘホハ゛ヒ゛フ゛ヘ゛ホ゛ハ゜ヒ゜フ゜ヘ゜ホ゜マミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check mixed input
        super.assertConverted(op_flags,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アカ゛ハ゜゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・キシ゛ヒ゜゜字"
        );
    }
    //}}}


    //{{{ 'KV': testHankakuKatakanaToZenkakuKatakanaWithoutCollapse()
    @Test
    public void testHankakuKatakanaToZenkakuKatakanaWithoutCollapse()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_KATA,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲン゛゜ﾡ"
        );

        // Check logic for collapsing diacritic marks
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_KATA,
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-",
            "Aソザザ゛ジスパパ゜ヒ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_KATA_TO_ZEN_KATA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛。アガパ゛漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜・キジピ゜字"
        );
    }
    //}}}


    //{{{ 'k': testZenkakuKatakanaToHankakuKatakana()
    @Test
    public void testZenkakuKatakanaToHankakuKatakana()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_KATA_TO_HAN_KATA,
            "～'。「」、・ァィゥェォャュョッーアイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロヮワヰヱヲンヴ゛゜ﾡヵヶヷヸヹヺヽヾ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞﾞﾟﾡヵヶヷヸヹヺヽヾ"
        );

        // Check logic for affixing diacritic marks
        super.assertConverted(KanaConverter.OP_ZEN_KATA_TO_HAN_KATA,
            "Aソザザ゛ジスパパ゜ヒ1-",
            "Aｿｻﾞｻﾞﾞｼﾞｽﾊﾟﾊﾟﾟﾋ1-"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_KATA_TO_HAN_KATA,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝｱｶﾞﾊﾟｲヸヷヵヽﾞ｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼･きじぴゑゖゞｷｼﾞﾋﾟｴヹヶヾﾟ･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'N': testHankakuNumberToZenkakuNumber()
    @Test
    public void testHankakuNumberToZenkakuNumber()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_NUMBER_TO_ZEN_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_NUMBER_TO_ZEN_NUMBER,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !０:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #１;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'n': testZenkakuNumberToHankakuNumber()
    @Test
    public void testZenkakuNumberToHankakuNumber()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_NUMBER_TO_HAN_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_NUMBER_TO_HAN_NUMBER,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！0：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃1；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'R': testHankakuLetterToZenkakuLetter()
    @Test
    public void testHankakuLetterToZenkakuLetter()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_LETTER_TO_ZEN_LETTER,
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            " !\"#$%&'()*+,-./0123456789:;<=>?@ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ[\\]^_`ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ{|}~"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_LETTER_TO_ZEN_LETTER,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:Ａ^ａ|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;Ｂ_ｂ}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'r': testZenkakuLetterToHankakuLetter()
    @Test
    public void testZenkakuLetterToHankakuLetter()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_LETTER_TO_HAN_LETTER,
            "　！＂＃＄％＆＇（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［￥］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝〜",
            "　！＂＃＄％＆＇（）＊＋，−．／０１２３４５６７８９：；＜＝＞？＠ABCDEFGHIJKLMNOPQRSTUVWXYZ［￥］＾＿｀abcdefghijklmnopqrstuvwxyz｛｜｝〜"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_LETTER_TO_HAN_LETTER,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：A＾a｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；B＿b｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 'S': testHankakuSpaceToZenkakuSpace()
    @Test
    public void testHankakuSpaceToZenkakuSpace()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_HAN_SPACE_TO_ZEN_SPACE,
            "Little spaces　and big spaces",
            "Little　spaces　and　big　spaces"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_HAN_SPACE_TO_ZEN_SPACE,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            "　!0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢　#1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}


    //{{{ 's': testZenkakuSpaceToHankakuSpace()
    @Test
    public void testZenkakuSpaceToHankakuSpace()
    {
        // Check all target chars
        super.assertConverted(KanaConverter.OP_ZEN_SPACE_TO_HAN_SPACE,
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ　ａｎｄ ｂｉｇ　ｓｐａｃｅｓ",
            "Ｌｉｔｔｌｅ ｓｐａｃｅｓ ａｎｄ ｂｉｇ ｓｐａｃｅｓ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_ZEN_SPACE_TO_HAN_SPACE,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\" ！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\ ＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );

        // Test that converted spaces are trimmed by the "trim" function
        String trimmed_test = KanaConverter.convertKana("　テスト　　", KanaConverter.OP_ZEN_SPACE_TO_HAN_SPACE).trim();
        assertEquals("テスト", trimmed_test);
    }
    //}}}


    //{{{ 'v': testCollapseHankakuMarkOnly()
    @Test
    public void testCollapseHankakuMarkOnly()
    {
        // This option is illegal, no conversion should be performed
        super.assertConverted(KanaConverter.OP_KEEP_DIACRITIC_MARKS_APART,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ"
        );

        // Check mixed input
        super.assertConverted(KanaConverter.OP_KEEP_DIACRITIC_MARKS_APART,
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜＂あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );
    }
    //}}}
}
