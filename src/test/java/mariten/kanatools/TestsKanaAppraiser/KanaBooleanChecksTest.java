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
        assertEquals(false, KanaAppraiser.isZenkakuHiragana                      ('〶'));
        assertEquals(false, KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('〶'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('ぁ'));
        assertEquals(true,  KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('ぁ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('あ'));
        assertEquals(true,  KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('あ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('を'));
        assertEquals(true,  KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('を'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('ん'));
        assertEquals(true,  KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('ん'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('ゔ'));
        assertEquals(false, KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('ゔ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('ゕ'));
        assertEquals(false, KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('ゕ'));

        assertEquals(true,  KanaAppraiser.isZenkakuHiragana                      ('ゖ'));
        assertEquals(false, KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('ゖ'));

        assertEquals(false, KanaAppraiser.isZenkakuHiragana                      ('゛'));
        assertEquals(false, KanaAppraiser.isZenkakuHiraganaWithKatakanaEquivalent('゛'));
    }
    //}}}


    //{{{ testZenkakuKatakanaChecks()
    @Test
    public void testZenkakuKatakanaChecks()
    {
        assertEquals(false, KanaAppraiser.isZenkakuKatakana                      ('ゞ'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ゞ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ァ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ァ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ア'));
        assertEquals(true,  KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ア'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ヲ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ヲ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ン'));
        assertEquals(true,  KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ン'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ヴ'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ヴ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ヵ'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ヵ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ヹ'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ヹ'));

        assertEquals(true,  KanaAppraiser.isZenkakuKatakana                      ('ヺ'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ヺ'));

        assertEquals(false, KanaAppraiser.isZenkakuKatakana                      ('・'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('・'));
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
