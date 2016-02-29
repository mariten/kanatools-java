package com.mariten.kanatools.TestsKanaConverter;

import com.mariten.kanatools.KanaConverter;
import com.mariten.kanatools.KanaConverterTester;
import org.junit.Test;

public class IgnoreCharsTest extends KanaConverterTester
{
    //{{{ testAsciiIgnoreChars()
    @Test
    public void testAsciiIgnoreChars()
    {
        String chars_to_exclude = "ABCＤＥＦ:；～";
        super.assertConvertedWithExclusions(KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII, chars_to_exclude,
            "　！＂＃＄％＆＇（）＊＋，－．／０１２３４５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［＼］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｜｝～",
            " !\"#$%&'()*+,-./0123456789:；<=>?@ABCＤＥＦGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}～"
        );
    }
    //}}}


    //{{{ testKanaIgnoreChars()
    @Test
    public void testKanaIgnoreChars()
    {
        String chars_to_exclude = "あがサゐゑ";
        super.assertConvertedWithExclusions(KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA, chars_to_exclude,
            "〶 | ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん | ゔゕゖゝゞ",
            "〶 | ァあィイゥウェエォオカがキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワゐゑヲン | ゔゕゖゝゞ"
        );
    }
    //}}}


    //{{{ testHankakuIgnoreChars()
    @Test
    public void testHankakuIgnoreChars()
    {
        String chars_to_exclude = "｢｣ｱｶﾞサ";
        super.assertConvertedWithExclusions(KanaConverter.OP_HAN_KATA_TO_ZEN_KATA, chars_to_exclude,
            "～'｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｶﾞｷﾞｸﾞｹﾞｺﾞｻｼｽｾｿｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾁﾂﾃﾄﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝﾞﾟﾡ",
            "～'。｢｣、・ァィゥェォャュョッーｱイウエオｶキクケコｶﾞギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲンﾞ゜ﾡ"
        );
    }
    //}}}


    //{{{ testIgnoreCharsWithNoMatches()
    @Test
    public void testIgnoreCharsWithNoMatches()
    {
        String chars_to_exclude = "東京A";
        int conv_ops = 0;
        conv_ops |= KanaConverter.OP_ZEN_KATA_TO_ZEN_HIRA;
        conv_ops |= KanaConverter.OP_ZEN_ASCII_TO_HAN_ASCII;

        // No matches in ignore list
        super.assertConvertedWithExclusions(conv_ops, chars_to_exclude,
            "トウキョウトＡＢＣ",
            "とうきょうとABC"
        );

        // Ignore list is empty string
        super.assertConvertedWithExclusions(conv_ops, "",
            "トウキョウトＡＢＣ",
            "とうきょうとABC"
        );
    }
    //}}}
}
