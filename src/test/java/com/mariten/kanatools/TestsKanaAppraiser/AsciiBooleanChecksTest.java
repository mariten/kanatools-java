package com.mariten.kanatools.TestsKanaAppraiser;
import com.mariten.kanatools.KanaAppraiser;
import com.mariten.kanatools.KanaAppraiserTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsciiBooleanChecksTest extends KanaAppraiserTester
{
    //{{{ testZenkakuAsciiChecks()
    @Test
    public void testZenkakuAsciiChecks()
    {
        assertEquals(false, KanaAppraiser.isZenkakuAscii ('　'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('　'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('　'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('！'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('！'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('！'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('＂'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('＂'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('＂'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('．'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('．'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('．'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('／'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('／'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('／'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('０'));
        assertEquals(true,  KanaAppraiser.isZenkakuNumber('０'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('０'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('１'));
        assertEquals(true,  KanaAppraiser.isZenkakuNumber('１'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('１'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('８'));
        assertEquals(true,  KanaAppraiser.isZenkakuNumber('８'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('８'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('９'));
        assertEquals(true,  KanaAppraiser.isZenkakuNumber('９'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('９'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('：'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('：'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('：'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('；'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('；'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('；'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('？'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('？'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('？'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('＠'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('＠'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('＠'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('Ａ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('Ａ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('Ａ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('Ｂ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('Ｂ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('Ｂ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('Ｙ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('Ｙ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('Ｙ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('Ｚ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('Ｚ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('Ｚ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('［'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('［'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('［'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('＼'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('＼'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('＼'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('＿'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('＿'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('＿'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('｀'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('｀'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('｀'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('ａ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('ａ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('ａ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('ｂ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('ｂ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('ｂ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('ｙ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('ｙ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('ｙ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('ｚ'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('ｚ'));
        assertEquals(true,  KanaAppraiser.isZenkakuLetter('ｚ'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('｛'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('｛'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('｛'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('｜'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('｜'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('｜'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('｝'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('｝'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('｝'));

        assertEquals(true,  KanaAppraiser.isZenkakuAscii ('～'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('～'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('～'));

        assertEquals(false, KanaAppraiser.isZenkakuAscii ('｟'));
        assertEquals(false, KanaAppraiser.isZenkakuNumber('｟'));
        assertEquals(false, KanaAppraiser.isZenkakuLetter('｟'));
    }
    //}}}


    //{{{ testHankakuAsciiChecks()
    @Test
    public void testHankakuAsciiChecks()
    {
        assertEquals(false, KanaAppraiser.isHankakuAscii (' '));
        assertEquals(false, KanaAppraiser.isHankakuNumber(' '));
        assertEquals(false, KanaAppraiser.isHankakuLetter(' '));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('!'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('!'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('!'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('"'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('"'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('"'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('.'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('.'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('.'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('/'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('/'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('/'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('0'));
        assertEquals(true,  KanaAppraiser.isHankakuNumber('0'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('0'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('1'));
        assertEquals(true,  KanaAppraiser.isHankakuNumber('1'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('1'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('8'));
        assertEquals(true,  KanaAppraiser.isHankakuNumber('8'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('8'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('9'));
        assertEquals(true,  KanaAppraiser.isHankakuNumber('9'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('9'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii (':'));
        assertEquals(false, KanaAppraiser.isHankakuNumber(':'));
        assertEquals(false, KanaAppraiser.isHankakuLetter(':'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii (';'));
        assertEquals(false, KanaAppraiser.isHankakuNumber(';'));
        assertEquals(false, KanaAppraiser.isHankakuLetter(';'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('?'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('?'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('?'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('@'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('@'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('@'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('A'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('A'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('A'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('B'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('B'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('B'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('Y'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('Y'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('Y'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('Z'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('Z'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('Z'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('['));
        assertEquals(false, KanaAppraiser.isHankakuNumber('['));
        assertEquals(false, KanaAppraiser.isHankakuLetter('['));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('\\'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('\\'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('\\'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('_'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('_'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('_'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('`'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('`'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('`'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('a'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('a'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('a'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('b'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('b'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('b'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('y'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('y'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('y'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('z'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('z'));
        assertEquals(true,  KanaAppraiser.isHankakuLetter('z'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('{'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('{'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('{'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('|'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('|'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('|'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('}'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('}'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('}'));

        assertEquals(true,  KanaAppraiser.isHankakuAscii ('~'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('~'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('~'));

        assertEquals(false, KanaAppraiser.isHankakuAscii ('¡'));
        assertEquals(false, KanaAppraiser.isHankakuNumber('¡'));
        assertEquals(false, KanaAppraiser.isHankakuLetter('¡'));
    }
    //}}}
}
