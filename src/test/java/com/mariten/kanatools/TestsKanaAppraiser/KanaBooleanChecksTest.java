package com.mariten.kanatools.TestsKanaAppraiser;
import com.mariten.kanatools.KanaAppraiser;
import com.mariten.kanatools.KanaAppraiserTester;
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

        assertEquals(false, KanaAppraiser.isZenkakuKatakana                      ('ー'));
        assertEquals(false, KanaAppraiser.isZenkakuKatakanaWithHiraganaEquivalent('ー'));
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
        assertEquals(false, KanaAppraiser.isHankakuKatakana('ｰ'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ﾜ'));
        assertEquals(true,  KanaAppraiser.isHankakuKatakana('ﾝ'));
        assertEquals(false, KanaAppraiser.isHankakuKatakana('ﾞ'));
        assertEquals(false, KanaAppraiser.isHankakuKatakana('ﾟ'));
    }
    //}}}


    //{{{ testZenkakuKutotenChecks()
    @Test
    public void testZenkakuKutotenChecks()
    {
        assertEquals(false, KanaAppraiser.isZenkakuKutoten('　'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('、'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('。'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('〛'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('〜'));
        assertEquals(false, KanaAppraiser.isZenkakuKutoten('〠'));
        assertEquals(false, KanaAppraiser.isZenkakuKutoten('〡'));

        assertEquals(false, KanaAppraiser.isZenkakuKutoten('ゖ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('゛'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('゜'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('ゝ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('ゞ'));
        assertEquals(false, KanaAppraiser.isZenkakuKutoten('ゟ'));

        assertEquals(false, KanaAppraiser.isZenkakuKutoten('ヺ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('・'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('ー'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('ヽ'));
        assertEquals(true,  KanaAppraiser.isZenkakuKutoten('ヾ'));
        assertEquals(false, KanaAppraiser.isZenkakuKutoten('ヿ'));
    }
    //}}}


    //{{{ testHankakuKutotenChecks()
    @Test
    public void testHankakuKutotenChecks()
    {
        assertEquals(false, KanaAppraiser.isHankakuKutoten('～'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('｡'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('｢'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('｣'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('､'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('･'));
        assertEquals(false, KanaAppraiser.isHankakuKutoten('ｦ'));
        assertEquals(false, KanaAppraiser.isHankakuKutoten('ｧ'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('ｰ'));
        assertEquals(false, KanaAppraiser.isHankakuKutoten('ﾜ'));
        assertEquals(false, KanaAppraiser.isHankakuKutoten('ﾝ'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('ﾞ'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('ﾟ'));
        assertEquals(true,  KanaAppraiser.isHankakuKutoten('ﾟ'));
        assertEquals(false, KanaAppraiser.isHankakuKutoten('ﾡ'));
    }
    //}}}
}
