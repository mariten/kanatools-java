package com.mariten.kanatools.TestsKanaConverter;

import com.mariten.kanatools.KanaConverter;
import com.mariten.kanatools.KanaConverterTester;
import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorCasesTest extends KanaConverterTester
{
    //{{{ testErrorCases()
    @Test
    public void testErrorCases()
    {
        // Pass empty string for input string
        this.assertConverted(KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA,
            "",
            ""
        );


        // Pass 0 (no methods) for operation flags, ensure no changes
        this.assertConverted(0, super.NEVER_TEST_IN_PHP,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );


        // Pass negative integer for operation flags, ensure no changes
        int negative_op = 0 - KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA;
        this.assertConverted(negative_op, super.NEVER_TEST_IN_PHP,
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字",
            " !0:A^a|\"　！０：Ａ＾ａ｜”あがぱゐゔゕゝアガパヰヸヷヵヽ゛｡ｱｶﾞﾊﾟﾞ漢 #1;B_b}\\　＃１；Ｂ＿ｂ｝＼・きじぴゑゖゞキジピヱヹヶヾ゜･ｷｼﾞﾋﾟﾟ字"
        );


        // Non-existant single PHP op - ensure no changes
        String single_op = "J";
        this.assertConverted(single_op, "かＡ", "かＡ");


        // Non-existant multiple PHP ops - ensure "j" operation is ignored
        String multi_ops = "Caj";
        this.assertConverted(multi_ops, "かＡ", "カA");


        // Pass null for input string, ensure NullPointerException is thrown
        String null_input_result = "not null";
        try {
            null_input_result = KanaConverter.convertKana(null, KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA);
        }
        catch(NullPointerException null_input_ex) {
            null_input_result = "caught exception";
        }
        assertEquals("caught exception", null_input_result);


        // Pass null for operation flags, ensure NullPointerException is thrown
        String null_ops_result = "";
        try {
            null_ops_result = KanaConverter.convertKana("A", null);
        }
        catch(NullPointerException null_ops_ex) {
            null_ops_result = "caught exception";
        }
        assertEquals("caught exception", null_ops_result);
    }
    //}}}
}
