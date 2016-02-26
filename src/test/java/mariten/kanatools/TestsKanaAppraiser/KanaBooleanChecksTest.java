package mariten.kanatools.TestsKanaAppraiser;
import mariten.kanatools.KanaAppraiser;
import mariten.kanatools.KanaAppraiserTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class KanaBooleanChecksTest extends KanaAppraiserTester
{
    //{{{ testZenkakuHiraganaChecks()
    @Test
    public void testZenkakuHiraganaChecks()
    {
        assertEquals(false, KanaAppraiser.isZenkakuHiragana        ('〶'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuHiragana('〶'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('ぁ'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuHiragana('ぁ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('あ'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuHiragana('あ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('を'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuHiragana('を'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('ん'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuHiragana('ん'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('ゔ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuHiragana('ゔ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('ゕ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuHiragana('ゕ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana        ('ゖ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuHiragana('ゖ'));

        assertEquals(false, KanaAppraiser.isZenkakuHiragana        ('゛'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuHiragana('゛'));
    }
    //}}}


    //{{{ testZenkakuKatakanaChecks()
    @Test
    public void testZenkakuKatakanaChecks()
    {
        assertEquals(false, KanaAppraiser.isZenkakuKatakana        ('ゞ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('ゞ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ァ'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuKatakana('ァ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ア'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuKatakana('ア'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ヲ'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuKatakana('ヲ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ン'));
        assertEquals(true,  KanaAppraiser.isMappableZenkakuKatakana('ン'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ヴ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('ヴ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ヵ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('ヵ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ヹ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('ヹ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana        ('ヺ'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('ヺ'));

        assertEquals(false, KanaAppraiser.isZenkakuKatakana        ('・'));
        assertEquals(false, KanaAppraiser.isMappableZenkakuKatakana('・'));
    }
    //}}}


    //{{{ testHankakuKatakanaChecks()
    @Test
    public void testHankakuKatakanaChecks()
    {
        assertEquals(false, KanaAppraiser.isHankakuKatakana('､'));
        assertEquals(false, KanaAppraiser.isHankakuKatakana('･'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ｦ'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ｧ'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ﾜ'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ﾝ'));
        assertEquals(false, KanaAppraiser.isHankakuKatakana('ﾞ'));
        assertEquals(false, KanaAppraiser.isHankakuKatakana('ﾟ'));
    }
    //}}}
}
