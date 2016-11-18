package com.mariten.kanatools.TestsKanaRomanizer;
import com.mariten.kanatools.KanaRomanizer;
import com.mariten.kanatools.KanaRomanizerTester;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicTest extends KanaRomanizerTester
{
    //{{{ testBasic()
    @Test
    public void testBasic()
    {
        assertEquals("aki",         KanaRomanizer.romanizeKana("あき"));
        assertEquals("kokyu",       KanaRomanizer.romanizeKana("コウキュウ"));
        assertEquals("kokyo",       KanaRomanizer.romanizeKana("こうきょ"));
        assertEquals("kuko",        KanaRomanizer.romanizeKana("クうこウ"));
        assertEquals("abikyokan",   KanaRomanizer.romanizeKana("あびきょうかん"));
    }
    //}}}
}
