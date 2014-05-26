package mariten.kanatools;

import mariten.kanatools.KanaConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class KanaConverterTest
{
    private KanaConverter convert_tester = new KanaConverter();


    //{{{ testErrorCases()
    @Test
    public void testErrorCases()
    {
        assertNull(convert_tester.mbConvertKana(null, "C", "utf8"));
    }
    //}}}
}
