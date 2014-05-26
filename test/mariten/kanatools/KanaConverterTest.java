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


    //{{{ 'N': testHankakuNumberToZenkakuNumber()
    @Test
    public void testHankakuNumberToZenkakuNumber()
    {
        this.assertConverted(KanaConverter.OP_HANKAKU_NUMBER_TO_ZENKAKU_NUMBER,
            "/0123456789:",
            "/０１２３４５６７８９:"
        );
    }
    //}}}


    //{{{ 'n': testZenkakuNumberToHankakuNumber()
    @Test
    public void testZenkakuNumberToHankakuNumber()
    {
        this.assertConverted(KanaConverter.OP_ZENKAKU_NUMBER_TO_HANKAKU_NUMBER,
            "／０１２３４５６７８９：",
            "／0123456789："
        );
    }
    //}}}


    //{{{ assertConverted()
    private void assertConverted(String conv_options, String str_to_convert, String expected_result)
    {
        assertEquals(expected_result, convert_tester.mbConvertKana(str_to_convert, conv_options, "utf8"));
    }
    private void assertConverted(char conv_option, String str_to_convert, String expected_result) {
        assertConverted(String.valueOf(conv_option), str_to_convert, expected_result);
    }
    //}}}
}
